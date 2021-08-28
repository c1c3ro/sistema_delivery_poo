package application;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UpdateDataClient {
	
	private String dataField = null;
	
	@FXML
    private Button nameButton;

    @FXML
    private Button passwordButton;

    @FXML
    private Button adressButton;

    @FXML
    private Button updateButton;
    
    @FXML
    private TextField newData;
    
    @FXML
    private Label messageLabel;
    
    @FXML
    private Button backButton;

    @FXML
    void backClientOptions(ActionEvent event) {
    	Main m = new Main();
        m.changeScene("clientOptions.fxml");
    }

    @FXML
    void changeAdress(ActionEvent event) {
    	this.dataField = "endereço";
    	messageLabel.setText("Digite seu novo endereço: ");
    	System.out.println("hey, do nothing");
    }

    @FXML
    void changeName(ActionEvent event) {
    	this.dataField = "nome";
    	messageLabel.setText("Digite um nome novo: ");
    	System.out.println("hey, do nothing");
    }

    @FXML
    void changePassword(ActionEvent event) {
    	this.dataField = "senha";
    	messageLabel.setText("Digite sua senha nova: ");
    	System.out.println("hey, do nothing");
    }

    @FXML
    void updateAction(ActionEvent event) {
    	System.out.println("hey, do nothing");
    	
    	if (this.dataField == null){
        	messageLabel.setText("Selecione uma das opções acima!");
        } else if(newData.getText().isEmpty()) {
            messageLabel.setText("Digite a informação a ser atualizada!");
        } else if (this.dataField.equals("endereço") || this.dataField.equals("nome") || this.dataField.equals("senha")){
        	messageLabel.setText("Atualizado!");
        	this.dataField = null;
        }
        
    }
}
