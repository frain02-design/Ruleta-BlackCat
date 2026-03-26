package com.blackcat;

import java.util.Random;
import java.util.Scanner;

public class ruleta {

    public static final int MAX_HISTORIAL = 100;
    public static int[] historialNumeros = new int[MAX_HISTORIAL];
    public static int[] historialApuestas = new int[MAX_HISTORIAL];
    public static boolean[] historialAciertos = new boolean[MAX_HISTORIAL];
    public static int historialSize = 0;
    public static Random rng = new Random();
    public static int[] numerosRojos = {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {}
    public static void mostrarMenu() {}
    public static int leerOpcion(Scanner in) { return 0; }
    public static void ejecutarOpcion(int opcion, Scanner in) {}
    public static void iniciarRonda(Scanner in) {}
    public static char leerTipoApuesta(Scanner in) { return ' '; }
    public static int girarRuleta() { return 0; }
    public static boolean evaluarResultado(int numero, char tipo) { return false; }
    public static boolean esRojo(int n) { return false; }
    public static void registrarResultado(int numero, int apuesta, boolean acierto) {}
    public static void mostrarResultado(int numero, char tipo, int monto, boolean acierto) {}
    public static void mostrarEstadisticas() {}
}
