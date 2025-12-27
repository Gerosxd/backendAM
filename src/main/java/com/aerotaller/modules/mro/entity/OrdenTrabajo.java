package com.aerotaller.modules.mro.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "ordenes_trabajo")
public class OrdenTrabajo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroOT;
    private String matricula;
    private String modelo;
    private String compania;

    private LocalDate fechaCreacion;
    private LocalDate fechaCierre;
    private LocalDate fechaEntrega;

    // Datos de contacto (Imagen 3)
    private String contacto;
    private String telefono;
    private String correo;
    private String direccion;
    private String ciudad;
    private String estado;

    // TIEMPOS Y CICLOS (Crucial para aeronáutica)
    private Double tiempoPlaneador;
    private Integer ciclosPlaneador;

    private Double tiempoMotor1LH;
    private Integer ciclosMotor1LH;

    private Double tiempoMotor2RH;
    private Integer ciclosMotor2RH;

    private Double tiempoMotor3Center;
    private Integer ciclosMotor3Center;

    private Double tiempoAPU;
    private Integer ciclosAPU;

    @Column(columnDefinition = "TEXT")
    private String comentariosAdicionales;
}