package tema3.tarea1;

import java.util.*;

public class NReinas {
  public static class Regla {

    public int fil;
    public int col;

    public Regla(int i, int j) {
      this.fil = i;
      this.col = j;
    }
  }

  public static void mostrar(int[][] m) {
    String s = "";
    for (int i = 0; i < m.length; i++) {
      for (int j = 0; j < m[i].length; j++) {
        s = s + m[i][j] + "\t";
      }
      s = s + "\n";
    }
    System.out.println(s);
  }

  public static boolean estanRectasVacias(int[][] m, int i, int j) {
    for (int k = 0; k < m.length; k++) {
      if (m[i][k] != 0)
        return false;
    }
    for (int k = 0; k < m.length; k++) {
      if (m[k][j] != 0)
        return false;
    }
    return true;
  }

  public static boolean estanDiagonalesVacias(int[][] m, int i, int j) {
    for (int k1 = i - 1, k2 = j - 1; k1 >= 0 && k2 >= 0; k1--, k2--) {
      if (m[k1][k2] != 0)
        return false;
    }
    for (int k1 = i + 1, k2 = j - 1; k1 < m.length && k2 >= 0; k1++, k2--) {
      if (m[k1][k2] != 0)
        return false;
    }

    for (int k1 = i + 1, k2 = j + 1; k1 < m.length && k2 < m[k1].length; k1++, k2++) {
      if (m[k1][k2] != 0)
        return false;
    }

    for (int k1 = i - 1, k2 = j + 1; k1 >= 0 && k2 < m[k1].length; k1--, k2++) {
      if (m[k1][k2] != 0)
        return false;
    }
    return true;
  }

  public static boolean hayDamaEnRango(int[][] m, int i, int j) {
    return !estanDiagonalesVacias(m, i, j) || !estanRectasVacias(m, i, j);
  }

  public static boolean posValida(int[][] m, int i, int j) {
    return i >= 0 && i < m.length && j >= 0 && j < m[i].length && m[i][j] == 0 && !hayDamaEnRango(m, i, j);
  }

  public static Regla elegirReglaA(LinkedList<Regla> L1, int[][] m) {
    return L1.removeFirst();
  }

  public static Regla elegirReglaB(LinkedList<Regla> L1, int[][] m) {
    return L1.remove((L1.size() - 1) / 2);
  }

  public static LinkedList<Regla> reglasAplicablesDama(int[][] m, int paso) {
    LinkedList<Regla> L1 = new LinkedList<>();
    for (int k = 0; k < m[paso].length; k++) {
      if (posValida(m, paso, k))
        L1.add(new Regla(paso, k));
    }

    return L1;
  }

  public static int vueltas = 0;

  public static boolean nReinasSinHeuristica(int[][] m, int paso) {
    if (paso > m.length)
      return true;

    LinkedList<Regla> L1 = reglasAplicablesDama(m, paso - 1);
    while (!L1.isEmpty()) {
      Regla R = elegirReglaA(L1, m);
      m[R.fil][R.col] = paso;
      if (nReinasSinHeuristica(m, paso + 1)) {
        return true;
      }
      m[R.fil][R.col] = 0;
      vueltas++;
    }
    return false;
  }

  public static boolean nReinasConHeuristica(int[][] m, int paso) {
    if (paso > m.length)
      return true;

    LinkedList<Regla> L1 = reglasAplicablesDama(m, paso - 1);
    while (!L1.isEmpty()) {
      Regla R = elegirReglaB(L1, m);
      m[R.fil][R.col] = paso;
      if (nReinasConHeuristica(m, paso + 1)) {
        return true;
      }
      m[R.fil][R.col] = 0;
      vueltas++;
    }
    return false;
  }

