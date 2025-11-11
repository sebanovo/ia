package modelos.examen3;

import java.util.*;

public class Sudoku {

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

    public static int vueltas = 0;

    /*
     * Sin Heuristica
     * "Siempre escoger la primera regla sin pensar."
     */
    private static Integer elegirRegla(LinkedList<Integer> L) {
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
            m[i][j] = elegirRegla(L);
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
    private static Integer elegirRegla1(LinkedList<Integer> L) {
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
            m[i][j] = elegirRegla1(L);
            if (sudoku2Original(m, i, j + 1)) {
                return true;
            }
            vueltas++;
            m[i][j] = 0;
        }
        return false;
    }
}
