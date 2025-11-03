package tema3.tarea3;

import java.util.*;

public class Sudoku {
    /*
     * A. ENCONTRAR TODAS LAS SOLUCIONES.
     * 
     * Implementar el problema del SUDOKU, utilizando inicialmente una matriz
     * cuadrada. Hacer el Algoritmo, para encontrar todas las soluciones posibles.
     * 
     * a) Ejecutar el Algoritmos para una matriz de 4x4, 9x9 y 16x16, con todos los
     * elementos iguales a cero. Mostrar en cada exploración la matriz y la lista de
     * reglas aplicables. Analizar la forma de armado de la soluciones, la cantidad
     * de soluciones posibles.
     * 
     * b) Ejecutar el Algoritmo, para una matriz de 4x4, con valores iniciales y
     * analizar las soluciones posibles que presentan.
     * 
     * c) Ejecutar el Algoritmo, para matrices de 9x9, con valores iniciales y
     * analizar la(s) solucion(es) que produce.
     * 
     * d) Ejecutar el Algoritmo, para matrices de 16x16, con valores iniciales y
     * analizar la(s) solucion(es) que produce.
     * 
     * B. ENCONTRAR LA PRIMERA SOLUCIÓN CON Y SIN HEURÍSTICAS.
     * 
     * SUDOKU CON HEURÍSTICA.
     * 
     * Implementar el problema del sudoku utilizando el algoritmo de backTrack(...),
     * encuentra la primera solución. Mostrar la primera solución y la cantidad de
     * vueltas que se realizan hasta encontrar la primera solución.
     * 
     * Implementar sin información heurística.
     * Implementar con información heurística. (Al menos 2 heurísticas).
     * Para ambos casos, implementar con sudokus de diferentes tamaños y de
     * diferentes complejidades. (básico, intermedio y avanzado). Escribir un
     * documento mostrando las diferencias en la cantidad de vueltas por el uso de
     * información heurística. Escribir sus conclusiones.
     */
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

    private static LinkedList<Integer> reglasAplicables(int[][] m, int i, int j) {
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
     * a) Ejecutar el Algoritmos para una matriz de 4x4, 9x9 y 16x16, con todos los
     * elementos iguales a cero. Mostrar en cada exploración la matriz y la lista de
     * reglas aplicables. Analizar la forma de armado de la soluciones, la cantidad
     * de soluciones posibles.
     */
    public static int c = 0;

    private static Integer elegirRegla(LinkedList<Integer> L) {
        return L.removeFirst();
    }

    public static void sudoku(int[][] m, int i, int j) {
        int subdivision = (int) Math.sqrt(m.length);
        for (int sub = subdivision; sub <= m.length; sub += subdivision) {
            if (sub == m.length) {
                sudokuOriginal(m, i, j);
            }
        }
    }

    private static void sudokuOriginal(int[][] m, int i, int j) {
        if (i >= m.length) {
            System.out.println("<- Reglas Aplicables");
            mostrar(m);
            c++;
            System.out.println("vueltas: " + vueltas);
            vueltas = 0;
            return;
        }
        if (j >= m[i].length) {
            sudokuOriginal(m, i + 1, 0);
            return;
        }
        if (m[i][j] != 0) {
            sudokuOriginal(m, i, j + 1);
        }

        LinkedList<Integer> L = reglasAplicables(m, i, j);
        System.out.print(L);
        while (!L.isEmpty()) {
            m[i][j] = elegirRegla(L);
            sudokuOriginal(m, i, j + 1);
            vueltas++;
            m[i][j] = 0;
        }
    }

    /*
     * Sin Heuristica
     * "Siempre escoger la primera regla sin pensar."
     */

    public static boolean sudokuSinHeuristica(int[][] m, int i, int j) {
        int subdivision = (int) Math.sqrt(m.length);
        for (int sub = subdivision; sub <= m.length; sub += subdivision) {
            if (sub == m.length) {
                return sudokuSinHeuristicaOriginal(m, i, j);
            }
        }
        return false;
    }

    private static boolean sudokuSinHeuristicaOriginal(int[][] m, int i, int j) {
        if (i >= m.length)
            return true;
        if (j >= m[i].length)
            return sudokuSinHeuristica(m, i + 1, 0);
        if (m[i][j] != 0)
            return sudokuSinHeuristica(m, i, j + 1);

        LinkedList<Integer> L = reglasAplicables(m, i, j);
        while (!L.isEmpty()) {
            m[i][j] = elegirRegla(L);
            if (sudokuSinHeuristicaOriginal(m, i, j + 1)) {
                return true;
            }
            vueltas++;
            m[i][j] = 0;
        }
        return false;
    }

    /*
     * Heuristica 1
     * "Siempre elegir la regla del medio"
     */
    private static Integer elegirRegla1(LinkedList<Integer> L) {
        return L.remove((L.size() - 1) / 2);
    }

    public static boolean sudokuConHeuristica1(int[][] m, int i, int j) {
        int subdivision = (int) Math.sqrt(m.length);
        for (int sub = subdivision; sub <= m.length; sub += subdivision) {
            if (sub == m.length) {
                return sudokuConHeuristica1Original(m, i, j);
            }
        }
        return false;
    }

