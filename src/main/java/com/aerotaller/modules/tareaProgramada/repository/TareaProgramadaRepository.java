package com.aerotaller.modules.tareaProgramada.repository;

import com.aerotaller.modelos.ReporteProgramado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TareaProgramadaRepository extends JpaRepository<ReporteProgramado, Integer> {


    Optional<ReporteProgramado> findByCodigo(String codigo);
}