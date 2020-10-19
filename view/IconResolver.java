package emailClient.view;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by PZON_SM on 16.10.2020.
 **/
public class IconResolver {
    public Node getFolderIcon(String folderName){
        String folderNameLC = folderName.toLowerCase();
        ImageView imageView;
        try{
            if (folderNameLC.contains("@")){
                imageView = new ImageView(new Image(getClass().getResourceAsStream("icons/email.png")));
            }else if(folderNameLC.contains("inbox")){
                imageView = new ImageView(new Image(getClass().getResourceAsStream("icons/inbox.png")));
            }else if(folderNameLC.contains("spam")){
                imageView = new ImageView(new Image(getClass().getResourceAsStream("icons/spam.png")));
            }else if(folderNameLC.contains("wysłane")){
                imageView = new ImageView(new Image(getClass().getResourceAsStream("icons/sent2.png")));
            }else{
                imageView = new ImageView(new Image(getClass().getResourceAsStream("icons/folder.png")));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        imageView.setFitHeight(16);
        imageView.setFitWidth(16);
        return imageView;
    }
}
