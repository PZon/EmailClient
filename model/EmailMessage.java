package emailClient.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by PZON_SM on 28.09.2020.
 **/
public class EmailMessage {
    private SimpleStringProperty subject;
    private SimpleStringProperty sender;
    private SimpleStringProperty recipient;
    private SimpleObjectProperty<EmailSize> size;
    private SimpleObjectProperty<Date>date;
    private boolean isRead;
    private Message message;
    private List<MimeBodyPart> attachmentList = new ArrayList<MimeBodyPart>();
    private boolean hasAttachment = false;

    public EmailMessage(String subject, String sender, String recipient, int size, boolean isRead, Message message, Date date) {
        this.subject = new SimpleStringProperty(subject);
        this.sender = new SimpleStringProperty(sender);
        this.recipient = new SimpleStringProperty(recipient);
        this.size = new SimpleObjectProperty<EmailSize>(new EmailSize(size));
        this.isRead = isRead;
        this.message = message;
        this.date = new SimpleObjectProperty<Date>(date);
    }

    public String getSubject() {
        return this.subject.get();
    }


    public String getSender() {
        return this.sender.get();
    }


    public String getRecipient() {
        return this.recipient.get();
    }


    public EmailSize getSize() {
        return this.size.get();
    }

    public Date getDate() {
        return this.date.get();
    }


    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public Message getMessage() {
        return message;
    }

    public void addAttachment(MimeBodyPart mimeBodyPart){
        hasAttachment = true;
        attachmentList.add(mimeBodyPart);
        try {
            System.out.println("Attachment: " + mimeBodyPart.getFileName());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public boolean hasAttachment(){
        return hasAttachment;
    }

    public List<MimeBodyPart> getAttachmentList(){
        return attachmentList;
    }
}
