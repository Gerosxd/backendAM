package com.aerotaller.modelos;

public class Discrepancia {

    private int idDiscrepancia;
    private String codigo;
    private int tecnico;
    private String descripcion;
    private String accion;
    private double horasTotales;

    public Discrepancia() {
    }

    public Discrepancia(int idDiscrepancia, String codigo, int tecnico, String descripcion, String accion, double horasTotales) {
        this.setIdDiscrepancia(idDiscrepancia);
        this.setCodigo(codigo);
        this.setTecnico(tecnico);
        this.setDescripcion(descripcion);
        this.setAccion(accion);
        this.setHorasTotales(horasTotales);
    }


    public int getIdDiscrepancia() {
        return idDiscrepancia;
    }

    public void setIdDiscrepancia(int idDiscrepancia) {
        this.idDiscrepancia = idDiscrepancia;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getTecnico() {
        return tecnico;
    }

    public void setTecnico(int tecnico) {
        this.tecnico = tecnico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public double getHorasTotales() {
        return horasTotales;
    }

    public void setHorasTotales(double horasTotales) {
        this.horasTotales = horasTotales;
    }
}
