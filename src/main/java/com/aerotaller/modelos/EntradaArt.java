package com.aerotaller.modelos;

import java.util.Date;

public class EntradaArt {

    private int idEntradaArt;
    private int usuario;
    private int articulo;
    private Date fechaEntrada;
    private int proveedor;
    private int cantidad;

    public EntradaArt() {
    }

    public EntradaArt(int idEntradaArt, int usuario, int articulo, Date fechaEntrada, int proveedor, int cantidad) {
        this.setIdEntradaArt(idEntradaArt);
        this.setUsuario(usuario);
        this.setArticulo(articulo);
        this.setFechaEntrada(fechaEntrada);
        this.setProveedor(proveedor);
        this.setCantidad(cantidad);
    }


    public int getIdEntradaArt() {
        return idEntradaArt;
    }

    public void setIdEntradaArt(int idEntradaArt) {
        this.idEntradaArt = idEntradaArt;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getArticulo() {
        return articulo;
    }

    public void setArticulo(int articulo) {
        this.articulo = articulo;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public int getProveedor() {
        return proveedor;
    }

    public void setProveedor(int proveedor) {
        this.proveedor = proveedor;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
