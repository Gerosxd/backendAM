package com.aerotaller.modelos;

public class ModeloAeronave {

    private int idModelo;
    private String modelo;
    private String marca;
    private int tipoAeronave;

    public ModeloAeronave() {
    }

    public ModeloAeronave(int idModelo, String modelo, String marca, int tipoAeronave) {
        this.setIdModelo(idModelo);
        this.setModelo(modelo);
        this.setMarca(marca);
        this.setTipoAeronave(tipoAeronave);
    }


    public int getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(int idModelo) {
        this.idModelo = idModelo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getTipoAeronave() {
        return tipoAeronave;
    }

    public void setTipoAeronave(int tipoAeronave) {
        this.tipoAeronave = tipoAeronave;
    }
}
