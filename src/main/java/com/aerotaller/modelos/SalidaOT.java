package com.aerotaller.modelos;

import java.util.Date;

public class SalidaOT {

    private int idSalida;
    private int tipoComprobante;
    private String nsComprobante;
    private String noComprobante;
    private int OT;
    private double impuesto;
    private Date fechaCreacion;
    private boolean aplicado;

    public SalidaOT() {
    }

    public SalidaOT(int idSalida, int tipoComprobante, String nsComprobante, String noComprobante, int OT, double impuesto, Date fechaCreacion, boolean aplicado) {
        this.setIdSalida(idSalida);
        this.setTipoComprobante(tipoComprobante);
        this.setNsComprobante(nsComprobante);
        this.setNoComprobante(noComprobante);
        this.setOT(OT);
        this.setImpuesto(impuesto);
        this.setFechaCreacion(fechaCreacion);
        this.setAplicado(aplicado);
    }


    public int getIdSalida() {
        return idSalida;
    }

    public void setIdSalida(int idSalida) {
        this.idSalida = idSalida;
    }

    public int getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(int tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public String getNsComprobante() {
        return nsComprobante;
    }

    public void setNsComprobante(String nsComprobante) {
        this.nsComprobante = nsComprobante;
    }

    public String getNoComprobante() {
        return noComprobante;
    }

    public void setNoComprobante(String noComprobante) {
        this.noComprobante = noComprobante;
    }

    public int getOT() {
        return OT;
    }

    public void setOT(int OT) {
        this.OT = OT;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean isAplicado() {
        return aplicado;
    }

    public void setAplicado(boolean aplicado) {
        this.aplicado = aplicado;
    }
}
