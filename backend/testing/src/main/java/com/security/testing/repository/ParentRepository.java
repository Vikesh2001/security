package com.security.testing.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.security.testing.entity.Parent;
import com.security.testing.entity.Student;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {
    
    // Use this for finding by user ID (since Parent extends User)
    Optional<Parent> findById(Long userId);
    
    // Use this for finding by student ID
    List<Parent> findByStudentId(Long studentId);
    
    // Custom query for finding by both user and student IDs
    @Query("SELECT p FROM Parent p WHERE p.id = :userId AND p.student.id = :studentId")
    Optional<Parent> findByUserIdAndStudentId(@Param("userId") Long userId, 
                                           @Param("studentId") Long studentId);
    
 // Add this new method to find students by parent ID
    @Query("SELECT p.student FROM Parent p WHERE p.id = :parentId")
    List<Student> findStudentsByParentId(@Param("parentId") Long parentId);
}
