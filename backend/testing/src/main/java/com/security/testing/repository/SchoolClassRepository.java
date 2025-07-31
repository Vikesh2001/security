package com.security.testing.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.security.testing.entity.SchoolClass;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {
    Optional<SchoolClass> findByName(String name);
    Optional<SchoolClass> findByCode(String code);
    @Query("SELECT c FROM SchoolClass c ORDER BY c.name ASC")
    List<SchoolClass> findAllOrderByNameAsc();
}
