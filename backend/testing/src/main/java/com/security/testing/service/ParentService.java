package com.security.testing.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
//import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.security.testing.dto.StudentProfileDto;
import com.security.testing.entity.Student;
import com.security.testing.exception.ResourceNotFoundException;
import com.security.testing.repository.ParentRepository;

@Service
public class ParentService {

    private final ParentRepository parentRepository;
    private final ModelMapper modelMapper;

    public ParentService(ParentRepository parentRepository, ModelMapper modelMapper) {
        this.parentRepository = parentRepository;
        this.modelMapper = modelMapper;
    }

    public List<StudentProfileDto> getChildren(Long parentId) {
        // Find all students associated with this parent
        List<Student> students = parentRepository.findStudentsByParentId(parentId);
        
        if (students.isEmpty()) {
            throw new ResourceNotFoundException("Parent", "id", parentId);
        }

        return students.stream()
                .map(student -> modelMapper.map(student, StudentProfileDto.class))
                .collect(Collectors.toList());
    }
    // Additional methods if needed
}
