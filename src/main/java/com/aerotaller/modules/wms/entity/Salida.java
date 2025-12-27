package com.aerotaller.modules.wms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Salida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String folioVenta;
    private String tipoMovimiento;
    private Boolean aplicada; // Switch
    private String tipoComprobante;
    private String serieComprobante;
    private String numeroComprobante;
    private Double impuesto;
    private LocalDateTime fechaCreacion;

    private Long ordenTrabajoId; // Vínculo con MRO
}