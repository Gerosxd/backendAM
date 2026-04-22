package com.aerotaller.modules.salidaArt.service;

import com.aerotaller.modules.salidaArt.dto.CreateSalidaArtRequest;
import com.aerotaller.modules.salidaArt.dto.SalidaArtDetalleResponse;
import com.aerotaller.modules.salidaArt.dto.SalidaArtResponse;
import java.util.List;

public interface SalidaArtService {
    SalidaArtDetalleResponse crearSalida(CreateSalidaArtRequest request);
    List<SalidaArtResponse> listarTodas();
    SalidaArtResponse obtenerPorId(Integer id);
    void eliminar(Integer id);
    SalidaArtDetalleResponse obtenerDetalle(Integer idSalida);
}