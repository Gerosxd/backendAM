package com.aerotaller.modules.wms.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "proveedores")
@Data
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(length = 100)
    private String contacto;

    @Column(name = "telefono_1", length = 20)
    private String telefono1;

    @Column(name = "telefono_2", length = 20)
    private String telefono2;

    @Column(length = 100)
    private String email;

    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean activo = true;
}