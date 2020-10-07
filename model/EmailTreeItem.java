package emailClient.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by PZON_SM on 24.09.2020.
 **/
public class EmailTreeItem<String> extends TreeItem<String> {
    private String name;
    private ObservableList<EmailMessage>emailMessages;
    private int unreadMessagesNr;

    public EmailTreeItem(String name) {
        super(name);
        this.name = name;
        this.emailMessages = FXCollections.observableArrayList();
    }

    public ObservableList<EmailMessage> getEmailMessages(){
        return emailMessages;
    }

    public void addEmail(Message message) throws MessagingException {
       EmailMessage emailMessage = fetchMessage(message);
       emailMessages.add(emailMessage);
    }

    private EmailMessage fetchMessage(Message message) throws MessagingException {
        boolean isRead = message.getFlags().contains(Flags.Flag.SEEN);
        EmailMessage emailMessage = new EmailMessage(
                message.getSubject(),
                message.getFrom()[0].toString(),
                message.getRecipients(MimeMessage.RecipientType.TO)[0].toString(),
                message.getSize(),
                isRead,
                message,
                message.getSentDate()
        );
        if(!isRead) incrementUnreadMessagesNr();
        return emailMessage;
    }

    private void updateInboxName(){
        if(unreadMessagesNr > 0){
            this.setValue((String) (name + " (" + unreadMessagesNr + ")"));
        }else {
            this.setValue(name);
        }
    }

    public void incrementUnreadMessagesNr(){
        unreadMessagesNr++;
        updateInboxName();
    }

    public void addEmailAtBeginning(Message message) throws MessagingException {
        EmailMessage emailMessage = fetchMessage(message);
        emailMessages.add(0,emailMessage);
    }

    public void decrementMessageNr() {
        unreadMessagesNr--;
        updateInboxName();
    }
}
