package com.blackcat;

import javax.swing.*;
import java.awt.*;

public class VentanaHistorial {

    private final JFrame ventana = new JFrame("Historial - Casino Black Cat");
    private final Usuario usuario;
    private final JTextArea areaHistorial = new JTextArea();
    private final JButton botonCerrar = new JButton("Cerrar");

    public VentanaHistorial(Usuario usuario) {
        this.usuario = usuario;
        configurarVentana();
    }

    private void configurarVentana() {
        ventana.setSize(500, 400);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setLayout(new BorderLayout());
        ventana.setLocationRelativeTo(null);

        areaHistorial.setEditable(false);
        areaHistorial.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scroll = new JScrollPane(areaHistorial);
        ventana.add(scroll, BorderLayout.CENTER);
        ventana.add(botonCerrar, BorderLayout.SOUTH);

        cargarHistorial();

        botonCerrar.addActionListener(e -> ventana.dispose());
    }

    private void cargarHistorial() {
        areaHistorial.setText("Historial de jugadas de " + usuario.getNombreCompleto() + "\n");
        areaHistorial.append("============================================\n");
        areaHistorial.append("\n(Próximamente: historial completo de jugadas)\n");
    }

    public void mostrarVentana() {
        ventana.setVisible(true);
    }
}