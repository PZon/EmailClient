package emailClient.view;

import emailClient.EmailManager;
import emailClient.controller.BaseController;
import emailClient.controller.LoginWindowController;
import emailClient.controller.MainWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

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
        BaseController controller = new LoginWindowController(emailManager, this, "LoginWindow.fxml");
        initializeStage(controller);
    }

    public void showMainWindow(){
        System.out.println("MainWindow method has been called");
        BaseController baseController = new MainWindowController(emailManager,this,"MainWindow.fxml");
        initializeStage(baseController);
    }

    private void initializeStage(BaseController controller){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(controller.getFxmlFileName()));
        fxmlLoader.setController(controller);
        Parent parent;
        try{
            parent = fxmlLoader.load();
        }catch (IOException e ){
            e.printStackTrace();
            return;
        }

        Scene scene= new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void closeStage(Stage stage){
        stage.close();
    }
}
