package application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;


public class AfterLogIn {

    @FXML
    private Button logOutButton;


    public void userLogOut(ActionEvent event) {
        Main m = new Main();
        m.changeScene("logIn.fxml");
    }
}
