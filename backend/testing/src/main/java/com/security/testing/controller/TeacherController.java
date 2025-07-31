package com.security.testing.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.testing.dto.AttendanceDto;
import com.security.testing.dto.ExamResultDto;
import com.security.testing.dto.HomeworkDto;
import com.security.testing.dto.SectionDto;
import com.security.testing.entity.User;
import com.security.testing.service.AttendanceService;
import com.security.testing.service.ExamService;
import com.security.testing.service.HomeworkService;
import com.security.testing.service.TeacherService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/teacher")
//@PreAuthorize("hasAnyRole('ADMIN', 'SUB_ADMIN', 'TEACHER')")
public class TeacherController {
    
    private final TeacherService teacherService;
    private final AttendanceService attendanceService;
    private final ExamService examService;
    private final HomeworkService homeworkService;
    
    
    
    public TeacherController(TeacherService teacherService, AttendanceService attendanceService,
			ExamService examService, HomeworkService homeworkService) {
		this.teacherService = teacherService;
		this.attendanceService = attendanceService;
		this.examService = examService;
		this.homeworkService = homeworkService;
	}

	// Constructor injection
    
    @GetMapping("/my-classes")
    public ResponseEntity<List<SectionDto>> getMyClasses(@AuthenticationPrincipal UserDetails currentUser) {
    	User user = (User) currentUser;
        return ResponseEntity.ok(teacherService.getAssignedClasses(user.getId()));
    }
    
    @PostMapping("/attendance")
    public ResponseEntity<AttendanceDto> markStudentAttendance(@Valid @RequestBody AttendanceDto attendanceDto) {
        return new ResponseEntity<>(attendanceService.markAttendance(attendanceDto), HttpStatus.CREATED);
    }
    
    @PostMapping("/exam-marks")
    public ResponseEntity<ExamResultDto> enterExamMarks(@Valid @RequestBody ExamResultDto examResultDto) {
        return new ResponseEntity<>(examService.enterExamMarks(examResultDto), HttpStatus.CREATED);
    }
    
    @PostMapping("/homework")
    public ResponseEntity<HomeworkDto> assignHomework(@Valid @RequestBody HomeworkDto homeworkDto) {
        return new ResponseEntity<>(homeworkService.assignHomework(homeworkDto), HttpStatus.CREATED);
    }
}

