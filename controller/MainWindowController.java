package emailClient.controller;

/**
 * Created by PZON_SM on 17.09.2020.
 **/
import emailClient.EmailManager;
import emailClient.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController extends BaseController implements Initializable {

    @FXML
    private TreeView<String> emailTreeView;

    @FXML
    private TableView<?> emailTableView;

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
    }

    private void setUpEmailTreeView() {
        emailTreeView.setRoot(emailManager.getFolderRoot());
        emailTreeView.setShowRoot(false);
    }
}