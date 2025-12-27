package com.aerotaller.modules.wms.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "wms_entrada_detalles")
@Data
public class EntradaDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Articulo articulo;
    private Double cantidad;
    private Double precioUnitario;
    @ManyToOne
    private Entrada entrada;
}