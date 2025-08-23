package tarea3;

import java.util.*;

public class Mochila {
  /*
   * II. EL PROBLEMA DE LA MOCHILA DE CAPACIDAD MAX
   * 
   * A. Implementar los siguientes problemas: ingresando los pesos de objetos en
   * una Lista de Enteros L1 y mostrando la combinación de objetos en una Lista de
   * Enteros L2 y utilizando un algoritmo de la forma de llamada recursiva dentro
   * de un ciclo.
   * 
   * 1. Encontrar todas las combinaciones de pesos de objetos que se pueden
   * transportar en la mochila.
   * 2. Encontrar todas las combinaciones de pesos diferentes que se pueden
   * transportar en la mochila.
   * 3. Encontrar todas las combinaciones de pesos entre a y b inclusive que se
   * pueden transportar en la mochila.
   * 4. Encontrar las combinaciones de objetos de mayor cantidad de objetos que se
   * pueden transportar.
   * 5. Encontrar las mejores combinaciones que se pueden transportar en la
   * mochila.
   * (Las más próximas a la capacidad de la mochila)
   * 6. Implementar las consultas anteriores, utilizando una Lista de Listas y
   * plantear consultas adicionales interesantes..
   */
  private static int suma(LinkedList<Integer> L1) {
    int suma = 0;
    for (int i = 0; i < L1.size(); i++) {
      suma += L1.get(i);
    }
    return suma;
  }

  /*
   * 1. Encontrar todas las combinaciones de pesos de objetos que se pueden
   * transportar en la mochila.
   */
  public static void mochila(
      LinkedList<Integer> L1,
      LinkedList<Integer> L2,
      LinkedList<LinkedList<Integer>> L3,
      int max,
      int i // indice de L1(pesos)
  ) {
    int sum = suma(L2);
    if (sum <= max) {
      L3.add(new LinkedList<Integer>(L2));
    }
    int k = i;
    while (k < L1.size()) {
      L2.add(L1.get(k));
      mochila(L1, L2, L3, max, k + 1);
      L2.removeLast();
      k = k + 1;
    }
  }

  /*
   * 2. Encontrar todas las combinaciones de pesos diferentes que se pueden
   * transportar en la mochila.
   */
  private static boolean todosDiferentes(LinkedList<Integer> L) {
    for (int i = 0; i < L.size(); i++) {
      for (int j = 0; j < L.size(); j++) {
        if (i != j) {
          if (L.get(i).equals(L.get(j))) {
            return false;
          }
        }
      }
    }
    return true;
  }

  public static void mochilaDiferentes(LinkedList<Integer> L1, LinkedList<Integer> L2,
      LinkedList<LinkedList<Integer>> L3,
      int max, int i) {
    int sum = suma(L2);
    if (sum <= max) {
      if (todosDiferentes(L2)) {
        L3.add(new LinkedList<Integer>(L2));
      }
    }
    int k = i;
    while (k < L1.size()) {
      L2.add(L1.get(k));
      mochilaDiferentes(L1, L2, L3, max, k + 1);
      L2.removeLast();
      k = k + 1;
    }
  }

  /*
   * 3. Encontrar todas las combinaciones de pesos entre a y b inclusive que se
   * pueden transportar en la mochila.
   */
  public static void mochilaEntreAB(LinkedList<Integer> L1, LinkedList<Integer> L2,
      LinkedList<LinkedList<Integer>> L3,
      int max, int a, int b) {
    int sum = suma(L2);
    if (sum <= max) {
      if (todosDiferentes(L2)) {
        L3.add(new LinkedList<Integer>(L2));
      }
    }
    int k = a;
    while (k <= b) {
      L2.add(L1.get(k));
      mochilaEntreAB(L1, L2, L3, max, k + 1, b);
      L2.removeLast();
      k = k + 1;
    }
  }

  /*
   * 4. Encontrar las combinaciones de objetos de mayor cantidad de objetos que se
   * pueden transportar.
   */
  public static void mochilaMayorCantidad(LinkedList<Integer> L1, LinkedList<Integer> L2,
      LinkedList<LinkedList<Integer>> L3, int max, int idx) {
    mochila(L1, L2, L3, max, 0);
    int mayorCantidad = 0;
    LinkedList<LinkedList<Integer>> aux = new LinkedList<LinkedList<Integer>>();
    for (LinkedList<Integer> lista : L3) {
      if (lista.size() > mayorCantidad) {
        mayorCantidad = lista.size();
        aux.clear();
        aux.add(lista);
      } else if (lista.size() == mayorCantidad) {
        aux.add(lista);
      }
    }
    L3.clear();
    L3.addAll(aux);
  }

