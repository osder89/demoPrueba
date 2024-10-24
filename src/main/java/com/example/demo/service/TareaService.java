package com.example.demo.service;

import com.example.demo.model.Tarea;
import com.example.demo.model.dto.TareaDto;

import java.util.List;

public interface TareaService {

    Tarea create(TareaDto tareaDto);

    TareaDto update(Long idTarea, TareaDto tareaDto );

    void delete(Long idTarea);

    List<Tarea> listAll();

    List<Tarea> findByStatusPendiente();

}
