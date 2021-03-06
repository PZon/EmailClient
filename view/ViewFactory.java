package emailClient.view;

import emailClient.EmailManager;
import emailClient.controller.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.xml.stream.events.StartElement;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by PZON_SM on 17.09.2020.
 **/
public class ViewFactory {
    private EmailManager emailManager;
    private ColorTheme colorTheme = ColorTheme.DEFAULT;
    private FontSize fontSize = FontSize.MEDIUM;
    private ArrayList<Stage> activeStages;
    private  boolean mainWindowActive = false;


    public ViewFactory(EmailManager emailManager) {

        this.emailManager = emailManager;
        activeStages = new ArrayList<Stage>();
    }

    public boolean isMainWindowActive(){
        return mainWindowActive;
    }

    public void showLoginWindow(){
        BaseController controller = new LoginWindowController(emailManager, this, "LoginWindow.fxml");
        initializeStage(controller);
    }

    public void showMainWindow(){
        BaseController baseController = new MainWindowController(emailManager,this,"MainWindow.fxml");
        initializeStage(baseController);
        mainWindowActive = true;
    }

    public void showOptionsWindow(){
        BaseController baseController = new OptionsWindowController(emailManager, this, "OptionsWindow.fxml");
        initializeStage(baseController);
    }

    public void showComposeMessageWindow(){
        BaseController baseController = new ComposeMessageController(emailManager, this, "ComposeMessageWindow.fxml");
        initializeStage(baseController);
    }

    public void showEmailWindow(){
        BaseController baseController = new EmailWindowController(emailManager, this, "EmailWindow.fxml");
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
        updateStyle(scene);
        Stage stage = new Stage();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("img/phiSmall.png")));
        stage.setTitle("emailManager by PZON");
        stage.setScene(scene);
        stage.show();
        activeStages.add(stage);
    }

    public void closeStage(Stage stage){
        stage.close();
        activeStages.remove(stage);
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

    public void updateAllStyle() {
        for(Stage stage:activeStages){
            Scene scene = stage.getScene();
            updateStyle(scene);
        }
    }

    private void updateStyle(Scene scene) {
        scene.getStylesheets().clear();
        scene.getStylesheets().add(getClass().getResource(ColorTheme.getThemePath(colorTheme)).toExternalForm());
        scene.getStylesheets().add(getClass().getResource(FontSize.getFontPath(fontSize)).toExternalForm());
    }
}
