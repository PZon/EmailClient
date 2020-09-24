package emailClient.controller;

import emailClient.EmailManager;
import emailClient.controller.services.LoginService;
import emailClient.model.EmailAccount;
import emailClient.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by PZON_SM on 17.09.2020.
 **/
public class LoginWindowController extends BaseController implements Initializable {


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
        if(fieldsAreValid()){
            EmailAccount emailAccount = new EmailAccount(emailInput.getText(), passwordInput.getText());
            LoginService loginService = new LoginService(emailManager, emailAccount);
            loginService.start();
            loginService.setOnSucceeded(event ->{
                EmailLoginResult emailLoginResult = loginService.getValue();

                switch (emailLoginResult){
                    case SUCCESS:
                        System.out.println("login ok: "+emailAccount);
                        viewFactory.showMainWindow();
                        Stage stage =(Stage) errorMessage.getScene().getWindow();
                        viewFactory.closeStage(stage);
                        return;
                    case FAILED_BY_CREDENTIALS:
                        System.out.println("invalid credentials");
                        return;
                    case FAILED_BY_UNEXPECTED_ERROR:
                        System.out.println("unexpected error");
                        return;
                    default:
                        return;
                }
            });
        }

    }

    private boolean fieldsAreValid() {
        if(emailInput.getText().isEmpty()){
            errorMessage.setText("Please fill login");
            return  false;
        }else if(passwordInput.getText().isEmpty()){
            errorMessage.setText("please fill password");
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        emailInput.setText("pzon.temp@gmail.com");
        passwordInput.setText("orbitek1");
    }
}
