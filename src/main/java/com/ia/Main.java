
package com.ia;

import java.util.*;

// import tema1.tarea1.*;
// import tema1.tarea2.*;
// import tema1.tarea3.*;
// import tema1.tarea4.*;
// import modelos.examen1.*;
// import tema2.tarea1.*;
// import tema2.tarea2.*;
import tema2.tarea3.*;

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
    int a = 3, b = 3;
    int[][] m = new int[a][b];
    // Rey
    Laberinto.laberintoReyA(m, 0, 0, a - 1, b - 1, 1);
    System.out.println("Cantidad: " + Laberinto.c);
    Laberinto.laberintoReyB(m, 0, 0, a - 1, b - 1, 1);
    System.out.println("Cantidad: " + Laberinto.c);
    Laberinto.laberintoReyC(m, 0, 0, a - 1, b - 1, 1);
    System.out.println("Cantidad: " + Laberinto.c);
    Laberinto.laberintoReyD(m, 0, 0, a - 1, b - 1, 1);
    System.out.println("Cantidad: " + Laberinto.c);
    LinkedList<int[][]> L = new LinkedList<>();
    Laberinto.laberintoRey(m, L, 0, 0, a - 1, b - 1, 1);
    System.out.println("Cantidad: " + L.size());
    // Caballo
    Laberinto.laberintoCaballoA(m, 0, 0, a - 1, b - 1, 1);
    System.out.println("Cantidad: " + Laberinto.c);
    Laberinto.laberintoCaballoB(m, 0, 0, a - 1, b - 1, 1);
    System.out.println("Cantidad: " + Laberinto.c);
    Laberinto.laberintoCaballoC(m, 0, 0, a - 1, b - 1, 1);
    System.out.println("Cantidad: " + Laberinto.c);
    Laberinto.laberintoCaballoD(m, 0, 0, a - 1, b - 1, 1);
    System.out.println("Cantidad: " + Laberinto.c);
    LinkedList<int[][]> L = new LinkedList<>();
    Laberinto.laberintoCaballo(m, L, 0, 0, a - 1, b - 1, 1);
    System.out.println("Cantidad: " + L.size());
    // Torres
    Laberinto.laberintoTorresA(m, 0, 0, a - 1, b - 1, 1);
    System.out.println("Cantidad: " + Laberinto.c);
    Laberinto.laberintoTorresB(m, 0, 0, a - 1, b - 1, 1);
    System.out.println("Cantidad: " + Laberinto.c);
    Laberinto.laberintoTorresC(m, 0, 0, a - 1, b - 1, 1);
    System.out.println("Cantidad: " + Laberinto.c);
    Laberinto.laberintoTorresD(m, 0, 0, a - 1, b - 1, 1);
    System.out.println("Cantidad: " + Laberinto.c);
    LinkedList<int[][]> L = new LinkedList<>();
    Laberinto.laberintoTorres(m, L, 0, 0, a - 1, b - 1, 1);
    System.out.println("Cantidad: " + L.size());
    // Alfil
    Laberinto.laberintoAlfilA(m, 0, 0, a - 1, b - 1, 1);
    System.out.println("Cantidad: " + Laberinto.c);
    Laberinto.laberintoAlfilB(m, 0, 0, a - 1, b - 1, 1);
    System.out.println("Cantidad: " + Laberinto.c);
    Laberinto.laberintoAlfilC(m, 0, 0, a - 1, b - 1, 1);
    System.out.println("Cantidad: " + Laberinto.c);
    Laberinto.laberintoAlfilD(m, 0, 0, a - 1, b - 1, 1);
    System.out.println("Cantidad: " + Laberinto.c);
    LinkedList<int[][]> L = new LinkedList<>();
    Laberinto.laberintoAlfil(m, L, 0, 0, a - 1, b - 1, 1);
    System.out.println("Cantidad: " + L.size());
    // Dama
    Laberinto.laberintoDamaA(m, 0, 0, a - 1, b - 1, 1);
    System.out.println("Cantidad: " + Laberinto.c);
    Laberinto.laberintoDamaB(m, 0, 0, a - 1, b - 1, 1);
    System.out.println("Cantidad: " + Laberinto.c);
    Laberinto.laberintoDamaC(m, 0, 0, a - 1, b - 1, 1);
    System.out.println("Cantidad: " + Laberinto.c);
    Laberinto.laberintoDamaD(m, 0, 0, a - 1, b - 1, 1);
    System.out.println("Cantidad: " + Laberinto.c);
    LinkedList<int[][]> L = new LinkedList<>();
    Laberinto.laberintoDama(m, L, 0, 0, a - 1, b - 1, 1);
    System.out.println("Cantidad: " + L.size());
  }
}