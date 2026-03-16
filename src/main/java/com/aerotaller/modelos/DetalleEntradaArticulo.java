package com.aerotaller.modelos;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "DetalleEntradaArticulo")
public class DetalleEntradaArticulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDetalleEntrada")
    private Integer idDetalleEntrada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Entrada", nullable = false)
    private EntradaArticulo entrada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Articulo", nullable = false)
    private Articulo articulo;

    @Column(name = "Cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "Ubicacion", nullable = false, length = 255)
    private String ubicacion;

    @Column(name = "PrecioUnitario", precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    public Integer getIdDetalleEntrada() {
        return idDetalleEntrada;
    }

    public void setIdDetalleEntrada(Integer idDetalleEntrada) {
        this.idDetalleEntrada = idDetalleEntrada;
    }

    public EntradaArticulo getEntrada() {
        return entrada;
    }

    public void setEntrada(EntradaArticulo entrada) {
        this.entrada = entrada;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}