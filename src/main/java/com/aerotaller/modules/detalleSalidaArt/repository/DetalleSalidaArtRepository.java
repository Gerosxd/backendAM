package com.aerotaller.modules.detalleSalidaArt.repository;

import com.aerotaller.modelos.DetalleSalidaArt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleSalidaArtRepository extends JpaRepository<DetalleSalidaArt, Integer> {
    List<DetalleSalidaArt> findBySalidaArt_IdSalidaArt(Integer idSalidaArt);
}
