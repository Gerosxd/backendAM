package com.aerotaller.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "DetalleSalidaArt")
public class DetalleSalidaArt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetalle;

    private Integer qty;

    private String observaciones;

    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "idSalidaArt")
    private SalidaArt salidaArt;

    @ManyToOne
    @JoinColumn(name = "idArticulo")
    private Articulo articulo;

    public DetalleSalidaArt() {}

    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public SalidaArt getSalidaArt() {
        return salidaArt;
    }

    public void setSalidaArt(SalidaArt salidaArt) {
        this.salidaArt = salidaArt;
    }

    // Backwards-compatible alias used by older code paths.
    public void setSalida(SalidaArt salidaArt) {
        setSalidaArt(salidaArt);
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }
}
