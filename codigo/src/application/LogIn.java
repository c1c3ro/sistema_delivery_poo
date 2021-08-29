package application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
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
    		var clienteLogado = holder.fachada.matchLoginSenhaCliente(logInCPF.getText().toString(), logInPassword.getText().toString());
	    	if (holder.fachada != null && clienteLogado != null) {
	    		holder.setClienteLogado(clienteLogado);
	    		m.changeScene("clientOptions.fxml");
	    	} else {
	    		logInIncorreto.setText("Senha incorreta!");
	    	}
    	} catch (UsuarioNaoEncontradoException e) {
    		logInIncorreto.setText("Usuário não cadastrado!");
    	}

        if(logInCPF.getText().isEmpty() || logInPassword.getText().isEmpty()) {
            logInIncorreto.setText("Digite suas informações!");
        }

    }
	
}
