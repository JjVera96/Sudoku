/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelT;

/**
 *
 * @author Juan Vera
 */
public class CrearSudoku {
        public Sudoku generator(int dificultad){
        Estrategia strategy = null;
        if(dificultad == 1) strategy = new SudokuFacil();
        if(dificultad == 2) strategy = new SudokuMedio();
        if(dificultad == 3) strategy = new SudokuDificil();
        return strategy.generator();
    }
}