  /*
   * Este problema consiste en ubicar n reinas en un tablero de ajedrez de n x n.
   * Tal que, entre las reinas no se puedan atrapar. Para propósitos metódicos,
   * ubicaremos las reinas desde la primera fila, hasta completar en la última
   * fila.
   * 
   * Implementar el Algoritmo y ejecutar de dos formas:
   * 
   * Sin información heurística. (Elegir y eliminar la primera Regla)
   * Con información heurística. (Elegir y eliminar la mejor Regla)
   * Para ambos casos, registrar la cantidad de vuelvas que realiza el algoritmo
   * hasta encontrar la solución.
   * 
   * Ejecutar para varios valores de n sucesivamente, n = 4, 5, 6, 7, . . . . . .
   * Para cada valor de n registrar la cantidad de vueltas con y sin heurística.
   * Graficar curvas de tendencias.
   * 
   * Entrenar al chatGPT, sobre este problema y solicitarle otras heurísticas
   * interesantes para implementar (al menos 3) y ejecutar con esas heurísticas la
   * resolución de problema y verificar que se llega al objetivo con menor
   * cantidad de vueltas, según aumenta el valor de n. (ejecutar sucesivamente
   * para n-grande).
   */
  // 1
  // elige la regla más al centro de la matriz
  public static Regla elegirReclaC(LinkedList<Regla> L1, int[][] m) {
    double centro = (m.length - 1) / 2;
    double distMenor = Double.MAX_VALUE;
    int posMenor = 0;

    for (int i = 0; i < L1.size(); i++) {
      Regla R = L1.get(i);
      double dist = Math.abs(R.col - centro);

      if (dist < distMenor) {
        distMenor = dist;
        posMenor = i;
      }
    }

    return L1.remove(posMenor);
  }

  public static boolean nReinasConHeuristica1(int[][] m, int paso) {
    if (paso > m.length)
      return true;

    LinkedList<Regla> L1 = reglasAplicablesDama(m, paso - 1);
    while (!L1.isEmpty()) {
      Regla R = elegirReclaC(L1, m);
      m[R.fil][R.col] = paso;
      if (nReinasConHeuristica1(m, paso + 1)) {
        return true;
      }
      m[R.fil][R.col] = 0;
      vueltas++;
    }
    return false;
  }

  // 2
  // Esta función auxiliar calcula el número de opciones válidas para la siguiente
  // fila
  public static int contarOpcionesFuturas(int[][] m, int fila) {
    if (fila >= m.length)
      return 0;

    int count = 0;
    for (int col = 0; col < m.length; col++) {
      if (posValida(m, fila, col)) {
        count++;
      }
    }
    return count;
  }

  public static Regla elegirReclaD(LinkedList<Regla> L1, int[][] m, int paso) {
    if (L1.isEmpty())
      return null;

    int minOpciones = Integer.MAX_VALUE;
    Regla mejorRegla = null;
    int indexMejor = -1;
    int filaActual = paso - 1;

    for (int i = 0; i < L1.size(); i++) {
      Regla R = L1.get(i);
      m[R.fil][R.col] = paso;
      int opcionesFuturas = contarOpcionesFuturas(m, filaActual + 1);
      m[R.fil][R.col] = 0;
      if (opcionesFuturas < minOpciones) {
        minOpciones = opcionesFuturas;
        mejorRegla = R;
        indexMejor = i;
      }
    }

    if (indexMejor != -1) {
      L1.remove(indexMejor);
    }
    return mejorRegla;
  }

  public static boolean nReinasConHeuristica2(int[][] m, int paso) {
    if (paso > m.length)
      return true;

    LinkedList<Regla> L1 = reglasAplicablesDama(m, paso - 1);

    while (!L1.isEmpty()) {
      Regla R = elegirReclaD(L1, m, paso);
      m[R.fil][R.col] = paso;
      if (nReinasConHeuristica2(m, paso + 1)) {
        return true;
      }
      m[R.fil][R.col] = 0;
      vueltas++;
    }
    return false;
  }

  // 3
  // Encuentra la solución constructiva sin backtracking vueltas = 0
  public static boolean nReinasConHeuristica3(int[][] m, int paso) {
    int n = m.length;
    if (n == 2 || n == 3)
      return false;

    int[] cols = new int[n];
    int idx = 0;

    // Lista de pares primero
    for (int i = 2; i <= n; i += 2) {
      cols[idx++] = i - 1;
    }
    // Luego impares
    for (int i = 1; i <= n; i += 2) {
      cols[idx++] = i - 1;
    }
    for (int fila = 0; fila < n; fila++) {
      m[fila][cols[fila]] = fila + 1;
    }

    vueltas = 0;
    return true;
  }
}
