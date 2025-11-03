package tema2.tarea3;

import java.util.*;

public class Laberinto {
    public static class Regla {

        public int fil;
        public int col;

        public Regla(int i, int j) {
            this.fil = i;
            this.col = j;
        }
    }

    private static boolean posValida(int[][] m, int i, int j) {
        return i >= 0 && i < m.length && j >= 0 && j < m[i].length && m[i][j] == 0;
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

    private static Regla elegirRegla(LinkedList<Regla> L1) {
        return L1.removeFirst();
    }
    /*
     * Dado una matriz de n x m, inicialmente con valores de ceros (Sin Atajos).
     * Implementar Algoritmos con llamadas recursivas desde un ciclo, para cada uno
     * de los problemas de los movimientos de: Rey, Caballo, Torre, Alfil y Dama.
     */
    /*
     * Implementar y ejecutar para diferentes valores de n y m. (No necesariamente
     * matriz cuadrada)
     */
    /*
  Rey
  // @formatter:off
          _____                    _____                _____          
         /\    \                  /\    \              |\    \         
        /::\    \                /::\    \             |:\____\        
       /::::\    \              /::::\    \            |::|   |        
      /::::::\    \            /::::::\    \           |::|   |        
     /:::/\:::\    \          /:::/\:::\    \          |::|   |        
    /:::/__\:::\    \        /:::/__\:::\    \         |::|   |        
   /::::\   \:::\    \      /::::\   \:::\    \        |::|   |        
  /::::::\   \:::\    \    /::::::\   \:::\    \       |::|___|______  
 /:::/\:::\   \:::\____\  /:::/\:::\   \:::\    \      /::::::::\    \ 
/:::/  \:::\   \:::|    |/:::/__\:::\   \:::\____\    /::::::::::\____\
\::/   |::::\  /:::|____|\:::\   \:::\   \::/    /   /:::/~~~~/~~      
 \/____|:::::\/:::/    /  \:::\   \:::\   \/____/   /:::/    /         
       |:::::::::/    /    \:::\   \:::\    \      /:::/    /          
       |::|\::::/    /      \:::\   \:::\____\    /:::/    /           
       |::| \::/____/        \:::\   \::/    /    \::/    /            
       |::|  ~|               \:::\   \/____/      \/____/             
       |::|   |                \:::\    \                              
       \::|   |                 \:::\____\                             
        \:|   |                  \::/    /                             
         \|___|                   \/____/                              
  // @formatter:on
  */
    /*
     * a) Algoritmo para mostrar todos los caminos posibles desde una posición
     * inicial a una posición final. Además, mostrar la cantidad de soluciones
     * posibles.
     */

    public static int c = 0;

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

    public static void laberintoReyA(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        if (!posValida(m, i, j)) {
            return;
        }
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            mostrar(m);
            c++;
        }
        LinkedList<Regla> L1 = reglasAplicablesRey(m, i, j);
        while (!L1.isEmpty()) {
            Regla R = elegirRegla(L1);
            laberintoReyA(m, R.fil, R.col, iFin, jFin, paso + 1);
            m[R.fil][R.col] = 0;
        }
    }

    /*
     * 
     * b) Algoritmo para mostrar todos los caminos posibles desde una posición
     * inicial a una posición final tal que se visiten todas las casillas de la
     * matriz. Además, mostrar la cantidad de soluciones posibles.
     */
    private static boolean tieneCeros(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (m[i][j] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void laberintoReyB(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        if (!posValida(m, i, j)) {
            return;
        }
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            if (!tieneCeros(m)) {
                mostrar(m);
                c++;
            }
        }
        LinkedList<Regla> L1 = reglasAplicablesRey(m, i, j);
        while (!L1.isEmpty()) {
            Regla R = elegirRegla(L1);
            laberintoReyB(m, R.fil, R.col, iFin, jFin, paso + 1);
            m[R.fil][R.col] = 0;
        }
    }

    /*
     * 
     * c) Algoritmo para mostrar todos los caminos posibles desde una posición
     * inicial a una posición final tal que NO se visiten todas las casillas de la
     * matriz. Además, mostrar la cantidad de soluciones posibles.
     */
    public static void laberintoReyC(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        if (!posValida(m, i, j)) {
            return;
        }
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            if (tieneCeros(m)) {
                mostrar(m);
                c++;
            }
        }
        LinkedList<Regla> L1 = reglasAplicablesRey(m, i, j);
        while (!L1.isEmpty()) {
            Regla R = elegirRegla(L1);
            laberintoReyC(m, R.fil, R.col, iFin, jFin, paso + 1);
            m[R.fil][R.col] = 0;
        }
    }

