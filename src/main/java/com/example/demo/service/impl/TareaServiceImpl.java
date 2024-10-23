package com.example.demo.service.impl;

import com.example.demo.emuns.TareaState;
import com.example.demo.model.Tarea;
import com.example.demo.model.dto.TareaDto;
import com.example.demo.repository.TareaRepository;
import com.example.demo.service.TareaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public TareaDto update(Long idTarea, TareaDto tareaDto) {
        return null;
    }

    @Override
    public void delete(Tarea tarea) {
        tareaRepository.delete(tarea);
    }

    @Override
    public List<Tarea> listAll() {
        return tareaRepository.findAll();
    }

    @Override
    public List<Tarea> findByStatusPendiente() {
        return null;
    }

    public void validateStrings(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("El valor no puede ser nulo o vacío.");
        }
    }



}
