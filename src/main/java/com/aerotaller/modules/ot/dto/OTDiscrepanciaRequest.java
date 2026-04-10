package com.aerotaller.modules.ot.dto;

public class OTDiscrepanciaRequest
{

    private String codigo;
    private String descripcion;
    private String estatus;
    private String acciones;

    public OTDiscrepanciaRequest()
    {
    }

    public String getCodigo()
    {
        return codigo;
    }

    public void setCodigo(String codigo)
    {
        this.codigo = codigo;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public String getEstatus()
    {
        return estatus;
    }

    public void setEstatus(String estatus)
    {
        this.estatus = estatus;
    }

    public String getAcciones()
    {
        return acciones;
    }

    public void setAcciones(String acciones)
    {
        this.acciones = acciones;
    }
}