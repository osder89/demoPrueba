package com.example.demo.model.dto;

import com.example.demo.emuns.TareaState;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
public class TareaDto implements Serializable {
    private  Long id;
    private String name;
    private String description;
    private TareaState status;

    public TareaDto(Long id, String name, String description, TareaState status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public TareaDto(String nombre, String descripcion) {
        this.name = nombre;
        this.description = descripcion;
    }
}
