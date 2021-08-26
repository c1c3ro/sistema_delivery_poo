package application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;

import fachada.Delivery;

public class InitialScene {
	
	private Delivery fachada = null;
	
    @FXML
    private Button client_option;
    
    @FXML
    private Button manager_option;
    
    @FXML
    public void showClientScene(ActionEvent event) {
    	
    	if (this.fachada == null) {
    		fachada = new Delivery();
    		Node node = (Node) event.getSource();
    		Stage stage = (Stage) node.getScene().getWindow();
    		stage.close();
    		try {
    		    Parent root = FXMLLoader.load(getClass().getResource("/application/initialScene.fxml"));
    		    stage.setUserData(fachada);
    		    Scene scene = new Scene(root);
    		    stage.setScene(scene);
    		    stage.show();
    		    Main m = new Main();
    	        m.changeScene("logIn.fxml");
    		  } catch (IOException e) {
    		    System.err.println(String.format("Error: %s", e.getMessage()));
    		  }
    	}
    	
        
    }
    
    @FXML
    public void showManagerScene(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("logInManager.fxml");
    }
	
}
