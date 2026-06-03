package com.blackcat;

import javax.swing.*;
import java.awt.*;

public class VentanaMenu {

    private final JFrame ventana = new JFrame("Menú - Casino Black Cat");
    private final JButton botonJugar = new JButton("Jugar a la ruleta");
    private final JButton botonHistorial = new JButton("Ver historial");
    private final JButton botonCerrarSesion = new JButton("Cerrar sesión");
    private final Usuario usuario;

    public VentanaMenu(Usuario usuario) {
        this.usuario = usuario;
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
        VentanaJuego juego = new VentanaJuego(usuario);
        juego.mostrarVentana();
    }

    private void abrirHistorial() {
        VentanaHistorial historial = new VentanaHistorial(usuario);
        historial.mostrarVentana();
    }

    private void cerrarSesion() {
        ventana.dispose();
        new VentanaLogin().mostrarVentana();
    }

    public void mostrarVentana() {
        ventana.setVisible(true);
    }
}