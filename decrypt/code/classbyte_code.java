package decrypt.code;

import core.CryptInterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import org.jetbrains.java.decompiler.main.decompiler.ConsoleDecompiler;
import org.jetbrains.java.decompiler.main.extern.IFernflowerPreferences;
import utils.Methods;

import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

public class classbyte_code implements CryptInterface {
    private static File file = null;
    private static String temp = "R4gd0ll";
    private static String content = null;
    @Override
    public Boolean decrypt(String text,String type ,TextArea textArea_decrypt,TextArea textArea_info) {
        return dec(text,type,textArea_decrypt,textArea_info);
    }

    @Override
    public Boolean encrypt(String text,String type, TextArea textArea_encrypt,TextArea textArea_info) {
        return enc(text,type,textArea_encrypt,textArea_info);
    }

    private Boolean dec(String text,String type,TextArea textArea_decrypt,TextArea textArea_info){
        try {
            String sourcetype = text.startsWith("yv66")?"Base64":"GZip+Base64";
            String flag = classbyteDecode(text);
            if(flag!=null && flag!="" && !flag.isEmpty()){

                Platform.runLater(() -> {
                    textArea_info.appendText(getAllEncode.getTime()+"\n[+]"+type+",源码为"+sourcetype+"编码 解密成功!\n");
                    textArea_decrypt.appendText(flag);
                });
                return true;
            }
        } catch (Exception e) {
            Platform.runLater(() -> {
                textArea_info.appendText(getAllEncode.getTime()+"\n[-]"+type+" 解密失败!\n");
            });
            e.printStackTrace();
        }
        Platform.runLater(() -> {
            textArea_info.appendText(getAllEncode.getTime()+"\n[-]"+type+" 解密失败!\n");
        });
        return false;
    }

    private Boolean enc(String text,String type ,TextArea textArea_encrypt,TextArea textArea_info){
        try {
            String flag = classbyteEncode(text);
            if(flag!=null && flag!="" && !flag.isEmpty()){
                Platform.runLater(() -> {
                    textArea_info.appendText(getAllEncode.getTime()+"\n[+]"+type+" 加密成功!\n");
                    textArea_encrypt.appendText(flag);
                });
                return true;
            }
        } catch (Exception e) {
            Platform.runLater(() -> {
                textArea_info.appendText(getAllEncode.getTime()+"\n[-]"+type+" 加密失败!\n");
            });
            e.printStackTrace();
        }
        return false;
    }
    private static String classbyteDecode(String passwd) throws Exception {
        Map<String, Object> options = new HashMap<>();
        options.put(IFernflowerPreferences.DECOMPILE_INNER, "1");
        options.put(IFernflowerPreferences.REMOVE_SYNTHETIC, "1");
        options.put(IFernflowerPreferences.ASCII_STRING_CHARACTERS, "1");
        if(passwd.startsWith("yv66")){
            byte[] classBytes = java.util.Base64.getDecoder().decode(passwd);
            passwd = temp+".class";
            file = new File(passwd);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            try {
                outputStream.write(classBytes);
                FileOutputStream fileOutputStream = new FileOutputStream(passwd);
                outputStream.writeTo(fileOutputStream);
                fileOutputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if(passwd.startsWith("H4s")){
            byte[] b64classBytes = java.util.Base64.getDecoder().decode(passwd);
            byte[] classBytes = yso.payloads.util.Methods.gzipDecompress(b64classBytes);
            passwd = temp+".class";
            file = new File(passwd);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            try {
                outputStream.write(classBytes);
                FileOutputStream fileOutputStream = new FileOutputStream(passwd);
                outputStream.writeTo(fileOutputStream);
                fileOutputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            file = new File(passwd);
        }
        ConsoleDecompiler decompiler = new ConsoleDecompiler(null, options);

        try {
            decompiler.addSpace(file, true);
            decompiler.decompileContext();
            String javapwd = "";
            if(passwd == temp+".class"){
                javapwd = temp+".java";
                file.delete();
            }else{
                javapwd = file.getName().replace(".class","")+".java";
            }
            file = new File(javapwd);
            content = Methods.readFile(javapwd);
            new File(javapwd).delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        new File(temp+".class").delete();
        return content;
    }
    private static String classbyteEncode(String input) throws Exception {
        try {
            File file = new File(input);
            URL url = file.toURI().toURL();

            URLClassLoader classLoader = new URLClassLoader(new URL[]{url});
            Class<?> clazz = classLoader.loadClass(file.getName().replace(".class",""));
            byte[] var1 = yso.payloads.util.Methods.classAsBytes(clazz);
            String var2 = java.util.Base64.getEncoder().encodeToString(var1);
            String var3 = java.util.Base64.getEncoder().encodeToString(yso.payloads.util.Methods.gzipEncompress(var1));
            if(var2.startsWith("yv66") && var3.startsWith("H4s")){
                return "Base64编码:\n\n"+var2+"\n\n\n\nGZip+Base64编码:\n\n"+var3;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

}