package com.aerotaller.modules.entradaart.dto;

public class EntradaArticuloPdfDetalleDto
{

    private Integer item;
    private Integer cantidad;
    private String descripcion;
    private String numeroParte;
    private String numeroSerie;
    private String condicion;
    private String proveedor;
    private String observaciones;

    public Integer getItem()
    {
        return item;
    }

    public void setItem(Integer item)
    {
        this.item = item;
    }

    public Integer getCantidad()
    {
        return cantidad;
    }

    public void setCantidad(Integer cantidad)
    {
        this.cantidad = cantidad;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public String getNumeroParte()
    {
        return numeroParte;
    }

    public void setNumeroParte(String numeroParte)
    {
        this.numeroParte = numeroParte;
    }

    public String getNumeroSerie()
    {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie)
    {
        this.numeroSerie = numeroSerie;
    }

    public String getCondicion()
    {
        return condicion;
    }

    public void setCondicion(String condicion)
    {
        this.condicion = condicion;
    }

    public String getProveedor()
    {
        return proveedor;
    }

    public void setProveedor(String proveedor)
    {
        this.proveedor = proveedor;
    }

    public String getObservaciones()
    {
        return observaciones;
    }

    public void setObservaciones(String observaciones)
    {
        this.observaciones = observaciones;
    }
}