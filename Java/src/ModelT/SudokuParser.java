package ModelT;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SudokuParser {
    
    private String filename;//nombre del archivo que se va a leer
    private int size;//números en el sudoku
    private List<String> stringsList;//sudokus
    
    public SudokuParser(File filename) {//recibe el nombre del archivo
        //this.filename = filename;
        File fin = filename;//lo lee como un objeto File
        stringsList = new ArrayList<String>();
        
        try {
            Scanner scanner = new Scanner(fin);//para leer el archivo
            if (scanner.hasNextInt()) {//busca el siguiente entero
                size = scanner.nextInt();//número que encontró en el scanner
            }
            if (scanner.hasNextLine()) {//si tiene más líneas
                String emptyLine = scanner.nextLine();//la guarda en el String
            }
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();//lee cada línea del archivo
                stringsList.add(s);//guarda los sudokus(ya que en cada línea le enviamos un sudoku)
            }
        } catch (Exception e) {//si no hay archivo
            e.printStackTrace();
        }
    }
    
    public int getSize() {
        return size;
    }
    
    public List<String> getStringsList() {
        return stringsList;
    }    
}