package com.security.testing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security.testing.entity.Homework;
import com.security.testing.entity.Student;

@Repository
public interface HomeworkRepository extends JpaRepository<Homework, Long> {
    List<Homework> findByStudentId(Long studentId);
    List<Homework> findBySchoolClassId(Long classId);
    List<Homework> findByTeacherId(Long teacherId);
    List<Homework> findByStudent(Student student);  // Add this line
}
