package com.blackcat.modelo;

public class ApuestaPar extends ApuestaBase {

    public ApuestaPar(int monto) {
        super(monto);
    }

    @Override
    public boolean acierta(int numero, String color) {
        return numero != 0 && numero % 2 == 0;
    }

    @Override
    public String getNombre() {
        return "Par";
    }
}