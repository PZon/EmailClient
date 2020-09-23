package emailClient.view;

/**
 * Created by PZON_SM on 22.09.2020.
 **/
public enum ColorTheme {
    LIGHT, DEFAULT, DARK;

    public static String getThemePath(ColorTheme colorTheme){
        switch (colorTheme){
            case LIGHT:
                return "styleCss/themeLight.css";
            case DARK:
                return "styleCss/themeDark.css";
            case DEFAULT:
                return "styleCss/themeDefault.css";
            default:
                return null;
        }
    }
}
