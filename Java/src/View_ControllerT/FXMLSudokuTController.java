package View_ControllerT;

import ModelT.CrearSudoku;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import ModelT.Sudoku;
import javafx.scene.Node;
import ModelT.SolvedBacktraking;
import ModelT.SolvedConstraints;
import ModelT.SudokuParser;
import java.io.File;
import java.util.List;
import static javafx.geometry.Pos.TOP_CENTER;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.GREEN;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import static javafx.scene.paint.Color.YELLOW;
import javafx.stage.FileChooser.ExtensionFilter;

public class FXMLSudokuTController implements Initializable {
    
    int[][] puzzle;
    
    private Sudoku sudoku;
    
    private Sudoku flat;

    @FXML
    private Button boton;
    
    @FXML
    private TextField Col;
    
    @FXML
    private TextField Fil;
    
    @FXML
    private MenuItem nuevoJuego;
    
    @FXML
    private MenuItem Abrir;
    
    @FXML
    private MenuItem Guardar;
    
    @FXML
    private MenuItem Cerrar;
    
    @FXML
    private TextField Num;
    
    @FXML
    private ToggleButton Candidatos;
    
    @FXML
    private ToggleButton Backtraking;
    
    @FXML 
    private ToggleButton Restricciones;
    
    @FXML
    private ToggleButton Dificil;
    
    @FXML
    private ToggleButton Normal;
    
    @FXML
    private ToggleButton Facil;
    
    @FXML
    private Button NuevoGame;
    
    @FXML
    private Button Resolver;
    
    @FXML 
    private Button delete; 
    
    @FXML 
    private Button restart; 
    
    @FXML
    private GridPane Tablero;
    
    @FXML
    private Label NumI;
    
    @FXML
    private Button Salir;
    
    @FXML
    private Label label;
    
    private boolean resuelto = false;
    
    //matriz de ceros utilizada como matriz auxiliar en el metodo reload
    
    int [][] cero={ {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0}};
    
    int [][] forma={{1, 1, 1, 2, 2, 2, 3, 3, 3},
                    {1, 1, 1, 2, 2, 2, 3, 3, 3},
                    {1, 1, 1, 2, 2, 2, 3, 3, 3},
                    {4, 4, 4, 5, 5, 5, 6, 6, 6},
                    {4, 4, 4, 5, 5, 5, 6, 6, 6},
                    {4, 4, 4, 5, 5, 5, 6, 6, 6},
                    {7, 7, 7, 8, 8, 8, 9, 9, 9},
                    {7, 7, 7, 8, 8, 8, 9, 9, 9},
                    {7, 7, 7, 8, 8, 8, 9, 9, 9}};
    
//metodo para pintar el tablero en la vista     
    private void drawPuzzle(Sudoku board){     
        for(int i = 0 ; i < 9;i++ ){
            for(int j = 0; j< 9 ; j++){
                if(board.cells[i][j].isSolved()){
                    NumI = new Label();               
                    NumI.setText(Integer.toString(board.getCellValue(i, j)));
                    NumI.setFont(Font.font("verdana", 24));
                    NumI.setTextFill(Color.web("#E4122B"));
                    NumI.setMaxSize(49, 49);
                    NumI.setAlignment(Pos.CENTER);
                    Tablero.add(NumI, j, i);  
                }
            }
        }
    }
    
    private void drawCell(int [][] matriz){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int n = matriz[i][j];
                label = new Label();
                label.setMaxSize(49, 49);
                switch(n){
                case 1:  label.setStyle(" -fx-background-color: #CEECF5"); break;
                case 2:  label.setStyle(" -fx-background-color: #CED8F6"); break;
                case 3:  label.setStyle(" -fx-background-color: #E2A9F3"); break;
                case 4:  label.setStyle(" -fx-background-color: #F5A9BC"); break;
                case 5:  label.setStyle(" -fx-background-color: #A9F5A9"); break;
                case 6:  label.setStyle(" -fx-background-color: #F5BCA9"); break;
                case 7:  label.setStyle(" -fx-background-color: #F2F5A9"); break;
                case 8:  label.setStyle(" -fx-background-color: #A9BCF5"); break;
                case 9:  label.setStyle(" -fx-background-color: #F5D0A9"); break;
            }
            Tablero.add(label,j,i);
            }
        }
    }

