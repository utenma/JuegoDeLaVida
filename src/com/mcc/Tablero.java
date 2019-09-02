package com.mcc;

public class Tablero {
    public static Celda[][] celdas;
    static int filas;
    static int columnas;
    public static int generacion = 0;
    public static int numeroDeGeneraciones;
    static int porcentajeInicialDeOrganismos = 0;

    public Tablero(int numFilas, int numColumnas, int numGeneraciones, int porOrganismos) {

        filas = numFilas;
        columnas = numColumnas;
        celdas = new Celda[numFilas][numColumnas];
        numeroDeGeneraciones = numGeneraciones;
        porcentajeInicialDeOrganismos = porOrganismos;

        System.out.println("Tablero inicializado con " +
                filas + " filas y " + columnas + " columnas");

        for (int f = 0; f < celdas.length; f++) {
            for (int c = 0; c < celdas[f].length; c++) {
                celdas[f][c] = new Celda();
            }
        }

        System.out.println("Cantidad de celdas = "
                + filas + " x " + columnas + " x ("
                + porOrganismos + " % )" + " = " + (porOrganismos * filas * columnas) / 100f
                + " -> " + ObtenerNumeroDeOrganismos());
    }

    public static int ObtenerNumeroDeCeldas() {
        return filas * columnas;
    }

    public static int ObtenerNumeroDeOrganismos() {
        return Math.round((porcentajeInicialDeOrganismos * filas * columnas) / 100f);
    }

    AccionCeldaSiguienteGen ChecarPorVecinos(byte x, byte y) {
        byte vecinos = 0;

        if (AdministradorDeJuego.debug) System.out.println("-----------------------------------------------------");
        if (AdministradorDeJuego.debug) System.out.println("Revisión de vecinos para celda [" + x + "][" + y + "]");
        for (int f = -1; f <= 1; f++) {
            int X = x + f;
            if (X >= 0 && X < filas) {
                for (int c = -1; c <= 1; c++) {
                    if (f == 0 && c == 0) continue;
                    int Y = y + c;
                    if (Y >= 0 && Y < columnas) {
                        try {
                            if (AdministradorDeJuego.debug) {
                                System.out.print("Vecino [" + X + "][" + Y + "] = ");
                                if ( celdas[X][Y].organismo) System.out.println(Console.Color.GREEN + celdas[X][Y].organismo + Console.Color.RESET);
                                else System.out.println(Console.Color.RED + celdas[X][Y].organismo + Console.Color.RESET);
                            }
                            if (celdas[X][Y].organismo == true) vecinos++;
                        } catch (Exception e) {
                            if (AdministradorDeJuego.debug) System.out.println("Excepción para Celda [" + X + "][" + Y + "]");
                            if (AdministradorDeJuego.debug) System.out.println(e.getMessage());
                        }
                    }
                }
            }
        }
        if (AdministradorDeJuego.debug) System.out.println("Resultado de revisión de vecinos para celda [" + x + "][" + y + "] = " + vecinos + " Vecinos");
        if (celdas[x][y].organismo) {
            if (vecinos < 2) return AccionCeldaSiguienteGen.Eliminar;
            else if (vecinos == 2 || vecinos == 3) return AccionCeldaSiguienteGen.Ninguna;
            else if (vecinos > 3) return AccionCeldaSiguienteGen.Eliminar;
        } else if (vecinos == 3) return AccionCeldaSiguienteGen.Añadir;

        return AccionCeldaSiguienteGen.Ninguna;
    }

    public void RecalcularCeldas() {

        for (byte f = 0; f < celdas.length; f++) {
            for (byte c = 0; c < celdas[f].length; c++) {
                celdas[f][c].accion = ChecarPorVecinos(f, c);
            }
        }
        if (AdministradorDeJuego.debug) System.out.println("-----------------------------------------------------");

        for (int f = 0; f < celdas.length; f++) {
            for (int c = 0; c < celdas[f].length; c++) {
                if (celdas[f][c].accion == AccionCeldaSiguienteGen.Añadir) celdas[f][c].organismo = true;
                if (celdas[f][c].accion == AccionCeldaSiguienteGen.Eliminar) celdas[f][c].organismo = false;
            }
        }
        generacion++;
    }

    public void MostrarCeldas() {
        System.out.println("Tablero Generacion " + generacion);

        System.out.print(" ");
        for (int c = 0; c < columnas; c++) {
            System.out.print(" ");
            System.out.print(c);
        }
        System.out.print(" ");

        for (int f = 0; f < celdas.length; f++) {
            System.out.print("\n" + f);
            for (int c = 0; c < celdas[f].length; c++) {
                System.out.print("|");
                if (celdas[f][c].organismo) {
                    String color;
                    if (celdas[f][c].accion == AccionCeldaSiguienteGen.Añadir) color = Console.Color.YELLOW;
                    else if (celdas[f][c].accion == AccionCeldaSiguienteGen.Eliminar) color = Console.Color.PURPLE;
                    else color = Console.Color.RED;
                    System.out.print(color + "*" + Console.Color.RESET);
                }
                else System.out.print(" ");
            }
            System.out.print("|");
        }
        System.out.println();
    }
}
