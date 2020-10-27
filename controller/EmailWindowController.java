package emailClient.controller;

import emailClient.EmailManager;
import emailClient.controller.services.MessageRendererService;
import emailClient.model.EmailMessage;
import emailClient.view.ViewFactory;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebView;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by PZON_SM on 23.10.2020.
 **/
public class EmailWindowController extends BaseController implements Initializable {

    public EmailWindowController(EmailManager emailManager, ViewFactory viewFactory, String fxmlFileName) {
        super(emailManager, viewFactory, fxmlFileName);
    }

    private String DOWNLOAD_LOCATION = System.getProperty("user.home")+"/Downloads/";

    @FXML
    private Label attachmentLabel;

    @FXML
    private WebView webView;

    @FXML
    private Label subjectLabel;

    @FXML
    private Label senderLabel;

    @FXML
    private HBox attachmentFiles;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EmailMessage emailMessage = emailManager.getSelectedMessage();
        subjectLabel.setText(emailMessage.getSubject());
        senderLabel.setText(emailMessage.getSender());
        loadAttachments(emailMessage);

        MessageRendererService messageRendererService = new MessageRendererService(webView.getEngine());
        messageRendererService.setEmailMessage(emailMessage);
        messageRendererService.restart();

    }

    private void loadAttachments(EmailMessage emailMessage){
        if(emailMessage.hasAttachment()){
            for(MimeBodyPart mimeBodyPart:emailMessage.getAttachmentList()){
                try{
                    AttachmentButton button = new AttachmentButton(mimeBodyPart);
                    attachmentFiles.getChildren().add(button);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }else {
            attachmentLabel.setText("");
        }
    }

    private class AttachmentButton extends Button{
        private String downloadFilePath;
        private MimeBodyPart mimeBodyPart;

        public AttachmentButton(MimeBodyPart mimeBodyPart) throws MessagingException {
            this.mimeBodyPart = mimeBodyPart;
            this.setText(mimeBodyPart.getFileName());
            this.downloadFilePath = DOWNLOAD_LOCATION + mimeBodyPart.getFileName();

            this.setOnAction(e->downloadAttachment());
        }

        private void downloadAttachment() {
            colorBlue();
            Service service = new Service() {
                @Override
                protected Task createTask() {
                    return new Task() {
                        @Override
                        protected Object call() throws Exception {
                            mimeBodyPart.saveFile(downloadFilePath);
                            return null;
                        }
                    };
                }
            };
            service.restart();
            service.setOnSucceeded(e->{
                colorGreen();
                this.setOnAction(e2->{
                    File file = new File(downloadFilePath);
                    Desktop desktop = Desktop.getDesktop();
                    if(file.exists()){
                      try{
                        desktop.open(file);
                      }catch (Exception exception){
                          exception.printStackTrace();
                      }
                    }
                });
            });
        }

        private void colorBlue() {
            this.setStyle("-fx-background-color: Blue");
            this.setStyle("-fx-text-fill: Blue");
        }

        private void colorGreen() {
            this.setStyle("-fx-background-color: Green");
            this.setStyle("-fx-text-fill: Orange");
        }
    }
}
