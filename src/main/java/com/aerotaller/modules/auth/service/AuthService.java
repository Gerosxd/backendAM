package com.aerotaller.modules.auth.service;

import com.aerotaller.modelos.Usuario;
import com.aerotaller.modules.auth.dto.LoginRequest;
import com.aerotaller.modules.auth.dto.LoginResponse;
import com.aerotaller.modules.auth.repository.UsuarioRepository;
import com.aerotaller.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService
{

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UsuarioRepository usuarioRepository,
                       PasswordEncoder passwordEncoder)
    {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse login(LoginRequest req)
    {

        Usuario u = usuarioRepository.findByUsuario(req.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

//        if (!passwordEncoder.matches(req.getPassword(), u.getContrasenia()))
//        {
//            throw new RuntimeException("Contraseña incorrecta");
//        }
        if (!u.getContrasenia().equals(req.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = "demo-token-" + u.getIdUsuario();
        return new LoginResponse(token, u.getUsuario());
    }
}