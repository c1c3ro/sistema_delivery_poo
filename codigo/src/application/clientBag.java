package application;

import java.util.ArrayList;
import java.util.Set;
import javafx.animation.PauseTransition;
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
import javafx.util.Duration;
import negocios.Item;
import negocios.Restaurante;
import negocios.Sacola;
import Exceptions.*;

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
    
    private ArrayList<Item> itensArray;
    
    private ObservableList<Item> itensObs;

    
    @FXML
    public void initialize() {
    	FachadaHolder holder = FachadaHolder.getInstance();
    	try {
    		Sacola sacola = holder.fachada.getSacolaAtual(holder.getClienteLogado().getCPF());
    		
    		String total = String.format("Total: R$ %.2f", holder.fachada.getTotalSacola(sacola));
    		String wallet = String.format("Carteira: R$ %.2f", holder.getClienteLogado().getCarteira());
    		
    		totalLabel.setText(total);
    		walletLabel.setText(wallet);
    		
    		itensArray = holder.fachada.getItensNaSacola(sacola);
    		
    		if (itensArray != null) {
    			itensObs = FXCollections.observableArrayList(itensArray);
        		nameC.setCellValueFactory(new PropertyValueFactory<Item, String>("nome"));
        		valueC.setCellValueFactory(new PropertyValueFactory<Item, Double>("valor"));
        		categoryC.setCellValueFactory(new PropertyValueFactory<Item, String>("categoria"));
        		descriptionC.setCellValueFactory(new PropertyValueFactory<Item, String>("descricao"));
        		idC.setCellValueFactory(new PropertyValueFactory<Item, Integer>("ID"));
        		
        		menuTable.setItems(itensObs);
    		} else {
    			Alert alert = new Alert(Alert.AlertType.ERROR);
    			alert.setTitle("Aviso");
    			alert.setHeaderText("Sacola vazia, adicione produtos!");
    			alert.show();
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

    
    @FXML
    void backToMenu(ActionEvent event) {
    	Main m = new Main();
        m.changeScene("viewMenuClient.fxml");
    }

    @FXML
    void makeOrder(ActionEvent event) {
    	FachadaHolder holder = FachadaHolder.getInstance();
    	try {
    		holder.fachada.realizarCompra(holder.getClienteLogado().getCPF());
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
    		alert.setTitle("Sucesso");
			alert.setHeaderText("Enviamos seu pedido para o(s) gerente(s), quando aprovado, o pedido chegará no seu endereço!");
			alert.show();
			
			menuTable.getItems().clear();
			//initialize();

    	} catch (SemDinheiroException e) {
    		messageLabel.setText("Dinheiro insuficiente!");
    	} catch (UsuarioNaoEncontradoException e) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setTitle("Aviso");
			alert.setHeaderText("Tivemos um problema, saia e entre de novo!");
			alert.show();
    	}
    }

    @FXML
    void removeFromBag(ActionEvent event) {
    	try {
    		FachadaHolder holder = FachadaHolder.getInstance();
    		int id = Integer.parseInt(IDField.getText().toString());
    		
    		Set<Restaurante> restaurantes = holder.fachada.getItensNaSacolaPorRestaurante(holder.fachada.getSacolaAtual(holder.getClienteLogado().getCPF())).keySet();
    		
    		Item item = null;
    		for (Restaurante restaurante : restaurantes) {
    			item = holder.fachada.getItemPorID(restaurante, id);
    			if (item != null) {
    				holder.fachada.removerItemDaSacola(holder.getClienteLogado().getCPF(), item, holder.fachada.pesquisarGerentePorRestaurante(restaurante));
    				break;
    			}
    		}
    		System.out.println(item.getID());
    		
    		IDField.setText("");
    		messageLabel.setText("Removido!");
    		emptyLabel();
    		initialize();
    		
    	} catch (NumberFormatException e) {
    		if (IDField.getText().toString().isEmpty()) {
    			messageLabel.setText("Digite o ID!");
    			emptyLabel();
    		} else {
    			messageLabel.setText("Dados Inválidos!");
    			emptyLabel();
    		}
    	} catch (NullPointerException e) {
    		messageLabel.setText("Produto não encontrado!");
    		emptyLabel();
    	} catch (SacolaVaziaException e) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setTitle("Aviso");
			alert.setHeaderText("A sacola está vazia, adicione produtos!");
			alert.show();
    	} catch (Exception e) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setTitle("Aviso");
			alert.setHeaderText("Tivemos um problema, saia e entre de novo!");
			alert.show();
    	}
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
