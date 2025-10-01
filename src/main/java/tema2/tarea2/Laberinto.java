package tema2.tarea2;

import java.util.*;

public class Laberinto {
  /*
   * 1. Dado una matriz de n x m, inicialmente todas las posiciones con valores de
   * cero, avanzar las casillas en sentido horario con movimientos de izquierda,
   * arriba, derecha y abajo. Hacer Algoritmos para los siguientes:
   */
  /*
   * Implementar los siguientes Algoritmos, utilizando la Estructura de Código de
   * "LLamada Recursiva dentro de un Ciclo". Las posiciones a dónde se pueden
   * mover de una posición, traslatar a una Lista de Reglas.
   */
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
    if (posValida(m, i, j - 1)) {
      L1.add(new Regla(i, j - 1));
    }
    if (posValida(m, i - 1, j)) {
      L1.add(new Regla(i - 1, j));
    }
    if (posValida(m, i, j + 1)) {
      L1.add(new Regla(i, j + 1));
    }
    if (posValida(m, i + 1, j)) {
      L1.add(new Regla(i + 1, j));
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
   * a) Algoritmo para mostrar todos los caminos posibles desde una posición
   * inicial a una posición final. Además, mostrar la cantidad de soluciones
   * posibles (Cantidad de caminos posibles de la posición inicial a la posición
   * final).
   */
  public static int c = 0;

