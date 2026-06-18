package com.blackcat.controlador;

import com.blackcat.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;

public class SessionController {

    private Usuario usuarioActual;
    private List<Usuario> usuariosRegistrados;

    public SessionController() {
        this.usuariosRegistrados = new ArrayList<>();
    }

    public void iniciarSesion(Usuario usuario) {
        this.usuarioActual = usuario;
    }

    public boolean iniciarSesion(String username, String password) {
        for (Usuario u : usuariosRegistrados) {
            if (u.validarCredenciales(username, password)) {
                this.usuarioActual = u;
                return true;
            }
        }
        return false;
    }

    public void cerrarSesion() {
        this.usuarioActual = null;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public boolean haySesionActiva() {
        return usuarioActual != null;
    }

    public void registrarUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario no puede ser nulo");
        }
        if (usuario.getNombreUsuario() == null) {
            throw new IllegalArgumentException("Username no puede ser nulo");
        }
        usuariosRegistrados.add(usuario);
    }

    public List<Usuario> getUsuariosRegistrados() {
        return usuariosRegistrados;
    }
}