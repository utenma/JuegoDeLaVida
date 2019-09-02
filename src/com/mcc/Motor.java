package com.mcc;

import com.util.Consola;

class Motor {
    static Tablero tablero;
    static final boolean debug = false;
    static final boolean marcar = true;
    private static final boolean organismosRandom = false;

    public static void main(String[] args) {
        System.out.println("Tecnologías de programación");
        System.out.println("Juego de la Vida de John H. Conway\n");

      try {
            new Configurador();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void GenerarTablero(int filas, int columnas, int generaciones, int porcentajeDeOrganismosIniciales){
        tablero = new Tablero(filas, columnas, generaciones, porcentajeDeOrganismosIniciales);
        if (organismosRandom) tablero.GenerarOrganismosRandom();
        else Configurador.ConfigurarCoordenadasDeOrganismosIniciales();
    }

    public static void IniciarJuego() {
        tablero.CalcularAcciones();
        tablero.MostrarCeldas();
        while (tablero.generacion < tablero.numeroDeGeneraciones) {
            tablero.AplicarAcciones();
            tablero.CalcularAcciones();
            tablero.MostrarCeldas();
            if(!tablero.HayAcciones()) break;
            if (tablero.NumeroDeOrganismos() == 0) break;
        }
        int numeroDeOrganismos = tablero.NumeroDeOrganismos();
        if (numeroDeOrganismos > 0)
            System.out.println(Consola.Color.GREEN + "Juego Ganado con " + numeroDeOrganismos + " organismos" + Consola.Color.RESET);
        else System.out.println(Consola.Color.RED + "Juego Perdido" + Consola.Color.RESET);
        System.out.println("Juego Terminado");
    }
}
