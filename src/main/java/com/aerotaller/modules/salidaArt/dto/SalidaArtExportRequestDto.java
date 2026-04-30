package com.aerotaller.modules.salidaArt.dto;

import lombok.Data;

@Data
public class SalidaArtExportRequestDto {
    private String encargadoAlmacen;
    private String fechaEncargado;
    private String traslada;
    private String fechaTraslada;
    private String recibe;
    private String fechaRecibe;
}