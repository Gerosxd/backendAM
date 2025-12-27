package com.aerotaller.modules.wms.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "wms_unidades_medida")
@Data
public class UnidadMedida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre; // Ej: PZA, LTR, FT
}
