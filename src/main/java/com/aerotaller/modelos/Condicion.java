package com.aerotaller.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "Condicion")
public class Condicion
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCondicion")
    private Integer idCondicion;

    @Column(name = "Nombre", nullable = false, length = 30)
    private String nombre;

    public Integer getIdCondicion()
    {
        return idCondicion;
    }

    public void setIdCondicion(Integer idCondicion)
    {
        this.idCondicion = idCondicion;
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