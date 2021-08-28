package application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;

public class InitialScene {
	
    @FXML
    private Button client_option;
    
    @FXML
    private Button manager_option;
    
    @FXML
    public void showClientScene(ActionEvent event) {
    	Main m = new Main();
        m.changeScene("logIn.fxml");
    }
    
    @FXML
    public void showManagerScene(ActionEvent event) {
        Main m = new Main();
        m.changeScene("logInManager.fxml");
    }
	
}
