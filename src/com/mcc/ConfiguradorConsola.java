package com.mcc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConfiguradorConsola {
    public ConfiguradorConsola() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Numero de Filas : ");
        int filas = LeerEntero();
        System.out.print("Numero de Columnas : ");
        int  columnas = LeerEntero();
        System.out.print("Numero de Generaciones : ");
        int  generaciones = LeerEntero();
        System.out.print("Porcentaje de Organismos Inicales (de 0 a 50) % : ");
        int  porcentajeDeOrganismosIniciales = LeerEntero();

        AdministradorDeJuego.GenerarTablero(filas, columnas, generaciones, porcentajeDeOrganismosIniciales);
        AdministradorDeJuego.IniciarJuego();
    }

    private static int LeerEntero() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int i = 0;

        try {
            i = Integer.parseInt(br.readLine());
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }

        return i;
    }

    public static void ConfigurarCoordenadasDeOrganismosIniciales() {
        for (int i = 1; i <=  AdministradorDeJuego.tablero.NumeroDeOrganismosIniciales; i++) {

            int x = 0;
            int y = 0;

            System.out.println("Organismo " + i );
            System.out.print(" x = " ) ;
            try {x = LeerEntero();} catch (Exception e) { System.out.println(e.getMessage());}
            System.out.print(" y = " ) ;
            try {y = LeerEntero();} catch (Exception e) { System.out.println(e.getMessage());}

            AdministradorDeJuego.tablero.celdas[x][y].organismo = true;
            }
        }
    }

