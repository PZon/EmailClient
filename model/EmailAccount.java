package emailClient.model;

import javax.mail.Store;
import java.util.Properties;

/**
 * Created by PZON_SM on 23.09.2020.
 **/
public class EmailAccount {
    private String emailAddress;
    private String password;
    private Properties properties;
    private Store store;

    public EmailAccount(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password;

        properties = new Properties();
        properties.put("incomingHost", "imap.gmail.com");
        properties.put("mail.store.protocol", "imaps");

        properties.put("mail.transport.protocol", "smtps");
        properties.put("mail.smtps.host", "smtp.gmail.com");
        properties.put("mail.smtps.auth", "true");
        properties.put("outgoingHost", "smtp.gmail.com");

    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
