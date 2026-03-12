package com.aerotaller.modules.catalogo.dto;

public class CatalogoOptionDto
{

    private Integer id;
    private String nombre;

    public CatalogoOptionDto()
    {
    }

    public CatalogoOptionDto(Integer id, String nombre)
    {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
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