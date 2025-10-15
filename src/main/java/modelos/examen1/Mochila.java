package modelos.examen1;

import java.util.*;

public class Mochila {
    public static class Objeto {
        int peso;
        String color;

        public Objeto(int peso, String color) {
            this.peso = peso;
            this.color = color;
        }

        @Override
        public String toString() {
            return "(" + peso + "," + color + ")";
        }
    }

    /*
     * 1. Encontrar todas las combinaciones de pesos de objetos que se pueden
     * transportar en la mochila.
     */
    private static int sumaPeso(LinkedList<Objeto> L) {
        int suma = 0;
        for (Objeto objeto : L) {
            suma += objeto.peso;
        }
        return suma;
    }

    public static void mochila(
            List<Objeto> L1,
            LinkedList<Objeto> L2,
            LinkedList<LinkedList<Objeto>> L3,
            int maxP,
            int i) {

        int p = sumaPeso(L2);
        if (p <= maxP) {
            L3.add(new LinkedList<Objeto>(L2));
        }
        int k = i;
        while (k < L1.size()) {
            L2.add(L1.get(k));
            mochila(L1, L2, L3, maxP, k + 1);
            L2.removeLast();
            k++;
        }
    }

    /*
     * 2. Encontrar todas las combinaciones de pesos de objetos que se pueden
     * transportar en la mochila del mismo color.
     */
    private static boolean todosColoresIguales(LinkedList<Objeto> L) {
        if (L.isEmpty())
            return true;
        Objeto o1 = L.get(0);
        for (int i = 1; i < L.size(); i++) {
            if (!L.get(i).color.equals(o1.color))
                return false;
        }
        return true;
    }

    public static void mochilaColoresIguales(
            List<Objeto> L1,
            LinkedList<Objeto> L2,
            LinkedList<LinkedList<Objeto>> L3,
            int maxP,
            int i) {

        int p = sumaPeso(L2);
        if (p <= maxP) {
            if (todosColoresIguales(L2)) {
                L3.add(new LinkedList<Objeto>(L2));
            }
        }
        int k = i;
        while (k < L1.size()) {
            L2.add(L1.get(k));
            mochilaColoresIguales(L1, L2, L3, maxP, k + 1);
            L2.removeLast();
            k++;
        }
    }
}
