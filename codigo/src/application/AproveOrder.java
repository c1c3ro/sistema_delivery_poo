package application;

import java.util.ArrayList;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import negocios.Cliente;
import negocios.Item;
import negocios.Restaurante;
import negocios.Sacola;

public class AproveOrder {
	
	private FachadaHolder holder = FachadaHolder.getInstance();

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
    private Label messageLabel;

    @FXML
    private Label totalLabel;

    @FXML
    private Label walletLabel;

    @FXML
    private ComboBox<Sacola> comboP;
    
    @FXML
    void initialize() {
    	
    	try {
    		
    		Set<Sacola> sacolas = holder.fachada.getPedidosParaAprovacao(null).keySet();
    		ObservableList<Sacola> sacolasObs = FXCollections.observableArrayList(sacolas);
    		comboP.setItems(sacolasObs);
    		
    	} catch (Exception e) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Aviso");
			alert.setHeaderText("Tivemos um problema, entre e saia de novo!");
			alert.show();
    	}
    }

    @FXML
    void aproveOrder(ActionEvent event) {
    	try {
    		
    		Sacola sacola = comboP.getSelectionModel().getSelectedItem();
    		
    		//Cliente cliente = holder.fachada.
    		
    		holder.fachada.aprovarPedido(holder.getGerenteLogado(), sacola);
    		
    		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Aviso");
			alert.setHeaderText("Pedido aprovado! Adicionamos o valor a sua conta.");
			alert.show();
    		String receita = String.format("Ganhos do restaurante: R$ %.2f", holder.fachada.ganhos(holder.getGerenteLogado().getRestaurante().getCnpj()));
    		walletLabel.setText(receita);
    		
    	} catch (Exception e) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Aviso");
			alert.setHeaderText("Tivemos um problema, saia e entre de novo!");
			alert.show();
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
    	}
    }

    @FXML
    void backToManagerOptions(ActionEvent event) {
    	Main m = new Main();
        m.changeScene("managerOptions.fxml");
    }

    @FXML
    void seeOrder(ActionEvent event) {
    	try {
    		
    		Sacola sacola = comboP.getSelectionModel().getSelectedItem();
    		
    		if (sacola == null) {
        		messageLabel.setText("Selecione uma sacola!");
        		Color color = Color.RED;
        		messageLabel.setTextFill(color);
    		} else {
	    		String total = String.format("Total: R$ %.2f", holder.fachada.getTotalSacola(sacola));
	    		String receita = String.format("Ganhos do restaurante: R$ %.2f", holder.fachada.ganhos(holder.getGerenteLogado().getRestaurante().getCnpj()));
	    		
	    		totalLabel.setText(total);
	    		walletLabel.setText(receita);
	    		
	    		ArrayList<Item> itensArray = holder.fachada.getItensNaSacola(sacola);
	    		
	    		ObservableList<Item> itensObs = FXCollections.observableArrayList(itensArray);
	
	    		nameC.setCellValueFactory(new PropertyValueFactory<Item, String>("nome"));
	    		valueC.setCellValueFactory(new PropertyValueFactory<Item, Double>("valor"));
	    		categoryC.setCellValueFactory(new PropertyValueFactory<Item, String>("categoria"));
	    		descriptionC.setCellValueFactory(new PropertyValueFactory<Item, String>("descricao"));
	    		idC.setCellValueFactory(new PropertyValueFactory<Item, Integer>("ID"));
	    		
	    		menuTable.setItems(itensObs);
    		}
    		    	
    	} catch (Exception e) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Aviso");
			alert.setHeaderText("Tivemos um problema, saia e entre de novo!");
			alert.show();
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
    	}
    	
    }

}
