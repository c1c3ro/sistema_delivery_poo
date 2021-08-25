package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ManagerOptions {
	
    @FXML
    private Button restaurantButton;

    @FXML
    private Button updateButton;
    
    @FXML
    private Button exitButton;

    @FXML
    void seeRestaurants(ActionEvent event) throws IOException {
    	System.out.println("hey, do nothing");
    }


    @FXML
    void updateData(ActionEvent event) throws IOException {
    	Main m = new Main();
        m.changeScene("updateDataManager.fxml");
    }
    
    @FXML
    void exitToLogIn(ActionEvent event) throws IOException{
    	Main m = new Main();
        m.changeScene("logInManager.fxml");
    }
}
