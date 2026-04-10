package com.aerotaller.modules.ot.dto;

public class CrearOTResponse
{

    private Integer idOT;
    private String noOT;
    private String mensaje;

    public CrearOTResponse()
    {
    }

    public CrearOTResponse(Integer idOT, String noOT, String mensaje)
    {
        this.idOT = idOT;
        this.noOT = noOT;
        this.mensaje = mensaje;
    }

    public Integer getIdOT()
    {
        return idOT;
    }

    public void setIdOT(Integer idOT)
    {
        this.idOT = idOT;
    }

    public String getNoOT()
    {
        return noOT;
    }

    public void setNoOT(String noOT)
    {
        this.noOT = noOT;
    }

    public String getMensaje()
    {
        return mensaje;
    }

    public void setMensaje(String mensaje)
    {
        this.mensaje = mensaje;
    }
}