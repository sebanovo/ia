package com.ia;

import java.util.*;

// import tarea1.Sumandos;
import tarea3.*;

/**
 *
 * @author HP
 */
public class Main {

  /**
   * @param args the command line arguments
   */

  public static void main(String[] args) {
    // Sumandos
    // LinkedList<Integer> L1 = new LinkedList<Integer>();
    // System.out.println();
    // Sumandos.sumandosPrimos(L1, 20, 1);
    // System.out.println(Sumandos.c);

    // Mochila
    LinkedList<VarianteMochila.Objeto> L1 = new LinkedList<VarianteMochila.Objeto>();
    LinkedList<VarianteMochila.Objeto> L2 = new LinkedList<VarianteMochila.Objeto>();
    VarianteMochila.Objeto o1 = new VarianteMochila.Objeto(1, 2, "Azul");
    VarianteMochila.Objeto o2 = new VarianteMochila.Objeto(2, 2, "Rojo");
    VarianteMochila.Objeto o3 = new VarianteMochila.Objeto(3, 3, "Amarillo");
    VarianteMochila.Objeto o4 = new VarianteMochila.Objeto(4, 4, "Verde");
    VarianteMochila.Objeto o5 = new VarianteMochila.Objeto(5, 5, "Morado");
    VarianteMochila.Objeto o6 = new VarianteMochila.Objeto(6, 6, "Cafe");
    L1.add(o1);
    L1.add(o2);
    L1.add(o3);
    L1.add(o4);
    L1.add(o5);
    L1.add(o6);
    System.out.println();
    // VarianteMochila.mochilaPesoVolumen(L1, L2, 10, 10, 0);
    // VarianteMochila.mochilaConColor(L1, L2, 10, "Cafe", 0);
    // VarianteMochila.mochilaColorDiferente(L1, L2, 10, 0);
    // VarianteMochila.mochilaVolumenCuadrado(L1, L2, 10, 0);
    VarianteMochila.mochilaVolumenFibonacci(L1, L2, 10, 0);
  }
}
