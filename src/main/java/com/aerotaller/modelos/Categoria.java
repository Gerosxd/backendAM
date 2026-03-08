package com.aerotaller.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "Categoria")
public class Categoria
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCategoria")
    private Integer idCategoria;

    @Column(name = "Nombre", nullable = false, length = 15)
    private String nombre;

    public Integer getIdCategoria()
    {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria)
    {
        this.idCategoria = idCategoria;
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