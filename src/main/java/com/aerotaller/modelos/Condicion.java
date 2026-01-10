package com.aerotaller.modelos;

public class Condicion {

    private int idCondicion;
    private String nombre;

    public Condicion() {
    }

    public Condicion(int idCondicion, String nombre) {
        this.setIdCondicion(idCondicion);
        this.setNombre(nombre);
    }

    public int getIdCondicion() {
        return idCondicion;
    }

    public void setIdCondicion(int idCondicion) {
        this.idCondicion = idCondicion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
