package com.mcc;

/** Define una accion a aplicar a una celda,
 * esto permite que la acción sea definida pero aplicada posteriormente
 * por lo que definir una accion en una celda no afecta el procesamiento de celdas vecinas
 * y permite el funcionamiento de otras funciones como el marcado de acciones en el tablero*/
public enum AccionDeCelda {
    Agregar, Eliminar, Ninguna
}
