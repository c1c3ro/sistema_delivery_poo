package application;
import fachada.Delivery;

import Exceptions.ClienteJaExisteException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class SignInClient {

    @FXML
    private Button signInButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField nameField;

    @FXML
    private TextField adressField;

    @FXML
    private TextField cpfField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    void backLogIn(ActionEvent event) {
    	Main m = new Main();
        m.changeScene("logIn.fxml");
    }

    @FXML
    void checkSignIn(ActionEvent event) {
    		
 		try {
		    FachadaHolder holder = FachadaHolder.getInstance();
		    
		    if (holder.fachada == null) {
		    	holder.fachada = new Delivery();
		    }
		    if (nameField.getText().isEmpty() || cpfField.getText().isEmpty() || passwordField.getText().isEmpty() || adressField.getText().isEmpty()) {
		    	messageLabel.setText("Digite todas as informações!");
		    } else {
		    	holder.fachada.cadastrarCliente(nameField.getText().toString(), cpfField.getText().toString(), passwordField.getText().toString(),
		    			adressField.getText().toString());
		    	
		    	 nameField.setText("");
				 cpfField.setText("");
				 adressField.setText("");
				 passwordField.setText("");
				 
				 Alert alert = new Alert(Alert.AlertType.INFORMATION);
				 alert.setTitle("Aviso");
				 alert.setHeaderText("Cliente cadastrado com sucesso!");
				 alert.show();
		    }
		  }
 		catch (ClienteJaExisteException e) {
 			messageLabel.setText("Cliente já existe!");
		  }
 		
    	
    }

}
