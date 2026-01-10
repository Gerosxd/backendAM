package com.aerotaller.modelos;

public class EstatusOT {

    private int idEstatus;
    private String nombre;

    public EstatusOT() {
    }

    public EstatusOT(int idEstatus, String nombre) {
        this.setIdEstatus(idEstatus);
        this.setNombre(nombre);
    }


    public int getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(int idEstatus) {
        this.idEstatus = idEstatus;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
