package com.example.demo.model;

import com.example.demo.emuns.TareaState;
import lombok.*;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tarea")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Tarea implements Serializable {

    @Id
    @SequenceGenerator(name = "SEQ_TAREA_ID_GENERATOR", sequenceName = "SEQ_TAREA_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TAREA_ID_GENERATOR")
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 70, unique = true)
    private String name;

    @Column(name = "description", nullable = false, length = 150)
    private String description;

    @Column(name = "status", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private TareaState status;

}
