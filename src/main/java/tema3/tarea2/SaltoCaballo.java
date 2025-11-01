package tema3.tarea2;

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
        System.out.println(s);
    }

    private static boolean posValida(int[][] m, int i, int j) {
        return i >= 0 && i < m.length && j >= 0 && j < m[i].length && m[i][j] == 0;
    }

    public static LinkedList<Regla> reglasAplicables(int[][] m, int i, int j) {
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
    private static Regla elegirRegla(LinkedList<Regla> reglas) {
        return reglas.removeFirst();
    }

    public static int vueltas = 0;

    public static boolean saltoCaballoSinHeuristica(int[][] m, int i, int j, int paso) {
        m[i][j] = paso;
        if (paso >= m.length * m.length) {
            return true;
        }
        LinkedList<Regla> L = reglasAplicables(m, i, j);
        while (!L.isEmpty()) {
            Regla R = elegirRegla(L);
            if (saltoCaballoSinHeuristica(m, R.fil, R.col, paso + 1)) {
                return true;
            }
            vueltas++;
            m[R.fil][R.col] = 0;
        }

        return false;
    }

    /*
     * Heuristica 1
     * "Siempre elegir la regla del medio"
     */
    private static Regla elegirRegla1(LinkedList<Regla> L, int[][] m) {
        return L.remove((L.size() - 1) / 2);
    }

    public static boolean saltoCaballoConHeuristica1(int[][] m, int i, int j, int paso) {
        m[i][j] = paso;
        if (paso >= m.length * m.length) {
            return true;
        }
        LinkedList<Regla> L = reglasAplicables(m, i, j);
        while (!L.isEmpty()) {
            Regla R = elegirRegla1(L, m);
            if (saltoCaballoConHeuristica3(m, R.fil, R.col, paso + 1)) {
                return true;
            }
            vueltas++;
            m[R.fil][R.col] = 0;
        }

        return false;
    }

    /*
     * Heuristica 2
     * "Si siempre eliges la casilla con menos opciones futuras, minimizas el
     * riesgo de bloquearte luego (heurística de Warnsdorff)."
     */
    private static Regla elegirRegla2(LinkedList<Regla> L, int[][] m) {
        int cantMovMenor = Integer.MAX_VALUE;
        Regla mejor = null;

        for (Regla R : L) {
            int cantMovActual = reglasAplicables(m, R.fil, R.col).size();

            if (cantMovActual < cantMovMenor) {
                cantMovMenor = cantMovActual;
                mejor = R;
            }
        }

        L.remove(mejor);
        return mejor;
    }

    public static boolean saltoCaballoConHeuristica2(int[][] m, int i, int j, int paso) {
        m[i][j] = paso;
        if (paso >= m.length * m.length) {
            return true;
        }
        LinkedList<Regla> L = reglasAplicables(m, i, j);
        while (!L.isEmpty()) {
            Regla R = elegirRegla2(L, m);
            if (saltoCaballoConHeuristica2(m, R.fil, R.col, paso + 1)) {
                return true;
            }
            vueltas++;
            m[R.fil][R.col] = 0;
        }

        return false;
    }

    /*
     * Heuristica 3
     * "Si siempre eliges la casilla con menos movimientos futuros, minimizas el
     * riesgo de bloquearte luego. Y además aquellas reglas con la misma cantidad de
     * movimientos futuros puedes elegir aleatoriamente"
     */
    private static Regla elegirRegla3(LinkedList<Regla> L, int[][] m) {
        int cantMovMenor = Integer.MAX_VALUE;
        List<Regla> candidatos = new ArrayList<>();

        for (Regla R : L) {
            int cantMovActual = reglasAplicables(m, R.fil, R.col).size();
            if (cantMovActual < cantMovMenor) {
                cantMovMenor = cantMovActual;
                candidatos.clear();
                candidatos.add(R);
            } else if (cantMovActual == cantMovMenor) {
                candidatos.add(R);
            }
        }

        if (candidatos.isEmpty())
            return null;

        Regla mejor = candidatos.get(new Random().nextInt(candidatos.size()));
        L.remove(mejor);
        return mejor;
    }

    public static boolean saltoCaballoConHeuristica3(int[][] m, int i, int j, int paso) {
        m[i][j] = paso;
        if (paso >= m.length * m.length) {
            return true;
        }
        LinkedList<Regla> L = reglasAplicables(m, i, j);
        while (!L.isEmpty()) {
            Regla R = elegirRegla3(L, m);
            if (saltoCaballoConHeuristica3(m, R.fil, R.col, paso + 1)) {
                return true;
            }
            vueltas++;
            m[R.fil][R.col] = 0;
        }

        return false;
    }

    /*
     * Heuristica 4
     * Combina 2 heuristica:
     * - Elige el movimiento con menor movimiento futuro
     * - Elige la regla que este más al centro de la fila de la matriz
     */
    private static double distanciaAlCentro(double centro, double columna) {
        return Math.abs(columna - centro);
    }

    private static Regla elegirRegla4(LinkedList<Regla> L, int[][] m) {
        double centro = (m.length - 1) / 2;
        double distMenor = Double.MAX_VALUE;

        int cantMovMenor = Integer.MAX_VALUE;
        Regla mejor = null;

        for (Regla R : L) {
            int cantMovActual = reglasAplicables(m, R.fil, R.col).size();
            double distCentro = distanciaAlCentro(centro, R.col);

            if (cantMovActual < cantMovMenor || (cantMovActual == cantMovMenor && distCentro < distMenor)) {
                cantMovMenor = cantMovActual;
                distMenor = distCentro;
                mejor = R;
            }
        }

        L.remove(mejor);
        return mejor;
    }

    public static boolean saltoCaballoConHeuristica4(int[][] m, int i, int j, int paso) {
        m[i][j] = paso;
        if (paso >= m.length * m.length) {
            return true;
        }
        LinkedList<Regla> L = reglasAplicables(m, i, j);
        while (!L.isEmpty()) {
            Regla R = elegirRegla4(L, m);
            if (saltoCaballoConHeuristica4(m, R.fil, R.col, paso + 1)) {
                return true;
            }
            vueltas++;
            m[R.fil][R.col] = 0;
        }

        return false;
    }

    /*
     * Heuristica 5
     * Combina 2 heuristica:
     * - Elige el movimiento con menor movimiento futuro
     * - Elige el movimiento más apegado o cercano a cualquier borde
     */
    private static int distanciaABorde(int n, int i, int j) {
        return Math.min(Math.min(i, n - 1 - i), Math.min(j, n - 1 - j));
    }

    private static Regla elegirRegla5(LinkedList<Regla> L, int[][] m) {
        int cantMovMenor = Integer.MAX_VALUE;
        int distABordeMenor = Integer.MAX_VALUE;
        Regla mejor = null;

        for (Regla R : L) {
            int cantMovActual = reglasAplicables(m, R.fil, R.col).size();
            int distBordeActual = distanciaABorde(m.length, R.fil, R.col);

            if (cantMovActual < cantMovMenor || (cantMovActual == cantMovMenor && distBordeActual < distABordeMenor)) {
                cantMovMenor = cantMovActual;
                distABordeMenor = distBordeActual;
                mejor = R;
            }
        }

        L.remove(mejor);
        return mejor;
    }

    public static boolean saltoCaballoConHeuristica5(int[][] m, int i, int j, int paso) {
        m[i][j] = paso;
        if (paso >= m.length * m.length) {
            return true;
        }
        LinkedList<Regla> L = reglasAplicables(m, i, j);
        while (!L.isEmpty()) {
            Regla R = elegirRegla5(L, m);
            if (saltoCaballoConHeuristica5(m, R.fil, R.col, paso + 1)) {
                return true;
            }
            vueltas++;
            m[R.fil][R.col] = 0;
        }

        return false;
    }
}
