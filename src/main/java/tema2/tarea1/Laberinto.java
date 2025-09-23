package tema2.tarea1;

import java.util.*;

public class Laberinto {
  /*
   * 1. Dado una matriz de n x m, inicialmente todas las posiciones con valores de
   * cero, avanzar las casillas en sentido horario con movimientos de izquierda,
   * arriba, derecha y abajo. Hacer Algoritmos para los siguientes:
   */
  public static boolean posValida(int[][] m, int i, int j) {
    return i >= 0 && i < m.length && j >= 0 && j < m[i].length && m[i][j] == 0;
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

  /*
   * 1. Dado una matriz de n x m, inicialmente todas las posiciones con valores de
   * cero, avanzar las casillas en sentido horario con movimientos de izquierda,
   * arriba, derecha y abajo. Hacer Algoritmos para los siguientes:
   */
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
    laberinto1A(m, i, j - 1, iFin, jFin, paso + 1);
    laberinto1A(m, i - 1, j, iFin, jFin, paso + 1);
    laberinto1A(m, i, j + 1, iFin, jFin, paso + 1);
    laberinto1A(m, i + 1, j, iFin, jFin, paso + 1);
    m[i][j] = 0;
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
    laberinto1B(m, i, j - 1, iFin, jFin, paso + 1);
    laberinto1B(m, i - 1, j, iFin, jFin, paso + 1);
    laberinto1B(m, i, j + 1, iFin, jFin, paso + 1);
    laberinto1B(m, i + 1, j, iFin, jFin, paso + 1);
    m[i][j] = 0;
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
    laberinto1C(m, i, j - 1, iFin, jFin, paso + 1);
    laberinto1C(m, i - 1, j, iFin, jFin, paso + 1);
    laberinto1C(m, i, j + 1, iFin, jFin, paso + 1);
    laberinto1C(m, i + 1, j, iFin, jFin, paso + 1);
    m[i][j] = 0;
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
    } else {
      laberinto1D(m, i, j - 1, iFin, jFin, paso + 1);
      laberinto1D(m, i - 1, j, iFin, jFin, paso + 1);
      laberinto1D(m, i, j + 1, iFin, jFin, paso + 1);
      laberinto1D(m, i + 1, j, iFin, jFin, paso + 1);
    }
    m[i][j] = 0;
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
      } else {
        laberinto2(m, i, j - 1, iFin, jFin, paso + 1);
        laberinto2(m, i - 1, j, iFin, jFin, paso + 1);
        laberinto2(m, i, j + 1, iFin, jFin, paso + 1);
        laberinto2(m, i + 1, j, iFin, jFin, paso + 1);
      }
      m[i][j] = 0;
    }
  }

  /*
   * 3. Implementar los ejercicios 2 y 3. Redefiniendo el movimiento en el
   * Laberinto, también se puede mover una casilla por las diagonales. (8
   * posibilidades de movimientos)
   */
  public static void laberinto3(int[][] m, int i, int j, int iFin, int jFin, int paso) {
    if (!posValida(m, i, j)) {
      return;
    }

    m[i][j] = paso;
    if (i == iFin && j == jFin) {
      mostrar(m);
      c++;
    }
    laberinto3(m, i, j - 1, iFin, jFin, paso + 1);
    laberinto3(m, i - 1, j - 1, iFin, jFin, paso + 1);
    laberinto3(m, i - 1, j, iFin, jFin, paso + 1);
    laberinto3(m, i - 1, j + 1, iFin, jFin, paso + 1);
    laberinto3(m, i, j + 1, iFin, jFin, paso + 1);
    laberinto3(m, i + 1, j + 1, iFin, jFin, paso + 1);
    laberinto3(m, i + 1, j, iFin, jFin, paso + 1);
    laberinto3(m, i + 1, j - 1, iFin, jFin, paso + 1);
    m[i][j] = 0;
  }

  /*
   * 4. Implementar los ejercicios 2 y 3. Redefiniendo el movimiento en el
   * Laberinto, solo por la diagonales, no horizontal ni vertical. (4
   * posibilidades de movimientos)
   */
  public static void laberinto4(int[][] m, int i, int j, int iFin, int jFin, int paso) {
    if (!posValida(m, i, j)) {
      return;
    }

    m[i][j] = paso;
    if (i == iFin && j == jFin) {
      mostrar(m);
      c++;
    }
    laberinto4(m, i - 1, j - 1, iFin, jFin, paso + 1);
    laberinto4(m, i - 1, j + 1, iFin, jFin, paso + 1);
    laberinto4(m, i + 1, j + 1, iFin, jFin, paso + 1);
    laberinto4(m, i + 1, j - 1, iFin, jFin, paso + 1);
    m[i][j] = 0;
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
    laberinto5(m, L, i, j - 1, iFin, jFin, paso + 1);
    laberinto5(m, L, i - 1, j, iFin, jFin, paso + 1);
    laberinto5(m, L, i, j + 1, iFin, jFin, paso + 1);
    laberinto5(m, L, i + 1, j, iFin, jFin, paso + 1);
    m[i][j] = 0;
  }

  /*
   * 6. Implementar el problema del laberinto según el enfoque de los ejercicios
   * 1) y 2). En este caso, avanzar según el movimiento del caballo.
   * (8-posibilidades de movimientos).
   */
  public static void laberinto6(int[][] m, int i, int j, int iFin, int jFin, int paso) {
    if (!posValida(m, i, j)) {
      return;
    }

    m[i][j] = paso;
    if (i == iFin && j == jFin) {
      mostrar(m);
      c++;
    }
    laberinto6(m, i - 2, j - 1, iFin, jFin, paso + 1);
    laberinto6(m, i - 2, j + 1, iFin, jFin, paso + 1);
    laberinto6(m, i - 1, j + 2, iFin, jFin, paso + 1);
    laberinto6(m, i + 1, j + 2, iFin, jFin, paso + 1);
    laberinto6(m, i + 2, j + 1, iFin, jFin, paso + 1);
    laberinto6(m, i + 2, j - 1, iFin, jFin, paso + 1);
    laberinto6(m, i + 1, j - 2, iFin, jFin, paso + 1);
    laberinto6(m, i - 1, j - 2, iFin, jFin, paso + 1);
    m[i][j] = 0;
  }
}
