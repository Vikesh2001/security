package com.security.testing.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.security.testing.dto.StudentDto;
import com.security.testing.dto.StudentProfileDto;
import com.security.testing.dto.TimetableDto;
import com.security.testing.entity.SchoolClass;
import com.security.testing.entity.Section;
import com.security.testing.entity.Student;
import com.security.testing.exception.ResourceNotFoundException;
import com.security.testing.repository.SchoolClassRepository;
import com.security.testing.repository.SectionRepository;
import com.security.testing.repository.StudentRepository;
import com.security.testing.repository.TimetableRepository;

@Service
public class StudentService {
    
    private final StudentRepository studentRepository;
    private final SchoolClassRepository classRepository;
    private final SectionRepository sectionRepository;
    private final ModelMapper modelMapper;
    private final TimetableRepository timetableRepository;
    
    
    
    public StudentService(StudentRepository studentRepository, SchoolClassRepository classRepository,
			SectionRepository sectionRepository, ModelMapper modelMapper,TimetableRepository timetableRepository) {
		this.studentRepository = studentRepository;
		this.classRepository = classRepository;
		this.sectionRepository = sectionRepository;
		this.modelMapper = modelMapper;
		this.timetableRepository = timetableRepository;
	}

	public StudentDto createStudent(StudentDto studentDto) {
        Student student = modelMapper.map(studentDto, Student.class);
        
        SchoolClass schoolClass = classRepository.findById(studentDto.getClassId())
                .orElseThrow(() -> new ResourceNotFoundException("Class", "id", studentDto.getClassId()));
        
        Section section = sectionRepository.findById(studentDto.getSectionId())
                .orElseThrow(() -> new ResourceNotFoundException("Section", "id", studentDto.getSectionId()));
        
        student.setSchoolClass(schoolClass);
        student.setSection(section);
        
        // Generate admission number
        student.setAdmissionNumber(generateAdmissionNumber(schoolClass));
        
        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, StudentDto.class);
    }
    
    public StudentDto promoteStudent(Long studentId, Long newClassId, Long newSectionId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));
        
        SchoolClass newClass = classRepository.findById(newClassId)
                .orElseThrow(() -> new ResourceNotFoundException("Class", "id", newClassId));
        
        Section newSection = sectionRepository.findById(newSectionId)
                .orElseThrow(() -> new ResourceNotFoundException("Section", "id", newSectionId));
        
        student.setSchoolClass(newClass);
        student.setSection(newSection);
        
        Student updatedStudent = studentRepository.save(student);
        return modelMapper.map(updatedStudent, StudentDto.class);
    }
    
    private String generateAdmissionNumber(SchoolClass schoolClass) {
        // Implementation to generate unique admission number
        return "ADM-" + schoolClass.getCode() + "-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
    
    public StudentProfileDto getStudentProfile(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));
        
        StudentProfileDto profileDto = modelMapper.map(student, StudentProfileDto.class);
        profileDto.setClassName(student.getSchoolClass().getName());
        profileDto.setSectionName(student.getSection().getName());
        profileDto.setClassTeacherName(student.getSection().getClassTeacher().getFirstName() + " " + 
                                     student.getSection().getClassTeacher().getLastName());
        
        return profileDto;
    }

    public List<TimetableDto> getStudentTimetable(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));
        
        return timetableRepository.findBySchoolClassAndSection(
                student.getSchoolClass(), student.getSection()).stream()
                .map(timetable -> modelMapper.map(timetable, TimetableDto.class))
                .collect(Collectors.toList());
    }
    
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(student -> {
                    StudentDto dto = modelMapper.map(student, StudentDto.class);
                    // Map additional fields if needed
                    dto.setClassName(student.getSchoolClass().getName());
                    dto.setSectionName(student.getSection().getName());
                    return dto;
                })
                .collect(Collectors.toList());
    }
    }
