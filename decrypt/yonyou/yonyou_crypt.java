package decrypt.yonyou;

import core.CryptInterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import decrypt.code.getAllEncode;

public class yonyou_crypt implements CryptInterface {
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
            String flag =  Decode(text);
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

    private Boolean enc(String text,String type, TextArea textArea_encrypt,TextArea textArea_info){
        try {
            String customKey = "1231234234";
            long key = 1231234234L;
            if (customKey != null) {
                key = Long.parseLong(customKey);
            }
            String flag = Encode.encode(text,key);
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
        Platform.runLater(() -> {
            textArea_info.appendText(getAllEncode.getTime()+"\n[-]"+type+" 加密失败!\n");
        });
        return false;
    }

    public static String Decode(String passwd) throws Exception {
        try {
            String password = passwd;
            String customKey = "1231234234";
            long key = 1231234234L;
            if (customKey != null) {
                key = Long.parseLong(customKey);
            }
            Encode encoder = new Encode();
            String plainText = encoder.decode(password, key);
            return plainText;
        }catch (Exception e){
            return null;
        }
    }


}
