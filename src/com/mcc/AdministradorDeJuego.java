package com.mcc;


import java.util.Random;

public class AdministradorDeJuego {
    public static Tablero tablero;
    public static boolean debug = false;
    public static boolean marcar = true;

    public static void main(String[] args) {
        System.out.println("Tecnologías de programación");
        System.out.println("Juego de la Vida de John H. Conway\n");
        new ConfiguradorGUI();
    }

    public static void IniciarJuego() {
        tablero.CalcularAcciones();
        tablero.MostrarCeldas();
        while (tablero.generacion < tablero.numeroDeGeneraciones) {
            tablero.AplicarAcciones();
            tablero.CalcularAcciones();
            tablero.MostrarCeldas();
        }
        int numeroDeOrganismos = tablero.NumeroDeOrganismos();
        if (numeroDeOrganismos > 0)
            System.out.println(Console.Color.GREEN + "Juego Ganado con " + numeroDeOrganismos + " organismos"+ Console.Color.RESET);
        else System.out.println(Console.Color.RED + "Juego Perdido" + Console.Color.RESET);
        System.out.println("Juego Terminado");
    }
}
