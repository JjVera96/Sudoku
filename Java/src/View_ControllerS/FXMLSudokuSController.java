package View_ControllerS;

import ModelS.SolvedS;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import static javafx.scene.paint.Color.BLACK;
import ModelS.SudokuS;
import ModelT.SudokuParser;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import static javafx.geometry.Pos.TOP_CENTER;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FXMLSudokuSController implements Initializable {
    
    @FXML
    private GridPane tablero;
    
    @FXML
    private Label label;
    
    @FXML
    private TextField Fil;
    
    @FXML
    private TextField Col;
    
    @FXML
    private TextField Num;
    
    @FXML
    private ToggleButton Candidatos;
    
    @FXML
    private Button salir;
    
    @FXML
    private Button borrar;
    
    @FXML
    private Button Resolver;
    
    @FXML
    private Button insertar;
    
    @FXML
    private Button restar;
    
    @FXML
    private MenuItem Abrir;
    
    @FXML
    private MenuItem Cerrar;    
    
    @FXML
    private MenuItem Guardar;
 
    
    private SudokuS sudoku;
    
    private SudokuS flat;
    
    private boolean resuelto;
    
        int [][] cero={ {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0}};
        
        int forma [][] = {  {1 ,1 ,1, 1, 2, 2, 2, 3, 3},
                            {1, 1, 1, 1, 2, 2, 2, 3, 3},
                            {4, 4, 1, 2, 2, 5, 3, 3, 3},
                            {4, 4, 4, 4, 2, 5, 5, 3, 3},
                            {4, 4, 4, 5, 5, 5, 6, 6, 6},
                            {7, 7, 5, 5, 8, 6, 6, 6, 6},
                            {7, 7, 7, 5, 8, 8, 9, 6, 6},
                            {7, 7, 8, 8, 8, 9, 9, 9, 9},
                            {7, 7, 8, 8, 8, 9, 9, 9, 9}};

    /**
     * Initializes the controller class.
     */
    
    private void insert(int num, int ROW, int COL){
        if(sudoku.cells[ROW][COL].isSolved()){
            sudoku.cells[ROW][COL].setValue(num);
            for (Node node : tablero.getChildren()) {
                if(node instanceof Label && tablero.getColumnIndex(node)== COL && tablero.getRowIndex(node)== ROW  ){
                    ((Label) node).setText("");
                    ((Label) node).setTextFill(BLACK);
                    ((Label) node).setText(Integer.toString(num));
                    ((Label) node).setFont(Font.font("verdana", 24));
                    //((Label) node).setMaxSize(48, 48);
                    ((Label) node).setAlignment(Pos.CENTER); 
                }
            }     
        }
        else{
            sudoku.cells[ROW][COL].setValue(num);
            for (Node node : tablero.getChildren()) {
                if(node instanceof Label && tablero.getColumnIndex(node)== COL && tablero.getRowIndex(node)== ROW  ){
                    ((Label) node).setTextFill(BLACK);
                    ((Label) node).setText(Integer.toString(num));
                    ((Label) node).setFont(Font.font("verdana", 24));
                     //((Label) node).setMaxSize(48, 48);
                    ((Label) node).setAlignment(Pos.CENTER);
                }
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
    
    //Metodo para limpiar todo el tablero , es utilizado para cargar un archivo sobre el tablero limpio.
    private void clearTable(){
        for(int i = 0; i < 9 ; i++){
            for (int j = 0; j < 9; j++) {
                for (Node node : tablero.getChildren()) {
                    if(node instanceof Label && tablero.getColumnIndex(node)== j && tablero.getRowIndex(node)== i){
                        ((Label) node).setText("");
                    }
                }
            }
        }
    }
    
    //Metodo para borrar un numero de determinada posicion, solo si es modificable.
    private void delete(int row,int col){
        sudoku.cells[row][col].setValue(0);
        for (Node node : tablero.getChildren()) {
            if(node instanceof Label && tablero.getColumnIndex(node)== col && tablero.getRowIndex(node)== row)
                ((Label) node).setText("");
                reload();
        }
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
                    for (Node node : tablero.getChildren()) {
                        if(node instanceof Label && tablero.getColumnIndex(node)== j && tablero.getRowIndex(node)== i  ){
                        ((Label) node).setText("");}
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
        flat = SudokuS.getNewInstance(forma);
        flat.setup(board);
        for (int j = 0; j < 9; j++){
            for (int k = 0; k < 9; k++) {
                flat.cells[j][k].setCan_modified(sudoku.cells[j][k].isCan_modified());
            }
        }
        sudoku.cells = flat.getAllCells();
    }
    
    private void candidates(){
        for (int i = 0; i < 9 ; i++){
            for(int j = 0; j< 9; j++){
               if(!sudoku.cells[i][j].isSolved){
                    List<Integer> candidates = sudoku.cells[i][j].candidates();
                    label = new Label();
                    label.setPrefHeight(60);
                    label.setPrefWidth(60);
                    label.setFont(Font.font("verdana", 10));
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
                    label.setText(s);
                    label.setAlignment(TOP_CENTER);
                    tablero.add(label,j,i);
                    label.setTextFill(Color.web("black"));                
               }
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int sudoku_to_solve [][] = {{0, 0, 0, 0, 8, 9, 0, 0, 0},
                                    {0, 0, 5, 0, 0, 0, 0, 7, 0},
                                    {0, 0, 0, 0, 0, 0, 8, 9, 0},
                                    {8, 0, 9, 0, 5, 0, 0, 0, 0},
                                    {0, 0, 6, 0, 0, 0, 2, 0, 0},
                                    {0, 0, 0, 0, 1, 0, 6, 0, 3},
                                    {0, 5, 4, 0, 0, 0, 0, 0, 0},
                                    {0, 1, 0, 0, 0, 0, 9, 0, 0},
                                    {0, 0, 0, 4, 3, 0, 0, 0, 0}};
        sudoku = SudokuS.getInstance(forma);
        sudoku.setup(sudoku_to_solve);  
        drawPuzzle();
        candidates();
        clear();
    }
    
    private void drawPuzzle(){
        int n ;
        for(int i = 0 ; i < sudoku.getSize();i++ ){
            for(int j = 0; j< sudoku.getSize() ; j++){
                label = new Label();
                label.setPrefHeight(49);
                label.setPrefWidth(49);
                label.setStyle("-fx-border-color: black");
                label.setStyle("-fx-border-width: 2");
                if(sudoku.cells[i][j].isSolved()){
                    label.setText(Integer.toString(sudoku.getCellValue(i, j)));
                    label.setFont(Font.font("verdana", 24));
                    label.setTextFill(Color.web("#DF0101"));
                    label.setMaxSize(48.2, 48.2);
                    label.setAlignment(Pos.CENTER);
                }
                n = sudoku.getForma()[i][j];
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
                tablero.add(label,j,i);
            }
        }
    }
    
    //boton para insertar numeros
    @FXML
    private void BotonInsertar(ActionEvent event) {
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
                            }else insert(num,ROW-1,COL-1);
                        }
                        else{
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Candidato Imposible");
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
                            alert.setTitle("Candidato Imposible");
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
    //boton para cerrar el programa
    @FXML
    private void BotonCerrar(ActionEvent event){
        Platform.exit();
    }
    
    @FXML
    private void BotonResolver(ActionEvent event){
        if(!resuelto){
            clear();
            SolvedS Res = new SolvedS(sudoku);
            if (Res.Backtracking(0, 0)){
                sudoku = Res.getSudoku();
                drawPuzzle();
                Candidatos.setSelected(false);
                resuelto = true;
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
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Error");
            alert.setHeaderText("¡¡Sudoku Resuelto!!");
            alert.setContentText("El Sudoku ya esta resuelto");
            alert.show();          
        }
    }
    
//boton para mostrar y quitar candidatos
    @FXML
    private void BotonCandidatos(ActionEvent event){
        if(Candidatos.isSelected()) candidates();
        else clear();
    }
    
    private int[][] stringtoMatriz(String s){
        int[][] matriz = new int[9][9];
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                matriz[i][j] = s.charAt(i * 9 + j) - '0';
            }
        }
        return matriz;
    }
    
    //boton para leer un archivo 
    @FXML
    private void BotonAbrir(ActionEvent event){
        Stage stage = new Stage();
        stage.centerOnScreen();
        SudokuParser lectura;
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JoJuku", "*.jojk"), 
                new FileChooser.ExtensionFilter("Archivos de texto", "*.txt"));
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
        sudoku = SudokuS.getNewInstance(stringtoMatriz(lectura.getStringsList().get(0)));
        sudoku.setup(lectura.getStringsList().get(1));
        clear();
        clearTable();
        resuelto = false;
        
        clear();
        reload();        
        drawPuzzle();
    }
    
    //boton para guardar un archivo
    @FXML
    private void BotonGuardar(ActionEvent event){
        if(!resuelto){
            Stage stage = new Stage();
            stage.centerOnScreen();
            int [][] Forma = sudoku.getForma();
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("JoJuku", "*.jojk"));
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
                            fw.write(String.valueOf(Forma[i][j]));
                        }
                    }
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

}
