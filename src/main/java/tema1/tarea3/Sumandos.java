package tema1.tarea3;

import java.util.*;

public class Sumandos {
  /*
   * I. PROBLEMA DE LOS SUMANDOS DE UN ENTERO
   * 
   * Dado un entero N, encontrar todos los sumandos posibles, enteros positivos de
   * N.
   * 
   * 1. Encontrar los sumandos posibles en una Lista.
   * 2. Encontrar todos los sumandos posibles diferentes en una Lista.
   * 3. Encontrar todos los sumandos posibles iguales en una Lista.
   * 4. Encontrar todos los sumandos primos posibles en una Lista.
   * 5. Encontrar todos los sumandos entre a y b inclusive en una Lista.
   * 6. Implementar las consultas anteriores, utilizando una Lista de Listas
   * plantear consultas adicionales interesantes
   */
  public static int c = 0;

  /*
   * 1. Encontrar los sumandos posibles en una Lista.
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
   * 2. Encontrar todos los sumandos posibles diferentes en una Lista.
   */
  private static boolean todosDiferentes(LinkedList<Integer> L1) {
    for (int i = 0; i < L1.size(); i++) {
      for (int j = 0; j < L1.size(); j++) {
        if (i != j) {
          if (L1.get(i).equals(L1.get(j))) {
            return false;
          }
        }
      }
    }
    return true;
  }

  public static void sumandosDiferentes(LinkedList<Integer> L1, LinkedList<LinkedList<Integer>> L2, int n, int i) {
    int sum = suma(L1);
    if (sum > n) {
      return;
    }
    if (sum == n) {
      if (todosDiferentes(L1)) {
        L2.add(new LinkedList<Integer>(L1));
      }
      return;
    }
    int k = i;
    while (k <= n) {
      L1.add(k);
      sumandosDiferentes(L1, L2, n, k);
      L1.removeLast();
      k = k + 1;
    }
  }

  /*
   * 3. Encontrar todos los sumandos posibles iguales en una Lista.
   */
  private static boolean todosIguales(LinkedList<Integer> L1) {
    int primero = L1.get(0);
    for (int i = 0; i < L1.size(); i++) {
      if (primero != L1.get(i)) {
        return false;
      }
    }
    return true;
  }

  public static void sumandosIguales(LinkedList<Integer> L1, LinkedList<LinkedList<Integer>> L2, int n, int i) {
    int sum = suma(L1);
    if (sum > n) {
      return;
    }
    if (sum == n) {
      if (todosIguales(L1)) {
        L2.add(new LinkedList<Integer>(L1));
      }
      return;
    }
    int k = i;
    while (k <= n) {
      L1.add(k);
      sumandosIguales(L1, L2, n, k);
      L1.removeLast();
      k = k + 1;
    }
  }

  /*
   * 4. Encontrar todos los sumandos primos posibles en una Lista.
   */
  private static boolean tieneDividores(int n, int ini, int fin) {
    if (ini > fin) {
      return false;
    }
    if (n % ini == 0) {
      return true;
    }
    return tieneDividores(n, ini + 1, fin);
  }

  private static boolean esPrimo(int n) {
    if (n < 2)
      return false;
    return !tieneDividores(n, 2, n - 1);
  }

  private static boolean todosPrimos(LinkedList<Integer> L1) {
    for (int i = 0; i < L1.size(); i++) {
      if (!esPrimo(L1.get(i)))
        return false;
    }
    return true;
  }

  public static void sumandosPrimos(LinkedList<Integer> L1, LinkedList<LinkedList<Integer>> L2, int n, int i) {
    int sum = suma(L1);
    if (sum > n) {
      return;
    }
    if (sum == n) {
      if (todosPrimos(L1)) {
        L2.add(new LinkedList<Integer>(L1));
      }
      return;
    }
    int k = i;
    while (k <= n) {
      L1.add(k);
      sumandosPrimos(L1, L2, n, k);
      L1.removeLast();
      k = k + 1;
    }
  }

  /*
   * 5. Encontrar todos los sumandos entre a y b inclusive en una Lista.
   */
  public static void sumandosEntreAB(LinkedList<Integer> L1, LinkedList<LinkedList<Integer>> L2, int n, int a, int b) {
    int sum = suma(L1);
    if (sum > n) {
      return;
    }
    if (sum == n) {
      if (todosPrimos(L1)) {
        L2.add(new LinkedList<Integer>(L1));
      }
      return;
    }
    int k = a;
    while (k <= b) {
      L1.add(k);
      sumandosEntreAB(L1, L2, n, k, b);
      L1.removeLast();
      k = k + 1;
    }
  }

  /*
   * 6. Implementar las consultas anteriores, utilizando una Lista de Listas
   * plantear consultas adicionales interesantes
   */
  /*
   * 6.1. Encontrar los sumandos pares de n.
   */
  private static boolean esPar(int n) {
    return n % 2 == 0;
  }

  private static boolean todosPares(LinkedList<Integer> L1) {
    for (Integer integer : L1) {
      if (!esPar(integer))
        return false;
    }
    return true;
  }

