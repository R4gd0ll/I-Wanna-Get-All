package decrypt.code;

import core.CryptInterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import org.apache.commons.lang3.StringEscapeUtils;


public class html_code implements CryptInterface {
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
            String flag = htmlDecode(text);
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
            String flag = htmlEncode(text);
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
    private static String htmlDecode(String passwd) throws Exception {
        try {
            return StringEscapeUtils.unescapeHtml4(passwd);
        }catch (Exception e){
            return null;
        }
    }
    private static String htmlEncode(String input) throws Exception {
        try {
            byte[] var = input.getBytes();
            String var1 = "";
            for (byte b : var) {
                var1 += "&#" + b + ";";
            }

            StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < input.length(); i++) {
                char ch = input.charAt(i);
                hexString.append(String.format("%04x", (int) ch)+";");
            }
            String var2 =  hexString.toString().replace("00","&#x");
            return "HTML编码十进制:\n\n"+var1+"\n\n\n\nHTML编码十六进制:\n\n"+var2;
        } catch (Exception e) {
            return null;
        }
    }
    public static void main(String[] argv){
        try {
            System.out.println(htmlDecode("&#x31;&#x32;&#x33;"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
