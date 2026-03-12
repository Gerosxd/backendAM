package com.aerotaller.modelos;

public class ReporteProgramado {

    private int idReporte;
    private String codigo;
    private String descripcion;
    private int modelo;
    private int tecnico;
    private double horasTotales;

    public ReporteProgramado() {
    }

    public ReporteProgramado(int idReporte, String codigo, String descripcion, int modelo, int tecnico, double horasTotales) {
        this.setIdReporte(idReporte);
        this.setCodigo(codigo);
        this.setDescripcion(descripcion);
        this.setModelo(modelo);
        this.setTecnico(tecnico);
        this.setHorasTotales(horasTotales);
    }


    public int getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
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

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public int getTecnico() {
        return tecnico;
    }

    public void setTecnico(int tecnico) {
        this.tecnico = tecnico;
    }

    public double getHorasTotales() {
        return horasTotales;
    }

    public void setHorasTotales(double horasTotales) {
        this.horasTotales = horasTotales;
    }
}
