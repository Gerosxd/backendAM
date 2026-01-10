package com.aerotaller.modelos;

public class UnidadMedida {

    private int idUnidad;
    private String nombre;

    public UnidadMedida() {
    }

    public UnidadMedida(int idUnidad, String nombre) {
        this.setIdUnidad(idUnidad);
        this.setNombre(nombre);
    }


    public int getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(int idUnidad) {
        this.idUnidad = idUnidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
