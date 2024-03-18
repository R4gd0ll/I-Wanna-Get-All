package decrypt.code;

import core.CryptInterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

import javax.xml.bind.DatatypeConverter;
import java.util.Locale;

public class hex_code implements CryptInterface {
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
            String flag = hexDecode(text);
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
            String flag = hexEncode(text);
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
    private static String hexDecode(String passwd) throws Exception {
        try {
            byte[] decodedBytes = DatatypeConverter.parseHexBinary(passwd);

            // 将解码后的字节数组转换为字符串
            String decodedString = new String(decodedBytes);
            return decodedString;
        }catch (Exception e){
            return null;
        }
    }
    private static String hexEncode(String input) throws Exception {
        try {
            return getAllEncode.StringToHex(input).toUpperCase(Locale.ROOT);
        } catch (Exception e) {
            return null;
        }
    }

}