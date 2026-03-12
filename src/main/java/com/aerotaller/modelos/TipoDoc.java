package com.aerotaller.modelos;

public class TipoDoc {

    private int idTipo;
    private String nombre;

    public TipoDoc() {
    }

    public TipoDoc(int idTipo, String nombre) {
        this.setIdTipo(idTipo);
        this.setNombre(nombre);
    }


    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
