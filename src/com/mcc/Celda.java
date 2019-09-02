package com.mcc;

public class Celda {
    AccionCeldaSiguienteGen accion;
    Boolean organismo ;

    public Celda() {
        organismo = false;
        accion =  AccionCeldaSiguienteGen.Ninguna;
    }
}
