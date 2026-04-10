package com.aerotaller.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "OTDiscrepancia")
public class OTDiscrepancia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idOTDiscrepancia")
    private Integer idOTDiscrepancia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OT", nullable = false)
    private NuevaOT ot;

    @Column(name = "Codigo", nullable = false, length = 20)
    private String codigo;

    @Column(name = "Descripcion", nullable = false, length = 255)
    private String descripcion;

    @Column(name = "Estatus", length = 100)
    private String estatus;

    @Column(name = "Acciones", length = 255)
    private String acciones;

    public OTDiscrepancia() {
    }

    public Integer getIdOTDiscrepancia() {
        return idOTDiscrepancia;
    }

    public void setIdOTDiscrepancia(Integer idOTDiscrepancia) {
        this.idOTDiscrepancia = idOTDiscrepancia;
    }

    public NuevaOT getOt() {
        return ot;
    }

    public void setOt(NuevaOT ot) {
        this.ot = ot;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getAcciones() {
        return acciones;
    }

    public void setAcciones(String acciones) {
        this.acciones = acciones;
    }
}