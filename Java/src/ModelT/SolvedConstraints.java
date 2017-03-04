/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelT;

import java.util.ArrayList;
import org.jacop.constraints.Alldistinct;
import org.jacop.core.IntVar;
import org.jacop.core.Store;
import org.jacop.search.DepthFirstSearch;
import org.jacop.search.IndomainMin;
import org.jacop.search.Search;
import org.jacop.search.SelectChoicePoint;
import org.jacop.search.SimpleMatrixSelect;

public class SolvedConstraints {
    IntVar[][] elements;
    int[][] setup;
    
    public SolvedConstraints(int[][] setup){
        this.setup = setup;
    }
    
    public void Solved(){
        ArrayList<IntVar> vars = new ArrayList<IntVar>();
        Store store = new Store();
        this.elements = new IntVar[9][9];
        for (int i = 1; i<=9; i++)
            for (int j = 1; j<=9; j++)
                if (setup[i-1][j-1] == 0) {
                    elements[i-1][j-1] = new IntVar(store, "f" + i + j, 1,9);
                    vars.add(elements[i-1][j-1]);
                }
                else{
                    elements[i-1][j-1] = new IntVar(store, "f" + i + j,
                    setup[i-1][j-1], setup[i-1][j-1]);
                    vars.add(elements[i-1][j-1]);
                }
        for (int i = 0; i <9; i++)
            store.impose(new Alldistinct(elements[i]));

	for (int j = 0; j <9; j++) {
            IntVar[] column = new IntVar[9];
            for (int i = 0; i <9; i++)
                column[i] = elements[i][j];
            store.impose(new Alldistinct(column));
	}
                
                // Creating constraints for blocks.
	for (int i = 0; i<3; i++)
            for (int j = 0; j<3; j++) {
		ArrayList<IntVar> block = new ArrayList<IntVar>();
		for (int k = 0; k < 3; k++)
                    for (int m = 0; m<3; m++)
                        block.add(elements[i * 3 + k][j * 3 + m]);
		store.impose(new Alldistinct(block));
            }
                                //ComparatorVariable varSelect; 
        Search<IntVar> label = new DepthFirstSearch<IntVar>(); 
        SelectChoicePoint<IntVar> select = new SimpleMatrixSelect<IntVar>(
            elements,null, new IndomainMin<IntVar>()
        );
        boolean result = label.labeling(store, select);
        Converter();
    }

    public void Converter(){
        for(int i=0; i<9; i++)
            for(int j=0; j<9; j++)
                setup[i][j] = elements[i][j].value();
    }
    
    public int[][] getMatriz(){
        return setup;
    }
}

