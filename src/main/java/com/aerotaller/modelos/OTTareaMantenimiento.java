package com.aerotaller.modelos;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "OTTareaMantenimiento")
public class OTTareaMantenimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTareaOT")
    private Integer idTareaOT;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OT", nullable = false)
    private NuevaOT ot;

    @Column(name = "Codigo", nullable = false, length = 20)
    private String codigo;

    @Column(name = "Descripcion", nullable = false, length = 255)
    private String descripcion;

    @Column(name = "Tecnicos", length = 150)
    private String tecnicos;

    @Column(name = "HorasTotales", precision = 10, scale = 2)
    private BigDecimal horasTotales;

    @Column(name = "TipoTarea", length = 100)
    private String tipoTarea;

    public OTTareaMantenimiento() {
    }

    public Integer getIdTareaOT() {
        return idTareaOT;
    }

    public void setIdTareaOT(Integer idTareaOT) {
        this.idTareaOT = idTareaOT;
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

    public String getTecnicos() {
        return tecnicos;
    }

    public void setTecnicos(String tecnicos) {
        this.tecnicos = tecnicos;
    }

    public BigDecimal getHorasTotales() {
        return horasTotales;
    }

    public void setHorasTotales(BigDecimal horasTotales) {
        this.horasTotales = horasTotales;
    }

    public String getTipoTarea() {
        return tipoTarea;
    }

    public void setTipoTarea(String tipoTarea) {
        this.tipoTarea = tipoTarea;
    }
}