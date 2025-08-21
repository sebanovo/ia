package tarea1;

import java.util.*;

public class Sumandos {
  /*
   * Implementar:
   * 1. Encontrar los sumandos de n.
   * 2. Encontrar los sumandos iguales, tal que la suma sea n.
   * 3. Encontrar los sumandos diferentes, tal que la suma sea n.
   * 4. Encontrar los factores de n. Generar factores a partir de 2. n >= 2.
   * 5. Encontrar los factores iguales de n.
   * 6. Encontrar los factores diferentes de n.
   * 7. Plantear al menos 4 ejercicios adicionales diferentes e interesantes.
   */
  public static int c = 0;

  /*
   * 1. Encontrar los sumandos de n.
   */
  public static int suma(LinkedList<Integer> L1) {
    int suma = 0;
    for (int i = 0; i < L1.size(); i++) {
      suma += L1.get(i);
    }
    return suma;
  }

  public static void sumandos(LinkedList<Integer> L1, int n, int i) {
    int sum = suma(L1);
    if (sum > n) {
      return;
    }
    if (sum == n) {
      System.out.println(L1);
      c += 1;
      return;
    }
    int k = i;
    while (k <= n) {
      L1.add(k);
      sumandos(L1, n, k);
      L1.removeLast();
      k = k + 1;
    }
  }

  /*
   * 2. Encontrar los sumandos iguales, tal que la suma sea n.
   */
  public static boolean todosIguales(LinkedList<Integer> L1) {
    int primero = L1.get(0);
    for (int i = 0; i < L1.size(); i++) {
      if (primero != L1.get(i)) {
        return false;
      }
    }
    return true;
  }

  public static void sumandosIguales(LinkedList<Integer> L1, int n, int i) {
    int sum = suma(L1);
    if (sum > n) {
      return;
    }
    if (sum == n) {
      if (todosIguales(L1)) {
        System.out.println(L1);
        c += 1;
      }
      return;
    }
    int k = i;
    while (k <= n) {
      L1.add(k);
      sumandosIguales(L1, n, k);
      L1.removeLast();
      k = k + 1;
    }
  }

