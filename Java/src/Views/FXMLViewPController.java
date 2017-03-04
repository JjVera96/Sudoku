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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class FXMLViewPController implements Initializable {
    
    @FXML
    private Button boton;
    
    @FXML
    private Button Salir;
    
    @FXML
    private ImageView iv1;
    
    
    
    
    @FXML
    private void SetView(ActionEvent event) throws IOException{
        Parent ParentSudokuT = FXMLLoader.load(getClass().getResource("/Views/FXMLModoDeJuego.fxml"));
        Scene ParentSudokuTScene = new Scene(ParentSudokuT);
        Stage Next_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        Next_Stage.setScene(ParentSudokuTScene);
        Next_Stage.show();
        

        
    }   
    
    @FXML
    private void BotonSalir(ActionEvent event) {
        Platform.exit();
    }

   @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Image imagen = new Image("file:sudoku.jpg");
        iv1.setImage(imagen);
        iv1.setFitWidth(150);
        iv1.setFitHeight(150);
        iv1.setPreserveRatio(true);
        iv1.setSmooth(true);
        iv1.setCache(true);
        
    }    
    
}
