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

public class UpdateDataManager {
	private String dataField = null;
	
	@FXML
    private Button nameButton;

    @FXML
    private Button passwordButton;

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
        m.changeScene("managerOptions.fxml");
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
	        	messageLabel.setText("Selecione uma das op��es acima!");
	        } else if(newData.getText().isEmpty()) {
	            messageLabel.setText("Digite a informa��o a ser atualizada!");
	        } else if (this.dataField.equals("nome")) {
	        	holder.fachada.atualizarGerente(holder.getGerenteLogado().getCPF(), 1, newData.getText().toString());
	        } else if ( this.dataField.equals("senha")) {
	        	holder.fachada.atualizarGerente(holder.getGerenteLogado().getCPF(), 2, newData.getText().toString());
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
