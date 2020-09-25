package emailClient.controller.services;

import emailClient.model.EmailTreeItem;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Store;

/**
 * Created by PZON_SM on 24.09.2020.
 **/
public class FetchFoldersService extends Service<Void> {

    private Store store;
    private EmailTreeItem<String> folderRoot;

    public FetchFoldersService(Store store, EmailTreeItem<String> folderRoot) {
        this.store = store;
        this.folderRoot = folderRoot;
    }

    @Override
    protected Task createTask() {
        return new Task() {
            @Override
            protected Void call() throws Exception {
                fetchFolders();
                return null;
            }
        };
    }

    private void fetchFolders() throws MessagingException {
        Folder[] folders = store.getDefaultFolder().list();
        handleFolders(folders, folderRoot);
    }

    private void handleFolders(Folder[] folders, EmailTreeItem<String> folderRoot) throws MessagingException {
        for(Folder folder:folders){
            EmailTreeItem<String> emailTreeItem = new EmailTreeItem<String>(folder.getName());
            folderRoot.getChildren().add(emailTreeItem);
            folderRoot.setExpanded(true);
            fetchMessagesToFolders(folder, emailTreeItem);
            if(folder.getType() == Folder.HOLDS_FOLDERS){
                Folder[] subFolders =  folder.list();
                handleFolders(subFolders, emailTreeItem);
            }
        }
    }

    private void fetchMessagesToFolders(Folder folder, EmailTreeItem<String> emailTreeItem) {
        Service fetchMessegesService = new Service() {
            @Override
            protected Task createTask() {
                return new Task() {
                    @Override
                    protected Object call() throws Exception {
                        if(folder.getType() != Folder.HOLDS_FOLDERS){
                            folder.open(Folder.READ_WRITE);
                            int folderSize = folder.getMessageCount();
                            for(int i=folderSize; i>0; i--){
                                System.out.println(folder.getMessage(i).getSubject());
                            }
                        }
                        return null;
                    }
                };
            }
        };
        fetchMessegesService.start();
    }
}
