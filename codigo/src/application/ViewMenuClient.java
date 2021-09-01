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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewMenuClient {

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
    
    @FXML
    private TextField IDField;

    @FXML
    private TextField qtdField;

    @FXML
    private Label messageLabel;
    
    private Hashtable<Integer, Item> itensCardapio = new Hashtable<Integer, Item>();
    
    private ArrayList<Item> itensArray;
    
    private ObservableList<Item> itensObs;
    
    @FXML
    public void initialize() {
    	FachadaHolder holder = FachadaHolder.getInstance();
    	
    	try {

    		itensCardapio = holder.fachada.getItensDoCardapio(holder.getRestauranteSelecionado());
    		
    		itensArray = new ArrayList<Item>();
    		itensCardapio.forEach((k, e) -> itensArray.add(e));
    		
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
    void addToBag(ActionEvent event) {
    	try {
    		FachadaHolder holder = FachadaHolder.getInstance();
    		int id = Integer.parseInt(IDField.getText().toString());
    		
    		
    		
    		//holder.fachada.adicionarItemNaSacola(holder.getClienteLogado().getCPF(), holder., null);
    		
    		IDField.setText("");
    		messageLabel.setText("Removido!");
    		
    	} catch (NumberFormatException e) {
    		if (IDField.getText().toString().isEmpty()) {
    			messageLabel.setText("Digite um número!");
    		} else {
    			messageLabel.setText("Digite um ID válido!");
    		}
    	} catch (UsuarioNaoEncontradoException e) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setTitle("Aviso");
			alert.setHeaderText("Tivemos um problema, entre e saia de novo!");
			alert.show();
    	} catch (NullPointerException e) {
    		messageLabel.setText("Produto não encontrado!");
    	}
    }

    @FXML
    void seeBag(ActionEvent event) {
    	return;
    }
    
    @FXML
    void backToClientOptions(ActionEvent event) {
    	Main m = new Main();
        m.changeScene("clientOptions.fxml");
    }

}
