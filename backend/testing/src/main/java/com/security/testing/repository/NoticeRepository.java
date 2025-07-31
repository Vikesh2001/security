package com.security.testing.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.security.testing.entity.Notice;
import com.security.testing.entity.NoticePriority;
import com.security.testing.entity.User;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    List<Notice> findByDate(LocalDate date);
    List<Notice> findByDateBetween(LocalDate startDate, LocalDate endDate);
    List<Notice> findByCreatedBy(User createdBy);
    List<Notice> findByPriority(NoticePriority priority);
    
    @Query("SELECT n FROM Notice n WHERE n.date >= :startDate AND n.date <= :endDate ORDER BY n.priority DESC, n.date DESC")
    List<Notice> findBetweenDatesOrderByPriorityAndDate(LocalDate startDate, LocalDate endDate);
    
    @Query("SELECT n FROM Notice n WHERE n.createdBy.id = :userId ORDER BY n.date DESC")
    List<Notice> findByUserIdOrderByDateDesc(Long userId);
    
    @Query("SELECT n FROM Notice n WHERE n.priority = 'HIGH' AND n.date >= CURRENT_DATE ORDER BY n.date ASC")
    List<Notice> findHighPriorityCurrentNotices();
}
