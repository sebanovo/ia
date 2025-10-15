package tema1.tarea2;

import java.util.*;

public class VarianteMochila {
    public static class Objeto {
        int peso, volumen;
        String color;

        public Objeto(int peso, int volumen, String color) {
            this.peso = peso;
            this.volumen = volumen;
            this.color = color;
        }

        @Override
        public String toString() {
            return "(" + peso + "," + volumen + "," + color + ")";
        }
    }

    public static int c = 0;
    /*
     * Proponer y resolver al menos 5 consultas interesantes, sobre el problema de
     * la mochila, utilizando diversas condiciones de los objetos. En lo posible,
     * citar fuentes de las investigaciones.
     */

    /*
     * 1. Encontrar todas las combinaciones de pesos de objetos y volumenes de
     * objetos como restricciones
     * que se pueden transportar en la mochila y la cantidad de soluciones posibles.
     */
    public static void mochilaPesoVolumen(
            List<Objeto> L1,
            LinkedList<Objeto> L2,
            int maxP, int maxV,
            int i) {
        int p = 0, v = 0;
        for (Objeto o : L2) {
            p += o.peso;
            v += o.volumen;
        }
        if (p <= maxP && v <= maxV) {
            System.out.println(L2);
            c++;
        }
        int k = i;
        while (k < L1.size()) {
            L2.add(L1.get(k));
            mochilaPesoVolumen(L1, L2, maxP, maxV, k + 1);
            L2.removeLast();
            k++;
        }
    }

    /*
     * 2. Combinaciones que contengan al menos un objeto de cierto color
     */
    private static boolean contieneColor(LinkedList<Objeto> L, String colorBuscado) {
        for (Objeto o : L) {
            if (o.color.equals(colorBuscado))
                return true;
        }
        return false;
    }

    public static void mochilaConColor(
            List<Objeto> L1,
            LinkedList<Objeto> L2,
            int maxP, String color,
            int i) {
        int p = 0;
        for (Objeto o : L2) {
            p += o.peso;
        }
        if (p <= maxP && contieneColor(L2, color)) {
            System.out.println(L2);
            c++;
        }
        int k = i;
        while (k < L1.size()) {
            L2.add(L1.get(k));
            mochilaConColor(L1, L2, maxP, color, k + 1);
            L2.removeLast();
            k++;
        }
    }

    /*
     * 3. Combinaciones que contengan objetos con colores Diferentes
     */
    private static boolean coloresDiferentes(LinkedList<Objeto> L) {
        for (int i = 0; i < L.size(); i++) {
            for (int j = 0; j < L.size(); j++) {
                if (i != j) {
                    if (L.get(i).color.equals(L.get(j).color))
                        return false;
                }
            }
        }
        return true;
    }

    public static void mochilaColoresDiferentes(
            List<Objeto> L1,
            LinkedList<Objeto> L2,
            int maxP,
            int i) {
        int p = 0;
        for (Objeto o : L2) {
            p += o.peso;
        }
        if (p <= maxP) {
            if (coloresDiferentes(L2)) {
                System.out.println(L2);
                c++;
            }
        }
        int k = i;
        while (k < L1.size()) {
            L2.add(L1.get(k));
            mochilaColoresDiferentes(L1, L2, maxP, k + 1);
            L2.removeLast();
            k++;
        }
    }

    /*
     * 4. Combinaciones que contengan objetos con colores cuadrados
     */
    private static boolean esCuadrado(int n) {
        int raiz = (int) Math.sqrt(n);
        return raiz * raiz == n;
    }

    private static boolean todosVolumenCuadrado(LinkedList<Objeto> L) {
        for (int i = 0; i < L.size(); i++) {
            if (!esCuadrado(L.get(i).volumen)) {
                return false;
            }
        }
        return true;
    }

    public static void mochilaVolumenCuadrado(
            List<Objeto> L1,
            LinkedList<Objeto> L2,
            int maxP,
            int i) {
        int p = 0;
        for (Objeto o : L2) {
            p += o.peso;
        }
        if (p <= maxP) {
            if (todosVolumenCuadrado(L2)) {
                System.out.println(L2);
                c++;
            }
        }
        int k = i;
        while (k < L1.size()) {
            L2.add(L1.get(k));
            mochilaVolumenCuadrado(L1, L2, maxP, k + 1);
            L2.removeLast();
            k++;
        }
    }

    /*
     * 5. Combinaciones que contengan objetos con volumenes fibonacci
     */
    private static boolean esFibonacci(int n) {
        int n1 = (5 * n * n) + 4;
        int n2 = (5 * n * n) - 4;
        return esCuadrado(n1) || esCuadrado(n2);
    }

    public static boolean todosVolumenFibonacci(LinkedList<Objeto> L) {
        for (int i = 0; i < L.size(); i++) {
            if (!esFibonacci(L.get(i).volumen))
                return false;
        }
        return true;
    }

    public static void mochilaVolumenFibonacci(
            List<Objeto> L1,
            LinkedList<Objeto> L2,
            int maxP,
            int i) {
        int p = 0;
        for (Objeto o : L2) {
            p += o.peso;
        }
        if (p <= maxP) {
            if (todosVolumenFibonacci(L2)) {
                System.out.println(L2);
                c++;
            }
        }
        int k = i;
        while (k < L1.size()) {
            L2.add(L1.get(k));
            mochilaVolumenFibonacci(L1, L2, maxP, k + 1);
            L2.removeLast();
            k++;
        }
    }
}
