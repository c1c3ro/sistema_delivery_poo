package application;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import negocios.Item;
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
    void aproveOrder(ActionEvent event) {

    }

    @FXML
    void backToManagerOptions(ActionEvent event) {

    }

    @FXML
    void seeOrder(ActionEvent event) {
    	try {
    		//Sacola sacola = holder.fachada.getSacolaAtual(holder.getClienteLogado().getCPF());
    		
    		String total = String.format("Total: R$ %.2f", holder.fachada.getTotalSacola(sacola));
    		String receita = String.format("Ganhos do restaurante: R$ %.2f", holder.fachada.ganhos(holder.getGerenteLogado().getRestaurante().getCnpj()));
    		
    		totalLabel.setText(total);
    		walletLabel.setText(receita);
    		
    		itensArray = holder.fachada.getItensNaSacola(sacola);
    		
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
			alert.setHeaderText("Tivemos um problema, saia e entre de novo!");
			alert.show();
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
    	}
    	
    }

}
