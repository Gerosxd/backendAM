package com.aerotaller.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "Proveedor")
public class Proveedor
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProveedor")
    private Integer idProveedor;

    @Column(name = "Nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "Contacto", nullable = false, length = 50)
    private String contacto;

    @Column(name = "Correo", length = 100)
    private String correo;

    @Column(name = "Direccion", length = 50)
    private String direccion;

    @Column(name = "CodigoPostal", length = 10)
    private String codigoPostal;

    @Column(name = "Ciudad", nullable = false, length = 25)
    private String ciudad;

    @Column(name = "Telefono", nullable = false, length = 15)
    private String telefono;

    @Column(name = "Estado", nullable = false)
    private Integer estado;

    public Integer getIdProveedor()
    {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor)
    {
        this.idProveedor = idProveedor;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getContacto()
    {
        return contacto;
    }

    public void setContacto(String contacto)
    {
        this.contacto = contacto;
    }

    public String getCorreo()
    {
        return correo;
    }

    public void setCorreo(String correo)
    {
        this.correo = correo;
    }

    public String getDireccion()
    {
        return direccion;
    }

    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }

    public String getCodigoPostal()
    {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal)
    {
        this.codigoPostal = codigoPostal;
    }

    public String getCiudad()
    {
        return ciudad;
    }

    public void setCiudad(String ciudad)
    {
        this.ciudad = ciudad;
    }

    public String getTelefono()
    {
        return telefono;
    }

    public void setTelefono(String telefono)
    {
        this.telefono = telefono;
    }

    public Integer getEstado()
    {
        return estado;
    }

    public void setEstado(Integer estado)
    {
        this.estado = estado;
    }
}