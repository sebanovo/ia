package com.ia;

import java.util.*;

import tarea1.Sumandos;

/**
 *
 * @author HP
 */
public class IA {

  /**
   * @param args the command line arguments
   */

  public static void main(String[] args) {
    LinkedList<Integer> L1 = new LinkedList<Integer>();
    System.out.println();
    Sumandos.sumandosPrimos(L1, 20, 1);
    System.out.println(Sumandos.c);
  }
}