    private static boolean sudokuConHeuristica1Original(int[][] m, int i, int j) {
        if (i >= m.length)
            return true;
        if (j >= m[i].length)
            return sudokuConHeuristica1Original(m, i + 1, 0);
        if (m[i][j] != 0)
            return sudokuConHeuristica1Original(m, i, j + 1);

        LinkedList<Integer> L = reglasAplicables(m, i, j);
        while (!L.isEmpty()) {
            m[i][j] = elegirRegla1(L);
            if (sudokuConHeuristica1Original(m, i, j + 1)) {
                return true;
            }
            vueltas++;
            m[i][j] = 0;
        }
        return false;
    }

    /*
     * Heuristica 2
     * "Si siempre eliges la casilla con menos opciones futuras, minimizas el
     * riesgo de bloquearte luego (heurística de Warnsdorff)."
     */
    private static Integer elegirRegla2(LinkedList<Integer> L, int[][] m, int i, int j) {
        int cantMovMenor = Integer.MAX_VALUE;
        Integer mejor = -1;

        for (Integer numero : L) {
            m[i][j] = numero;

            int cantMovActual;
            if (j + 1 >= m[i].length) {
                if (i + 1 >= m.length) {
                    return L.removeFirst();
                }
                cantMovActual = reglasAplicables(m, i + 1, 0).size();
            } else {
                cantMovActual = reglasAplicables(m, i, j + 1).size();
            }
            m[i][j] = 0;
            if (cantMovActual < cantMovMenor) {
                cantMovMenor = cantMovActual;
                mejor = numero;
            }
        }
        L.remove(mejor);
        return mejor;
    };

    public static boolean sudokuConHeuristica2(int[][] m, int i, int j) {
        int subdivision = (int) Math.sqrt(m.length);
        for (int sub = subdivision; sub <= m.length; sub += subdivision) {
            if (sub == m.length) {
                return sudokuConHeuristica2Original(m, i, j);
            }
        }
        return false;
    }

    private static boolean sudokuConHeuristica2Original(int[][] m, int i, int j) {
        if (i >= m.length)
            return true;
        if (j >= m[i].length)
            return sudokuConHeuristica2Original(m, i + 1, 0);
        if (m[i][j] != 0)
            return sudokuConHeuristica2Original(m, i, j + 1);

        LinkedList<Integer> L = reglasAplicables(m, i, j);
        while (!L.isEmpty()) {
            m[i][j] = elegirRegla2(L, m, i, j);
            if (sudokuConHeuristica2Original(m, i, j + 1)) {
                return true;
            }
            vueltas++;
            m[i][j] = 0;
        }
        return false;
    }

    /*
     * Heuristica 3
     * "Si siempre eliges la casilla con menos movimientos futuros, minimizas el
     * riesgo de bloquearte luego. Y además aquellas reglas con la misma cantidad de
     * movimientos futuros puedes elegir aleatoriamente."
     */
    private static Integer elegirRegla3(LinkedList<Integer> L, int[][] m, int i, int j) {
        int cantMovMenor = Integer.MAX_VALUE;
        List<Integer> candidatos = new ArrayList<>();

        for (Integer numero : L) {
            m[i][j] = numero;
            int cantMovActual;
            if (j + 1 >= m[i].length) {
                if (i + 1 >= m.length) {
                    return L.removeFirst();
                }
                cantMovActual = reglasAplicables(m, i + 1, 0).size();
            } else {
                cantMovActual = reglasAplicables(m, i, j + 1).size();
            }
            m[i][j] = 0;
            if (cantMovActual < cantMovMenor) {
                cantMovMenor = cantMovActual;
                candidatos.clear();
                candidatos.add(numero);
            } else if (cantMovActual == cantMovMenor) {
                candidatos.add(numero);
            }
        }
        Integer mejor = candidatos.get(new Random().nextInt(candidatos.size()));
        L.remove(mejor);
        return mejor;
    };

    public static boolean sudokuConHeuristica3(int[][] m, int i, int j) {
        int subdivision = (int) Math.sqrt(m.length);
        for (int sub = subdivision; sub <= m.length; sub += subdivision) {
            if (sub == m.length) {
                return sudokuConHeuristica3Original(m, i, j);
            }
        }
        return false;
    }

    private static boolean sudokuConHeuristica3Original(int[][] m, int i, int j) {
        if (i >= m.length)
            return true;
        if (j >= m[i].length)
            return sudokuConHeuristica3Original(m, i + 1, 0);
        if (m[i][j] != 0)
            return sudokuConHeuristica3Original(m, i, j + 1);

        LinkedList<Integer> L = reglasAplicables(m, i, j);
        while (!L.isEmpty()) {
            m[i][j] = elegirRegla3(L, m, i, j);
            if (sudokuConHeuristica3Original(m, i, j + 1)) {
                return true;
            }
            vueltas++;
            m[i][j] = 0;
        }
        return false;
    }
}
