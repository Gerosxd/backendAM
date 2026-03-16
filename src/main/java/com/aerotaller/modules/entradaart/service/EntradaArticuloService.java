package com.aerotaller.modules.entradaart.service;

import com.aerotaller.modules.entradaart.dto.EntradaArticuloListadoResponseDto;
import com.aerotaller.modules.entradaart.dto.EntradaArticuloRegistroRequestDto;
import com.aerotaller.modules.entradaart.dto.EntradaArticuloRegistroResponseDto;

import java.util.List;

public interface EntradaArticuloService {
    EntradaArticuloRegistroResponseDto registrarEntradaCompleta(EntradaArticuloRegistroRequestDto dto);
    List<EntradaArticuloListadoResponseDto> listarEntradas();
    EntradaArticuloRegistroResponseDto obtenerEntradaPorId(Integer idEntrada);
}