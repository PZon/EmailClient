package emailClient.controller;

/**
 * Created by PZON_SM on 17.09.2020.
 **/
import emailClient.EmailManager;
import emailClient.controller.services.MessageRendererService;
import emailClient.model.EmailMessage;
import emailClient.model.EmailSize;
import emailClient.model.EmailTreeItem;
import emailClient.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebView;
import javafx.util.Callback;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class MainWindowController extends BaseController implements Initializable {

    private MenuItem markUnreadMenuItem = new MenuItem("set unread");
    private MenuItem deleteMenuItem = new MenuItem("delete");

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
    private TableColumn<EmailMessage, EmailSize> sizeCol;

    @FXML
    private WebView emailWebView;


    private MessageRendererService messageRendererService;

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

    @FXML
    void newMessageActon() {
        viewFactory.showComposeMessageWindow();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpEmailTreeView();
        setUpEmailTableView();
        setUpFolderSelection();
        setUpBoldFont();
        setUpMessageRenderService();
        setUpMessageSelection();
        setUpContextMenu();
    }

    private void setUpContextMenu() {
        markUnreadMenuItem.setOnAction(e->{
            emailManager.setIsUnread();
        });

        deleteMenuItem.setOnAction(e->{
            emailManager.deleteMessage();
            emailWebView.getEngine().loadContent("");
        });
    }

    private void setUpMessageSelection() {
        emailTableView.setOnMouseClicked(e->{
            EmailMessage emailMessage = emailTableView.getSelectionModel().getSelectedItem();
            if(emailMessage!=null){
                emailManager.setSelectedMessage(emailMessage);
                if(!emailMessage.isRead()) emailManager.setIsRead();
                messageRendererService.setEmailMessage(emailMessage);
                messageRendererService.restart();
            }
        });
    }

    private void setUpMessageRenderService() {
        messageRendererService = new MessageRendererService(emailWebView.getEngine());
    }

    private void setUpBoldFont() {
        emailTableView.setRowFactory(new Callback<TableView<EmailMessage>, TableRow<EmailMessage>>() {
            @Override
            public TableRow<EmailMessage> call(TableView<EmailMessage> emailMessageTableView) {
                return new TableRow<EmailMessage>(){
                    @Override
                    protected void updateItem(EmailMessage emailMessage, boolean b) {
                        super.updateItem(emailMessage, b);
                        if(emailMessage != null){
                            if(emailMessage.isRead()){
                                setStyle("");
                            }else{
                                setStyle("-fx-font-weight: bold");
                            }
                        }
                    }
                };
            }
        });
    }

    private void setUpFolderSelection() {
        emailTreeView.setOnMouseClicked(e->{
            EmailTreeItem<String>item = (EmailTreeItem<String>) emailTreeView.getSelectionModel().getSelectedItem();
            if(item!=null){
                emailManager.setSelectedFolder(item);
                emailTableView.setItems(item.getEmailMessages());
            }
        });
    }

    private void setUpEmailTableView() {
        senderCol.setCellValueFactory(new PropertyValueFactory<EmailMessage,String>("Sender"));
        subjectCol.setCellValueFactory(new PropertyValueFactory<EmailMessage,String>("Subject"));
        recipientCol.setCellValueFactory(new PropertyValueFactory<EmailMessage,String>("Recipient"));
        dateCol.setCellValueFactory(new PropertyValueFactory<EmailMessage,Date>("Date"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<EmailMessage,EmailSize>("Size"));

        emailTableView.setContextMenu(new ContextMenu(markUnreadMenuItem, deleteMenuItem));
    }

    private void setUpEmailTreeView() {
        emailTreeView.setRoot(emailManager.getFolderRoot());
        emailTreeView.setShowRoot(false);
    }


}