package com.example.demo.service;

import com.example.demo.model.Tarea;
import com.example.demo.model.dto.TareaDto;

import java.util.List;
import java.util.Optional;

public interface TareaService {

    Tarea create(TareaDto tareaDto);

    void completeTarea(Long idTarea );

    void delete(Long idTarea);

    List<Tarea> listAll();

    Optional<Tarea> listById(Long idTarea);

    List<Tarea> findByStatusPendiente();

}
