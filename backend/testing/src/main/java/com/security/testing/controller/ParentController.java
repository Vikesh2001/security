package com.security.testing.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.security.testing.dto.AttendanceDto;
import com.security.testing.dto.ExamResultDto;
import com.security.testing.dto.FeeStatusDto;
import com.security.testing.dto.StudentProfileDto;
import com.security.testing.entity.User;
import com.security.testing.service.AttendanceService;
import com.security.testing.service.ExamService;
import com.security.testing.service.FeeService;
import com.security.testing.service.ParentService;
import com.security.testing.service.StudentService;

@RestController
@RequestMapping("/api/parent")
//@PreAuthorize("hasAnyRole('ADMIN', 'SUB_ADMIN', 'PARENT')")
public class ParentController {
    
    private final ParentService parentService;
    private final StudentService studentService;
    private final AttendanceService attendanceService;
    private final ExamService examService;
    private final FeeService feeService;
    
    // Constructor injection
    
    public ParentController(ParentService parentService, StudentService studentService,
			AttendanceService attendanceService, ExamService examService, FeeService feeService) {
		this.parentService = parentService;
		this.studentService = studentService;
		this.attendanceService = attendanceService;
		this.examService = examService;
		this.feeService = feeService;
	}
    
    @GetMapping("/my-children")
    public ResponseEntity<List<StudentProfileDto>> getMyChildren(@AuthenticationPrincipal UserDetails currentUser) {
    	
    	User user = (User) currentUser;
        return ResponseEntity.ok(parentService.getChildren(user.getId()));
    }
    
    

	@GetMapping("/child/{childId}/attendance")
    public ResponseEntity<List<AttendanceDto>> getChildAttendance(
            @PathVariable Long childId,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate) {
        return ResponseEntity.ok(attendanceService.getStudentAttendance(childId, startDate, endDate));
    }
    
    @GetMapping("/child/{childId}/exam-results")
    public ResponseEntity<List<ExamResultDto>> getChildExamResults(@PathVariable Long childId) {
        return ResponseEntity.ok(examService.getStudentResults(childId));
    }
    
    @GetMapping("/child/{childId}/fee-status")
    public ResponseEntity<FeeStatusDto> getChildFeeStatus(@PathVariable Long childId) {
        return ResponseEntity.ok(feeService.getStudentFeeStatus(childId));
    }
}
