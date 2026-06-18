package com.blackcat.vista;

import com.blackcat.controlador.RuletaController;
import com.blackcat.controlador.SessionController;
import com.blackcat.repositorio.IRepositorioResultados;

import javax.swing.*;
import java.awt.*;

public class VentanaMenu {

    private final JFrame ventana = new JFrame("Menú - Casino Black Cat");
    private final JButton botonJugar = new JButton("Jugar a la ruleta");
    private final JButton botonHistorial = new JButton("Ver historial");
    private final JButton botonEstadisticas = new JButton("Ver estadísticas");
    private final JButton botonCerrarSesion = new JButton("Cerrar sesión");
    private final SessionController sessionController;
    private final IRepositorioResultados repositorio;

    public VentanaMenu(SessionController sessionController, IRepositorioResultados repositorio) {
        this.sessionController = sessionController;
        this.repositorio = repositorio;
        configurarVentana();
    }

    private void configurarVentana() {
        ventana.setSize(300, 300);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new GridLayout(4, 1, 10, 10));
        ventana.setLocationRelativeTo(null);

        ventana.add(botonJugar);
        ventana.add(botonHistorial);
        ventana.add(botonEstadisticas);
        ventana.add(botonCerrarSesion);

        botonJugar.addActionListener(e -> abrirRuleta());
        botonHistorial.addActionListener(e -> abrirHistorial());
        botonEstadisticas.addActionListener(e -> abrirEstadisticas());
        botonCerrarSesion.addActionListener(e -> cerrarSesion());
    }

    private void abrirRuleta() {
        ventana.dispose();
        RuletaController ruletaController = new RuletaController(sessionController, repositorio);
        VentanaJuego juego = new VentanaJuego(ruletaController, sessionController, repositorio);
        juego.mostrarVentana();
    }

    private void abrirHistorial() {
        VentanaHistorial historial = new VentanaHistorial(sessionController);
        historial.mostrarVentana();
    }

    private void abrirEstadisticas() {
        VentanaEstadisticas estadisticas = new VentanaEstadisticas(sessionController);
        estadisticas.mostrarVentana();
    }

    private void cerrarSesion() {
        sessionController.cerrarSesion();
        ventana.dispose();
        new VentanaLogin(sessionController, repositorio).mostrarVentana();
    }

    public void mostrarVentana() {
        ventana.setVisible(true);
    }
}