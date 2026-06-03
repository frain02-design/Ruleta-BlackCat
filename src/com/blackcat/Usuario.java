package com.blackcat;

public class Usuario {

    private String nombreUsuario;
    private String contrasena;
    private String nombreCompleto;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public Usuario(String nombreUsuario, String contrasena, String nombreCompleto) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.nombreCompleto = nombreCompleto;
    }

    public boolean validarCredenciales(String usuario, String clave) {
        return this.nombreUsuario.equals(usuario) && this.contrasena.equals(clave);
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }
}