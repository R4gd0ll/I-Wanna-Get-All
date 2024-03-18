package decrypt.hongjing;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class HrmsCrypt {
    public static final String ALGORITHM_DES = "DES/CBC/PKCS5Padding";
    public static final String keypassword = "ilovethisgame";


    public static void main(String[] argv) {
            String var1 = "-e";
            String var2 = "123";
            if ("-e".equals(var1)) {
                System.out.println("safe-encode: " + encode(var2));
                System.out.println("encrypt: " + encryptEncode(var2));
            } else if ("-d".equals(var1)) {
                System.out.println("safe-decode: " + decode(var2));
                System.out.println("decrypt: " + decryptDecode(var2));
            } else {

            }

    }


    public static String encryptEncode(String var0) {
        if (null == var0) {
            return "";
        } else {
            String var1 = encrypt(var0);
            var1 = var1.replaceAll("%", "@2HJ5@");
            var1 = var1.replaceAll("\\+", "@2HJB@");
            var1 = var1.replaceAll(" ", "@2HJ0@");
            var1 = var1.replaceAll("\\/", "@2HJF@");
            var1 = var1.replaceAll("\\?", "@3HJF@");
            var1 = var1.replaceAll("#", "@2HJ3@");
            var1 = var1.replaceAll("&", "@2HJ6@");
            var1 = var1.replaceAll("=", "@3HJD@");
            var1 = var1.replaceAll("\r\n", "").replaceAll("\n", "").replaceAll("\r", "");
            var1 = var1.replaceAll("@", "PAATTP");
            return var1;
        }
    }

    public static String decryptDecode(String var0) {
        if (null == var0) {
            return "";
        } else {
            var0 = var0.replaceAll("PAATTP", "@");
            var0 = var0.replaceAll("@2HJ5@", "%");
            var0 = var0.replaceAll("@2HJB@", "\\+");
            var0 = var0.replaceAll("@2HJ0@", " ");
            var0 = var0.replaceAll("@2HJF@", "\\/");
            var0 = var0.replaceAll("@3HJF@", "\\?");
            var0 = var0.replaceAll("@2HJ3@", "#");
            var0 = var0.replaceAll("@2HJ6@", "&");
            var0 = var0.replaceAll("@3HJD@", "=");
            String var1 = decrypt(var0);
            return var1;
        }
    }

    public static String encrypt(String var0) {
        return encrypt("ilovethisgame", var0);
    }

    public static String decrypt(String var0) {
        return decrypt("ilovethisgame", var0);
    }

    public static String encrypt(String var0, String var1) {
        return encrypt(var0, var1.getBytes());
    }

    public static String encrypt(String var0, byte[] var1) {
        String var2 = "";

        try {
            byte[] var3 = new byte[]{1, 2, 3, 4, 5, 6, 7, 8};
            DESKeySpec var4 = new DESKeySpec(var0.getBytes());
            SecretKeyFactory var5 = SecretKeyFactory.getInstance("DES");
            SecretKey var6 = var5.generateSecret(var4);
            Cipher var7 = Cipher.getInstance("DES/CBC/PKCS5Padding");
            IvParameterSpec var8 = new IvParameterSpec(var3);
            var7.init(1, var6, var8);
            byte[] var9 = var7.doFinal(var1);
            BASE64Encoder var10 = new BASE64Encoder();
            var2 = new String(var10.encode(var9));
        } catch (Exception var11) {
        }

        return var2;
    }

    public static String decrypt(String var0, String var1) {
        String var2 = "";

        try {
            BASE64Decoder var3 = new BASE64Decoder();
            byte[] var4 = var3.decodeBuffer(var1);
            byte[] var5 = new byte[]{1, 2, 3, 4, 5, 6, 7, 8};
            DESKeySpec var6 = new DESKeySpec(var0.getBytes());
            SecretKeyFactory var7 = SecretKeyFactory.getInstance("DES");
            SecretKey var8 = var7.generateSecret(var6);
            Cipher var9 = Cipher.getInstance("DES/CBC/PKCS5Padding");
            IvParameterSpec var10 = new IvParameterSpec(var5);
            var9.init(2, var8, var10);
            byte[] var11 = var9.doFinal(var4);
            var2 = new String(var11, "GBK");
        } catch (Exception var12) {
        }

        return var2;
    }

    public static String compressBase64(String var0) {
        String var1 = compress(var0);
        var1 = convertTo64Base(var1);
        return var1;
    }

    public static String decompressBase64(String var0) {
        String var1 = convert64BaseToString(var0);
        var1 = decompress(var1);
        return var1;
    }

    public static String compress(String var0) {
        if (var0 != null && var0.length() != 0) {
            String var1 = "";

            try {
                ByteArrayOutputStream var2 = new ByteArrayOutputStream();
                GZIPOutputStream var3 = new GZIPOutputStream(var2);
                var3.write(var0.getBytes());
                var3.close();
                var1 = var2.toString("ISO-8859-1");
            } catch (Exception var4) {
            }

            return var1;
        } else {
            return var0;
        }
    }

    public static String decompress(String var0) {
        if (var0 != null && var0.length() != 0) {
            String var1 = "";

            try {
                ByteArrayOutputStream var2 = new ByteArrayOutputStream();
                ByteArrayInputStream var3 = new ByteArrayInputStream(var0.getBytes("ISO-8859-1"));
                GZIPInputStream var4 = new GZIPInputStream(var3);
                byte[] var5 = new byte[256];

                int var6;
                while((var6 = var4.read(var5)) >= 0) {
                    var2.write(var5, 0, var6);
                }

                var1 = var2.toString();
            } catch (Exception var7) {
            }

            return var1;
        } else {
            return var0;
        }
    }

    public static String convertTo64Base(String var0) {
        StringBuffer var1 = new StringBuffer();
        if (var0 == null) {
            var0 = "";
        }

        BASE64Encoder var2 = new BASE64Encoder();

        try {
            var1.append(var2.encode(var0.getBytes("ISO-8859-1")));
        } catch (Exception var4) {
        }

        return var1.toString();
    }

    public static String convertTo64Base(String var0, String var1) {
        StringBuffer var2 = new StringBuffer();
        if (var0 == null) {
            var0 = "";
        }

        BASE64Encoder var3 = new BASE64Encoder();

        try {
            var2.append(var3.encode(var0.getBytes(var1)));
        } catch (Exception var5) {
        }

        return var2.toString();
    }

    public static String convert64BaseToString(String var0) {
        StringBuffer var1 = new StringBuffer();
        if (var0 == null) {
            var0 = "";
        }

        BASE64Decoder var2 = new BASE64Decoder();
        String var3 = null;

        try {
            byte[] var4 = var2.decodeBuffer(var0);
            var3 = new String(var4, "ISO-8859-1");
            var1.append(var3);
        } catch (Exception var5) {
        }

        return var1.toString();
    }

    public static String convert64BaseToString(String var0, String var1) {
        StringBuffer var2 = new StringBuffer();
        if (var0 == null) {
            var0 = "";
        }

        BASE64Decoder var3 = new BASE64Decoder();
        String var4 = null;

        try {
            byte[] var5 = var3.decodeBuffer(var0);
            var4 = new String(var5, var1);
            var2.append(var4);
        } catch (Exception var6) {
        }

        return var2.toString();
    }

    public static String DecBase64(String var0) {
        StringBuffer var1 = new StringBuffer();
        if (var0 == null) {
            var0 = "";
        }

        BASE64Decoder var2 = new BASE64Decoder();
        String var3 = null;

        try {
            byte[] var4 = var2.decodeBuffer(var0);
            var3 = new String(var4, "UTF-8");
            var1.append(var3);
        } catch (Exception var5) {
        }

        return var1.toString();
    }

    public static final String decode(String var0) {
        if (var0 == null) {
            return "";
        } else {
            String var1 = "";

            for(int var2 = 0; var2 < var0.length(); ++var2) {
                char var3;
                switch(var3 = var0.charAt(var2)) {
                    case '^':
                        String var4 = var0.substring(var2 + 1, var2 + 4 + 1);
                        var1 = var1 + (char)Integer.parseInt(var4, 16);
                        var2 += 4;
                        break;
                    case '~':
                        String var5 = var0.substring(var2 + 1, var2 + 4 - 1);
                        var1 = var1 + (char)Integer.parseInt(var5, 16);
                        var2 += 2;
                        break;
                    default:
                        var1 = var1 + var3;
                }
            }

            return var1;
        }
    }

    public static final String encode_v1(String var0) {
        if (var0 == null) {
            return "";
        } else {
            String var1 = "";

            for(int var2 = 0; var2 < var0.length(); ++var2) {
                char var3;
                String var4;
                int var5;
                if ((var3 = var0.charAt(var2)) > 255) {
                    for(var5 = (var4 = Integer.toString(var3, 16)).length(); var5 < 4; ++var5) {
                        var4 = "0" + var4;
                    }

                    var1 = var1 + "^" + var4;
                } else {
                    for(var5 = (var4 = Integer.toString(var3, 16)).length(); var5 < 2; ++var5) {
                        var4 = "0" + var4;
                    }

                    var1 = var1 + "~" + var4;
                }
            }

            return var1;
        }
    }

    public static final String encode(String var0) {
        if (var0 == null) {
            return "";
        } else {
            String var1 = "";

            for(int var2 = 0; var2 < var0.length(); ++var2) {
                char var3;
                String var4;
                int var5;
                if ((var3 = var0.charAt(var2)) > 255) {
                    for(var5 = (var4 = Integer.toString(var3, 16)).length(); var5 < 4; ++var5) {
                        var4 = "0" + var4;
                    }

                    var1 = var1 + "^" + var4;
                } else if (var3 >= '0' && (var3 <= '/' || var3 >= 'A') && (var3 <= 'Z' || var3 >= 'a') && var3 <= 'z') {
                    var1 = var1 + var3;
                } else {
                    for(var5 = (var4 = Integer.toString(var3, 16)).length(); var5 < 2; ++var5) {
                        var4 = "0" + var4;
                    }

                    var1 = var1 + "~" + var4;
                }
            }

            return var1;
        }
    }

    public static String convertUrlSpecialCharacter(String var0) {
        var0 = var0.replaceAll("%", "%25");
        var0 = var0.replaceAll("\\+", "%2B");
        var0 = var0.replaceAll(" ", "%20");
        var0 = var0.replaceAll("\\/", "%2F");
        var0 = var0.replaceAll("\\?", "%3F");
        var0 = var0.replaceAll("#", "%23");
        var0 = var0.replaceAll("&", "%26");
        var0 = var0.replaceAll("=", "%3D");
        return var0;
    }

    public static String convertCharacterUrlSpecial(String var0) {
        var0 = var0.replaceAll("%25", "%");
        var0 = var0.replaceAll("%2B", "\\+");
        var0 = var0.replaceAll("%20", " ");
        var0 = var0.replaceAll("%2F", "\\/");
        var0 = var0.replaceAll("%3F", "\\?");
        var0 = var0.replaceAll("%23", "#");
        var0 = var0.replaceAll("%26", "&");
        var0 = var0.replaceAll("%3D", "=");
        return var0;
    }

    public static String keyWord_reback(String var0) {
        if (var0 != null && var0.trim().length() != 0) {
            var0 = var0.replaceAll("＜", "<");
            var0 = var0.replaceAll("＞", ">");
            var0 = var0.replaceAll("＂", "\"");
            var0 = var0.replaceAll("＇", "'");
            var0 = var0.replaceAll("；", ";");
            var0 = var0.replaceAll("〔", "(");
            var0 = var0.replaceAll("〕", ")");
            var0 = var0.replaceAll("＋", "+");
            var0 = var0.replaceAll("－－", "--");
            var0 = var0.replaceAll("｜", "|");
            var0 = var0.replaceAll("＄", "\\$");
            var0 = var0.replaceAll("＆", "&");
            var0 = var0.replaceAll("％", "%");
            var0 = var0.replaceAll("＃", "#");
            var0 = var0.replaceAll("＼", "\\\\");
            var0 = var0.replaceAll("？", "?");
            var0 = var0.replaceAll("［", "[");
            var0 = var0.replaceAll("］", "]");
            var0 = var0.replaceAll("＊", "*");
            var0 = var0.replaceAll("／", "/");
            var0 = var0.replaceAll("＝", "=");
            return var0.toString();
        } else {
            return var0;
        }
    }

    public static String keyWord_filter(String var0) {
        if (var0 != null && var0.trim().length() != 0) {
            StringBuffer var1 = new StringBuffer(var0.length());

            for(int var2 = 0; var2 < var0.length(); ++var2) {
                switch(var0.charAt(var2)) {
                    case '"':
                        var1.append("＂");
                        break;
                    case '#':
                        var1.append("＃");
                        break;
                    case '$':
                        var1.append("＄");
                        break;
                    case '%':
                        var1.append("％");
                        break;
                    case '&':
                        var1.append("＆");
                        break;
                    case '\'':
                        var1.append("＇");
                        break;
                    case '(':
                        var1.append("〔");
                        break;
                    case ')':
                        var1.append("〕");
                        break;
                    case '*':
                        var1.append("＊");
                        break;
                    case '+':
                        var1.append("＋");
                        break;
                    case '/':
                        var1.append("／");
                        break;
                    case ';':
                        var1.append("；");
                        break;
                    case '<':
                        var1.append("＜");
                        break;
                    case '=':
                        var1.append("＝");
                        break;
                    case '>':
                        var1.append("＞");
                        break;
                    case '?':
                        var1.append("？");
                        break;
                    case '[':
                        var1.append("［");
                        break;
                    case '\\':
                        var1.append("＼");
                        break;
                    case ']':
                        var1.append("］");
                        break;
                    case '|':
                        var1.append("｜");
                        break;
                    default:
                        var1.append(var0.charAt(var2));
                }
            }

            var0 = replaceSQLkey(var0);
            return var0;
        } else {
            return var0;
        }
    }

    public static String replaceSQLkey(String var0) {
        var0 = var0.replaceAll(" (?i)insert ", "_insert_");
        var0 = var0.replaceAll(" (?i)select ", "_select_");
        var0 = var0.replaceAll(" (?i)master ", "_master_");
        var0 = var0.replaceAll(" (?i)update ", "_update_");
        var0 = var0.replaceAll(" (?i)trancate ", "_trancate_");
        var0 = var0.replaceAll(" (?i)into ", "_into_");
        var0 = var0.replaceAll(" (?i)and ", "_and_");
        var0 = var0.replaceAll(" (?i)or ", "_or_");
        var0 = var0.replaceAll(" (?i)ascii ", "_asciit_");
        var0 = var0.replaceAll(" (?i)exec ", "_exec_");
        var0 = var0.replaceAll(" (?i)execute ", "_execute_");
        var0 = var0.replaceAll(" (?i)drop ", "_drop_");
        var0 = var0.replaceAll(" (?i)delete ", "_delete_");
        return var0;
    }

    public static String filter_xml(String var0) {
        if (var0 != null && var0.trim().length() != 0) {
            var0 = var0.replaceAll("%", "%25");
            var0 = var0.replaceAll("&", "%26amp;");
            var0 = var0.replaceAll("'", "%26apos;");
            var0 = var0.replaceAll("<", "%26lt;");
            var0 = var0.replaceAll(">", "%26gt;");
            var0 = var0.replaceAll("\"", "%26quot;");
            var0 = var0.replaceAll(",", "````");
            return var0.toString();
        } else {
            return var0;
        }
    }

    public static String replace_xml(String var0) {
        if (var0 != null && var0.trim().length() != 0) {
            var0 = var0.replaceAll("%26amp;", "&");
            var0 = var0.replaceAll("%26apos;", "'");
            var0 = var0.replaceAll("%26lt;", "<");
            var0 = var0.replaceAll("%26gt;", ">");
            var0 = var0.replaceAll("%26quot;", "\"");
            var0 = var0.replaceAll("%25", "%");
            return var0.toString();
        } else {
            return var0;
        }
    }
}

