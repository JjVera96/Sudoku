package ModelT;

import java.util.Random;

public class SudokuMedio implements Estrategia {
    Sudoku sudoku;
    SolvedBacktraking Res;
    int quitar;
    
    private void NumQuitar(){
        Random rnd = new Random();
        int i, j;
        quitar = rnd.nextInt(50) + 36;  
        sudoku = Sudoku.getNewInstance();;
        LlenarFila();
        Res = new SolvedBacktraking(sudoku);
        Res.Backtracking(0, 0);
        sudoku = Res.getSudoku();
        for(int k = 1; k < quitar; k++){
            i = rnd.nextInt(9);
            j = rnd.nextInt(9);
            sudoku.cells[i][j].setValue(0);
            sudoku.cells[i][j].isSolved = false;
        }
        for(i = 0; i < 9; i++){
            for(j = 0; j < 9; j++){
                if(sudoku.cells[i][j].getValue() != 0){
                    sudoku.cells[i][j].setCan_modified(false);
                }
            }
        }       
    }
    
    private boolean Verificar(int i){
        for(int j = 0; j < sudoku.size-1; j++){
            if(sudoku.cells[0][j].getValue() == i) return false;
        }
        return true;
    }

    private void LlenarFila(){
        Random rnd = new Random();
        int Aux;
        for(int j = 0; j < sudoku.size; j++){
            Aux = rnd.nextInt(9) + 1;
            if(j == 0) sudoku.cells[0][j].setValue(Aux);
            else{
                while(!Verificar(Aux)){
                    Aux = rnd.nextInt(9) + 1;
                }
                sudoku.cells[0][j].setValue(Aux);
            }
        }
    }
    
    @Override
    public Sudoku generator() {
        NumQuitar();
        return sudoku;
    }
}
