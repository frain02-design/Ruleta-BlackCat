package com.blackcat.modelo;

public enum TipoApuesta {
    ROJO('R'),
    NEGRO('N'),
    PAR('P'),
    IMPAR('I');

    private final char codigo;

    TipoApuesta(char codigo) {
        this.codigo = codigo;
    }

    public char getCodigo() {
        return codigo;
    }

    public static TipoApuesta fromCodigo(char codigo) {
        for (TipoApuesta tipo : values()) {
            if (tipo.codigo == codigo) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Código inválido: " + codigo);
    }

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}