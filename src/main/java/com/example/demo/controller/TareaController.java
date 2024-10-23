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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tarea")
public class TareaController {
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

}
