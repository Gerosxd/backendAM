package com.aerotaller.modules.tareaProgramada.service;

import com.aerotaller.modelos.ReporteProgramado;
import com.aerotaller.modules.tareaProgramada.dto.TareaProgramadaDTO;
import com.aerotaller.modules.tareaProgramada.repository.TareaProgramadaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TareaProgramadaServiceImpl implements TareaProgramadaService {

    private final TareaProgramadaRepository repository;

    // Inyección explícita por constructor, ideal para arquitecturas limpias
    public TareaProgramadaServiceImpl(TareaProgramadaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ReporteProgramado> listarTodas() {
        return repository.findAll();
    }

    @Override
    public ReporteProgramado guardar(TareaProgramadaDTO dto) {
        // 1. Buscamos si ya existe el código en la base de datos
        Optional<ReporteProgramado> existente = repository.findByCodigo(dto.getCodigo());

        ReporteProgramado tarea;

        if (existente.isPresent()) {
            // Si ya existe, extraemos el objeto persistido (conserva su idReporte de la DB)
            tarea = existente.get();
        } else {
            // Si es un registro nuevo, creamos la instancia y asignamos la clave primaria única
            tarea = new ReporteProgramado();
            tarea.setCodigo(dto.getCodigo());
        }

        // 2. Mapeamos los datos modificados o nuevos que provienen de la vista de Vue
        tarea.setDescripcion(dto.getDescripcion());
        tarea.setModelo(dto.getModeloId());
        tarea.setTecnico(dto.getTecnico());
        tarea.setHorasTotales(dto.getHorasTotales());


        return repository.save(tarea);
    }
}