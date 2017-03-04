package ModelT;

public class Sudoku {
    private static Sudoku instance;
    public Cell[][] cells;//de tipo Cell
    public int size;//para crear la matriz[size][size]
     
    private Sudoku(int size) {
        cells = new Cell[size][size];
        this.size = size;
        
        // inicializa celdas
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                cells[i][j] = new Cell(i, j);
         
        // adiciona observadores
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                cells[i][j].addObserver(cells);//añade un observador tipo cells a cada celda
    }
    
    public static Sudoku getInstance(){
        if (instance == null) instance = new Sudoku(9);
        return instance;
    }
    
    public static Sudoku getNewInstance(){
        instance = new Sudoku(9);
        return instance;
    }
 
    // fija valores conocidos
    public void setup(int[][] puzzle) {//recibe el sudoku
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
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
                if (puzzle.charAt(i * size + j) != '.') {//matriz aplanada
                    cells[i][j].setValue(puzzle.charAt(i * size + j) - '0');//lo pone en la celda
                    cells[i][j].setCan_modified(false);
                }
            }
        }
    }
    
    public Cell[][] getCells(){
        return cells;
    }
    
    public int getCellValue(int i, int j) {
        return cells[i][j].getValue();
    }
    
    public void setCellValue(int i, int j, int value){ 
        cells[i][j].setValue(value);
    }
    
    public boolean IsSolved(int i, int j) {
        return cells[i][j].isSolved();
    }
     
    public String toString(){//cómo debe imprimir
        String str = "+---+---+---+---+---+---+---+---+---+\n";
 
       for (int i = 0; i < size; i++) {
            str += "|";
            for (int j = 0; j < size; j++) {
                str += String.format(" %d |", cells[i][j].getValue());
            }
            str += "\n+---+---+---+---+---+---+---+---+---+\n";
       }
       return str;
    }
}