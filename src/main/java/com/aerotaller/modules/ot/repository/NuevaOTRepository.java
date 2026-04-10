package com.aerotaller.modules.ot.repository;

import com.aerotaller.modelos.NuevaOT;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface NuevaOTRepository extends JpaRepository<NuevaOT, Integer>
{

    boolean existsByNoOT(String noOT);

    Optional<NuevaOT> findTopByOrderByIdOTDesc();

    List<NuevaOT> findAllByOrderByFechaCreacionDescIdOTDesc();
}