  /*
   * 3. Encontrar los sumandos diferentes, tal que la suma sea n.
   */
  public static boolean todosDiferentes(LinkedList<Integer> L1) {
    for (int i = 0; i < L1.size(); i++) {
      for (int j = 0; j < L1.size(); j++) {
        if (i != j) {
          if (L1.get(i) == L1.get(j)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  public static void sumandosDiferentes(LinkedList<Integer> L1, int n, int i) {
    int sum = suma(L1);
    if (sum > n) {
      return;
    }
    if (sum == n) {
      if (todosDiferentes(L1)) {
        System.out.println(L1);
        c += 1;
      }
      return;
    }
    int k = i;
    while (k <= n) {
      L1.add(k);
      sumandosDiferentes(L1, n, k);
      L1.removeLast();
      k = k + 1;
    }
  }

  /*
   * 4. Encontrar los factores de n. Generar factores a partir de 2. n >= 2.
   */
  public static int multiplicacion(LinkedList<Integer> L1) {
    int factores = 1;
    for (int i = 0; i < L1.size(); i++) {
      factores *= L1.get(i);
    }
    return factores;
  }

  public static void factores(LinkedList<Integer> L1, int n, int i) {
    int mul = multiplicacion(L1);
    if (mul > n)
      return;
    if (mul == n) {
      System.out.println(L1);
      c += 1;
      return;
    }
    int k = i;
    while (k <= n) {
      L1.add(k);
      factores(L1, n, k);
      L1.removeLast();
      k = k + 1;
    }
  }

  /*
   * 5. Encontrar los factores iguales de n.
   */
  public static void factoresIguales(LinkedList<Integer> L1, int n, int i) {
    int mul = multiplicacion(L1);
    if (mul > n)
      return;
    if (mul == n) {
      if (todosIguales(L1)) {
        System.out.println(L1);
        c += 1;
      }
      return;
    }
    int k = i;
    while (k <= n) {
      L1.add(k);
      factoresIguales(L1, n, k);
      L1.removeLast();
      k = k + 1;
    }
  }

  /*
   * 6. Encontrar los factores diferentes de n.
   */
  public static void factoresDiferentes(LinkedList<Integer> L1, int n, int i) {
    int mul = multiplicacion(L1);
    if (mul > n)
      return;
    if (mul == n) {
      if (todosDiferentes(L1)) {
        System.out.println(L1);
        c += 1;
      }
      return;
    }
    int k = i;
    while (k <= n) {
      L1.add(k);
      factoresDiferentes(L1, n, k);
      L1.removeLast();
      k = k + 1;
    }
  }

  /*
   * 7. Plantear al menos 4 ejercicios adicionales diferentes e interesantes.
   */
  /*
   * 7.1. Encontrar los sumandos primos de n.
   */
  public static boolean tieneDividores(int n, int ini, int fin) {
    if (ini > fin) {
      return false;
    }
    if (n % ini == 0) {
      return true;
    }
    return tieneDividores(n, ini + 1, fin);
  }

  public static boolean esPrimo(int n) {
    if (n < 2)
      return false;
    return !tieneDividores(n, 2, n - 1);
  }

  public static boolean todosPrimos(LinkedList<Integer> L1) {
    for (int i = 0; i < L1.size(); i++) {
      if (!esPrimo(L1.get(i)))
        return false;
    }
    return true;
  }

  public static void sumandosPrimos(LinkedList<Integer> L1, int n, int i) {
    int sum = suma(L1);
    if (sum > n) {
      return;
    }
    if (sum == n) {
      if (todosPrimos(L1)) {
        System.out.println(L1);
        c += 1;
      }
      return;
    }
    int k = i;
    while (k <= n) {
      L1.add(k);
      sumandosPrimos(L1, n, k);
      L1.removeLast();
      k = k + 1;
    }
  }

  /*
   * 7.2. Encontrar los sumandos pares de n.
   */
  public static boolean esPar(int n) {
    return n % 2 == 0;
  }

  public static boolean todosPares(LinkedList<Integer> L1) {
    for (Integer integer : L1) {
      if (!esPar(integer))
        return false;
    }
    return true;
  }

  public static void sumandosPares(LinkedList<Integer> L1, int n, int i) {
    int sum = suma(L1);
    if (sum > n) {
      return;
    }
    if (sum == n) {
      if (todosPares(L1)) {
        System.out.println(L1);
        c += 1;
      }
      return;
    }
    int k = i;
    while (k <= n) {
      L1.add(k);
      sumandosPares(L1, n, k);
      L1.removeLast();
      k = k + 1;
    }
  }

  /*
   * 7.3. Encontrar los sumandos cuadrados de n.
   */

  public static boolean esCuadrado(int n) {
    int raizCua = (int) Math.sqrt(n);
    return raizCua * raizCua == n;
  }

  public static boolean todosCuadrados(LinkedList<Integer> L1) {
    for (int i = 0; i < L1.size(); i++) {
      if (!esCuadrado(L1.get(i)))
        return false;
    }
    return true;
  }

  public static void sumandosCuadrados(LinkedList<Integer> L1, int n, int i) {
    int sum = suma(L1);
    if (sum > n) {
      return;
    }
    if (sum == n) {
      if (todosCuadrados(L1)) {
        System.out.println(L1);
        c += 1;
      }
      return;
    }
    int k = i;
    while (k <= n) {
      L1.add(k);
      sumandosCuadrados(L1, n, k);
      L1.removeLast();
      k = k + 1;
    }
  }

  /*
   * 7.4. Encontrar los sumandos fibonacci de n. (Todos los números que pertenecen
   * a la secuencia de fibonnaci)
   */
  public static boolean esFibonacci(int n) {
    int n1 = (5 * n * n) + 4;
    int n2 = (5 * n * n) - 4;
    return esCuadrado(n1) || esCuadrado(n2);
  }

  public static boolean todosFibonacci(LinkedList<Integer> L1) {
    for (int i = 0; i < L1.size(); i++) {
      if (!esFibonacci(L1.get(i)))
        return false;
    }
    return true;
  }

  public static void sumandosFibonaccis(LinkedList<Integer> L1, int n, int i) {
    int sum = suma(L1);
    if (sum > n) {
      return;
    }
    if (sum == n) {
      if (todosFibonacci(L1)) {
        System.out.println(L1);
        c += 1;
      }
      return;
    }
    int k = i;
    while (k <= n) {
      L1.add(k);
      sumandosFibonaccis(L1, n, k);
      L1.removeLast();
      k = k + 1;
    }
  }
}
