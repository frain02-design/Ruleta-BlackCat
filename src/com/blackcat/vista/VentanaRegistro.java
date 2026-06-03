package com.blackcat.vista;

import com.blackcat.modelo.Usuario;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistro {

    private final JFrame ventana = new JFrame("Registro - Casino Black Cat");
    private final JLabel etiquetaUsuario = new JLabel("Usuario:");
    private final JTextField campoUsuario = new JTextField();
    private final JLabel etiquetaClave = new JLabel("Clave:");
    private final JPasswordField campoClave = new JPasswordField();
    private final JLabel etiquetaNombre = new JLabel("Nombre completo:");
    private final JTextField campoNombre = new JTextField();
    private final JButton botonRegistrar = new JButton("Registrar");
    private final JButton botonCancelar = new JButton("Cancelar");

    public VentanaRegistro() {
        configurarVentana();
    }

    private void configurarVentana() {
        ventana.setSize(350, 250);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setLayout(new GridLayout(4, 2, 10, 10));
        ventana.setLocationRelativeTo(null);

        ventana.add(etiquetaUsuario);
        ventana.add(campoUsuario);
        ventana.add(etiquetaClave);
        ventana.add(campoClave);
        ventana.add(etiquetaNombre);
        ventana.add(campoNombre);
        ventana.add(botonCancelar);
        ventana.add(botonRegistrar);

        botonRegistrar.addActionListener(e -> registrar());
        botonCancelar.addActionListener(e -> ventana.dispose());
    }

    private void registrar() {
        String usuario = campoUsuario.getText().trim();
        String clave = new String(campoClave.getPassword()).trim();
        String nombre = campoNombre.getText().trim();

        if (usuario.isEmpty() || clave.isEmpty() || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(ventana,
                    "Todos los campos son obligatorios",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificar si el usuario ya existe
        for (Usuario u : VentanaLogin.USUARIOS) {
            if (u.getNombreUsuario().equals(usuario)) {
                JOptionPane.showMessageDialog(ventana,
                        "El usuario ya existe",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        Usuario nuevoUsuario = new Usuario(usuario, clave, nombre);
        VentanaLogin.USUARIOS.add(nuevoUsuario);

        JOptionPane.showMessageDialog(ventana,
                "Usuario registrado exitosamente",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE);

        ventana.dispose();
    }

    public void mostrarVentana() {
        ventana.setVisible(true);
    }
}