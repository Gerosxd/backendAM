package com.aerotaller.modelos;

public class Rol {
    private int idRol;
    private String nombre;
    private String descripcion;

    public Rol() {
    }

    public Rol(int idRol, String nombre, String descripcion) {
        this.setIdRol(idRol);
        this.setNombre(nombre);
        this.setDescripcion(descripcion);
    }


    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
