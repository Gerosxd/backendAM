package com.aerotaller.modules.wms.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "wms_salida_detalles")
@Data
public class SalidaDetalle {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Articulo articulo;
    private Double cantidad;
    @ManyToOne
    private Salida salida;
}