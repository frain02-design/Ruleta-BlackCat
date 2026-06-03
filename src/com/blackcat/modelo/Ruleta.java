package com.blackcat.modelo;

import java.util.Random;

public class Ruleta {

    private static final int MAX_HISTORIAL = 100;
    private static final int NUMEROS_RULETA = 37;

    private int[] historialNumeros;
    private int[] historialApuestas;
    private boolean[] historialAciertos;
    private int historialSize;
    private Random rng;
    private int[] numerosRojos;

    public Ruleta() {
        historialNumeros = new int[MAX_HISTORIAL];
        historialApuestas = new int[MAX_HISTORIAL];
        historialAciertos = new boolean[MAX_HISTORIAL];
        historialSize = 0;
        rng = new Random();
        numerosRojos = new int[]{1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
    }

    public int girarRuleta() {
        return rng.nextInt(NUMEROS_RULETA);
    }

    public boolean esRojo(int numero) {
        for (int rojo : numerosRojos) {
            if (rojo == numero) {
                return true;
            }
        }
        return false;
    }

    public String obtenerColor(int numero) {
        if (numero == 0) {
            return "Verde";
        }
        return esRojo(numero) ? "Rojo" : "Negro";
    }

    public boolean evaluarResultado(int numero, char tipoApuesta) {
        if (numero == 0) {
            return false;
        }
        switch (tipoApuesta) {
            case 'P': return numero % 2 == 0;
            case 'I': return numero % 2 != 0;
            case 'R': return esRojo(numero);
            case 'N': return !esRojo(numero);
            default: return false;
        }
    }

    public void registrarResultado(int numero, int apuesta, boolean acierto) {
        if (historialSize < MAX_HISTORIAL) {
            historialNumeros[historialSize] = numero;
            historialApuestas[historialSize] = apuesta;
            historialAciertos[historialSize] = acierto;
            historialSize++;
        }
    }

    public int getHistorialSize() {
        return historialSize;
    }

    public int[] getHistorialNumeros() {
        return historialNumeros;
    }

    public int[] getHistorialApuestas() {
        return historialApuestas;
    }

    public boolean[] getHistorialAciertos() {
        return historialAciertos;
    }
}