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
    
    public void showClientScene(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("logIn.fxml");
    }
    
    public void showManagerScene(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("logIn.fxml");
    }
	
}
