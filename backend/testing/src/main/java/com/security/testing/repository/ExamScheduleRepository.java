package com.security.testing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.security.testing.entity.Exam;
import com.security.testing.entity.ExamSchedule;
import com.security.testing.entity.SchoolClass;
import com.security.testing.entity.Subject;

public interface ExamScheduleRepository extends JpaRepository<ExamSchedule, Long> {
    List<ExamSchedule> findByExam(Exam exam);
    List<ExamSchedule> findBySubject(Subject subject);
    List<ExamSchedule> findBySchoolClass(SchoolClass schoolClass);
    
    @Query("SELECT es FROM ExamSchedule es WHERE es.exam.id = :examId AND es.schoolClass.id = :classId")
    List<ExamSchedule> findByExamIdAndClassId(Long examId, Long classId);
    
    @Query("SELECT es FROM ExamSchedule es WHERE es.exam.id = :examId AND es.subject.id = :subjectId")
    List<ExamSchedule> findByExamIdAndSubjectId(Long examId, Long subjectId);
}