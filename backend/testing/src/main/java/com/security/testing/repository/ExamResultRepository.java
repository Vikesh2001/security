package com.security.testing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.security.testing.entity.Exam;
import com.security.testing.entity.ExamResult;
import com.security.testing.entity.Student;
import com.security.testing.entity.Subject;

public interface ExamResultRepository extends JpaRepository<ExamResult, Long> {
    List<ExamResult> findByExam(Exam exam);
    List<ExamResult> findByStudent(Student student);
    List<ExamResult> findBySubject(Subject subject);
    List<ExamResult> findByExamAndStudent(Exam exam, Student student);
    
    @Query("SELECT er FROM ExamResult er WHERE er.exam.id = :examId AND er.student.id = :studentId")
    List<ExamResult> findByExamIdAndStudentId(Long examId, Long studentId);
    
    @Query("SELECT er FROM ExamResult er WHERE er.exam.id = :examId AND er.subject.id = :subjectId")
    List<ExamResult> findByExamIdAndSubjectId(Long examId, Long subjectId);
    
    @Query("SELECT er FROM ExamResult er WHERE er.student.id = :studentId AND er.exam.id IN :examIds")
    List<ExamResult> findByStudentIdAndExamIds(Long studentId, List<Long> examIds);
    
    @Query("SELECT AVG(er.marksObtained) FROM ExamResult er WHERE er.student.id = :studentId AND er.subject.id = :subjectId")
    Double findAverageMarksByStudentAndSubject(Long studentId, Long subjectId);
}
