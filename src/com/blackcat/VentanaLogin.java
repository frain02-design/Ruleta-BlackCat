package com.blackcat;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaLogin {

    // Lista de usuarios hardcodeados
    public static final List<Usuario> USUARIOS = new ArrayList<>();

    // Componentes de la ventana
    private final JFrame ventana = new JFrame("Login - Casino Black Cat");
    private final JLabel etiquetaUsuario = new JLabel("Usuario:");
    private final JTextField campoUsuario = new JTextField();
    private final JLabel etiquetaClave = new JLabel("Clave:");
    private final JPasswordField campoClave = new JPasswordField();
    private final JButton botonIngresar = new JButton("Ingresar");

    public VentanaLogin() {
        // Agregar usuarios hardcodeados
        USUARIOS.add(new Usuario("admin", "1234", "Administrador"));
        USUARIOS.add(new Usuario("juan", "abc123", "Juan Pérez"));

        configurarVentana();
    }

    private void configurarVentana() {
        ventana.setSize(300, 200);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new GridLayout(3, 2, 10, 10));
        ventana.setLocationRelativeTo(null);

        ventana.add(etiquetaUsuario);
        ventana.add(campoUsuario);
        ventana.add(etiquetaClave);
        ventana.add(campoClave);
        ventana.add(new JLabel());
        ventana.add(botonIngresar);

        botonIngresar.addActionListener(e -> ingresar());
    }

    public void mostrarVentana() {
        ventana.setVisible(true);
    }

    private void ingresar() {
        String usuario = campoUsuario.getText();
        String clave = new String(campoClave.getPassword());

        String nombreUsuario = validarCredenciales(usuario, clave);

        if (!nombreUsuario.isEmpty()) {
            JOptionPane.showMessageDialog(ventana,
                    "Bienvenido " + nombreUsuario,
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(ventana,
                    "Usuario o clave incorrectos",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private String validarCredenciales(String usuario, String clave) {
        for (Usuario u : USUARIOS) {
            if (u.validarCredenciales(usuario, clave)) {
                return u.getNombreCompleto();
            }
        }
        return "";
    }
}