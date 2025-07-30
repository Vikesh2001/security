package com.security.testing.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.security.testing.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findByEmployeeId(String employeeId);
    @Query("SELECT t FROM Teacher t JOIN t.subjects s WHERE s.id = :subjectId")
    List<Teacher> findBySubjectId(Long subjectId);
    @Query("SELECT t FROM Teacher t JOIN t.subjects s WHERE s.schoolClass.id = :classId")
    List<Teacher> findByClassId(Long classId);
}
