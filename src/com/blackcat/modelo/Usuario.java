package com.blackcat.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Usuario {

    private String nombreUsuario;
    private String contrasena;
    private String nombreCompleto;
    private List<Resultado> historial;

    public Usuario(String nombreUsuario, String contrasena, String nombreCompleto) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.nombreCompleto = nombreCompleto;
        this.historial = new ArrayList<>();
    }

    public boolean validarCredenciales(String usuario, String clave) {
        return this.nombreUsuario.equals(usuario) && this.contrasena.equals(clave);
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void agregarResultado(Resultado resultado) {
        historial.add(resultado);
    }

    public List<Resultado> getHistorial() {
        return Collections.unmodifiableList(historial);
    }

    public void setHistorial(List<Resultado> historial) {
        this.historial = new ArrayList<>(historial);
    }
}