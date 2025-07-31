package com.security.testing.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.testing.dto.ExamDto;
import com.security.testing.dto.FeeDto;
import com.security.testing.dto.SchoolClassDto;
import com.security.testing.dto.UserDto;
import com.security.testing.service.ExamService;
import com.security.testing.service.FeeService;
import com.security.testing.service.SchoolClassService;
import com.security.testing.service.SubjectService;
import com.security.testing.service.TimetableService;
import com.security.testing.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin")
//@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    
    private final UserService userService;
    private final SchoolClassService classService;
    private final SubjectService subjectService;
    private final TimetableService timetableService;
    private final FeeService feeService;
    private final ExamService examService;
    
    // Constructor injection
    public AdminController(UserService userService, SchoolClassService classService, SubjectService subjectService,
			TimetableService timetableService, FeeService feeService, ExamService examService) {
		super();
		this.userService = userService;
		this.classService = classService;
		this.subjectService = subjectService;
		this.timetableService = timetableService;
		this.feeService = feeService;
		this.examService = examService;
	}

    
    // User Management
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    
   
	@PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }
    
    @PutMapping("/users/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long userId, 
                                            @Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(userId, userDto));
    }
    
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
    
    // Class Management
    @GetMapping("/classes")
    public ResponseEntity<List<SchoolClassDto>> getAllClasses() {
        return ResponseEntity.ok(classService.getAllClasses());
    }
    
    @PostMapping("/classes")
    public ResponseEntity<SchoolClassDto> createClass(@Valid @RequestBody SchoolClassDto classDto) {
        return new ResponseEntity<>(classService.createClass(classDto), HttpStatus.CREATED);
    }
    
    // Similar endpoints for sections, subjects, etc.
    
    // Fee Management
    @GetMapping("/fees")
    public ResponseEntity<List<FeeDto>> getAllFees() {
        return ResponseEntity.ok(feeService.getAllFees());
    }
    
    @PostMapping("/fees")
    public ResponseEntity<FeeDto> createFee(@Valid @RequestBody FeeDto feeDto) {
        return new ResponseEntity<>(feeService.createFee(feeDto), HttpStatus.CREATED);
    }
    
    // Exam Management
    @GetMapping("/exams")
    public ResponseEntity<List<ExamDto>> getAllExams() {
        return ResponseEntity.ok(examService.getAllExams());
    }
    
    @PostMapping("/exams")
    public ResponseEntity<ExamDto> createExam(@Valid @RequestBody ExamDto examDto) {
        return new ResponseEntity<>(examService.createExam(examDto), HttpStatus.CREATED);
    }
    
    // More admin-specific endpoints
}
