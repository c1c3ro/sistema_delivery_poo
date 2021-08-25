package application;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class Wallet {
	
	private double moneyStored = 0.0;
	
	@FXML
    private Button fiveButton;

    @FXML
    private Button fiftyButton;

    @FXML
    private Button tenButton;

    @FXML
    private Button twentyButton;

    @FXML
    private Button hundredButton;

    @FXML
    private Button backButton;
    
    @FXML
    private Label money;
    
    @FXML
    private Label addedLabel;

    @FXML
    public void initialize() {
        money.setText("R$ " + this.moneyStored);
    }
    
    private void updateLabels() {
    	money.setText("R$ " + this.moneyStored);
    	addedLabel.setText("Adicionado!");
    	PauseTransition pause = new PauseTransition(Duration.millis(1000));
        pause.setOnFinished(
            e -> {
            	addedLabel.setText(" ");
            });
        pause.play();
    }
    
    @FXML
    void add100toWallet(ActionEvent event) throws IOException {
    	this.moneyStored += 100;
    	updateLabels();
    }

    @FXML
    void add10toWallet(ActionEvent event) throws IOException {
    	this.moneyStored += 10;
    	updateLabels();
    }

    @FXML
    void add20toWallet(ActionEvent event) throws IOException {
    	this.moneyStored += 20;
    	updateLabels();
    }

    @FXML
    void add50toWallet(ActionEvent event) throws IOException {
    	this.moneyStored += 50;
    	updateLabels();
    }

    @FXML
    void add5toWallet(ActionEvent event) throws IOException {
    	this.moneyStored += 5;
    	updateLabels();
    }

    @FXML
    void backClientOptions(ActionEvent event) throws IOException{
    	Main m = new Main();
        m.changeScene("clientOptions.fxml");

    }
}
