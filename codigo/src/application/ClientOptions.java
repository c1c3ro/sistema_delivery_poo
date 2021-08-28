package application;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ClientOptions {
	
    @FXML
    private Button restaurantButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button walletButton;
    
    @FXML
    private Button exitButton;

    @FXML
    void seeRestaurants(ActionEvent event) {
    	System.out.println("hey, do nothing");
    }

    @FXML
    void seeWallet(ActionEvent event) {
    	Main m = new Main();
        m.changeScene("wallet.fxml");
    }

    @FXML
    void updateData(ActionEvent event) {
    	Main m = new Main();
        m.changeScene("updateDataClient.fxml");
    }
    
    @FXML
    void exitToLogIn(ActionEvent event) {
    	Main m = new Main();
        m.changeScene("logIn.fxml");
    }
}
