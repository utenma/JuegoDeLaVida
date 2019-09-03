package com.mcc;

import java.util.ArrayList;

/**
 * Elemento basico del tablero.
 * Cada celda define su estado presente y futuro mediante
 * una AccionDeCelda (estado siguiente generación)
 * y un booleano (estado actual / organismo )
 */
class Celda {
    private AccionDeCelda accion;
    private boolean organismo;
//    ArrayList<pos> vecinos;

    /**
     * Define la celda como vacia (sin organismo)
     * y sin accion
     * @return void
     */
    public Celda() {
        organismo = false;
        accion = AccionDeCelda.Ninguna;
//        vecinos = new ArrayList<pos>();
    }

    AccionDeCelda getAccion() { return accion; }
    void setAccion(AccionDeCelda accion) { this.accion = accion; }
    boolean getOrganismo() { return organismo; }
    void setOrganismo(boolean organismo) { this.organismo = organismo; }

    public class pos {
        public int x;
        public int y;
    }
}