  /*
   * 5. Encontrar las mejores combinaciones que se pueden transportar en la
   * mochila.
   * (Las más próximas a la capacidad de la mochila)
   */
  public static void mochilaMejores(LinkedList<Integer> L1, LinkedList<Integer> L2,
      LinkedList<LinkedList<Integer>> L3, int max, int idx) {
    mochila(L1, L2, L3, max, 0);

    int mejorSuma = 0;
    LinkedList<LinkedList<Integer>> aux = new LinkedList<>();

    for (LinkedList<Integer> lista : L3) {
      int suma = suma(lista);
      if (suma > mejorSuma && suma <= max) {
        mejorSuma = suma;
        aux.clear();
        aux.add(lista);
      } else if (suma == mejorSuma) {
        aux.add(lista);
      }
    }

    L3.clear();
    L3.addAll(aux);
  }
  /*
   * 6. Implementar las consultas anteriores, utilizando una Lista de Listas y
   * plantear consultas adicionales interesantes..
   */

  /*
   * 6.1. Encontrar las combinaciones de pesos pares que se pueden transportar en
   * la mochila.
   */
  private static boolean todosPares(LinkedList<Integer> L) {
    for (int i = 0; i < L.size(); i++) {
      if (!esPar(L.get(i)))
        return false;
    }
    return true;
  }

  public static void mochilaPares(LinkedList<Integer> L1, LinkedList<Integer> L2,
      LinkedList<LinkedList<Integer>> L3,
      int max, int i) {
    int sum = suma(L2);
    if (sum <= max) {
      if (todosPares(L2)) {
        L3.add(new LinkedList<Integer>(L2));
      }
    }
    int k = i;
    while (k < L1.size()) {
      L2.add(L1.get(k));
      mochilaPares(L1, L2, L3, max, k + 1);
      L2.removeLast();
      k = k + 1;
    }
  }

  /*
   * 6.2. Encontrar las combinaciones de pesos cuadrados que se pueden transportar
   * en la mochila.
   */
  private static boolean esCuadrado(int n) {
    int raiz = (int) Math.sqrt(n);
    return raiz * raiz == n;
  }

  private static boolean todosCuadrados(LinkedList<Integer> L) {
    for (int i = 0; i < L.size(); i++) {
      if (!esCuadrado(L.get(i))) {
        return false;
      }
    }
    return true;
  }

  public static void mochilaCuadrados(LinkedList<Integer> L1, LinkedList<Integer> L2,
      LinkedList<LinkedList<Integer>> L3,
      int max, int i) {
    int sum = suma(L2);
    if (sum <= max) {
      if (todosCuadrados(L2)) {
        L3.add(new LinkedList<Integer>(L2));
      }
    }
    int k = i;
    while (k < L1.size()) {
      L2.add(L1.get(k));
      mochilaCuadrados(L1, L2, L3, max, k + 1);
      L2.removeLast();
      k = k + 1;
    }
  }

  /*
   * 6.3. Encontrar las combinaciones de pesos fibonacci que se pueden transportar
   * en la mochila.
   */
  private static boolean esFibonacci(int n) {
    int n1 = (5 * n * n) + 4;
    int n2 = (5 * n * n) - 4;
    return esCuadrado(n1) || esCuadrado(n2);
  }

  private static boolean todosFibonacci(LinkedList<Integer> L) {
    for (int i = 0; i < L.size(); i++) {
      if (!esFibonacci(L.get(i)))
        return false;
    }
    return true;
  }

  public static void mochilaFibonacci(LinkedList<Integer> L1, LinkedList<Integer> L2,
      LinkedList<LinkedList<Integer>> L3,
      int max, int i) {
    int sum = suma(L2);
    if (sum <= max) {
      if (todosFibonacci(L2)) {
        L3.add(new LinkedList<Integer>(L2));
      }
    }
    int k = i;
    while (k < L1.size()) {
      L2.add(L1.get(k));
      mochilaFibonacci(L1, L2, L3, max, k + 1);
      L2.removeLast();
      k = k + 1;
    }
  }

