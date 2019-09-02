package com.mcc;


import java.util.Random;

public class AdministradorDeJuego {
    public static Tablero tablero;

    public static void main(String[] args) {
        new ConfiguradorGUI();
    }

    public static void IniciarJuego() {
        tablero.RecalcularCeldas();
        tablero.MostrarCeldas();
    }
}
