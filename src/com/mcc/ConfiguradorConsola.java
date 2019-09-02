package com.mcc;

import java.io.IOException;
import java.util.Scanner;

class ConfiguradorConsola {
    ConfiguradorConsola() throws IOException {

        System.out.print("Numero de Filas : ");
        int filas = LeerEntero();
        System.out.print("Numero de Columnas : ");
        int columnas = LeerEntero();
        System.out.print("Numero de Generaciones : ");
        int generaciones = LeerEntero();
        System.out.print("Porcentaje de Organismos Inicales (de 0 a 50) % : ");
        int porcentajeDeOrganismosIniciales = LeerEntero();

        AdministradorDeJuego.GenerarTablero(filas, columnas, generaciones, porcentajeDeOrganismosIniciales);
        AdministradorDeJuego.IniciarJuego();
    }

    private static int LeerEntero() throws IOException {

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
        for (int i = 1; i <= AdministradorDeJuego.tablero.NumeroDeOrganismosIniciales; i++) {

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
                if (x >= AdministradorDeJuego.tablero.filas) {
                    x = AdministradorDeJuego.tablero.filas - 1;
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

                if (y >= AdministradorDeJuego.tablero.columnas) {
                    y = AdministradorDeJuego.tablero.columnas - 1;
                    System.out.println("y -> " + y);
                } else if (y < 0) {
                    y = 0;
                    System.out.println("y -> " + y);
                }

                if (AdministradorDeJuego.tablero.celdas[x][y].organismo)
                    System.out.println("La celda [" + x + "][" + y + "] ya tien organismo.\nReiniciando proceso para organismo inicial " + i);
            } while (AdministradorDeJuego.tablero.celdas[x][y].organismo);

            AdministradorDeJuego.tablero.celdas[x][y].organismo = true;
        }
    }
}

