package com.mcc;

public class AdministradorDeJuego {
    public static Tablero tablero;
    public static boolean debug = false;
    public static boolean marcar = true;
    static boolean organismosRandom = false;

    public static void main(String[] args) {
        System.out.println("Tecnologías de programación");
        System.out.println("Juego de la Vida de John H. Conway\n");

      try {
            new ConfiguradorConsola();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void GenerarTablero(int filas, int columnas, int generaciones, int porcentajeDeOrganismosIniciales){
        tablero = new Tablero(filas, columnas, generaciones, porcentajeDeOrganismosIniciales);
        if (organismosRandom) tablero.GenerarOrganismosRandom();
        else ConfiguradorConsola.ConfigurarCoordenadasDeOrganismosIniciales();
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
            System.out.println(Console.Color.GREEN + "Juego Ganado con " + numeroDeOrganismos + " organismos" + Console.Color.RESET);
        else System.out.println(Console.Color.RED + "Juego Perdido" + Console.Color.RESET);
        System.out.println("Juego Terminado");
    }
}
