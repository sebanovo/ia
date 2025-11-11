
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
import tema2.tarea4.*;
// import modelos.examen2.*;;
// import tema3.tarea1.*;
// import tema3.tarea2.*;
// import tema3.tarea3.*;

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
        for (int n = 1; n <= 500; n++) {
            int[][] m = new int[n][n];
            System.out.println();
            if (Laberinto.laberintoReyConHeuristica(m, 0, 0, n - 1, n - 1, 1)) {
                // Laberinto.mostrar(m);
                // System.out.println("vueltas: " + Laberinto.vueltas);
                System.out.print(Laberinto.vueltas);
            } else {
                System.out.println("No tiene solucion");
                // System.out.println("vueltas: " + Laberinto.vueltas);
                // System.out.println(Laberinto.vueltas);
            }
            Laberinto.vueltas = 0;
        }
    }
}