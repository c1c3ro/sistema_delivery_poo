package application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import javafx.scene.Scene;
import javafx.scene.Node;

import java.io.IOException;

import fachada.Delivery;

public class LogIn {
	
	private Delivery fachada = null;

    @FXML
    private Button logInButton;
    @FXML
    private Button backButton;
    @FXML
    private Button signInButton;
    @FXML
    private Label logInIncorreto;
    @FXML
    private TextField logInCPF;
    @FXML
    private PasswordField logInPassword;


    public void userSignIn(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("SignInClient.fxml");
    }
    
    public void backInitialScene(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("initialScene.fxml");
    }

    public void userLogIn(ActionEvent event) throws IOException {
    	
    	Node node = (Node) event.getSource();
   	 	Stage stage = (Stage) node.getScene().getWindow();
   	 	this.fachada = (Delivery) stage.getUserData();
    	
        checkLogin();
    }

    private void checkLogin() throws IOException {
    	
    	
    	
        Main m = new Main();
        if(logInCPF.getText().toString().equals("javacoding") && logInPassword.getText().toString().equals("123")) {
            logInIncorreto.setText("Sucesso!");

            m.changeScene("clientOptions.fxml");
        }

        else if(logInCPF.getText().isEmpty() && logInPassword.getText().isEmpty()) {
            logInIncorreto.setText("Digite suas informações!");
        }


        else {
        	logInIncorreto.setText("CPF ou senha incorretos!");
        }
    }
	
}