    /*
     * 
     * d) Algoritmo para mostrar todos los caminos posibles de máxima longitud desde
     * una posición inicial a una posición final. Además, mostrar la cantidad de
     * soluciones posibles.
     */
    public static void laberintoReyDAux(int[][] m, LinkedList<int[][]> L, int i, int j, int iFin, int jFin, int paso) {
        if (!posValida(m, i, j)) {
            return;
        }
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            L.add(Arrays.stream(m).map(fila -> fila.clone()).toArray($ -> m.clone()));
        }
        LinkedList<Regla> L1 = reglasAplicablesRey(m, i, j);
        while (!L1.isEmpty()) {
            Regla R = elegirRegla(L1);
            laberintoReyDAux(m, L, R.fil, R.col, iFin, jFin, paso + 1);
            m[R.fil][R.col] = 0;
        }
    }

    private static int contarCeros(int[][] m) {
        int cantidad = 0;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (m[i][j] == 0)
                    cantidad++;
            }
        }
        return cantidad;
    }

    public static void laberintoReyD(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        LinkedList<int[][]> soluciones = new LinkedList<>();
        laberintoReyDAux(m, soluciones, i, j, iFin, jFin, paso);
        LinkedList<int[][]> solucionesMaximas = new LinkedList<>();

        // filtrar las soluciones que sean maximas
        if (soluciones.size() == 0)
            return;
        int minCeros = contarCeros(soluciones.getFirst());
        for (int[][] sol : soluciones) {
            int ceros = contarCeros(sol);
            if (ceros < minCeros) {
                minCeros = ceros;
            }
        }

        // filtramos las soluciones que tengan esa máxima cantidad de ceros
        for (int[][] sol : soluciones) {
            if (contarCeros(sol) == minCeros) {
                solucionesMaximas.add(sol);
                c++;
            }
        }

        // mostrar soluciones minimas
        for (int[][] sol : solucionesMaximas) {
            mostrar(sol);
        }
    }

    /*
     * 
     * e) Algoritmo para mostrar todos los caminos posibles de mínima longitud desde
     * una posición inicial a una posición final. Además, mostrar la cantidad de
     * soluciones posibles.
     */
    public static void laberintoReyEAux(int[][] m, LinkedList<int[][]> L, int i, int j, int iFin, int jFin, int paso) {
        if (!posValida(m, i, j)) {
            return;
        }
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            L.add(Arrays.stream(m).map(fila -> fila.clone()).toArray($ -> m.clone()));
        }
        LinkedList<Regla> L1 = reglasAplicablesRey(m, i, j);
        while (!L1.isEmpty()) {
            Regla R = elegirRegla(L1);
            laberintoReyEAux(m, L, R.fil, R.col, iFin, jFin, paso + 1);
            m[R.fil][R.col] = 0;
        }
    }

    public static void laberintoReyE(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        LinkedList<int[][]> soluciones = new LinkedList<>();
        laberintoReyEAux(m, soluciones, i, j, iFin, jFin, paso);
        LinkedList<int[][]> solucionesMinimas = new LinkedList<>();

        // filtrar las soluciones que sean minimas
        if (soluciones.size() == 0)
            return;
        int maxCeros = contarCeros(soluciones.getFirst());
        for (int[][] sol : soluciones) {
            int ceros = contarCeros(sol);
            if (ceros > maxCeros) {
                maxCeros = ceros;
            }
        }

        // filtramos las soluciones que tengan esa máxima cantidad de ceros
        for (int[][] sol : soluciones) {
            if (contarCeros(sol) == maxCeros) {
                solucionesMinimas.add(sol);
                c++;
            }
        }

        // mostrar soluciones minimas
        for (int[][] sol : solucionesMinimas) {
            mostrar(sol);
        }
    }

    /*
     * f) Algoritmo para resolver cualquiera de los incisos anteriores utilizando
     * una Lista de Matrices.
     */
    public static void laberintoRey(int[][] m, LinkedList<int[][]> L, int i, int j, int iFin, int jFin, int paso) {
        if (!posValida(m, i, j)) {
            return;
        }
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            L.add(Arrays.stream(m).map(fila -> fila.clone()).toArray($ -> m.clone()));
            c++;
        }
        LinkedList<Regla> L1 = reglasAplicablesRey(m, i, j);
        while (!L1.isEmpty()) {
            Regla R = elegirRegla(L1);
            laberintoRey(m, L, R.fil, R.col, iFin, jFin, paso + 1);
            m[R.fil][R.col] = 0;
        }
    }

    /*
  Caballo
  // @formatter:off
          _____                    _____                    _____          
         /\    \                  /\    \                  /\    \         
        /::\    \                /::\    \                /::\    \        
       /::::\    \              /::::\    \              /::::\    \       
      /::::::\    \            /::::::\    \            /::::::\    \      
     /:::/\:::\    \          /:::/\:::\    \          /:::/\:::\    \     
    /:::/  \:::\    \        /:::/__\:::\    \        /:::/__\:::\    \    
   /:::/    \:::\    \      /::::\   \:::\    \      /::::\   \:::\    \   
  /:::/    / \:::\    \    /::::::\   \:::\    \    /::::::\   \:::\    \  
 /:::/    /   \:::\    \  /:::/\:::\   \:::\    \  /:::/\:::\   \:::\ ___\ 
/:::/____/     \:::\____\/:::/  \:::\   \:::\____\/:::/__\:::\   \:::|    |
\:::\    \      \::/    /\::/    \:::\  /:::/    /\:::\   \:::\  /:::|____|
 \:::\    \      \/____/  \/____/ \:::\/:::/    /  \:::\   \:::\/:::/    / 
  \:::\    \                       \::::::/    /    \:::\   \::::::/    /  
   \:::\    \                       \::::/    /      \:::\   \::::/    /   
    \:::\    \                      /:::/    /        \:::\  /:::/    /    
     \:::\    \                    /:::/    /          \:::\/:::/    /     
      \:::\    \                  /:::/    /            \::::::/    /      
       \:::\____\                /:::/    /              \::::/    /       
        \::/    /                \::/    /                \::/____/        
         \/____/                  \/____/                  ~~              
                                                                           
          _____                    _____            _____                  
         /\    \                  /\    \          /\    \                 
        /::\    \                /::\____\        /::\____\                
       /::::\    \              /:::/    /       /:::/    /                
      /::::::\    \            /:::/    /       /:::/    /                 
     /:::/\:::\    \          /:::/    /       /:::/    /                  
    /:::/__\:::\    \        /:::/    /       /:::/    /                   
   /::::\   \:::\    \      /:::/    /       /:::/    /                    
  /::::::\   \:::\    \    /:::/    /       /:::/    /                     
 /:::/\:::\   \:::\    \  /:::/    /       /:::/    /                      
/:::/  \:::\   \:::\____\/:::/____/       /:::/____/                       
\::/    \:::\  /:::/    /\:::\    \       \:::\    \                       
 \/____/ \:::\/:::/    /  \:::\    \       \:::\    \                      
          \::::::/    /    \:::\    \       \:::\    \                     
           \::::/    /      \:::\    \       \:::\    \                    
           /:::/    /        \:::\    \       \:::\    \                   
          /:::/    /          \:::\    \       \:::\    \                  
         /:::/    /            \:::\    \       \:::\    \                 
        /:::/    /              \:::\____\       \:::\____\                
        \::/    /                \::/    /        \::/    /                
         \/____/                  \/____/          \/____/                 
                                                                           
         _______                                                           
        /::\    \                                                          
       /::::\    \                                                         
      /::::::\    \                                                        
     /::::::::\    \                                                       
    /:::/~~\:::\    \                                                      
   /:::/    \:::\    \                                                     
  /:::/    / \:::\    \                                                    
 /:::/____/   \:::\____\                                                   
|:::|    |     |:::|    |                                                  
|:::|____|     |:::|    |                                                  
 \:::\    \   /:::/    /                                                   
  \:::\    \ /:::/    /                                                    
   \:::\    /:::/    /                                                     
    \:::\__/:::/    /                                                      
     \::::::::/    /                                                       
      \::::::/    /                                                        
       \::::/    /                                                         
        \::/____/                                                          
         ~~                                                                
  // @formatter:on
  */
    /*
     * a) Algoritmo para mostrar todos los caminos posibles desde una posición
     * inicial a una posición final. Además, mostrar la cantidad de soluciones
     * posibles.
     */
    private static LinkedList<Regla> reglasAplicablesCaballo(int[][] m, int i, int j) {
        LinkedList<Regla> L = new LinkedList<>();

        int[][] movimientos = {
                { -2, -1 }, { -2, 1 },
                { -1, 2 }, { 1, 2 },
                { 2, 1 }, { 2, -1 },
                { 1, -2 }, { -1, -2 }
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

    public static void laberintoCaballoA(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        if (!posValida(m, i, j)) {
            return;
        }
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            mostrar(m);
            c++;
        }
        LinkedList<Regla> L1 = reglasAplicablesCaballo(m, i, j);
        while (!L1.isEmpty()) {
            Regla R = elegirRegla(L1);
            laberintoCaballoA(m, R.fil, R.col, iFin, jFin, paso + 1);
            m[R.fil][R.col] = 0;
        }
    }

    /*
     * 
     * b) Algoritmo para mostrar todos los caminos posibles desde una posición
     * inicial a una posición final tal que se visiten todas las casillas de la
     * matriz. Además, mostrar la cantidad de soluciones posibles.
     */
    public static void laberintoCaballoB(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        if (!posValida(m, i, j)) {
            return;
        }
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            if (!tieneCeros(m)) {
                mostrar(m);
                c++;
            }
        }
        LinkedList<Regla> L1 = reglasAplicablesCaballo(m, i, j);
        while (!L1.isEmpty()) {
            Regla R = elegirRegla(L1);
            laberintoCaballoB(m, R.fil, R.col, iFin, jFin, paso + 1);
            m[R.fil][R.col] = 0;
        }
    }

    /*
     * 
     * c) Algoritmo para mostrar todos los caminos posibles desde una posición
     * inicial a una posición final tal que NO se visiten todas las casillas de la
     * matriz. Además, mostrar la cantidad de soluciones posibles.
     */
    public static void laberintoCaballoC(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        if (!posValida(m, i, j)) {
            return;
        }
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            if (tieneCeros(m)) {
                mostrar(m);
                c++;
            }
        }
        LinkedList<Regla> L1 = reglasAplicablesCaballo(m, i, j);
        while (!L1.isEmpty()) {
            Regla R = elegirRegla(L1);
            laberintoCaballoC(m, R.fil, R.col, iFin, jFin, paso + 1);
            m[R.fil][R.col] = 0;
        }
    }

    /*
     * 
     * d) Algoritmo para mostrar todos los caminos posibles de máxima longitud desde
     * una posición inicial a una posición final. Además, mostrar la cantidad de
     * soluciones posibles.
     */
    public static void laberintoCaballoDAux(int[][] m, LinkedList<int[][]> L, int i, int j, int iFin, int jFin,
            int paso) {
        if (!posValida(m, i, j)) {
            return;
        }
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            L.add(Arrays.stream(m).map(fila -> fila.clone()).toArray($ -> m.clone()));
        }
        LinkedList<Regla> L1 = reglasAplicablesCaballo(m, i, j);
        while (!L1.isEmpty()) {
            Regla R = elegirRegla(L1);
            laberintoCaballoDAux(m, L, R.fil, R.col, iFin, jFin, paso + 1);
            m[R.fil][R.col] = 0;
        }
    }

    public static void laberintoCaballoD(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        LinkedList<int[][]> soluciones = new LinkedList<>();
        laberintoCaballoDAux(m, soluciones, i, j, iFin, jFin, paso);
        LinkedList<int[][]> solucionesMaximas = new LinkedList<>();

        // filtrar las soluciones que sean maximas
        if (soluciones.size() == 0)
            return;
        int minCeros = contarCeros(soluciones.getFirst());
        for (int[][] sol : soluciones) {
            int ceros = contarCeros(sol);
            if (ceros < minCeros) {
                minCeros = ceros;
            }
        }

        // filtramos las soluciones que tengan esa máxima cantidad de ceros
        for (int[][] sol : soluciones) {
            if (contarCeros(sol) == minCeros) {
                solucionesMaximas.add(sol);
                c++;
            }
        }

        // mostrar soluciones minimas
        for (int[][] sol : solucionesMaximas) {
            mostrar(sol);
        }
    }

    /*
     * 
     * e) Algoritmo para mostrar todos los caminos posibles de mínima longitud desde
     * una posición inicial a una posición final. Además, mostrar la cantidad de
     * soluciones posibles.
     */
    public static void laberintoCaballoEAux(int[][] m, LinkedList<int[][]> L, int i, int j, int iFin, int jFin,
            int paso) {
        if (!posValida(m, i, j)) {
            return;
        }
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            L.add(Arrays.stream(m).map(fila -> fila.clone()).toArray($ -> m.clone()));
        }
        LinkedList<Regla> L1 = reglasAplicablesCaballo(m, i, j);
        while (!L1.isEmpty()) {
            Regla R = elegirRegla(L1);
            laberintoCaballoEAux(m, L, R.fil, R.col, iFin, jFin, paso + 1);
            m[R.fil][R.col] = 0;
        }
    }

    public static void laberintoCaballoE(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        LinkedList<int[][]> soluciones = new LinkedList<>();
        laberintoCaballoEAux(m, soluciones, i, j, iFin, jFin, paso);
        LinkedList<int[][]> solucionesMinimas = new LinkedList<>();

        // filtrar las soluciones que sean minimas
        if (soluciones.size() == 0)
            return;
        int maxCeros = contarCeros(soluciones.getFirst());
        for (int[][] sol : soluciones) {
            int ceros = contarCeros(sol);
            if (ceros > maxCeros) {
                maxCeros = ceros;
            }
        }

        // filtramos las soluciones que tengan esa máxima cantidad de ceros
        for (int[][] sol : soluciones) {
            if (contarCeros(sol) == maxCeros) {
                solucionesMinimas.add(sol);
                c++;
            }
        }

        // mostrar soluciones minimas
        for (int[][] sol : solucionesMinimas) {
            mostrar(sol);
        }
    }

    /*
     * f) Algoritmo para resolver cualquiera de los incisos anteriores utilizando
     * una Lista de Matrices.
     */
    public static void laberintoCaballo(int[][] m, LinkedList<int[][]> L, int i, int j, int iFin, int jFin, int paso) {
        if (!posValida(m, i, j)) {
            return;
        }
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            L.add(Arrays.stream(m).map(fila -> fila.clone()).toArray($ -> m.clone()));
            c++;
        }
        LinkedList<Regla> L1 = reglasAplicablesCaballo(m, i, j);
        while (!L1.isEmpty()) {
            Regla R = elegirRegla(L1);
            laberintoCaballo(m, L, R.fil, R.col, iFin, jFin, paso + 1);
            m[R.fil][R.col] = 0;
        }
    }

    /*
  Torre 
  // @formatter:off
      _____                   _______                   _____              
     /\    \                 /::\    \                 /\    \             
    /::\    \               /::::\    \               /::\    \            
    \:::\    \             /::::::\    \             /::::\    \           
     \:::\    \           /::::::::\    \           /::::::\    \          
      \:::\    \         /:::/~~\:::\    \         /:::/\:::\    \         
       \:::\    \       /:::/    \:::\    \       /:::/__\:::\    \        
       /::::\    \     /:::/    / \:::\    \     /::::\   \:::\    \       
      /::::::\    \   /:::/____/   \:::\____\   /::::::\   \:::\    \      
     /:::/\:::\    \ |:::|    |     |:::|    | /:::/\:::\   \:::\____\     
    /:::/  \:::\____\|:::|____|     |:::|    |/:::/  \:::\   \:::|    |    
   /:::/    \::/    / \:::\    \   /:::/    / \::/   |::::\  /:::|____|    
  /:::/    / \/____/   \:::\    \ /:::/    /   \/____|:::::\/:::/    /     
 /:::/    /             \:::\    /:::/    /          |:::::::::/    /      
/:::/    /               \:::\__/:::/    /           |::|\::::/    /       
\::/    /                 \::::::::/    /            |::| \::/____/        
 \/____/                   \::::::/    /             |::|  ~|              
                            \::::/    /              |::|   |              
                             \::/____/               \::|   |              
                              ~~                      \:|   |              
                                                       \|___|              
                                                                           
          _____                    _____                   
         /\    \                  /\    \                  
        /::\    \                /::\    \        
       /::::\    \              /::::\    \       
      /::::::\    \            /::::::\    \      
     /:::/\:::\    \          /:::/\:::\    \     
    /:::/__\:::\    \        /:::/__\:::\    \    
   /::::\   \:::\    \      /::::\   \:::\    \   
  /::::::\   \:::\    \    /::::::\   \:::\    \  
 /:::/\:::\   \:::\____\  /:::/\:::\   \:::\    \ 
/:::/  \:::\   \:::|    |/:::/__\:::\   \:::\____\
\::/   |::::\  /:::|____|\:::\   \:::\   \::/    /
 \/____|:::::\/:::/    /  \:::\   \:::\   \/____/ 
       |:::::::::/    /    \:::\   \:::\    \     
       |::|\::::/    /      \:::\   \:::\____\    
       |::| \::/____/        \:::\   \::/    /    
       |::|  ~|               \:::\   \/____/     
       |::|   |                \:::\    \         
       \::|   |                 \:::\____\        
        \:|   |                  \::/    /        
         \|___|                   \/____/                                                      
  // @formatter:on
  */
    /*
     * a) Algoritmo para mostrar todos los caminos posibles desde una posición
     * inicial a una posición final. Además, mostrar la cantidad de soluciones
     * posibles.
     */
    private static LinkedList<Regla> reglasAplicablesTorre(int[][] m, int i, int j) {
        LinkedList<Regla> L = new LinkedList<>();

        int[][] direcciones = {
                { -1, 0 }, // arriba
                { 0, -1 }, // izquierda
                { 1, 0 }, // abajo
                { 0, 1 } // derecha
        };

        for (int[] d : direcciones) {
            int ni = i + d[0];
            int nj = j + d[1];
            while (posValida(m, ni, nj)) {
                L.add(new Regla(ni, nj));
                ni += d[0];
                nj += d[1];
            }
        }

        return L;
    }

    public static void laberintoTorreA(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        if (!posValida(m, i, j)) {
            return;
        }
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            mostrar(m);
            c++;
        }
        LinkedList<Regla> L1 = reglasAplicablesTorre(m, i, j);
        while (!L1.isEmpty()) {
            Regla R = elegirRegla(L1);
            laberintoTorreA(m, R.fil, R.col, iFin, jFin, paso + 1);
            m[R.fil][R.col] = 0;
        }
    }

    /*
     * 
     * b) Algoritmo para mostrar todos los caminos posibles desde una posición
     * inicial a una posición final tal que se visiten todas las casillas de la
     * matriz. Además, mostrar la cantidad de soluciones posibles.
     */
    public static void laberintoTorreB(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        if (!posValida(m, i, j)) {
            return;
        }
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            if (!tieneCeros(m)) {
                mostrar(m);
                c++;
            }
        }
        LinkedList<Regla> L1 = reglasAplicablesTorre(m, i, j);
        while (!L1.isEmpty()) {
            Regla R = elegirRegla(L1);
            laberintoTorreB(m, R.fil, R.col, iFin, jFin, paso + 1);
            m[R.fil][R.col] = 0;
        }
    }

    /*
     * 
     * c) Algoritmo para mostrar todos los caminos posibles desde una posición
     * inicial a una posición final tal que NO se visiten todas las casillas de la
     * matriz. Además, mostrar la cantidad de soluciones posibles.
     */
    public static void laberintoTorreC(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        if (!posValida(m, i, j)) {
            return;
        }
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            if (tieneCeros(m)) {
                mostrar(m);
                c++;
            }
        }
        LinkedList<Regla> L1 = reglasAplicablesTorre(m, i, j);
        while (!L1.isEmpty()) {
            Regla R = elegirRegla(L1);
            laberintoTorreC(m, R.fil, R.col, iFin, jFin, paso + 1);
            m[R.fil][R.col] = 0;
        }
    }

    /*
     * 
     * d) Algoritmo para mostrar todos los caminos posibles de máxima longitud desde
     * una posición inicial a una posición final. Además, mostrar la cantidad de
     * soluciones posibles.
     */
    public static void laberintoTorreDAux(int[][] m, LinkedList<int[][]> L, int i, int j, int iFin, int jFin,
            int paso) {
        if (!posValida(m, i, j)) {
            return;
        }
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            L.add(Arrays.stream(m).map(fila -> fila.clone()).toArray($ -> m.clone()));
        }
        LinkedList<Regla> L1 = reglasAplicablesTorre(m, i, j);
        while (!L1.isEmpty()) {
            Regla R = elegirRegla(L1);
            laberintoTorreDAux(m, L, R.fil, R.col, iFin, jFin, paso + 1);
            m[R.fil][R.col] = 0;
        }
    }

    public static void laberintoTorreD(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        LinkedList<int[][]> soluciones = new LinkedList<>();
        laberintoTorreDAux(m, soluciones, i, j, iFin, jFin, paso);
        LinkedList<int[][]> solucionesMaximas = new LinkedList<>();

        // filtrar las soluciones que sean maximas
        if (soluciones.size() == 0)
            return;
        int minCeros = contarCeros(soluciones.getFirst());
        for (int[][] sol : soluciones) {
            int ceros = contarCeros(sol);
            if (ceros < minCeros) {
                minCeros = ceros;
            }
        }

        // filtramos las soluciones que tengan esa máxima cantidad de ceros
        for (int[][] sol : soluciones) {
            if (contarCeros(sol) == minCeros) {
                solucionesMaximas.add(sol);
                c++;
            }
        }

        // mostrar soluciones minimas
        for (int[][] sol : solucionesMaximas) {
            mostrar(sol);
        }
    }

    /*
     * 
     * e) Algoritmo para mostrar todos los caminos posibles de mínima longitud desde
     * una posición inicial a una posición final. Además, mostrar la cantidad de
     * soluciones posibles.
     */
    public static void laberintoTorreEAux(int[][] m, LinkedList<int[][]> L, int i, int j, int iFin, int jFin,
            int paso) {
        if (!posValida(m, i, j)) {
            return;
        }
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            L.add(Arrays.stream(m).map(fila -> fila.clone()).toArray($ -> m.clone()));
        }
        LinkedList<Regla> L1 = reglasAplicablesTorre(m, i, j);
        while (!L1.isEmpty()) {
            Regla R = elegirRegla(L1);
            laberintoTorreEAux(m, L, R.fil, R.col, iFin, jFin, paso + 1);
            m[R.fil][R.col] = 0;
        }
    }

    public static void laberintoTorreE(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        LinkedList<int[][]> soluciones = new LinkedList<>();
        laberintoTorreEAux(m, soluciones, i, j, iFin, jFin, paso);
        LinkedList<int[][]> solucionesMinimas = new LinkedList<>();

        // filtrar las soluciones que sean minimas
        if (soluciones.size() == 0)
            return;
        int maxCeros = contarCeros(soluciones.getFirst());
        for (int[][] sol : soluciones) {
            int ceros = contarCeros(sol);
            if (ceros > maxCeros) {
                maxCeros = ceros;
            }
        }

        // filtramos las soluciones que tengan esa máxima cantidad de ceros
        for (int[][] sol : soluciones) {
            if (contarCeros(sol) == maxCeros) {
                solucionesMinimas.add(sol);
                c++;
            }
        }

        // mostrar soluciones minimas
        for (int[][] sol : solucionesMinimas) {
            mostrar(sol);
        }
    }

    /*
     * f) Algoritmo para resolver cualquiera de los incisos anteriores utilizando
     * una Lista de Matrices.
     */
    public static void laberintoTorre(int[][] m, LinkedList<int[][]> L, int i, int j, int iFin, int jFin, int paso) {
        if (!posValida(m, i, j)) {
            return;
        }
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            L.add(Arrays.stream(m).map(fila -> fila.clone()).toArray($ -> m.clone()));
            c++;
        }
        LinkedList<Regla> L1 = reglasAplicablesTorre(m, i, j);
        while (!L1.isEmpty()) {
            Regla R = elegirRegla(L1);
            laberintoTorre(m, L, R.fil, R.col, iFin, jFin, paso + 1);
            m[R.fil][R.col] = 0;
        }
    }

    /*
  Alfil
  // @formatter:off
          _____                    _____            _____          
         /\    \                  /\    \          /\    \         
        /::\    \                /::\____\        /::\    \        
       /::::\    \              /:::/    /       /::::\    \       
      /::::::\    \            /:::/    /       /::::::\    \      
     /:::/\:::\    \          /:::/    /       /:::/\:::\    \     
    /:::/__\:::\    \        /:::/    /       /:::/__\:::\    \    
   /::::\   \:::\    \      /:::/    /       /::::\   \:::\    \   
  /::::::\   \:::\    \    /:::/    /       /::::::\   \:::\    \  
 /:::/\:::\   \:::\    \  /:::/    /       /:::/\:::\   \:::\    \ 
/:::/  \:::\   \:::\____\/:::/____/       /:::/  \:::\   \:::\____\
\::/    \:::\  /:::/    /\:::\    \       \::/    \:::\   \::/    /
 \/____/ \:::\/:::/    /  \:::\    \       \/____/ \:::\   \/____/ 
          \::::::/    /    \:::\    \               \:::\    \     
           \::::/    /      \:::\    \               \:::\____\    
           /:::/    /        \:::\    \               \::/    /    
          /:::/    /          \:::\    \               \/____/     
         /:::/    /            \:::\    \                          
        /:::/    /              \:::\____\                         
        \::/    /                \::/    /                         
         \/____/                  \/____/                          
                                                                   
          _____                    _____                           
         /\    \                  /\    \                          
        /::\    \                /::\____\                         
        \:::\    \              /:::/    /                         
         \:::\    \            /:::/    /                          
          \:::\    \          /:::/    /                           
           \:::\    \        /:::/    /                            
           /::::\    \      /:::/    /                             
  ____    /::::::\    \    /:::/    /                              
 /\   \  /:::/\:::\    \  /:::/    /                               
/::\   \/:::/  \:::\____\/:::/____/                                
\:::\  /:::/    \::/    /\:::\    \                                
 \:::\/:::/    / \/____/  \:::\    \                               
  \::::::/    /            \:::\    \                              
   \::::/____/              \:::\    \                             
    \:::\    \               \:::\    \                            
     \:::\    \               \:::\    \                           
      \:::\    \               \:::\    \                          
       \:::\____\               \:::\____\                         
        \::/    /                \::/    /                         
         \/____/                  \/____/                                                                          
  // @formatter:on
  */
    /*
     * a) Algoritmo para mostrar todos los caminos posibles desde una posición
     * inicial a una posición final. Además, mostrar la cantidad de soluciones
     * posibles.
     */
    private static LinkedList<Regla> reglasAplicablesAlfil(int[][] m, int i, int j) {
        LinkedList<Regla> L = new LinkedList<>();

        int[][] direcciones = {
                { -1, -1 }, // ↖
                { -1, 1 }, // ↙
                { 1, -1 }, // ↗
                { 1, 1 } // ↘
        };

        for (int[] d : direcciones) {
            int ni = i + d[0];
            int nj = j + d[1];
            while (posValida(m, ni, nj)) {
                L.add(new Regla(ni, nj));
                ni += d[0];
                nj += d[1];
            }
        }

        return L;
    }

    public static void laberintoAlfilA(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        if (!posValida(m, i, j)) {
            return;
        }
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            mostrar(m);
            c++;
        }
        LinkedList<Regla> L1 = reglasAplicablesAlfil(m, i, j);
        while (!L1.isEmpty()) {
            Regla R = elegirRegla(L1);
            laberintoAlfilA(m, R.fil, R.col, iFin, jFin, paso + 1);
            m[R.fil][R.col] = 0;
        }
    }

    /*
     * 
     * b) Algoritmo para mostrar todos los caminos posibles desde una posición
     * inicial a una posición final tal que se visiten todas las casillas de la
     * matriz. Además, mostrar la cantidad de soluciones posibles.
     */
    public static void laberintoAlfilB(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        if (!posValida(m, i, j)) {
            return;
        }
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            if (!tieneCeros(m)) {
                mostrar(m);
                c++;
            }
        }
        LinkedList<Regla> L1 = reglasAplicablesAlfil(m, i, j);
        while (!L1.isEmpty()) {
            Regla R = elegirRegla(L1);
            laberintoAlfilB(m, R.fil, R.col, iFin, jFin, paso + 1);
            m[R.fil][R.col] = 0;
        }
    }

    /*
     * 
     * c) Algoritmo para mostrar todos los caminos posibles desde una posición
     * inicial a una posición final tal que NO se visiten todas las casillas de la
     * matriz. Además, mostrar la cantidad de soluciones posibles.
     */
    public static void laberintoAlfilC(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        if (!posValida(m, i, j)) {
            return;
        }
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            if (tieneCeros(m)) {
                mostrar(m);
                c++;
            }
        }
        LinkedList<Regla> L1 = reglasAplicablesAlfil(m, i, j);
        while (!L1.isEmpty()) {
            Regla R = elegirRegla(L1);
            laberintoAlfilC(m, R.fil, R.col, iFin, jFin, paso + 1);
            m[R.fil][R.col] = 0;
        }
    }

    /*
     * 
     * d) Algoritmo para mostrar todos los caminos posibles de máxima longitud desde
     * una posición inicial a una posición final. Además, mostrar la cantidad de
     * soluciones posibles.
     */
    public static void laberintoAlfilDAux(int[][] m, LinkedList<int[][]> L, int i, int j, int iFin, int jFin,
            int paso) {
        if (!posValida(m, i, j)) {
            return;
        }
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            L.add(Arrays.stream(m).map(fila -> fila.clone()).toArray($ -> m.clone()));
        }
        LinkedList<Regla> L1 = reglasAplicablesAlfil(m, i, j);
        while (!L1.isEmpty()) {
            Regla R = elegirRegla(L1);
            laberintoAlfilDAux(m, L, R.fil, R.col, iFin, jFin, paso + 1);
            m[R.fil][R.col] = 0;
        }
    }

    public static void laberintoAlfilD(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        LinkedList<int[][]> soluciones = new LinkedList<>();
        laberintoAlfilDAux(m, soluciones, i, j, iFin, jFin, paso);
        LinkedList<int[][]> solucionesMaximas = new LinkedList<>();

        // filtrar las soluciones que sean maximas
        if (soluciones.size() == 0)
            return;
        int minCeros = contarCeros(soluciones.getFirst());
        for (int[][] sol : soluciones) {
            int ceros = contarCeros(sol);
            if (ceros < minCeros) {
                minCeros = ceros;
            }
        }

        // filtramos las soluciones que tengan esa máxima cantidad de ceros
        for (int[][] sol : soluciones) {
            if (contarCeros(sol) == minCeros) {
                solucionesMaximas.add(sol);
                c++;
            }
        }

        // mostrar soluciones minimas
        for (int[][] sol : solucionesMaximas) {
            mostrar(sol);
        }
    }

    /*
     * 
     * e) Algoritmo para mostrar todos los caminos posibles de mínima longitud desde
     * una posición inicial a una posición final. Además, mostrar la cantidad de
     * soluciones posibles.
     */
    public static void laberintoAlfilEAux(int[][] m, LinkedList<int[][]> L, int i, int j, int iFin, int jFin,
            int paso) {
        if (!posValida(m, i, j)) {
            return;
        }
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            L.add(Arrays.stream(m).map(fila -> fila.clone()).toArray($ -> m.clone()));
        }
        LinkedList<Regla> L1 = reglasAplicablesAlfil(m, i, j);
        while (!L1.isEmpty()) {
            Regla R = elegirRegla(L1);
            laberintoAlfilEAux(m, L, R.fil, R.col, iFin, jFin, paso + 1);
            m[R.fil][R.col] = 0;
        }
    }

    public static void laberintoAlfilE(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        LinkedList<int[][]> soluciones = new LinkedList<>();
        laberintoAlfilEAux(m, soluciones, i, j, iFin, jFin, paso);
        LinkedList<int[][]> solucionesMinimas = new LinkedList<>();

        // filtrar las soluciones que sean minimas
        if (soluciones.size() == 0)
            return;
        int maxCeros = contarCeros(soluciones.getFirst());
        for (int[][] sol : soluciones) {
            int ceros = contarCeros(sol);
            if (ceros > maxCeros) {
                maxCeros = ceros;
            }
        }

        // filtramos las soluciones que tengan esa máxima cantidad de ceros
        for (int[][] sol : soluciones) {
            if (contarCeros(sol) == maxCeros) {
                solucionesMinimas.add(sol);
                c++;
            }
        }

        // mostrar soluciones minimas
        for (int[][] sol : solucionesMinimas) {
            mostrar(sol);
        }
    }

    /*
     * f) Algoritmo para resolver cualquiera de los incisos anteriores utilizando
     * una Lista de Matrices.
     */
    public static void laberintoAlfil(int[][] m, LinkedList<int[][]> L, int i, int j, int iFin, int jFin, int paso) {
        if (!posValida(m, i, j)) {
            return;
        }
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            L.add(Arrays.stream(m).map(fila -> fila.clone()).toArray($ -> m.clone()));
            c++;
        }
        LinkedList<Regla> L1 = reglasAplicablesAlfil(m, i, j);
        while (!L1.isEmpty()) {
            Regla R = elegirRegla(L1);
            laberintoAlfil(m, L, R.fil, R.col, iFin, jFin, paso + 1);
            m[R.fil][R.col] = 0;
        }
    }

    /*
  Dama
  // @formatter:off
          _____                    _____                    _____          
         /\    \                  /\    \                  /\    \         
        /::\    \                /::\    \                /::\____\        
       /::::\    \              /::::\    \              /::::|   |        
      /::::::\    \            /::::::\    \            /:::::|   |        
     /:::/\:::\    \          /:::/\:::\    \          /::::::|   |        
    /:::/  \:::\    \        /:::/__\:::\    \        /:::/|::|   |        
   /:::/    \:::\    \      /::::\   \:::\    \      /:::/ |::|   |        
  /:::/    / \:::\    \    /::::::\   \:::\    \    /:::/  |::|___|______  
 /:::/    /   \:::\ ___\  /:::/\:::\   \:::\    \  /:::/   |::::::::\    \ 
/:::/____/     \:::|    |/:::/  \:::\   \:::\____\/:::/    |:::::::::\____\
\:::\    \     /:::|____|\::/    \:::\  /:::/    /\::/    / ~~~~~/:::/    /
 \:::\    \   /:::/    /  \/____/ \:::\/:::/    /  \/____/      /:::/    / 
  \:::\    \ /:::/    /            \::::::/    /               /:::/    /  
   \:::\    /:::/    /              \::::/    /               /:::/    /   
    \:::\  /:::/    /               /:::/    /               /:::/    /    
     \:::\/:::/    /               /:::/    /               /:::/    /     
      \::::::/    /               /:::/    /               /:::/    /      
       \::::/    /               /:::/    /               /:::/    /       
        \::/____/                \::/    /                \::/    /        
         ~~                       \/____/                  \/____/         
                                                                           
          _____                                                            
         /\    \                                                           
        /::\    \                                                          
       /::::\    \                                                         
      /::::::\    \                                                        
     /:::/\:::\    \                                                       
    /:::/__\:::\    \                                                      
   /::::\   \:::\    \                                                     
  /::::::\   \:::\    \                                                    
 /:::/\:::\   \:::\    \                                                   
/:::/  \:::\   \:::\____\                                                  
\::/    \:::\  /:::/    /                                                  
 \/____/ \:::\/:::/    /                                                   
          \::::::/    /                                                    
           \::::/    /                                                     
           /:::/    /                                                      
          /:::/    /                                                       
         /:::/    /                                                        
        /:::/    /                                                         
        \::/    /                                                          
         \/____/                                                                                                                                                       
  // @formatter:on
  */
    /*
     * a) Algoritmo para mostrar todos los caminos posibles desde una posición
     * inicial a una posición final. Además, mostrar la cantidad de soluciones
     * posibles.
     */
    private static LinkedList<Regla> reglasAplicablesDama(int[][] m, int i, int j) {
        LinkedList<Regla> L = new LinkedList<>();

        L.addAll(reglasAplicablesTorre(m, i, j));
        L.addAll(reglasAplicablesAlfil(m, i, j));

        return L;
    }

    public static void laberintoDamaA(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        if (!posValida(m, i, j)) {
            return;
        }
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            mostrar(m);
            c++;
        }
        LinkedList<Regla> L1 = reglasAplicablesDama(m, i, j);
        while (!L1.isEmpty()) {
            Regla R = elegirRegla(L1);
            laberintoDamaA(m, R.fil, R.col, iFin, jFin, paso + 1);
            m[R.fil][R.col] = 0;
        }
    }

    /*
     * 
     * b) Algoritmo para mostrar todos los caminos posibles desde una posición
     * inicial a una posición final tal que se visiten todas las casillas de la
     * matriz. Además, mostrar la cantidad de soluciones posibles.
     */
    public static void laberintoDamaB(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        if (!posValida(m, i, j)) {
            return;
        }
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            if (!tieneCeros(m)) {
                mostrar(m);
                c++;
            }
        }
        LinkedList<Regla> L1 = reglasAplicablesDama(m, i, j);
        while (!L1.isEmpty()) {
            Regla R = elegirRegla(L1);
            laberintoDamaB(m, R.fil, R.col, iFin, jFin, paso + 1);
            m[R.fil][R.col] = 0;
        }
    }

    /*
     * 
     * c) Algoritmo para mostrar todos los caminos posibles desde una posición
     * inicial a una posición final tal que NO se visiten todas las casillas de la
     * matriz. Además, mostrar la cantidad de soluciones posibles.
     */
    public static void laberintoDamaC(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        if (!posValida(m, i, j)) {
            return;
        }
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            if (tieneCeros(m)) {
                mostrar(m);
                c++;
            }
        }
        LinkedList<Regla> L1 = reglasAplicablesDama(m, i, j);
        while (!L1.isEmpty()) {
            Regla R = elegirRegla(L1);
            laberintoDamaC(m, R.fil, R.col, iFin, jFin, paso + 1);
            m[R.fil][R.col] = 0;
        }
    }

    /*
     * 
     * d) Algoritmo para mostrar todos los caminos posibles de máxima longitud desde
     * una posición inicial a una posición final. Además, mostrar la cantidad de
     * soluciones posibles.
     */
    public static void laberintoDamaDAux(int[][] m, LinkedList<int[][]> L, int i, int j, int iFin, int jFin,
            int paso) {
        if (!posValida(m, i, j)) {
            return;
        }
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            L.add(Arrays.stream(m).map(fila -> fila.clone()).toArray($ -> m.clone()));
        }
        LinkedList<Regla> L1 = reglasAplicablesDama(m, i, j);
        while (!L1.isEmpty()) {
            Regla R = elegirRegla(L1);
            laberintoDamaDAux(m, L, R.fil, R.col, iFin, jFin, paso + 1);
            m[R.fil][R.col] = 0;
        }
    }

    public static void laberintoDamaD(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        LinkedList<int[][]> soluciones = new LinkedList<>();
        laberintoDamaDAux(m, soluciones, i, j, iFin, jFin, paso);
        LinkedList<int[][]> solucionesMaximas = new LinkedList<>();

        // filtrar las soluciones que sean maximas
        if (soluciones.size() == 0)
            return;
        int minCeros = contarCeros(soluciones.getFirst());
        for (int[][] sol : soluciones) {
            int ceros = contarCeros(sol);
            if (ceros < minCeros) {
                minCeros = ceros;
            }
        }

        // filtramos las soluciones que tengan esa máxima cantidad de ceros
        for (int[][] sol : soluciones) {
            if (contarCeros(sol) == minCeros) {
                solucionesMaximas.add(sol);
                c++;
            }
        }

        // mostrar soluciones minimas
        for (int[][] sol : solucionesMaximas) {
            mostrar(sol);
        }
    }

    /*
     * 
     * e) Algoritmo para mostrar todos los caminos posibles de mínima longitud desde
     * una posición inicial a una posición final. Además, mostrar la cantidad de
     * soluciones posibles.
     */
    public static void laberintoDamaEAux(int[][] m, LinkedList<int[][]> L, int i, int j, int iFin, int jFin,
            int paso) {
        if (!posValida(m, i, j)) {
            return;
        }
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            L.add(Arrays.stream(m).map(fila -> fila.clone()).toArray($ -> m.clone()));
        }
        LinkedList<Regla> L1 = reglasAplicablesDama(m, i, j);
        while (!L1.isEmpty()) {
            Regla R = elegirRegla(L1);
            laberintoDamaEAux(m, L, R.fil, R.col, iFin, jFin, paso + 1);
            m[R.fil][R.col] = 0;
        }
    }

    public static void laberintoDamaE(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        LinkedList<int[][]> soluciones = new LinkedList<>();
        laberintoDamaEAux(m, soluciones, i, j, iFin, jFin, paso);
        LinkedList<int[][]> solucionesMinimas = new LinkedList<>();

        // filtrar las soluciones que sean minimas
        if (soluciones.size() == 0)
            return;
        int maxCeros = contarCeros(soluciones.getFirst());
        for (int[][] sol : soluciones) {
            int ceros = contarCeros(sol);
            if (ceros > maxCeros) {
                maxCeros = ceros;
            }
        }

        // filtramos las soluciones que tengan esa máxima cantidad de ceros
        for (int[][] sol : soluciones) {
            if (contarCeros(sol) == maxCeros) {
                solucionesMinimas.add(sol);
                c++;
            }
        }

        // mostrar soluciones minimas
        for (int[][] sol : solucionesMinimas) {
            mostrar(sol);
        }
    }

    /*
     * f) Algoritmo para resolver cualquiera de los incisos anteriores utilizando
     * una Lista de Matrices.
     */
    public static void laberintoDama(int[][] m, LinkedList<int[][]> L, int i, int j, int iFin, int jFin, int paso) {
        if (!posValida(m, i, j)) {
            return;
        }
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            L.add(Arrays.stream(m).map(fila -> fila.clone()).toArray($ -> m.clone()));
            c++;
        }
        LinkedList<Regla> L1 = reglasAplicablesDama(m, i, j);
        while (!L1.isEmpty()) {
            Regla R = elegirRegla(L1);
            laberintoDama(m, L, R.fil, R.col, iFin, jFin, paso + 1);
            m[R.fil][R.col] = 0;
        }
    }
}
