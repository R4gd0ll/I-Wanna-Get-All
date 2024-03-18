package decrypt.fanruan;

import core.CryptInterface;
import decrypt.code.getAllEncode;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class fanruan_crypt implements CryptInterface {
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
            String flag = frDecrypt(text);
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
            String flag = frEncrypt(text);
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
    public static String frDecrypt(String passwd) throws Exception {
        try {
            final int[] PassWordArray = {19, 78, 10, 15, 100, 213, 43, 23};
            String Key = passwd; // 密文
            if (Key != null && Key.startsWith("___")) {
                Key = Key.substring(3);
                final StringBuilder stringBuilder = new StringBuilder();
                byte Step = 0;
                for (byte i = 0; i <= Key.length() - 4; i += 4) {
                    if (Step == PassWordArray.length) {
                        Step = 0;
                    }
                    final String str = Key.substring(i, i + 4);
                    final int num = Integer.parseInt(str, 16) ^ PassWordArray[Step];
                    stringBuilder.append((char) num);
                    Step++;
                }
                Key = stringBuilder.toString();
            }
            return Key;
        }catch (Exception e){
            return null;
        }
    }
    public static String frEncrypt(String passwd) throws Exception {
        try {
            final int[] PassWordArray = {19, 78, 10, 15, 100, 213, 43, 23};
            String Key = passwd; // 明文
            if (Key != null) {
                final StringBuilder stringBuilder = new StringBuilder();
                byte Step = 0;
                for (byte i = 0; i < Key.length(); i++) {
                    if (Step == PassWordArray.length) {
                        Step = 0;
                    }
                    final int num = (int) Key.charAt(i) ^ PassWordArray[Step];
                    stringBuilder.append(String.format("%04x", num));
                    Step++;
                }
                Key = "___" + stringBuilder.toString();
            }
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