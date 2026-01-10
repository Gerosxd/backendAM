package com.aerotaller.modelos;

public class Usuario {

    private int idUsuario;
    private String nombre;
    private String usuario;
    private int rol;
    private int tipoDoc;
    private String numeroDoc;
    private String direccion;
    private String telefono;
    private String correo;
    private String contrasenia;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nombre, String usuario, int rol, int tipoDoc, String numeroDoc, String direccion, String telefono, String correo, String contrasenia) {
        this.setIdUsuario(idUsuario);
        this.setNombre(nombre);
        this.setUsuario(usuario);
        this.setRol(rol);
        this.setTipoDoc(tipoDoc);
        this.setNumeroDoc(numeroDoc);
        this.setDireccion(direccion);
        this.setTelefono(telefono);
        this.setCorreo(correo);
        this.setContrasenia(contrasenia);
    }


    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public int getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(int tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getNumeroDoc() {
        return numeroDoc;
    }

    public void setNumeroDoc(String numeroDoc) {
        this.numeroDoc = numeroDoc;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
