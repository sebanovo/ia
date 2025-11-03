package tema2.tarea4;

import java.util.*;

public class Laberinto {
    /*
     * Aplicar el Algoritmo de backTrack(), a los siguientes problemas. El Algoritmo
     * debe encontrar el primer camino solución, desde la posición inicial a la
     * posición final.
     * 
     * Aplicar el Algoritmo de exploración de caminos: sin heurística (Elige la
     * primera regla) y con heurística (Elige la mejor regla, por distancia).
     * Comentar los resultados para cada uno de ellos, respecto a la longitud de
     * camino y/o cantidad de pasos para llegar a la solución.
     * 
     * Ejecutar los siguientes problemas con y sin Atajos para valores de n y m
     * relativamente grandes.
     * 
     * 1. El problema de Movimientos del Rey.
     * 2. El problema del Salto de Caballo.
     * 3. El problema de Movimientos de la Torre.
     * 4. El problema de Movimientos del Alfil.
     * 5. El problema de Movimientos de la Reina.
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
        System.out.print(s);
    }

    private static boolean posValida(int[][] m, int i, int j) {
        return i >= 0 && i < m.length && j >= 0 && j < m[i].length && m[i][j] == 0;
    }

    private static Regla elegirRegla(LinkedList<Regla> L) {
        return L.removeFirst();
    }

    private static double distancia(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    private static Regla elegirMejorRegla(LinkedList<Regla> L, int iFin, int jFin) {
        double distMenor = Double.MAX_VALUE;
        int posMenor = 0;
        for (int i = 0; i < L.size(); i++) {
            double dist = distancia(L.get(i).fil, L.get(i).col, iFin, jFin);
            if (dist < distMenor) {
                distMenor = dist;
                posMenor = i;
            }
        }
        return L.remove(posMenor);
    }
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

    public static boolean laberintoReySinHeuristica(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            return true;
        }

        LinkedList<Regla> L = reglasAplicablesRey(m, i, j);
        while (!L.isEmpty()) {
            Regla R = elegirRegla(L);
            if (laberintoReySinHeuristica(m, R.fil, R.col, iFin, jFin, paso + 1)) {
                return true;
            }
            m[R.fil][R.col] = 0;
        }
        return false;
    }

    public static boolean laberintoReyConHeuristica(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            return true;
        }

        LinkedList<Regla> L = reglasAplicablesRey(m, i, j);
        while (!L.isEmpty()) {
            Regla R = elegirMejorRegla(L, iFin, jFin);
            if (laberintoReyConHeuristica(m, R.fil, R.col, iFin, jFin, paso + 1)) {
                return true;
            }
            m[R.fil][R.col] = 0;
        }
        return false;
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

    public static boolean laberintoCaballoSinHeuristica(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            return true;
        }

        LinkedList<Regla> L = reglasAplicablesCaballo(m, i, j);
        while (!L.isEmpty()) {
            Regla R = elegirRegla(L);
            if (laberintoCaballoSinHeuristica(m, R.fil, R.col, iFin, jFin, paso + 1)) {
                return true;
            }
            m[R.fil][R.col] = 0;
        }
        return false;
    }

    public static boolean laberintoCaballoConHeuristica(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            return true;
        }

        LinkedList<Regla> L = reglasAplicablesCaballo(m, i, j);
        while (!L.isEmpty()) {
            Regla R = elegirMejorRegla(L, iFin, jFin);
            if (laberintoCaballoConHeuristica(m, R.fil, R.col, iFin, jFin, paso + 1)) {
                return true;
            }
            m[R.fil][R.col] = 0;
        }
        return false;
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

    public static boolean laberintoTorreSinHeuristica(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            return true;
        }

        LinkedList<Regla> L = reglasAplicablesTorre(m, i, j);
        while (!L.isEmpty()) {
            Regla R = elegirRegla(L);
            if (laberintoTorreSinHeuristica(m, R.fil, R.col, iFin, jFin, paso + 1)) {
                return true;
            }
            m[R.fil][R.col] = 0;
        }
        return false;
    }

    public static boolean laberintoTorreConHeuristica(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            return true;
        }

        LinkedList<Regla> L = reglasAplicablesTorre(m, i, j);
        while (!L.isEmpty()) {
            Regla R = elegirMejorRegla(L, iFin, jFin);
            if (laberintoTorreConHeuristica(m, R.fil, R.col, iFin, jFin, paso + 1)) {
                return true;
            }
            m[R.fil][R.col] = 0;
        }
        return false;
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

    public static boolean laberintoAlfilSinHeuristica(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            return true;
        }

        LinkedList<Regla> L = reglasAplicablesAlfil(m, i, j);
        while (!L.isEmpty()) {
            Regla R = elegirRegla(L);
            if (laberintoAlfilSinHeuristica(m, R.fil, R.col, iFin, jFin, paso + 1)) {
                return true;
            }
            m[R.fil][R.col] = 0;
        }
        return false;
    }

    public static boolean laberintoAlfilConHeuristica(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            return true;
        }

        LinkedList<Regla> L = reglasAplicablesAlfil(m, i, j);
        while (!L.isEmpty()) {
            Regla R = elegirMejorRegla(L, iFin, jFin);
            if (laberintoAlfilConHeuristica(m, R.fil, R.col, iFin, jFin, paso + 1)) {
                return true;
            }
            m[R.fil][R.col] = 0;
        }
        return false;
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

    private static LinkedList<Regla> reglasAplicablesDama(int[][] m, int i, int j) {
        LinkedList<Regla> L = new LinkedList<>();

        L.addAll(reglasAplicablesTorre(m, i, j));
        L.addAll(reglasAplicablesAlfil(m, i, j));

        return L;
    }

    public static boolean laberintoDamaSinHeuristica(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            return true;
        }

        LinkedList<Regla> L = reglasAplicablesDama(m, i, j);
        while (!L.isEmpty()) {
            Regla R = elegirRegla(L);
            if (laberintoDamaSinHeuristica(m, R.fil, R.col, iFin, jFin, paso + 1)) {
                return true;
            }
            m[R.fil][R.col] = 0;
        }
        return false;
    }

    public static boolean laberintoDamaConHeuristica(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            return true;
        }

        LinkedList<Regla> L = reglasAplicablesDama(m, i, j);
        while (!L.isEmpty()) {
            Regla R = elegirMejorRegla(L, iFin, jFin);
            if (laberintoDamaConHeuristica(m, R.fil, R.col, iFin, jFin, paso + 1)) {
                return true;
            }
            m[R.fil][R.col] = 0;
        }
        return false;
    }
}
