package com.blackcat.modelo;

public class ApuestaRojo extends ApuestaBase {

    public ApuestaRojo(int monto) {
        super(monto);
    }

    @Override
    public boolean acierta(int numero, String color) {
        return numero != 0 && color.equals("Rojo");
    }

    @Override
    public String getNombre() {
        return "Rojo";
    }
}