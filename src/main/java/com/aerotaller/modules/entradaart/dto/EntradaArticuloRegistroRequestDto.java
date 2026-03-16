package com.aerotaller.modules.entradaart.dto;

import java.util.List;

public class EntradaArticuloRegistroRequestDto {

    private Integer usuario;
    private Integer proveedor;
    private Integer almacenDestino;
    private Integer estadoEntrada;
    private String observaciones;
    private List<EntradaArticuloDetalleRequestDto> detalles;

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public Integer getProveedor() {
        return proveedor;
    }

    public void setProveedor(Integer proveedor) {
        this.proveedor = proveedor;
    }

    public Integer getAlmacenDestino() {
        return almacenDestino;
    }

    public void setAlmacenDestino(Integer almacenDestino) {
        this.almacenDestino = almacenDestino;
    }

    public Integer getEstadoEntrada() {
        return estadoEntrada;
    }

    public void setEstadoEntrada(Integer estadoEntrada) {
        this.estadoEntrada = estadoEntrada;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public List<EntradaArticuloDetalleRequestDto> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<EntradaArticuloDetalleRequestDto> detalles) {
        this.detalles = detalles;
    }
}