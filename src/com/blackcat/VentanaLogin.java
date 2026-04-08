package com.blackcat;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaLogin {

    public static final List<Usuario> USUARIOS = new ArrayList<>();

    private final JFrame ventana = new JFrame("Login - Casino Black Cat");
    private final JLabel etiquetaUsuario = new JLabel("Usuario:");
    private final JTextField campoUsuario = new JTextField();
    private final JLabel etiquetaClave = new JLabel("Clave:");
    private final JPasswordField campoClave = new JPasswordField();
    private final JButton botonIngresar = new JButton("Ingresar");
    private final JButton botonRegistrar = new JButton("Registrar");

    public VentanaLogin() {
        USUARIOS.add(new Usuario("admin", "1234", "Administrador"));
        USUARIOS.add(new Usuario("juan", "abc123", "Juan Pérez"));

        configurarVentana();
    }

    private void configurarVentana() {
        ventana.setSize(300, 250);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new GridLayout(4, 2, 10, 10));
        ventana.setLocationRelativeTo(null);

        ventana.add(etiquetaUsuario);
        ventana.add(campoUsuario);
        ventana.add(etiquetaClave);
        ventana.add(campoClave);
        ventana.add(new JLabel());
        ventana.add(botonIngresar);
        ventana.add(botonRegistrar);

        botonIngresar.addActionListener(e -> ingresar());
        botonRegistrar.addActionListener(e -> abrirRegistro());
    }

    public void mostrarVentana() {
        ventana.setVisible(true);
    }

    private void ingresar() {
        String usuario = campoUsuario.getText();
        String clave = new String(campoClave.getPassword());

        System.out.println("Usuario ingresado: " + usuario);
        String nombreUsuario = validarCredenciales(usuario, clave);

        System.out.println("Nombre usuario validado: " + nombreUsuario);

        if (!nombreUsuario.isEmpty()) {
            System.out.println("Login exitoso, abriendo ruleta...");
            JOptionPane.showMessageDialog(ventana,
                    "Bienvenido " + nombreUsuario,
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
            ventana.dispose();
            try {
                VentanaRuleta ruleta = new VentanaRuleta(nombreUsuario);
                ruleta.mostrarVentana();
                System.out.println("Ventana de ruleta debería estar abierta");
            } catch (Exception e) {
                System.out.println("ERROR al abrir ruleta: " + e.getMessage());
                e.printStackTrace();
            }
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

    private void abrirRegistro() {
        VentanaRegistro registro = new VentanaRegistro();
        registro.mostrarVentana();
    }

}