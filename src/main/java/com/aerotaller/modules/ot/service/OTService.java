package com.aerotaller.modules.ot.service;

import com.aerotaller.modules.ot.dto.AeronaveComboResponse;
import com.aerotaller.modules.ot.dto.CrearOTRequest;
import com.aerotaller.modules.ot.dto.CrearOTResponse;
import com.aerotaller.modules.ot.dto.SiguienteNoOTResponse;
import com.aerotaller.modules.ot.dto.OTListadoResponse;

import java.util.List;

public interface OTService
{

    List<AeronaveComboResponse> obtenerMatriculas();

    SiguienteNoOTResponse obtenerSiguienteNoOT();

    CrearOTResponse crearOT(CrearOTRequest request);

    List<OTListadoResponse> listarOTs();
}