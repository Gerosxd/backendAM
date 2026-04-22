package com.aerotaller.modules.entradaart.service;

import com.aerotaller.modules.entradaart.dto.EntradaArticuloExportRequestDto;

public interface EntradaArticuloExcelService
{
    byte[] generarExcelEntrada(Integer idEntrada, EntradaArticuloExportRequestDto exportDto);
}