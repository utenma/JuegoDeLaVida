package com.mcc;

import com.util.Consola;

import java.util.Random;

class Tablero {

    public final Celda[][] celdas;
    final int filas;
    final int columnas;
    public int generacion = 0;
    public final int numeroDeGeneraciones;
    private final int porcentajeInicialDeOrganismos;
    final int NumeroDeOrganismosIniciales;
    private final int NumeroDeCeldas;

    public Tablero(int numFilas, int numColumnas, int numGeneraciones, int porOrganismos) {

        filas = numFilas;
        columnas = numColumnas;
        NumeroDeCeldas = filas * columnas;

        celdas = new Celda[numFilas][numColumnas];
        numeroDeGeneraciones = numGeneraciones;
        porcentajeInicialDeOrganismos = porOrganismos;
        NumeroDeOrganismosIniciales = Math.round((porcentajeInicialDeOrganismos * filas * columnas) / 100f);

        for (int f = 0; f < celdas.length; f++) {
            for (int c = 0; c < celdas[f].length; c++) {
                celdas[f][c] = new Celda();
            }
        }
            System.out.println("---------------------------------------------------------");
            System.out.println("Tablero inicializado con " +
                    filas + " filas y " + columnas + " columnas = " +
                    NumeroDeCeldas + " celdas");

            System.out.println("Cantidad de organismos iniciales = " +
                    NumeroDeCeldas + " x " + porOrganismos +
                    " % " + " = " + (porOrganismos * filas * columnas) / 100f
                    + " -> " + NumeroDeOrganismosIniciales);
            System.out.println("---------------------------------------------------------");

    }

    private AccionCeldaSiguienteGeneracion ChecarPorVecinos(byte x, byte y) {
        byte vecinos = 0;

        if (Motor.debug) System.out.println("-----------------------------------------------------");
        if (Motor.debug) System.out.println("Revisión de vecinos para celda [" + x + "][" + y + "]");
        for (int f = -1; f <= 1; f++) {
            int X = x + f;
            if (X >= 0 && X < filas) {
                for (int c = -1; c <= 1; c++) {
                    if (f == 0 && c == 0) continue;
                    int Y = y + c;
                    if (Y >= 0 && Y < columnas) {
                        try {
                            if (Motor.debug) {
                                System.out.print("Vecino [" + X + "][" + Y + "] = ");
                                if (celdas[X][Y].organismo)
                                    System.out.println(Consola.Color.GREEN + celdas[X][Y].organismo + Consola.Color.RESET);
                                else
                                    System.out.println(Consola.Color.RED + celdas[X][Y].organismo + Consola.Color.RESET);
                            }
                            if (celdas[X][Y].organismo) vecinos++;
                        } catch (Exception e) {
                            if (Motor.debug)
                                System.out.println("Excepción para Celda [" + X + "][" + Y + "]");
                            if (Motor.debug) System.out.println(e.getMessage());
                        }
                    }
                }
            }
        }
        if (Motor.debug)
            System.out.println("Resultado de revisión de vecinos para celda [" + x + "][" + y + "] = " + vecinos + " Vecinos");
        if (celdas[x][y].organismo) {
            if (vecinos < 2) return AccionCeldaSiguienteGeneracion.Eliminar;
            else if (vecinos == 2 || vecinos == 3) return AccionCeldaSiguienteGeneracion.Ninguna;
            else if (vecinos > 3) return AccionCeldaSiguienteGeneracion.Eliminar;
        } else if (vecinos == 3) return AccionCeldaSiguienteGeneracion.Añadir;

        return AccionCeldaSiguienteGeneracion.Ninguna;
    }

    public void CalcularAcciones() {
        for (byte f = 0; f < celdas.length; f++) {
            for (byte c = 0; c < celdas[f].length; c++) {
                celdas[f][c].accion = ChecarPorVecinos(f, c);
            }
        }
        if (Motor.debug) System.out.println("-----------------------------------------------------");
    }

    public void AplicarAcciones() {
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                if (celdas[f][c].accion == AccionCeldaSiguienteGeneracion.Añadir) celdas[f][c].organismo = true;
                if (celdas[f][c].accion == AccionCeldaSiguienteGeneracion.Eliminar) celdas[f][c].organismo = false;
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
                String caracter ;
                String color = Consola.Color.BLUE;
                if (celdas[f][c].organismo) {
                    caracter = "*";
                    if(Motor.marcar) { if (celdas[f][c].accion == AccionCeldaSiguienteGeneracion.Eliminar) color = Consola.Color.RED;}
                }
                else {
                    if (Motor.marcar) {
                        if (celdas[f][c].accion == AccionCeldaSiguienteGeneracion.Añadir) {
                            color = Consola.Color.WHITE;
                            caracter = "¤";
                        } else caracter = " ";
                    } else caracter = " ";
                }
                System.out.print(color + caracter + Consola.Color.RESET);
            }
            System.out.print("|");
        }
        System.out.println();
    }

    public void GenerarOrganismosRandom() {
        Random random = new Random();

        for (int i = 0; i < NumeroDeOrganismosIniciales; i++) {
            int fil;
            int col;
            do {
                fil = random.nextInt(filas);
                col = random.nextInt(columnas);
            } while (celdas[fil][col].organismo);

            celdas[fil][col].organismo = true;
        }
    }

    public int NumeroDeOrganismos() {
        int numeroDeOrganismos = 0;
        for (byte f = 0; f < filas; f++) {
            for (byte c = 0; c < columnas; c++) {
                if (celdas[f][c].organismo) numeroDeOrganismos++;
            }
        }
        return  numeroDeOrganismos;
    }

    public boolean HayAcciones(){
        for (byte f = 0; f < filas; f++) {
            for (byte c = 0; c < columnas; c++) {
                if (celdas[f][c].accion != AccionCeldaSiguienteGeneracion.Ninguna) return true;
            }
        }
        return  false;
    }
}
