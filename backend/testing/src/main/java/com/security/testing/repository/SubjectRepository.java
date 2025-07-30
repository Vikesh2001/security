package com.security.testing.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.security.testing.entity.SchoolClass;
import com.security.testing.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Optional<Subject> findByCode(String code);
    List<Subject> findBySchoolClass(SchoolClass schoolClass);
    @Query("SELECT s FROM Subject s WHERE s.schoolClass.id = :classId")
    List<Subject> findByClassId(Long classId);
//    @Query("SELECT s FROM Subject s JOIN s.teachers t WHERE t.id = :teacherId")
//    List<Subject> findByTeacherId(Long teacherId);
    @Query("SELECT s FROM Subject s JOIN s.teachers t WHERE t.id = :teacherId")
    List<Subject> findByTeacherId(@Param("teacherId") Long teacherId);
}
