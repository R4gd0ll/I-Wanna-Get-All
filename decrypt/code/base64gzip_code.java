package decrypt.code;

import yso.payloads.util.Methods;
import core.CryptInterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class base64gzip_code implements CryptInterface {
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
            String flag = base64gzipDecode(text);
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
            String flag = base64gzipEncode(text);
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
    private static String base64gzipDecode(String passwd) throws Exception {
        try {
            byte[] var = Methods.decodeBase64(passwd);
            String decodedString = new String(Methods.gzipDecompress(var));
            return decodedString;
        }catch (Exception e){
            return null;
        }
    }
    private static String base64gzipEncode(String input) throws Exception {
        try {
            byte[] var = Methods.gzipEncompress(input.getBytes());
            String var1 = java.util.Base64.getEncoder().encodeToString(var);
            return var1;
        } catch (Exception e) {
            return null;
        }
    }

}