  public static void laberinto1A(int[][] m, int i, int j, int iFin, int jFin, int paso) {
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
      laberinto1A(m, R.fil, R.col, iFin, jFin, paso + 1);
      m[R.fil][R.col] = 0;
    }
  }

  /*
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

  public static void laberinto1B(int[][] m, int i, int j, int iFin, int jFin, int paso) {
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
      laberinto1B(m, R.fil, R.col, iFin, jFin, paso + 1);
      m[R.fil][R.col] = 0;
    }
  }

  /*
   * c) Algoritmo para mostrar todos los caminos posibles desde una posición
   * inicial a una posición final tal que NO se visiten todas las casillas de la
   * matriz. Además, mostrar la cantidad de soluciones posibles.
   */
  public static void laberinto1C(int[][] m, int i, int j, int iFin, int jFin, int paso) {
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
      laberinto1C(m, R.fil, R.col, iFin, jFin, paso + 1);
      m[R.fil][R.col] = 0;
    }
  }

  /*
   * d) Algoritmo para mostrar todos los caminos posibles de máxima longitud desde
   * una posición inicial a una posición final.. Además, mostrar la cantidad de
   * soluciones posibles.
   */
  public static int longitudMinima = Integer.MAX_VALUE;
  public static int longitudMaxima = Integer.MIN_VALUE;

  public static void laberinto1D(int[][] m, int i, int j, int iFin, int jFin, int paso) {
    if (!posValida(m, i, j)) {
      return;
    }
    m[i][j] = paso;
    if (i == iFin && j == jFin) {
      if (paso > longitudMaxima) {
        longitudMaxima = paso;
        c = 1;
        mostrar(m);
      } else if (paso == longitudMaxima) {
        c++;
        mostrar(m);
      }
    }
    LinkedList<Regla> L1 = reglasAplicables(m, i, j);
    while (!L1.isEmpty()) {
      Regla R = elegirRegla(L1);
      laberinto1D(m, R.fil, R.col, iFin, jFin, paso + 1);
      m[R.fil][R.col] = 0;
    }
  }

  /*
   * e) Algoritmo para mostrar todos los caminos posibles de mínima longitud desde
   * una posición inicial a una posición final. Además, mostrar la cantidad de
   * soluciones posibles.
   */
  /*
   * 2. Ejecutar para todos los incisos del Ejercicio 1, inicialmente con
   * posiciones con valor de cero (paso libre), valor de -1 (atajo o pared).
   * Analizar las salidas y escribir conclusiones.
   */
  public static boolean posValidaConParedes(int[][] m, int i, int j) {
    return i >= 0 && i < m.length && j >= 0 && j < m[i].length && m[i][j] != -1;
  }

  public static void laberinto2(int[][] m, int i, int j, int iFin, int jFin, int paso) {
    if (!posValidaConParedes(m, i, j)) {
      return;
    }
    if (m[i][j] == 0) {
      m[i][j] = paso;
      if (i == iFin && j == jFin) {
        mostrar(m);
        c++;
      }
      LinkedList<Regla> L1 = reglasAplicables(m, i, j);
      while (!L1.isEmpty()) {
        Regla R = elegirRegla(L1);
        laberinto2(m, R.fil, R.col, iFin, jFin, paso + 1);
        m[R.fil][R.col] = 0;
      }
    }
  }

  /*
   * 3. Implementar los ejercicios 2 y 3. Redefiniendo el movimiento en el
   * Laberinto, también se puede mover una casilla por las diagonales. (8
   * posibilidades de movimientos)
   */
  public static LinkedList<Regla> reglasAplicablesConDiagonales(int[][] m, int i, int j) {
    LinkedList<Regla> L1 = new LinkedList<>();
    if (posValida(m, i, j - 1)) {
      L1.add(new Regla(i, j - 1));
    }
    if (posValida(m, i - 1, j)) {
      L1.add(new Regla(i - 1, j));
    }
    if (posValida(m, i, j + 1)) {
      L1.add(new Regla(i, j + 1));
    }
    if (posValida(m, i + 1, j)) {
      L1.add(new Regla(i + 1, j));
    }
    if (posValida(m, i - 1, j - 1)) {
      L1.add(new Regla(i - 1, j - 1));
    }
    if (posValida(m, i - 1, j + 1)) {
      L1.add(new Regla(i - 1, j + 1));
    }
    if (posValida(m, i + 1, j + 1)) {
      L1.add(new Regla(i + 1, j + 1));
    }
    if (posValida(m, i + 1, j - 1)) {
      L1.add(new Regla(i + 1, j - 1));
    }
    return L1;
  }

  public static void laberinto3(int[][] m, int i, int j, int iFin, int jFin, int paso) {
    if (!posValida(m, i, j)) {
      return;
    }
    m[i][j] = paso;
    if (i == iFin && j == jFin) {
      mostrar(m);
      c++;
    }
    LinkedList<Regla> L1 = reglasAplicablesConDiagonales(m, i, j);
    while (!L1.isEmpty()) {
      Regla R = elegirRegla(L1);
      laberinto3(m, R.fil, R.col, iFin, jFin, paso + 1);
      m[R.fil][R.col] = 0;
    }
  }

  /*
   * 4. Implementar los ejercicios 2 y 3. Redefiniendo el movimiento en el
   * Laberinto, solo por la diagonales, no horizontal ni vertical. (4
   * posibilidades de movimientos)
   */
  public static LinkedList<Regla> reglasAplicablesSinDiagonales(int[][] m, int i, int j) {
    LinkedList<Regla> L1 = new LinkedList<>();
    if (posValida(m, i - 1, j - 1)) {
      L1.add(new Regla(i - 1, j - 1));
    }
    if (posValida(m, i - 1, j + 1)) {
      L1.add(new Regla(i - 1, j + 1));
    }
    if (posValida(m, i + 1, j + 1)) {
      L1.add(new Regla(i + 1, j + 1));
    }
    if (posValida(m, i + 1, j - 1)) {
      L1.add(new Regla(i + 1, j - 1));
    }
    return L1;
  }

  public static void laberinto4(int[][] m, int i, int j, int iFin, int jFin, int paso) {
    if (!posValida(m, i, j)) {
      return;
    }
    m[i][j] = paso;
    if (i == iFin && j == jFin) {
      mostrar(m);
      c++;
    }
    LinkedList<Regla> L1 = reglasAplicablesSinDiagonales(m, i, j);
    while (!L1.isEmpty()) {
      Regla R = elegirRegla(L1);
      laberinto4(m, R.fil, R.col, iFin, jFin, paso + 1);
      m[R.fil][R.col] = 0;
    }
  }

  /*
   * 5. Utilizar una Lista de Matrices para adaptar la resolución del Problema del
   * Laberinto y mostrar la resolución de cualquiera de los problemas de arriba,
   * utilizando esta estructura de datos.
   */
  public static void mostrar(LinkedList<int[][]> L) {
    String s = "";
    for (int[][] matriz : L) {
      for (int[] fila : matriz) {
        for (int columna : fila) {
          s += columna + "\t";
        }
        s += "\n";
      }
      s += "------------------------\n";
    }
    System.out.println(s);
  }

  public static void laberinto5(int[][] m, LinkedList<int[][]> L, int i, int j, int iFin, int jFin, int paso) {
    if (!posValida(m, i, j)) {
      return;
    }
    m[i][j] = paso;
    if (i == iFin && j == jFin) {
      L.add(Arrays.stream(m).map(fila -> fila.clone()).toArray($ -> m.clone()));
      c++;
    }
    LinkedList<Regla> L1 = reglasAplicables(m, i, j);
    while (!L1.isEmpty()) {
      Regla R = elegirRegla(L1);
      laberinto5(m, L, R.fil, R.col, iFin, jFin, paso + 1);
      m[R.fil][R.col] = 0;
    }
  }

  /*
   * 6. Implementar el problema del laberinto según el enfoque de los ejercicios
   * 1) y 2). En este caso, avanzar según el movimiento del caballo.
   * (8-posibilidades de movimientos).
   */
  public static LinkedList<Regla> reglasAplicablesCaballo(int[][] m, int i, int j) {
    LinkedList<Regla> L1 = new LinkedList<>();
    if (posValida(m, i - 2, j - 1)) {
      L1.add(new Regla(i - 2, j - 1));
    }
    if (posValida(m, i - 2, j + 1)) {
      L1.add(new Regla(i - 2, j + 1));
    }
    if (posValida(m, i - 1, j + 2)) {
      L1.add(new Regla(i - 1, j + 2));
    }
    if (posValida(m, i + 1, j + 2)) {
      L1.add(new Regla(i + 1, j + 2));
    }
    if (posValida(m, i + 2, j + 1)) {
      L1.add(new Regla(i + 2, j + 1));
    }
    if (posValida(m, i + 2, j - 1)) {
      L1.add(new Regla(i + 2, j - 1));
    }
    if (posValida(m, i + 1, j - 2)) {
      L1.add(new Regla(i + 1, j - 2));
    }
    if (posValida(m, i - 1, j - 2)) {
      L1.add(new Regla(i - 1, j - 2));
    }
    return L1;
  }

  public static void laberinto6(int[][] m, int i, int j, int iFin, int jFin, int paso) {
    if (!posValida(m, i, j)) {
      return;
    }
    m[i][j] = paso;
    if (i == iFin && j == jFin) {
      mostrar(m);
      c++;
    }
    LinkedList<Regla> L1 = reglasAplicablesCaballo(m, i, j);
    while (!L1.isEmpty()) {
      Regla R = elegirRegla(L1);
      laberinto6(m, R.fil, R.col, iFin, jFin, paso + 1);
      m[R.fil][R.col] = 0;
    }
  }
}
