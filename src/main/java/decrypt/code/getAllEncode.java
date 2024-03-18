package decrypt.code;

import org.apache.commons.lang3.StringEscapeUtils;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class getAllEncode {
    public static String getTime(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        return formattedDateTime+" ";
    }

    public static String getMD5_16(String message) {

        try {
            // 创建MD5消息摘要对象
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 计算消息的摘要
            byte[] digest = md.digest(message.getBytes());

            // 将摘要转换为十六进制字符串
            String hexString = bytesToHex(digest).substring(0,16);

            return hexString;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getMD5_32(String message) {

        try {
            // 创建MD5消息摘要对象
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 计算消息的摘要
            byte[] digest = md.digest(message.getBytes());

            // 将摘要转换为十六进制字符串
            String hexString = bytesToHex(digest);

            return hexString;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static String StringToHex(String str) {
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            hexString.append(String.format("%04x", (int) ch));
        }
        return hexString.toString().replace("00","");
    }

    public static String getU_Encode(String message){
        StringBuilder unicodeStr = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            char ch = message.charAt(i);
            unicodeStr.append("\\u").append(String.format("%04x", (int) ch));
        }
        return unicodeStr.toString();
    }

    public static String getU_Decode(String message){
        return StringEscapeUtils.unescapeJava(message);
    }

    public static String getAllUrlEncode(String input,String type) {
        StringBuilder encodedString = new StringBuilder();
        if(type.contains("char")){
            for (char c : input.toCharArray()) {
                if (Character.isLetter(c)) {
                    encodedString.append(c);
                } else {
                    String encodeChar = "%" + Integer.toHexString((int) c);
                    encodedString.append(encodeChar);
                }
            }
            return encodedString.toString();
        }else if(type.contains("int")){
            for (char i : input.toCharArray()) {
                if (Character.isDigit(i)) {
                    encodedString.append(i);
                } else {
                    String encodeChar = "%" + Integer.toHexString((int) i);
                    encodedString.append(encodeChar);
                }
            }
            return encodedString.toString();
        }else if(type.contains("all")){
            for (char a : input.toCharArray()) {
                String encodeChar = "%" + Integer.toHexString((int) a);
                encodedString.append(encodeChar);
            }
            return encodedString.toString();
        }else{
            return URLEncoder.encode(input);
        }
    }

}
