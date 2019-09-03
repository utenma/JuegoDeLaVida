package com.mcc;

import com.util.Consola;

import java.util.Scanner;

class Motor {
    private static Tablero tablero;
    private static boolean marcar = true;
    private static boolean debug = false;

    /**
     * Funcion de entrada del programa
     * @param args arreglo String con los parámetros ingresados al inciar el programa
     */
    public static void main(String[] args) {
        System.out.println("Tecnologías de programación");
        System.out.println("Juego de la Vida de John H. Conway\n");

        try {
            configurar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Obtiene entrada del usuario referente al juego,
     * despues inicializa el tablero y configuraciones de juego,
     * finalmente inicia el Game Loop
     * @return void
     */
    private static void configurar() {
        System.out.print("Numero de Filas : ");
        int filas = leerEntero();
        System.out.print("Numero de Columnas : ");
        int columnas = leerEntero();
        System.out.print("Numero de Generaciones (2 o más): ");
        int generaciones = leerEntero();
        generaciones = Math.max(generaciones, 2);
        System.out.print("Porcentaje de Organismos Inicales (de 1 a 50) % : ");
        int porcentajeDeOrganismosIniciales = leerEntero();
        if (porcentajeDeOrganismosIniciales < 1) porcentajeDeOrganismosIniciales = 1;
        else if (porcentajeDeOrganismosIniciales > 50) porcentajeDeOrganismosIniciales = 50;
        System.out.print("Generar organismos iniciales en posiciones random Sí: true , No: false ");
        boolean organismosRandom = leerBooleano();
        System.out.print("Marcar acciones en tablero: true , No: false ");
        marcar = leerBooleano();
        System.out.print("Mostrar impresión de conteo de vecinos Sí: true , No: false ");
        debug = leerBooleano();

        tablero = new Tablero(filas, columnas, generaciones, porcentajeDeOrganismosIniciales);
        if (organismosRandom) tablero.generarOrganismosRandom();
        else configurarCoordenadasDeOrganismosIniciales();
        ejecutarGameLoop();
    }

    /**
     * El famoso GameLoop donde se ejecuta el juego de forma cíclica
     * hasta que se cumpla alguna condición de terminación
     * @return void
     * */
    private static void ejecutarGameLoop() {
        Scanner sc = new Scanner(System.in);
        tablero.calcularAcciones();
        tablero.mostrarCeldas();
        System.out.print("Esperando enter ->");
        sc.nextLine();
        do {
            tablero.aplicarAcciones();
            tablero.calcularAcciones();
            tablero.mostrarCeldas();
            if (!tablero.hayAcciones()) break; //Ya no hay cambios en generaciones siguientes
            if (tablero.numeroDeOrganismos() == 0) break; //Ya no hay organismos
            System.out.print("Presione enter ->");
            sc.nextLine();
        } while (tablero.getGeneracionActual() < tablero.getNumeroDeGeneraciones());
        int numeroDeOrganismos = tablero.numeroDeOrganismos();
        if (numeroDeOrganismos > 0)
            System.out.println(Consola.Color.GREEN + "Juego Ganado con " + numeroDeOrganismos + " organismos" + Consola.Color.RESET);
        else System.out.println(Consola.Color.RED + "Juego Perdido" + Consola.Color.RESET);
        System.out.println("Juego Terminado");
    }

    /**
     * Obtiene entrada de usuario de tipo entero
     * @return int
     * */
    private static int leerEntero() {
        Scanner sc = new Scanner(System.in);
        int i = 0;
        try {
            i = sc.nextInt();
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }
        return i;
    }

    /**
     * Obtiene entrada de usuario de tipo booleano
     * @return boolean
     * */
    private static boolean leerBooleano() {
        Scanner sc = new Scanner(System.in);
        boolean b = true;
        try {
            b = sc.nextBoolean();
        } catch (Exception e) {
            System.out.println("Error de entrada -> se usa Default (true)");
        }
        return b;
    }

    /**
     * Obtiene entrada de usuario para definir las posiciones de los organismos iniciales en el tablero
     * @return void
     * */
    private static void configurarCoordenadasDeOrganismosIniciales() {
        for (int i = 1; i <= tablero.getNumeroDeOrganismosIniciales(); i++) {

            int x = 0;
            int y = 0;

            do {
                System.out.println("Organismo " + i);
                System.out.print(" x = ");

                try {
                    x = leerEntero();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                if (x >= tablero.getNumeroDeFilas()) {
                    x = tablero.getNumeroDeFilas() - 1;
                    System.out.println("x -> " + x);
                } else if (x < 0) {
                    x = 0;
                    System.out.println("x -> " + x);
                }

                System.out.print(" y = ");
                try {
                    y = leerEntero();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                if (y >= tablero.getNumeroDeColumnas()) {
                    y = tablero.getNumeroDeColumnas() - 1;
                    System.out.println("y -> " + y);
                } else if (y < 0) {
                    y = 0;
                    System.out.println("y -> " + y);
                }

                if (tablero.getCeldas()[x][y].getOrganismo())
                    System.out.println("La celda [" + x + "][" + y + "] ya tien organismo.\nReiniciando proceso para organismo inicial " + i);
            } while (tablero.getCeldas()[x][y].getOrganismo());

            tablero.getCeldas()[x][y].setOrganismo(true);
        }
    }

    /**
     * Regresa si el marcado de acciones futuras del tablero está activo
     * @return boolean
     * */
    static boolean getMarcar() {
        return marcar;
    }
    /**
     * Regresa si la impresión del proceso de revisión de celdas vecinas del tablero está activo
     * @return boolean
     * */
    static boolean getDebug() {
        return debug;
    }
}
