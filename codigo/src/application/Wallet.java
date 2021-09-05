package application;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class Wallet {
	
	private FachadaHolder holder = FachadaHolder.getInstance();
	
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
    	
        money.setText("R$ " + this.holder.getClienteLogado().getCarteira());
        
    }
    
    private void updateLabels() {
    	String dinheiro = String.format("R$ %.2f", this.holder.getClienteLogado().getCarteira());
    	money.setText(dinheiro);
    	addedLabel.setText("Adicionado!");
    	PauseTransition pause = new PauseTransition(Duration.millis(1000));
        pause.setOnFinished(
            e -> {
            	addedLabel.setText(" ");
            });
        pause.play();
    }
    
    @FXML
    void add100toWallet(ActionEvent event) {
    	this.holder.getClienteLogado().adicionarDinheiro(100);
    	updateLabels();
    }

    @FXML
    void add10toWallet(ActionEvent event) {
    	this.holder.getClienteLogado().adicionarDinheiro(10);
    	updateLabels();
    }

    @FXML
    void add20toWallet(ActionEvent event) {
    	this.holder.getClienteLogado().adicionarDinheiro(20);
    	updateLabels();
    }

    @FXML
    void add50toWallet(ActionEvent event){
    	this.holder.getClienteLogado().adicionarDinheiro(50);
    	updateLabels();
    }

    @FXML
    void add5toWallet(ActionEvent event) {
    	this.holder.getClienteLogado().adicionarDinheiro(5);
    	updateLabels();
    }

    @FXML
    void backClientOptions(ActionEvent event){
    	Main m = new Main();
        m.changeScene("clientOptions.fxml");

    }
}
