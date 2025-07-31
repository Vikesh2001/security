package com.security.testing.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
//import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.security.testing.dto.TimetableDto;
import com.security.testing.entity.Timetable;
import com.security.testing.exception.ResourceNotFoundException;
import com.security.testing.repository.TimetableRepository;

@Service
public class TimetableService {
    
    private final TimetableRepository timetableRepository;
    private final ModelMapper modelMapper;
    
    public TimetableService(TimetableRepository timetableRepository, ModelMapper modelMapper) {
        this.timetableRepository = timetableRepository;
        this.modelMapper = modelMapper;
    }
    
    public List<TimetableDto> getAllTimetables() {
        return timetableRepository.findAll().stream()
                .map(timetable -> modelMapper.map(timetable, TimetableDto.class))
                .collect(Collectors.toList());
    }
    
    public TimetableDto createTimetable(TimetableDto timetableDto) {
        Timetable timetable = modelMapper.map(timetableDto, Timetable.class);
        Timetable savedTimetable = timetableRepository.save(timetable);
        return modelMapper.map(savedTimetable, TimetableDto.class);
    }
    
    public TimetableDto updateTimetable(Long timetableId, TimetableDto timetableDto) {
        Timetable existingTimetable = timetableRepository.findById(timetableId)
                .orElseThrow(() -> new ResourceNotFoundException("Timetable", "id", timetableId));
        
        modelMapper.map(timetableDto, existingTimetable);
        Timetable updatedTimetable = timetableRepository.save(existingTimetable);
        return modelMapper.map(updatedTimetable, TimetableDto.class);
    }
    
    public void deleteTimetable(Long timetableId) {
        if (!timetableRepository.existsById(timetableId)) {
            throw new ResourceNotFoundException("Timetable", "id", timetableId);
        }
        timetableRepository.deleteById(timetableId);
    }
}
