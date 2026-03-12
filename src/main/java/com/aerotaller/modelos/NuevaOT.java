package com.aerotaller.modelos;

import java.util.Date;

public class NuevaOT {
    private int idOT;
    private int aeronave;
    private Date fechaCreacion;
    private Date fechaEntrega;
    private int cliente;
    private int estatus;
    private double tiempoPlaneador;
    private int cicloPlaneador;
    private double tiempoMotorLH;
    private int cicloMotorLH;
    private double tiempoMotorRH;
    private int cicloMotorRH;
    private double tiempoMotorC;
    private int cicloMotorC;
    private double tiempoAPU;
    private int cicloAPU;
    private String comentario;
    private int reporte;
    private int discrepancia;

    public NuevaOT() {
    }

    public NuevaOT(int idOT, int aeronave, Date fechaCreacion, Date fechaEntrega, int cliente, int estatus, double tiempoPlaneador, int cicloPlaneador, double tiempoMotorLH, int cicloMotorLH, double tiempoMotorRH, int cicloMotorRH, double tiempoMotorC, int cicloMotorC, double tiempoAPU, int cicloAPU, String comentario, int reporte, int discrepancia) {
        this.setIdOT(idOT);
        this.setAeronave(aeronave);
        this.setFechaCreacion(fechaCreacion);
        this.setFechaEntrega(fechaEntrega);
        this.setCliente(cliente);
        this.setEstatus(estatus);
        this.setTiempoPlaneador(tiempoPlaneador);
        this.setCicloPlaneador(cicloPlaneador);
        this.setTiempoMotorLH(tiempoMotorLH);
        this.setCicloMotorLH(cicloMotorLH);
        this.setTiempoMotorRH(tiempoMotorRH);
        this.setCicloMotorRH(cicloMotorRH);
        this.setTiempoMotorC(tiempoMotorC);
        this.setCicloMotorC(cicloMotorC);
        this.setTiempoAPU(tiempoAPU);
        this.setCicloAPU(cicloAPU);
        this.setComentario(comentario);
        this.setReporte(reporte);
        this.setDiscrepancia(discrepancia);
    }


    public int getIdOT() {
        return idOT;
    }

    public void setIdOT(int idOT) {
        this.idOT = idOT;
    }

    public int getAeronave() {
        return aeronave;
    }

    public void setAeronave(int aeronave) {
        this.aeronave = aeronave;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public double getTiempoPlaneador() {
        return tiempoPlaneador;
    }

    public void setTiempoPlaneador(double tiempoPlaneador) {
        this.tiempoPlaneador = tiempoPlaneador;
    }

    public int getCicloPlaneador() {
        return cicloPlaneador;
    }

    public void setCicloPlaneador(int cicloPlaneador) {
        this.cicloPlaneador = cicloPlaneador;
    }

    public double getTiempoMotorLH() {
        return tiempoMotorLH;
    }

    public void setTiempoMotorLH(double tiempoMotorLH) {
        this.tiempoMotorLH = tiempoMotorLH;
    }

    public int getCicloMotorLH() {
        return cicloMotorLH;
    }

    public void setCicloMotorLH(int cicloMotorLH) {
        this.cicloMotorLH = cicloMotorLH;
    }

    public double getTiempoMotorRH() {
        return tiempoMotorRH;
    }

    public void setTiempoMotorRH(double tiempoMotorRH) {
        this.tiempoMotorRH = tiempoMotorRH;
    }

    public int getCicloMotorRH() {
        return cicloMotorRH;
    }

    public void setCicloMotorRH(int cicloMotorRH) {
        this.cicloMotorRH = cicloMotorRH;
    }

    public double getTiempoMotorC() {
        return tiempoMotorC;
    }

    public void setTiempoMotorC(double tiempoMotorC) {
        this.tiempoMotorC = tiempoMotorC;
    }

    public int getCicloMotorC() {
        return cicloMotorC;
    }

    public void setCicloMotorC(int cicloMotorC) {
        this.cicloMotorC = cicloMotorC;
    }

    public double getTiempoAPU() {
        return tiempoAPU;
    }

    public void setTiempoAPU(double tiempoAPU) {
        this.tiempoAPU = tiempoAPU;
    }

    public int getCicloAPU() {
        return cicloAPU;
    }

    public void setCicloAPU(int cicloAPU) {
        this.cicloAPU = cicloAPU;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getReporte() {
        return reporte;
    }

    public void setReporte(int reporte) {
        this.reporte = reporte;
    }

    public int getDiscrepancia() {
        return discrepancia;
    }

    public void setDiscrepancia(int discrepancia) {
        this.discrepancia = discrepancia;
    }
}
