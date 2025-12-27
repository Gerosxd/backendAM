package com.aerotaller.modules.mro.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "mro_modelos")
@Data
public class Modelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre; // Ej: Cessna 206
    private String marca;  // Ej: Cessna
    private String descripcion;
}