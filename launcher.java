package emailClient;

import emailClient.view.ViewFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by PZON_SM on 16.09.2020.
 **/
public class launcher extends Application {
    public static void main(String[] args){
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        ViewFactory viewFactory = new ViewFactory(new EmailManager());
        //viewFactory.showMainWindow();
        viewFactory.showLoginWindow();
        viewFactory.showOptionsWindow();
    }
}
