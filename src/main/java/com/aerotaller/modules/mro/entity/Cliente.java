package com.aerotaller.modules.mro.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "mro_clientes")
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String compania;
    private String rfc;
    private String responsable;
    private String telefono;
    private String direccion;
}