package ModelT;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Cell extends Observable implements Observer {

    private List<Integer> values = new ArrayList<Integer>();//posibilidades de cada celda
    public boolean isSolved = false;//
    private boolean can_modified = true;
    private int row;//
    private int col;//
    
    public boolean isSolved(){
        return isSolved;
    }

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.can_modified = true;
        for (int n = 1; n <= 9; n++) {//agrega los posibles valores de la celda
            values.add(n);
        }
    }
    
    public boolean isCandidate(int n){
        if (values.contains(n)) return true;
        else return false;
    }
    

    // adiciona celdas que estan en la misma linea, cuadro como observadores
    public synchronized void addObserver(Cell[][] cells) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                boolean isSame = (i == row) && (j == col);//no nos interesa que se observe a si mismo
                boolean isSameLine = (i == row) || (j == col);//está en la fila o la columna
                boolean isSecondary = (i / 3 == row / 3) && (j / 3 == col / 3);//está en el recuadro
                if (!isSame && (isSameLine || isSecondary)) {
                    super.addObserver(cells[i][j]);//agrega un observador en esa posición
                }
            }
        }
    }
    
   // adiciona valores conocidos despues de limpiar y notificar
    public void setValue(int value) {//se pone el valor de la celda
        
{
            values.clear();//ya sabemos cuál es el valor que debe ir en esa celda
            values.add(value);//lo ponemos
            isSolved = true;//esta celda está solucionada
            super.setChanged();//cambió
            super.notifyObservers(value);//notificar a los observadores
            
        }
    }
       
    // Observa y remueve el conjunto de entrada en el observable
    @Override
    public void update(Observable o, Object arg) {
        values.remove(arg);//removemos la opción que se puso en setValue
       /* if (!isSolved && values.size() == 1) {//si hay una sola posibilidad en la casilla
            int value = values.get(0);
            setValue(value);//se pone
        }*/
    }

    public Iterator<Integer> iterator() {
        return values.iterator();
    }

    // una celda es solved si solo tiene un valor
    public int getValue() {
        return (values.size() == 1) ? values.get(0) : 0;//si hay una sola opción, la muestra, si hay varias muestra 0
    }

    /**
     * @return the can_modified
     */
    public boolean isCan_modified() {
        return can_modified;
    }

    /**
     * @param can_modified the can_modified to set
     */
    public void setCan_modified(boolean can_modified) {
        this.can_modified = can_modified;
    }

    public List<Integer> candidates() {
        return values; //To change body of generated methods, choose Tools | Templates.
    }
}
