package com.blackcat.modelo;

import java.time.LocalDateTime;

public class Resultado {

    private final LocalDateTime fecha;
    private final int numero;
    private final char tipoApuesta;
    private final boolean acierto;
    private final int monto;
    private final Usuario usuario;

    public Resultado(Usuario usuario, int numero, char tipoApuesta, boolean acierto, int monto) {
        this.usuario = usuario;
        this.numero = numero;
        this.tipoApuesta = tipoApuesta;
        this.acierto = acierto;
        this.monto = monto;
        this.fecha = LocalDateTime.now();
    }

    // Getters
    public LocalDateTime getFecha() { return fecha; }
    public int getNumero() { return numero; }
    public char getTipoApuesta() { return tipoApuesta; }
    public boolean isAcierto() { return acierto; }
    public int getMonto() { return monto; }
    public Usuario getUsuario() { return usuario; }

    @Override
    public String toString() {
        return String.format("%s | Número: %d | Apuesta: %s | Monto: $%d | %s",
                fecha, numero, tipoApuesta, monto, acierto ? "GANÓ" : "PERDIÓ");
    }
}