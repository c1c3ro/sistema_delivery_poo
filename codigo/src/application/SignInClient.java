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
    	
    	//Node node = (Node) event.getSource();
   	 	//Stage stage = (Stage) node.getScene().getWindow();
   	 	//stage.close();	
 		try {
		    //Parent root = FXMLLoader.load(getClass().getResource("/application/signInClient.fxml"));
		    FachadaHolder holder = FachadaHolder.getInstance();
		    
		    if (holder.fachada == null) {
		    	holder.fachada = new Delivery();
		    }
		    
		    holder.fachada.cadastrarCliente(nameField.getText().toString(), cpfField.getText().toString(), passwordField.getText().toString(),
		    		adressField.getText().toString());
		    
		    //Scene scene = new Scene(root);
		    //stage.setScene(scene);
		    //stage.show();
		    
		  } //catch (IOException e) {
		    //System.err.println(String.format("Error: %s", e.getMessage()));
		  //}
 		catch (ClienteJaExisteException e) {
	 			System.out.println(e.getMessage());
		  }
 		
    	messageLabel.setText("Cadastrado!");
    }

}
