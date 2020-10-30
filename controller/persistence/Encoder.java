package emailClient.controller.persistence;

import java.util.Base64;

/**
 * Created by PZON_SM on 30.10.2020.
 **/
public class Encoder {
    private static Base64.Encoder encoder = Base64.getEncoder();
    private static Base64.Decoder decoder = Base64.getDecoder();

    public String encode(String txt){
        try {
            return encoder.encodeToString(txt.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String decode(String txt){
        try {
            return new String(decoder.decode(txt.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
