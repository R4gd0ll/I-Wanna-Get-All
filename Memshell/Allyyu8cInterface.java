package Memshell;

public class Allyyu8cInterface {
    public static String getInterFace(String vulname){
        String interF = null;
        if(vulname.contains("CacheInvokeServlet")){
            interF = "/service/~iufo/com.ufsoft.iufo.web.appletinvoke.CacheInvokeServlet";
        }else if(vulname.contains("FileTransportServlet")){
            interF = "/service/~iufo/nc.ui.iufo.server.center.FileTransportServlet";
        }else if(vulname.contains("TableInputOperServlet")){
            interF = "/servlet/~iufo/com.ufsoft.iuforeport.tableinput.TableInputOperServlet";
        }else if(vulname.contains("ServletCommander")){
            interF = "/service/~tbb/nc.bs.ntb.plugin.ServletCommander";
        }else if(vulname.contains("LoginVideoServlet")){
            interF = "/servlet/LoginVideoServlet";
        }else if(vulname.contains("LoggingConfigServlet")){
            interF = "/servlet/~ic/nc.bs.logging.config.LoggingConfigServlet";
        }else if(vulname.contains("MonitorServlet")){
            interF = "/servlet/~ic/nc.bs.framework.mx.monitor.MonitorServlet";
        }else if(vulname.contains("MxServlet")){
            interF = "/servlet/~ic/nc.bs.framework.mx.MxServlet";
        }else if(vulname.contains("FileManageServlet")){
            interF = "/service/FileManageServlet";
        }else if(vulname.contains("ESBInvokerServlet")){
            interF = "/service/ESBInvokerServlet";
        }
        return interF;
    }
    public static Boolean getCryptType(String interF){
        String[] var = {"CacheInvokeServlet","FileTransportServlet","TableInputOperServlet"};
        for(String i : var){
            if(interF.contains(i)){
                return true;
            }
        }
        return false;
    }
}
