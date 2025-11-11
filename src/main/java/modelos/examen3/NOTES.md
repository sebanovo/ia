class Regla(fil, col)
void mostrar(m)
boolean posValida(m, i, j)
L<Regla> reglasAplicablesRey(m, i, j) 
public static int vueltas

* LABERINTO

- [X] Sin heuristica
- Regla elegirRegla(L)
- boolean laberintoRey1(m, i, j, iFin, jFin, paso)
- [X] Con heuristica
- Regla elegirRegla1(L)
- boolean laberintoRey2(m, i, j, iFin, jFin, paso)

* NREINAS

- boolean estanRectasVacias(m, i, j) 
- boolean estanDiagonalesVacias(m, i, j) 
- boolean estanDireccionesVacias(m, i, j, int[][] direcciones) 
- hayDamaEnRango(m, i, j)
- reglasAplicablesDama(m, fila)

- [X] Sin heuristica
- boolean nReinas1(m, paso)
- [X] Con heuristica
- boolean nReinas2(m, paso)

* SALTOCABALLO

- L<Regla> reglasAplicablesCaballo(m, i, j)
- [X] Sin heuristica
- boolean saltoCaballo1(m, i, j, paso)
- [X] Con heuristica
- boolean saltoCaballo2(m, i, j, paso)

* SUDOKU
- boolean estaEnFila(m, i, valor)
- boolean estaEnColumna(m, j, valor)
- boolean estaEnRegion(m, i, j, valor)
- L<Regla> reglasAplicablesSudoku(m, i, j)

- [X] Sin heuristica
- elegirRegla2(L)
- boolean sudoku1(m, i, j)

- [X] Con heuristica
- elegirRegla3(L)
- boolean sudoku2(m, i, j)