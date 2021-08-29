package application;
import negocios.Item;

import Exceptions.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewMenuManager {

    @FXML
    private TableView<Item> menuTable;

    @FXML
    private TableColumn<Item, String> nameC;

    @FXML
    private TableColumn<Item, Double> valueC;

    @FXML
    private TableColumn<Item, String> categoryC;

    @FXML
    private TableColumn<Item, String> descriptionC;

    @FXML
    private TableColumn<Item, Integer> idC;
    
    @FXML
    private Button backButton;
    
    private Collection<ArrayList<Item>> itensCardapio = new ArrayList<>();
    
    private ArrayList<Item> itensArray;
    
    private ObservableList<Item> itensObs;
    
    @FXML
    public void initialize() {
    	FachadaHolder holder = FachadaHolder.getInstance();
    	
    	try {
    	
    		itensCardapio = holder.fachada.getItensDoCardapio(holder.getGerenteLogado().getRestaurante().getCnpj()).values();
    		
    		itensCardapio.forEach(e -> itensArray.addAll(e));
    		
    		itensObs = FXCollections.observableArrayList(itensArray);
    		
    		nameC.setCellValueFactory(new PropertyValueFactory<Item, String>("Nome"));
    		valueC.setCellValueFactory(new PropertyValueFactory<Item, Double>("Valor"));
    		categoryC.setCellValueFactory(new PropertyValueFactory<Item, String>("Categoria"));
    		descriptionC.setCellValueFactory(new PropertyValueFactory<Item, String>("Descrição"));
    		idC.setCellValueFactory(new PropertyValueFactory<Item, Integer>("ID"));
    		
    		menuTable.setItems(itensObs);
    	
    	} catch (UsuarioNaoEncontradoException e) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Aviso");
			alert.setHeaderText("Tivemos um problema, entre e saia de novo!");
			alert.show();
    	}
    }
    
    @FXML
    void backToManagerOptions(ActionEvent event) {
    	Main m = new Main();
        m.changeScene("managerOptions.fxml");
    }

}
