package utils;

import core.CryptInterface;
import decrypt.code.*;
import decrypt.esafenet.esafenet_crypt;
import decrypt.fanruan.fanruan_crypt;
import decrypt.hongjing.hongjing_crypt;
import decrypt.landray.landray_ht_crypt;
import decrypt.landray.landray_qt_crypt;
import decrypt.seeyon.seeyon_crypt;
import decrypt.wanhu.wanhu_crypt;
import decrypt.yonyou.yonyou_crypt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Kinds_Crypt {
    private static ArrayList<String> oaList;

    public Kinds_Crypt() {
        this.OAType();
    }
    public ArrayList<String> getOAList() {
        return oaList;
    }

    public static ArrayList<String> oakinds() {
        oaList = new ArrayList<>();
        oaList.add("用友");
        oaList.add("万户");
        oaList.add("致远");
        oaList.add("蓝凌(前台)");
        oaList.add("蓝凌(后台)");
        oaList.add("帆软");
        oaList.add("宏景");
        oaList.add("亿赛通");
        oaList.add("Classbyte");
        oaList.add("BCEL");
        oaList.add("Url");
        oaList.add("ASCII");
        oaList.add("HTML实体编码");
        oaList.add("Unicode");
        oaList.add("Base64");
        oaList.add("Base64-Gzip");
        oaList.add("Hex");
        oaList.add("Hex-Gzip");
        return oaList;
    }
    public static ObservableList<String> OAType() {
        ArrayList<String> OAlist = oakinds();
        return FXCollections.observableArrayList(OAlist);
    }

    public static CryptInterface getCrypt(String OAName) {
        CryptInterface ei = null;
        if(OAName.contains("用友")){
            ei = new yonyou_crypt();
        } else if(OAName.contains("万户")){
            ei = new wanhu_crypt();
        } else if(OAName.contains("致远")){
            ei = new seeyon_crypt();
        } else if(OAName.contains("帆软")){
            ei = new fanruan_crypt();
        } else if(OAName.contains("蓝凌(前台)")){
            ei = new landray_qt_crypt();
        } else if(OAName.contains("蓝凌(后台)")){
            ei = new landray_ht_crypt();
        } else if(OAName.contains("宏景")){
            ei = new hongjing_crypt();
        } else if(OAName.contains("亿赛通")){
            ei = new esafenet_crypt();
        } else if(OAName.contains("Classbyte")){
            ei = new classbyte_code();
        } else if(OAName.contains("BCEL")){
            ei = new bcel_code();
        } else if(OAName.contains("Url")){
            ei = new url_code();
        } else if(OAName.contains("ASCII")){
            ei = new ascii_code();
        } else if(OAName.contains("HTML实体编码")){
            ei = new html_code();
        } else if(OAName.contains("Unicode")){
            ei = new unicode_code();
        }  else if(OAName.contains("Base64")&&!OAName.contains("-")){
            ei = new base64_code();
        } else if(OAName.contains("Base64-Gzip")){
            ei = new base64gzip_code();
        } else if(OAName.contains("Hex")&&!OAName.contains("-")){
            ei = new hex_code();
        } else if(OAName.contains("Hex-Gzip")){
            ei = new hexgzip_code();
        }
        return ei;
    }
}
