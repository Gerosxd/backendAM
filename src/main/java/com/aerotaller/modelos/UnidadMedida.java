package com.aerotaller.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "UnidadMedida")
public class UnidadMedida
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUnidad")
    private Integer idUnidad;

    @Column(name = "Nombre", nullable = false, length = 10)
    private String nombre;

    public Integer getIdUnidad()
    {
        return idUnidad;
    }

    public void setIdUnidad(Integer idUnidad)
    {
        this.idUnidad = idUnidad;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
}