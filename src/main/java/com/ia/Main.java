
package com.ia;

import java.util.*;

// import tarea1.*;
// import tarea2.*;
// import tarea3.*;
import tarea4.*;

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
    LinkedList<String> L1 = new LinkedList<String>();
    LinkedList<String> L2 = new LinkedList<String>();
    // L1 = [“Alejandro”, “Pedro”, “Juan”, “Mario”, “Lucas”, “Daniel”]
    L1.add("Manzana");
    L1.add("Pera");
    L1.add("Uva");
    L1.add("Manga");
    int n = L1.size();
    int r = 2;
    Combinacion.combiSR(L1, L2, n, r, 0);
    // Combinacion.combiCR(L1, L2, n, r, 0);
    // Combinacion.permSR(L1, L2, n, r, 0);
    // Combinacion.permCR(L1, L2, n, r, 0);
    System.out.println("Cantidad :" + Combinacion.c);
  }
}