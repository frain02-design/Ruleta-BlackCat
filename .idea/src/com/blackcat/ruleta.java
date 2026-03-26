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


    public static void menu() {
        Scanner in = new Scanner(System.in);
        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion(in);
            ejecutarOpcion(opcion, in);
        } while (opcion != 0);
        in.close();
    }

    public static void mostrarMenu() {
        System.out.println("\n=== CASINO BLACK CAT - RULETA ===");
        System.out.println("1. Iniciar ronda");
        System.out.println("2. Ver estadísticas");
        System.out.println("0. Salir");
        System.out.print("Opción: ");
    }

    public static int leerOpcion(Scanner in) {
        return in.nextInt();
    }

    public static void ejecutarOpcion(int opcion, Scanner in) {
        switch (opcion) {
            case 1:
                iniciarRonda(in);
                break;
            case 2:
                mostrarEstadisticas();
                break;
            case 0:
                System.out.println("¡Gracias por jugar!");
                break;
            default:
                System.out.println("Opción no válida");
        }
    }

    // ========== RONDA ==========
    public static void iniciarRonda(Scanner in) {
        System.out.println("\n--- NUEVA RONDA ---");
        char tipo = leerTipoApuesta(in);
        int monto = leerMonto(in);
        System.out.println("Apuesta: " + tipo + " - $" + monto);
    }

    public static int leerMonto(Scanner in) {
        System.out.print("Monto a apostar: $");
        return in.nextInt();
    }

    public static char leerTipoApuesta(Scanner in) {
        char tipo;
        do {
            System.out.print("Tipo (R=Rojo, N=Negro, P=Par, I=Impar): ");
            tipo = in.next().toUpperCase().charAt(0);
        } while (tipo != 'R' && tipo != 'N' && tipo != 'P' && tipo != 'I');
        return tipo;
    }

    // ========== FUNCIONES PENDIENTES ==========
    public static int girarRuleta() { return 0; }
    public static boolean evaluarResultado(int numero, char tipo) { return false; }
    public static boolean esRojo(int n) { return false; }
    public static void registrarResultado(int numero, int apuesta, boolean acierto) {}
    public static void mostrarResultado(int numero, char tipo, int monto, boolean acierto) {}

    public static void mostrarEstadisticas() {
        System.out.println("\nEstadísticas (próximamente)");
    }
}
