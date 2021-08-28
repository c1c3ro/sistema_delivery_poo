package application;
import java.io.IOException;

import Exceptions.ClienteJaExisteException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import fachada.Delivery;
import negocios.NegociosRestaurante;

public class SignInManager {

    @FXML
    private Button signInButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField nameField;

    @FXML
    private TextField restaurantField;
    
    @FXML
    private TextField cnpjField;

    @FXML
    private TextField cpfField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    void backLogIn(ActionEvent event) {
    	Main m = new Main();
        m.changeScene("logInManager.fxml");
    }

    @FXML
    void checkSignIn(ActionEvent event) {
    	try {
		    FachadaHolder holder = FachadaHolder.getInstance();
		    
		    if (holder.fachada == null) {
		    	holder.fachada = new Delivery();
		    }
		    
		    if (holder.fachada.getRestaurantes() == null) {
		    	holder.fachada.setRestaurantes(new NegociosRestaurante());
		    }
		    
		    if (nameField.getText().isEmpty() || cpfField.getText().isEmpty() || passwordField.getText().isEmpty() ||
    		cnpjField.getText().isEmpty() || restaurantField.getText().isEmpty()) {
		    	messageLabel.setText("Digite todas as informações!");
		    } else {		    
			    holder.fachada.cadastrarGerente(nameField.getText().toString(), cpfField.getText().toString(), passwordField.getText().toString(),
			    		cnpjField.getText().toString(), restaurantField.getText().toString(), holder.fachada.getRestaurantes());
			    
			    messageLabel.setText("Cadastrado!");
			    nameField.setText("");
			    cpfField.setText("");
			    passwordField.setText("");
			    cnpjField.setText("");
			    restaurantField.setText("");
		    }
		    
		  } catch (ClienteJaExisteException e) {
 			messageLabel.setText("Gerente ou restaurante já cadastrado!");
		  }
 		
    }

}