//Metodo para insertar un numero en el modelo y vista ,en una posicion determinada.
    private void insert(int num, int ROW, int COL){
        if(!sudoku.cells[ROW][COL].isSolved()){
            sudoku.cells[ROW][COL].setValue(num);
            for(Node node: Tablero.getChildren())
                if(node instanceof Label && Tablero.getColumnIndex(node)==COL && Tablero.getRowIndex(node)== ROW){
                    ((Label) node).setTextFill(BLACK);
                    ((Label) node).setText(Num.getText());
                    ((Label) node).setFont(Font.font("verdana", 24));
                    ((Label) node).setMaxSize(49, 49);
                    ((Label) node).setAlignment(Pos.CENTER);            
                }
        }
        else{
            sudoku.cells[ROW][COL].setValue(num);
            for(Node node: Tablero.getChildren())
                if(node instanceof Label && Tablero.getColumnIndex(node)==COL && Tablero.getRowIndex(node)== ROW){
                    ((Label) node).setTextFill(GREEN);
                    ((Label) node).setText(Num.getText());
                    ((Label) node).setFont(Font.font("verdana", 24));
                    ((Label) node).setMaxSize(49, 49);
                    ((Label) node).setAlignment(Pos.CENTER);
                }
        }
        if(vResuelto()) {
            resuelto = true;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sudoku Resuelto");
            alert.setHeaderText("¡¡Ganaste!!");
            alert.setContentText("¡Felicitaciones! Haz ganado");
            alert.show();
        }
    }
    
    private boolean vResuelto(){
        for(int i = 0; i < sudoku.size; i++){
            for(int j = 0; j < sudoku.size; j++){
                if(!sudoku.cells[i][j].isSolved) return false;
            }
        }
        return true;
    }

//metodo ara validar si los nuero ingresados por los campos de texto, esta en el rago requerido.
    private boolean inRange(int ROW,int COL, int num) {
        return (num > 0 && num < 10) && (ROW > 0 && ROW < 10) && (COL > 0 && COL < 10);
    }

//metodo para limpiar los candidatos de las casillas no resultas.    
    private void clear(){
        for(int i = 0; i < 9 ; i++){
            for (int j = 0; j < 9; j++) {
                if(!sudoku.cells[i][j].isSolved()){
                    for (Node node : Tablero.getChildren()) {
                        if(node instanceof Label && Tablero.getColumnIndex(node)== j && Tablero.getRowIndex(node)== i  ){
                            ((Label) node).setText("");
                        }
                    }
                }
            }
        }
    }

//metodo para volver a cargar todos los candidatos de todas las casillas.
    private void reload(){
        int [][] board = cero;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(sudoku.cells[i][j].isSolved()){
                    board[i][j]= sudoku.cells[i][j].getValue();
                }
                else board[i][j]=0; 
            }
        }       
        flat = Sudoku.getNewInstance();
        flat.setup(board);
        for (int j = 0; j < 9; j++){
            for (int k = 0; k < 9; k++) {
                flat.cells[j][k].setCan_modified(sudoku.cells[j][k].isCan_modified());
            }
        }
        sudoku.cells = flat.getCells();
    }

//Metodo para limpiar todo el tablero , es utilizado para cargar un archivo sobre el tablero limpio.
    private void clearTable(){
        for(int i = 0; i < 9 ; i++){
            for (int j = 0; j < 9; j++) {
                for (Node node : Tablero.getChildren()) {
                    if(node instanceof Label && Tablero.getColumnIndex(node)== j && Tablero.getRowIndex(node)== i  ){
                        ((Label) node).setText("");
                    }
                }
            }
        }
    }

