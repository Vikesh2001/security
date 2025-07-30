package com.security.testing.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.security.testing.dto.SectionDto;
import com.security.testing.entity.Section;
import com.security.testing.entity.Teacher;
import com.security.testing.exception.ResourceNotFoundException;
import com.security.testing.repository.SectionRepository;
import com.security.testing.repository.TeacherRepository;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final SectionRepository sectionRepository;
    private final ModelMapper modelMapper;

    public TeacherService(TeacherRepository teacherRepository,
                        SectionRepository sectionRepository,
                        ModelMapper modelMapper) {
        this.teacherRepository = teacherRepository;
        this.sectionRepository = sectionRepository;
        this.modelMapper = modelMapper;
    }

    public List<SectionDto> getAssignedClasses(Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher", "id", teacherId));

        List<Section> sections = sectionRepository.findByClassTeacherOrSubjectTeacher(teacher);
        return mapToDto(sections, teacherId);
    }

    private List<SectionDto> mapToDto(List<Section> sections, Long teacherId) {
        return sections.stream()
                .map(section -> {
                    SectionDto dto = modelMapper.map(section, SectionDto.class);
                    dto.setClassId(section.getSchoolClass().getId());
                    dto.setClassName(section.getSchoolClass().getName());
                    dto.setSectionId(section.getId());
                    dto.setSectionName(section.getName());
                    dto.setClassTeacher(section.getClassTeacher().getId().equals(teacherId));
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
