package com.aerotaller.modelos;

public class Aeronave {
    private int idAeronave;
    private String matricula;
    private String nsAeronave;
    private int modeloAeronave;
    private String operador;
    private String maMotorLH;
    private String moMotorLH;
    private String nsMotorLH;
    private String maMotorRH;
    private String moMotorRH;
    private String nsMotorRH;
    private String maMotorC;
    private String moMotorC;
    private String nsMotorC;
    private String maAPU;
    private String moAPU;
    private String nsAPU;

    public Aeronave() {
    }

    public Aeronave(int idAeronave, String matricula, String nsAeronave, int modeloAeronave, String operador, String maMotorLH, String moMotorLH, String nsMotorLH, String maMotorRH, String moMotorRH, String nsMotorRH, String maMotorC, String moMotorC, String nsMotorC, String maAPU, String moAPU, String nsAPU) {
        this.setIdAeronave(idAeronave);
        this.setMatricula(matricula);
        this.setNsAeronave(nsAeronave);
        this.setModeloAeronave(modeloAeronave);
        this.setOperador(operador);
        this.setMaMotorLH(maMotorLH);
        this.setMoMotorLH(moMotorLH);
        this.setNsMotorLH(nsMotorLH);
        this.setMaMotorRH(maMotorRH);
        this.setMoMotorRH(moMotorRH);
        this.setNsMotorRH(nsMotorRH);
        this.setMaMotorC(maMotorC);
        this.setMoMotorC(moMotorC);
        this.setNsMotorC(nsMotorC);
        this.setMaAPU(maAPU);
        this.setMoAPU(moAPU);
        this.setNsAPU(nsAPU);
    }


    public int getIdAeronave() {
        return idAeronave;
    }

    public void setIdAeronave(int idAeronave) {
        this.idAeronave = idAeronave;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNsAeronave() {
        return nsAeronave;
    }

    public void setNsAeronave(String nsAeronave) {
        this.nsAeronave = nsAeronave;
    }

    public int getModeloAeronave() {
        return modeloAeronave;
    }

    public void setModeloAeronave(int modeloAeronave) {
        this.modeloAeronave = modeloAeronave;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public String getMaMotorLH() {
        return maMotorLH;
    }

    public void setMaMotorLH(String maMotorLH) {
        this.maMotorLH = maMotorLH;
    }

    public String getMoMotorLH() {
        return moMotorLH;
    }

    public void setMoMotorLH(String moMotorLH) {
        this.moMotorLH = moMotorLH;
    }

    public String getNsMotorLH() {
        return nsMotorLH;
    }

    public void setNsMotorLH(String nsMotorLH) {
        this.nsMotorLH = nsMotorLH;
    }

    public String getMaMotorRH() {
        return maMotorRH;
    }

    public void setMaMotorRH(String maMotorRH) {
        this.maMotorRH = maMotorRH;
    }

    public String getMoMotorRH() {
        return moMotorRH;
    }

    public void setMoMotorRH(String moMotorRH) {
        this.moMotorRH = moMotorRH;
    }

    public String getNsMotorRH() {
        return nsMotorRH;
    }

    public void setNsMotorRH(String nsMotorRH) {
        this.nsMotorRH = nsMotorRH;
    }

    public String getMaMotorC() {
        return maMotorC;
    }

    public void setMaMotorC(String maMotorC) {
        this.maMotorC = maMotorC;
    }

    public String getMoMotorC() {
        return moMotorC;
    }

    public void setMoMotorC(String moMotorC) {
        this.moMotorC = moMotorC;
    }

    public String getNsMotorC() {
        return nsMotorC;
    }

    public void setNsMotorC(String nsMotorC) {
        this.nsMotorC = nsMotorC;
    }

    public String getMaAPU() {
        return maAPU;
    }

    public void setMaAPU(String maAPU) {
        this.maAPU = maAPU;
    }

    public String getMoAPU() {
        return moAPU;
    }

    public void setMoAPU(String moAPU) {
        this.moAPU = moAPU;
    }

    public String getNsAPU() {
        return nsAPU;
    }

    public void setNsAPU(String nsAPU) {
        this.nsAPU = nsAPU;
    }
}
