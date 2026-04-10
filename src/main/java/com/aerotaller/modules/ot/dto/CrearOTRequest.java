package com.aerotaller.modules.ot.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class CrearOTRequest
{

    private Integer idAeronave;
    private Integer idCliente;

    private LocalDateTime fechaApertura;
    private LocalDate fechaEntrega;
    private LocalDate fechaCierre;

    private BigDecimal horasTotales;
    private Integer ciclosTotales;

    private BigDecimal tiempoMotor1;
    private Integer cicloMotor1;

    private BigDecimal tiempoMotor2;
    private Integer cicloMotor2;

    private BigDecimal tiempoMotor3;
    private Integer cicloMotor3;

    private BigDecimal tiempoAPU;
    private Integer cicloAPU;

    private String comentarioCliente;

    private List<OTTareaRequest> tareasMantenimiento;
    private List<OTDiscrepanciaRequest> discrepancias;

    public CrearOTRequest()
    {
    }

    public Integer getIdAeronave()
    {
        return idAeronave;
    }

    public void setIdAeronave(Integer idAeronave)
    {
        this.idAeronave = idAeronave;
    }

    public Integer getIdCliente()
    {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente)
    {
        this.idCliente = idCliente;
    }

    public LocalDateTime getFechaApertura()
    {
        return fechaApertura;
    }

    public void setFechaApertura(LocalDateTime fechaApertura)
    {
        this.fechaApertura = fechaApertura;
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

    public BigDecimal getHorasTotales()
    {
        return horasTotales;
    }

    public void setHorasTotales(BigDecimal horasTotales)
    {
        this.horasTotales = horasTotales;
    }

    public Integer getCiclosTotales()
    {
        return ciclosTotales;
    }

    public void setCiclosTotales(Integer ciclosTotales)
    {
        this.ciclosTotales = ciclosTotales;
    }

    public BigDecimal getTiempoMotor1()
    {
        return tiempoMotor1;
    }

    public void setTiempoMotor1(BigDecimal tiempoMotor1)
    {
        this.tiempoMotor1 = tiempoMotor1;
    }

    public Integer getCicloMotor1()
    {
        return cicloMotor1;
    }

    public void setCicloMotor1(Integer cicloMotor1)
    {
        this.cicloMotor1 = cicloMotor1;
    }

    public BigDecimal getTiempoMotor2()
    {
        return tiempoMotor2;
    }

    public void setTiempoMotor2(BigDecimal tiempoMotor2)
    {
        this.tiempoMotor2 = tiempoMotor2;
    }

    public Integer getCicloMotor2()
    {
        return cicloMotor2;
    }

    public void setCicloMotor2(Integer cicloMotor2)
    {
        this.cicloMotor2 = cicloMotor2;
    }

    public BigDecimal getTiempoMotor3()
    {
        return tiempoMotor3;
    }

    public void setTiempoMotor3(BigDecimal tiempoMotor3)
    {
        this.tiempoMotor3 = tiempoMotor3;
    }

    public Integer getCicloMotor3()
    {
        return cicloMotor3;
    }

    public void setCicloMotor3(Integer cicloMotor3)
    {
        this.cicloMotor3 = cicloMotor3;
    }

    public BigDecimal getTiempoAPU()
    {
        return tiempoAPU;
    }

    public void setTiempoAPU(BigDecimal tiempoAPU)
    {
        this.tiempoAPU = tiempoAPU;
    }

    public Integer getCicloAPU()
    {
        return cicloAPU;
    }

    public void setCicloAPU(Integer cicloAPU)
    {
        this.cicloAPU = cicloAPU;
    }

    public String getComentarioCliente()
    {
        return comentarioCliente;
    }

    public void setComentarioCliente(String comentarioCliente)
    {
        this.comentarioCliente = comentarioCliente;
    }

    public List<OTTareaRequest> getTareasMantenimiento()
    {
        return tareasMantenimiento;
    }

    public void setTareasMantenimiento(List<OTTareaRequest> tareasMantenimiento)
    {
        this.tareasMantenimiento = tareasMantenimiento;
    }

    public List<OTDiscrepanciaRequest> getDiscrepancias()
    {
        return discrepancias;
    }

    public void setDiscrepancias(List<OTDiscrepanciaRequest> discrepancias)
    {
        this.discrepancias = discrepancias;
    }
}