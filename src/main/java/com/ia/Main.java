
package com.ia;

import java.util.*;

// import tema1.tarea1.*;
// import tema1.tarea2.*;
// import tema1.tarea3.*;
// import tema1.tarea4.*;
// import modelos.examen1.*;
// import tema2.tarea1.*;
// import tema2.tarea2.*;
// import tema2.tarea3.*;
// import tema2.tarea4.*;
// import modelos.examen2.*;;
// import tema3.tarea1.*;
import tema3.tarea2.*;

/**
 *
 * @author HP
 */
public class Main {
    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        System.out.println();
        int n = 83; // 1:83 (sof), 2:73 (depende de la suerte) 3:83 (sof)
        int[][] m = new int[n][n];

        if (SaltoCaballo.saltoCaballoConHeuristica3(m, 0, 0, 1)) {
            SaltoCaballo.mostrar(m);
            System.out.println("Cantidad de vueltas: " + SaltoCaballo.vueltas);
            return;
        }
        System.out.println("No hay solución");
    }
}