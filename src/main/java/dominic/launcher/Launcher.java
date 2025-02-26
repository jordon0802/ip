package dominic.launcher;

import dominic.main.Main;
import javafx.application.Application;

/**
 * Launcher class for JavaFX
 *
 * @author Jordon Chang
 * @version v1.1
 */
public class Launcher {
    /**
     * The entry point to the launcher.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
