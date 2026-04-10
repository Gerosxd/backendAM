package com.aerotaller.modules.ot.dto;

import java.math.BigDecimal;

public class OTTareaRequest {

    private String codigo;
    private String descripcion;
    private String tecnicos;
    private BigDecimal horasTotales;
    private String tipoTarea;

    public OTTareaRequest() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTecnicos() {
        return tecnicos;
    }

    public void setTecnicos(String tecnicos) {
        this.tecnicos = tecnicos;
    }

    public BigDecimal getHorasTotales() {
        return horasTotales;
    }

    public void setHorasTotales(BigDecimal horasTotales) {
        this.horasTotales = horasTotales;
    }

    public String getTipoTarea() {
        return tipoTarea;
    }

    public void setTipoTarea(String tipoTarea) {
        this.tipoTarea = tipoTarea;
    }
}