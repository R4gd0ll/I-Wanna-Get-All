package utils;

import core.Exploitlnterface;

import java.util.ArrayList;

import exp.oa.esafenet.*;
import exp.oa.esafenet.esafenetInf.esafenet_CheckClientServelt_serial;
import exp.oa.esafenet.esafenetInf.esafenet_EmailAuditService_serial;
import exp.oa.esafenet.esafenetInf.esafenet_GetValidateLoginUserService_serial;
import exp.oa.esafenet.esafenetInf.esafenet_SystemService_serial;
import exp.oa.fanruanoa.*;
import exp.oa.hongfanoa.*;
import exp.oa.hongjingoa.*;
import exp.oa.kingdeeoa.*;
import exp.oa.jinheoa.*;
import exp.oa.landrayoa.*;
import exp.oa.seeyonoa.A6_8.*;
import exp.oa.seeyonoa.yyoa.*;
import exp.oa.FE.*;
import exp.oa.tongdaoa.*;
import exp.oa.wanhuoa.*;
import exp.oa.weaveroa.ecology.*;
import exp.oa.weaveroa.emobile.*;
import exp.oa.weaveroa.eoffice.*;
import exp.oa.yongyou.grp.*;
import exp.oa.yongyou.nc.*;
import exp.oa.yongyou.ncc.*;
import exp.oa.yongyou.ncinterface.*;
import exp.oa.yongyou.tplus.*;
import exp.oa.yongyou.u8c.*;
import exp.oa.yongyou.ufida.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Kinds_Exp {


  private ArrayList<String> expList;
  private ArrayList<String> kindList;

  /**
   * 构造器初始化数据
   */
  public Kinds_Exp() {
    this.kinds();
  }


  public ArrayList<String> getKindList() {
    return kindList;
  }

  public ObservableList<String> getFXKindList() {
    return FXCollections.observableArrayList(kindList);
  }

  public ArrayList<String> getExpList() {
    return expList;
  }

  /**
   * 初始化默认数据.
   *
   * @return 选项集合.
   */
  public ArrayList<String> kinds() {
    kindList = new ArrayList<>();
    kindList.add("OA");
    return kindList;
  }


  public static ObservableList<String> oa() {
    ArrayList<String> oa = new ArrayList<>();
    oa.add("用友-NC");
    oa.add("用友-GRP");
    oa.add("用友-U8C");
    oa.add("用友-Tplus");
    oa.add("用友-serial接口");
    oa.add("泛微-ECology");
    oa.add("泛微-EOffice");
    oa.add("泛微-EMobile");
    oa.add("蓝凌-OA");
    oa.add("万户-OA");
    oa.add("致远-OA");
    oa.add("通达-OA");
    oa.add("帆软-OA");
    oa.add("金蝶-OA");
    oa.add("金和-OA");
    oa.add("红帆-OA");
    oa.add("宏景-OA");
    oa.add("亿赛通-OA");
    oa.add("飞企互联-FE");
    return FXCollections.observableArrayList(oa);
  }


  //根据选择的Exp返回对应的对象
  public ObservableList<String> defaultList() {
    expList = new ArrayList<>();
    expList.add("All");
    expList.add("待添加");
    return FXCollections.observableArrayList(expList);
  }
  /*---------------------OA系列-------------------------*/

  //飞企互联
  public ObservableList<String> FE() {
    expList = new ArrayList<>();
    expList.add("All");
    expList.add("FE_editflow_manager_sqli");
    expList.add("FE_common_sort_tree_RCE");
    expList.add("FE_common_sort_tree_upload");
    expList.add("FE_publicData_sqli");
    expList.add("FE_iweboffice_OfficeServer_upload");
    return FXCollections.observableArrayList(expList);
  }
  //亿赛通
  public ObservableList<String> esafenet() {
    expList = new ArrayList<>();
    expList.add("All");
    expList.add("esafenet_useractivate_update_sqli");
    expList.add("esafenet_PolicyAjax_sqli");
    expList.add("esafenet_dataimport_rce");
    expList.add("esafenet_LinkFilterService_unauth");
    expList.add("esafenet_createSmartSec_info");
    expList.add("esafenet_syncuser_fastjson_jndi");
    expList.add("esafenet_DownLoadMail_FileRead");
    expList.add("esafenet_EmailAuditService_serial");
    expList.add("esafenet_SystemService_serial");
    expList.add("esafenet_CheckClientServelt_serial");
    expList.add("esafenet_GetValidateLoginUserService_serial");
    return FXCollections.observableArrayList(expList);
  }

  //泛微ec
  public ObservableList<String> weaverec() {
    expList = new ArrayList<>();
    expList.add("All");
    expList.add("weaver_ec_UploaderOperate_upload");
    expList.add("weaver_ec_WorkflowServlet_xml");
    expList.add("weaver_ec_CheckServer_sqli");
    expList.add("weaver_ec_FileDownloadForOutDoc_sqli");
    expList.add("weaver_ec_RequestByXml_xxe");
    return FXCollections.observableArrayList(expList);
  }

  //泛微eo
  public ObservableList<String> weavereo() {
    expList = new ArrayList<>();
    expList.add("All");
    expList.add("weaver_eo_Ajax_upload");
    expList.add("weaver_eo_Uploadify_upload");
    expList.add("weaver_eo_sample_incextfile_upload");
    expList.add("weaver_eo_sample_saveimage_upload");
    expList.add("weaver_eo_UserSelect_info");
    expList.add("weaver_eo_dept_info");
    expList.add("weaver_eo_json_tfs_sqli");
    expList.add("weaver_eo_getSelectList_crm_sqli");
    expList.add("weaver_eo_config_info");
    expList.add("weaver_eo_login_other_unauth");
    expList.add("weaver_eo_userselectNew_info");
    expList.add("weaver_eo_mobileurl_configfile_info");
    expList.add("weaver_eo_loginwsdl_sqli");
    expList.add("weaver_eo_calewsdl_sqli");
    expList.add("weaver_eo_Officeserver2_FileRead");
    return FXCollections.observableArrayList(expList);
  }

  //泛微em
  public ObservableList<String> weaverem() {
    expList = new ArrayList<>();
    expList.add("All");
    expList.add("weaver_em_messageType_rce");
    expList.add("weaver_em_lang2sql_upload");
    expList.add("weaver_emessage_FileRead");
    return FXCollections.observableArrayList(expList);
  }

  //蓝凌oa
  public ObservableList<String> landrayoa() {
    expList = new ArrayList<>();
    expList.add("All");
    expList.add("landray_getLoginSessionId_unauth");
    expList.add("landray_ekp_treexmlTmpl_rce");
    expList.add("landray_sysSearchMain_rce");
    expList.add("landray_sysUiComponent_upload");
    expList.add("landray_sysZonePersonInfo_info");
    expList.add("landray_eis_ShowUserInfo_sqli");
    expList.add("landray_eis_definefield_sqli");
    expList.add("landray_eis_UniformEntry_sqli");
    expList.add("landray_eis_FrmButtonFunc_sqli");
    expList.add("landray_eis_saveImg_upload");
    expList.add("landray_eis_FrmFormUpload_upload");
    expList.add("landray_eis_FrmFormListMain_sqli");
    expList.add("landray_eis_DocFileeditWord_sqli");
    expList.add("landray_wechatloginhelper_sqli");
    return FXCollections.observableArrayList(expList);
  }

  //宏景
  public ObservableList<String> hongjingoa() {
    expList = new ArrayList<>();
    expList.add("All");
    expList.add("hongjing_hcm_ajaxService_sqli");
    expList.add("hongjing_hcm_getorgtree_sqli");
    expList.add("hongjing_hcm_infoView_sqli");
    expList.add("hongjing_hcm_FrCodeAddTreeServlet_sqli");
    expList.add("hongjing_hcm_KhFieldTree_sqli");
    expList.add("hongjing_hcm_trainplanTree_sqli");
    expList.add("hongjing_hcm_codesettree_sqli");
    expList.add("hongjing_hcm_loadhistoryorgtree_sqli");
    expList.add("hongjing_hcm_SmsAcceptGSTXServlet_xxe");
    expList.add("hongjing_hcm_openFile_FileRead");
    return FXCollections.observableArrayList(expList);
  }

  //金蝶oa
  public ObservableList<String> kingdeeoa() {
    expList = new ArrayList<>();
    expList.add("All");
    expList.add("kingdee_Apusic_serverFile_overload");
    expList.add("kingdee_Apusic_deployapp_upload");
    expList.add("kingdee_Yun_CommonFileServer_FileRead");
    expList.add("kingdee_Yun_FileProxyHandler_FileDown");
    expList.add("kingdee_Yun_DevReportService_serial");
    expList.add("kingdee_Yun_AppDesignerService_serial");
    expList.add("kingdee_Yun_DynamicFormService_serial");
    expList.add("kingdee_Yun_GetServiceUri_serial");
    expList.add("kingdee_Yun_scpsupreghandler_upload");
    expList.add("kingdee_EAS_serverFile_overload");
    expList.add("kingdee_EAS_uploadLogo_upload");
    expList.add("kingdee_EAS_pdfViewLocal_FileRead");
    expList.add("kingdee_EAS_myUploadFile_upload");
    expList.add("kingdee_EAS_loadTree_JNDI");
    return FXCollections.observableArrayList(expList);
  }

  //帆软oa
  public ObservableList<String> fanruanoa(){
    expList = new ArrayList<>();
    expList.add("All");
    expList.add("fanruan_channel_SerialRCE");
    return FXCollections.observableArrayList(expList);
  }

  //红帆oa
  public ObservableList<String> hongfanoa(){
    expList = new ArrayList<>();
    expList.add("All");
    expList.add("hongfan_ioFileExport_FileRead");
    expList.add("hongfan_iorepsavexml_upload");
    expList.add("hongfan_udfmr_error_sqli");
    expList.add("hongfan_zyy_AttFile_info");
    expList.add("hongfan_wssRtFile_sqli");
    expList.add("hongfan_iocomgetatt_sqli");
    return FXCollections.observableArrayList(expList);
  }

  //金和oa
  public ObservableList<String> jinheoa(){
    expList = new ArrayList<>();
    expList.add("All");
    expList.add("jinhe_GetTreeDate_info");
    expList.add("jinhe_UserWebControl_info");
    expList.add("jinhe_GetTreeDate_sqli");
    expList.add("jinhe_sapb1config_unauth");
    expList.add("jinhe_uploaddoc_sqli");
    expList.add("jinhe_MailTemplates_sqli");
    expList.add("jinhe_GetHomeInfo_sqli");
    expList.add("jinhe_CarCardInfo_sqli");
    expList.add("jinhe_FileUploadMessage_FileRead");
    expList.add("jinhe_GetSqlData_RCE");
    expList.add("jinhe_viewConTemplate_RCE");
    expList.add("jinhe_getattout_sqli");
    expList.add("jinhe_RssModulesHttp_sqli");
    expList.add("jinhe_WebBill_xxe");
    expList.add("jinhe_OfficeServer_upload");
    expList.add("jinhe_ntkoUpload_upload");
    expList.add("jinhe_uploadfileeditorsave_upload");
    expList.add("jinhe_saveAsOtherFormatServlet_upload");
    expList.add("jinhe_Uploadfileblock_upload");
    return FXCollections.observableArrayList(expList);
  }

  //用友oa
  public ObservableList<String> yongyounc() {
    expList = new ArrayList<>();
    expList.add("All");
    expList.add("yongyou_nc_BshServlet_RCE");
    expList.add("yongyou_nc_jsInvoke_RCE");
    expList.add("yongyou_nc_NCFindWeb_FileRead");
    expList.add("yongyou_nc_DocServlet_FileRead");
    expList.add("yongyou_nc_worddoxc_FileRead");
    expList.add("yongyou_nc_PortalPt_FileDownload");
    expList.add("yongyou_nc_wsncapplet_info");
    expList.add("yongyou_nc_PortalSESInitToolService_info");
    expList.add("yongyou_nc_Grouptemplet_upload");
    expList.add("yongyou_nc_accept_upload");
    expList.add("yongyou_nc_LfwFileUploadServlet_upload");
    expList.add("yongyou_nc_ResourceManagerServlet_upload");
    expList.add("yongyou_nc_FileReceiveServlet_upload");
    expList.add("yongyou_nc_ActionHandlerServlet_serial");
    expList.add("yongyou_nc_IMsgCenterWebServer_JNDI");
    expList.add("yongyou_nc_IUpdateService_xxe");
    expList.add("yongyou_nc_ufida_getFileLocal_FileRead");
    expList.add("yongyou_nc_showcontent_sqli");
    expList.add("yongyou_nc_qryAddGoodsApplyPK_sqli");
    expList.add("yongyou_ufida_downloadPageId_FileRead");
    expList.add("yongyou_ufida_INtbOBAWebService_xxe");
    expList.add("yongyou_nc_cloud_uploadChunk_upload");
    expList.add("yongyou_nc_cloud_getStaffInfo_sqli");
    expList.add("yongyou_nc_cloud_queryRuleByDeptId_sqli");
    expList.add("yongyou_nc_could_queryBeginEndTime_sqli");
    expList.add("yongyou_nc_cloud_importhttpscer_upload");
    expList.add("yongyou_nc_cloud_PMCloudDriveProjectStateServlet_JNDI");

    return FXCollections.observableArrayList(expList);
  }

  //用友GRP
  public ObservableList<String> yongyougrp() {
    expList = new ArrayList<>();
    expList.add("All");
    expList.add("yongyou_grpu8_bx_historyDataCheck_sqli");
    expList.add("yongyou_grpu8_UploadFileData_upload");
    expList.add("yongyou_grpu8_U8AppProxy_upload");
    expList.add("yongyou_grpu8_SmartUpload01_RCE");
    expList.add("yongyou_grpu8_dialogmoreUsercheck_sqli");
    expList.add("yongyou_grpu8_sqcxIndex_sqli");
    expList.add("yongyou_grpu8_slbmbygr_sqli");
    expList.add("yongyou_grpu8_bxdjcheck_sqli");
    expList.add("yongyou_grpu8_license_check_sqli");
    expList.add("yongyou_grpu8_SelectDMJE_sqli");
    expList.add("yongyou_grpu8_operOriztion_sqli");
    expList.add("yongyou_grpu8_ufgovbanl_xxe");
    expList.add("yongyou_grpu8_forgetPasswordOld_sqli");

    return FXCollections.observableArrayList(expList);
  }

  //用友U8C
  public ObservableList<String> yongyouU8C() {
    expList = new ArrayList<>();
    expList.add("All");
    expList.add("yongyou_U8C_RegisterServlet_sqli");
    expList.add("yongyou_U8C_TaskTreeQuery_sqli");
    expList.add("yongyou_U8C_BlurTypeQuery_sqli");
    expList.add("yongyou_U8C_base64_sqli");
    expList.add("yongyou_U8C_AppPhoneServletService_sqli");
    expList.add("yongyou_U8C_XChangeServlet_xxe");
    expList.add("yongyou_U8C_lube_update_XXE");
    expList.add("yongyou_U8C_FileTransportServlet_serial");
    expList.add("yongyou_U8C_ServletCommander_serial");
    expList.add("yongyou_U8C_TableInputOperServlet_serial");
    expList.add("yongyou_U8C_CacheInvokeServlet_serial");
    expList.add("yongyou_U8C_LoginVideoServlet_serial");
    expList.add("yongyou_U8C_linuxpages_upload");
    return FXCollections.observableArrayList(expList);
  }

  //用友Tplus
  public ObservableList<String> yongyouTplus() {
    expList = new ArrayList<>();
    expList.add("All");
    expList.add("yongyou_tplus_GetStoreWarehouseByStore_serial");
    expList.add("yongyou_tplus_DownloadProxy_FileRead");
    expList.add("yongyou_tplus_SetupAccount_upload");
    return FXCollections.observableArrayList(expList);
  }

  //用友serial接口
  public ObservableList<String> yongyouserial() {
    expList = new ArrayList<>();
    expList.add("All");
    expList.add("yongyou_nc_MonitorServlet_serial");
    expList.add("yongyou_nc_OAContactsFuzzySearchServlet_serial");
    expList.add("yongyou_nc_OAUserAuthenticationServlet_serial");
    expList.add("yongyou_nc_OAUserQryServlet_serial");
    expList.add("yongyou_nc_NCMessageServlet_serial");
    expList.add("yongyou_nc_XbrlPersistenceServlet_serial");
    expList.add("yongyou_nc_UploadServlet_serial");
    expList.add("yongyou_nc_fsDownloadServlet_serial");
    expList.add("yongyou_nc_ECFileManageServlet_serial");
    expList.add("yongyou_nc_DownloadServlet_serial");
    expList.add("yongyou_nc_DeleteServlet_serial");
    expList.add("yongyou_nc_dcupdateService_serial");
    expList.add("yongyou_nc_LoggingConfigServlet_serial");
    expList.add("yongyou_nc_MxServlet_serial");
    expList.add("yongyou_nc_JiuQiClientReqDispatch_serial");
    expList.add("yongyou_nc_FileParserServlet_serial");
    expList.add("yongyou_nc_FileReceiveServlet_serial");
    expList.add("yongyou_nc_ModelHandleServlet_serial");
    expList.add("yongyou_nc_ConfigResourceServlet_serial");
    return FXCollections.observableArrayList(expList);
  }

  //万户oa
  public ObservableList<String> wanhuoa() {
    expList = new ArrayList<>();
    expList.add("All");
    expList.add("wanhu_OfficeServer_upload");
    expList.add("wanhu_fileUploadController_upload");
    expList.add("wanhu_axisGeneralweb_upload");
    expList.add("wanhu_wpsservlet_upload");
    expList.add("wanhu_file2Html_upload");
    expList.add("wanhu_evoInterfaceServlet_unauth");
    expList.add("wanhu_porletSettingId_upload");
    expList.add("wanhu_senddocument_upload");
    expList.add("wanhu_onlyfield_sqli");
    expList.add("wanhu_getSignPicture_sqli");
    expList.add("wanhu_wfAccessoryDelete_sqli");
    expList.add("wanhu_wfProcessAttrelateAiframe_sqli");
    expList.add("wanhu_DocumentEditunite_sqli");
    expList.add("wanhu_Logindownload_FileRead");
    return FXCollections.observableArrayList(expList);
  }

  //致远oa
  public ObservableList<String> zhiyuanoa() {
    expList = new ArrayList<>();
    expList.add("All");
    expList.add("seeyon_M1Server_userTokenService_serial");
    expList.add("seeyon_M3Server_sendbatch6_1sp1_serial");
    expList.add("seeyon_A8_htmlofficeservlet_upload");
    expList.add("seeyon_A8_ReportServer_upload");
    expList.add("seeyon_A8_statusJsp_info");
    expList.add("seeyon_A8_officeservlet_info");
    expList.add("seeyon_A6A8_webmail_info");
    expList.add("seeyon_A6A8_getAjaxDataServlet_xxe");
    expList.add("seeyon_A6A8_saveExcelInBase_upload");
    expList.add("seeyon_A6A8_wpsAssistServlet_upload");
    //body="yyoa" && app="致远互联-OA"
    expList.add("seeyon_yyoa_getSessionListJsp_info");
    expList.add("seeyon_yyoa_createMysqlJsp_info");
    expList.add("seeyon_yyoa_DownExcelBeanServlet_info");
    expList.add("seeyon_yyoa_initDataAssessJsp_info");
    expList.add("seeyon_yyoa_setextnoJsp_sqli");
    expList.add("seeyon_yyoa_testJsp_sqli");
    expList.add("seeyon_yyoa_configJsp_info");
    expList.add("seeyon_yyoa_operaFile_FileRead");
    expList.add("seeyon_yyoa_doUpload_upload");
    return FXCollections.observableArrayList(expList);
  }

  //通达oa
  public ObservableList<String> tongdaoa() {
    expList = new ArrayList<>();
    expList.add("All");
    expList.add("tongda_v119_getdata_RCE");
    expList.add("tongda_package_down_FileDown");
    expList.add("tongda_header_Inc_unauth");
    expList.add("tongda_get_datas_sqli");
    expList.add("tongda_Authmobi_getCookie");
    expList.add("tongda_AnyUserLogin_unauth");
    expList.add("tongda_action_crawler_upload");
    expList.add("tongda_action_uploadfile_upload");
    expList.add("tongda_ImModule_upload");
    expList.add("tongda_tempModule_upload");
    expList.add("tongda_Ispirit_upload");
    expList.add("tongda_apiali_upload");
    expList.add("tongda_dianju_deleteLog_sqli");
    expList.add("tongda_iweboffice_deleteSeal_sqli");
    return FXCollections.observableArrayList(expList);
  }

  /*---------------------中间件系列-------------------------*/

  //apache
  public ObservableList<String> apache() {
    expList = new ArrayList<>();
    expList.add("All");
    expList.add("apache_activemq_rce");
    expList.add("apache_rocketmq_rce");
    return FXCollections.observableArrayList(expList);
  }


  /*---------------------安全设备-------------------------*/

//  F5
  public ObservableList<String> F5() {
    expList = new ArrayList<>();
    expList.add("All");
    expList.add("F5_Bigip_unauthenticated");
    return FXCollections.observableArrayList(expList);
  }
//天融信
  public ObservableList<String> topsec() {
    expList = new ArrayList<>();
    expList.add("All");
    expList.add("topsec_maincgi_cookie_rce");
    return FXCollections.observableArrayList(expList);
  }
//华为
  public ObservableList<String> huawei() {
    expList = new ArrayList<>();
    expList.add("All");
    expList.add("huawei_fusioncompute_JNDI");
    return FXCollections.observableArrayList(expList);
  }

  /*---------------------CMS-------------------------*/

  public ObservableList<String> xxljob() {
    expList = new ArrayList<>();
    expList.add("All");
    expList.add("xxljob_accessToken_rce");
    return FXCollections.observableArrayList(expList);
  }

  public static Exploitlnterface getExploit(String vulName) {
    Exploitlnterface ei = null;
    /*-----OA-----*/

    if(vulName.contains("yongyou_nc_BshServlet_RCE")){
      //用友
      ei = new yongyou_nc_BshServlet_RCE();
    } else if(vulName.contains("yongyou_nc_NCFindWeb_FileRead")){
      ei = new yongyou_nc_NCFindWeb_FileRead();
    } else if(vulName.contains("yongyou_nc_DocServlet_FileRead")){
      ei = new yongyou_nc_DocServlet_FileRead();
    } else if(vulName.contains("yongyou_nc_worddoxc_FileRead")){
      ei = new yongyou_nc_worddoxc_FileRead();
    } else if(vulName.contains("yongyou_nc_PortalPt_FileDownload")){
      ei = new yongyou_nc_PortalPt_FileDownload();
    } else if(vulName.contains("yongyou_nc_ActionHandlerServlet_serial")){
      ei = new yongyou_nc_ActionHandlerServlet_serial();
    } else if(vulName.contains("yongyou_nc_ResourceManagerServlet_upload")){
      ei = new yongyou_nc_ResourceManagerServlet_upload();
    } else if(vulName.contains("yongyou_nc_FileReceiveServlet_serial")){
      ei = new yongyou_nc_FileReceiveServlet_serial();
    } else if(vulName.contains("yongyou_nc_ConfigResourceServlet_serial")){
      ei = new yongyou_nc_ConfigResourceServlet_serial();
    } else if(vulName.contains("yongyou_nc_FileParserServlet_serial")){
      ei = new yongyou_nc_FileParserServlet_serial();
    } else if(vulName.contains("yongyou_nc_UploadServlet_serial")){
      ei = new yongyou_nc_UploadServlet_serial();
    } else if(vulName.contains("yongyou_nc_DownloadServlet_serial")){
      ei = new yongyou_nc_DownloadServlet_serial();
    } else if(vulName.contains("yongyou_nc_ModelHandleServlet_serial")){
      ei = new yongyou_nc_ModelHandleServlet_serial();
    } else if(vulName.contains("yongyou_nc_DeleteServlet_serial")){
      ei = new yongyou_nc_DeleteServlet_serial();
    } else if(vulName.contains("yongyou_nc_NCMessageServlet_serial")){
      ei = new yongyou_nc_NCMessageServlet_serial();
    } else if(vulName.contains("yongyou_nc_MxServlet_serial")){
      ei = new yongyou_nc_MxServlet_serial();
    } else if(vulName.contains("yongyou_nc_XbrlPersistenceServlet_serial")){
      ei = new yongyou_nc_XbrlPersistenceServlet_serial();
    } else if(vulName.contains("yongyou_nc_jsInvoke_RCE")){
      ei = new yongyou_nc_jsInvoke_RCE();
    } else if(vulName.contains("yongyou_nc_qryAddGoodsApplyPK_sqli")){
      ei = new yongyou_nc_qryAddGoodsApplyPK_sqli();
    } else if(vulName.contains("yongyou_nc_FileReceiveServlet_upload")){
      ei = new yongyou_nc_FileReceiveServlet_upload();
    } else if(vulName.contains("yongyou_nc_wsncapplet_info")){
      ei = new yongyou_nc_wsncapplet_info();
    } else if(vulName.contains("yongyou_nc_PortalSESInitToolService_info")){
      ei = new yongyou_nc_PortalSESInitToolService_info();
    } else if(vulName.contains("yongyou_nc_Grouptemplet_upload")){
      ei = new yongyou_nc_Grouptemplet_upload();
    } else if(vulName.contains("yongyou_nc_accept_upload")){
      ei = new yongyou_nc_accept_upload();
    } else if(vulName.contains("yongyou_nc_LfwFileUploadServlet_upload")){
      ei = new yongyou_nc_LfwFileUploadServlet_upload();
    } else if(vulName.contains("yongyou_nc_fsDownloadServlet_serial")){
      ei = new yongyou_nc_fsDownloadServlet_serial();
    } else if(vulName.contains("yongyou_nc_MonitorServlet_serial")){
      ei = new yongyou_nc_MonitorServlet_serial();
    } else if(vulName.contains("yongyou_nc_ECFileManageServlet_serial")){
      ei = new yongyou_nc_ECFileManageServlet_serial();
    } else if(vulName.contains("yongyou_nc_LoggingConfigServlet_serial")){
      ei = new yongyou_nc_LoggingConfigServlet_serial();
    }else if(vulName.contains("yongyou_nc_OAContactsFuzzySearchServlet_serial")){
      ei = new yongyou_nc_OAContactsFuzzySearchServlet_serial();
    } else if(vulName.contains("yongyou_nc_OAUserAuthenticationServlet_serial")){
      ei = new yongyou_nc_OAUserAuthenticationServlet_serial();
    } else if(vulName.contains("yongyou_nc_OAUserQryServlet_serial")){
      ei = new yongyou_nc_OAUserQryServlet_serial();
    } else if(vulName.contains("yongyou_nc_cloud_queryRuleByDeptId_sqli")){
      ei = new yongyou_nc_cloud_queryRuleByDeptId_sqli();
    } else if(vulName.contains("yongyou_nc_cloud_getStaffInfo_sqli")){
      ei = new yongyou_nc_cloud_getStaffInfo_sqli();
    } else if(vulName.contains("yongyou_nc_could_queryBeginEndTime_sqli")){
      ei = new yongyou_nc_could_queryBeginEndTime_sqli();
    } else if(vulName.contains("yongyou_nc_JiuQiClientReqDispatch_serial")){
      ei = new yongyou_nc_JiuQiClientReqDispatch_serial();
    } else if(vulName.contains("yongyou_nc_dcupdateService_serial")){
      ei = new yongyou_nc_dcupdateService_serial();
    } else if(vulName.contains("yongyou_nc_IMsgCenterWebServer_JNDI")){
      ei = new yongyou_nc_IMsgCenterWebServer_JNDI();
    } else if(vulName.contains("yongyou_nc_IUpdateService_xxe")){
      ei = new yongyou_nc_IUpdateService_xxe();
    } else if(vulName.contains("yongyou_ufida_downloadPageId_FileRead")){
      ei = new yongyou_ufida_downloadPageId_FileRead();
    } else if(vulName.contains("yongyou_nc_cloud_PMCloudDriveProjectStateServlet_JNDI")){
      ei = new yongyou_nc_cloud_PMCloudDriveProjectStateServlet_JNDI();
    } else if(vulName.contains("yongyou_nc_cloud_uploadChunk_upload")){
      ei = new yongyou_nc_cloud_uploadChunk_upload();
    } else if(vulName.contains("yongyou_nc_cloud_importhttpscer_upload")){
      ei = new yongyou_nc_cloud_importhttpscer_upload();
    } else if(vulName.contains("yongyou_nc_showcontent_sqli")){
      ei = new yongyou_nc_showcontent_sqli();
    }

    else if(vulName.contains("yongyou_grpu8_bx_historyDataCheck_sqli")){
      //用友GRP-U8C
      ei = new yongyou_grpu8_bx_historyDataCheck_sqli();
    } else if(vulName.contains("yongyou_grpu8_UploadFileData_upload")){
      ei = new yongyou_grpu8_UploadFileData_upload();
    } else if(vulName.contains("yongyou_grpu8_U8AppProxy_upload")){
      ei = new yongyou_grpu8_U8AppProxy_upload();
    } else if(vulName.contains("yongyou_grpu8_SmartUpload01_RCE")){
      ei = new yongyou_grpu8_SmartUpload01_RCE();
    } else if(vulName.contains("yongyou_grpu8_ufgovbanl_xxe")){
      ei = new yongyou_grpu8_ufgovbanl_xxe();
    } else if(vulName.contains("yongyou_grpu8_sqcxIndex_sqli")){
      ei = new yongyou_grpu8_sqcxIndex_sqli();
    } else if(vulName.contains("yongyou_grpu8_license_check_sqli")){
      ei = new yongyou_grpu8_license_check_sqli();
    } else if(vulName.contains("yongyou_grpu8_slbmbygr_sqli")){
      ei = new yongyou_grpu8_slbmbygr_sqli();
    } else if(vulName.contains("yongyou_grpu8_SelectDMJE_sqli")){
      ei = new yongyou_grpu8_SelectDMJE_sqli();
    } else if(vulName.contains("yongyou_grpu8_bxdjcheck_sqli")){
      ei = new yongyou_grpu8_bxdjcheck_sqli();
    } else if(vulName.contains("yongyou_grpu8_operOriztion_sqli")){
      ei = new yongyou_grpu8_operOriztion_sqli();
    } else if(vulName.contains("yongyou_grpu8_dialogmoreUsercheck_sqli")){
      ei = new yongyou_grpu8_dialogmoreUsercheck_sqli();
    } else if(vulName.contains("yongyou_grpu8_forgetPasswordOld_sqli")){
      ei = new yongyou_grpu8_forgetPasswordOld_sqli();
    } else if(vulName.contains("yongyou_U8C_linuxpages_upload")){
      ei = new yongyou_U8C_linuxpages_upload();
    }  else if(vulName.contains("yongyou_U8C_RegisterServlet_sqli")){
      ei = new yongyou_U8C_RegisterServlet_sqli();
    } else if(vulName.contains("yongyou_U8C_AppPhoneServletService_sqli")){
      ei = new yongyou_U8C_AppPhoneServletService_sqli();
    } else if(vulName.contains("yongyou_U8C_lube_update_XXE")){
      ei = new yongyou_U8C_lube_update_XXE();
    } else if(vulName.contains("yongyou_U8C_CacheInvokeServlet_serial")){
      ei = new yongyou_U8C_CacheInvokeServlet_serial();
    } else if(vulName.contains("yongyou_U8C_TableInputOperServlet_serial")){
      ei = new yongyou_U8C_TableInputOperServlet_serial();
    } else if(vulName.contains("yongyou_U8C_FileTransportServlet_serial")){
      ei = new yongyou_U8C_FileTransportServlet_serial();
    } else if(vulName.contains("yongyou_U8C_TaskTreeQuery_sqli")){
      ei = new yongyou_U8C_TaskTreeQuery_sqli();
    } else if(vulName.contains("yongyou_U8C_BlurTypeQuery_sqli")){
      ei = new yongyou_U8C_BlurTypeQuery_sqli();
    } else if(vulName.contains("yongyou_U8C_base64_sqli")){
      ei = new yongyou_U8C_base64_sqli();
    } else if(vulName.contains("yongyou_U8C_XChangeServlet_xxe")){
      ei = new yongyou_U8C_XChangeServlet_xxe();
    } else if(vulName.contains("yongyou_U8C_ServletCommander_serial")){
      ei = new yongyou_U8C_ServletCommander_serial();
    } else if(vulName.contains("yongyou_U8C_LoginVideoServlet_serial")){
      ei = new yongyou_U8C_LoginVideoServlet_serial();
    }

    else if (vulName.contains("yongyou_tplus_GetStoreWarehouseByStore_serial")) {
      //用友-TPLUS
      ei = new yongyou_tplus_GetStoreWarehouseByStore_serial();
    } else if(vulName.contains("yongyou_tplus_DownloadProxy_FileRead")){
      ei = new yongyou_tplus_DownloadProxy_FileRead();
    } else if(vulName.contains("yongyou_tplus_SetupAccount_upload")){
      ei = new yongyou_tplus_SetupAccount_upload();
    }

    else if (vulName.contains("yongyou_nc_ufida_getFileLocal_FileRead")) {
      //用友-UFIDA
      ei = new yongyou_ufida_getFileLocal_FileRead();
    } else if(vulName.contains("yongyou_ufida_INtbOBAWebService_xxe")){
      ei = new yongyou_ufida_INtbOBAWebService_xxe();
    }



    else if(vulName.contains("fanruan_channel_SerialRCE")){
      //帆软
      ei = new fanruan_channel_SerialRCE();
    }

    else if(vulName.contains("hongjing_hcm_ajaxService_sqli")){
      //宏景
      ei = new hongjing_hcm_ajaxService_sqli();
    } else if(vulName.contains("hongjing_hcm_getorgtree_sqli")){
      ei = new hongjing_hcm_getorgtree_sqli();
    } else if(vulName.contains("hongjing_hcm_KhFieldTree_sqli")){
      ei = new hongjing_hcm_KhFieldTree_sqli();
    } else if(vulName.contains("hongjing_hcm_trainplanTree_sqli")){
      ei = new hongjing_hcm_trainplanTree_sqli();
    } else if(vulName.contains("hongjing_hcm_codesettree_sqli")){
      ei = new hongjing_hcm_codesettree_sqli();
    } else if(vulName.contains("hongjing_hcm_FrCodeAddTreeServlet_sqli")){
      ei = new hongjing_hcm_FrCodeAddTreeServlet_sqli();
    } else if(vulName.contains("hongjing_hcm_loadhistoryorgtree_sqli")){
      ei = new hongjing_hcm_loadhistoryorgtree_sqli();
    } else if(vulName.contains("hongjing_hcm_SmsAcceptGSTXServlet_xxe")){
      ei = new hongjing_hcm_SmsAcceptGSTXServlet_xxe();
    } else if(vulName.contains("hongjing_hcm_openFile_FileRead")){
      ei = new hongjing_hcm_openFile_FileRead();
    } else if(vulName.contains("hongjing_hcm_infoView_sqli")){
      ei = new hongjing_hcm_infoView_sqli();
    }

    else if(vulName.contains("hongfan_ioFileExport_FileRead")){
      //红帆
      ei = new hongfan_ioFileExport_FileRead();
    } else if(vulName.contains("hongfan_ioFileDown_FileRead")){
      ei = new hongfan_ioFileDown_FileRead();
    } else if(vulName.contains("hongfan_udfmr_error_sqli")){
      ei = new hongfan_udfmr_error_sqli();
    } else if(vulName.contains("hongfan_iorepsavexml_upload")){
      ei = new hongfan_iorepsavexml_upload();
    } else if(vulName.contains("hongfan_wssRtFile_sqli")){
      ei = new hongfan_wssRtFile_sqli();
    } else if(vulName.contains("hongfan_iocomgetatt_sqli")){
      ei = new hongfan_iocomgetatt_sqli();
    } else if(vulName.contains("hongfan_zyy_AttFile_info")){
      ei = new hongfan_zyy_AttFile_info();
    }

    else if(vulName.contains("jinhe_GetTreeDate_info")){
      //金和
      ei = new jinhe_GetTreeDate_info();
    } else if(vulName.contains("jinhe_GetTreeDate_sqli")){
      ei = new jinhe_GetTreeDate_sqli();
    } else if(vulName.contains("jinhe_GetHomeInfo_sqli")){
      ei = new jinhe_GetHomeInfo_sqli();
    } else if(vulName.contains("jinhe_UserWebControl_info")){
      ei = new jinhe_UserWebControl_info();
    } else if(vulName.contains("jinhe_FileUploadMessage_FileRead")){
      ei = new jinhe_FileUploadMessage_FileRead();
    } else if(vulName.contains("jinhe_sapb1config_unauth")){
      ei = new jinhe_sapb1config_unauth();
    } else if(vulName.contains("jinhe_getattout_sqli")){
      ei = new jinhe_getattout_sqli();
    } else if(vulName.contains("jinhe_viewConTemplate_RCE")){
      ei = new jinhe_viewConTemplate_RCE();
    } else if(vulName.contains("jinhe_WebBill_xxe")){
      ei = new jinhe_WebBill_xxe();
    } else if(vulName.contains("jinhe_saveAsOtherFormatServlet_upload")){
      ei = new jinhe_saveAsOtherFormatServlet_upload();
    } else if(vulName.contains("jinhe_uploadfileeditorsave_upload")){
      ei = new jinhe_uploadfileeditorsave_upload();
    } else if(vulName.contains("jinhe_uploaddoc_sqli")){
      ei = new jinhe_uploaddoc_sqli();
    } else if(vulName.contains("jinhe_CarCardInfo_sqli")){
      ei = new jinhe_CarCardInfo_sqli();
    } else if(vulName.contains("jinhe_MailTemplates_sqli")){
      ei = new jinhe_MailTemplates_sqli();
    } else if(vulName.contains("jinhe_RssModulesHttp_sqli")){
      ei = new jinhe_RssModulesHttp_sqli();
    } else if(vulName.contains("jinhe_GetSqlData_RCE")){
      ei = new jinhe_GetSqlData_RCE();
    } else if(vulName.contains("jinhe_OfficeServer_upload")){
      ei = new jinhe_OfficeServer_upload();
    } else if(vulName.contains("jinhe_ntkoUpload_upload")){
      ei = new jinhe_ntkoUpload_upload();
    } else if(vulName.contains("jinhe_Uploadfileblock_upload")){
      ei = new jinhe_Uploadfileblock_upload();
    }


    else if(vulName.contains("kingdee_Apusic_serverFile_overload")){
      //金蝶
      ei = new kingdee_Apusic_serverFile_overload();
    } else if(vulName.contains("kingdee_Apusic_deployapp_upload")){
      ei = new kingdee_Apusic_deployapp_upload();
    } else if(vulName.contains("kingdee_Yun_CommonFileServer_FileRead")){
      ei = new kingdee_Yun_CommonFileServer_FileRead();
    } else if(vulName.contains("kingdee_Yun_DevReportService_serial")){
      ei = new kingdee_Yun_DevReportService_serial();
    } else if(vulName.contains("kingdee_Yun_DynamicFormService_serial")){
      ei = new kingdee_Yun_DynamicFormService_serial();
    } else if(vulName.contains("kingdee_Yun_AppDesignerService_serial")){
      ei = new kingdee_Yun_AppDesignerService_serial();
    } else if(vulName.contains("kingdee_Yun_GetServiceUri_serial")){
      ei = new kingdee_Yun_GetServiceUri_serial();
    } else if(vulName.contains("kingdee_EAS_serverFile_overload")){
      ei = new kingdee_EAS_serverFile_overload();
    } else if(vulName.contains("kingdee_Yun_scpsupreghandler_upload")){
      ei = new kingdee_Yun_scpsupreghandler_upload();
    } else if(vulName.contains("kingdee_EAS_uploadLogo_upload")){
      ei = new kingdee_EAS_uploadLogo_upload();
    } else if(vulName.contains("kingdee_EAS_loadTree_JNDI")){
      ei = new kingdee_EAS_loadTree_JNDI();
    } else if(vulName.contains("kingdee_EAS_pdfViewLocal_FileRead")){
      ei = new kingdee_EAS_pdfViewLocal_FileRead();
    } else if(vulName.contains("kingdee_EAS_myUploadFile_upload")){
      ei = new kingdee_EAS_myUploadFile_upload();
    } else if(vulName.contains("kingdee_Yun_FileProxyHandler_FileDown")){
      ei = new kingdee_Yun_FileProxyHandler_FileDown();
    }


    else if(vulName.contains("landray_ekp_treexmlTmpl_rce")){
      //蓝凌
      ei = new landray_ekp_treexmlTmpl_rce();
    } else if(vulName.contains("landray_sysSearchMain_rce")){
      ei = new landray_sysSearchMain_rce();
    } else if(vulName.contains("landray_eis_saveImg_upload")){
      ei = new landray_eis_saveImg_upload();
    } else if(vulName.contains("landray_getLoginSessionId_unauth")){
      ei = new landray_getLoginSessionId_unauth();
    } else if(vulName.contains("landray_eis_ShowUserInfo_sqli")){
      ei = new landray_eis_ShowUserInfo_sqli();
    } else if(vulName.contains("landray_eis_FrmButtonFunc_sqli")){
      ei = new landray_eis_FrmButtonFunc_sqli();
    } else if(vulName.contains("landray_eis_UniformEntry_sqli")){
      ei = new landray_eis_UniformEntry_sqli();
    } else if(vulName.contains("landray_sysUiComponent_upload")){
      ei = new landray_sysUiComponent_upload();
    } else if(vulName.contains("landray_sysZonePersonInfo_info")){
      ei = new landray_sysZonePersonInfo_info();
    } else if(vulName.contains("landray_eis_FrmFormUpload_upload")){
      ei = new landray_eis_FrmFormUpload_upload();
    } else if(vulName.contains("landray_eis_FrmFormListMain_sqli")){
      ei = new landray_eis_FrmFormListMain_sqli();
    } else if(vulName.contains("landray_eis_DocFileeditWord_sqli")){
      ei = new landray_eis_DocFileeditWord_sqli();
    } else if(vulName.contains("landray_wechatloginhelper_sqli")){
      ei = new landray_wechatloginhelper_sqli();
    } else if(vulName.contains("landray_eis_definefield_sqli")){
      ei = new landray_eis_definefield_sqli();
    }

    else if(vulName.contains("wanhu_OfficeServer_upload")){
      //万户
      ei = new wanhu_OfficeServer_upload();
    } else if(vulName.contains("wanhu_fileUploadController_upload")){
      ei = new wanhu_fileUploadController_upload();
    } else if(vulName.contains("wanhu_axisGeneralweb_upload")){
      ei = new wanhu_axisGeneralweb_upload();
    } else if(vulName.contains("wanhu_wpsservlet_upload")){
      ei = new wanhu_wpsservlet_upload();
    } else if(vulName.contains("wanhu_senddocument_upload")){
      ei = new wanhu_senddocument_upload();
    } else if(vulName.contains("wanhu_file2Html_upload")){
      ei = new wanhu_file2Html_upload();
    } else if(vulName.contains("wanhu_porletSettingId_upload")){
      ei = new wanhu_porletSettingId_upload();
    } else if(vulName.contains("wanhu_onlyfield_sqli")){
      ei = new wanhu_onlyfield_sqli();
    } else if(vulName.contains("wanhu_evoInterfaceServlet_unauth")){
      ei = new wanhu_evoInterfaceServlet_unauth();
    } else if(vulName.contains("wanhu_DocumentEditunite_sqli")){
      ei = new wanhu_DocumentEditunite_sqli();
    } else if(vulName.contains("wanhu_wfAccessoryDelete_sqli")){
      ei = new wanhu_wfAccessoryDelete_sqli();
    } else if(vulName.contains("wanhu_getSignPicture_sqli")){
      ei = new wanhu_getSignPicture_sqli();
    } else if(vulName.contains("wanhu_Logindownload_FileRead")){
      ei = new wanhu_Logindownload_FileRead();
    } else if(vulName.contains("wanhu_wfProcessAttrelateAiframe_sqli")){
      ei = new wanhu_wfProcessAttrelateAiframe_sqli();
    }

    else if(vulName.contains("tongda_v119_getdata_RCE")){
      //通达
      ei = new tongda_v119_getdata_RCE();
    } else if(vulName.contains("tongda_header_Inc_unauth")){
      ei = new tongda_header_Inc_unauth();
    } else if(vulName.contains("tongda_Authmobi_getCookie")){
      ei = new tongda_Authmobi_getCookie();
    } else if(vulName.contains("tongda_action_uploadfile_upload")){
      ei = new tongda_action_uploadfile_upload();
    } else if(vulName.contains("tongda_action_crawler_upload")){
      ei = new tongda_action_crawler_upload();
    } else if(vulName.contains("tongda_package_down_FileDown")){
      ei = new tongda_package_down_FileDown();
    } else if(vulName.contains("tongda_AnyUserLogin_unauth")){
      ei = new tongda_AnyUserLogin_unauth();
    } else if(vulName.contains("tongda_ImModule_upload")){
      ei = new tongda_ImModule_upload();
    } else if(vulName.contains("tongda_tempModule_upload")){
      ei = new tongda_tempModule_upload();
    } else if(vulName.contains("tongda_Ispirit_upload")){
      ei = new tongda_Ispirit_upload();
    } else if(vulName.contains("tongda_apiali_upload")){
      ei = new tongda_apiali_upload();
    } else if(vulName.contains("tongda_dianju_deleteLog_sqli")){
      ei = new tongda_dianju_deleteLog_sqli();
    } else if(vulName.contains("tongda_get_datas_sqli")){
      ei = new tongda_get_datas_sqli();
    } else if(vulName.contains("tongda_iweboffice_deleteSeal_sqli")){
      ei = new tongda_iweboffice_deleteSeal_sqli();
    }


    else if(vulName.contains("seeyon_A8_htmlofficeservlet_upload")){
      //致远
      ei = new seeyon_A8_htmlofficeservlet_upload();
    } else if(vulName.contains("seeyon_M1Server_userTokenService_serial")){
      ei = new seeyon_M1Server_userTokenService_serial();
    } else if(vulName.contains("seeyon_A8_ReportServer_upload")){
      ei = new seeyon_A8_ReportServer_upload();
    } else if(vulName.contains("seeyon_A8_statusJsp_info")){
      ei = new seeyon_A8_statusJsp_info();
    } else if(vulName.contains("seeyon_A8_officeservlet_info")){
      ei = new seeyon_A8_officeservlet_info();
    } else if(vulName.contains("seeyon_A6A8_saveExcelInBase_upload")){
      ei = new seeyon_A6A8_saveExcelInBase_upload();
    } else if(vulName.contains("seeyon_A6A8_webmail_info")){
      ei = new seeyon_A6A8_webmail_info();
    } else if(vulName.contains("seeyon_M3Server_sendbatch6_1sp1_serial")){
      ei = new seeyon_M3Server_sendbatch6_1sp1_serial();
    } else if(vulName.contains("seeyon_A6A8_wpsAssistServlet_upload")){
      ei = new seeyon_A6A8_wpsAssistServlet_upload();
    } else if(vulName.contains("seeyon_A6A8_getAjaxDataServlet_xxe")){
      ei = new seeyon_A6A8_getAjaxDataServlet_xxe();
    } else if(vulName.contains("seeyon_yyoa_createMysqlJsp_info")){
      ei = new seeyon_yyoa_createMysqlJsp_info();
    } else if(vulName.contains("seeyon_yyoa_DownExcelBeanServlet_info")){
      ei = new seeyon_yyoa_DownExcelBeanServlet_info();
    }else if(vulName.contains("seeyon_yyoa_configJsp_info")){
      ei = new seeyon_yyoa_configJsp_info();
    } else if(vulName.contains("seeyon_yyoa_testJsp_sqli")){
      ei = new seeyon_yyoa_testJsp_sqli();
    } else if(vulName.contains("seeyon_yyoa_initDataAssessJsp_info")){
      ei = new seeyon_yyoa_initDataAssessJsp_info();
    } else if(vulName.contains("seeyon_yyoa_setextnoJsp_sqli")){
      ei = new seeyon_yyoa_setextnoJsp_sqli();
    } else if(vulName.contains("seeyon_yyoa_getSessionListJsp_info")){
      ei = new seeyon_yyoa_getSessionListJsp_info();
    } else if(vulName.contains("seeyon_yyoa_operaFile_FileRead")){
      ei = new seeyon_yyoa_operaFile_FileRead();
    } else if(vulName.contains("seeyon_yyoa_doUpload_upload")){
      ei = new seeyon_yyoa_doUpload_upload();
    }





    else if(vulName.contains("weaver_ec_UploaderOperate_upload")){
      //泛微
      ei = new weaver_ec_UploaderOperate_upload();
    } else if(vulName.contains("weaver_ec_WorkflowServlet_xml")){
      ei = new weaver_ec_WorkflowServlet_xml();
    } else if(vulName.contains("weaver_ec_CheckServer_sqli")){
      ei = new weaver_ec_CheckServer_sqli();
    } else if(vulName.contains("weaver_ec_FileDownloadForOutDoc_sqli")){
      ei = new weaver_ec_FileDownloadForOutDoc_sqli();
    } else if(vulName.contains("weaver_ec_RequestByXml_xxe")){
      ei = new weaver_ec_RequestByXml_xxe();
    } else if(vulName.contains("weaver_em_messageType_rce")){
      ei = new weaver_em_messageType_rce();
    } else if(vulName.contains("weaver_em_lang2sql_upload")){
      ei = new weaver_em_lang2sql_upload();
    } else if(vulName.contains("weaver_emessage_FileRead")){
      ei = new weaver_emessage_FileRead();
    } else if(vulName.contains("weaver_eo_Ajax_upload")){
      ei = new weaver_eo_Ajax_upload();
    } else if(vulName.contains("weaver_eo_json_tfs_sqli")){
      ei = new weaver_eo_json_tfs_sqli();
    } else if(vulName.contains("weaver_eo_login_other_unauth")){
      ei = new weaver_eo_login_other_unauth();
    } else if(vulName.contains("weaver_eo_Uploadify_upload")){
      ei = new weaver_eo_Uploadify_upload();
    } else if(vulName.contains("weaver_eo_sample_incextfile_upload")){
      ei = new weaver_eo_sample_incextfile_upload();
    } else if(vulName.contains("weaver_eo_sample_saveimage_upload")){
      ei = new weaver_eo_sample_saveimage_upload();
    } else if(vulName.contains("weaver_eo_UserSelect_info")){
      ei = new weaver_eo_UserSelect_info();
    } else if(vulName.contains("weaver_eo_userselectNew_info")){
      ei = new weaver_eo_userselectNew_info();
    } else if(vulName.contains("weaver_eo_mobileurl_configfile_info")){
      ei = new weaver_eo_mobileurl_configfile_info();
    } else if(vulName.contains("weaver_eo_dept_info")){
      ei = new weaver_eo_dept_info();
    } else if(vulName.contains("weaver_eo_getSelectList_crm_sqli")){
      ei = new weaver_eo_getSelectList_crm_sqli();
    } else if(vulName.contains("weaver_eo_config_info")){
      ei = new weaver_eo_config_info();
    } else if(vulName.contains("weaver_eo_loginwsdl_sqli")){
      ei = new weaver_eo_loginwsdl_sqli();
    } else if(vulName.contains("weaver_eo_calewsdl_sqli")){
      ei = new weaver_eo_calewsdl_sqli();
    } else if(vulName.contains("weaver_eo_Officeserver2_FileRead")){
      ei = new weaver_eo_Officeserver2_FileRead();
    }


    else if(vulName.contains("esafenet_useractivate_update_sqli")){
      //esafenet
      ei = new esafenet_useractivate_update_sqli();
    } else if(vulName.contains("esafenet_dataimport_rce")){
      ei = new esafenet_dataimport_rce();
    } else if(vulName.contains("esafenet_PolicyAjax_sqli")){
      ei = new esafenet_PolicyAjax_sqli();
    } else if(vulName.contains("esafenet_createSmartSec_info")){
      ei = new esafenet_createSmartSec_info();
    } else if(vulName.contains("esafenet_DownLoadMail_FileRead")){
      ei = new esafenet_DownLoadMail_FileRead();
    } else if(vulName.contains("esafenet_syncuser_fastjson_jndi")){
      ei = new esafenet_syncuser_fastjson_jndi();
    } else if(vulName.contains("esafenet_LinkFilterService_unauth")){
      ei = new esafenet_LinkFilterService_unauth();
    } else if(vulName.contains("esafenet_EmailAuditService_serial")){
      ei = new esafenet_EmailAuditService_serial();
    } else if(vulName.contains("esafenet_SystemService_serial")){
      ei = new esafenet_SystemService_serial();
    } else if(vulName.contains("esafenet_CheckClientServelt_serial")){
      ei = new esafenet_CheckClientServelt_serial();
    } else if(vulName.contains("esafenet_GetValidateLoginUserService_serial")){
      ei = new esafenet_GetValidateLoginUserService_serial();
    }

    else if(vulName.contains("FE_editflow_manager_sqli")){
      //飞企互联
      ei = new FE_editflow_manager_sqli();
    } else if(vulName.contains("FE_common_sort_tree_RCE")){
      ei = new FE_common_sort_tree_RCE();
    } else if(vulName.contains("FE_publicData_sqli")){
      ei = new FE_publicData_sqli();
    } else if(vulName.contains("FE_iweboffice_OfficeServer_upload")){
      ei = new FE_iweboffice_OfficeServer_upload();
    } else if(vulName.contains("FE_common_sort_tree_upload")){
      ei = new FE_common_sort_tree_upload();
    }

    return ei;
  }
}
