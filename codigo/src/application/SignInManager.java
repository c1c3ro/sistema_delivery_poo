package application;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
public class SignInManager {

    @FXML
    private Button signInButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField nameField;

    @FXML
    private TextField restaurantField;

    @FXML
    private TextField cpfField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    void backLogIn(ActionEvent event) throws IOException {
    	Main m = new Main();
        m.changeScene("logInManager.fxml");
    }

    @FXML
    void checkSignIn(ActionEvent event) throws IOException {
    	System.out.println("hey, do nothing");
    }

}
