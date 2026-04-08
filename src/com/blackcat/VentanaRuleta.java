package com.blackcat;

import javax.swing.*;
import java.awt.*;

public class VentanaRuleta {

    private final JFrame ventana = new JFrame("Ruleta - Casino Black Cat");
    private final JLabel mensajeBienvenida = new JLabel();
    private final JButton botonCerrar = new JButton("Cerrar");

    public VentanaRuleta(String nombreUsuario) {
        configurarVentana(nombreUsuario);
    }

    private void configurarVentana(String nombreUsuario) {
        ventana.setSize(400, 300);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setLayout(new BorderLayout());
        ventana.setLocationRelativeTo(null);
        ventana.setAlwaysOnTop(true);

        mensajeBienvenida.setText("Bienvenido a la Ruleta, " + nombreUsuario);
        mensajeBienvenida.setHorizontalAlignment(SwingConstants.CENTER);

        ventana.add(mensajeBienvenida, BorderLayout.CENTER);
        ventana.add(botonCerrar, BorderLayout.SOUTH);

        botonCerrar.addActionListener(e -> ventana.dispose());
    }

    public void mostrarVentana() {
        ventana.setVisible(true);
        ventana.toFront();
        ventana.requestFocus();
    }
}