  /*
   * B. Con la misma lógica del Problema de la Mochila, los objetos tienen al
   * menos los siguientes dos valores: peso y color. Implementar:
   * 
   * 1. Encontrar todas las combinaciones de pesos de objetos que se pueden
   * transportar en la mochila.
   * 2. Encontrar todas las combinaciones de pesos de objetos que se pueden
   * transportar en la mochila del mismo color.
   * 3. Encontrar todas las combinaciones de pesos de objetos que se pueden
   * transportar en la mochila, todos de colores diferentes.
   * 4. Encontrar todas las combinaciones de pesos de objetos que se pueden
   * transportar en la mochila, que sean del mismo peso y el mismo color.
   * 5. Encontrar todas las combinaciones de pesos de objetos que se pueden
   * transportar en la mochila que sean de pesos diferentes y colores diferentes.
   * 6. Implementar las consultas anteriores, utilizando una Lista de Listas y
   * plantear consultas adicionales interesantes.
   */
  public static class Objeto {
    int peso;
    String color;

    public Objeto(int peso, String color) {
      this.peso = peso;
      this.color = color;
    }

    @Override
    public String toString() {
      return "(" + peso + "," + color + ")";
    }
  }

  /*
   * 1. Encontrar todas las combinaciones de pesos de objetos que se pueden
   * transportar en la mochila.
   */
  private static int sumaPeso(LinkedList<Objeto> L) {
    int suma = 0;
    for (Objeto objeto : L) {
      suma += objeto.peso;
    }
    return suma;
  }

  public static void mochila(
      List<Objeto> L1,
      LinkedList<Objeto> L2,
      LinkedList<LinkedList<Objeto>> L3,
      int maxP,
      int i) {

    int p = sumaPeso(L2);
    if (p <= maxP) {
      L3.add(new LinkedList<Objeto>(L2));
    }
    int k = i;
    while (k < L1.size()) {
      L2.add(L1.get(k));
      mochila(L1, L2, L3, maxP, k + 1);
      L2.removeLast();
      k++;
    }
  }

  /*
   * 2. Encontrar todas las combinaciones de pesos de objetos que se pueden
   * transportar en la mochila del mismo color.
   */
  private static boolean todosColoresIguales(LinkedList<Objeto> L) {
    if (L.isEmpty())
      return true;
    Objeto o1 = L.get(0);
    for (int i = 1; i < L.size(); i++) {
      if (!L.get(i).color.equals(o1.color))
        return false;
    }
    return true;
  }

  public static void mochilaColoresIguales(
      List<Objeto> L1,
      LinkedList<Objeto> L2,
      LinkedList<LinkedList<Objeto>> L3,
      int maxP,
      int i) {

    int p = sumaPeso(L2);
    if (p <= maxP) {
      if (todosColoresIguales(L2)) {
        L3.add(new LinkedList<Objeto>(L2));
      }
    }
    int k = i;
    while (k < L1.size()) {
      L2.add(L1.get(k));
      mochilaColoresIguales(L1, L2, L3, maxP, k + 1);
      L2.removeLast();
      k++;
    }
  }

  /*
   * 3. Encontrar todas las combinaciones de pesos de objetos que se pueden
   * transportar en la mochila, todos de colores diferentes.
   */
  private static boolean todosColoresDiferentes(LinkedList<Objeto> L) {
    for (int i = 0; i < L.size(); i++) {
      for (int j = 0; j < L.size(); j++) {
        if (i != j) {
          if (L.get(i).color.equals(L.get(j).color))
            return false;
        }
      }
    }
    return true;
  }

  public static void mochilaColoresDiferentes(
      List<Objeto> L1,
      LinkedList<Objeto> L2,
      LinkedList<LinkedList<Objeto>> L3,
      int maxP,
      int i) {

    int p = sumaPeso(L2);
    if (p <= maxP) {
      if (todosColoresDiferentes(L2)) {
        L3.add(new LinkedList<Objeto>(L2));
      }
    }
    int k = i;
    while (k < L1.size()) {
      L2.add(L1.get(k));
      mochilaColoresDiferentes(L1, L2, L3, maxP, k + 1);
      L2.removeLast();
      k++;
    }
  }

  /*
   * 4. Encontrar todas las combinaciones de pesos de objetos que se pueden
   * transportar en la mochila, que sean del mismo peso y el mismo color.
   */
  private static boolean todosPesosYColoresIguales(LinkedList<Objeto> L) {
    if (L.isEmpty())
      return true;
    Objeto o1 = L.get(0);
    for (int i = 1; i < L.size(); i++) {
      if (!L.get(i).color.equals(o1.color) || L.get(i).peso != o1.peso)
        return false;
    }
    return true;
  }

  public static void mochilaMismoPesoYColor(
      List<Objeto> L1,
      LinkedList<Objeto> L2,
      LinkedList<LinkedList<Objeto>> L3,
      int maxP,
      int i) {

    int p = sumaPeso(L2);
    if (p <= maxP) {
      if (todosPesosYColoresIguales(L2)) {
        L3.add(new LinkedList<Objeto>(L2));
      }
    }
    int k = i;
    while (k < L1.size()) {
      L2.add(L1.get(k));
      mochilaMismoPesoYColor(L1, L2, L3, maxP, k + 1);
      L2.removeLast();
      k++;
    }
  }

