package Memshell;

public class AllyyncInterface {
    public static String getInterFace(String vulname){
        String interF = null;
        if(vulname.contains("DeleteServlet")){
            interF = "/servlet/~ic/nc.document.pub.fileSystem.servlet.DeleteServlet";
        }else if(vulname.contains("ConfigResourceServlet")){
            interF = "/servlet/~ic/nc.bs.framework.server.ConfigResourceServlet";
        }else if(vulname.contains("DcUpdateServiceImpl")){
            interF = "/servlet/~uapbs/uap.bs.dc.file.service.impl.DcUpdateServiceImpl";
        }else if(vulname.contains("DownloadServlet")){
            interF = "/servlet/~ic/nc.document.pub.fileSystem.servlet.DownloadServlet";
        }else if(vulname.contains("ECFileManageServlet")){
            interF = "/service/ECFileManageServlet";
        }else if(vulname.contains("FileParserServlet")){
            interF = "/servlet/~uapss/nc.search.file.parser.FileParserServlet";
        }else if(vulname.contains("FileReceiveServlet")){
            interF = "/servlet/~uapss/com.yonyou.ante.servlet.FileReceiveServlet";
        }else if(vulname.contains("fsDownloadServlet")){
            interF = "/fs/update/DownloadServlet";
        }else if(vulname.contains("JiuQiClientReqDispatch")){
            interF = "/servlet/~ic/com.ufsoft.iufo.jiuqi.JiuQiClientReqDispatch";
        }else if(vulname.contains("LoggingConfigServlet")){
            interF = "/servlet/~ic/nc.bs.logging.config.LoggingConfigServlet";
        }else if(vulname.contains("ModelHandleServlet")){
            interF = "/servlet/~ic/uap.pub.ae.model.handle.ModelHandleServlet";
        }else if(vulname.contains("MonitorServlet")){
            interF = "/servlet/~ic/nc.bs.framework.mx.monitor.MonitorServlet";
        }else if(vulname.contains("MxServlet")){
            interF = "/servlet/~ic/nc.bs.framework.mx.MxServlet";
        }else if(vulname.contains("NCMessageServlet")){
            interF = "/servlet/~baseapp/nc.message.bs.NCMessageServlet";
        }else if(vulname.contains("OAContactsFuzzySearchServlet")){
            interF = "/servlet/~ic/nc.uap.bs.im.servlet.OAContactsFuzzySearchServlet";
        }else if(vulname.contains("OAUserAuthenticationServlet")){
            interF = "/servlet/~ic/nc.uap.bs.im.servlet.OAUserAuthenticationServlet";
        }else if(vulname.contains("OAUserQryServlet")){
            interF = "/servlet/~ic/nc.uap.bs.im.servlet.OAUserQryServlet";
        }else if(vulname.contains("UploadServlet")){
            interF = "/servlet/~ic/nc.document.pub.fileSystem.servlet.UploadServlet";
        }else if(vulname.contains("XbrlPersistenceServlet")){
            interF = "/servlet/~uapxbrl/uap.xbrl.persistenceImpl.XbrlPersistenceServlet";
        }else if(vulname.contains("ActionHandlerServlet")){
            interF = "/servlet/~ic/com.ufida.zior.console.ActionHandlerServlet";
        }
        return interF;
    }
}