  public static void sumandosPares(LinkedList<Integer> L1, LinkedList<LinkedList<Integer>> L2, int n, int i) {
    int sum = suma(L1);
    if (sum > n) {
      return;
    }
    if (sum == n) {
      if (todosPares(L1)) {
        L2.add(new LinkedList<Integer>(L1));
      }
      return;
    }
    int k = i;
    while (k <= n) {
      L1.add(k);
      sumandosPares(L1, L2, n, k);
      L1.removeLast();
      k = k + 1;
    }
  }

  /*
   * 6.2. Encontrar los sumandos cuadrados de n.
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
   * 6.3. Encontrar los sumandos fibonacci de n. (Todos los números que pertenecen
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
   * Dado un entero N, encontrar todos los factores posibles, enteros positivos de
   * N.
   * 
   * 1. Encontrar los factores posibles en una Lista.
   * 2. Encontrar todos los factores posibles diferentes en una Lista.
   * 3. Encontrar todos los factores posibles iguales en una Lista.
   * 4. Encontrar todos los factores primos posibles en una Lista.
   * 5. Encontrar todos los factores entre a y b inclusive en una Lista.
   * 6. Implementar las consultas anteriores, utilizando una Lista de Listas y
   * plantear consultas adicionales interesantes.
   */

  private static int multiplicacion(LinkedList<Integer> L1) {
    int factores = 1;
    for (int i = 0; i < L1.size(); i++) {
      factores *= L1.get(i);
    }
    return factores;
  }

  /*
   * 1. Encontrar los factores posibles en una Lista.
   */
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
   * 2. Encontrar todos los factores posibles diferentes en una Lista.
   */
  public static void factoresDiferentes(LinkedList<Integer> L1, LinkedList<LinkedList<Integer>> L2, int n, int i) {
    int mul = multiplicacion(L1);
    if (mul > n) {
      return;
    }
    if (mul == n) {
      if (todosDiferentes(L1)) {
        L2.add(new LinkedList<Integer>(L1));
      }
      return;
    }
    int k = i;
    while (k <= n) {
      L1.add(k);
      factoresDiferentes(L1, L2, n, k);
      L1.removeLast();
      k = k + 1;
    }
  }

  /*
   * 3. Encontrar todos los factores posibles iguales en una Lista.
   */
  public static void factoresIguales(LinkedList<Integer> L1, LinkedList<LinkedList<Integer>> L2, int n, int i) {
    int mul = multiplicacion(L1);
    if (mul > n) {
      return;
    }
    if (mul == n) {
      if (todosIguales(L1)) {
        L2.add(new LinkedList<Integer>(L1));
      }
      return;
    }
    int k = i;
    while (k <= n) {
      L1.add(k);
      factoresIguales(L1, L2, n, k);
      L1.removeLast();
      k = k + 1;
    }
  }

  /*
   * 4. Encontrar todos los factores primos posibles en una Lista.
   */
  public static void factoresPrimos(LinkedList<Integer> L1, LinkedList<LinkedList<Integer>> L2, int n, int i) {
    int mul = multiplicacion(L1);
    if (mul > n) {
      return;
    }
    if (mul == n) {
      if (todosPrimos(L1)) {
        L2.add(new LinkedList<Integer>(L1));
      }
      return;
    }
    int k = i;
    while (k <= n) {
      L1.add(k);
      factoresPrimos(L1, L2, n, k);
      L1.removeLast();
      k = k + 1;
    }
  }

  /*
   * 5. Encontrar todos los sumandos entre a y b inclusive en una Lista.
   */
  public static void factoresEntreAB(LinkedList<Integer> L1, LinkedList<LinkedList<Integer>> L2, int n, int a, int b) {
    int mul = multiplicacion(L1);
    if (mul > n) {
      return;
    }
    if (mul == n) {
      if (todosPrimos(L1)) {
        L2.add(new LinkedList<Integer>(L1));
      }
      return;
    }
    int k = a;
    while (k <= b) {
      L1.add(k);
      factoresEntreAB(L1, L2, n, k, b);
      L1.removeLast();
      k = k + 1;
    }
  }

  /*
   * 6. Implementar las consultas anteriores, utilizando una Lista de Listas y
   * plantear consultas adicionales interesantes.
   */
  /*
   * 6.1. Encontrar los factores pares de n.
   */
  public static void factoresPares(LinkedList<Integer> L1, LinkedList<LinkedList<Integer>> L2, int n, int i) {
    int mul = multiplicacion(L1);
    if (mul > n) {
      return;
    }
    if (mul == n) {
      if (todosPares(L1)) {
        L2.add(new LinkedList<Integer>(L1));
      }
      return;
    }
    int k = i;
    while (k <= n) {
      L1.add(k);
      factoresPares(L1, L2, n, k);
      L1.removeLast();
      k = k + 1;
    }
  }

  /*
   * 6.1. Encontrar los factores cuadrados de n.
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
   * 6.3. Encontrar los factores fibonacci de n. (Todos los números que pertenecen
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