//Metodo para borrar un numero de determinada posicion, solo si es modificable.
    private void delete(int row,int col){
        sudoku.cells[row][col].setValue(0);
        for (Node node : Tablero.getChildren()) {
            if(node instanceof Label && Tablero.getColumnIndex(node)== col && Tablero.getRowIndex(node)== row)
               ((Label) node).setText("");
            reload();
        }
    }

    
//funcion para imprimir los candidatos de todas las casillas del tablero no resultas.   
     private void candidates(){
        for (int i = 0; i < 9 ; i++){
            for(int j = 0; j< 9; j++){
               if(!sudoku.cells[i][j].isSolved){
                    List<Integer> candidates = sudoku.cells[i][j].candidates();
                    NumI = new Label();
                    NumI.setPrefHeight(49);
                    NumI.setPrefWidth(49);
                    NumI.setFont(Font.font("verdana", 10));
                    String s = "";
                    int k = 0;
                    for(int x = 1; x <= 9; x++){
                        if(candidates.contains(x)) {
                            s += candidates.get(k)+" ";
                            k++;
                        }
                        else s+="   ";
                        if((x % 3== 0) && (x != 9)) s+="\n";
                    }
                    NumI.setText(s);
                    NumI.setAlignment(TOP_CENTER);
                    Tablero.add(NumI,j,i);
                    NumI.setTextFill(Color.web("black"));            
               }
            }
        }
    }
     
    @FXML
    private void BotonNuevoJuego(ActionEvent event){
        if(Dificil.isSelected() || Normal.isSelected() || Facil.isSelected()){
            clear();
            clearTable();
            resuelto = false;
            if(Dificil.isSelected()) sudoku = new CrearSudoku().generator(3);
            else if(Normal.isSelected()) sudoku = new CrearSudoku().generator(2);
            else sudoku = new CrearSudoku().generator(1);   
            clear();
            reload();        
            drawPuzzle(sudoku);
            Candidatos.setSelected(false);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("¡¡Nivel de Juego!!");
            alert.setContentText("No se ha seleccionado ningun nivel de juego");
            alert.show();
        }
    }
    
    @FXML
    private void BotonDificil(ActionEvent event){
        if(Dificil.isSelected()){
            Normal.setSelected(false);
            Facil.setSelected(false);
        }
    }
    
    @FXML
    private void BotonNormal(ActionEvent event){
        if(Normal.isSelected()){
            Dificil.setSelected(false);
            Facil.setSelected(false);
        }
    }
    
    @FXML
    private void BotonFacil(ActionEvent event){
        if(Facil.isSelected()){
            Normal.setSelected(false);
            Dificil.setSelected(false);
        }
    }
     
    @FXML
    private void BotonAyuda(ActionEvent event){
        Stage stage = new Stage();
        stage.show();
    }
     
