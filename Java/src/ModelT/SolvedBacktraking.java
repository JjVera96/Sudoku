package ModelT;

public class SolvedBacktraking {
    Sudoku sudoku;
    public SolvedBacktraking (Sudoku sudoku){
        this.sudoku = sudoku;
    }
    
    private boolean isValid(int row, int col, int num) {
        int rowStart = (row/3)*3;
        int colStart = (col/3)*3;
        int i;
        for(i=0;i<9;i++){
            if(sudoku.cells[row][i].isSolved && (sudoku.cells[row][i].getValue()== num )) return false;
            if(sudoku.cells[i][col].isSolved && (sudoku.cells[i][col].getValue()== num )) return false;
            if(sudoku.cells[rowStart + (i%3)][colStart + (i/3)].isSolved && (sudoku.cells[rowStart + (i%3)][colStart + (i/3)].getValue() == num )) return false;
        }
        return true;
    }
    
    public boolean Backtracking(int row, int col){
        int i;
        if(row < 9 && col < 9){
            if(sudoku.cells[row][col].isSolved){
                if((col+1)<9) return Backtracking(row,col+1);
                else if((row+1)<9) return Backtracking(row+1,0);
                else return true;
            }
            else{
                for(i=0;i<9;i++){
                    if(isValid(row,col,i+1)){
                        sudoku.cells[row][col].setValue(i+1); 
                        if((col+1)<9){
                            if(Backtracking(row,col+1)) return true;
                            else sudoku.cells[row][col]= new Cell(row, col);
                        }
                        else if((row+1)<9){
                            if(Backtracking(row+1,0)) return true;
                            else sudoku.cells[row][col]= new Cell(row, col);
                        }
                        else return true;
                    }
                }
            }
            return false;
        }
        else return true; 
    }
    
    public Sudoku getSudoku(){
        return sudoku;
    }
}
