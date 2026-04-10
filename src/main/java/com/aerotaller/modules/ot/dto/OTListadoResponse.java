package com.aerotaller.modules.ot.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OTListadoResponse
{

    private Integer idOT;
    private String noOT;
    private String matricula;
    private String cliente;
    private LocalDateTime fechaCreacion;
    private LocalDate fechaEntrega;
    private LocalDate fechaCierre;
    private String estado;

    public OTListadoResponse()
    {
    }

    public OTListadoResponse(
            Integer idOT,
            String noOT,
            String matricula,
            String cliente,
            LocalDateTime fechaCreacion,
            LocalDate fechaEntrega,
            LocalDate fechaCierre,
            String estado
    )
    {
        this.idOT = idOT;
        this.noOT = noOT;
        this.matricula = matricula;
        this.cliente = cliente;
        this.fechaCreacion = fechaCreacion;
        this.fechaEntrega = fechaEntrega;
        this.fechaCierre = fechaCierre;
        this.estado = estado;
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

    public String getMatricula()
    {
        return matricula;
    }

    public void setMatricula(String matricula)
    {
        this.matricula = matricula;
    }

    public String getCliente()
    {
        return cliente;
    }

    public void setCliente(String cliente)
    {
        this.cliente = cliente;
    }

    public LocalDateTime getFechaCreacion()
    {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion)
    {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaEntrega()
    {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega)
    {
        this.fechaEntrega = fechaEntrega;
    }

    public LocalDate getFechaCierre()
    {
        return fechaCierre;
    }

    public void setFechaCierre(LocalDate fechaCierre)
    {
        this.fechaCierre = fechaCierre;
    }

    public String getEstado()
    {
        return estado;
    }

    public void setEstado(String estado)
    {
        this.estado = estado;
    }
}