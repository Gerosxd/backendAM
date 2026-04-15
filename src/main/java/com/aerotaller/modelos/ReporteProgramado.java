package com.aerotaller.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// ReporteProgramado.java
@Setter
@Getter
@Entity
@Table(name = "ReporteProgramado")
public class ReporteProgramado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idReporte", insertable = false, updatable = false)
    private int idReporte;

    @Column(name = "Codigo", unique = true, nullable = false)
    private String codigo;

    @Column(name = "Descripcion")
    private String descripcion;

    @Column(name = "Modelo")
    private int modelo;

    // 2. AGREGAMOS LA RELACIÓN:
    // Usamos el mismo nombre de columna "Modelo" pero solo para LECTURA (insertable/updatable = false)
    @ManyToOne
    @JoinColumn(name = "Modelo", referencedColumnName = "idModelo", insertable = false, updatable = false)
    private ModeloAeronave modeloAeronave;

    @Column(name = "Tecnico")
    private String tecnico; // En tu DB es VARCHAR, cámbialo a String en Java

    @Column(name = "horasTotales")
    private double horasTotales;

    public ReporteProgramado() {}
}