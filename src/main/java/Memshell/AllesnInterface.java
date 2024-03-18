package Memshell;

public class AllesnInterface {
    public static String getInterFace(String vulname) {
        String interF = null;
        if(vulname.contains("CheckClientServelt")){
            interF = "/CDGServer3/CheckClientServelt";
        }else if(vulname.contains("EmailAuditService")){
            interF = "/CDGServer3/EmailAuditService";
        }else if(vulname.contains("GetValidateLoginUserService")){
            interF = "/CDGServer3/GetValidateLoginUserService";
        }else if(vulname.contains("SystemService")){
            interF = "/CDGServer3/SystemService?command=GETSYSTEMINFO";
        }else if(vulname.contains("DecryptionApp")){
            interF = "/CDGServer3/DecryptionApp";

        }else if(vulname.contains("outgoingServlet")){
            interF = "/CDGServer3/outgoingServlet";

        }else if(vulname.contains("docRenewApp")){
            interF = "/CDGServer3/docRenewApp";

        }else if(vulname.contains("PrintLimitApp")){
            interF = "/CDGServer3/PrintLimitApp";

        }else if(vulname.contains("offlineApp")){
            interF = "/CDGServer3/offlineApp";

        }else if(vulname.contains("OutgoingRestoreApp")){
            interF = "/CDGServer3/OutgoingRestoreApp";

        }else if(vulname.contains("permissionApp")){
            interF = "/CDGServer3/permissionApp";

        }else if(vulname.contains("MailApp")){
            interF = "/CDGServer3/MailApp";

        }else if(vulname.contains("FileAuditService")){
            interF = "/CDGServer3/FileAuditService";

        }else if(vulname.contains("MailMessageLogServices")){
            interF = "/CDGServer3/MailMessageLogServices";

        }else if(vulname.contains("clientMessage")){
            interF = "/CDGServer3/clientMessage";

        }else if(vulname.contains("FileCountService")){
            interF = "/CDGServer3/FileCountService";

        }else if(vulname.contains("DecryPermissApp")){
            interF = "/CDGServer3/DecryPermissApp";

        }else if(vulname.contains("PrintAuditService")){
            interF = "/CDGServer3/PrintAuditService";

        }else if(vulname.contains("ODMSubmitApplyService")){
            interF = "/CDGServer3/ODMSubmitApplyService";

        }else if(vulname.contains("UpdateClientStatus")){
            interF = "/CDGServer3/UpdateClientStatus";

        }else if(vulname.contains("UpgradeService1")){
            interF = "/CDGServer3/UpgradeService1";

        }else if(vulname.contains("GetValidateAuthCodeService")){
            interF = "/CDGServer3/GetValidateAuthCodeService";

        }else if(vulname.contains("UserLoginOutService1")){
            interF = "/CDGServer3/UserLoginOutService1";

        }else if(vulname.contains("GetUserSafetyPolicyService")){
            interF = "/CDGServer3/GetUserSafetyPolicyService";

        }else if(vulname.contains("GetMakeOutSendFileInfoService")){
            interF = "/CDGServer3/GetMakeOutSendFileInfoService";

        }else if(vulname.contains("UpgradeService2")){
            interF = "/CDGServer3/UpgradeService2";

        }else if(vulname.contains("UpdatePasswordService")){
            interF = "/CDGServer3/UpdatePasswordService";

        }else if(vulname.contains("GetUsecPolicyService")){
            interF = "/CDGServer3/GetUsecPolicyService";

        }else if(vulname.contains("GetValidateServerService")){
            interF = "/CDGServer3/GetValidateServerService";

        }else if(vulname.contains("UninstallApplicationService1")){
            interF = "/CDGServer3/UninstallApplicationService1";

        }else if(vulname.contains("OfflineApplicationService2")){
            interF = "/CDGServer3/OfflineApplicationService2";

        }else if(vulname.contains("DecryptApplicationService1")){
            interF = "/CDGServer3/DecryptApplicationService1";

        }else if(vulname.contains("OfflineApplicationService1")){
            interF = "/CDGServer3/OfflineApplicationService1";

        }else if(vulname.contains("document")){
            interF = "/CDGServer3/document";

        }else if(vulname.contains("AutoSignService1")){
            interF = "/CDGServer3/AutoSignService1";

        }else if(vulname.contains("CDGAuthoriseTempletService1")){
            interF = "/CDGServer3/CDGAuthoriseTempletService1";

        }else if(vulname.contains("CreateDocService1")){
            interF = "/CDGServer3/CreateDocService1";

        }else if(vulname.contains("SecureUsbConnection")){
            interF = "/CDGServer3/SecureUsbConnection";

        }else if(vulname.contains("ExamCDGDocService1")) {
            interF = "/CDGServer3/ExamCDGDocService1";
        }

        return interF;
    }
}
