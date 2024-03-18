package decrypt.code;

import core.CryptInterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class ascii_code implements CryptInterface {
    @Override
    public Boolean decrypt(String text, String type , TextArea textArea_decrypt, TextArea textArea_info) {
        return dec(text,type,textArea_decrypt,textArea_info);
    }

    @Override
    public Boolean encrypt(String text,String type, TextArea textArea_encrypt,TextArea textArea_info) {
        return enc(text,type,textArea_encrypt,textArea_info);
    }

    private Boolean dec(String text,String type,TextArea textArea_decrypt,TextArea textArea_info){
        try {
            String flag = asciiDecode(text);
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
            String flag = asciiEncode(text);
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
    private static String asciiDecode(String passwd) throws Exception {
        try {
            if(!passwd.contains(" ") && Integer.parseInt(passwd)>127){
                return null;
            }
            String[] var3 = passwd.split(" ");
            String var5 = "";
            for(String var4 : var3){
                int value = Integer.parseInt(var4);
                if(value<=127){
                    char c = (char) value;
                    var5 += Character.toString(c);
                }else{
                    return null;
                }
            }
            return var5;
        }catch (Exception e){
            return null;
        }
    }
    private static String asciiEncode(String input) throws Exception {
        try {
            byte[] var = input.getBytes();
            String var1 = "";
            for (byte b : var) {
                var1 += b + " ";
            }
            return var1;
        } catch (Exception e) {
            return null;
        }
    }
}
