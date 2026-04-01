package com.aerotaller.modelos;

import jakarta.persistence.*; // Si usas Spring Boot 2.x, cambia 'jakarta' por 'javax'

@Entity
@Table(name = "ModeloAeronave")
public class ModeloAeronave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idModelo")
    private int idModelo;

    @Column(name = "Modelo", length = 25, nullable = false) // Obligatorio según el ticket
    private String modelo;

    @Column(name = "Marca", length = 25, nullable = false) // Obligatorio según el ticket
    private String marca;

    @Column(name = "TipoAeronave")
    private Integer tipoAeronave; // Usamos Integer por si el DDL permite nulos temporalmente

    // Constructores vacíos y llenos
    public ModeloAeronave() {}

    public ModeloAeronave(int idModelo, String modelo, String marca, Integer tipoAeronave) {
        this.idModelo = idModelo;
        this.modelo = modelo;
        this.marca = marca;
        this.tipoAeronave = tipoAeronave;
    }

    // --- GETTERS Y SETTERS ---
    public int getIdModelo() { return idModelo; }
    public void setIdModelo(int idModelo) { this.idModelo = idModelo; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public Integer getTipoAeronave() { return tipoAeronave; }
    public void setTipoAeronave(Integer tipoAeronave) { this.tipoAeronave = tipoAeronave; }
}