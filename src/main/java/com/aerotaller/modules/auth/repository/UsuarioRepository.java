package com.aerotaller.modules.auth.repository;

import com.aerotaller.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>
{
    @Query(value = "SELECT * FROM Usuario u WHERE BINARY u.Usuario = :usuario LIMIT 1", nativeQuery = true)
    Optional<Usuario> findByUsuarioExacto(@Param("usuario") String usuario);
}