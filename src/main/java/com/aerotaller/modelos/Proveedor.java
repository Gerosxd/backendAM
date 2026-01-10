package com.aerotaller.modelos;

public class Proveedor {

    private int idProveedor;
    private String nombre;
    private String contacto;
    private String correo;
    private String telefono;
    private String direccion;
    private String codigoPostal;
    private String ciudad;
    private int estado;

    public Proveedor() {
    }

    public Proveedor(int idProveedor, String nombre, String contacto, String correo, String telefono, String direccion, String codigoPostal, String ciudad, int estado) {
        this.setIdProveedor(idProveedor);
        this.setNombre(nombre);
        this.setContacto(contacto);
        this.setCorreo(correo);
        this.setTelefono(telefono);
        this.setDireccion(direccion);
        this.setCodigoPostal(codigoPostal);
        this.setCiudad(ciudad);
        this.setEstado(estado);
    }


    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
