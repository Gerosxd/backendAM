package com.aerotaller.modules.ot.service;

import com.aerotaller.modules.ot.dto.*;

import java.util.List;

public interface OTService
{

    List<AeronaveComboResponse> obtenerMatriculas();

    SiguienteNoOTResponse obtenerSiguienteNoOT();

    CrearOTResponse crearOT(CrearOTRequest request);

    List<OTListadoResponse> listarOTs();

    OTDetalleResponse obtenerPorId(Integer idOT);
}