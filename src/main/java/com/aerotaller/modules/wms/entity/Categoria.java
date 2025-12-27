package com.aerotaller.modules.wms.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "categorias")
@Data // Si usas Lombok, si no, genera Getters y Setters manualmente
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 50)
    private String nombre;

    // Constructor vacío requerido por JPA
    public Categoria() {}

    public Categoria(String nombre) {
        this.nombre = nombre;
    }
}