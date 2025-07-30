package com.security.testing.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.security.testing.dto.AttendanceDto;
import com.security.testing.entity.Attendance;
import com.security.testing.entity.SchoolClass;
import com.security.testing.entity.Student;
import com.security.testing.exception.ResourceNotFoundException;
import com.security.testing.repository.AttendanceRepository;
import com.security.testing.repository.SchoolClassRepository;
import com.security.testing.repository.StudentRepository;

@Service
public class AttendanceService {
    
    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private final SchoolClassRepository schoolClassRepository;
    
    
    
    public AttendanceService(AttendanceRepository attendanceRepository, StudentRepository studentRepository,
			ModelMapper modelMapper,SchoolClassRepository schoolClassRepository) {
		this.attendanceRepository = attendanceRepository;
		this.studentRepository = studentRepository;
		this.modelMapper = modelMapper;
		this.schoolClassRepository = schoolClassRepository;
	}

	public AttendanceDto markAttendance(AttendanceDto attendanceDto) {
        Student student = studentRepository.findById(attendanceDto.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", attendanceDto.getStudentId()));
        
        Attendance attendance = modelMapper.map(attendanceDto, Attendance.class);
        attendance.setStudent(student);
        
        Attendance savedAttendance = attendanceRepository.save(attendance);
        return modelMapper.map(savedAttendance, AttendanceDto.class);
    }
    
    public List<AttendanceDto> getStudentAttendance(Long studentId, LocalDate startDate, LocalDate endDate) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));
        
        List<Attendance> attendances;
        if (startDate != null && endDate != null) {
            attendances = attendanceRepository.findByStudentAndDateBetween(student, startDate, endDate);
        } else {
            attendances = attendanceRepository.findByStudent(student);
        }
        
        return attendances.stream()
                .map(attendance -> modelMapper.map(attendance, AttendanceDto.class))
                .collect(Collectors.toList());
    }
    
    public List<AttendanceDto> getClassAttendance(Long classId, LocalDate date) {
        SchoolClass schoolClass = schoolClassRepository.findById(classId)
                .orElseThrow(() -> new ResourceNotFoundException("Class", "id", classId));
        
        List<Attendance> attendances = attendanceRepository.findBySchoolClassAndDate(schoolClass, date);
        return attendances.stream()
                .map(attendance -> modelMapper.map(attendance, AttendanceDto.class))
                .collect(Collectors.toList());
    }
}
