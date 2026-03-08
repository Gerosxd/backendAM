package com.aerotaller.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "Almacen")
public class Almacen
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAlmacen")
    private Integer idAlmacen;

    @Column(name = "Nombre", nullable = false, length = 25)
    private String nombre;

    @Column(name = "Direccion", nullable = false, length = 50)
    private String direccion;

    @Column(name = "Ciudad", nullable = false, length = 25)
    private String ciudad;

    @Column(name = "Estado", nullable = false)
    private Integer estado;

    public Integer getIdAlmacen()
    {
        return idAlmacen;
    }

    public void setIdAlmacen(Integer idAlmacen)
    {
        this.idAlmacen = idAlmacen;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getDireccion()
    {
        return direccion;
    }

    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }

    public String getCiudad()
    {
        return ciudad;
    }

    public void setCiudad(String ciudad)
    {
        this.ciudad = ciudad;
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