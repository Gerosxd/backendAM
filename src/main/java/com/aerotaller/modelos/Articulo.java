package com.aerotaller.modelos;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Articulo")
public class Articulo
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idArticulo")
    private Integer idArticulo;

    @Column(name = "Codigo", nullable = false, unique = true, length = 50)
    private String codigo;

    @Column(name = "NoSerie", nullable = false, length = 50)
    private String noSerie;

    @Column(name = "Descripcion", nullable = false, length = 255)
    private String descripcion;

    @Column(name = "Categoria", nullable = false)
    private Integer categoria;

    @Column(name = "UnidadMedida", nullable = false)
    private Integer unidadMedida;

    @Column(name = "Almacen", nullable = false)
    private Integer almacen;

    @Column(name = "Ubicacion", nullable = false, length = 255)
    private String ubicacion;

    @Column(name = "Proveedor", nullable = false)
    private Integer proveedor;

    @Column(name = "PrecioCompra", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioCompra;

    @Column(name = "Stock", nullable = false)
    private Integer stock;

    @Column(name = "Condicion", nullable = false)
    private Integer condicion;

    public Integer getIdArticulo()
    {
        return idArticulo;
    }

    public void setIdArticulo(Integer idArticulo)
    {
        this.idArticulo = idArticulo;
    }

    public String getCodigo()
    {
        return codigo;
    }

    public void setCodigo(String codigo)
    {
        this.codigo = codigo;
    }

    public String getNoSerie()
    {
        return noSerie;
    }

    public void setNoSerie(String noSerie)
    {
        this.noSerie = noSerie;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public Integer getCategoria()
    {
        return categoria;
    }

    public void setCategoria(Integer categoria)
    {
        this.categoria = categoria;
    }

    public Integer getUnidadMedida()
    {
        return unidadMedida;
    }

    public void setUnidadMedida(Integer unidadMedida)
    {
        this.unidadMedida = unidadMedida;
    }

    public Integer getAlmacen()
    {
        return almacen;
    }

    public void setAlmacen(Integer almacen)
    {
        this.almacen = almacen;
    }

    public String getUbicacion()
    {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion)
    {
        this.ubicacion = ubicacion;
    }

    public Integer getProveedor()
    {
        return proveedor;
    }

    public void setProveedor(Integer proveedor)
    {
        this.proveedor = proveedor;
    }

    public BigDecimal getPrecioCompra()
    {
        return precioCompra;
    }

    public void setPrecioCompra(BigDecimal precioCompra)
    {
        this.precioCompra = precioCompra;
    }

    public Integer getStock()
    {
        return stock;
    }

    public void setStock(Integer stock)
    {
        this.stock = stock;
    }

    public Integer getCondicion()
    {
        return condicion;
    }

    public void setCondicion(Integer condicion)
    {
        this.condicion = condicion;
    }
}