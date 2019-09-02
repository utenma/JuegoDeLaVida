package com.mcc;


import java.io.IOException;
import java.util.Random;

public class AdministradorDeJuego {
    public static Tablero tablero;
    public static boolean debug = true;

    public static void main(String[] args) {
        System.out.println("Maestría en Ciencias de la Computación");
        System.out.println("Tecnologías de Programación");
        System.out.println("Juego de la Vida de John H. Conway\n");
        new ConfiguradorGUI();
    }

    public static void IniciarJuego() {


        tablero.MostrarCeldas();

        while (tablero.generacion < tablero.numeroDeGeneraciones){
            tablero.RecalcularCeldas();
            tablero.MostrarCeldas();
        }
    }
}
