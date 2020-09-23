package emailClient.view;

/**
 * Created by PZON_SM on 22.09.2020.
 **/
public enum FontSize {
    SMALL, MEDIUM, LARGE;

    public static String getFontPath(FontSize fontSize){
        switch (fontSize){
            case SMALL:
                return "style/Css/FontSmall.css";
            case MEDIUM:
                return "styleCss/FontMedium.css";
            case LARGE:
                return "styleCss/FontLarge.css";
            default:
                return null;
        }
    }
}
