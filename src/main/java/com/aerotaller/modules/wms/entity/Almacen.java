package com.aerotaller.modules.wms.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "wms_almacenes")
@Data
public class Almacen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String ubicacionFisica;
}