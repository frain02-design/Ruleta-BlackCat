package com.blackcat.controlador;

import com.blackcat.modelo.Ruleta;
import com.blackcat.modelo.Resultado;
import com.blackcat.modelo.Usuario;

public class RuletaController {

    private final Ruleta ruleta;
    private final SessionController sessionController;

    public RuletaController(SessionController sessionController) {
        this.ruleta = new Ruleta();
        this.sessionController = sessionController;
    }

    public Resultado jugar(int monto, char tipoApuesta) {
        Usuario usuario = sessionController.getUsuarioActual();
        if (usuario == null) {
            throw new IllegalStateException("No hay sesión activa");
        }

        int numero = ruleta.girarRuleta();
        boolean acierto = ruleta.evaluarResultado(numero, tipoApuesta);
        ruleta.registrarResultado(numero, monto, acierto);

        Resultado resultado = new Resultado(usuario, numero, tipoApuesta, acierto, monto);
        usuario.agregarResultado(resultado);

        return resultado;
    }

    public Ruleta getRuleta() {
        return ruleta;
    }
}