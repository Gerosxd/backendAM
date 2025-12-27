package com.aerotaller.modules.mro.entity;

import jakarta.persistence.*;
import lombok.Data;


// OrdenTrabajoTarea.java
@Entity
@Table(name = "mro_ot_tareas")
@Data
public class OrdenTrabajoTarea {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private OrdenTrabajo ordenTrabajo;

    @ManyToOne
    private TareaProgramada tarea; // La definición del catálogo

    private String estado; // Pendiente, Completada, etc.
    private String hallazgos; // Notas del técnico sobre lo que encontró
    private String accionesCorrectivas;
}