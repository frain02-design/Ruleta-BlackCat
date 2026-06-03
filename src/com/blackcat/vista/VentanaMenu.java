package com.blackcat.vista;

import com.blackcat.controlador.RuletaController;
import com.blackcat.controlador.SessionController;

import javax.swing.*;
import java.awt.*;

public class VentanaMenu {

    private final JFrame ventana = new JFrame("Menú - Casino Black Cat");
    private final JButton botonJugar = new JButton("Jugar a la ruleta");
    private final JButton botonHistorial = new JButton("Ver historial");
    private final JButton botonEstadisticas = new JButton("Ver estadísticas");  // NUEVO
    private final JButton botonCerrarSesion = new JButton("Cerrar sesión");
    private final SessionController sessionController;

    public VentanaMenu(SessionController sessionController) {
        this.sessionController = sessionController;
        configurarVentana();
    }

    private void configurarVentana() {
        ventana.setSize(300, 300);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new GridLayout(4, 1, 10, 10));  // Ahora 4 filas
        ventana.setLocationRelativeTo(null);

        ventana.add(botonJugar);
        ventana.add(botonHistorial);
        ventana.add(botonEstadisticas);  // NUEVO
        ventana.add(botonCerrarSesion);

        botonJugar.addActionListener(e -> abrirRuleta());
        botonHistorial.addActionListener(e -> abrirHistorial());
        botonEstadisticas.addActionListener(e -> abrirEstadisticas());  // NUEVO
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

    private void abrirEstadisticas() {  // NUEVO
        VentanaEstadisticas estadisticas = new VentanaEstadisticas(sessionController);
        estadisticas.mostrarVentana();
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