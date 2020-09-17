package emailClient.controller;

import emailClient.EmailManager;
import emailClient.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Created by PZON_SM on 17.09.2020.
 **/
public class LoginWindowController extends BaseController {


    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private TextField emailInput;

    @FXML
    private Label errorMessage;

    public LoginWindowController(EmailManager emailManager, ViewFactory viewFactory, String fxmlFileName) {
        super(emailManager, viewFactory, fxmlFileName);
    }

    @FXML
    void loginButtonAction() {
        System.out.println("button has been clicked");
    }
}
