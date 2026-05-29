package com.aerotaller.modules.tareaProgramada.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TareaProgramadaDTO {
    private String codigo;
    private String descripcion;
    private int modeloId;
    private String tecnico;
    private double horasTotales;
}