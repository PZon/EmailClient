package emailClient;

import emailClient.controller.EmailLoginResult;
import emailClient.controller.services.FetchFoldersService;
import emailClient.controller.services.FolderUpdateService;
import emailClient.model.EmailAccount;
import emailClient.model.EmailMessage;
import emailClient.model.EmailTreeItem;
import emailClient.view.IconResolver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import javax.mail.Flags;
import javax.mail.Folder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PZON_SM on 17.09.2020.
 **/
public class EmailManager {

    private EmailMessage selectedMessage;
    private EmailTreeItem<String>selectedFolder;
    private FolderUpdateService folderUpdateService;
    private EmailTreeItem<String> folderRoot = new EmailTreeItem<String>("");
    private ObservableList<EmailAccount> emailAccounts = FXCollections.observableArrayList();
    private IconResolver iconResolver = new IconResolver();



    public EmailTreeItem<String> getFolderRoot() {
        return folderRoot;
    }
    private List<Folder> folderList = new ArrayList<Folder>();

    public List<Folder> getFolderList() {
        return folderList;
    }

    public EmailManager() {
        folderUpdateService = new FolderUpdateService(folderList);
        folderUpdateService.start();
    }

    public EmailMessage getSelectedMessage() {
        return selectedMessage;
    }

    public ObservableList<EmailAccount> getEmailAccounts() {
        return emailAccounts;
    }

    public void setSelectedMessage(EmailMessage selectedMessage) {
        this.selectedMessage = selectedMessage;
    }

    public EmailTreeItem<String> getSelectedFolder() {
        return selectedFolder;
    }

    public void setSelectedFolder(EmailTreeItem<String> selectedFolder) {
        this.selectedFolder = selectedFolder;
    }

    public void addEmailAccount(EmailAccount emailAccount) {
        emailAccounts.add(emailAccount);
        EmailTreeItem<String> treeItem = new EmailTreeItem<String>(emailAccount.getEmailAddress());
        treeItem.setGraphic(iconResolver.getFolderIcon(emailAccount.getEmailAddress()));
        treeItem.setExpanded(true);
        FetchFoldersService fetchFoldersService = new FetchFoldersService(emailAccount.getStore(), treeItem,folderList);
        fetchFoldersService.start();
        folderRoot.getChildren().add(treeItem);
    }

    public void setIsRead(){
        try{
            selectedMessage.setRead(true);
            selectedMessage.getMessage().setFlag(Flags.Flag.SEEN, true);
            selectedFolder.decrementMessageNr();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setIsUnread(){
        try{
            selectedMessage.setRead(false);
            selectedMessage.getMessage().setFlag(Flags.Flag.SEEN, false);
            selectedFolder.incrementUnreadMessagesNr();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteMessage(){
        try{
            selectedMessage.getMessage().setFlag(Flags.Flag.DELETED, true);
            selectedFolder.getEmailMessages().remove(selectedMessage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
