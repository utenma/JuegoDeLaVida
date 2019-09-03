package com.mcc;

import com.util.Consola;

import java.util.Random;

class Tablero {

    private final Celda[][] celdas;
    private final int filas;
    private final int columnas;
    private final int numeroDeGeneraciones;
    private final int numeroDeOrganismosIniciales;
    private int generacion = 1;

    public Tablero(int numFilas, int numColumnas, int numGeneraciones, int porOrganismos) {
        filas = numFilas;
        columnas = numColumnas;
        int numeroDeCeldas = filas * columnas;

        celdas = new Celda[numFilas][numColumnas];
        numeroDeGeneraciones = numGeneraciones;
        numeroDeOrganismosIniciales = Math.round((porOrganismos * filas * columnas) / 100f);

        for (int f = 0; f < celdas.length; f++) {
            for (int c = 0; c < celdas[f].length; c++) {
                celdas[f][c] = new Celda();
            }
        }
            System.out.println("---------------------------------------------------------");
            System.out.println("Tablero inicializado con " +
                    filas + " filas y " + columnas + " columnas = " +
                    numeroDeCeldas + " celdas");

            System.out.println("Cantidad de organismos iniciales = " +
                    numeroDeCeldas + " x " + porOrganismos +
                    " % " + " = " + (porOrganismos * filas * columnas) / 100f
                    + " -> " + numeroDeOrganismosIniciales);
            System.out.println("---------------------------------------------------------");
    }

    int getNumeroDeFilas() { return filas; }
    int getNumeroDeColumnas() { return columnas; }
    int getGeneracionActual() { return generacion; }
    int getNumeroDeGeneraciones() { return numeroDeGeneraciones; }
    int getNumeroDeOrganismosIniciales() { return numeroDeOrganismosIniciales; }
    Celda[][] getCeldas() { return celdas; }

    private AccionDeCelda checarPorVecinos(byte x, byte y) {

        if (Motor.getDebug()) {
            System.out.println("-----------------------------------------------------");
            System.out.println("Revisión de vecinos para celda [" + x + "][" + y + "]");
        }

        byte vecinos = 0;
        for (int f = -1; f <= 1; f++) {
            int X = x + f;
            if (X >= 0 && X < filas) {
                for (int c = -1; c <= 1; c++) {
                    if (f == 0 && c == 0) continue;
                    int Y = y + c;
                    if (Y >= 0 && Y < columnas) {
                        try {
                            if (Motor.getDebug()) {
                                System.out.print("Vecino [" + X + "][" + Y + "] = ");
                                String color = (celdas[X][Y].getOrganismo()) ? Consola.Color.GREEN : Consola.Color.RED;
                                System.out.println(color + celdas[X][Y].getOrganismo() + Consola.Color.RESET);
                            }
                            if (celdas[X][Y].getOrganismo()) vecinos++;
                        } catch (Exception e) {
                            if (Motor.getDebug())
                                System.out.println("Excepción para Celda [" + X + "][" + Y + "]");
                            if (Motor.getDebug()) System.out.println(e.getMessage());
                        }
                    }
                }
            }
        }
        if (Motor.getDebug())
            System.out.println("Resultado de revisión de vecinos para celda [" + x + "][" + y + "] = " + vecinos + " Vecinos");
        if (celdas[x][y].getOrganismo()) {
            if (vecinos < 2) return AccionDeCelda.Eliminar;
            else if (vecinos == 2 || vecinos == 3) return AccionDeCelda.Ninguna;
            else return AccionDeCelda.Eliminar; //Vecinos > 3
        } else if (vecinos == 3) return AccionDeCelda.Agregar;

        return AccionDeCelda.Ninguna;
    }

    public void calcularAcciones() {
        for (byte f = 0; f < celdas.length; f++) {
            for (byte c = 0; c < celdas[f].length; c++) {
                celdas[f][c].setAccion(checarPorVecinos(f, c));
            }
        }
        if (Motor.getDebug()) System.out.println("-----------------------------------------------------");
    }

    public void aplicarAcciones() {
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                if (celdas[f][c].getAccion() == AccionDeCelda.Agregar) celdas[f][c].setOrganismo(true);
                if (celdas[f][c].getAccion() == AccionDeCelda.Eliminar) celdas[f][c].setOrganismo(false);
            }
        }
        generacion++;
    }

    public void mostrarCeldas() {
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
                String color;
                if (celdas[f][c].getOrganismo()) {
                    caracter = "*";
                    if(Motor.getMarcar()) {color = (celdas[f][c].getAccion() == AccionDeCelda.Eliminar) ? Consola.Color.RED : Consola.Color.BLUE;}
                    else color = Consola.Color.BLUE;
                }
                else {
                    if (Motor.getMarcar()) { caracter = (celdas[f][c].getAccion() == AccionDeCelda.Agregar) ? "¤" :  " ";}
                    else caracter = " ";
                    color = Consola.Color.WHITE;
                }
                System.out.print(color + caracter + Consola.Color.RESET);
            }
            System.out.print("|");
        }
        System.out.println();
    }

    public void generarOrganismosRandom() {
        Random random = new Random();
        for (int i = 0; i < numeroDeOrganismosIniciales; i++) {
            int fil;
            int col;
            do {
                fil = random.nextInt(filas);
                col = random.nextInt(columnas);
            } while (celdas[fil][col].getOrganismo());
            celdas[fil][col].setOrganismo(true);
        }
    }

    public int numeroDeOrganismos() {
        int numeroDeOrganismos = 0;
        for (byte f = 0; f < filas; f++) {
            for (byte c = 0; c < columnas; c++) {
                if (celdas[f][c].getOrganismo()) numeroDeOrganismos++;
            }
        }
        return  numeroDeOrganismos;
    }

    public boolean hayAcciones(){
        for (byte f = 0; f < filas; f++) {
            for (byte c = 0; c < columnas; c++) {
                if (celdas[f][c].getAccion() != AccionDeCelda.Ninguna) return true;
            }
        }
        return  false;
    }
}
