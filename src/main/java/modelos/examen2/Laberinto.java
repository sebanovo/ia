package modelos.examen2;

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

    public static boolean posValida(int[][] m, int i, int j) {
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
        System.out.println(s);
    }

    public static Regla elegirRegla(LinkedList<Regla> L) {
        return L.removeFirst();
    }

    public static int c = 0;

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

    public static LinkedList<Regla> reglasAplicablesRey(int[][] m, int i, int j) {
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

    public static void laberintoRey(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        if (!posValida(m, i, j)) {
            return;
        }
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            mostrar(m);
            c++;
        }
        LinkedList<Regla> L = reglasAplicablesRey(m, i, j);
        while (!L.isEmpty()) {
            Regla R = elegirRegla(L);
            laberintoRey(m, R.fil, R.col, iFin, jFin, paso + 1);
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
    public static LinkedList<Regla> reglasAplicablesCaballo(int[][] m, int i, int j) {
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

    public static void laberintoCaballo(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        if (!posValida(m, i, j)) {
            return;
        }
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            mostrar(m);
            c++;
        }
        LinkedList<Regla> L = reglasAplicablesCaballo(m, i, j);
        while (!L.isEmpty()) {
            Regla R = elegirRegla(L);
            laberintoCaballo(m, R.fil, R.col, iFin, jFin, paso + 1);
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
    public static LinkedList<Regla> reglasAplicablesTorre(int[][] m, int i, int j) {
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

    public static void laberintoTorre(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        if (!posValida(m, i, j)) {
            return;
        }
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            mostrar(m);
            c++;
        }
        LinkedList<Regla> L = reglasAplicablesTorre(m, i, j);
        while (!L.isEmpty()) {
            Regla R = elegirRegla(L);
            laberintoTorre(m, R.fil, R.col, iFin, jFin, paso + 1);
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
    public static LinkedList<Regla> reglasAplicablesAlfil(int[][] m, int i, int j) {
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

    public static void laberintoAlfil(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        if (!posValida(m, i, j)) {
            return;
        }
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            mostrar(m);
            c++;
        }
        LinkedList<Regla> L = reglasAplicablesAlfil(m, i, j);
        while (!L.isEmpty()) {
            Regla R = elegirRegla(L);
            laberintoAlfil(m, R.fil, R.col, iFin, jFin, paso + 1);
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
    public static LinkedList<Regla> reglasAplicablesDama(int[][] m, int i, int j) {
        LinkedList<Regla> L = new LinkedList<>();

        L.addAll(reglasAplicablesTorre(m, i, j));
        L.addAll(reglasAplicablesAlfil(m, i, j));

        return L;
    }

    public static void laberintoDama(int[][] m, int i, int j, int iFin, int jFin, int paso) {
        if (!posValida(m, i, j)) {
            return;
        }
        m[i][j] = paso;
        if (i == iFin && j == jFin) {
            mostrar(m);
            c++;
        }
        LinkedList<Regla> L = reglasAplicablesDama(m, i, j);
        while (!L.isEmpty()) {
            Regla R = elegirRegla(L);
            laberintoDama(m, R.fil, R.col, iFin, jFin, paso + 1);
            m[R.fil][R.col] = 0;
        }
    }
}
