package com.security.testing.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.security.testing.entity.SchoolClass;
import com.security.testing.entity.Section;
import com.security.testing.entity.Teacher;

public interface SectionRepository extends JpaRepository<Section, Long> {
    Optional<Section> findByNameAndSchoolClass(String name, SchoolClass schoolClass);
    List<Section> findBySchoolClass(SchoolClass schoolClass);
    @Query("SELECT s FROM Section s WHERE s.schoolClass.id = :classId")
    List<Section> findByClassId(Long classId);
    @Query("SELECT s FROM Section s WHERE s.classTeacher.id = :teacherId")
    List<Section> findByClassTeacherId(Long teacherId);
//    @Query("SELECT s FROM Section s WHERE s.classTeacher = :teacher OR :teacher MEMBER OF s.schoolClass.subjects.teachers")
//    List<Section> findByClassTeacherOrSubjectsTeachers(Teacher teacher, Teacher teacher2);
    @Query("SELECT DISTINCT s FROM Section s " +
            "JOIN s.schoolClass sc " +
            "JOIN sc.subjects subj " +
            "JOIN subj.teachers t " +
            "WHERE t = :teacher")
     List<Section> findBySubjectTeacher(@Param("teacher") Teacher teacher);
    List<Section> findByClassTeacher(Teacher teacher);
    @Query("SELECT DISTINCT s FROM Section s " +
    	       "LEFT JOIN s.schoolClass sc " +
    	       "LEFT JOIN sc.subjects subj " +
    	       "LEFT JOIN subj.teachers t " +
    	       "WHERE s.classTeacher = :teacher OR t = :teacher")
    	List<Section> findByClassTeacherOrSubjectTeacher(@Param("teacher") Teacher teacher);

}