package application;

import java.util.ArrayList;
import java.util.Hashtable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import negocios.Item;
import negocios.Sacola;

public class clientBag {

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
    private TextField IDField;

    @FXML
    private Label messageLabel;

    @FXML
    private Label totalLabel;

    @FXML
    private Label walletLabel;
    
    private Hashtable<Integer, Item> itensSacola = new Hashtable<Integer, Item>();
    
    private ArrayList<Item> itensArray;
    
    private ObservableList<Item> itensObs;

    
    @FXML
    public void initialize() {
    	FachadaHolder holder = FachadaHolder.getInstance();
    	try {

    		
    		Sacola sacola = holder.fachada.getSacolaAtual(holder.getClienteLogado().getCPF());
    		
    		//itensSacola = holder.fachada.getItensNaSacola(sacola);
    		
    		itensArray = new ArrayList<Item>();
    		itensSacola.forEach((k, e) -> itensArray.add(e));
    		
    		itensObs = FXCollections.observableArrayList(itensArray);

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
    	}
    }

    
    @FXML
    void backToMenu(ActionEvent event) {

    }

    @FXML
    void makeOrder(ActionEvent event) {

    }

    @FXML
    void removeFromBag(ActionEvent event) {

    }

}
