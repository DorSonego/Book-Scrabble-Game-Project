package View;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    public View.BoardDisplayer BoardDisplayer;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}