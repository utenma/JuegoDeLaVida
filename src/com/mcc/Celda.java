package com.mcc;

public class Celda {
    AccionCeldaSiguienteGen accion;
    Boolean organismo = false;

    public Celda() {
        organismo = false;
        accion =  AccionCeldaSiguienteGen.Ninguna;
    }
}
