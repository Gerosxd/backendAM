package com.aerotaller.modules.aeronave.service;

import com.aerotaller.modules.aeronave.dto.CreateAeronaveRequest;
import com.aerotaller.modules.aeronave.dto.AeronaveResponse;
import com.aerotaller.modelos.Aeronave;
import com.aerotaller.modules.aeronave.repository.AeronaveRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AeronaveService {

    private final AeronaveRepository aeronaveRepository;

    public AeronaveService(AeronaveRepository aeronaveRepository) {
        this.aeronaveRepository = aeronaveRepository;
    }

    public AeronaveResponse guardarAeronave(CreateAeronaveRequest dto) {

        Aeronave aeronave = new Aeronave();

        aeronave.setMatricula(dto.getMatricula());
        aeronave.setNsAeronave(dto.getNsAeronave());
        aeronave.setModeloAeronave(dto.getModeloAeronave());
        aeronave.setOperador(dto.getOperador());

        aeronave.setMaMotorLH(dto.getMaMotorLH());
        aeronave.setMoMotorLH(dto.getMoMotorLH());
        aeronave.setNsMotorLH(dto.getNsMotorLH());

        aeronave.setMaMotorRH(dto.getMaMotorRH());
        aeronave.setMoMotorRH(dto.getMoMotorRH());
        aeronave.setNsMotorRH(dto.getNsMotorRH());

        aeronave.setMaMotorC(dto.getMaMotorC());
        aeronave.setMoMotorC(dto.getMoMotorC());
        aeronave.setNsMotorC(dto.getNsMotorC());

        aeronave.setMaAPU(dto.getMaAPU());
        aeronave.setMoAPU(dto.getMoAPU());
        aeronave.setNsAPU(dto.getNsAPU());

        return AeronaveResponse.fromEntity(aeronaveRepository.save(aeronave));
    }

    public List<AeronaveResponse> listarAeronaves() {
        return aeronaveRepository.findAll()
                .stream()
                .map(AeronaveResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
