package dominic.main;

import java.io.IOException;

import dominic.controllers.MainWindow;
import dominic.ui.Dominic;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The Main class to load the UI
 *
 * @author Jordon Chang
 * @version v1.0.0-alpha
 */
public class Main extends Application {
    private final Dominic dominic = new Dominic();

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDominic(this.dominic);
            stage.show();
        } catch (IOException e) {
            System.out.println("Error: Failed to load MainWindow.fxml");
        }
    }
}
