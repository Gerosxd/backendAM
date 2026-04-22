package com.aerotaller.modules.auth.dto;

public class LoginResponse
{
    private String token;
    private Integer idUsuario;
    private String username;
    private String nombre;
    private String correo;
    private Integer rol;

    public LoginResponse() {}

    public LoginResponse(String token, Integer idUsuario, String username, String nombre, String correo, Integer rol)
    {
        this.token = token;
        this.idUsuario = idUsuario;
        this.username = username;
        this.nombre = nombre;
        this.correo = correo;
        this.rol = rol;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public Integer getIdUsuario()
    {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario)
    {
        this.idUsuario = idUsuario;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getCorreo()
    {
        return correo;
    }

    public void setCorreo(String correo)
    {
        this.correo = correo;
    }

    public Integer getRol()
    {
        return rol;
    }

    public void setRol(Integer rol)
    {
        this.rol = rol;
    }
}