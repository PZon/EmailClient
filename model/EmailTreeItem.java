package emailClient.model;

import javafx.scene.control.TreeItem;

/**
 * Created by PZON_SM on 24.09.2020.
 **/
public class EmailTreeItem<String> extends TreeItem<String> {
    private String name;

    public EmailTreeItem(String name) {
        super(name);
        this.name = name;
    }
}
