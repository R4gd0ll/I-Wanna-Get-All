package decrypt.seeyon;

import core.CryptInterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import decrypt.code.getAllEncode;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class seeyon_crypt implements CryptInterface {
    @Override
    public Boolean decrypt(String text,String type ,TextArea textArea_decrypt,TextArea textArea_info) {
        return dec(text,type,textArea_decrypt,textArea_info);
    }

    @Override
    public Boolean encrypt(String text,String type, TextArea textArea_encrypt,TextArea textArea_info) {
        return enc(text,type,textArea_encrypt,textArea_info);
    }

    private Boolean dec(String text,String type,TextArea textArea_decrypt,TextArea textArea_info){
        try {
            String flag = zyDecrypt(text);
            if(flag!=null && flag!="" && !flag.isEmpty()){
                Platform.runLater(() -> {
                    textArea_info.appendText(getAllEncode.getTime()+"\n[+]"+type+" 解密成功!\n");
                    textArea_decrypt.appendText(flag);
                });
                return true;
            }
        } catch (Exception e) {
            Platform.runLater(() -> {
                textArea_info.appendText(getAllEncode.getTime()+"\n[-]"+type+" 解密失败!\n");
            });
            e.printStackTrace();
        }
        Platform.runLater(() -> {
            textArea_info.appendText(getAllEncode.getTime()+"\n[-]"+type+" 解密失败!\n");
        });
        return false;
    }

    private Boolean enc(String text,String type ,TextArea textArea_encrypt,TextArea textArea_info){
        try {
            String flag = zyEncrypt(text);
            if(flag!=null && flag!="" && !flag.isEmpty()){
                Platform.runLater(() -> {
                    textArea_info.appendText(getAllEncode.getTime()+"\n[+]"+type+" 加密成功!\n");
                    textArea_encrypt.appendText(flag);
                });
                return true;
            }
        } catch (Exception e) {
            Platform.runLater(() -> {
                textArea_info.appendText(getAllEncode.getTime()+"\n[-]"+type+" 加密失败!\n");
            });
            e.printStackTrace();
        }
        return false;
    }
    public static String zyDecrypt(String passwd) throws Exception {
        try {
            String input = passwd;
            String base64String = input.substring(5); // 去掉前五位
            byte[] decodedBytes = Base64.getDecoder().decode(base64String); // 进行base64解码
            String decodedString = new String(decodedBytes, StandardCharsets.UTF_8);
            char secondChar = input.charAt(1); // 获取解密后的字符串中的第二位字符
            int numericValue = Character.getNumericValue(secondChar);
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < decodedString.length(); i++) {
                result.append((char) (decodedString.charAt(i) - numericValue));
            }
            return result.toString();
        }catch (Exception e){
            return null;
        }
    }
    public static String zyEncrypt(String input) throws Exception {
        try {
            int numericValue = Character.getNumericValue('1');
            StringBuilder encodedString = new StringBuilder();
            for (int i = 0; i < input.length(); i++) {
                encodedString.append((char) (input.charAt(i) + numericValue));
            }
            String base64String = Base64.getEncoder().encodeToString(encodedString.toString().getBytes(StandardCharsets.UTF_8));
            return "/1.0/" + base64String;
        } catch (Exception e) {
            return null;
        }
    }

}