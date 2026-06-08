package com.aerotaller.modules.tareaProgramada.service;

import com.aerotaller.modelos.ReporteProgramado;
import com.aerotaller.modules.tareaProgramada.dto.TareaProgramadaDTO;
import com.aerotaller.modules.tareaProgramada.repository.TareaProgramadaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Importar de Spring
import java.util.List;
import java.util.Optional;

@Service
public class TareaProgramadaServiceImpl implements TareaProgramadaService {

    private final TareaProgramadaRepository repository;

    public TareaProgramadaServiceImpl(TareaProgramadaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ReporteProgramado> listarTodas() {
        return repository.findAll();
    }

    @Override
    @Transactional // SOLUCIÓN: Asegura la integridad transaccional en producción
    public ReporteProgramado guardar(TareaProgramadaDTO dto) {
        Optional<ReporteProgramado> existente = repository.findByCodigo(dto.getCodigo());
        ReporteProgramado tarea;

        if (existente.isPresent()) {
            tarea = existente.get();
        } else {
            tarea = new ReporteProgramado();
            tarea.setCodigo(dto.getCodigo());
        }

        // Mapeo explícito
        tarea.setDescripcion(dto.getDescripcion());
        tarea.setModelo(dto.getModeloId()); // Asegurado gracias a la corrección del DTO
        tarea.setTecnico(dto.getTecnico());
        tarea.setHorasTotales(dto.getHorasTotales());

        return repository.save(tarea);
    }
}