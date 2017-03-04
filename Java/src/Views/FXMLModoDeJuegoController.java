package Views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Jorge
 */
public class FXMLModoDeJuegoController implements Initializable {
    
    @FXML
    private Button botonSudokuS;
    
    @FXML
    private Button botonSudokuT;
    
    @FXML
    private MenuItem Cerrar;
    
    @FXML
    private MenuItem Acerca;
    
    @FXML
    private MenuItem Info;
    
    @FXML
    private Button restart;
    
    @FXML
    private ImageView iv1;
    
    @FXML
    private void SetViewudokuS(ActionEvent event) throws IOException{
        Parent ParentSudokuT = FXMLLoader.load(getClass().getResource("/View_ControllerS/FXMLSudokuS.fxml"));
        Scene ParentSudokuTScene = new Scene(ParentSudokuT);
        Stage Next_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Next_Stage.setScene(ParentSudokuTScene);
        Next_Stage.centerOnScreen();
        Next_Stage.setTitle("JoJuKu");
        Next_Stage.show();
    }
    
    @FXML
    private void SetViewudokuT(ActionEvent event) throws IOException{
        Parent ParentSudokuT = FXMLLoader.load(getClass().getResource("/View_ControllerT/FXMLSudokuT.fxml"));
        Scene ParentSudokuTScene = new Scene(ParentSudokuT);
        Stage Next_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Next_Stage.setScene(ParentSudokuTScene);
        Next_Stage.centerOnScreen();
        Next_Stage.setTitle("JuJoKu");
        Next_Stage.show();
    } 
    
    @FXML
    private void Restart(ActionEvent event) throws IOException{
        Parent ParentSudokuT = FXMLLoader.load(getClass().getResource("/Views/FXMLViewP.fxml"));
        Scene ParentSudokuTScene = new Scene(ParentSudokuT);
        Stage Next_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Next_Stage.setScene(ParentSudokuTScene);
        Next_Stage.centerOnScreen();
        Next_Stage.setTitle("JoJuKu");
        Next_Stage.show();
    }
    
    @FXML
    private void BotonInfo(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/FXMLInfo.fxml"));
        AnchorPane ventana = (AnchorPane) loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(ventana);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void BotonJugar(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/FXMLInstruccion.fxml"));
        AnchorPane ventana = (AnchorPane) loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(ventana);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void BotonSalir(ActionEvent event) {
        Platform.exit();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image imagen = new Image("file:sudoku2.jpg");
        iv1.setImage(imagen);
        iv1.setFitWidth(150);
        iv1.setFitHeight(150);
        iv1.setPreserveRatio(true);
        iv1.setSmooth(true);
        iv1.setCache(true);
    }        
}
