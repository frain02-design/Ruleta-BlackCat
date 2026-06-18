package com.blackcat.vista;

import com.blackcat.controlador.SessionController;
import com.blackcat.modelo.Resultado;

import javax.swing.*;
import java.awt.*;

public class VentanaHistorial {

    private final JFrame ventana = new JFrame("Historial - Casino Black Cat");
    private final SessionController sessionController;
    private final JTextArea areaHistorial = new JTextArea();
    private final JButton botonCerrar = new JButton("Cerrar");

    public VentanaHistorial(SessionController sessionController) {
        this.sessionController = sessionController;
        configurarVentana();
    }

    private void configurarVentana() {
        ventana.setSize(600, 450);
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
        String nombreUsuario = sessionController.getUsuarioActual().getNombreCompleto();
        areaHistorial.setText("📊 Historial de jugadas de " + nombreUsuario + "\n");
        areaHistorial.append("============================================================\n\n");

        int totalApostado = 0;
        int totalGanado = 0;
        int rondasGanadas = 0;

        for (Resultado r : sessionController.getUsuarioActual().getHistorial()) {
            areaHistorial.append(r.toString() + "\n");
            totalApostado += r.getMonto();
            if (r.isAcierto()) {
                totalGanado += r.getMonto();
                rondasGanadas++;
            }
        }

        int totalRondas = sessionController.getUsuarioActual().getHistorial().size();
        if (totalRondas > 0) {
            areaHistorial.append("\n============================================================\n");
            areaHistorial.append("📈 RESUMEN FINAL:\n");
            areaHistorial.append("   Rondas jugadas: " + totalRondas + "\n");
            areaHistorial.append("   Rondas ganadas: " + rondasGanadas + "\n");
            areaHistorial.append("   Total apostado: $" + totalApostado + "\n");
            areaHistorial.append("   Total ganado: $" + totalGanado + "\n");
            double porcentaje = (double) rondasGanadas / totalRondas * 100;
            areaHistorial.append(String.format("   Porcentaje de acierto: %.2f%%\n", porcentaje));
        } else {
            areaHistorial.append("\n   No hay jugadas registradas aún.\n");
        }
    }

    public void mostrarVentana() {
        ventana.setVisible(true);
    }
}