//boton para borrar un numero
    @FXML
    private void Delete(ActionEvent event){
        if(!resuelto){
        int ROW =  Integer.parseInt(Fil.getText());
        int COL =  Integer.parseInt(Col.getText());
            if(sudoku.cells[ROW-1][COL-1].isCan_modified()){
                if(Candidatos.isSelected()){
                    delete(ROW-1,COL-1);
                    clear();  
                    candidates();
                    sudoku.cells[ROW-1][COL-1].isSolved = false;
                }
                else{
                    delete(ROW-1,COL-1);
                }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("¡¡Celda Inmodificable!!");
                alert.setContentText("La celda no es modificable");
                alert.show();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("¡¡Sudoku Resuelto!!");
            alert.setContentText("El Sudoku ya esta resuelto");
            alert.show();
        }       
    }

//boton para resolver un sudoku por el metodo de backtracking
    @FXML
    private void BotonResolver(ActionEvent event){
        if(!resuelto){
            clear();
            if(Backtraking.isSelected()){
                SolvedBacktraking Res = new SolvedBacktraking(sudoku);
                if (Res.Backtracking(0, 0)){
                    drawPuzzle(Res.getSudoku());
                    resuelto = true;
                    Candidatos.setSelected(false);
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("¡¡Sudoku Sin Solución!!");
                    alert.setContentText("El Sudoku no tiene solución");
                    alert.show();
                    reload();
                    resuelto = false;
                }
            }
            if(Restricciones.isSelected()){
                ObtenerPuzzle();
                SolvedConstraints solving = new SolvedConstraints(puzzle);
                solving.Solved();
                puzzle = solving.getMatriz();
                sudoku = Sudoku.getNewInstance();
                sudoku.setup(puzzle);
                drawPuzzle(sudoku);
                resuelto = true;
                Candidatos.setSelected(false);
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("¡¡Sudoku Resuelto!!");
            alert.setContentText("El Sudoku ya esta resuelto");
            alert.show();
        }
    } 
    
    private void ObtenerPuzzle(){
        puzzle = new int[9][9];
        for(int i = 0; i < sudoku.size; i++){
            for(int j = 0; j < sudoku.size; j++){
                puzzle[i][j] = sudoku.cells[i][j].getValue();
            }
        }
    }
    
    @FXML
    private void BotonBacktraking(ActionEvent event){
        if(Backtraking.isSelected()) Restricciones.setSelected(false);
    }
    
    @FXML
    private void BotonRestricciones(ActionEvent event){
        if(Restricciones.isSelected()) Backtraking.setSelected(false);
    }

//boton para leer un archivo 
    @FXML
    private void BotonAbrir(ActionEvent event){
        Stage stage = new Stage();
        stage.centerOnScreen();
        SudokuParser lectura;
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("JuJoku", "*.jujk"), 
                new ExtensionFilter("Archivos de texto", "*.txt"));
        File file = fileChooser.showOpenDialog(stage);
        int i = 0, j = 0;
        if(file != null){
            FileReader fr = null;
            BufferedReader br = null;
            try{
                fr = new FileReader(file);
                br = new BufferedReader(fr);
            }catch(Exception e){}
        }
        lectura = new SudokuParser(file);
        for(String str : lectura.getStringsList()){
            clear();
            clearTable();
            resuelto = false;
            sudoku = sudoku = Sudoku.getNewInstance();
            sudoku.setup(str);
            clear();
            reload();        
            drawPuzzle(sudoku);            
        }
    }

//boton para guardar un archivo
    @FXML
    private void BotonGuardar(ActionEvent event){
        if(!resuelto){
            Stage stage = new Stage();
            stage.centerOnScreen();
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new ExtensionFilter("JuJoku", "*.jujk"));
            File file = fileChooser.showSaveDialog(stage);  
            if(file != null){
                FileWriter fw = null;
                BufferedWriter bw = null;
                try{
                    fw = new FileWriter(file, false);
                    bw = new BufferedWriter(fw);
                    fw.write("9");
                    fw.write(System.getProperty("line.separator"));
                    for(int i = 0; i < 9; i++){
                        for(int j = 0; j < 9; j++){
                            if(sudoku.cells[i][j].getValue() != 0) {
                                fw.write(String.valueOf(sudoku.cells[i][j].getValue()));
                            }
                            else fw.write(".");
                        }
                    }
                }catch (Exception e){
                }finally{
                    try{
                        bw.close();
                    }catch(Exception e){}
                }
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("¡¡Sudoku Resuelto!!");
            alert.setContentText("Un sudoku resuelto no se puede guardar");
            alert.show();          
        }
    }
    
    @FXML
    private void Restart(ActionEvent event) throws IOException{
        Parent ParentSudokuT = FXMLLoader.load(getClass().getResource("/Views/FXMLModoDeJuego.fxml"));
        Scene ParentSudokuTScene = new Scene(ParentSudokuT);
        Stage Next_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Next_Stage.setScene(ParentSudokuTScene);
        Next_Stage.centerOnScreen();
        Next_Stage.setTitle("Sudoku");
        Next_Stage.show();
    }

 
//boton para cerrar el programa
    @FXML
    private void BotonCerrar(ActionEvent event){
        Platform.exit();
    }
    
//boton para mostrar y quitar candidatos
    @FXML
    private void BotonCandidatos(ActionEvent event){
        if(Candidatos.isSelected()) candidates();
        else clear();
    }
    
    
    
//boton para insertar numeros
    @FXML
    private void BotonInsertar(ActionEvent event){
        if(!resuelto){
            int aux;
            int ROW =  Integer.parseInt(Fil.getText());
            int COL =  Integer.parseInt(Col.getText());
            int num = Integer.parseInt(Num.getText()); 
            if(inRange(ROW,COL,num)){
                if (sudoku.cells[ROW-1][COL-1].isCan_modified()){
                    if(!sudoku.cells[ROW-1][COL-1].isSolved){
                        if(sudoku.cells[ROW-1][COL-1].isCandidate(num)){
                            if(Candidatos.isSelected()){
                                clear();
                                insert(num, ROW-1,COL-1);
                                candidates();
                            }
                            else{
                                insert(num, ROW-1,COL-1); 
                            }                     
                        }
                        else{
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("¡¡Candidato Imposible!!");
                            alert.setContentText("El numero ingresado no es candidato");
                            alert.show();
                        }
                    }
                    else if(sudoku.cells[ROW-1][COL-1].isSolved){
                        aux = sudoku.cells[ROW-1][COL-1].getValue();
                        sudoku.cells[ROW-1][COL-1].setValue(0);
                        sudoku.cells[ROW-1][COL-1].isSolved = false;
                        reload();
                        if(sudoku.cells[ROW-1][COL-1].isCandidate(num)) {
                            if(Candidatos.isSelected()){
                                insert(num, ROW-1,COL-1);
                                clear();
                                candidates();
                                reload();
                            }
                            else{
                                insert(num, ROW-1,COL-1);
                                reload();
                            }
                        } 
                        else {
                            sudoku.cells[ROW-1][COL-1].setValue(aux);
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Error");
                            alert.setHeaderText("¡¡Candidato Imposible!!");
                            alert.setContentText("El numero ingresado no es candidato");
                            alert.show();
                        }
                    }            
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("¡¡Celda Inmodificable!!");
                    alert.setContentText("La celda no es modificable");
                    alert.show();
                }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("¡¡Numero en COl, Fila o Num Fuera de Rango!!");
                alert.setContentText("El numero ingresado en Col, Fila o Num estan por fuera del rango ");
                alert.show();
            }
        }    
        else{    
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("¡¡Sudoku Resuelto!!");
            alert.setContentText("El Sudoku ya esta resuelto");
            alert.show();
        }
    }
    
    
//metodo que inicializa el sudoku    
    @Override
    public void initialize(URL url, ResourceBundle rb) {   
        int[][] puzzleInicial ={{1, 0, 0, 0, 0, 0, 0, 9, 0},
                                {0, 9, 0, 4, 0, 0, 6, 0, 8},
                                {0, 0, 2, 0, 1, 9, 0, 0, 0},
                                {2, 0, 0, 0, 0, 8, 0, 0, 0},
                                {0, 0, 9, 0, 0, 0, 5, 0, 0},
                                {0, 0, 0, 7, 5, 0, 0, 0, 9},
                                {0, 0, 0, 3, 0, 0, 1, 0, 0},
                                {5, 0, 6, 0, 8, 1, 0, 7, 0},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0}};
        drawCell(forma);
        sudoku = Sudoku.getInstance();       
        sudoku.setup(puzzleInicial);
        candidates();
        clear();
        drawPuzzle(sudoku);
    }
}

/*   
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setContentText("La celda no es modificable");
    alert.show();

http://www.uaq.mx/ingenieria/publicaciones/eureka/n24/camepa01.pdf
      
https://www.youtube.com/watch?v=4Lj36wbywp8
*/
