package com.aerotaller.modelos;

public class Cliente {

    private int idCliente;
    private String compania;
    private String contacto;
    private String telefono;
    private String correo;
    private String direccion;
    private String colonia;
    private String ciudad;
    private String estado;
    private String pais;

    public Cliente() {
    }

    public Cliente(int idCliente, String compania, String contacto, String telefono, String correo, String direccion, String colonia, String ciudad, String estado, String pais) {
        this.setIdCliente(idCliente);
        this.setCompania(compania);
        this.setContacto(contacto);
        this.setTelefono(telefono);
        this.setCorreo(correo);
        this.setDireccion(direccion);
        this.setColonia(colonia);
        this.setCiudad(ciudad);
        this.setEstado(estado);
        this.setPais(pais);
    }


    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
