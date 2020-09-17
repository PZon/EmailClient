package emailClient.controller;

/**
 * Created by PZON_SM on 17.09.2020.
 **/
import emailClient.EmailManager;
import emailClient.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;
import javafx.scene.web.WebView;

public class MainWindowController extends BaseController {

    @FXML
    private TreeView<?> emailTreeView;

    @FXML
    private TableView<?> emailTableView;

    @FXML
    private WebView emailWebView;

    public MainWindowController(EmailManager emailManager, ViewFactory viewFactory, String fxmlFileName) {
        super(emailManager, viewFactory, fxmlFileName);
    }

    @FXML
    void menuOptionAction() {

    }
}