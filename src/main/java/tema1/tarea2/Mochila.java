package tema1.tarea2;

import java.util.*;

public class Mochila {
    public static int c = 0;

    private static int suma(LinkedList<Integer> L) {
        int suma = 0;
        for (int i = 0; i < L.size(); i++) {
            suma += L.get(i);
        }
        return suma;
    }

    /*
     * 1. Encontrar todas las combinaciones de pesos de objetos
     * que se pueden transportar en la mochila y la cantidad de soluciones posibles.
     */
    public static void mochila(LinkedList<Integer> L1, LinkedList<Integer> L2, int max, int i) {
        int sum = suma(L2);
        if (sum <= max) {
            c += 1;
            System.out.println(L2);
        }
        int k = i;
        while (k < L1.size()) {
            L2.add(L1.get(k));
            mochila(L1, L2, max, k + 1);
            L2.removeLast();
            k = k + 1;
        }
    }

    /*
     * 2. Encontrar todas las combinaciones de pesos diferentes que se
     * pueden transportar en la mochila. (Se asume que existen objetos con pesos
     * iguales)
     */
    private static boolean todosDiferentes(LinkedList<Integer> L) {

        for (int i = 0; i < L.size(); i++) {
            for (int j = 0; j < L.size(); j++) {
                if (i != j) {
                    if (L.get(i).equals(L.get(j))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void mochilaDiferentes(LinkedList<Integer> L1, LinkedList<Integer> L2, int max, int i) {
        int sum = suma(L2);
        if (sum <= max) {
            if (todosDiferentes(L2)) {
                c += 1;
                System.out.println(L2);

            }
        }
        int k = i;
        while (k < L1.size()) {
            L2.add(L1.get(k));
            mochilaDiferentes(L1, L2, max, k + 1);
            L2.removeLast();
            k = k + 1;
        }
    }

    /*
     * 3. Encontrar todas las combinaciones de pesos entre los pesos a y b inclusive
     * que se pueden
     * transportar en la mochila.
     */
    public static void mochilaAyB(LinkedList<Integer> L1, LinkedList<Integer> L2, int max, int a, int b) {
        int sum = suma(L2);
        if (sum <= max) {
            c += 1;
            System.out.println(L2);
        }
        int k = a;
        while (k <= b) {
            L2.add(L1.get(k));
            mochilaAyB(L1, L2, max, k + 1, b);
            L2.removeLast();
            k = k + 1;
        }
    }

    /*
     * 4. Encontrar las combinaciones de m-objetos (m <= n), que se pueden
     * transportar en la
     * mochila.
     */
    public static void mochilaMObjetos(LinkedList<Integer> L1, LinkedList<Integer> L2, int max,
            int i, int m) {
        int sum = suma(L2);
        if (sum <= max) {
            if (L2.size() <= m) {
                c += 1;
                System.out.println(L2);
            }

        }
        int k = i;
        while (k < L1.size()) {
            L2.add(L1.get(k));
            mochilaMObjetos(L1, L2, max, k + 1, m);
            L2.removeLast();
            k = k + 1;
        }
    }

    /*
     * 5. Adicionar al menos 2 consultas cualesquiera sobre los objetos de una
     * mochila.
     */
    private static boolean esCuadrado(int n) {
        int raiz = (int) Math.sqrt(n);
        return raiz * raiz == n;
    }

    private static boolean todosCuadrados(LinkedList<Integer> L) {
        for (int i = 0; i < L.size(); i++) {
            if (!esCuadrado(L.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static void mochilaCuadrados(LinkedList<Integer> L1, LinkedList<Integer> L2, int max, int i) {
        int sum = suma(L2);
        if (sum <= max) {
            if (todosCuadrados(L2)) {
                c += 1;
                System.out.println(L2);
            }

        }
        int k = i;
        while (k < L1.size()) {
            L2.add(L1.get(k));
            mochilaCuadrados(L1, L2, max, k + 1);
            L2.removeLast();
            k = k + 1;
        }
    }

    private static boolean esFibonacci(int n) {
        int n1 = (5 * n * n) + 4;
        int n2 = (5 * n * n) - 4;
        return esCuadrado(n1) || esCuadrado(n2);
    }

    private static boolean todosFibonacci(LinkedList<Integer> L) {
        for (int i = 0; i < L.size(); i++) {
            if (!esFibonacci(L.get(i)))
                return false;
        }
        return true;
    }

    public static void mochilaFibonacci(LinkedList<Integer> L1, LinkedList<Integer> L2, int max, int i) {
        int sum = suma(L2);
        if (sum <= max) {
            if (todosFibonacci(L2)) {
                c += 1;
                System.out.println(L2);
            }
        }
        int k = i;
        while (k < L1.size()) {
            L2.add(L1.get(k));
            mochilaFibonacci(L1, L2, max, k + 1);
            L2.removeLast();
            k = k + 1;
        }
    }
}
