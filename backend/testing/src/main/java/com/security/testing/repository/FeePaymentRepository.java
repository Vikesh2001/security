package com.security.testing.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.security.testing.entity.Fee;
import com.security.testing.entity.FeePayment;
import com.security.testing.entity.Student;

public interface FeePaymentRepository extends JpaRepository<FeePayment, Long> {
    List<FeePayment> findByStudent(Student student);
    List<FeePayment> findByFee(Fee fee);
    List<FeePayment> findByPaymentDateBetween(LocalDate startDate, LocalDate endDate);
    
    @Query("SELECT fp FROM FeePayment fp WHERE fp.student.id = :studentId")
    List<FeePayment> findByStudentId(Long studentId);
    
    @Query("SELECT fp FROM FeePayment fp WHERE fp.fee.id = :feeId")
    List<FeePayment> findByFeeId(Long feeId);
    
    @Query("SELECT fp FROM FeePayment fp WHERE fp.student.id = :studentId AND fp.fee.id = :feeId")
    List<FeePayment> findByStudentIdAndFeeId(Long studentId, Long feeId);
    
    @Query("SELECT SUM(fp.amount) FROM FeePayment fp WHERE fp.student.id = :studentId")
    BigDecimal sumPaidAmountByStudentId(Long studentId);
    
    @Query("SELECT SUM(fp.amount) FROM FeePayment fp WHERE fp.student.id = :studentId AND fp.fee.id = :feeId")
    BigDecimal sumPaidAmountByStudentAndFee(Long studentId, Long feeId);
    
    @Query("SELECT SUM(f.amount) FROM Fee f WHERE f.schoolClass.id = (SELECT s.schoolClass.id FROM Student s WHERE s.id = :studentId)")
    BigDecimal sumTotalFeesByStudentId(Long studentId);
}
