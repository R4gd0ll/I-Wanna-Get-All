package decrypt.code;

import core.CryptInterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class unicode_code implements CryptInterface {
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
            String flag = unicodeDecode(text);
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
            String flag = unicodeEncode(text);
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
    private static String unicodeDecode(String passwd) throws Exception {
        try {

            return getAllEncode.getU_Decode(passwd);
        }catch (Exception e){
            return null;
        }
    }
    private static String unicodeEncode(String input) throws Exception {
        try {
            return getAllEncode.getU_Encode(input);
        } catch (Exception e) {
            return null;
        }
    }

}