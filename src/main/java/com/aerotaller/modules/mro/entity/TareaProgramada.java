package com.aerotaller.modules.mro.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "mro_tareas_programadas")
@Data
public class TareaProgramada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String codigoTarea; // Ejemplo: 21-00-01 (Referencia al capítulo ATA)

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion; // Descripción detallada de lo que se debe hacer

    @Column(length = 100)
    private String referenciaManual; // Ejemplo: AMM Rev. 35

    private Double horasEstimadas; // Tiempo que se estima tardará el técnico

    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean activo = true;

    // Relación opcional: Si la tarea es específica para un solo modelo de avión
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modelo_id")
    private Modelo modelo;

    public TareaProgramada() {}
}