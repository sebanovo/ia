
package com.ia;

import java.util.*;

// import tema1.tarea1.*;
// import tema1.tarea2.*;
// import tema1.tarea3.*;
// import tema1.tarea4.*;
// import modelos.examen1.*;
// import tema2.tarea1.*;
import tema2.tarea2.*;

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
    int a = 5, b = 5;
    int[][] m = new int[a][b];
    Laberinto.laberinto1A(m, 0, 0, 2, 2, 1);
    System.out.println("Cantidad: " + Laberinto.c);
    Laberinto.laberinto1B(m, 0, 0, 2, 2, 1);
    System.out.println("Cantidad: " + Laberinto.c);
    Laberinto.laberinto2(m, 0, 0, 2, 2, 1);
    System.out.println("Cantidad: " + Laberinto.c);
    Laberinto.laberinto3(m, 0, 0, 2, 2, 1);
    System.out.println("Cantidad: " + Laberinto.c);
    Laberinto.laberinto4(m, 0, 0, 2, 2, 1);
    System.out.println("Cantidad: " + Laberinto.c);
    LinkedList<int[][]> L = new LinkedList<>();
    Laberinto.laberinto5(m, L, 0, 0, 2, 2, 1);
    Laberinto.mostrar(L);
    System.out.println("Cantidad: " + L.size());
    Laberinto.laberinto6(m, 0, 0, 4, 4, 1);
    System.out.println("Cantidad: " + Laberinto.c);

  }
}