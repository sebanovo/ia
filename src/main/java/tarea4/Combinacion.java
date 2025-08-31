package tarea4;

import java.util.*;

public class Combinacion {
  public static int c = 0;

  public static void combiSR(LinkedList<Integer> L1, LinkedList<Integer> L2, int n, int r, int i) {
    if (L2.size() == r) {
      System.out.println(L2);
      c += 1;
      return;
    }
    int k = i;
    while (k < L1.size()) {
      L2.add(L1.get(k));
      combiSR(L1, L2, n, r, k + 1);
      L2.removeLast();
      k = k + 1;
    }
  }

  public static void combiCR(LinkedList<String> L1, LinkedList<String> L2, int n, int r, int i) {
    if (L2.size() == r) {
      System.out.println(L2);
      c += 1;
      return;
    }
    int k = i;
    while (k < L1.size()) {
      L2.add(L1.get(k));
      combiCR(L1, L2, n, r, k);
      L2.removeLast();
      k = k + 1;
    }
  }

  public static void permSR(LinkedList<String> L1, LinkedList<String> L2, int n, int r, int i) {
    if (L2.size() == r) {
      System.out.println(L2);
      c += 1;
      return;
    }
    int k = 0;
    while (k < L1.size()) {
      if (!L2.contains(L1.get(k))) {
        L2.add(L1.get(k));
        permSR(L1, L2, n, r, k + 1);
        L2.removeLast();
      }
      k = k + 1;
    }
  }

  public static void permCR(LinkedList<String> L1, LinkedList<String> L2, int n, int r, int i) {
    if (L2.size() == r) {
      System.out.println(L2);
      c += 1;
      return;
    }
    int k = 0;
    while (k < L1.size()) {
      L2.add(L1.get(k));
      permCR(L1, L2, n, r, k + 1);
      L2.removeLast();
      k = k + 1;
    }
  }

}
