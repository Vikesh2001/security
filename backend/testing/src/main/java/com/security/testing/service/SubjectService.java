package com.security.testing.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
//import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.security.testing.dto.SubjectDto;
import com.security.testing.entity.Subject;
import com.security.testing.exception.ResourceNotFoundException;
import com.security.testing.repository.SubjectRepository;

@Service
public class SubjectService {
    
    private final SubjectRepository subjectRepository;
    private final ModelMapper modelMapper;
    
    public SubjectService(SubjectRepository subjectRepository, ModelMapper modelMapper) {
        this.subjectRepository = subjectRepository;
        this.modelMapper = modelMapper;
    }
    
    public List<SubjectDto> getAllSubjects() {
        return subjectRepository.findAll().stream()
                .map(subject -> modelMapper.map(subject, SubjectDto.class))
                .collect(Collectors.toList());
    }
    
    public SubjectDto createSubject(SubjectDto subjectDto) {
        Subject subject = modelMapper.map(subjectDto, Subject.class);
        Subject savedSubject = subjectRepository.save(subject);
        return modelMapper.map(savedSubject, SubjectDto.class);
    }
    
    public SubjectDto updateSubject(Long subjectId, SubjectDto subjectDto) {
        Subject existingSubject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException("Subject", "id", subjectId));
        
        modelMapper.map(subjectDto, existingSubject);
        Subject updatedSubject = subjectRepository.save(existingSubject);
        return modelMapper.map(updatedSubject, SubjectDto.class);
    }
    
    public void deleteSubject(Long subjectId) {
        if (!subjectRepository.existsById(subjectId)) {
            throw new ResourceNotFoundException("Subject", "id", subjectId);
        }
        subjectRepository.deleteById(subjectId);
    }
}
