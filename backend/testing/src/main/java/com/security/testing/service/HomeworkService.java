package com.security.testing.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.security.testing.dto.HomeworkDto;
import com.security.testing.entity.Homework;
import com.security.testing.entity.Student;
import com.security.testing.exception.ResourceNotFoundException;
import com.security.testing.repository.HomeworkRepository;
import com.security.testing.repository.StudentRepository;

@Service
public class HomeworkService {

    private final HomeworkRepository homeworkRepository;
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public HomeworkService(HomeworkRepository homeworkRepository, 
                         StudentRepository studentRepository,
                         ModelMapper modelMapper) {
        this.homeworkRepository = homeworkRepository;
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    public HomeworkDto assignHomework(HomeworkDto homeworkDto) {
        Homework homework = modelMapper.map(homeworkDto, Homework.class);
        Homework savedHomework = homeworkRepository.save(homework);
        return modelMapper.map(savedHomework, HomeworkDto.class);
    }

    public List<HomeworkDto> getHomeworkByStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));
        
        return homeworkRepository.findByStudent(student).stream()
                .map(homework -> modelMapper.map(homework, HomeworkDto.class))
                .collect(Collectors.toList());
    }

    public HomeworkDto updateHomework(Long homeworkId, HomeworkDto homeworkDto) {
        Homework existingHomework = homeworkRepository.findById(homeworkId)
                .orElseThrow(() -> new ResourceNotFoundException("Homework", "id", homeworkId));

        modelMapper.map(homeworkDto, existingHomework);
        Homework updatedHomework = homeworkRepository.save(existingHomework);
        return modelMapper.map(updatedHomework, HomeworkDto.class);
    }

    public void deleteHomework(Long homeworkId) {
        if (!homeworkRepository.existsById(homeworkId)) {
            throw new ResourceNotFoundException("Homework", "id", homeworkId);
        }
        homeworkRepository.deleteById(homeworkId);
    }

    public List<HomeworkDto> getHomeworkByClass(Long classId) {
        return homeworkRepository.findBySchoolClassId(classId).stream()
                .map(homework -> modelMapper.map(homework, HomeworkDto.class))
                .collect(Collectors.toList());
    }
}
