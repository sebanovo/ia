package tema3.tarea1;

import java.util.*;

public class NReinas {
	/*
	 * Este problema consiste en ubicar n reinas en un tablero de ajedrez de n x n.
	 * Tal que, entre las reinas no se puedan atrapar. Para propósitos metódicos,
	 * ubicaremos las reinas desde la primera fila, hasta completar en la última
	 * fila.
	 * 
	 * Implementar el Algoritmo y ejecutar de dos formas:
	 * 
	 * Sin información heurística. (Elegir y eliminar la primera Regla)
	 * Con información heurística. (Elegir y eliminar la mejor Regla)
	 * Para ambos casos, registrar la cantidad de vuelvas que realiza el algoritmo
	 * hasta encontrar la solución.
	 * 
	 * Ejecutar para varios valores de n sucesivamente, n = 4, 5, 6, 7, . . . . . .
	 * Para cada valor de n registrar la cantidad de vueltas con y sin heurística.
	 * Graficar curvas de tendencias.
	 * 
	 * Entrenar al chatGPT, sobre este problema y solicitarle otras heurísticas
	 * interesantes para implementar (al menos 3) y ejecutar con esas heurísticas la
	 * resolución de problema y verificar que se llega al objetivo con menor
	 * cantidad de vueltas, según aumenta el valor de n. (ejecutar sucesivamente
	 * para n-grande).
	 */
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
		int[][] direcciones = {
				{ 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }
		};
		return estanDireccionesVacias(m, i, j, direcciones);
	}

	public static boolean estanDiagonalesVacias(int[][] m, int i, int j) {
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

	public static boolean hayDamaEnRango(int[][] m, int i, int j) {
		return !estanDiagonalesVacias(m, i, j) || !estanRectasVacias(m, i, j);
	}

	public static boolean posValida(int[][] m, int i, int j) {
		return i >= 0 && i < m.length && j >= 0 && j < m[i].length && m[i][j] == 0 && !hayDamaEnRango(m, i, j);
	}

	public static Regla elegirRegla(LinkedList<Regla> L, int[][] m) {
		return L.removeFirst();
	}

	public static LinkedList<Regla> reglasAplicablesDama(int[][] m, int fila) {
		LinkedList<Regla> L = new LinkedList<>();
		for (int k = 0; k < m[fila].length; k++) {
			if (posValida(m, fila, k))
				L.add(new Regla(fila, k));
		}

		return L;
	}

	public static int vueltas = 0;

	/*
	 * Sin Heuristica
	 * "Siempre escoger la primera regla sin pensar."
	 */
	public static boolean nReinasSinHeuristica(int[][] m, int paso) {
		if (paso > m.length)
			return true;

		LinkedList<Regla> L = reglasAplicablesDama(m, paso - 1); // paso - 1 es la fila
		while (!L.isEmpty()) {
			Regla R = elegirRegla(L, m);
			m[R.fil][R.col] = paso;
			if (nReinasSinHeuristica(m, paso + 1)) {
				return true;
			}
			vueltas++;
			m[R.fil][R.col] = 0;
		}
		return false;
	}

	/*
	 * Heuristica 1
	 * "Siempre elegir la regla del medio de lista"
	 */
	public static Regla elegirRegla1(LinkedList<Regla> L, int[][] m) {
		return L.remove((L.size() - 1) / 2);
	}

	public static boolean nReinasConHeuristica1(int[][] m, int paso) {
		if (paso > m.length)
			return true;

		LinkedList<Regla> L = reglasAplicablesDama(m, paso - 1);
		while (!L.isEmpty()) {
			Regla R = elegirRegla1(L, m);
			m[R.fil][R.col] = paso;
			if (nReinasConHeuristica1(m, paso + 1)) {
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
	public static int getPaso(int[][] m) {
		int paso = 0;
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				if (m[i][j] != 0)
					paso = Math.max(paso, m[i][j]);
			}
		}
		return paso + 1;
	}

	public static Regla elegirRegla2(LinkedList<Regla> L, int[][] m) {
		int cantMovMenor = Integer.MAX_VALUE;
		int mejor = -1;
		int paso = getPaso(m);

		for (int i = 0; i < L.size(); i++) {
			Regla R = L.get(i);
			m[R.fil][R.col] = paso;
			int cantMovActual = paso < m.length ? reglasAplicablesDama(m, paso).size() : 0;
			m[R.fil][R.col] = 0;

			if (cantMovActual < cantMovMenor) {
				cantMovMenor = cantMovActual;
				mejor = i;
			}
		}

		return L.remove(mejor);
	}

	public static boolean nReinasConHeuristica2(int[][] m, int paso) {
		if (paso > m.length)
			return true;

		LinkedList<Regla> L = reglasAplicablesDama(m, paso - 1);

		while (!L.isEmpty()) {
			Regla R = elegirRegla2(L, m);
			m[R.fil][R.col] = paso;
			if (nReinasConHeuristica2(m, paso + 1)) {
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

	public static Regla elegirRegla3(LinkedList<Regla> L, int[][] m) {
		int cantMovMenor = Integer.MAX_VALUE;
		ArrayList<Integer> candidatos = new ArrayList<>();
		int paso = getPaso(m);

		for (int i = 0; i < L.size(); i++) {
			Regla R = L.get(i);
			m[R.fil][R.col] = paso;
			int cantMovActual = (paso < m.length) ? reglasAplicablesDama(m, paso).size() : 0;
			m[R.fil][R.col] = 0;

			if (cantMovActual < cantMovMenor) {
				cantMovMenor = cantMovActual;
				candidatos.clear();
				candidatos.add(i);
			} else if (cantMovActual == cantMovMenor) {
				candidatos.add(i);
			}
		}

		int mejor = candidatos.get(new Random().nextInt(candidatos.size()));
		return L.remove(mejor);
	}

	public static boolean nReinasConHeuristica3(int[][] m, int paso) {
		if (paso > m.length)
			return true;

		LinkedList<Regla> L = reglasAplicablesDama(m, paso - 1);

		while (!L.isEmpty()) {
			Regla R = elegirRegla3(L, m);
			m[R.fil][R.col] = paso;
			if (nReinasConHeuristica3(m, paso + 1)) {
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

	public static Regla elegirRegla4(LinkedList<Regla> L, int[][] m) {
		double centro = (m.length - 1) / 2;
		double distMenor = Double.MAX_VALUE;

		int cantMovMenor = Integer.MAX_VALUE;
		int mejor = -1;
		int paso = getPaso(m);

		for (int i = 0; i < L.size(); i++) {
			Regla R = L.get(i);
			m[R.fil][R.col] = paso;
			int cantMovActual = paso < m.length ? reglasAplicablesDama(m, paso).size() : 0;
			double distCentro = distanciaAlCentro(centro, R.col);
			m[R.fil][R.col] = 0;

			if (cantMovActual < cantMovMenor || (cantMovActual == cantMovMenor && distCentro < distMenor)) {
				cantMovMenor = cantMovActual;
				distMenor = distCentro;
				mejor = i;
			}
		}

		return L.remove(mejor);
	}

	public static boolean nReinasConHeuristica4(int[][] m, int paso) {
		if (paso > m.length)
			return true;

		LinkedList<Regla> L = reglasAplicablesDama(m, paso - 1);
		while (!L.isEmpty()) {
			Regla R = elegirRegla4(L, m);
			m[R.fil][R.col] = paso;
			if (nReinasConHeuristica4(m, paso + 1)) {
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

	public static Regla elegirRegla5(LinkedList<Regla> L, int[][] m) {
		int distBordeMenor = Integer.MAX_VALUE;

		int cantMovMenor = Integer.MAX_VALUE;
		int mejor = -1;
		int paso = getPaso(m);

		for (int i = 0; i < L.size(); i++) {
			Regla R = L.get(i);
			m[R.fil][R.col] = paso;
			int cantMovActual = paso < m.length ? reglasAplicablesDama(m, paso).size() : 0;
			int distBordeActual = distanciaABorde(m.length, R.fil, R.col);
			m[R.fil][R.col] = 0;

			if (cantMovActual < cantMovMenor || (cantMovActual == cantMovMenor && distBordeActual < distBordeMenor)) {
				cantMovMenor = cantMovActual;
				distBordeMenor = distBordeActual;
				mejor = i;
			}
		}

		return L.remove(mejor);
	}

	public static boolean nReinasConHeuristica5(int[][] m, int paso) {
		if (paso > m.length)
			return true;

		LinkedList<Regla> L = reglasAplicablesDama(m, paso - 1);

		while (!L.isEmpty()) {
			Regla R = elegirRegla5(L, m);
			m[R.fil][R.col] = paso;
			if (nReinasConHeuristica3(m, paso + 1)) {
				return true;
			}
			vueltas++;
			m[R.fil][R.col] = 0;
		}
		return false;
	}

	/*
	 * Extra
	 * "Solución que es un patron que siempre se repite en todas las soluciones."
	 */
	public static boolean nReinasExtra(int[][] m, int paso) {
		int n = m.length;
		if (n == 2 || n == 3)
			return false;

		int[] cols = new int[n];
		int idx = 0;

		for (int i = 2; i <= n; i += 2) {
			cols[idx++] = i - 1;
		}
		for (int i = 1; i <= n; i += 2) {
			cols[idx++] = i - 1;
		}
		for (int fila = 0; fila < n; fila++) {
			m[fila][cols[fila]] = fila + 1;
		}
		vueltas = 0;
		return true;
	}
}
