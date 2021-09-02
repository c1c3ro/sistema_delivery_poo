package application;
import fachada.Delivery;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import negocios.NegociosRestaurante;
import negocios.NegociosGerente;
import negocios.NegociosCliente;

public class InitialScene {
	
	private FachadaHolder holder = FachadaHolder.getInstance();
	
    @FXML
    private Button client_option;
    
    @FXML
    private Button manager_option;
    
    @FXML
    private Label messageLabel;
    
    @FXML
    void initialize() {
    	this.holder.fachada = new Delivery();
    }
    
    @FXML
    void loadData(ActionEvent event) {
    	try {
    		holder.fachada.getRestaurantes().saveData();
    		holder.fachada.getClientes().saveData();
    		holder.fachada.getGerentes().saveData();
    		messageLabel.setText("Sucesso!");
    		emptyLabel();
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    		messageLabel.setText("Ocorreu algum erro!");
    		emptyLabel();
    	}
    }

    @FXML
    void saveData(ActionEvent event) {
    	try {
    		holder.fachada.setRestaurantes(holder.fachada.getRestaurantes().readData()); ;
    		holder.fachada.setClientes(holder.fachada.getClientes().readData());
    		holder.fachada.setGerentes(holder.fachada.getGerentes().readData());
    		messageLabel.setText("Sucesso!");
    		emptyLabel();
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    		messageLabel.setText("Ocorreu algum erro!");
    		emptyLabel();
    	}
    }
    
    @FXML
    public void showClientScene(ActionEvent event) {
    	Main m = new Main();
        m.changeScene("logIn.fxml");
    }
    
    @FXML
    public void showManagerScene(ActionEvent event) {
        Main m = new Main();
        m.changeScene("logInManager.fxml");
    }
	
    @FXML
    void emptyLabel() {
    	PauseTransition pause = new PauseTransition(Duration.millis(1000));
        pause.setOnFinished(
            e -> {
            	messageLabel.setText(" ");
            });
        pause.play();
    }
    
}
