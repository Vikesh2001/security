package com.security.testing.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.security.testing.dto.FeeDto;
import com.security.testing.dto.FeePaymentDto;
import com.security.testing.dto.FeeStatusDto;
import com.security.testing.entity.Fee;
import com.security.testing.entity.FeePayment;
import com.security.testing.entity.SchoolClass;
import com.security.testing.entity.Student;
import com.security.testing.exception.ResourceNotFoundException;
import com.security.testing.repository.FeePaymentRepository;
import com.security.testing.repository.FeeRepository;
import com.security.testing.repository.SchoolClassRepository;
import com.security.testing.repository.StudentRepository;

@Service
public class FeeService {
    
    private final FeeRepository feeRepository;
    private final FeePaymentRepository feePaymentRepository;
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private final SchoolClassRepository schoolClassRepository;
    
    
    
    public FeeService(FeeRepository feeRepository, FeePaymentRepository feePaymentRepository,
			StudentRepository studentRepository, ModelMapper modelMapper, SchoolClassRepository schoolClassRepository) {
		this.feeRepository = feeRepository;
		this.feePaymentRepository = feePaymentRepository;
		this.studentRepository = studentRepository;
		this.modelMapper = modelMapper;
		this.schoolClassRepository = schoolClassRepository;
	}

	public FeeDto createFee(FeeDto feeDto) {
        SchoolClass schoolClass = schoolClassRepository.findById(feeDto.getClassId())
                .orElseThrow(() -> new ResourceNotFoundException("Class", "id", feeDto.getClassId()));
        
        Fee fee = modelMapper.map(feeDto, Fee.class);
        fee.setSchoolClass(schoolClass);
        
        Fee savedFee = feeRepository.save(fee);
        return modelMapper.map(savedFee, FeeDto.class);
    }
    
    public FeeStatusDto getStudentFeeStatus(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));
        
        List<Fee> fees = feeRepository.findBySchoolClass(student.getSchoolClass());
        List<FeePayment> payments = feePaymentRepository.findByStudent(student);
        
        FeeStatusDto feeStatus = new FeeStatusDto();
        feeStatus.setStudentId(studentId);
        feeStatus.setStudentName(student.getFirstName() + " " + student.getLastName());
        
        BigDecimal totalFees = fees.stream()
                .map(Fee::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        BigDecimal totalPaid = payments.stream()
                .map(FeePayment::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        feeStatus.setTotalFees(totalFees);
        feeStatus.setTotalPaid(totalPaid);
        feeStatus.setBalance(totalFees.subtract(totalPaid));
        feeStatus.setFeePayments(payments.stream()
                .map(payment -> modelMapper.map(payment, FeePaymentDto.class))
                .collect(Collectors.toList()));
        
        return feeStatus;
    }
    
    public List<FeeDto> getAllFees() {
        return feeRepository.findAll().stream()
                .map(fee -> {
                    FeeDto dto = modelMapper.map(fee, FeeDto.class);
                    dto.setClassName(fee.getSchoolClass().getName());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
