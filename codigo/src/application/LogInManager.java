package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LogInManager {
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
        m.changeScene("SignInManager.fxml");
    }
    
    public void backInitialScene(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("initialScene.fxml");
    }

    public void userLogIn(ActionEvent event) throws IOException {
        checkLogin();
    }

    private void checkLogin() throws IOException {
        Main m = new Main();
        if(logInCPF.getText().toString().equals("javacoding") && logInPassword.getText().toString().equals("123")) {
            logInIncorreto.setText("Sucesso!");

            m.changeScene("managerOptions.fxml");
        }

        else if(logInCPF.getText().isEmpty() && logInPassword.getText().isEmpty()) {
            logInIncorreto.setText("Digite suas informações!");
        }


        else {
        	logInIncorreto.setText("CPF ou senha incorretos!");
        }
    }
}
