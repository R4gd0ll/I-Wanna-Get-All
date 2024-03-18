package utils;

import Memshell.esafenet_Inject_Memshell;
import Memshell.yonyouU8C_Inject_Memshell;
import Memshell.yonyou_Inject_Memshell;
import core.MemShellInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Kinds_Mem {
    private ArrayList<String> MemList;
    private ArrayList<String> kindList;
    private ArrayList<String> printList;

    /**
     * 构造器初始化数据
     */
    public Kinds_Mem() {
        this.kinds();
        this.Memkinds();
    }


    public ArrayList<String> getKindList() {
        return kindList;
    }
    public ObservableList<String> getFXKindList() {
        return FXCollections.observableArrayList(kindList);
    }
    public ArrayList<String> getMemList() {
        return MemList;
    }



    /**
     * 初始化默认数据.
     *
     * @return 选项集合.
     */
    public ArrayList<String> kinds() {
        kindList = new ArrayList<>();
        kindList.add("用友NC");
        kindList.add("亿赛通");
        kindList.add("用友U8C");
        return kindList;
    }


    public static ObservableList<String> yonyouNc() {
        ArrayList<String> yyNC = new ArrayList<>();
        yyNC.add("ActionHandlerServlet反序列化");
        yyNC.add("DeleteServlet反序列化");
        yyNC.add("ConfigResourceServlet反序列化");
        yyNC.add("DcUpdateServiceImpl反序列化");
        yyNC.add("DownloadServlet反序列化");
        yyNC.add("ECFileManageServlet反序列化");
        yyNC.add("FileParserServlet反序列化");
        yyNC.add("FileReceiveServlet反序列化");
        yyNC.add("fsDownloadServlet反序列化");
        yyNC.add("JiuQiClientReqDispatch反序列化");
        yyNC.add("LoggingConfigServlet反序列化");
        yyNC.add("ModelHandleServlet反序列化");
        yyNC.add("MonitorServlet反序列化");
        yyNC.add("MxServlet反序列化");
        yyNC.add("NCMessageServlet反序列化");
        yyNC.add("OAContactsFuzzySearchServlet反序列化");
        yyNC.add("OAUserAuthenticationServlet反序列化");
        yyNC.add("OAUserQryServlet反序列化");
        yyNC.add("UploadServlet反序列化");
        yyNC.add("XbrlPersistenceServlet反序列化");
//        yyNC.add("反序列化");
        return FXCollections.observableArrayList(yyNC);
    }

    public static ObservableList<String> yonyouU8C() {
        ArrayList<String> yyU8C = new ArrayList<>();
        yyU8C.add("CacheInvokeServlet反序列化");
        yyU8C.add("FileTransportServlet反序列化");
        yyU8C.add("TableInputOperServlet反序列化");
        yyU8C.add("ServletCommander反序列化");
        yyU8C.add("LoginVideoServlet反序列化");
        yyU8C.add("LoggingConfigServlet反序列化");
        yyU8C.add("MonitorServlet反序列化");
        yyU8C.add("MxServlet反序列化");
        yyU8C.add("FileManageServlet反序列化");
        yyU8C.add("ESBInvokerServlet反序列化");
//        yyU8C.add("反序列化");
        return FXCollections.observableArrayList(yyU8C);
    }

    public static ObservableList<String> esafe() {
        ArrayList<String> esafenet = new ArrayList<>();
        esafenet.add("CheckClientServelt反序列化");
        esafenet.add("EmailAuditService反序列化");
        esafenet.add("GetValidateLoginUserService反序列化");
        esafenet.add("SystemService反序列化");
        esafenet.add("DecryptionApp反序列化");
        esafenet.add("outgoingServlet反序列化");
        esafenet.add("docRenewApp反序列化");
        esafenet.add("PrintLimitApp反序列化");
        esafenet.add("offlineApp反序列化");
        esafenet.add("OutgoingRestoreApp反序列化");
        esafenet.add("permissionApp反序列化");
        esafenet.add("MailApp反序列化");
        esafenet.add("FileAuditService反序列化");
        esafenet.add("MailMessageLogServices反序列化");
        esafenet.add("clientMessage反序列化");
        esafenet.add("FileCountService反序列化");
        esafenet.add("DecryPermissApp反序列化");
        esafenet.add("PrintAuditService反序列化");
        esafenet.add("ODMSubmitApplyService反序列化");
        esafenet.add("UpdateClientStatus反序列化");
        esafenet.add("UpgradeService1反序列化");
        esafenet.add("GetValidateAuthCodeService反序列化");
        esafenet.add("UserLoginOutService1反序列化");
        esafenet.add("GetUserSafetyPolicyService反序列化");
        esafenet.add("GetMakeOutSendFileInfoService反序列化");
        esafenet.add("UpgradeService2反序列化");
        esafenet.add("UpdatePasswordService反序列化");
        esafenet.add("GetUsecPolicyService反序列化");
        esafenet.add("GetValidateServerService反序列化");
        esafenet.add("UninstallApplicationService1反序列化");
        esafenet.add("OfflineApplicationService2反序列化");
        esafenet.add("DecryptApplicationService1反序列化");
        esafenet.add("OfflineApplicationService1反序列化");
        esafenet.add("document反序列化");
        esafenet.add("AutoSignService1反序列化");
        esafenet.add("CDGAuthoriseTempletService1反序列化");
        esafenet.add("CreateDocService1反序列化");
        esafenet.add("SecureUsbConnection反序列化");
        esafenet.add("ExamCDGDocService1反序列化");
        return FXCollections.observableArrayList(esafenet);
    }


    public ArrayList<String> Memkinds() {
        MemList = new ArrayList<>();
        MemList.add("冰蝎3.0");
        MemList.add("哥斯拉");
        MemList.add("蚁剑");
        MemList.add("suo5");
        MemList.add("cmdEcho");
        MemList.add("NeoreGeorg");
        MemList.add("define");
        return MemList;
    }
    public ObservableList<String> Mem() {
        ArrayList<String> Memtest = Memkinds();
        return FXCollections.observableArrayList(Memtest);
    }

    public ArrayList<String> printkinds() {
        printList = new ArrayList<>();
        printList.add("source");
        printList.add("base64");
        printList.add("hex");
        printList.add("gzip-base64");
        printList.add("gzip-hex");
        return printList;
    }
    public ObservableList<String> pri() {
        ArrayList<String> pritest = printkinds();
        return FXCollections.observableArrayList(pritest);
    }

    public static MemShellInterface getMemShell(String vulName,String memshelltype) {
        MemShellInterface mi = null;

        if(vulName.contains("用友NC")) {
            mi = new yonyou_Inject_Memshell();
        }else if(vulName.contains("亿赛通")){
            mi = new esafenet_Inject_Memshell();
        }else if(vulName.contains("用友U8C")){
            mi = new yonyouU8C_Inject_Memshell();
        }


        return mi;
    }
}
