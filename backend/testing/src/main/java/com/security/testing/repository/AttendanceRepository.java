package com.security.testing.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.security.testing.entity.Attendance;
import com.security.testing.entity.SchoolClass;
import com.security.testing.entity.Section;
import com.security.testing.entity.Student;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByStudent(Student student);
    List<Attendance> findBySchoolClass(SchoolClass schoolClass);
    List<Attendance> findBySection(Section section);
    List<Attendance> findByDate(LocalDate date);
    List<Attendance> findByDateBetween(LocalDate startDate, LocalDate endDate);
    List<Attendance> findByStudentAndDateBetween(Student student, LocalDate startDate, LocalDate endDate);
    // Add this new method
    List<Attendance> findBySchoolClassAndDate(SchoolClass schoolClass, LocalDate date);
    
    @Query("SELECT a FROM Attendance a WHERE a.student.id = :studentId AND a.date BETWEEN :startDate AND :endDate")
    List<Attendance> findByStudentIdAndDateBetween(Long studentId, LocalDate startDate, LocalDate endDate);
    
    @Query("SELECT a FROM Attendance a WHERE a.schoolClass.id = :classId AND a.date = :date")
    List<Attendance> findByClassIdAndDate(Long classId, LocalDate date);
    
    @Query("SELECT a FROM Attendance a WHERE a.schoolClass.id = :classId AND a.section.id = :sectionId AND a.date = :date")
    List<Attendance> findByClassAndSectionAndDate(Long classId, Long sectionId, LocalDate date);
    
    @Query("SELECT COUNT(a) FROM Attendance a WHERE a.student.id = :studentId AND a.status = 'PRESENT'")
    Long countPresentByStudentId(Long studentId);
    
    @Query("SELECT COUNT(a) FROM Attendance a WHERE a.student.id = :studentId")
    Long countTotalByStudentId(Long studentId);
}