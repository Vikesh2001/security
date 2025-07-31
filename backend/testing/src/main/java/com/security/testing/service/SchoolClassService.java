package com.security.testing.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
//import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.security.testing.dto.SchoolClassDto;
import com.security.testing.entity.SchoolClass;
import com.security.testing.exception.ResourceNotFoundException;
import com.security.testing.repository.SchoolClassRepository;

@Service
public class SchoolClassService {
    
    private final SchoolClassRepository classRepository;
    private final ModelMapper modelMapper;
    
    public SchoolClassService(SchoolClassRepository classRepository, ModelMapper modelMapper) {
        this.classRepository = classRepository;
        this.modelMapper = modelMapper;
    }
    
    public List<SchoolClassDto> getAllClasses() {
        return classRepository.findAll().stream()
                .map(clazz -> modelMapper.map(clazz, SchoolClassDto.class))
                .collect(Collectors.toList());
    }
    
    public SchoolClassDto createClass(SchoolClassDto classDto) {
        SchoolClass schoolClass = modelMapper.map(classDto, SchoolClass.class);
        SchoolClass savedClass = classRepository.save(schoolClass);
        return modelMapper.map(savedClass, SchoolClassDto.class);
    }
    
    public SchoolClassDto updateClass(Long classId, SchoolClassDto classDto) {
        SchoolClass existingClass = classRepository.findById(classId)
                .orElseThrow(() -> new ResourceNotFoundException("Class", "id", classId));
        
        modelMapper.map(classDto, existingClass);
        SchoolClass updatedClass = classRepository.save(existingClass);
        return modelMapper.map(updatedClass, SchoolClassDto.class);
    }
    
    public void deleteClass(Long classId) {
        if (!classRepository.existsById(classId)) {
            throw new ResourceNotFoundException("Class", "id", classId);
        }
        classRepository.deleteById(classId);
    }
}
