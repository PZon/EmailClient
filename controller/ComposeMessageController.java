package emailClient.controller;

import emailClient.EmailManager;
import emailClient.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;

/**
 * Created by PZON_SM on 09.10.2020.
 **/
public class ComposeMessageController extends BaseController {


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
        System.out.println(txtAreaField.getHtmlText());
        System.out.println("send button has been clicked");
    }

    public ComposeMessageController(EmailManager emailManager, ViewFactory viewFactory, String fxmlFileName) {
        super(emailManager, viewFactory, fxmlFileName);
    }
}
