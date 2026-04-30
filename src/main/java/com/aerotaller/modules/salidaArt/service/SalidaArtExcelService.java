package com.aerotaller.modules.salidaArt.service;

import com.aerotaller.modules.salidaArt.dto.SalidaArtExportRequestDto;

public interface SalidaArtExcelService {
    byte[] generarExcelSalida(Integer idSalida, SalidaArtExportRequestDto exportDto);
}
