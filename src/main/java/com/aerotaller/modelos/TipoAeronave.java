package com.aerotaller.modelos;

public class TipoAeronave {

    private int idModelo;
    private String nombre;

    public TipoAeronave() {
    }

    public TipoAeronave(int idModelo, String nombre) {
        this.setIdModelo(idModelo);
        this.setNombre(nombre);
    }


    public int getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(int idModelo) {
        this.idModelo = idModelo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
