package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ManagerOptions {
	
    @FXML
    private Button orderButton;

    @FXML
    private Button updateButton;
    
    @FXML
    private Button exitButton;
    
    @FXML
    private Button menuButton;
    
    @FXML
    void updateMenu(ActionEvent event) {
    	Main m = new Main();
        m.changeScene("updateMenu.fxml");
    }

    @FXML
    void approveOrder(ActionEvent event) {
    	System.out.println("hey, do nothing");
    }


    @FXML
    void updateData(ActionEvent event) {
    	Main m = new Main();
        m.changeScene("updateDataManager.fxml");
    }
    
    @FXML
    void exitToLogIn(ActionEvent event) {
    	Main m = new Main();
        m.changeScene("logInManager.fxml");
    }
}
