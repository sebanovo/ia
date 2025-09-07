package modelos.examen1;

import java.util.*;

public class Sumandos {
  public static int c = 0;

  /*
   * 1. Sumandos
   */
  private static int suma(LinkedList<Integer> L1) {
    int suma = 0;
    for (int i = 0; i < L1.size(); i++) {
      suma += L1.get(i);
    }
    return suma;
  }

  public static void sumandos(LinkedList<Integer> L1, LinkedList<LinkedList<Integer>> L2, int n, int i) {
    int sum = suma(L1);
    if (sum > n) {
      return;
    }
    if (sum == n) {
      L2.add(new LinkedList<Integer>(L1));
      return;
    }
    int k = i;
    while (k <= n) {
      L1.add(k);
      sumandos(L1, L2, n, k);
      L1.removeLast();
      k = k + 1;
    }
  }

  /*
   * 1.1. Encontrar los sumandos cuadrados de n.
   */
  private static boolean esCuadrado(int n) {
    int raizCua = (int) Math.sqrt(n);
    return raizCua * raizCua == n;
  }

  private static boolean todosCuadrados(LinkedList<Integer> L1) {
    for (int i = 0; i < L1.size(); i++) {
      if (!esCuadrado(L1.get(i)))
        return false;
    }
    return true;
  }

  public static void sumandosCuadrados(LinkedList<Integer> L1, LinkedList<LinkedList<Integer>> L2, int n, int i) {
    int sum = suma(L1);
    if (sum > n) {
      return;
    }
    if (sum == n) {
      if (todosCuadrados(L1)) {
        L2.add(new LinkedList<Integer>(L1));
      }
      return;
    }
    int k = i;
    while (k <= n) {
      L1.add(k);
      sumandosCuadrados(L1, L2, n, k);
      L1.removeLast();
      k = k + 1;
    }
  }

  /*
   * 1.2. Encontrar los sumandos fibonacci de n. (Todos los números que pertenecen
   * a la secuencia de fibonnaci)
   */
  private static boolean esFibonacci(int n) {
    int n1 = (5 * n * n) + 4;
    int n2 = (5 * n * n) - 4;
    return esCuadrado(n1) || esCuadrado(n2);
  }

  private static boolean todosFibonacci(LinkedList<Integer> L1) {
    for (int i = 0; i < L1.size(); i++) {
      if (!esFibonacci(L1.get(i)))
        return false;
    }
    return true;
  }

  public static void sumandosFibonacci(LinkedList<Integer> L1, LinkedList<LinkedList<Integer>> L2, int n, int i) {
    int sum = suma(L1);
    if (sum > n) {
      return;
    }
    if (sum == n) {
      if (todosFibonacci(L1)) {
        L2.add(new LinkedList<Integer>(L1));
      }
      return;
    }
    int k = i;
    while (k <= n) {
      L1.add(k);
      sumandosFibonacci(L1, L2, n, k);
      L1.removeLast();
      k = k + 1;
    }
  }

  /*
   * 2. Factores.
   */
  private static int multiplicacion(LinkedList<Integer> L1) {
    int factores = 1;
    for (int i = 0; i < L1.size(); i++) {
      factores *= L1.get(i);
    }
    return factores;
  }

  public static void factores(LinkedList<Integer> L1, LinkedList<LinkedList<Integer>> L2, int n, int i) {
    int mul = multiplicacion(L1);
    if (mul > n) {
      return;
    }
    if (mul == n) {
      L2.add(new LinkedList<Integer>(L1));
      return;
    }
    int k = i;
    while (k <= n) {
      L1.add(k);
      factores(L1, L2, n, k);
      L1.removeLast();
      k = k + 1;
    }
  }

  /*
   * 2.1. Encontrar los factores cuadrados de n.
   */
  public static void factoresCuadrados(LinkedList<Integer> L1, LinkedList<LinkedList<Integer>> L2, int n, int i) {
    int mul = multiplicacion(L1);
    if (mul > n) {
      return;
    }
    if (mul == n) {
      if (todosCuadrados(L1)) {
        L2.add(new LinkedList<Integer>(L1));
      }
      return;
    }
    int k = i;
    while (k <= n) {
      L1.add(k);
      factoresCuadrados(L1, L2, n, k);
      L1.removeLast();
      k = k + 1;
    }
  }

  /*
   * 2.2. Encontrar los factores fibonacci de n. (Todos los números que pertenecen
   * a la secuencia de fibonnaci)
   */
  public static void factoresFibonacci(LinkedList<Integer> L1, LinkedList<LinkedList<Integer>> L2, int n, int i) {
    int mul = multiplicacion(L1);
    if (mul > n) {
      return;
    }
    if (mul == n) {
      if (todosFibonacci(L1)) {
        L2.add(new LinkedList<Integer>(L1));
      }
      return;
    }
    int k = i;
    while (k <= n) {
      L1.add(k);
      factoresFibonacci(L1, L2, n, k);
      L1.removeLast();
      k = k + 1;
    }
  }
}
