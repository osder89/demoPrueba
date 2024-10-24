package com.example.demo.service.impl;

import com.example.demo.emuns.TareaState;
import com.example.demo.model.Tarea;
import com.example.demo.model.dto.TareaDto;
import com.example.demo.repository.TareaRepository;
import com.example.demo.service.TareaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service("tareaService")
public class TareaServiceImpl implements TareaService {
    private final TareaRepository tareaRepository;

    @Override
    public Tarea create(TareaDto tareaDto) {
        validateStrings(tareaDto.getName());
        validateStrings(tareaDto.getDescription());

        Tarea tarea = Tarea.builder()
                .name(tareaDto.getName())
                .description(tareaDto.getDescription())
                .status(TareaState.PENDIENTE)
                .build();
        return tareaRepository.save(tarea);
    }

    @Override
    public void completeTarea(Long idTarea) {
        Optional<Tarea> tareaOptional = tareaRepository.findById(idTarea);
        if (tareaOptional.isPresent()) {
            Tarea tarea = tareaOptional.get();
            tarea.setStatus(TareaState.FINALIZADA);
            tareaRepository.save(tarea);  
        } else {
            throw new EntityNotFoundException("La tarea con ID: " + idTarea + " no fue encontrada.");
        }
    }


    @Override
    public void delete(Long idtarea) {
        Optional<Tarea> tareaOptional = tareaRepository.findById(idtarea);
        if (tareaOptional.isPresent()) {
            Tarea tarea = tareaOptional.get();
            tareaRepository.delete(tarea);
        }
    }

    @Override
    public List<Tarea> listAll() {
        return tareaRepository.findAll();
    }

    @Override
    public Optional<Tarea> listById(Long idTarea) {
        return tareaRepository.findById(idTarea);
    }

    @Override
    public List<Tarea> findByStatusPendiente() {
        return tareaRepository.findByStatusPendiente();
    }

    public void validateStrings(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("El valor no puede ser nulo o vacío.");
        }
    }

}
