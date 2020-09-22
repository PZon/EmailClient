package emailClient.controller;

import emailClient.EmailManager;
import emailClient.view.ViewFactory;

/**
 * Created by PZON_SM on 17.09.2020.
 **/
public abstract class BaseController {
    protected EmailManager emailManager;
    protected ViewFactory viewFactory;
    private String fxmlFileName;

    public BaseController(EmailManager emailManager, ViewFactory viewFactory, String fxmlFileName) {
        this.emailManager = emailManager;
        this.viewFactory = viewFactory;
        this.fxmlFileName = fxmlFileName;
    }

    public String getFxmlFileName(){
        return fxmlFileName;
    }
}
