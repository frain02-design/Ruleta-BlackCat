package com.blackcat.modelo;

import java.time.LocalDateTime;

public class Resultado {

    private final LocalDateTime fecha;
    private final int numero;
    private final ApuestaBase apuesta;
    private final boolean acierto;
    private final Usuario usuario;

    public Resultado(Usuario usuario, int numero, ApuestaBase apuesta, boolean acierto) {
        this.usuario = usuario;
        this.numero = numero;
        this.apuesta = apuesta;
        this.acierto = acierto;
        this.fecha = LocalDateTime.now();
    }

    // Getters
    public LocalDateTime getFecha() { return fecha; }
    public int getNumero() { return numero; }
    public ApuestaBase getApuesta() { return apuesta; }
    public boolean isAcierto() { return acierto; }
    public int getMonto() { return apuesta.getMonto(); }
    public Usuario getUsuario() { return usuario; }

    @Override
    public String toString() {
        return String.format("%s | Número: %d | Apuesta: %s | Monto: $%d | %s",
                fecha, numero, apuesta.getNombre(), apuesta.getMonto(), acierto ? "GANÓ" : "PERDIÓ");
    }
}