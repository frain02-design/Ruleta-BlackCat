package com.blackcat.vista;

import com.blackcat.controlador.RuletaController;
import com.blackcat.controlador.SessionController;
import com.blackcat.modelo.Resultado;
import com.blackcat.modelo.TipoApuesta;

import javax.swing.*;
import java.awt.*;

public class VentanaJuego {

    private final JFrame ventana = new JFrame("Ruleta - Casino Black Cat");
    private final RuletaController ruletaController;
    private final SessionController sessionController;

    private final JLabel etiquetaNumero = new JLabel("Número: --");
    private final JLabel etiquetaColor = new JLabel("Color: --");
    private final JLabel etiquetaResultado = new JLabel("Resultado: --");

    private final JComboBox<TipoApuesta> comboTipo = new JComboBox<>(TipoApuesta.values());
    private final JTextField campoMonto = new JTextField();
    private final JButton botonGirar = new JButton("Girar ruleta");
    private final JButton botonVolver = new JButton("Volver al menú");

    public VentanaJuego(RuletaController ruletaController, SessionController sessionController) {
        this.ruletaController = ruletaController;
        this.sessionController = sessionController;
        configurarVentana();
    }

    private void configurarVentana() {
        ventana.setSize(450, 350);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new GridLayout(7, 2, 10, 10));
        ventana.setLocationRelativeTo(null);

        String nombreUsuario = sessionController.getUsuarioActual().getNombreCompleto();
        ventana.add(new JLabel("Bienvenido: " + nombreUsuario));
        ventana.add(new JLabel());

        ventana.add(new JLabel("Tipo de apuesta:"));
        ventana.add(comboTipo);
        ventana.add(new JLabel("Monto: $"));
        ventana.add(campoMonto);
        ventana.add(new JLabel());
        ventana.add(botonGirar);

        ventana.add(etiquetaNumero);
        ventana.add(etiquetaColor);
        ventana.add(etiquetaResultado);
        ventana.add(new JLabel());

        ventana.add(botonVolver);
        ventana.add(new JLabel());

        botonGirar.addActionListener(e -> jugar());
        botonVolver.addActionListener(e -> volverAlMenu());
    }

    private void jugar() {
        try {
            int monto = Integer.parseInt(campoMonto.getText().trim());
            if (monto <= 0) {
                JOptionPane.showMessageDialog(ventana, "Monto inválido", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            TipoApuesta tipo = (TipoApuesta) comboTipo.getSelectedItem();
            Resultado resultado = ruletaController.jugar(monto, tipo);

            etiquetaNumero.setText("Número: " + resultado.getNumero());
            etiquetaColor.setText("Color: " + (resultado.getNumero() == 0 ? "Verde" :
                    (ruletaController.getRuleta().esRojo(resultado.getNumero()) ? "Rojo" : "Negro")));
            etiquetaResultado.setText(resultado.isAcierto() ? "🎉 ¡GANASTE! +$" + monto : "💀 PERDISTE -$" + monto);

            campoMonto.setText("");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(ventana, "Ingrese un monto válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void volverAlMenu() {
        ventana.dispose();
        new VentanaMenu(sessionController).mostrarVentana();
    }

    public void mostrarVentana() {
        ventana.setVisible(true);
    }
}