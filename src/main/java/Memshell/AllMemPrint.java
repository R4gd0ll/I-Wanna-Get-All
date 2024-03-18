package Memshell;


import yso.payloads.util.Methods;

import java.io.ByteArrayOutputStream;

import static Memshell.esafenet_Inject_Memshell.getXMLPayload;

public class AllMemPrint {
    public static String getMemPrint(String oatype,byte[] var,String PrintType){
        String text = null;
        try {
            if(oatype.contains("用友")) {
                if (PrintType.contains("source")) {
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    outputStream.write(var, 0, var.length);
                    text = outputStream.toString();
                } else if (PrintType == "base64") {
                    text = java.util.Base64.getEncoder().encodeToString(var);
                } else if (PrintType == "hex") {
                    text = javax.xml.bind.DatatypeConverter.printHexBinary(var);
                } else if (PrintType == "gzip-base64") {
                    text = java.util.Base64.getEncoder().encodeToString(Methods.gzipEncompress(var));
                } else if (PrintType == "gzip-hex") {
                    text = javax.xml.bind.DatatypeConverter.printHexBinary(Methods.gzipEncompress(var));
                }
            }else{
                if (!PrintType.contains("source")) {
                    text = "";
                }else{
                    text = getXMLPayload(java.util.Base64.getEncoder().encodeToString(var));
                }
            }
            return text;
        }catch (Exception e){

        }
        return text;
    }
}
