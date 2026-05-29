package com.aerotaller.modules.tareaProgramada.service;

import com.aerotaller.modelos.ReporteProgramado;
import com.aerotaller.modules.tareaProgramada.dto.TareaProgramadaDTO;
import java.util.List;

public interface TareaProgramadaService {
    List<ReporteProgramado> listarTodas();
    ReporteProgramado guardar(TareaProgramadaDTO dto);
}