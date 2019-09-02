package com.mcc;

public class Tablero {
    public static  Celda[][] celdas;
    static int filas;
    static int columnas;
    static int generacion = 0;
    static int numeroDeGeneraciones;
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

    public static int ObtenerNumeroDeCeldas(){
        return  filas*columnas;
    }

    public static int ObtenerNumeroDeOrganismos(){
        return  Math.round((porcentajeInicialDeOrganismos * filas * columnas) / 100f);
    }

    public void RecalcularCeldas() {

        for (int f = 0; f < celdas.length; f++) {
            for (int c = 0; c < celdas[f].length; c++) {
                if (celdas[f][c].accion ==  AccionCeldaSiguienteGen.Añadir) celdas[f][c].organismo = true;
                if (celdas[f][c].accion ==  AccionCeldaSiguienteGen.Eliminar) celdas[f][c].organismo = false;
            }
        }

        for (int f = 0; f < celdas.length; f++) {
            for (int c = 0; c < celdas[f].length; c++) {
                if (celdas[f][c].accion ==  AccionCeldaSiguienteGen.Añadir) celdas[f][c].organismo = true;
                if (celdas[f][c].accion ==  AccionCeldaSiguienteGen.Eliminar) celdas[f][c].organismo = false;
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
                if (celdas[f][c].organismo) System.out.print("*");
                else System.out.print(" ");
            }
            System.out.print("|");
        }
    }
}
