package tema2.tarea3;

import java.util.*;

public class Laberinto {
  public static class Regla {

    public int fil;
    public int col;

    public Regla(int i, int j) {
      this.fil = i;
      this.col = j;
    }
  }

  public static boolean posValida(int[][] m, int i, int j) {
    return i >= 0 && i < m.length && j >= 0 && j < m[i].length && m[i][j] == 0;
  }

  public static LinkedList<Regla> reglasAplicables(int[][] m, int i, int j) {
    LinkedList<Regla> L1 = new LinkedList<>();
    int j1 = j - 1;
    while (posValida(m, i, j1)) {
      L1.add(new Regla(i, j1));
      j1 = j1 - 1;
    }
    int i1 = i - 1;
    while (posValida(m, i1, j)) {
      L1.add(new Regla(i1, j));
      i1 = i1 - 1;
    }

    j1 = j + 1;
    while (posValida(m, i, j1)) {
      L1.add(new Regla(i, j1));
      j1 = j1 + 1;
    }
    i1 = i + 1;
    while (posValida(m, i1, j)) {
      L1.add(new Regla(i1, j));
      i1 = i1 + 1;
    }
    return L1;
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

  public static Regla elegirRegla(LinkedList<Regla> L1) {
    return L1.removeFirst();
  }

  /*
   * 
   * 1. Dado un Tablero de n x m casillas. Se tiene una posición inicial y final,
   * encontrar todos los caminos posibles del estado inicial al estado final
   * (extremo superior izquierdo al extremo inferior derecho) con los movimientos
   * de la TORRE. Implementar este algoritmo, para movimientos en Sentido HORARIO.
   * (Sin ATajos, todas las casillas se inicializan con valor de CERO)
   */

  /*
   * 
   * a) Algoritmo para mostrar todos los caminos posibles desde una posición
   * inicial a una posición final. Además, mostrar la cantidad de soluciones
   * posibles (Cantidad de caminos posibles de la posición inicial a la posición
   * final).
   */
  public static int c = 0;

  public static void laberintoA(int[][] m, int i, int j, int iFin, int jFin, int paso) {
    if (!posValida(m, i, j)) {
      return;
    }
    m[i][j] = paso;
    if (i == iFin && j == jFin) {
      mostrar(m);
      c++;
    }
    LinkedList<Regla> L1 = reglasAplicables(m, i, j);
    while (!L1.isEmpty()) {
      Regla R = elegirRegla(L1);
      laberintoA(m, R.fil, R.col, iFin, jFin, paso + 1);
      m[R.fil][R.col] = 0;
    }
  }

  /*
   * 
   * b) Algoritmo para mostrar todos los caminos posibles desde una posición
   * inicial a una posición final tal que se visiten todas las casillas de la
   * matriz. Además, mostrar la cantidad de soluciones posibles.
   */
  public static boolean tieneCeros(int[][] m) {
    for (int i = 0; i < m.length; i++) {
      for (int j = 0; j < m[i].length; j++) {
        if (m[i][j] == 0) {
          return true;
        }
      }
    }
    return false;
  }

  public static void laberintoB(int[][] m, int i, int j, int iFin, int jFin, int paso) {
    if (!posValida(m, i, j)) {
      return;
    }
    m[i][j] = paso;
    if (i == iFin && j == jFin) {
      if (!tieneCeros(m)) {
        mostrar(m);
        c++;
      }
    }
    LinkedList<Regla> L1 = reglasAplicables(m, i, j);
    while (!L1.isEmpty()) {
      Regla R = elegirRegla(L1);
      laberintoB(m, R.fil, R.col, iFin, jFin, paso + 1);
      m[R.fil][R.col] = 0;
    }
  }

  /*
   * 
   * c) Algoritmo para mostrar todos los caminos posibles desde una posición
   * inicial a una posición final tal que NO se visiten todas las casillas de la
   * matriz. Además, mostrar la cantidad de soluciones posibles.
   */

  public static void laberintoC(int[][] m, int i, int j, int iFin, int jFin, int paso) {
    if (!posValida(m, i, j)) {
      return;
    }
    m[i][j] = paso;
    if (i == iFin && j == jFin) {
      if (tieneCeros(m)) {
        mostrar(m);
        c++;
      }
    }
    LinkedList<Regla> L1 = reglasAplicables(m, i, j);
    while (!L1.isEmpty()) {
      Regla R = elegirRegla(L1);
      laberintoC(m, R.fil, R.col, iFin, jFin, paso + 1);
      m[R.fil][R.col] = 0;
    }
  }

  /*
   * 
   * d) Algoritmo para mostrar todos los caminos posibles de longitud mínima
   * (camino óptimo) desde una posición inicial a una posición final.. Además,
   * mostrar la cantidad de soluciones posibles.
   */
  public static void laberintoDAux(int[][] m, LinkedList<int[][]> L, int i, int j, int iFin, int jFin, int paso) {
    if (!posValida(m, i, j)) {
      return;
    }
    m[i][j] = paso;
    if (i == iFin && j == jFin) {
      L.add(Arrays.stream(m).map(fila -> fila.clone()).toArray($ -> m.clone()));
    }
    LinkedList<Regla> L1 = reglasAplicables(m, i, j);
    while (!L1.isEmpty()) {
      Regla R = elegirRegla(L1);
      laberintoDAux(m, L, R.fil, R.col, iFin, jFin, paso + 1);
      m[R.fil][R.col] = 0;
    }
  }

  public static int contarCeros(int[][] m) {
    int cantidad = 0;
    for (int i = 0; i < m.length; i++) {
      for (int j = 0; j < m[i].length; j++) {
        if (m[i][j] == 0)
          cantidad++;
      }
    }
    return cantidad;
  }

  public static void laberintoD(int[][] m, int i, int j, int iFin, int jFin, int paso) {
    LinkedList<int[][]> soluciones = new LinkedList<>();
    laberintoDAux(m, soluciones, i, j, iFin, jFin, paso);
    LinkedList<int[][]> solucionesMinimas = new LinkedList<>();

    // filtrar las soluciones que sean minimas
    if (soluciones.size() == 0)
      return;
    int maxCeros = contarCeros(soluciones.getFirst());
    for (int[][] sol : soluciones) {
      int ceros = contarCeros(sol);
      if (ceros > maxCeros) {
        maxCeros = ceros;
      }
    }

    // filtramos las soluciones que tengan esa máxima cantidad de ceros
    for (int[][] sol : soluciones) {
      if (contarCeros(sol) == maxCeros) {
        solucionesMinimas.add(sol);
        c++;
      }
    }

    // mostrar soluciones minimas
    for (int[][] sol : solucionesMinimas) {
      mostrar(sol);
    }
  }
}
