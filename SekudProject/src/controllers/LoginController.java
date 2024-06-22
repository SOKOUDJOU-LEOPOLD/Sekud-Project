package controllers;

import database.DatabaseConnectionManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import models.Login;

/**
 * FXML Controller class
 *
 * @author Roger NDJEUMOU
 */
public class LoginController implements Initializable {

    @FXML
    private TextField loginInput;

    @FXML
    private PasswordField pwdInput;

    @FXML
    private Label welcomeText;

    @FXML
    void cancelHandler(ActionEvent event) {
        // Close the window that originated the event
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void signInHandler(ActionEvent event) throws Exception {
        // Correct credentials: Login: Sekud Pwd: sekud2022

        DatabaseConnectionManager manager = new DatabaseConnectionManager();
        Login user = manager.checkLogin(new Login(loginInput.getText(), pwdInput.getText()));
        if (user.getId() != 0) {
            Alert ok = new Alert(Alert.AlertType.INFORMATION);
            ok.setTitle("Connection successful");
            ok.setHeaderText("You provided the correct credentials.");
            ok.showAndWait();

            main.Main.loadHomeView(event, user);

        } else {
            Alert wrong = new Alert(Alert.AlertType.ERROR);
            wrong.setTitle("Connection unsuccessful");
            wrong.setHeaderText("Incorrect credentials.");
            wrong.showAndWait();
        }
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        Ellipse ellipse = new Ellipse(100, 70);
        ellipse.setStroke(Color.valueOf("#D1AC6D"));
        ellipse.setStrokeWidth(5);
        ellipse.setStrokeType(StrokeType.INSIDE);
        ellipse.setStrokeDashOffset(6);
        ellipse.setFill(Color.WHITE);

        ImageView logo = new ImageView(
                new Image(LoginController.class.
                        getResourceAsStream("../images/sekud-logo.jpg")));
        logo.setFitWidth(145);
        logo.setFitHeight(85);
        logo.setSmooth(true);

        Pane stack = new StackPane(ellipse, logo);
        welcomeText.setGraphic(stack);
        welcomeText.setContentDisplay(ContentDisplay.TOP);
        welcomeText.setGraphicTextGap(0);
        //welcomeText.setText("Log in to your account");
    }

}
