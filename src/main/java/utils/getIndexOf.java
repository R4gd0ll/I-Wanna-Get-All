package utils;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class getIndexOf {
    public static int getLineNumber(String text, String targetChar) {
        int index = text.indexOf(targetChar);
        if (index != -1) {
            int lineNumber = 1;
            for (int i = 0; i < index; i++) {
                if (text.charAt(i) == '\n') {
                    lineNumber++;
                }
            }
            return lineNumber;
        }
        return -1;
    }

    public static String getMessage(String text, String start,int staset , String end,int endset) {
        int startIndex = text.indexOf(start)+staset;
        int endIndex = text.indexOf(end)+endset;
        String result = text.substring(startIndex,endIndex);
        return result;
    }

    public static String getMessage_useStr(String message,String start,int staset, String end , int endset){
        String[] lines = message.split("\\r?\\n");
        int startLine = getLineNumber(message, start)+staset;
        int endLine =  getLineNumber(message, end)+endset;

        List<String> echoList = new ArrayList<>();
        // 输出每一行
        if (startLine > 0 && startLine <= lines.length && endLine > 0 && endLine <= lines.length && startLine <= endLine) {
            // 输出指定行数范围的内容
            for (int i = startLine - 1; i <= endLine - 1; i++) {
                echoList.add(lines[i]);
            }
        }
        String resultlist = String.join("\n", echoList);
        return resultlist;
    }

    public static String getMessage_useLine(String message,int staset,  int endset){
        String[] lines = message.split("\\r?\\n");
        int startLine = staset;
        int endLine =  endset;

        List<String> echoList = new ArrayList<>();
        // 输出每一行
        if (startLine > 0 && startLine <= lines.length && endLine > 0 && endLine <= lines.length && startLine <= endLine) {
            // 输出指定行数范围的内容
            for (int i = startLine - 1; i <= endLine - 1; i++) {
                echoList.add(lines[i]);
            }
        }
        String resultlist = String.join("\n", echoList);
        return resultlist;
    }



    public static String getXMLStr(String xml,String lableMain,String... strings){
        try {
            // 创建一个DocumentBuilderFactory对象
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource inputSource = new InputSource(new StringReader(xml));
            Document document = builder.parse(inputSource);
            Element rootElement = document.getDocumentElement();
            Element Element1 = (Element) rootElement.getElementsByTagName(lableMain).item(0);
            Element Elementn = Element1;
            for(String label: strings){
                Element Elementn1 = (Element) Elementn.getElementsByTagName(label).item(0);
                Elementn = Elementn1;
            }

            String xmlStr = Elementn.getTextContent();

            return xmlStr;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getType(String filename){
        String pattern = "\\.([^.]+)$";

        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(filename);

        if (matcher.find()) {
            String extension = matcher.group(1);
            return filename;
        }
        return null;
    }

    public static String getHTMLStr(String html,String label){
        try{
            org.jsoup.nodes.Document doc = Jsoup.parse(html);
            //<td id="alltd" width="15%"> --> td#alltd[width="15%"]
            Pattern pattern = Pattern.compile("<(\\w+)\\s+.*.=\"(.*?)\"(.*?)>");
            Matcher matcher = pattern.matcher(label);
            String convertedTag = null;
            if (matcher.find()) {
                String tagName = matcher.group(1);
                String id = matcher.group(2);
                String otherAttributes = matcher.group(3);
                convertedTag = tagName + "#" + id +"["+ otherAttributes+"]";
            }
            if(convertedTag == null) {
                return null;
            }
            String var = convertedTag;
            Elements elements = doc.select(var);
            String content = null;
            for (org.jsoup.nodes.Element element : elements) {
                content = element.text();
            }

            return content;
        }catch (Exception e){
            return null;
            }
        }


    public static List<String> getHTMLContents(String html, String tag) {
        List<String> contents = new ArrayList<>();
        org.jsoup.nodes.Document doc = Jsoup.parse(html);
        Elements elements = doc.select(tag);
        for (org.jsoup.nodes.Element element : elements) {
            if (element.attr("ColumnName").equals("output")) {
                contents.add(element.text());
            }
        }
        return contents;
    }
}
