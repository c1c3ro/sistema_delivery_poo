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

import Exceptions.*;
import fachada.Delivery;

public class LogIn {

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


    public void userSignIn(ActionEvent event)  {
        Main m = new Main();
        m.changeScene("SignInClient.fxml");
    }
    
    public void backInitialScene(ActionEvent event) {
        Main m = new Main();
        m.changeScene("initialScene.fxml");
    }

    public void userLogIn(ActionEvent event)  {
        checkLogin();
    }

    private void checkLogin()  {
    	Main m = new Main();
    	FachadaHolder holder = FachadaHolder.getInstance();

    	try {
	    	if (holder.fachada != null && holder.fachada.matchLoginSenha(logInCPF.getText().toString(), logInPassword.getText().toString())) {
	    		m.changeScene("clientOptions.fxml");
	    	} else {
	    		System.out.println("errou a senha mlr");
	    	}
    	} catch (UsuarioNaoEncontradoException e) {
    		System.out.println("não encontrei mona");
    	}
        
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
