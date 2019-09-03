package com.mcc;

public class Celda {
    private AccionDeCelda accion;
    private boolean organismo;

    public Celda() {
        organismo = false;
        accion = AccionDeCelda.Ninguna;
    }

    AccionDeCelda getAccion() { return accion; }
    void setAccion(AccionDeCelda accion) { this.accion = accion; }
    boolean getOrganismo() { return organismo; }
    void setOrganismo(boolean organismo) { this.organismo = organismo; }
}
