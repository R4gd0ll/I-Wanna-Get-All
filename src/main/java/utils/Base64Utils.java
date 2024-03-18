package utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Utils {

    private static final Base64.Encoder encoder = Base64.getEncoder();

    public static String base64Encode(String text) {
        try {
            byte[] textByte = text.getBytes("UTF-8");
            String encodedText = encoder.encodeToString(textByte);
            //System.out.println(encodedText);
            return encodedText;
        } catch (UnsupportedEncodingException e) {

        }
        return null;
    }

    private static final Base64.Decoder decoder = Base64.getDecoder();
    public static String base64Decode(String encodedText) {
        try {
            String text = new String(decoder.decode(encodedText.replace(" ","")), "UTF-8");
            //System.out.println(text);
            return text;
        } catch (UnsupportedEncodingException e) {
        }
        return null;
    }
}


