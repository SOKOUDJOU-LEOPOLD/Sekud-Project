package main;

import java.io.File;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import models.Login;

/**
 *
 * @author Roger NDJEUMOU
 */
public class Main extends Application {

    public static final String APP_NAME = "Sek\u00F9d";

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;
        stage.getIcons().add(
                new Image(Main.class.getResourceAsStream("../images/sekud-logo.jpg")));
        setStage(APP_NAME + " - Login", "../views/LoginView.fxml");
    }

    public static void loadHomeView(ActionEvent event, Login loginInfo)
            throws Exception {
        // Close the log in windows
        Node node = (Node) event.getSource();
        stage = (Stage) node.getScene().getWindow();
        stage.close();

        stage.setUserData(loginInfo); // Set data for home view
        // Launch home view
        setStage(APP_NAME + " - Home", "../views/homeView.fxml");
    }

    // Method to open new scene whose title is "title" and 
    // whose location is "path"
    private static void setStage(String title, String path)
            throws Exception {
        Scene scene = new Scene(loadFXML(path));
        stage.setTitle(title);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    private static Parent loadFXML(String path)
            throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(path));
        return fxmlLoader.load();
    }

    public void viewPDF(File file) {
        HostServices hostServices = getHostServices();
        hostServices.showDocument(file.getAbsolutePath());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public Main() {

    }
}
