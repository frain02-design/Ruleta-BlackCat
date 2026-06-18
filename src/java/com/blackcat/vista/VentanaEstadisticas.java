package com.blackcat.vista;

import com.blackcat.controlador.SessionController;
import com.blackcat.modelo.Estadisticas;

import javax.swing.*;
import java.awt.*;

public class VentanaEstadisticas {

    private final JFrame ventana = new JFrame("Estadísticas - Casino Black Cat");
    private final SessionController sessionController;
    private final JTextArea areaEstadisticas = new JTextArea();
    private final JButton botonCerrar = new JButton("Cerrar");

    public VentanaEstadisticas(SessionController sessionController) {
        this.sessionController = sessionController;
        configurarVentana();
    }

    private void configurarVentana() {
        ventana.setSize(500, 400);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setLayout(new BorderLayout());
        ventana.setLocationRelativeTo(null);

        areaEstadisticas.setEditable(false);
        areaEstadisticas.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scroll = new JScrollPane(areaEstadisticas);
        ventana.add(scroll, BorderLayout.CENTER);
        ventana.add(botonCerrar, BorderLayout.SOUTH);

        cargarEstadisticas();

        botonCerrar.addActionListener(e -> ventana.dispose());
    }

    private void cargarEstadisticas() {
        Estadisticas estadisticas = new Estadisticas(sessionController.getUsuarioActual().getHistorial());
        areaEstadisticas.setText(estadisticas.getResumen());
    }

    public void mostrarVentana() {
        ventana.setVisible(true);
    }
}