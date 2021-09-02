package application;
import java.io.IOException;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

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
    }

    @FXML
    void changeName(ActionEvent event) {
    	this.dataField = "nome";
    	messageLabel.setText("Digite um nome novo: ");
    }

    @FXML
    void changePassword(ActionEvent event) {
    	this.dataField = "senha";
    	messageLabel.setText("Digite sua senha nova: ");
    }

    @FXML
    void updateAction(ActionEvent event) {
    	FachadaHolder holder = FachadaHolder.getInstance();
    	
    	try {
	    	if (this.dataField == null){
	        	messageLabel.setText("Selecione uma das opções acima!");
	        } else if(newData.getText().isEmpty()) {
	            messageLabel.setText("Digite a informação a ser atualizada!");
	        } else if (this.dataField.equals("endereço")) {
	        	holder.fachada.atualizarCliente(holder.getClienteLogado().getCPF(), 3, newData.getText().toString());
	        } else if (this.dataField.equals("nome")) {
	        	holder.fachada.atualizarCliente(holder.getClienteLogado().getCPF(), 1, newData.getText().toString());
	        } else if ( this.dataField.equals("senha")) {
	        	holder.fachada.atualizarCliente(holder.getClienteLogado().getCPF(), 2, newData.getText().toString());
	        }

	    	messageLabel.setText("Atualizado!");
	    	this.dataField = null;
	    	newData.setText("");
	    	PauseTransition pause = new PauseTransition(Duration.millis(1000));
	        pause.setOnFinished(
	            e -> {
	            	messageLabel.setText(" ");
	            });
	        pause.play();
    	} catch (Exception e) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setTitle("Aviso");
			alert.setHeaderText("Tivemos um problema, saia e entre de novo!");
			alert.show();
    	}
    	
    }
}
