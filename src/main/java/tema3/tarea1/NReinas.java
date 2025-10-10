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

  public static boolean estanDiagnoalesVacias(int[][] m, int i, int j) {
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

  public static boolean hayJaqueMateDama(int[][] m, int i, int j) {
    return !estanDiagnoalesVacias(m, i, j) || !estanRectasVacias(m, i, j);
  }

  public static boolean posValida(int[][] m, int i, int j) {
    return i >= 0 && i < m.length && j >= 0 && j < m[i].length && m[i][j] == 0 && !hayJaqueMateDama(m, i, j);
  }

  public static Regla elegirRegla(LinkedList<Regla> L1, int[][] m) {
    return L1.removeFirst();
  }

  public static double distancia(int x1, int y1, int x2, int y2) {
    return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
  }

  public static Regla elegirMejorRegla(LinkedList<Regla> L1, int[][] m) {
    return L1.remove(L1.size() / 2);
  }

  public static LinkedList<Regla> reglasAplicablesTorres(int[][] m, int i, int j) {
    LinkedList<Regla> L1 = new LinkedList<>();
    int j1 = j - 1;
    while (posValida(m, i, j1)) { // ⬆
      L1.add(new Regla(i, j1));
      j1 = j1 - 1;
    }
    int i1 = i - 1;
    while (posValida(m, i1, j)) { // ⬅
      L1.add(new Regla(i1, j));
      i1 = i1 - 1;
    }

    j1 = j + 1;
    while (posValida(m, i, j1)) { // ⬇
      L1.add(new Regla(i, j1));
      j1 = j1 + 1;
    }
    i1 = i + 1;
    while (posValida(m, i1, j)) { // ➡
      L1.add(new Regla(i1, j));
      i1 = i1 + 1;
    }
    return L1;
  }

  public static LinkedList<Regla> reglasAplicablesAlfil(int[][] m, int i, int j) {
    LinkedList<Regla> L1 = new LinkedList<>();
    int i1 = i - 1, j1 = j - 1;
    while (posValida(m, i1, j1)) { // ↖
      L1.add(new Regla(i1, j1));
      i1--;
      j1--;
    }

    i1 = i - 1;
    j1 = j + 1;
    while (posValida(m, i1, j1)) { // ↙
      L1.add(new Regla(i1, j1));
      i1--;
      j1++;
    }

    i1 = i + 1;
    j1 = j - 1;
    while (posValida(m, i1, j1)) { // ↗
      L1.add(new Regla(i1, j1));
      i1++;
      j1--;
    }

    i1 = i + 1;
    j1 = j + 1;
    while (posValida(m, i1, j1)) { // ↘
      L1.add(new Regla(i1, j1));
      i1++;
      j1++;
    }

    return L1;
  }

  public static LinkedList<Regla> reglasAplicablesDama(int[][] m, int i, int j) {
    LinkedList<Regla> L = new LinkedList<>();

    if (posValida(m, i, j)) {
      L.add(new Regla(i, j));
    }
    L.addAll(reglasAplicablesTorres(m, i, j));
    L.addAll(reglasAplicablesAlfil(m, i, j));

    return L;
  }

  public static int vueltas = 0;

  public static boolean nReinasSinHeuristica(int[][] m, int paso) {
    if (paso > m.length)
      return true;

    LinkedList<Regla> L1 = reglasAplicablesDama(m, paso - 1, paso - 1);
    while (!L1.isEmpty()) {
      Regla R = elegirRegla(L1, m);
      m[R.fil][R.col] = paso;
      if (nReinasConHeuristica(m, paso + 1)) {
        vueltas++;
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
      Regla R = elegirMejorRegla(L1, m);
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