  /*
   * 5. Encontrar todas las combinaciones de pesos de objetos que se pueden
   * transportar en la mochila que sean de pesos diferentes y colores diferentes.
   */
  private static boolean todosPesosYColoresDiferentes(LinkedList<Objeto> L) {
    for (int i = 0; i < L.size(); i++) {
      for (int j = 0; j < L.size(); j++) {
        if (i != j) {
          if (L.get(i).color.equals(L.get(j).color))
            return false;
          if (L.get(i).peso == L.get(j).peso)
            return false;
        }
      }
    }
    return true;
  }

  public static void mochilaPesosYColoresDiferentes(
      List<Objeto> L1,
      LinkedList<Objeto> L2,
      LinkedList<LinkedList<Objeto>> L3,
      int maxP,
      int i) {

    int p = sumaPeso(L2);
    if (p <= maxP) {
      if (todosPesosYColoresDiferentes(L2)) {
        L3.add(new LinkedList<Objeto>(L2));
      }
    }
    int k = i;
    while (k < L1.size()) {
      L2.add(L1.get(k));
      mochilaPesosYColoresDiferentes(L1, L2, L3, maxP, k + 1);
      L2.removeLast();
      k++;
    }
  }

  /*
   * 6. Implementar las consultas anteriores, utilizando una Lista de Listas y
   * plantear consultas adicionales interesantes.
   */

  /*
   * 6.1. Encontrar las combinaciones de pesos pares que se pueden transportar en
   * la mochila.
   */
  private static boolean esPar(int n) {
    return n % 2 == 0;
  }

  private static boolean todosPesosPares(LinkedList<Objeto> L) {
    for (Objeto objeto : L) {
      if (!esPar(objeto.peso))
        return false;
    }
    return true;
  }

  public static void mochilaPesosPares(
      List<Objeto> L1,
      LinkedList<Objeto> L2,
      LinkedList<LinkedList<Objeto>> L3,
      int maxP,
      int i) {

    int p = sumaPeso(L2);
    if (p <= maxP) {
      if (todosPesosPares(L2)) {
        L3.add(new LinkedList<Objeto>(L2));
      }
    }
    int k = i;
    while (k < L1.size()) {
      L2.add(L1.get(k));
      mochilaPesosPares(L1, L2, L3, maxP, k + 1);
      L2.removeLast();
      k++;
    }
  }

  /*
   * 6.2. Encontrar las combinaciones de pesos cuadrados que se pueden transportar
   * en la mochila.
   */

  private static boolean todosPesosCuadrados(LinkedList<Objeto> L1) {
    for (Objeto objeto : L1) {
      if (!esCuadrado(objeto.peso))
        return false;
    }
    return true;
  }

  public static void mochilaPesosCuadrados(
      List<Objeto> L1,
      LinkedList<Objeto> L2,
      LinkedList<LinkedList<Objeto>> L3,
      int maxP,
      int i) {

    int p = sumaPeso(L2);
    if (p <= maxP) {
      if (todosPesosCuadrados(L2)) {
        L3.add(new LinkedList<Objeto>(L2));
      }
    }
    int k = i;
    while (k < L1.size()) {
      L2.add(L1.get(k));
      mochilaPesosCuadrados(L1, L2, L3, maxP, k + 1);
      L2.removeLast();
      k++;
    }
  }

  /*
   * 6.3. Encontrar las combinaciones de pesos fibonacci que se pueden transportar
   * en la mochila.
   */
  private static boolean todosPesosFibonacci(LinkedList<Objeto> L1) {
    for (Objeto objeto : L1) {
      if (!esFibonacci(objeto.peso))
        return false;
    }
    return true;
  }

  public static void mochilaPesosFibonacci(
      List<Objeto> L1,
      LinkedList<Objeto> L2,
      LinkedList<LinkedList<Objeto>> L3,
      int maxP,
      int i) {

    int p = sumaPeso(L2);
    if (p <= maxP) {
      if (todosPesosFibonacci(L2)) {
        L3.add(new LinkedList<Objeto>(L2));
      }
    }
    int k = i;
    while (k < L1.size()) {
      L2.add(L1.get(k));
      mochilaPesosFibonacci(L1, L2, L3, maxP, k + 1);
      L2.removeLast();
      k++;
    }
  }
}
