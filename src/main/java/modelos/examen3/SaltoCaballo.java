package modelos.examen3;

import java.util.*;

public class SaltoCaballo {

    private static class Regla {
        int fil, col;

        public Regla(int fil, int col) {
            this.fil = fil;
            this.col = col;
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

    private static LinkedList<Regla> reglasAplicablesCaballo(int[][] m, int i, int j) {
        LinkedList<Regla> L = new LinkedList<>();

        int[][] movimientos = {
                { -2, 1 }, { -1, 2 },
                { 1, 2 }, { 2, 1 },
                { 2, -1 }, { 1, -2 },
                { -1, -2 }, { -2, -1 },
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

    /*
     * Sin Heuristica
     * "Siempre escoger la primera regla sin pensar."
     */
    private static Regla elegirRegla1(LinkedList<Regla> reglas) {
        return reglas.removeFirst();
    }

    public static int vueltas = 0;

    public static boolean saltoCaballo1(int[][] m, int i, int j, int paso) {
        m[i][j] = paso;
        if (paso >= m.length * m.length) {
            return true;
        }
        LinkedList<Regla> L = reglasAplicablesCaballo(m, i, j);
        while (!L.isEmpty()) {
            Regla R = elegirRegla1(L);
            if (saltoCaballo1(m, R.fil, R.col, paso + 1)) {
                return true;
            }
            vueltas++;
            m[R.fil][R.col] = 0;
        }

        return false;
    }

    /*
     * Heuristica
     * "Siempre elegir la regla del medio"
     */
    private static Regla elegirRegla2(LinkedList<Regla> L, int[][] m) {
        return L.remove((L.size() - 1) / 2);
    }

    public static boolean saltoCaballo2(int[][] m, int i, int j, int paso) {
        m[i][j] = paso;
        if (paso >= m.length * m.length) {
            return true;
        }
        LinkedList<Regla> L = reglasAplicablesCaballo(m, i, j);
        while (!L.isEmpty()) {
            Regla R = elegirRegla2(L, m);
            if (saltoCaballo2(m, R.fil, R.col, paso + 1)) {
                return true;
            }
            vueltas++;
            m[R.fil][R.col] = 0;
        }

        return false;
    }
}
