package com.example.demo.model.dto;

import com.example.demo.emuns.TareaState;
import com.example.demo.model.Tarea;
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

    public void TareaDtoToEntity(Tarea tarea){
        this.id = tarea.getId();
        this.name = tarea.getName();
        this.description = tarea.getDescription();
        this.status = tarea.getStatus();
    }

}
