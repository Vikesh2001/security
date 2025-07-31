package com.security.testing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.security.testing.entity.Fee;
import com.security.testing.entity.SchoolClass;

public interface FeeRepository extends JpaRepository<Fee, Long> {
    List<Fee> findBySchoolClass(SchoolClass schoolClass);
    List<Fee> findByFeeType(String feeType);
    
    @Query("SELECT f FROM Fee f WHERE f.schoolClass.id = :classId")
    List<Fee> findByClassId(Long classId);
    
    @Query("SELECT DISTINCT f.feeType FROM Fee f")
    List<String> findDistinctFeeTypes();
}
