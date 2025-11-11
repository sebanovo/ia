package modelos.examen3;

import java.util.*;

public class Examen {
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
     ***************************************
     ************* LABERINTO ***************
     ***************************************
     */
    /*
     * Sin Heuristica
     * "Siempre escoger la primera regla sin pensar."
     */

    private static Regla elegirRegla(LinkedList<Regla> L) {
        return L.removeFirst();
    }

    public static boolean laberintoRey1(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            return true;
        }

        LinkedList<Regla> L = reglasAplicablesRey(m, i, j);
        while (!L.isEmpty()) {
            Regla R = elegirRegla(L);
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
    private static Regla elegirRegla1(LinkedList<Regla> L) {
        return L.remove((L.size() - 1) / 2);
    }

    public static boolean laberintoRey2(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            return true;
        }

        LinkedList<Regla> L = reglasAplicablesRey(m, i, j);
        while (!L.isEmpty()) {
            Regla R = elegirRegla1(L);
            if (laberintoRey2(m, R.fil, R.col, iFin, jFin, paso + 1)) {
                return true;
            }
            m[R.fil][R.col] = 0;
            vueltas++;
        }
        return false;
    }

    /*
     *************************************
     ************* NREINAS ***************
     *************************************
     */
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

    private static LinkedList<Regla> reglasAplicablesDama(int[][] m, int fila) {
        LinkedList<Regla> L = new LinkedList<>();
        for (int k = 0; k < m[fila].length; k++) {
            if (posValida(m, fila, k) && !hayDamaEnRango(m, fila, k))
                L.add(new Regla(fila, k));
        }

        return L;
    }

    /*
     * Sin Heuristica
     * "Siempre escoger la primera regla sin pensar."
     */
    public static boolean nReinas1(int[][] m, int paso) {
        if (paso > m.length)
            return true;

        LinkedList<Regla> L = reglasAplicablesDama(m, paso - 1); // paso - 1 es la fila
        while (!L.isEmpty()) {
            Regla R = elegirRegla(L);
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
    public static boolean nReinas2(int[][] m, int paso) {
        if (paso > m.length)
            return true;

        LinkedList<Regla> L = reglasAplicablesDama(m, paso - 1);
        while (!L.isEmpty()) {
            Regla R = elegirRegla1(L);
            m[R.fil][R.col] = paso;
            if (nReinas2(m, paso + 1)) {
                return true;
            }
            vueltas++;
            m[R.fil][R.col] = 0;
        }
        return false;
    }

    /*
     *************************************
     ********** SALTOCABALLO *************
     *************************************
     */
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
    public static boolean saltoCaballo1(int[][] m, int i, int j, int paso) {
        m[i][j] = paso;
        if (paso >= m.length * m.length) {
            return true;
        }
        LinkedList<Regla> L = reglasAplicablesCaballo(m, i, j);
        while (!L.isEmpty()) {
            Regla R = elegirRegla(L);
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
    public static boolean saltoCaballo2(int[][] m, int i, int j, int paso) {
        m[i][j] = paso;
        if (paso >= m.length * m.length) {
            return true;
        }
        LinkedList<Regla> L = reglasAplicablesCaballo(m, i, j);
        while (!L.isEmpty()) {
            Regla R = elegirRegla1(L);
            if (saltoCaballo2(m, R.fil, R.col, paso + 1)) {
                return true;
            }
            vueltas++;
            m[R.fil][R.col] = 0;
        }

        return false;
    }

    /*
     *************************************
     ********** SUDOKU *************
     *************************************
     */
    private static boolean estaEnFila(int[][] m, int i, int valor) {
        for (int j = 0; j < m[i].length; j++) {
            if (m[i][j] == valor)
                return true;
        }
        return false;
    }

    private static boolean estaEnColumna(int[][] m, int j, int valor) {
        for (int i = 0; i < m.length; i++) {
            if (m[i][j] == valor)
                return true;
        }
        return false;
    }

    private static boolean estaEnRegion(int[][] m, int i, int j, int valor) {
        int nFilRegion = (int) Math.sqrt(m.length);
        int nColRegion = (int) Math.sqrt(m[i].length);

        int iRegion = (i / nFilRegion) * nFilRegion;
        int jRegion = (j / nFilRegion) * nColRegion;

        for (int fil = iRegion; fil < iRegion + nFilRegion; fil++) {
            for (int col = jRegion; col < jRegion + nColRegion; col++) {
                if (m[fil][col] == valor) {
                    return true;
                }
            }

        }
        return false;
    }

    private static LinkedList<Integer> reglasAplicablesSudoku(int[][] m, int i, int j) {
        LinkedList<Integer> L = new LinkedList<>();
        for (int valor = 1; valor <= m.length; valor++) {
            if (!estaEnFila(m, i, valor) && !estaEnColumna(m, j, valor) && !estaEnRegion(m, i, j, valor)) {
                L.add(valor);
            }
        }
        return L;
    }

    /*
     * Sin Heuristica
     * "Siempre escoger la primera regla sin pensar."
     */
    private static Integer elegirRegla2(LinkedList<Integer> L) {
        return L.removeFirst();
    }

    public static boolean sudoku1(int[][] m, int i, int j) {
        int subdivision = (int) Math.sqrt(m.length);
        for (int sub = subdivision; sub <= m.length; sub += subdivision) {
            if (sub == m.length) {
                return sudoku1Original(m, i, j);
            }
        }
        return false;
    }

    private static boolean sudoku1Original(int[][] m, int i, int j) {
        if (i >= m.length)
            return true;
        if (j >= m[i].length)
            return sudoku1(m, i + 1, 0);
        if (m[i][j] != 0)
            return sudoku1(m, i, j + 1);

        LinkedList<Integer> L = reglasAplicablesSudoku(m, i, j);
        while (!L.isEmpty()) {
            m[i][j] = elegirRegla2(L);
            if (sudoku1Original(m, i, j + 1)) {
                return true;
            }
            vueltas++;
            m[i][j] = 0;
        }
        return false;
    }

    /*
     * Heuristica
     * "Siempre elegir la regla del medio"
     */
    private static Integer elegirRegla3(LinkedList<Integer> L) {
        return L.remove((L.size() - 1) / 2);
    }

    public static boolean sudoku2(int[][] m, int i, int j) {
        int subdivision = (int) Math.sqrt(m.length);
        for (int sub = subdivision; sub <= m.length; sub += subdivision) {
            if (sub == m.length) {
                return sudoku2Original(m, i, j);
            }
        }
        return false;
    }

    private static boolean sudoku2Original(int[][] m, int i, int j) {
        if (i >= m.length)
            return true;
        if (j >= m[i].length)
            return sudoku2Original(m, i + 1, 0);
        if (m[i][j] != 0)
            return sudoku2Original(m, i, j + 1);

        LinkedList<Integer> L = reglasAplicablesSudoku(m, i, j);
        while (!L.isEmpty()) {
            m[i][j] = elegirRegla3(L);
            if (sudoku2Original(m, i, j + 1)) {
                return true;
            }
            vueltas++;
            m[i][j] = 0;
        }
        return false;
    }
}
