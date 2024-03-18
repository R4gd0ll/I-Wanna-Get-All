package decrypt.hongjing;

import core.CryptInterface;
import decrypt.code.getAllEncode;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class hongjing_crypt implements CryptInterface {
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
            String flag = hjDecrypt(text);
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
            String flag = hjEncrypt(text);
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
    public static String hjDecrypt(String passwd) throws Exception {
        try {
            String Key = "";
            if(passwd.contains("~")){
                Key = HrmsCrypt.decode(passwd);
            }else{
                Key = HrmsCrypt.decryptDecode(passwd);
            }


            return Key;
        }catch (Exception e){
            return null;
        }
    }
    public static String hjEncrypt(String passwd) throws Exception {
        try {
            String Key = "密文: "+HrmsCrypt.encode(passwd)+"\n安全密文: "+HrmsCrypt.encryptEncode(passwd);
            return Key;
        } catch (Exception e) {
            return null;
        }
    }

//    public static void main(String[] args) throws Exception {
//        String aa = frDecrypt("___0072002a00670066000a");
//        System.out.println(frDecrypt(frEncrypt(aa)));
//    }
}