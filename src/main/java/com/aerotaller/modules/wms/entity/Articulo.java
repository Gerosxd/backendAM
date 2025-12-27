package com.aerotaller.modules.wms.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "articulos")
public class Articulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private String noSerie;
    private String descripcion;
    private String unidadMedida; // U/M
    private Boolean requiereSerie; // El switch de "Requiere Número de Serie"

    private Double stock;
    private Double stockMinimo;
    private Double stockMaximo;
    private Double precioCompra;

    private String condicion; // Nuevo, Reparado, etc.
    private String estado;    // Activo / Inactivo

    private LocalDate fechaRegistro;
    private LocalDate ultimaEntrada;
    private LocalDate ultimaSalida;

    @ManyToOne
    private Categoria categoria;

    @ManyToOne
    private Proveedor proveedor;

    // Relación para "Agregar Ubicación"
    @OneToMany(mappedBy = "articulo", cascade = CascadeType.ALL)
    private List<ArticuloStock> ubicaciones;


}