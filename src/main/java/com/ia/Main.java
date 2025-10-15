
package com.ia;

import java.util.*;

// import tema1.tarea1.*;
// import tema1.tarea2.*;
// import tema1.tarea3.*;
// import tema1.tarea4.*;
// import modelos.examen1.*;
// import tema2.tarea1.*;
// import tema2.tarea2.*;
// import tema2.tarea3.*;
// import tema2.tarea4.*;
import modelos.examen2.*;;
// import tema3.tarea1.*;

/**
 *
 * @author HP
 */
public class Main {
    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        System.out.println();
        int a = 3, b = 3;
        int[][] m = new int[a][b];
        // con n = 3
        // rey=235
        // caballo=2
        // torre=64
        // alfil=2
        // dama=1172

        // Rey sin
        Laberinto.laberintoDama(m, 0, 0, a - 1, b - 1, 1);
        System.out.println(Laberinto.c);
        Laberinto.c = 0;

        // // Rey con
        // m[0][2] = -1;
        // m[1][2] = -1;
        // m[2][2] = -1;
        // Laberinto.laberintoRey(m, 0, 0, a - 1, b - 1, 1);
        // System.out.println(Laberinto.c);
        // Laberinto.c = 0;
        //
        // // Caballo sin
        // Laberinto.laberintoCaballo(m, 0, 0, a - 1, b - 1, 1);
        // System.out.println(Laberinto.c);
        // Laberinto.c = 0;
        //
        // // Caballo con
        // m[0][2] = -1;
        // m[1][2] = -1;
        // m[2][2] = -1;
        // Laberinto.laberintoCaballo(m, 0, 0, a - 1, b - 1, 1);
        // System.out.println(Laberinto.c);
        // Laberinto.c = 0;
        //
        // // Torre sin
        // Laberinto.laberintoTorre(m, 0, 0, a - 1, b - 1, 1);
        // System.out.println(Laberinto.c);
        // Laberinto.c = 0;
        //
        // // Torre con
        // m[0][2] = -1;
        // m[1][2] = -1;
        // m[2][2] = -1;
        // Laberinto.laberintoTorre(m, 0, 0, a - 1, b - 1, 1);
        // System.out.println(Laberinto.c);
        // Laberinto.c = 0;
        //
        // // Alfil sin
        // Laberinto.laberintoAlfil(m, 0, 0, a - 1, b - 1, 1);
        // System.out.println(Laberinto.c);
        // Laberinto.c = 0;
        //
        // // Alfil con
        // m[0][2] = -1;
        // m[1][2] = -1;
        // m[2][2] = -1;
        // Laberinto.laberintoAlfil(m, 0, 0, a - 1, b - 1, 1);
        // System.out.println(Laberinto.c);
        // Laberinto.c = 0;
        //
        // // Dama sin
        // Laberinto.laberintoDama(m, 0, 0, a - 1, b - 1, 1);
        // System.out.println(Laberinto.c);
        // Laberinto.c = 0;
        //
        // // Dama con
        // m[0][2] = -1;
        // m[1][2] = -1;
        // m[2][2] = -1;
        // Laberinto.laberintoDama(m, 0, 0, a - 1, b - 1, 1);
        // System.out.println(Laberinto.c);
        // Laberinto.c = 0;
    }
}