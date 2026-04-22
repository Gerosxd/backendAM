package com.aerotaller.modules.auth.service;

import com.aerotaller.modelos.Usuario;
import com.aerotaller.modules.auth.dto.LoginRequest;
import com.aerotaller.modules.auth.dto.LoginResponse;
import com.aerotaller.modules.auth.repository.UsuarioRepository;
import com.aerotaller.security.JwtService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService
{
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;

    public AuthService(UsuarioRepository usuarioRepository, JwtService jwtService)
    {
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
    }

    public LoginResponse login(LoginRequest req)
    {
        Usuario u = usuarioRepository.findByUsuarioExacto(req.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // TEMPORAL: comparación directa porque tu BD tiene contraseñas en texto plano
        if (!u.getContrasenia().equals(req.getPassword()))
        {
            throw new RuntimeException("Contraseña incorrecta");
        }

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("idUsuario", u.getIdUsuario());
        extraClaims.put("nombre", u.getNombre());
        extraClaims.put("correo", u.getCorreo());
        extraClaims.put("rol", u.getRol());

        String token = jwtService.generateToken(u.getUsuario(), extraClaims);

        return new LoginResponse(
                token,
                u.getIdUsuario(),
                u.getUsuario(),
                u.getNombre(),
                u.getCorreo(),
                u.getRol()
        );
    }
}