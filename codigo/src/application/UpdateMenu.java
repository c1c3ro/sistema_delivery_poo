package application;

import Exceptions.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class UpdateMenu {

    @FXML
    private TextField nameField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private TextField valueField;

    @FXML
    private TextField categoryField;

    @FXML
    private Button addButton;

    @FXML
    private Label messageALabel;

    @FXML
    private Button removeButton;

    @FXML
    private TextField IDField;

    @FXML
    private Label messageRLabel;
    
    @FXML
    private Button backButton;

    @FXML
    void addProduct(ActionEvent event) {
    	try {
    		String name = nameField.getText().toString();
    		double value = Double.parseDouble(valueField.getText().toString());
    		String category = categoryField.getText().toString();
    		String description = descriptionField.getText().toString();
    		
    		if (name.isEmpty() || category.isEmpty() || description.isEmpty()) {
    			messageALabel.setText("Digite todas as informações!");
    		} else {
    			FachadaHolder holder = FachadaHolder.getInstance();
    			holder.fachada.inserirProdutoNoCardapio(holder.getGerenteLogado().getRestaurante(), name, value, category, description);
    			messageALabel.setText("Adicionado!");
    			nameField.setText("");
    			valueField.setText("");
    			categoryField.setText("");
    			descriptionField.setText("");
    		}
    		 
    	} catch (NumberFormatException e) {
    		if (nameField.getText().toString().isEmpty() || categoryField.getText().toString().isEmpty() || descriptionField.getText().toString().isEmpty())
    			messageALabel.setText("Digite todas as informações!");
    		else if (!valueField.getText().toString().isEmpty())
    			messageALabel.setText("Digite o preço com ponto (.), Ex.: 19.49");
    	} catch (UsuarioNaoEncontradoException e) {
    		messageALabel.setText("Tivemos um problema, saia e entre de novo.");
    	} catch (ClienteJaExisteException e) {
    		messageALabel.setText("Este item já existe!");
    	}
    	
    	
    }

    @FXML
    void removeProduct(ActionEvent event) {
    	return;
    }
    
    @FXML
    void backManagerOptions(ActionEvent event) {
    	Main m = new Main();
        m.changeScene("managerOptions.fxml");
    }

}
