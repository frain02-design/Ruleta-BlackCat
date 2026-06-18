package com.blackcat.controlador;

import com.blackcat.modelo.ApuestaBase;
import com.blackcat.modelo.Resultado;
import com.blackcat.modelo.Ruleta;
import com.blackcat.modelo.Usuario;
import com.blackcat.repositorio.IRepositorioResultados;

public class RuletaController {

    private final Ruleta ruleta;
    private final SessionController sessionController;
    private final IRepositorioResultados repositorio;

    public RuletaController(SessionController sessionController, IRepositorioResultados repositorio) {
        this.ruleta = new Ruleta();
        this.sessionController = sessionController;
        this.repositorio = repositorio;
    }

    public Resultado jugar(ApuestaBase apuesta) {
        Usuario usuario = sessionController.getUsuarioActual();
        if (usuario == null) {
            throw new IllegalStateException("No hay sesión activa");
        }
        Resultado resultado = ruleta.jugar(apuesta);
        repositorio.guardarResultado(resultado);
        return resultado;
    }

    public Ruleta getRuleta() {
        return ruleta;
    }

    public IRepositorioResultados getRepositorio() {
        return repositorio;
    }
}