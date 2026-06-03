package com.blackcat.modelo;

public abstract class ApuestaBase {

    protected int monto;

    public ApuestaBase(int monto) {
        this.monto = monto;
    }

    public int getMonto() {
        return monto;
    }

    public abstract boolean acierta(int numero, String color);

    public abstract String getNombre();

    @Override
    public String toString() {
        return getNombre() + " - $" + monto;
    }
}
