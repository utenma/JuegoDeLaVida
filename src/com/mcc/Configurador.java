package com.mcc;

import java.io.IOException;
import java.util.Scanner;

class Configurador {
    Configurador() throws IOException {

        System.out.print("Numero de Filas : ");
        int filas = LeerEntero();
        System.out.print("Numero de Columnas : ");
        int columnas = LeerEntero();
        System.out.print("Numero de Generaciones : ");
        int generaciones = LeerEntero();
        System.out.print("Porcentaje de Organismos Inicales (de 0 a 50) % : ");
        int porcentajeDeOrganismosIniciales = LeerEntero();
        System.out.print("Generar Organismos automaticamente Sí: true , No: false ");
        Motor.organismosRandom = LeerBooleano();
        System.out.print("Marcar acciones en tablero: true , No: false ");
        Motor.marcar = LeerBooleano();
        System.out.print("Mostrar Debug Sí: true , No: false ");
        Motor.debug = LeerBooleano();

        Motor.GenerarTablero(filas, columnas, generaciones, porcentajeDeOrganismosIniciales);
        Motor.IniciarJuego();
    }

    private static boolean LeerBooleano(){
        Scanner sc = new Scanner(System.in);
        boolean b = true;
        try {
            b = sc.nextBoolean();
        } catch (Exception  e) {
            System.out.println("Error de entrada -> se usa Default (true)");
        }
        return b;
    }

    private static int LeerEntero() {
        Scanner sc = new Scanner(System.in);
        int i = 0;
        try {
            i = sc.nextInt();
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }
        return i;
    }

    static void ConfigurarCoordenadasDeOrganismosIniciales() {
        for (int i = 1; i <= Motor.tablero.NumeroDeOrganismosIniciales; i++) {

            int x = 0;
            int y = 0;

            do {
                System.out.println("Organismo " + i);
                System.out.print(" x = ");

                try {
                    x = LeerEntero();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                if (x >= Motor.tablero.filas) {
                    x = Motor.tablero.filas - 1;
                    System.out.println("x -> " + x);
                } else if (x < 0) {
                    x = 0;
                    System.out.println("x -> " + x);
                }


                System.out.print(" y = ");
                try {
                    y = LeerEntero();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                if (y >= Motor.tablero.columnas) {
                    y = Motor.tablero.columnas - 1;
                    System.out.println("y -> " + y);
                } else if (y < 0) {
                    y = 0;
                    System.out.println("y -> " + y);
                }

                if (Motor.tablero.celdas[x][y].organismo)
                    System.out.println("La celda [" + x + "][" + y + "] ya tien organismo.\nReiniciando proceso para organismo inicial " + i);
            } while (Motor.tablero.celdas[x][y].organismo);

            Motor.tablero.celdas[x][y].organismo = true;
        }
    }
}

