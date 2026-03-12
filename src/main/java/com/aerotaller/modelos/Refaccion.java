package com.aerotaller.modelos;

public class Refaccion {

    private int idRefaccion;
    private int OT;
    private int salidaOT;
    private int articulo;
    private int cantidad;
    private double precio;
    private String nsInstalada;
    private String npRetirada;
    private String nsRetirada;

    public Refaccion() {
    }

    public Refaccion(int idRefaccion, int OT, int salidaOT, int articulo, int cantidad, double precio, String nsInstalada, String npRetirada, String nsRetirada) {
        this.setIdRefaccion(idRefaccion);
        this.setOT(OT);
        this.setSalidaOT(salidaOT);
        this.setArticulo(articulo);
        this.setCantidad(cantidad);
        this.setPrecio(precio);
        this.setNsInstalada(nsInstalada);
        this.setNpRetirada(npRetirada);
        this.setNsRetirada(nsRetirada);
    }


    public int getIdRefaccion() {
        return idRefaccion;
    }

    public void setIdRefaccion(int idRefaccion) {
        this.idRefaccion = idRefaccion;
    }

    public int getOT() {
        return OT;
    }

    public void setOT(int OT) {
        this.OT = OT;
    }

    public int getSalidaOT() {
        return salidaOT;
    }

    public void setSalidaOT(int salidaOT) {
        this.salidaOT = salidaOT;
    }

    public int getArticulo() {
        return articulo;
    }

    public void setArticulo(int articulo) {
        this.articulo = articulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getNsInstalada() {
        return nsInstalada;
    }

    public void setNsInstalada(String nsInstalada) {
        this.nsInstalada = nsInstalada;
    }

    public String getNpRetirada() {
        return npRetirada;
    }

    public void setNpRetirada(String npRetirada) {
        this.npRetirada = npRetirada;
    }

    public String getNsRetirada() {
        return nsRetirada;
    }

    public void setNsRetirada(String nsRetirada) {
        this.nsRetirada = nsRetirada;
    }
}
