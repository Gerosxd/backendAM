package com.aerotaller.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Cliente")
public class Cliente {

    // --- GETTERS Y SETTERS ---
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCliente")
    private Integer idCliente;

    @Column(name = "Compania", nullable = false, length = 100)
    private String compania;

    @Column(name = "RFC", nullable = false, length = 15)
    private String rfc;

    @Column(name = "Contacto", nullable = false, length = 150)
    private String contacto;

    @Column(name = "Telefono", nullable = false, length = 15)
    private String telefono;

    @Column(name = "Correo", nullable = false, length = 100)
    private String correo;

    @Column(name = "Direccion", nullable = false, length = 200)
    private String direccion;

    @Column(name = "estado", length = 20)
    private String estado = "Activo";

}