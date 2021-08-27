package application;
import fachada.Delivery;
import java.io.IOException;

import Exceptions.ClienteJaExisteException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import fachada.Delivery;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.Parent;

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
		    }
		  }
 		catch (ClienteJaExisteException e) {
 			messageLabel.setText("Cliente já existe!");
		  }
 		
    	messageLabel.setText("Cadastrado!");
    }

}
