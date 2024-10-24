package com.example.demo.controller;

import com.example.demo.controller.common.ApiUtil;
import com.example.demo.controller.common.ResponseGeneric;
import com.example.demo.controller.exceptions.DataNotFoundException;
import com.example.demo.controller.exceptions.OperationException;
import com.example.demo.model.Tarea;
import com.example.demo.model.dto.TareaDto;
import com.example.demo.service.TareaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tareas")
public class TareaController {
    @Autowired
    private final TareaService tareaService;

    @PostMapping("")
    public ResponseEntity<ResponseGeneric<Tarea>> create(@RequestBody TareaDto dto) {
        log.info("create");
        try {
            return ok(ApiUtil.responseOk(tareaService.create(dto)));
        } catch (OperationException | DataNotFoundException e) {
            log.error("Error message: {}", e.getMessage());
            return ResponseEntity.badRequest().body(ApiUtil.responseError(e.getMessage()));
        } catch (Exception e) {
            log.error("Error inesperado", e);
            return ResponseEntity.badRequest().body(ApiUtil.responseError500());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseGeneric> update(@PathVariable("id") Long id) {
        log.info("update");
        try {
            tareaService.completeTarea(id);
            return ok(ApiUtil.responseOk());
        } catch (DataNotFoundException | OperationException e) {
            log.error("{} message: {}", e.getClass().getSimpleName(), e.getMessage());
            return ResponseEntity.badRequest().body(ApiUtil.responseError(e.getMessage()));
        } catch (Exception e) {
            log.error("Error inesperado", e);
            return ResponseEntity.badRequest().body(ApiUtil.responseError500());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        log.info("delete");
        try {
            tareaService.delete(id);
            return ok().build();
        } catch (DataNotFoundException e) {
            log.error("DataNotFoundException message: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error("Error inesperado", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<ResponseGeneric<List<Tarea>>> listAll() {
        log.info("Todas las tareas");
        try {
            return ok(ApiUtil.responseOk(tareaService.listAll()));
        } catch (Exception e) {
            log.error("Error inesperado", e);
            return ResponseEntity.internalServerError().body(ApiUtil.responseError500());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseGeneric<Optional<Tarea>>> listById(@PathVariable("id") Long id) {
        log.info("Todas las tareas");
        try {
            return ok(ApiUtil.responseOk(tareaService.listById(id)));
        } catch (Exception e) {
            log.error("Error inesperado", e);
            return ResponseEntity.internalServerError().body(ApiUtil.responseError500());
        }
    }

    @GetMapping("/pendiente")
    public ResponseEntity<ResponseGeneric<List<Tarea>>> listByStatusPendiente() {
        log.info("Todas las tareas");
        try {
            return ok(ApiUtil.responseOk(tareaService.findByStatusPendiente()));
        } catch (Exception e) {
            log.error("Error inesperado", e);
            return ResponseEntity.internalServerError().body(ApiUtil.responseError500());
        }
    }

}
