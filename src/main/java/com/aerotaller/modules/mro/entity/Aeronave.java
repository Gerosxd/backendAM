package com.aerotaller.modules.mro.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "mro_aeronaves")
@Data
public class Aeronave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String matricula;
    private String noSerie;
    @ManyToOne
    private Modelo modelo;
    @ManyToOne
    private Cliente cliente;
}