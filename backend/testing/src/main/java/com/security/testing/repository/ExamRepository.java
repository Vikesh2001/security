package com.security.testing.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.security.testing.entity.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {
    List<Exam> findByStartDateBetween(LocalDate startDate, LocalDate endDate);
    List<Exam> findByEndDateBefore(LocalDate date);
    List<Exam> findByStartDateAfter(LocalDate date);
    
    @Query("SELECT e FROM Exam e WHERE e.startDate <= :date AND e.endDate >= :date")
    List<Exam> findCurrentExams(LocalDate date);
}
