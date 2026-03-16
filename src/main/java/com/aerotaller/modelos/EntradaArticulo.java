package com.aerotaller.modelos;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "EntradaArticulo")
public class EntradaArticulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEntrada")
    private Integer idEntrada;

    @Column(name = "Folio", nullable = false, unique = true, length = 20)
    private String folio;

    @Column(name = "FechaEntrada", nullable = false)
    private LocalDateTime fechaEntrada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Proveedor")
    private Proveedor proveedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AlmacenDestino", nullable = false)
    private Almacen almacenDestino;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EstadoEntrada", nullable = false)
    private EstadoEntrada estadoEntrada;

    @Column(name = "Observaciones", length = 255)
    private String observaciones;

    @OneToMany(mappedBy = "entrada", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleEntradaArticulo> detalles = new ArrayList<>();

    public Integer getIdEntrada() {
        return idEntrada;
    }

    public void setIdEntrada(Integer idEntrada) {
        this.idEntrada = idEntrada;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public LocalDateTime getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDateTime fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Almacen getAlmacenDestino() {
        return almacenDestino;
    }

    public void setAlmacenDestino(Almacen almacenDestino) {
        this.almacenDestino = almacenDestino;
    }

    public EstadoEntrada getEstadoEntrada() {
        return estadoEntrada;
    }

    public void setEstadoEntrada(EstadoEntrada estadoEntrada) {
        this.estadoEntrada = estadoEntrada;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public List<DetalleEntradaArticulo> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleEntradaArticulo> detalles) {
        this.detalles = detalles;
    }

    public void agregarDetalle(DetalleEntradaArticulo detalle) {
        detalles.add(detalle);
        detalle.setEntrada(this);
    }

    public void removerDetalle(DetalleEntradaArticulo detalle) {
        detalles.remove(detalle);
        detalle.setEntrada(null);
    }
}