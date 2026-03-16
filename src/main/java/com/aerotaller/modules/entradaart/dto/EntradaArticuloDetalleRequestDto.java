package com.aerotaller.modules.entradaart.dto;

public class EntradaArticuloDetalleRequestDto
{

    private String codigo;
    private String noSerie;
    private String descripcion;
    private Integer categoria;
    private Integer unidadMedida;
    private Integer cantidad;
    private String ubicacion;
    private Integer precioCompra;
    private Integer condicion;

    public String getCodigo()
    {
        return codigo;
    }

    public void setCodigo(String codigo)
    {
        this.codigo = codigo;
    }

    public String getNoSerie()
    {
        return noSerie;
    }

    public void setNoSerie(String noSerie)
    {
        this.noSerie = noSerie;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public Integer getCategoria()
    {
        return categoria;
    }

    public void setCategoria(Integer categoria)
    {
        this.categoria = categoria;
    }

    public Integer getUnidadMedida()
    {
        return unidadMedida;
    }

    public void setUnidadMedida(Integer unidadMedida)
    {
        this.unidadMedida = unidadMedida;
    }

    public Integer getCantidad()
    {
        return cantidad;
    }

    public void setCantidad(Integer cantidad)
    {
        this.cantidad = cantidad;
    }

    public String getUbicacion()
    {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion)
    {
        this.ubicacion = ubicacion;
    }

    public Integer getPrecioCompra()
    {
        return precioCompra;
    }

    public void setPrecioCompra(Integer precioCompra)
    {
        this.precioCompra = precioCompra;
    }

    public Integer getCondicion()
    {
        return condicion;
    }

    public void setCondicion(Integer condicion)
    {
        this.condicion = condicion;
    }
}