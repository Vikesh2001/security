package com.security.testing.repository;

import java.time.DayOfWeek;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.security.testing.entity.SchoolClass;
import com.security.testing.entity.Section;
import com.security.testing.entity.Subject;
import com.security.testing.entity.Teacher;
import com.security.testing.entity.Timetable;

public interface TimetableRepository extends JpaRepository<Timetable, Long> {
    List<Timetable> findBySchoolClass(SchoolClass schoolClass);
    List<Timetable> findBySection(Section section);
    List<Timetable> findByTeacher(Teacher teacher);
    List<Timetable> findBySubject(Subject subject);
    List<Timetable> findByDay(DayOfWeek day);
    
    // Add this method
    List<Timetable> findBySchoolClassAndSection(SchoolClass schoolClass, Section section);
    
    @Query("SELECT t FROM Timetable t WHERE t.schoolClass.id = :classId AND t.section.id = :sectionId")
    List<Timetable> findByClassAndSection(Long classId, Long sectionId);
    
    @Query("SELECT t FROM Timetable t WHERE t.schoolClass.id = :classId AND t.section.id = :sectionId AND t.day = :day")
    List<Timetable> findByClassAndSectionAndDay(Long classId, Long sectionId, DayOfWeek day);
}
