package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;

public class HttpTools_getTime {
    public static long get(String url, HashMap<String,String> headers, String encoding){
        new Response(0, null, null, null);
        try {
            HttpURLConnection conn = HttpTools.getCoon(url);
            conn.setRequestMethod("GET");
            long startTime = System.currentTimeMillis();
            Iterator var4 = headers.keySet().iterator();

            while(var4.hasNext()) {
                String key = (String)var4.next();
                conn.setRequestProperty(key, headers.get(key));
            }

            HttpTools.getResponse(conn, encoding);
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            if(conn.getResponseCode()!=0 && conn.getResponseCode()!=404){
                return duration;
            }

        } catch (Exception e) {
            System.out.println("连接异常");
        }
        return 0;
    }

    public static long post(String url, String postString, HashMap<String, String> headers, String encoding) {
        new Response(0, null, null, null);
        try {
            HttpURLConnection conn = HttpTools.getCoon(url);
            conn.setRequestMethod("POST");
            long startTime = System.currentTimeMillis();
            Iterator var5 = headers.keySet().iterator();

            while(var5.hasNext()) {
                String key = (String)var5.next();
                conn.setRequestProperty(key, headers.get(key));
            }

            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(postString.getBytes());
            outputStream.flush();
            outputStream.close();
            HttpTools.getResponse(conn, encoding);
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            if(conn.getResponseCode()!=0 && conn.getResponseCode()!=404){
                return duration;
            }
        } catch (Exception e) {
            System.out.println("连接异常");
        }
        return 0;
    }
}
