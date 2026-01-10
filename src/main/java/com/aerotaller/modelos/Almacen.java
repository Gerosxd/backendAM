package com.aerotaller.modelos;

public class Almacen {

    private int idAlmacen;
    private String nombre;
    private String direccion;
    private String ciudad;
    private String estado;

    public Almacen() {
    }

    public Almacen(int idAlmacen, String nombre, String direccion, String ciudad, String estado) {
        this.setIdAlmacen(idAlmacen);
        this.setNombre(nombre);
        this.setDireccion(direccion);
        this.setCiudad(ciudad);
        this.setEstado(estado);
    }

    public int getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(int idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
