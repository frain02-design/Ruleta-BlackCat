package com.blackcat.modelo;

public class ApuestaNegro extends ApuestaBase {

    public ApuestaNegro(int monto) {
        super(monto);
    }

    @Override
    public boolean acierta(int numero, String color) {
        return numero != 0 && color.equals("Negro");
    }

    @Override
    public String getNombre() {
        return "Negro";
    }
}