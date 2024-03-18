package decrypt.landray;

import core.CryptInterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import decrypt.code.getAllEncode;

public class landray_ht_crypt implements CryptInterface {
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
            String flag = llDecrypt(text,1);
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
            String flag = llEncrypt(text,1);
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
    public static String key = "";
    private static final String ALGORITHM_NAME = "DES";
    private static final String ALGORITHM_MODE_PADDING = "DES/ECB/PKCS5Padding";

    public static String decrypt(String encryptedText, String key) throws Exception {
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
        Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING);
        SecretKeySpec keySpec = new SecretKeySpec(fixKeySize(key), ALGORITHM_NAME);
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    public static String encrypt(String plainText, String key) throws Exception {
        byte[] plainBytes = plainText.getBytes(StandardCharsets.UTF_8);
        Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING);
        SecretKeySpec keySpec = new SecretKeySpec(fixKeySize(key), ALGORITHM_NAME);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encryptedBytes = cipher.doFinal(plainBytes);
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    private static byte[] fixKeySize(String key) {
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        byte[] fixedKey = new byte[8];
        System.arraycopy(keyBytes, 0, fixedKey, 0, Math.min(keyBytes.length, 8));
        return fixedKey;
    }

    public static String llDecrypt(String passwd,int selectnum) throws Exception {
        if(selectnum == 0){
            key = "kmssPropertiesKey";
        }else {
            key = "kmssAdminKey";
        }
        try {
            String decryptedText = decrypt(passwd, key);
            return decryptedText;
        } catch (Exception e) {
            return null;
        }
    }

    public static String llEncrypt(String passwd,int selectnum) throws Exception {
        if(selectnum == 0){
            key = "kmssPropertiesKey";
        }else {
            key = "kmssAdminKey";
        }
        try {
            String encryptedText = encrypt(passwd, key);
            return encryptedText;
        } catch (Exception e) {
            return " [-] 加密失败!";
        }
    }

//    public static void main(String[] args) throws Exception {
//
//        login.do  前台密码
//        landray_crypt des1 = new landray_crypt("kmssPropertiesKey");
//        System.out.println(des1.decrypt("edlR+Pow/ew="));
//        admin.do：后台密码
//        landray_crypt des2 = new landray_crypt("kmssAdminKey");
//        System.out.println(des2.decrypt("JMK83aAgUCrm2fHdvJWIEQ=="));
//    }
}


