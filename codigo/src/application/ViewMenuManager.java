package application;
import negocios.Item;

import Exceptions.*;

import java.util.ArrayList;
import java.util.Hashtable;

import javafx.beans.binding.DoubleBinding;
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
    
    private Hashtable<Integer, Item> itensCardapio = new Hashtable<Integer, Item>();
    
    private ArrayList<Item> itensArray;
    
    private ObservableList<Item> itensObs;
    
    @FXML
    public void initialize() {
    	FachadaHolder holder = FachadaHolder.getInstance();
    	
    	try {

    		itensCardapio = holder.fachada.getItensDoCardapio(holder.getGerenteLogado().getRestaurante());
    		
    		//System.out.println("itensCardapio size: "+itensCardapio.size());
    		
    		itensArray = new ArrayList<Item>();
    		itensCardapio.forEach((k, e) -> itensArray.add(e));
    		
    		//System.out.println("itensArray size: "+itensArray.size());
    		
    		itensObs = FXCollections.observableArrayList(itensArray);
    		
    		//DoubleBinding usedWidth = idC.widthProperty().add(nameC.widthProperty()).add(valueC.widthProperty()).add(categoryC.widthProperty());

    		//descriptionC.prefWidthProperty().bind(menuTable.widthProperty().subtract(usedWidth));

    		
    		nameC.setCellValueFactory(new PropertyValueFactory<Item, String>("nome"));
    		valueC.setCellValueFactory(new PropertyValueFactory<Item, Double>("valor"));
    		categoryC.setCellValueFactory(new PropertyValueFactory<Item, String>("categoria"));
    		descriptionC.setCellValueFactory(new PropertyValueFactory<Item, String>("descricao"));
    		idC.setCellValueFactory(new PropertyValueFactory<Item, Integer>("ID"));
    		
    		menuTable.setItems(itensObs);
    		    	
    	} catch (Exception e) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Aviso");
			alert.setHeaderText("Tivemos um problema, entre e saia de novo!");
			alert.show();
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			//e.printStackTrace();
    	}
    }
    
    @FXML
    void backToManagerOptions(ActionEvent event) {
    	Main m = new Main();
        m.changeScene("managerOptions.fxml");
    }

}
