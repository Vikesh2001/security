package com.security.testing.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.security.testing.entity.SchoolClass;
import com.security.testing.entity.Section;
import com.security.testing.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByAdmissionNumber(String admissionNumber);
    List<Student> findBySchoolClass(SchoolClass schoolClass);
    List<Student> findBySchoolClassAndSection(SchoolClass schoolClass, Section section);
    List<Student> findBySection(Section section);
    @Query("SELECT s FROM Student s WHERE s.schoolClass.id = :classId AND s.section.id = :sectionId")
    List<Student> findByClassIdAndSectionId(Long classId, Long sectionId);
}