package com.blackcat;

import javax.swing.*;
import java.awt.*;

public class VentanaJuego {

    private final JFrame ventana = new JFrame("Ruleta - Casino Black Cat");
    private final RuletaLogica ruleta = new RuletaLogica();
    private final Usuario usuario;

    private final JLabel etiquetaNumero = new JLabel("Número: --");
    private final JLabel etiquetaColor = new JLabel("Color: --");
    private final JLabel etiquetaResultado = new JLabel("Resultado: --");

    private final JComboBox<String> comboTipo = new JComboBox<>(new String[]{"Rojo (R)", "Negro (N)", "Par (P)", "Impar (I)"});
    private final JTextField campoMonto = new JTextField();
    private final JButton botonGirar = new JButton("Girar ruleta");
    private final JButton botonVolver = new JButton("Volver al menú");

    public VentanaJuego(Usuario usuario) {
        this.usuario = usuario;
        configurarVentana();
    }

    private void configurarVentana() {
        ventana.setSize(450, 350);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new GridLayout(7, 2, 10, 10));
        ventana.setLocationRelativeTo(null);

        ventana.add(new JLabel("Bienvenido: " + usuario.getNombreCompleto()));
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

            String seleccion = (String) comboTipo.getSelectedItem();
            char tipo = obtenerTipo(seleccion);

            int numero = ruleta.girarRuleta();
            boolean acierto = ruleta.evaluarResultado(numero, tipo);
            ruleta.registrarResultado(numero, monto, acierto);

            etiquetaNumero.setText("Número: " + numero);
            etiquetaColor.setText("Color: " + ruleta.obtenerColor(numero));
            etiquetaResultado.setText(acierto ? "🎉 ¡GANASTE! +$" + monto : "💀 PERDISTE -$" + monto);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(ventana, "Ingrese un monto válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private char obtenerTipo(String seleccion) {
        if (seleccion.contains("Rojo")) return 'R';
        if (seleccion.contains("Negro")) return 'N';
        if (seleccion.contains("Par")) return 'P';
        return 'I';
    }

    private void volverAlMenu() {
        ventana.dispose();
        new VentanaMenu(usuario).mostrarVentana();
    }

    public void mostrarVentana() {
        ventana.setVisible(true);
    }
}