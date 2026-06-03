package com.blackcat.controlador;

import com.blackcat.modelo.ApuestaBase;
import com.blackcat.modelo.Resultado;
import com.blackcat.modelo.Ruleta;
import com.blackcat.modelo.Usuario;

public class RuletaController {

    private final Ruleta ruleta;
    private final SessionController sessionController;

    public RuletaController(SessionController sessionController) {
        this.ruleta = new Ruleta();
        this.sessionController = sessionController;
    }

    public Resultado jugar(ApuestaBase apuesta) {
        Usuario usuario = sessionController.getUsuarioActual();
        if (usuario == null) {
            throw new IllegalStateException("No hay sesión activa");
        }
        return ruleta.jugar(usuario, apuesta);
    }

    public Ruleta getRuleta() {
        return ruleta;
    }
}