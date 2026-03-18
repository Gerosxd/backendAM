package com.aerotaller.modules.aeronave.repository;
import com.aerotaller.modelos.Aeronave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AeronaveRepository extends JpaRepository<Aeronave, Long> {
}