package com.blackcat.controlador;

import com.blackcat.modelo.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SessionControllerTest {

    private SessionController sessionController;
    private Usuario usuarioRegistrado;

    @BeforeEach
    void setUp() {
        sessionController = new SessionController();
        usuarioRegistrado = new Usuario("admin", "1234", "Administrador");
        sessionController.registrarUsuario(usuarioRegistrado);
    }

    @Test
    @DisplayName("Caso 6: Inicio de sesión con usuario no registrado es rechazado")
    void loginConUsuarioNoRegistradoEsRechazado() {
        boolean resultado = sessionController.iniciarSesion("invalido", "123");
        assertFalse(resultado);
        assertNull(sessionController.getUsuarioActual());
    }

    @Test
    @DisplayName("Caso 7: Registro de usuario con username nulo es rechazado")
    void registroConUsernameNuloEsRechazado() {
        Usuario usuarioNulo = new Usuario(null, "123", "Usuario Nulo");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            sessionController.registrarUsuario(usuarioNulo);
        });
        assertEquals("Username no puede ser nulo", exception.getMessage());
    }

    @Test
    @DisplayName("Inicio de sesión con usuario registrado es exitoso")
    void loginConUsuarioRegistradoEsExitoso() {
        boolean resultado = sessionController.iniciarSesion("admin", "1234");
        assertTrue(resultado);
        assertNotNull(sessionController.getUsuarioActual());
        assertEquals("Administrador", sessionController.getUsuarioActual().getNombreCompleto());
    }
}