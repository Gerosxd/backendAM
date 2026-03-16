package com.aerotaller.modules.entradaart.dto;

import java.util.List;

public class EntradaArticuloRegistroResponseDto {

    private Integer idEntrada;
    private String folio;
    private String fechaEntrada;
    private String proveedor;
    private String almacenDestino;
    private String estado;
    private String recibidoPor;
    private List<EntradaArticuloDetalleResponseDto> detalles;

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

    public String getAlmacenDestino() {
        return almacenDestino;
    }

    public void setAlmacenDestino(String almacenDestino) {
        this.almacenDestino = almacenDestino;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRecibidoPor() {
        return recibidoPor;
    }

    public void setRecibidoPor(String recibidoPor) {
        this.recibidoPor = recibidoPor;
    }

    public List<EntradaArticuloDetalleResponseDto> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<EntradaArticuloDetalleResponseDto> detalles) {
        this.detalles = detalles;
    }
}