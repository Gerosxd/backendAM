package com.aerotaller.modules.entradaart.dto;

public class EntradaArticuloExportRequestDto
{

    private String encargadoAlmacen;
    private String fechaEncargado;
    private String traslada;
    private String fechaTraslada;
    private String recibe;
    private String fechaRecibe;

    public String getEncargadoAlmacen()
    {
        return encargadoAlmacen;
    }

    public void setEncargadoAlmacen(String encargadoAlmacen)
    {
        this.encargadoAlmacen = encargadoAlmacen;
    }

    public String getFechaEncargado()
    {
        return fechaEncargado;
    }

    public void setFechaEncargado(String fechaEncargado)
    {
        this.fechaEncargado = fechaEncargado;
    }

    public String getTraslada()
    {
        return traslada;
    }

    public void setTraslada(String traslada)
    {
        this.traslada = traslada;
    }

    public String getFechaTraslada()
    {
        return fechaTraslada;
    }

    public void setFechaTraslada(String fechaTraslada)
    {
        this.fechaTraslada = fechaTraslada;
    }

    public String getRecibe()
    {
        return recibe;
    }

    public void setRecibe(String recibe)
    {
        this.recibe = recibe;
    }

    public String getFechaRecibe()
    {
        return fechaRecibe;
    }

    public void setFechaRecibe(String fechaRecibe)
    {
        this.fechaRecibe = fechaRecibe;
    }
}