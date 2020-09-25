package emailClient;

import emailClient.controller.EmailLoginResult;
import emailClient.controller.services.FetchFoldersService;
import emailClient.model.EmailAccount;
import emailClient.model.EmailTreeItem;
import javafx.scene.control.TreeItem;

/**
 * Created by PZON_SM on 17.09.2020.
 **/
public class EmailManager {
    private EmailTreeItem<String> folderRoot = new EmailTreeItem<String>("");

    public EmailTreeItem<String> getFolderRoot() {
        return folderRoot;
    }

    public void addEmailAccount(EmailAccount emailAccount) {
        EmailTreeItem<String> treeItem = new EmailTreeItem<String>(emailAccount.getEmailAddress());
        treeItem.setExpanded(true);
        FetchFoldersService fetchFoldersService = new FetchFoldersService(emailAccount.getStore(), treeItem);
        fetchFoldersService.start();
        folderRoot.getChildren().add(treeItem);
    }
}
