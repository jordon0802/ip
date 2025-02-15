package dominic.controllers;

import dominic.storage.StorageWriter;
import dominic.ui.Dominic;
import dominic.utils.Parser;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Dominic dominic;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dominicImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getDominicDialog(Dominic.initialize(), dominicImage)
        );
    }

    /** Injects Dominic Instance */
    public void setDominic(Dominic dominic) {
        this.dominic = dominic;
    }

    @FXML
    private void handleUserInput() {
        if (userInput.getText().equalsIgnoreCase("bye")) {
            StorageWriter.writeToFile();
                Platform.exit();
        }
        String input = userInput.getText();
        String response = Parser.run(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDominicDialog(response, dominicImage)
        );
        userInput.clear();
    }
}
