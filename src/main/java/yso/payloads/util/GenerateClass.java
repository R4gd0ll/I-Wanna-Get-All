package R4g;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtField.Initializer;
import javassist.bytecode.ClassFile;

import java.util.function.Consumer;

public class GenerateClass {
    private static final ClassPool POOL = ClassPool.getDefault();

    public GenerateClass() {
    }

    public static byte[] generateClass(Class<?> var0, String var1) {
        return generateClass(var0, var1, (var0x) -> {
        });
    }

    public static byte[] generateClass(Class<?> var0, String var1, Consumer<CtClass> var2) {
        try {
            CtClass var3 = POOL.getCtClass(var0.getName());
            var3.defrost();
            var2.accept(var3);
            var3.setName(var1);
            ClassFile var4 = var3.getClassFile();
            var4.setMajorVersion(51);
            var4.removeAttribute("SourceFile");
            return var3.toBytecode();
        } catch (Exception var5) {
            return null;
        }
    }

    public static void modifyClassFieldString(CtClass var0, String var1, String var2) {
        try {
            CtField var3 = var0.getDeclaredField(var1);
            var0.removeField(var3);
            CtField var4 = new CtField(var3.getType(), var3.getName(), var3.getDeclaringClass());
            var4.setModifiers(var3.getModifiers());
            var0.addField(var4, Initializer.constant(var2));
        } catch (Exception var5) {
        }

    }
}
