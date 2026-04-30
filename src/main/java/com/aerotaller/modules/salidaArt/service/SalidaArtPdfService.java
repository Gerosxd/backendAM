package com.aerotaller.modules.salidaArt.service;

import com.aerotaller.modules.salidaArt.dto.SalidaArtExportRequestDto;

public interface SalidaArtPdfService {
    byte[] generarPdfSalida(Integer idSalida, SalidaArtExportRequestDto exportDto);
}
