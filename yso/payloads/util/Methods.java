package yso.payloads.util;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Methods {
    public Methods() {
    }

    public static String classAsFile(Class<?> var0) {
        return classAsFile(var0, true);
    }

    public static String classAsFile(Class<?> var0, boolean var1) {
        String var2;
        if (var0.getEnclosingClass() == null) {
            var2 = var0.getName().replace(".", "/");
        } else {
            var2 = classAsFile(var0.getEnclosingClass(), false) + "$" + var0.getSimpleName();
        }

        if (var1) {
            var2 = var2 + ".class";
        }

        return var2;
    }
    public static String ClassBytetoBase64(Class<?> var0){
        try{
            byte[] classBytes = classAsBytes(var0);
            String var = encodeBase64(classBytes);
            return var;
        }catch (Exception var2){
            throw new RuntimeException(var2);
        }
    }

    public static String ClassBytetoGzipandBase64(Class<?> var0){
        try{
            byte[] classBytes = classAsBytes(var0);
            String var = encodeBase64(gzipEncompress(classBytes));
            return var;
        }catch (Exception var2){
            throw new RuntimeException(var2);
        }
    }

    public static byte[] classAsBytes(Class<?> var0) {
        try {
            byte[] var1 = new byte[1024];
            String var2 = classAsFile(var0);
            InputStream var3 = Methods.class.getClassLoader().getResourceAsStream(var2);
            if (var3 == null) {
                throw new IOException("couldn't find '" + var2 + "'");
            } else {
                ByteArrayOutputStream var4 = new ByteArrayOutputStream();

                int var5;
                while((var5 = var3.read(var1)) != -1) {
                    var4.write(var1, 0, var5);
                }

                return var4.toByteArray();
            }
        } catch (IOException var6) {
            throw new RuntimeException(var6);
        }
    }



    public static byte[] decodeBase64(String base64Str) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class decoderClass;
        try {
            decoderClass = Class.forName("sun.misc.BASE64Decoder");
            return (byte[])((byte[])decoderClass.getMethod("decodeBuffer", String.class).invoke(decoderClass.newInstance(), base64Str));
        } catch (Exception var4) {
            decoderClass = Class.forName("java.util.Base64");
            Object decoder = decoderClass.getMethod("getDecoder").invoke((Object)null);
            return (byte[])((byte[])decoder.getClass().getMethod("decode", String.class).invoke(decoder, base64Str));
        }
    }


    public static byte[] gzipDecompress(byte[] compressedData) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(compressedData);
        GZIPInputStream ungzip = new GZIPInputStream(in);
        byte[] buffer = new byte[256];

        int n;
        while((n = ungzip.read(buffer)) >= 0) {
            out.write(buffer, 0, n);
        }

        return out.toByteArray();
    }

    private static String encodeBase64(byte[] bytes) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class encoderClass;
        try {
            encoderClass = Class.forName("sun.misc.BASE64Encoder");
            return (String) encoderClass.getMethod("encodeBuffer", byte[].class).invoke(encoderClass.newInstance(), bytes);
        } catch (Exception var4) {
            encoderClass = Class.forName("java.util.Base64");
            Object encoder = encoderClass.getMethod("getEncoder").invoke((Object)null);
            return (String) encoder.getClass().getMethod("decode", String.class).invoke(encoder, bytes);
        }
    }

    public static byte[] gzipEncompress(byte[] compressedData) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(out);
        gzip.write(compressedData);
        gzip.close();

        return out.toByteArray();
    }


}
