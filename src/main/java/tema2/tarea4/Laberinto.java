package tema2.tarea4;

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

  public static boolean posValida(int[][] m, int i, int j) {
    return i >= 0 && i < m.length && j >= 0 && j < m[i].length && m[i][j] == 0;
  }

  public static double distancia(int x1, int y1, int x2, int y2) {
    return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
  }

  public static Regla elegirMejorRegla(LinkedList<Regla> L1, int iFin, int jFin) {
    double distMenor = Double.MAX_VALUE;
    int posMenor = 0;
    for (int i = 0; i < L1.size(); i++) {
      double dist = distancia(L1.get(i).fil, L1.get(i).col, iFin, jFin);
      if (dist < distMenor) {
        distMenor = dist;
        posMenor = i;
      }
    }
    return L1.remove(posMenor);
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

  public static boolean laberinto(int[][] m, int i, int j, int iFin, int jFin, int paso) {
    m[i][j] = paso;
    if (i == iFin && j == jFin) {
      return true;
    }

    LinkedList<Regla> L1 = reglasAplicables(m, i, j);
    while (!L1.isEmpty()) {
      Regla R = elegirMejorRegla(L1, iFin, jFin);
      if (laberinto(m, R.fil, R.col, iFin, jFin, paso + 1)) {
        return true;
      }
      m[R.fil][R.col] = 0;
    }
    return false;
  }
}
