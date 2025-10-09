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
    System.out.println(s);
  }

  public static boolean posValida(int[][] m, int i, int j) {
    return i >= 0 && i < m.length && j >= 0 && j < m[i].length && m[i][j] == 0;
  }

  public static Regla elegirRegla(LinkedList<Regla> L1) {
    return L1.removeFirst();
  }

  public static double distancia(int x1, int y1, int x2, int y2) {
    return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
  }

  public static Regla elegirMejorRegla(LinkedList<Regla> L1, int iFin, int jFin) {
    double distMenor = Double.MAX_VALUE;
    int posMenor = 0;
    for (int i = 0; i < L1.size(); i++) {
      double dist = distancia(L1.get(i).fil, L1.get(i).col, iFin, jFin);
      if (dist < distMenor) {
        distMenor = dist;
        posMenor = i;
      }
    }
    return L1.remove(posMenor);
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

  public static LinkedList<Regla> reglasAplicablesRey(int[][] m, int i, int j) {
    LinkedList<Regla> L1 = new LinkedList<>();
    if (posValida(m, i, j - 1)) {
      L1.add(new Regla(i, j - 1));
    }
    if (posValida(m, i - 1, j)) {
      L1.add(new Regla(i - 1, j));
    }
    if (posValida(m, i, j + 1)) {
      L1.add(new Regla(i, j + 1));
    }
    if (posValida(m, i + 1, j)) {
      L1.add(new Regla(i + 1, j));
    }
    return L1;
  }

  public static boolean laberintoReySinHeuristica(int[][] m, int i, int j, int iFin, int jFin, int paso) {
    m[i][j] = paso;
    if (i == iFin && j == jFin) {
      return true;
    }

    LinkedList<Regla> L1 = reglasAplicablesRey(m, i, j);
    while (!L1.isEmpty()) {
      Regla R = elegirRegla(L1);
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

    LinkedList<Regla> L1 = reglasAplicablesRey(m, i, j);
    while (!L1.isEmpty()) {
      Regla R = elegirMejorRegla(L1, iFin, jFin);
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

  public static LinkedList<Regla> reglasAplicablesCaballo(int[][] m, int i, int j) {
    LinkedList<Regla> L1 = new LinkedList<>();
    if (posValida(m, i - 2, j - 1)) {
      L1.add(new Regla(i - 2, j - 1));
    }
    if (posValida(m, i - 2, j + 1)) {
      L1.add(new Regla(i - 2, j + 1));
    }
    if (posValida(m, i - 1, j + 2)) {
      L1.add(new Regla(i - 1, j + 2));
    }
    if (posValida(m, i + 1, j + 2)) {
      L1.add(new Regla(i + 1, j + 2));
    }
    if (posValida(m, i + 2, j + 1)) {
      L1.add(new Regla(i + 2, j + 1));
    }
    if (posValida(m, i + 2, j - 1)) {
      L1.add(new Regla(i + 2, j - 1));
    }
    if (posValida(m, i + 1, j - 2)) {
      L1.add(new Regla(i + 1, j - 2));
    }
    if (posValida(m, i - 1, j - 2)) {
      L1.add(new Regla(i - 1, j - 2));
    }
    return L1;
  }

  public static boolean laberintoCaballoSinHeuristica(int[][] m, int i, int j, int iFin, int jFin, int paso) {
    m[i][j] = paso;
    if (i == iFin && j == jFin) {
      return true;
    }

    LinkedList<Regla> L1 = reglasAplicablesCaballo(m, i, j);
    while (!L1.isEmpty()) {
      Regla R = elegirRegla(L1);
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

    LinkedList<Regla> L1 = reglasAplicablesCaballo(m, i, j);
    while (!L1.isEmpty()) {
      Regla R = elegirMejorRegla(L1, iFin, jFin);
      if (laberintoCaballoConHeuristica(m, R.fil, R.col, iFin, jFin, paso + 1)) {
        return true;
      }
      m[R.fil][R.col] = 0;
    }
    return false;
  }
  /*
  Torres 
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
                                                                           
          _____                    _____                    _____          
         /\    \                  /\    \                  /\    \         
        /::\    \                /::\    \                /::\    \        
       /::::\    \              /::::\    \              /::::\    \       
      /::::::\    \            /::::::\    \            /::::::\    \      
     /:::/\:::\    \          /:::/\:::\    \          /:::/\:::\    \     
    /:::/__\:::\    \        /:::/__\:::\    \        /:::/__\:::\    \    
   /::::\   \:::\    \      /::::\   \:::\    \       \:::\   \:::\    \   
  /::::::\   \:::\    \    /::::::\   \:::\    \    ___\:::\   \:::\    \  
 /:::/\:::\   \:::\____\  /:::/\:::\   \:::\    \  /\   \:::\   \:::\    \ 
/:::/  \:::\   \:::|    |/:::/__\:::\   \:::\____\/::\   \:::\   \:::\____\
\::/   |::::\  /:::|____|\:::\   \:::\   \::/    /\:::\   \:::\   \::/    /
 \/____|:::::\/:::/    /  \:::\   \:::\   \/____/  \:::\   \:::\   \/____/ 
       |:::::::::/    /    \:::\   \:::\    \       \:::\   \:::\    \     
       |::|\::::/    /      \:::\   \:::\____\       \:::\   \:::\____\    
       |::| \::/____/        \:::\   \::/    /        \:::\  /:::/    /    
       |::|  ~|               \:::\   \/____/          \:::\/:::/    /     
       |::|   |                \:::\    \               \::::::/    /      
       \::|   |                 \:::\____\               \::::/    /       
        \:|   |                  \::/    /                \::/    /        
         \|___|                   \/____/                  \/____/                                                       
  // @formatter:on
  */

  public static LinkedList<Regla> reglasAplicablesTorres(int[][] m, int i, int j) {
    LinkedList<Regla> L1 = new LinkedList<>();
    int j1 = j - 1;
    while (posValida(m, i, j1)) { // ⬆
      L1.add(new Regla(i, j1));
      j1 = j1 - 1;
    }
    int i1 = i - 1;
    while (posValida(m, i1, j)) { // ⬅
      L1.add(new Regla(i1, j));
      i1 = i1 - 1;
    }

    j1 = j + 1;
    while (posValida(m, i, j1)) { // ⬇
      L1.add(new Regla(i, j1));
      j1 = j1 + 1;
    }
    i1 = i + 1;
    while (posValida(m, i1, j)) { // ➡
      L1.add(new Regla(i1, j));
      i1 = i1 + 1;
    }
    return L1;
  }

  public static boolean laberintoTorresSinHeuristica(int[][] m, int i, int j, int iFin, int jFin, int paso) {
    m[i][j] = paso;
    if (i == iFin && j == jFin) {
      return true;
    }

    LinkedList<Regla> L1 = reglasAplicablesTorres(m, i, j);
    while (!L1.isEmpty()) {
      Regla R = elegirRegla(L1);
      if (laberintoTorresSinHeuristica(m, R.fil, R.col, iFin, jFin, paso + 1)) {
        return true;
      }
      m[R.fil][R.col] = 0;
    }
    return false;
  }

  public static boolean laberintoTorresConHeuristica(int[][] m, int i, int j, int iFin, int jFin, int paso) {
    m[i][j] = paso;
    if (i == iFin && j == jFin) {
      return true;
    }

    LinkedList<Regla> L1 = reglasAplicablesTorres(m, i, j);
    while (!L1.isEmpty()) {
      Regla R = elegirMejorRegla(L1, iFin, jFin);
      if (laberintoTorresConHeuristica(m, R.fil, R.col, iFin, jFin, paso + 1)) {
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

  public static LinkedList<Regla> reglasAplicablesAlfil(int[][] m, int i, int j) {
    LinkedList<Regla> L1 = new LinkedList<>();
    int i1 = i - 1, j1 = j - 1;
    while (posValida(m, i1, j1)) { // ↖
      L1.add(new Regla(i1, j1));
      i1--;
      j1--;
    }

    i1 = i - 1;
    j1 = j + 1;
    while (posValida(m, i1, j1)) { // ↙
      L1.add(new Regla(i1, j1));
      i1--;
      j1++;
    }

    i1 = i + 1;
    j1 = j - 1;
    while (posValida(m, i1, j1)) { // ↗
      L1.add(new Regla(i1, j1));
      i1++;
      j1--;
    }

    i1 = i + 1;
    j1 = j + 1;
    while (posValida(m, i1, j1)) { // ↘
      L1.add(new Regla(i1, j1));
      i1++;
      j1++;
    }

    return L1;
  }

  public static boolean laberintoAlfilSinHeuristica(int[][] m, int i, int j, int iFin, int jFin, int paso) {
    m[i][j] = paso;
    if (i == iFin && j == jFin) {
      return true;
    }

    LinkedList<Regla> L1 = reglasAplicablesAlfil(m, i, j);
    while (!L1.isEmpty()) {
      Regla R = elegirRegla(L1);
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

    LinkedList<Regla> L1 = reglasAplicablesAlfil(m, i, j);
    while (!L1.isEmpty()) {
      Regla R = elegirMejorRegla(L1, iFin, jFin);
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

  public static LinkedList<Regla> reglasAplicablesDama(int[][] m, int i, int j) {
    LinkedList<Regla> L = new LinkedList<>();

    L.addAll(reglasAplicablesTorres(m, i, j));
    L.addAll(reglasAplicablesAlfil(m, i, j));

    return L;
  }

  public static boolean laberintoDamaSinHeuristica(int[][] m, int i, int j, int iFin, int jFin, int paso) {
    m[i][j] = paso;
    if (i == iFin && j == jFin) {
      return true;
    }

    LinkedList<Regla> L1 = reglasAplicablesDama(m, i, j);
    while (!L1.isEmpty()) {
      Regla R = elegirRegla(L1);
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

    LinkedList<Regla> L1 = reglasAplicablesDama(m, i, j);
    while (!L1.isEmpty()) {
      Regla R = elegirMejorRegla(L1, iFin, jFin);
      if (laberintoDamaConHeuristica(m, R.fil, R.col, iFin, jFin, paso + 1)) {
        return true;
      }
      m[R.fil][R.col] = 0;
    }
    return false;
  }
}
