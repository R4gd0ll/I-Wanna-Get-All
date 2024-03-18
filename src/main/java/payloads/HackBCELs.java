package payloads;

import me.gv7.woodpecker.bcel.classfile.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class HackBCELs {
    public static byte[] decode(String strBCEL) throws IOException {
        strBCEL = strBCEL.replace("$$BCEL$$","");
        byte[] clazzBytes = me.gv7.woodpecker.bcel.classfile.Utility.decode(strBCEL,true);
        return clazzBytes;
    }

    public static String encode(String classFilePath) throws Exception{
        byte[] clazzBytes = getFileBytes(classFilePath);
        return encode(clazzBytes);
    }

    public static String encode(byte[] clazzBytes) throws IOException {
        return "$$BCEL$$" + Utility.encode(clazzBytes,true);
    }

    public static byte[] getFileBytes(String file) throws Exception {
        File f = new File(file);
        int length = (int) f.length();
        byte[] data = new byte[length];
        new FileInputStream(f).read(data);
        return data;
    }
}
