package modelos.examen3;

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
        System.out.print(s);
    }

    private static boolean estanRectasVacias(int[][] m, int i, int j) {
        int[][] direcciones = {
                { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }
        };
        return estanDireccionesVacias(m, i, j, direcciones);
    }

    private static boolean estanDiagonalesVacias(int[][] m, int i, int j) {
        int[][] direcciones = {
                { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 }
        };
        return estanDireccionesVacias(m, i, j, direcciones);
    }

    private static boolean estanDireccionesVacias(int[][] m, int i, int j, int[][] direcciones) {
        for (int[] d : direcciones) {
            int x = i + d[0];
            int y = j + d[1];
            while (x >= 0 && x < m.length && y >= 0 && y < m[x].length) {
                if (m[x][y] != 0)
                    return false;
                x += d[0];
                y += d[1];
            }
        }
        return true;
    }

    private static boolean hayDamaEnRango(int[][] m, int i, int j) {
        return !estanDiagonalesVacias(m, i, j) || !estanRectasVacias(m, i, j);
    }

    private static boolean posValida(int[][] m, int i, int j) {
        return i >= 0 && i < m.length && j >= 0 && j < m[i].length && m[i][j] == 0 && !hayDamaEnRango(m, i, j);
    }

    private static LinkedList<Regla> reglasAplicablesDama(int[][] m, int fila) {
        LinkedList<Regla> L = new LinkedList<>();
        for (int k = 0; k < m[fila].length; k++) {
            if (posValida(m, fila, k) && !hayDamaEnRango(m, fila, k))
                L.add(new Regla(fila, k));
        }

        return L;
    }

    public static int vueltas = 0;

    /*
     * Sin Heuristica
     * "Siempre escoger la primera regla sin pensar."
     */
    private static Regla elegirRegla(LinkedList<Regla> L, int[][] m) {
        return L.removeFirst();
    }

    public static boolean nReinas1(int[][] m, int paso) {
        if (paso > m.length)
            return true;

        LinkedList<Regla> L = reglasAplicablesDama(m, paso - 1); // paso - 1 es la fila
        while (!L.isEmpty()) {
            Regla R = elegirRegla(L, m);
            m[R.fil][R.col] = paso;
            if (nReinas1(m, paso + 1)) {
                return true;
            }
            vueltas++;
            m[R.fil][R.col] = 0;
        }
        return false;
    }

    /*
     * Heuristica
     * "Siempre elegir la regla del medio de lista"
     */
    private static Regla elegirRegla1(LinkedList<Regla> L, int[][] m) {
        return L.remove((L.size() - 1) / 2);
    }

    public static boolean nReinas2(int[][] m, int paso) {
        if (paso > m.length)
            return true;

        LinkedList<Regla> L = reglasAplicablesDama(m, paso - 1);
        while (!L.isEmpty()) {
            Regla R = elegirRegla1(L, m);
            m[R.fil][R.col] = paso;
            if (nReinas2(m, paso + 1)) {
                return true;
            }
            vueltas++;
            m[R.fil][R.col] = 0;
        }
        return false;
    }
}
