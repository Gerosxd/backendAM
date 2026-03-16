package com.aerotaller.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "EstadoEntrada")
public class EstadoEntrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEstadoEntrada")
    private Integer idEstadoEntrada;

    @Column(name = "Nombre", nullable = false, length = 30)
    private String nombre;

    public Integer getIdEstadoEntrada() {
        return idEstadoEntrada;
    }

    public void setIdEstadoEntrada(Integer idEstadoEntrada) {
        this.idEstadoEntrada = idEstadoEntrada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}