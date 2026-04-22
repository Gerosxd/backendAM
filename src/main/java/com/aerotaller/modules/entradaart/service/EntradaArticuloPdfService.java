package com.aerotaller.modules.entradaart.service;

import com.aerotaller.modules.entradaart.dto.EntradaArticuloExportRequestDto;

public interface EntradaArticuloPdfService
{
    byte[] generarPdfEntrada(Integer idEntrada, EntradaArticuloExportRequestDto exportDto);
}