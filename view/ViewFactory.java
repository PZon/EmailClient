package emailClient.view;

import emailClient.EmailManager;
import emailClient.controller.BaseController;
import emailClient.controller.LoginWindowController;
import emailClient.controller.MainWindowController;
import emailClient.controller.OptionsWindowController;
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
    private ColorTheme colorTheme = ColorTheme.DEFAULT;
    private FontSize fontSize = FontSize.MEDIUM;

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

    public void showOptionsWindow(){
        System.out.println("Options method window has been called");
        BaseController baseController = new OptionsWindowController(emailManager, this, "OptionsWindow.fxml");
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

    public ColorTheme getColorTheme() {
        return colorTheme;
    }

    public void setColorTheme(ColorTheme colorTheme) {
        this.colorTheme = colorTheme;
    }

    public FontSize getFontSize() {
        return fontSize;
    }

    public void setFontSize(FontSize fontSize) {
        this.fontSize = fontSize;
    }
}
