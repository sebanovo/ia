package modelos.examen3;

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
        System.out.print(s);
    }

    private static boolean posValida(int[][] m, int i, int j) {
        return i >= 0 && i < m.length && j >= 0 && j < m[i].length && m[i][j] == 0;
    }

    private static LinkedList<Regla> reglasAplicablesRey(int[][] m, int i, int j) {
        LinkedList<Regla> L = new LinkedList<>();

        int[][] movimientos = {
                { -1, -1 }, { -1, 0 }, { -1, 1 },
                { 0, -1 }, { 0, 1 },
                { 1, -1 }, { 1, 0 }, { 1, 1 }
        };

        for (int[] mov : movimientos) {
            int ni = i + mov[0];
            int nj = j + mov[1];
            if (posValida(m, ni, nj)) {
                L.add(new Regla(ni, nj));
            }
        }
        return L;
    }

    public static int vueltas = 0;
    /*
     * Sin Heuristica
     * "Siempre escoger la primera regla sin pensar."
     */

    private static Regla elegirRegla1(LinkedList<Regla> L) {
        return L.removeFirst();
    }

    public static boolean laberintoRey1(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            return true;
        }

        LinkedList<Regla> L = reglasAplicablesRey(m, i, j);
        while (!L.isEmpty()) {
            Regla R = elegirRegla1(L);
            if (laberintoRey1(m, R.fil, R.col, iFin, jFin, paso + 1)) {
                return true;
            }
            m[R.fil][R.col] = 0;
            vueltas++;
        }
        return false;
    }

    /*
     * Heuristica
     * "Siempre elegir la regla del medio de lista"
     */
    private static Regla elegirRegla2(LinkedList<Regla> L) {
        return L.remove((L.size() - 1) / 2);
    }

    public static boolean laberintoRey2(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            return true;
        }

        LinkedList<Regla> L = reglasAplicablesRey(m, i, j);
        while (!L.isEmpty()) {
            Regla R = elegirRegla2(L);
            if (laberintoRey2(m, R.fil, R.col, iFin, jFin, paso + 1)) {
                return true;
            }
            m[R.fil][R.col] = 0;
            vueltas++;
        }
        return false;
    }
}
