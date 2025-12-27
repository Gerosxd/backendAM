package com.aerotaller.modules.wms.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "wms_entradas")
@Data
public class Entrada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fechaEntrada;
    private String numeroFactura;
    @ManyToOne
    private Proveedor proveedor;
    @OneToMany(mappedBy = "entrada", cascade = CascadeType.ALL)
    private List<EntradaDetalle> detalles;
}