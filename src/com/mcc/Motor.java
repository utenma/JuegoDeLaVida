package com.mcc;

import com.util.Consola;
import java.util.Scanner;

class Motor {
    private static Tablero tablero;

    private static boolean organismosRandom;
    private static boolean marcar = true;
    private static boolean debug = false;

    public static void main(String[] args) {
        System.out.println("Tecnologías de programación");
        System.out.println("Juego de la Vida de John H. Conway\n");

      try {
            configurar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void configurar() {
        System.out.print("Numero de Filas : ");
        int filas = leerEntero();
        System.out.print("Numero de Columnas : ");
        int columnas = leerEntero();
        System.out.print("Numero de Generaciones : ");
        int generaciones = leerEntero();
        System.out.print("Porcentaje de Organismos Inicales (de 1 a 50) % : ");
        int porcentajeDeOrganismosIniciales = leerEntero();
        if ( porcentajeDeOrganismosIniciales < 1 ) porcentajeDeOrganismosIniciales = 1;
        else if( porcentajeDeOrganismosIniciales > 50 ) porcentajeDeOrganismosIniciales = 50;
        System.out.print("Generar Organismos Iniciales en posiciones random Sí: true , No: false ");
        organismosRandom = leerBooleano();
        System.out.print("Marcar acciones en tablero: true , No: false ");
        marcar = leerBooleano();
        System.out.print("Mostrar Debug Sí: true , No: false ");
        debug = leerBooleano();

        generarTablero(filas, columnas, generaciones, porcentajeDeOrganismosIniciales);
        iniciarJuego();
    }

    private static void generarTablero(int filas, int columnas, int generaciones, int porcentajeDeOrganismosIniciales){
        tablero = new Tablero(filas, columnas, generaciones, porcentajeDeOrganismosIniciales);
        if (organismosRandom) tablero.generarOrganismosRandom();
        else configurarCoordenadasDeOrganismosIniciales();
    }

    ///Famoso GameLoop
     private static void iniciarJuego() {
        Scanner sc = new Scanner(System.in);
        tablero.calcularAcciones();
        tablero.mostrarCeldas();
        System.out.print("Esperando enter ->");sc.nextLine();
        while (tablero.getGeneracionActual() < tablero.getNumeroDeGeneraciones()) {
            tablero.aplicarAcciones();
            tablero.calcularAcciones();
            tablero.mostrarCeldas();
            if(!tablero.hayAcciones()) break;
            if (tablero.numeroDeOrganismos() == 0) break;
            System.out.print("Presione enter ->");sc.nextLine();
        }
        int numeroDeOrganismos = tablero.numeroDeOrganismos();
        if (numeroDeOrganismos > 0)
            System.out.println(Consola.Color.GREEN + "Juego Ganado con " + numeroDeOrganismos + " organismos" + Consola.Color.RESET);
        else System.out.println(Consola.Color.RED + "Juego Perdido" + Consola.Color.RESET);
        System.out.println("Juego Terminado");
    }

    private static boolean leerBooleano(){
        Scanner sc = new Scanner(System.in);
        boolean b = true;
        try {
            b = sc.nextBoolean();
        } catch (Exception  e) {
            System.out.println("Error de entrada -> se usa Default (true)");
        }
        return b;
    }

    private static int leerEntero() {
        Scanner sc = new Scanner(System.in);
        int i = 0;
        try {
            i = sc.nextInt();
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }
        return i;
    }

    private static void configurarCoordenadasDeOrganismosIniciales() {
        for (int i = 1; i <= tablero.getNumeroDeOrganismosIniciales(); i++) {

            int x = 0;
            int y = 0;

            do {
                System.out.println("Organismo " + i);
                System.out.print(" x = ");

                try {
                    x = leerEntero();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                if (x >= tablero.getNumeroDeFilas()) {
                    x = tablero.getNumeroDeFilas() - 1;
                    System.out.println("x -> " + x);
                } else if (x < 0) {
                    x = 0;
                    System.out.println("x -> " + x);
                }

                System.out.print(" y = ");
                try {
                    y = leerEntero();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                if (y >= tablero.getNumeroDeColumnas()) {
                    y = tablero.getNumeroDeColumnas() - 1;
                    System.out.println("y -> " + y);
                } else if (y < 0) {
                    y = 0;
                    System.out.println("y -> " + y);
                }

                if (tablero.getCeldas()[x][y].getOrganismo())
                    System.out.println("La celda [" + x + "][" + y + "] ya tien organismo.\nReiniciando proceso para organismo inicial " + i);
            } while (tablero.getCeldas()[x][y].getOrganismo());

            tablero.getCeldas()[x][y].setOrganismo(true);
        }
    }

    static boolean getMarcar() {
        return marcar;
    }

    static boolean getDebug() {
        return debug;
    }
}
