package com.aerotaller.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuario")
public class Usuario
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private Integer idUsuario;

    @Column(name = "Nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "Usuario", nullable = false, unique = true, length = 30)
    private String usuario;

    @Column(name = "Correo", nullable = false, unique = true, length = 100)
    private String correo;

    @Column(name = "Contrasenia", nullable = false, length = 255)
    private String contrasenia;

    @Column(name = "Rol", nullable = false)
    private Integer rol;

    @Column(name = "TipoDoc", nullable = false)
    private Integer tipoDoc;

    @Column(name = "NumeroDoc", nullable = false, length = 15)
    private String numeroDoc;

    @Column(name = "Direccion", length = 50)
    private String direccion;

    @Column(name = "Telefono", length = 15)
    private String telefono;

    public Integer getIdUsuario()
    {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario)
    {
        this.idUsuario = idUsuario;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getUsuario()
    {
        return usuario;
    }

    public void setUsuario(String usuario)
    {
        this.usuario = usuario;
    }

    public String getCorreo()
    {
        return correo;
    }

    public void setCorreo(String correo)
    {
        this.correo = correo;
    }

    public String getContrasenia()
    {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia)
    {
        this.contrasenia = contrasenia;
    }

    public Integer getRol()
    {
        return rol;
    }

    public void setRol(Integer rol)
    {
        this.rol = rol;
    }

    public Integer getTipoDoc()
    {
        return tipoDoc;
    }

    public void setTipoDoc(Integer tipoDoc)
    {
        this.tipoDoc = tipoDoc;
    }

    public String getNumeroDoc()
    {
        return numeroDoc;
    }

    public void setNumeroDoc(String numeroDoc)
    {
        this.numeroDoc = numeroDoc;
    }

    public String getDireccion()
    {
        return direccion;
    }

    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }

    public String getTelefono()
    {
        return telefono;
    }

    public void setTelefono(String telefono)
    {
        this.telefono = telefono;
    }
}