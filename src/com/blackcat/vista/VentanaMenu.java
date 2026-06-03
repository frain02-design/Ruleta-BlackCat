package com.blackcat.vista;

import com.blackcat.controlador.RuletaController;
import com.blackcat.controlador.SessionController;

import javax.swing.*;
import java.awt.*;

public class VentanaMenu {

    private final JFrame ventana = new JFrame("Menú - Casino Black Cat");
    private final JButton botonJugar = new JButton("Jugar a la ruleta");
    private final JButton botonHistorial = new JButton("Ver historial");
    private final JButton botonCerrarSesion = new JButton("Cerrar sesión");
    private final SessionController sessionController;

    public VentanaMenu(SessionController sessionController) {
        this.sessionController = sessionController;
        configurarVentana();
    }

    private void configurarVentana() {
        ventana.setSize(300, 250);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new GridLayout(3, 1, 10, 10));
        ventana.setLocationRelativeTo(null);

        ventana.add(botonJugar);
        ventana.add(botonHistorial);
        ventana.add(botonCerrarSesion);

        botonJugar.addActionListener(e -> abrirRuleta());
        botonHistorial.addActionListener(e -> abrirHistorial());
        botonCerrarSesion.addActionListener(e -> cerrarSesion());
    }

    private void abrirRuleta() {
        ventana.dispose();
        RuletaController ruletaController = new RuletaController(sessionController);
        VentanaJuego juego = new VentanaJuego(ruletaController, sessionController);
        juego.mostrarVentana();
    }

    private void abrirHistorial() {
        VentanaHistorial historial = new VentanaHistorial(sessionController);
        historial.mostrarVentana();
    }

    private void cerrarSesion() {
        sessionController.cerrarSesion();
        ventana.dispose();
        new VentanaLogin(sessionController).mostrarVentana();
    }

    public void mostrarVentana() {
        ventana.setVisible(true);
    }
}