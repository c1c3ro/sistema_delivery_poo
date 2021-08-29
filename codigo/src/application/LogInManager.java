package application;

import Exceptions.UsuarioNaoEncontradoException;
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


    public void userSignIn(ActionEvent event) {
        Main m = new Main();
        m.changeScene("SignInManager.fxml");
    }
    
    public void backInitialScene(ActionEvent event) {
        Main m = new Main();
        m.changeScene("initialScene.fxml");
    }

    public void userLogIn(ActionEvent event) {
        if (!checkLogin()) {
        	logInIncorreto.setText("Usuário não cadastrado!");
        }
        
        if(logInCPF.getText().isEmpty() || logInPassword.getText().isEmpty()) {
            logInIncorreto.setText("Digite suas informações!");
        }
    }

    private boolean checkLogin() {
        Main m = new Main();
        FachadaHolder holder = FachadaHolder.getInstance();
    	try {
    		
    		if (holder.fachada == null) {
    			return false;
    		}
    		
    		var gerenteLogado = holder.fachada.matchLoginSenhaGerente(logInCPF.getText().toString(), logInPassword.getText().toString());
	    	if (gerenteLogado != null) {
	    		holder.setGerenteLogado(gerenteLogado);
	    		m.changeScene("managerOptions.fxml");
	    	} else {
	    		logInIncorreto.setText("Senha incorreta!");
	    	}
    	} catch (UsuarioNaoEncontradoException e) {
    		logInIncorreto.setText("Usuário não cadastrado!");
    	}
        
        return true;
    }
}
