package emailClient.controller.persistence;

import java.io.Serializable;

/**
 * Created by PZON_SM on 29.10.2020.
 **/
public class ValidAccount implements Serializable {
    private String emailAddress;
    private String password;

    public ValidAccount(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }
}
