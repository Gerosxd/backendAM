package com.aerotaller.modules.ot.service;

import com.aerotaller.modules.ot.dto.*;
import jakarta.transaction.Transactional;

import java.util.List;

public interface OTService
{

    List<AeronaveComboResponse> obtenerMatriculas();

    SiguienteNoOTResponse obtenerSiguienteNoOT();

    CrearOTResponse crearOT(CrearOTRequest request);

    List<OTListadoResponse> listarOTs();

    OTDetalleResponse obtenerPorId(Integer idOT);

    @Transactional
        // Asegura que si algo truena en MySQL, se haga Rollback automático
    void actualizarOT(Integer id, CrearOTRequest request);
}