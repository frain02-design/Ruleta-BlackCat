package com.blackcat.vista;

import com.blackcat.controlador.SessionController;
import com.blackcat.modelo.Resultado;
import com.blackcat.modelo.Usuario;
import com.blackcat.repositorio.IRepositorioResultados;
import com.blackcat.repositorio.RepositorioArchivo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaLogin {

    public static final List<Usuario> USUARIOS = new ArrayList<>();
    private final SessionController sessionController;
    private final IRepositorioResultados repositorio;

    private final JFrame ventana = new JFrame("Login - Casino Black Cat");
    private final JLabel etiquetaUsuario = new JLabel("Usuario:");
    private final JTextField campoUsuario = new JTextField();
    private final JLabel etiquetaClave = new JLabel("Clave:");
    private final JPasswordField campoClave = new JPasswordField();
    private final JButton botonIngresar = new JButton("Ingresar");
    private final JButton botonRegistrar = new JButton("Registrar");

    public VentanaLogin(SessionController sessionController, IRepositorioResultados repositorio) {
        this.sessionController = sessionController;
        this.repositorio = repositorio;
        USUARIOS.add(new Usuario("admin", "1234", "Administrador"));
        USUARIOS.add(new Usuario("juan", "abc123", "Juan Pérez"));
        cargarHistorialesDeUsuarios();
        configurarVentana();
    }

    private void cargarHistorialesDeUsuarios() {
        for (Usuario u : USUARIOS) {
            List<Resultado> historial = repositorio.obtenerResultadosPorUsuario(u);
            u.setHistorial(historial);
        }
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
        ventana.add(new JLabel());
        ventana.add(botonRegistrar);

        botonIngresar.addActionListener(e -> ingresar());
        botonRegistrar.addActionListener(e -> abrirRegistro());
    }

    private void ingresar() {
        String nombreUsuario = campoUsuario.getText();
        String clave = new String(campoClave.getPassword());

        Usuario usuarioActual = buscarUsuario(nombreUsuario, clave);

        if (usuarioActual != null) {
            sessionController.iniciarSesion(usuarioActual);
            JOptionPane.showMessageDialog(ventana,
                    "Bienvenido " + usuarioActual.getNombreCompleto(),
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
            ventana.dispose();
            VentanaMenu menu = new VentanaMenu(sessionController, repositorio);
            menu.mostrarVentana();
        } else {
            JOptionPane.showMessageDialog(ventana,
                    "Usuario o clave incorrectos",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private Usuario buscarUsuario(String nombreUsuario, String clave) {
        for (Usuario u : USUARIOS) {
            if (u.validarCredenciales(nombreUsuario, clave)) {
                return u;
            }
        }
        return null;
    }

    private void abrirRegistro() {
        VentanaRegistro registro = new VentanaRegistro(repositorio);
        registro.mostrarVentana();
    }

    public void mostrarVentana() {
        ventana.setVisible(true);
    }
}