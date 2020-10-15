package emailClient.controller;

import emailClient.EmailManager;
import emailClient.controller.services.EmailSenderService;
import emailClient.model.EmailAccount;
import emailClient.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by PZON_SM on 09.10.2020.
 **/
public class ComposeMessageController extends BaseController implements Initializable {


    @FXML
    private ChoiceBox<EmailAccount> emailAccChoice;

    @FXML
    private TextField recipientTxtField;

    @FXML
    private TextField subjectTxtField;

    @FXML
    private Label emailErrorLabel;

    @FXML
    private HTMLEditor txtAreaField;

    @FXML
    void buttonSendMessage() {
        EmailSenderService emailSenderService = new EmailSenderService(
                emailAccChoice.getValue(), subjectTxtField.getText(), recipientTxtField.getText(), txtAreaField.getHtmlText()
        );
        emailSenderService.start();
        emailSenderService.setOnSucceeded(e->{
            EmailSendingResult emailSendingResult = emailSenderService.getValue();
            switch (emailSendingResult){
                case SUCCESS:
                    Stage stage = (Stage) recipientTxtField.getScene().getWindow();
                    viewFactory.closeStage(stage);
                    break;
                case FAILED_BY_USER:
                    emailErrorLabel.setText("User error");
                    break;
                case FAILED_UNEXPECTED_ERROR:
                    emailErrorLabel.setText("Unexpected error");
                    break;
            }
        });
    }

    public ComposeMessageController(EmailManager emailManager, ViewFactory viewFactory, String fxmlFileName) {
        super(emailManager, viewFactory, fxmlFileName);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        emailAccChoice.setItems(emailManager.getEmailAccounts());
        emailAccChoice.setValue(emailManager.getEmailAccounts().get(0));
    }
}
