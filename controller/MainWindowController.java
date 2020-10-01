package emailClient.controller;

/**
 * Created by PZON_SM on 17.09.2020.
 **/
import emailClient.EmailManager;
import emailClient.model.EmailMessage;
import emailClient.model.EmailTreeItem;
import emailClient.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class MainWindowController extends BaseController implements Initializable {

    @FXML
    private TreeView<String> emailTreeView;

    @FXML
    private TableView<EmailMessage> emailTableView;

    @FXML
    private TableColumn<EmailMessage, String> senderCol;

    @FXML
    private TableColumn<EmailMessage, String> subjectCol;

    @FXML
    private TableColumn<EmailMessage, String> recipientCol;

    @FXML
    private TableColumn<EmailMessage, Date> dateCol;

    @FXML
    private TableColumn<EmailMessage, Integer> sizeCol;


    @FXML
    private WebView emailWebView;

    public MainWindowController(EmailManager emailManager, ViewFactory viewFactory, String fxmlFileName) {
        super(emailManager, viewFactory, fxmlFileName);
    }

    @FXML
    void menuOptionAction() {
        viewFactory.showOptionsWindow();
    }

    @FXML
    void addAccountAction() {
        viewFactory.showLoginWindow();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setUpEmailTreeView();
        setUpEmailTableView();
        setUpFolderSelection();
    }

    private void setUpFolderSelection() {
        emailTreeView.setOnMouseClicked(e->{
            EmailTreeItem<String>item = (EmailTreeItem<String>) emailTreeView.getSelectionModel().getSelectedItem();
            if(item!=null){
                emailTableView.setItems(item.getEmailMessages());
            }
        });
    }

    private void setUpEmailTableView() {
        senderCol.setCellValueFactory(new PropertyValueFactory<EmailMessage,String>("Sender"));
        subjectCol.setCellValueFactory(new PropertyValueFactory<EmailMessage,String>("Subject"));
        recipientCol.setCellValueFactory(new PropertyValueFactory<EmailMessage,String>("Recipient"));
        dateCol.setCellValueFactory(new PropertyValueFactory<EmailMessage,Date>("Date"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<EmailMessage,Integer>("Size"));
    }

    private void setUpEmailTreeView() {
        emailTreeView.setRoot(emailManager.getFolderRoot());
        emailTreeView.setShowRoot(false);
    }
}