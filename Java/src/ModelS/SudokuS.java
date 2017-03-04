package ModelS;

public class SudokuS {
    private static SudokuS instance;
    public Cell[][] cells;//de tipo Cell
    public int size; //public int size;//para crear la matriz[size][size]
    private int[][] forma ;
     
    private SudokuS(int size, int[][]forma) {
        this.size = size;
        cells = new Cell[size][size];
        this.forma = forma;    
        
        // inicializa celdas
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                cells[i][j] = new Cell(i, j);
         
        // adiciona observadores
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                cells[i][j].addObserver(cells,forma);//añade un observador tipo cells a cada celda
    }
    
    public static SudokuS getInstance(int[][] forma){
        if(instance == null) instance = new SudokuS(9, forma);
        return instance;
    }
    
    public static SudokuS getNewInstance(int[][] forma){
        instance = new SudokuS(9, forma);
        return instance;
    }
    
    // fija valores conocidos
    public void setup(int[][] puzzle) {//recibe el sudoku
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                if (puzzle[i][j] != 0) {//si ya hay un número para esa celda
                    cells[i][j].setValue(puzzle[i][j]);//lo pone en la celda, quita los otros posibles valores
                    cells[i][j].setCan_modified(false);
                }
            }
        }
    }
    
    public void setup(String puzzle) {//lee el sudoku de la forma como se lo mandamos en el archivo
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (puzzle.charAt(i * getSize() + j) != '.') {//matriz aplanada
                    cells[i][j].setValue(puzzle.charAt(i * size + j) - '0');//lo pone en la celda
                    cells[i][j].setCan_modified(false);
                }
            }
        }
    }
    
    public Cell[][] getAllCells(){
        return cells;
    }
    
    public int getCellValue(int i, int j) {
        return cells[i][j].getValue();
    }
    
    public boolean IsSolved(int i, int j) {
        return cells[i][j].isSolved();
    }
    
    @Override
    public String toString(){//cómo debe imprimir
        String str = "+---+---+---+---+---+---+---+---+---+\n";
 
       for (int i = 0; i < getSize(); i++) {
            str += "|";
            for (int j = 0; j < getSize(); j++) {
                str += String.format(" %d |", cells[i][j].getValue());
            }
            str += "\n+---+---+---+---+---+---+---+---+---+\n";
       }
       return str;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @return the forma
     */
    public int[][] getForma() {
        return forma;
    }
}
