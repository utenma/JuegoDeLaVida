package com.mcc;

public class Celda {
    AccionCeldaSiguienteGeneracion accion;
    Boolean organismo ;

    public Celda() {
        organismo = false;
        accion =  AccionCeldaSiguienteGeneracion.Ninguna;
    }
}
