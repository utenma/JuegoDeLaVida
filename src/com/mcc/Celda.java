package com.mcc;

/**
 * Elemento basico del tablero.
 * Cada celda define su estado presente y futuro mediante
 * una AccionDeCelda (estado siguiente generación)
 * y un booleano (estado actual / organismo )
 */
class Celda {
    private AccionDeCelda accion;
    private boolean organismo;

    /**
     * Define la celda como vacia (sin organismo)
     * y sin accion
     * @return void
     */
    public Celda() {
        organismo = false;
        accion = AccionDeCelda.Ninguna;
    }

    AccionDeCelda getAccion() { return accion; }
    void setAccion(AccionDeCelda accion) { this.accion = accion; }
    boolean getOrganismo() { return organismo; }
    void setOrganismo(boolean organismo) { this.organismo = organismo; }
}
