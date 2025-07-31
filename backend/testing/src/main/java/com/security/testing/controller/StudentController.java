package com.security.testing.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.security.testing.dto.AttendanceDto;
import com.security.testing.dto.ExamResultDto;
import com.security.testing.dto.FeeStatusDto;
import com.security.testing.dto.StudentProfileDto;
import com.security.testing.dto.TimetableDto;
import com.security.testing.entity.User;
import com.security.testing.service.AttendanceService;
import com.security.testing.service.ExamService;
import com.security.testing.service.FeeService;
import com.security.testing.service.HomeworkService;
import com.security.testing.service.StudentService;

@RestController
@RequestMapping("/api/student")
//@PreAuthorize("hasAnyRole('ADMIN', 'SUB_ADMIN', 'STUDENT')")
public class StudentController {
    
    private final StudentService studentService;
    private final AttendanceService attendanceService;
    private final ExamService examService;
    private final HomeworkService homeworkService;
    private final FeeService feeService;
    
 // Constructor injection
    public StudentController(StudentService studentService, AttendanceService attendanceService,
			ExamService examService, HomeworkService homeworkService, FeeService feeService) {
		super();
		this.studentService = studentService;
		this.attendanceService = attendanceService;
		this.examService = examService;
		this.homeworkService = homeworkService;
		this.feeService = feeService;
	}

	
    
    @GetMapping("/profile")
    public ResponseEntity<StudentProfileDto> getMyProfile(@AuthenticationPrincipal UserDetails currentUser) {
    	User user = (User) currentUser;
        return ResponseEntity.ok(studentService.getStudentProfile(user.getId()));
    }
    
    @GetMapping("/timetable")
    public ResponseEntity<List<TimetableDto>> getMyTimetable(@AuthenticationPrincipal UserDetails currentUser) {
    	User user = (User) currentUser;
        return ResponseEntity.ok(studentService.getStudentTimetable(user.getId()));
    }
    
    @GetMapping("/attendance")
    public ResponseEntity<List<AttendanceDto>> getMyAttendance(
            @AuthenticationPrincipal UserDetails currentUser,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate) {
    	User user = (User) currentUser;
        return ResponseEntity.ok(attendanceService.getStudentAttendance(
                user.getId(), startDate, endDate));
    }
    
    @GetMapping("/exam-results")
    public ResponseEntity<List<ExamResultDto>> getMyExamResults(@AuthenticationPrincipal UserDetails currentUser) {
    	User user = (User) currentUser;
        return ResponseEntity.ok(examService.getStudentResults(user.getId()));
    }
    
    @GetMapping("/fee-status")
    public ResponseEntity<FeeStatusDto> getMyFeeStatus(@AuthenticationPrincipal UserDetails currentUser) {
    	User user = (User) currentUser;
        return ResponseEntity.ok(feeService.getStudentFeeStatus(user.getId()));
    }
}
