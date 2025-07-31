package com.security.testing.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.security.testing.dto.AttendanceDto;
import com.security.testing.dto.StudentDto;
import com.security.testing.service.AttendanceService;
import com.security.testing.service.ExamService;
import com.security.testing.service.StudentService;
import com.security.testing.service.TimetableService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/sub-admin")
//@PreAuthorize("hasAnyRole('ADMIN', 'SUB_ADMIN')")
public class SubAdminController {
    
    private final StudentService studentService;
//    private final TeacherService teacherService;
    private final AttendanceService attendanceService;
    private final TimetableService timetableService;
    private final ExamService examService;
//    private final EventService eventService;
    
    // Constructor injection
    public SubAdminController(StudentService studentService,
			AttendanceService attendanceService, TimetableService timetableService, ExamService examService
			) {
		this.studentService = studentService;
		
		this.attendanceService = attendanceService;
		this.timetableService = timetableService;
		this.examService = examService;
	}
    
    // Student Management
    @GetMapping("/students")
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }
    
    

	@PostMapping("/students")
    public ResponseEntity<StudentDto> createStudent(@Valid @RequestBody StudentDto studentDto) {
        return new ResponseEntity<>(studentService.createStudent(studentDto), HttpStatus.CREATED);
    }
    
    @PutMapping("/students/{studentId}/promote")
    public ResponseEntity<StudentDto> promoteStudent(@PathVariable Long studentId, 
                                                   @RequestParam Long newClassId,
                                                   @RequestParam(required = false) Long newSectionId) {
        return ResponseEntity.ok(studentService.promoteStudent(studentId, newClassId, newSectionId));
    }
    
    // Attendance Management
    @PostMapping("/attendance")
    public ResponseEntity<AttendanceDto> markAttendance(@Valid @RequestBody AttendanceDto attendanceDto) {
        return new ResponseEntity<>(attendanceService.markAttendance(attendanceDto), HttpStatus.CREATED);
    }
    
    @GetMapping("/attendance/class/{classId}")
    public ResponseEntity<List<AttendanceDto>> getClassAttendance(@PathVariable Long classId,
                                                                @RequestParam LocalDate date) {
        return ResponseEntity.ok(attendanceService.getClassAttendance(classId, date));
    }
    
    // More sub-admin endpoints
}