package application;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import negocios.Restaurante;

public class SeeOpenRestaurants {

    @FXML
    private ComboBox<Restaurante> comboR;

    @FXML
    private Button seeMenuButton;

    @FXML
    private Button backButton;
    
    @FXML
    private Label messageLabel;
    
    @FXML
    private Label mainLabel;
    
    private ArrayList<Restaurante> restaurantes;

    
    @FXML
    public void initialize() {
    	FachadaHolder holder = FachadaHolder.getInstance();
    	restaurantes = holder.fachada.restaurantesAbertos();
    	
    	if (restaurantes == null) {
    		mainLabel.setText("Não há restaurantes disponíveis");
    		Color color = Color.RED;
    		mainLabel.setTextFill(color);
    	} else {
    		ObservableList<Restaurante> restaurantesObs = FXCollections.observableArrayList(restaurantes);
    		System.out.println(restaurantes);
    		comboR.setItems(restaurantesObs);
    	}
    }
    
    @FXML
    void backToClientOptions(ActionEvent event) {
    	Main m = new Main();
        m.changeScene("clientOptions.fxml");
    }

    @FXML
    void seeMenu(ActionEvent event) {
    	FachadaHolder holder = FachadaHolder.getInstance();
    	Restaurante restauranteSelecionado = comboR.getSelectionModel().getSelectedItem();
    	if (restauranteSelecionado == null) {
    		mainLabel.setText("Selecione um restaurante!");
    		Color color = Color.RED;
    		mainLabel.setTextFill(color);
    	} else {
    		holder.setRestauranteSelecionado(restauranteSelecionado);
    		Main m = new Main();
            m.changeScene("viewMenuClient.fxml");
    	}
    }

}
