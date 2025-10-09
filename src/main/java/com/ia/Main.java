
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
    // int a = 3, b = 3;
    // int[][] m = new int[a][b];
    // Caballo
    // Laberinto.laberintoDamaA(m, 0, 0, a - 1, b - 1, 1);
    // System.out.println("Cantidad: " + Laberinto.c);
    // Laberinto.laberintoCaballoB(m, 0, 0, a - 1, b - 1, 1);
    // System.out.println("Cantidad: " + Laberinto.c);
    // Laberinto.laberintoCaballoC(m, 0, 0, a - 1, b - 1, 1);
    // System.out.println("Cantidad: " + Laberinto.c);
    // Laberinto.laberintoCaballoD(m, 0, 0, a - 1, b - 1, 1);
    // System.out.println("Cantidad: " + Laberinto.c);
    // LinkedList<int[][]> L = new LinkedList<>();
    // Laberinto.laberintoCaballo(m, L, 0, 0, a - 1, b - 1, 1);
    // System.out.println("Cantidad: " + L.size());

    int a = 5;
    int b = 5;
    int[][] m = new int[a][b];
    if (Laberinto.laberintoDamaConHeuristica(m, 0, 0, a - 1, b - 1, 1)) {
      Laberinto.mostrar(m);
    } else {
      System.out.println("# No existe solución");
    }
  }
}