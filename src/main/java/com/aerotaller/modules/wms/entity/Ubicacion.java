package com.aerotaller.modules.wms.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "wms_ubicaciones")
@Data
public class Ubicacion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rack;
    private String nivel;
    @ManyToOne
    private Almacen almacen;
}