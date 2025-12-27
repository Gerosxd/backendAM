package com.aerotaller.modules.wms.entity;

import jakarta.persistence.*;

@Entity
public class ArticuloStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Articulo articulo;

    private String bodega;    // Almacén
    private String localizacion; // Rack/Ubicación
    private Double cantidad;
}