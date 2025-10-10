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
    return L1.remove(L1.size() / 2);
  }

  public static LinkedList<Regla> reglasAplicablesDama(int[][] m, int i, int j) {
    LinkedList<Regla> L1 = new LinkedList<>();
    for (int k = 0; k < m[i].length; k++) {
      if (posValida(m, i, k))
        L1.add(new Regla(i, k));
    }
    return L1;
  }

  public static int vueltas = 0;

  public static boolean nReinasSinHeuristica(int[][] m, int paso) {
    if (paso > m.length)
      return true;

    LinkedList<Regla> L1 = reglasAplicablesDama(m, paso - 1, paso - 1);
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

    LinkedList<Regla> L1 = reglasAplicablesDama(m, paso - 1, paso - 1);
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
}
