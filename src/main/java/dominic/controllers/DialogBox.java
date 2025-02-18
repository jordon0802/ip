package dominic.controllers;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a Dialog Box
 *
 * @author Jordon Chang
 * @version v1.0.0-alpha
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox where {@param message} is the text to display and {@param image} is the image to display.
     *
     * @param message message to be displayed.
     * @param image image to be displayed.
     */
    public DialogBox(String message, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("Error: Failed to load DialogBox.fxml");
        }

        dialog.setText(message);
        displayPicture.setImage(image);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Returns the DialogBox for a user.
     *
     * @param message message to be displayed.
     * @param image image to be displayed.
     * @return the DialogBox constructed
     */
    public static DialogBox getUserDialog(String message, Image image) {
        return new DialogBox(message, image);
    }

    /**
     * Returns the DialogBox for Dominic.
     *
     * @param message message to be displayed.
     * @param image image to be displayed.
     * @return the DialogBox constructed.
     */
    public static DialogBox getDominicDialog(String message, Image image) {
        var db = new DialogBox(message, image);
        db.flip();
        return db;
    }
}
