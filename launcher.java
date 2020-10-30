package emailClient;

import emailClient.controller.persistence.PersistenceAccess;
import emailClient.controller.persistence.ValidAccount;
import emailClient.controller.services.LoginService;
import emailClient.model.EmailAccount;
import emailClient.view.ViewFactory;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PZON_SM on 16.09.2020.
 **/
public class launcher extends Application {
    public static void main(String[] args){
        launch(args);
    }
    private PersistenceAccess persistenceAccess = new PersistenceAccess();
    private EmailManager emailManager = new EmailManager();


  @Override
    public void start(Stage stage) throws Exception {
        ViewFactory viewFactory = new ViewFactory(emailManager);
        List<ValidAccount> validAccounts = persistenceAccess.loadFromPersistence();
        if(validAccounts.size()>0) {
            viewFactory.showMainWindow();
            for(ValidAccount validAccount: validAccounts){
                EmailAccount emailAccount = new EmailAccount(validAccount.getEmailAddress(), validAccount.getPassword());
                LoginService loginService = new LoginService( emailManager, emailAccount);
                loginService.start();
            }
        }else {
            viewFactory.showLoginWindow();
        }
    }

 /*
   @Override
    public void start(Stage stage) throws Exception {
        ViewFactory viewFactory = new ViewFactory(new EmailManager());
        viewFactory.showLoginWindow();
    }*/

    @Override
    public void stop() throws Exception {
        List<ValidAccount> validAccounts = new ArrayList<ValidAccount>();
        for(EmailAccount emailAccount:emailManager.getEmailAccounts()){
            validAccounts.add(new ValidAccount(emailAccount.getEmailAddress(), emailAccount.getPassword()));
        }
        persistenceAccess.savePersistence(validAccounts);
    }
}
