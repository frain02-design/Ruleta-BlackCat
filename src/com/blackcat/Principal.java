package com.blackcat;

import com.blackcat.controlador.SessionController;
import com.blackcat.repositorio.IRepositorioResultados;
import com.blackcat.repositorio.RepositorioArchivo;
import com.blackcat.vista.VentanaLogin;

public class Principal {
    public static void main(String[] args) {
        IRepositorioResultados repositorio = new RepositorioArchivo();
        repositorio.cargarDatosIniciales();

        SessionController session = new SessionController();
        VentanaLogin login = new VentanaLogin(session, repositorio);
        login.mostrarVentana();
    }
}