
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
import tema3.tarea1.*;

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

    // int n = 4;
    // int[][] m = new int[n][n];
    // if (NReinas.nReinasConHeuristica2(m, 1)) {
    // NReinas.mostrar(m);
    // System.out.println("Vueltas: " + NReinas.vueltas);
    // } else {
    // System.out.println("# No existe solucion");
    // System.out.println("Vueltas: " + NReinas.vueltas);
    // }

    for (int n = 1; n <= 30; n++) {
      long vueltasCon = -1;

      // Ejecución Con Heurística
      int[][] tableroCon = new int[n][n];
      NReinas.vueltas = 0; // Se resetea el contador
      boolean exitoCon = NReinas.nReinasConHeuristica1(tableroCon, 1);

      // Contar vueltas incluso si no hay solución (N=2,3)
      if (exitoCon || n == 2 || n == 3) {
        vueltasCon = NReinas.vueltas;
      }

      // Manejar N=2 y N=3 (sin solución)
      if (!exitoCon && (n == 2 || n == 3)) {
        System.out.printf("| %-5d | %-20s |\n",
            n, "NO SOLUCIÓN (" + vueltasCon + "v)");
      } else {
        System.out.printf("| %-5d | %-20d |\n",
            n, vueltasCon);
      }
    }
  }
}