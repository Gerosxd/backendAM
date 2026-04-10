package com.aerotaller.modules.entradaart.dto;

import java.util.List;

public class EntradaArticuloPdfResponseDto {

    private Integer idEntrada;
    private String folio;
    private String fechaEntrada;
    private String proveedor;
    private String observaciones;
    private String departamento;
    private List<EntradaArticuloPdfDetalleDto> detalles;

    public Integer getIdEntrada() {
        return idEntrada;
    }

    public void setIdEntrada(Integer idEntrada) {
        this.idEntrada = idEntrada;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public List<EntradaArticuloPdfDetalleDto> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<EntradaArticuloPdfDetalleDto> detalles) {
        this.detalles = detalles;
    }
}