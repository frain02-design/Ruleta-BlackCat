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

    public static void iniciarRonda(Scanner in) {
        System.out.println("\n--- NUEVA RONDA ---");
        char tipo = leerTipoApuesta(in);
        int monto = leerMonto(in);
        System.out.println("Apuesta: " + tipo + " - $" + monto);

        System.out.println("\nGirando la ruleta...");
        int numero = girarRuleta();

        boolean acierto = evaluarResultado(numero, tipo);
        mostrarResultado(numero, tipo, monto, acierto);
        registrarResultado(numero, monto, acierto);

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

    public static int girarRuleta() {
        return rng.nextInt(37); }

    public static boolean esRojo(int n) {
        for (int rojo : numerosRojos){
            if (rojo == n){
                return true;
            }
        }
        return false;
    }

    public static boolean evaluarResultado(int numero, char tipo) {

        if(numero == 0){
        return false;
        }
    switch (tipo) {
        case 'P':
            return numero % 2 == 0;
        case 'I':
            return numero % 2 != 0;
        case 'R':
            return esRojo(numero);
        case 'N':
            return !esRojo(numero);
        default:
            return false;

    }

    public static void registrarResultado(int numero, int apuesta, boolean acierto) {
            if (historialSize < MAX_HISTORIAL){
                historialNumeros[historialSize] = numero;
                historialApuestas[historialSize] = apuesta;
                historialAciertos[historialSize] = acierto;
                historialSize++;
                System.out.println("Ronda Registrada en el Historial");
            }else{
                System.out.println("Historial lleno. no se puedo registrar esta ronda");
            }

        }


    public static void mostrarResultado(int numero, char tipo, int monto, boolean acierto) {
            System.out.println("\n=== RESULTADO ===");
            System.out.println("Número: " + numero);

            if (numero == 0) {
                System.out.println("Color: Verde (0)");
            } else if (esRojo(numero)) {
                System.out.println("Color: Rojo");
            } else {
                System.out.println("Color: Negro");
            }

            String nombreApuesta = "";
            switch (tipo) {
                case 'R': nombreApuesta = "Rojo"; break;
                case 'N': nombreApuesta = "Negro"; break;
                case 'P': nombreApuesta = "Par"; break;
                case 'I': nombreApuesta = "Impar"; break;
            }
            System.out.println("Apuesta: " + nombreApuesta + " - $" + monto);

            if (acierto) {
                System.out.println("¡GANASTE! +$" + monto);
            } else {
                System.out.println("PERDISTE -$" + monto);
            }
    }

    public static void mostrarEstadisticas() {
        System.out.println("\nEstadísticas (próximamente)");
    }
}
