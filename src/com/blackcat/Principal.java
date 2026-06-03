package com.blackcat;

import com.blackcat.controlador.SessionController;
import com.blackcat.vista.VentanaLogin;

public class Principal {
    public static void main(String[] args) {
        SessionController session = new SessionController();
        VentanaLogin login = new VentanaLogin(session);
        login.mostrarVentana();
    }
}