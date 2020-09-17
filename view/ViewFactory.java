package emailClient.view;

import emailClient.EmailManager;

/**
 * Created by PZON_SM on 17.09.2020.
 **/
public class ViewFactory {
    private EmailManager emailManager;

    public ViewFactory(EmailManager emailManager) {
        this.emailManager = emailManager;
    }

    public void showLoginWindow(){
        System.out.println("LoginWindow method has been called");
    }
}
