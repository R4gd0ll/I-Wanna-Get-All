package controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import utils.Methods;

public class TsklistController {
    private String list = "\"360tray.exe\": \"360安全卫士-实时保护\",\n" +
            "\"360safe.exe\": \"360安全卫士-主程序\",\n" +
            "\"ZhuDongFangYu.exe\": \"360安全卫士-主动防御\",\n" +
            "\"360sd.exe\": \"360杀毒\",\n" +
            "\"a2guard.exe\": \"a-squared杀毒\",\n" +
            "\"ad-watch.exe\": \"Lavasoft杀毒\",\n" +
            "\"cleaner8.exe\": \"The Cleaner杀毒\",\n" +
            "\"vba32lder.exe\": \"vb32杀毒\",\n" +
            "\"MongoosaGUI.exe\": \"Mongoosa杀毒\",\n" +
            "\"CorantiControlCenter32.exe\": \"Coranti2012杀毒\",\n" +
            "\"F-PROT.exe\": \"F-Prot AntiVirus\",\n" +
            "\"CMCTrayIcon.exe\": \"CMC杀毒\",\n" +
            "\"K7TSecurity.exe\": \"K7杀毒\",\n" +
            "\"UnThreat.exe\": \"UnThreat杀毒\",\n" +
            "\"CKSoftShiedAntivirus4.exe\": \"Shield Antivirus杀毒\",\n" +
            "\"AVWatchService.exe\": \"VIRUSfighter杀毒\",\n" +
            "\"ArcaTasksService.exe\": \"ArcaVir杀毒\",\n" +
            "\"iptray.exe\": \"Immunet杀毒\",\n" +
            "\"PSafeSysTray.exe\": \"PSafe杀毒\",\n" +
            "\"nspupsvc.exe\": \"nProtect杀毒\",\n" +
            "\"SpywareTerminatorShield.exe\": \"SpywareTerminator反间谍软件\",\n" +
            "\"BKavService.exe\": \"Bkav杀毒\",\n" +
            "\"MsMpEng.exe\": \"Microsoft Security Essentials\",\n" +
            "\"SBAMSvc.exe\": \"VIPRE\",\n" +
            "\"ccSvcHst.exe\": \"Norton杀毒\",\n" +
            "\"f-secure.exe\": \"冰岛\",\n" +
            "\"avp.exe\": \"Kaspersky\",\n" +
            "\"KvMonXP.exe\": \"江民杀毒\",\n" +
            "\"RavMonD.exe\": \"瑞星杀毒\",\n" +
            "\"Mcshield.exe\": \"McAfee\",\n" +
            "\"Tbmon.exe\": \"McAfee\",\n" +
            "\"Frameworkservice.exe\": \"McAfee\",\n" +
            "\"egui.exe\": \"ESET NOD32\",\n" +
            "\"ekrn.exe\": \"ESET NOD32\",\n" +
            "\"eguiProxy.exe\": \"ESET NOD32\",\n" +
            "\"kxetray.exe\": \"金山毒霸\",\n" +
            "\"knsdtray.exe\": \"可牛杀毒\",\n" +
            "\"TMBMSRV.exe\": \"趋势杀毒\",\n" +
            "\"avcenter.exe\": \"Avira(小红伞)\",\n" +
            "\"avguard.exe\": \"Avira(小红伞)\",\n" +
            "\"avgnt.exe\": \"Avira(小红伞)\",\n" +
            "\"sched.exe\": \"Avira(小红伞)\",\n" +
            "\"ashDisp.exe\": \"Avast网络安全\",\n" +
            "\"rtvscan.exe\": \"诺顿杀毒\",\n" +
            "\"ccapp.exe\": \"SymantecNorton\",\n" +
            "\"NPFMntor.exe\": \"Norton杀毒软件\",\n" +
            "\"ccSetMgr.exe\": \"赛门铁克\",\n" +
            "\"ccRegVfy.exe\": \"Norton杀毒软件\",\n" +
            "\"ksafe.exe\": \"金山卫士\",\n" +
            "\"QQPCRTP.exe\": \"QQ电脑管家\",\n" +
            "\"avgwdsvc.exe\": \"AVG杀毒\",\n" +
            "\"QUHLPSVC.exe\": \"QUICK HEAL杀毒\",\n" +
            "\"mssecess.exe\": \"微软杀毒\",\n" +
            "\"SavProgress.exe\": \"Sophos杀毒\",\n" +
            "\"SophosUI.exe\": \"Sophos杀毒\",\n" +
            "\"SophosFS.exe\": \"Sophos杀毒\",\n" +
            "\"SophosHealth.exe\": \"Sophos杀毒\",\n" +
            "\"SophosSafestore64.exe\": \"Sophos杀毒\",\n" +
            "\"SophosCleanM.exe\": \"Sophos杀毒\",\n" +
            "\"fsavgui.exe\": \"F-Secure杀毒\",\n" +
            "\"vsserv.exe\": \"比特梵德\",\n" +
            "\"remupd.exe\": \"熊猫卫士\",\n" +
            "\"FortiTray.exe\": \"飞塔\",\n" +
            "\"safedog.exe\": \"安全狗\",\n" +
            "\"parmor.exe\": \"木马克星\",\n" +
            "\"Iparmor.exe.exe\": \"木马克星\",\n" +
            "\"beikesan.exe\": \"贝壳云安全\",\n" +
            "\"KSWebShield.exe\": \"金山网盾\",\n" +
            "\"TrojanHunter.exe\": \"木马猎手\",\n" +
            "\"GG.exe\": \"巨盾网游安全盾\",\n" +
            "\"adam.exe\": \"绿鹰安全精灵\",\n" +
            "\"AST.exe\": \"超级巡警\",\n" +
            "\"ananwidget.exe\": \"墨者安全专家\",\n" +
            "\"AVK.exe\": \"AntiVirusKit\",\n" +
            "\"avg.exe\": \"AVG Anti-Virus\",\n" +
            "\"spidernt.exe\": \"Dr.web\",\n" +
            "\"avgaurd.exe\": \"Avira Antivir\",\n" +
            "\"vsmon.exe\": \"Zone Alarm\",\n" +
            "\"cpf.exe\": \"Comodo\",\n" +
            "\"outpost.exe\": \"Outpost Firewall\",\n" +
            "\"rfwmain.exe\": \"瑞星防火墙\",\n" +
            "\"kpfwtray.exe\": \"金山网镖\",\n" +
            "\"FYFireWall.exe\": \"风云防火墙\",\n" +
            "\"MPMon.exe\": \"微点主动防御\",\n" +
            "\"pfw.exe\": \"天网防火墙\",\n" +
            "\"BaiduSdSvc.exe\": \"百度杀毒-服务进程\",\n" +
            "\"BaiduSdTray.exe\": \"百度杀毒-托盘进程\",\n" +
            "\"BaiduSd.exe\": \"百度杀毒-主程序\",\n" +
            "\"SafeDogGuardCenter.exe\": \"安全狗\",\n" +
            "\"safedogupdatecenter.exe\": \"安全狗\",\n" +
            "\"safedogguardcenter.exe\": \"安全狗\",\n" +
            "\"SafeDogSiteIIS.exe\": \"安全狗\",\n" +
            "\"SafeDogTray.exe\": \"安全狗\",\n" +
            "\"SafeDogServerUI.exe\": \"安全狗\",\n" +
            "\"D_Safe_Manage.exe\": \"D盾\",\n" +
            "\"d_manage.exe\": \"D盾\",\n" +
            "\"yunsuo_agent_service.exe\": \"云锁\",\n" +
            "\"yunsuo_agent_daemon.exe\": \"云锁\",\n" +
            "\"HwsPanel.exe\": \"护卫神\",\n" +
            "\"hws_ui.exe\": \"护卫神\",\n" +
            "\"hws.exe\": \"护卫神\",\n" +
            "\"hwsd.exe\": \"护卫神\",\n" +
            "\"hipsdaemon.exe\": \"火绒\",\n" +
            "\"hipstray.exe\": \"火绒\",\n" +
            "\"wsctrl.exe\": \"火绒\",\n" +
            "\"usysdiag.exe\": \"火绒\",\n" +
            "\"SPHINX.exe\": \"SPHINX防火墙\",\n" +
            "\"bddownloader.exe\": \"百度卫士\",\n" +
            "\"baiduansvx.exe\": \"百度卫士-主进程\",\n" +
            "\"AvastUI.exe\": \"Avast!5主程序\",\n" +
            "\"emet_agent.exe\": \"EMET\",\n" +
            "\"emet_service.exe\": \"EMET\",\n" +
            "\"firesvc.exe\": \"McAfee\",\n" +
            "\"firetray.exe\": \"McAfee\",\n" +
            "\"hipsvc.exe\": \"McAfee\",\n" +
            "\"mfevtps.exe\": \"McAfee\",\n" +
            "\"mcafeefire.exe\": \"McAfee\",\n" +
            "\"scan32.exe\": \"McAfee\",\n" +
            "\"shstat.exe\": \"McAfee\",\n" +
            "\"vstskmgr.exe\": \"McAfee\",\n" +
            "\"engineserver.exe\": \"McAfee\",\n" +
            "\"mfeann.exe\": \"McAfee\",\n" +
            "\"mcscript.exe\": \"McAfee\",\n" +
            "\"updaterui.exe\": \"McAfee\",\n" +
            "\"udaterui.exe\": \"McAfee\",\n" +
            "\"naprdmgr.exe\": \"McAfee\",\n" +
            "\"cleanup.exe\": \"McAfee\",\n" +
            "\"cmdagent.exe\": \"McAfee\",\n" +
            "\"frminst.exe\": \"McAfee\",\n" +
            "\"mcscript_inuse.exe\": \"McAfee\",\n" +
            "\"mctray.exe\": \"McAfee\",\n" +
            "\"_avp32.exe\": \"卡巴斯基\",\n" +
            "\"_avpcc.exe\": \"卡巴斯基\",\n" +
            "\"_avpm.exe\": \"卡巴斯基\",\n" +
            "\"aAvgApi.exe\": \"AVG\",\n" +
            "\"ackwin32.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"alertsvc.exe\": \"Norton AntiVirus\",\n" +
            "\"alogserv.exe\": \"McAfee VirusScan\",\n" +
            "\"anti-trojan.exe\": \"Anti-Trojan Elite\",\n" +
            "\"arr.exe\": \"Application Request Route\",\n" +
            "\"atguard.exe\": \"AntiVir\",\n" +
            "\"atupdater.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"atwatch.exe\": \"Mustek\",\n" +
            "\"au.exe\": \"NSIS\",\n" +
            "\"aupdate.exe\": \"Symantec\",\n" +
            "\"auto-protect.nav80try.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"autodown.exe\": \"AntiVirus AutoUpdater\",\n" +
            "\"avconsol.exe\": \"McAfee\",\n" +
            "\"avgcc32.exe\": \"AVG\",\n" +
            "\"avgctrl.exe\": \"AVG\",\n" +
            "\"avgemc.exe\": \"AVG\",\n" +
            "\"avgrsx.exe\": \"AVG\",\n" +
            "\"avgserv.exe\": \"AVG\",\n" +
            "\"avgserv9.exe\": \"AVG\",\n" +
            "\"avgw.exe\": \"AVG\",\n" +
            "\"avkpop.exe\": \"G DATA SOFTWARE AG\",\n" +
            "\"avkserv.exe\": \"G DATA SOFTWARE AG\",\n" +
            "\"avkservice.exe\": \"G DATA SOFTWARE AG\",\n" +
            "\"avkwctl9.exe\": \"G DATA SOFTWARE AG\",\n" +
            "\"avltmain.exe\": \"Panda Software Aplication\",\n" +
            "\"avnt.exe\": \"H+BEDV Datentechnik GmbH\",\n" +
            "\"avp32.exe\": \"Kaspersky Anti-Virus\",\n" +
            "\"avpcc.exe\": \" Kaspersky AntiVirus\",\n" +
            "\"avpdos32.exe\": \" Kaspersky AntiVirus\",\n" +
            "\"avpm.exe\": \" Kaspersky AntiVirus\",\n" +
            "\"avptc32.exe\": \" Kaspersky AntiVirus\",\n" +
            "\"avpupd.exe\": \" Kaspersky AntiVirus\",\n" +
            "\"avsynmgr.exe\": \"McAfee\",\n" +
            "\"avwin.exe\": \" H+BEDV\",\n" +
            "\"bargains.exe\": \"Exact Advertising SpyWare\",\n" +
            "\"beagle.exe\": \"Avast\",\n" +
            "\"blackd.exe\": \"BlackICE\",\n" +
            "\"blackice.exe\": \"BlackICE\",\n" +
            "\"blink.exe\": \"micromedia\",\n" +
            "\"blss.exe\": \"CBlaster\",\n" +
            "\"bootwarn.exe\": \"Symantec\",\n" +
            "\"bpc.exe\": \"Grokster\",\n" +
            "\"brasil.exe\": \"Exact Advertising\",\n" +
            "\"ccevtmgr.exe\": \"Norton Internet Security\",\n" +
            "\"cdp.exe\": \"CyberLink Corp.\",\n" +
            "\"cfd.exe\": \"Motive Communications\",\n" +
            "\"cfgwiz.exe\": \" Norton AntiVirus\",\n" +
            "\"claw95.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"claw95cf.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"clean.exe\": \"windows流氓软件清理大师\",\n" +
            "\"cleaner.exe\": \"windows流氓软件清理大师\",\n" +
            "\"cleaner3.exe\": \"windows流氓软件清理大师\",\n" +
            "\"cleanpc.exe\": \"windows流氓软件清理大师\",\n" +
            "\"cpd.exe\": \"McAfee\",\n" +
            "\"ctrl.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"cv.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"defalert.exe\": \"Symantec\",\n" +
            "\"defscangui.exe\": \"Symantec\",\n" +
            "\"defwatch.exe\": \"Norton Antivirus\",\n" +
            "\"doors.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"dpf.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"dpps2.exe\": \"PanicWare\",\n" +
            "\"dssagent.exe\": \"Broderbund\",\n" +
            "\"ecengine.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"emsw.exe\": \"Alset Inc\",\n" +
            "\"ent.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"espwatch.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"ethereal.exe\": \"RationalClearCase\",\n" +
            "\"exe.avxw.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"expert.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"f-prot95.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"fameh32.exe\": \"F-Secure\",\n" +
            "\"fast.exe\": \" FastUsr\",\n" +
            "\"fch32.exe\": \"F-Secure\",\n" +
            "\"fih32.exe\": \"F-Secure\",\n" +
            "\"findviru.exe\": \"F-Secure\",\n" +
            "\"firewall.exe\": \"AshampooSoftware\",\n" +
            "\"fnrb32.exe\": \"F-Secure\",\n" +
            "\"fp-win.exe\": \" F-Prot Antivirus OnDemand\",\n" +
            "\"fsaa.exe\": \"F-Secure\",\n" +
            "\"fsav.exe\": \"F-Secure\",\n" +
            "\"fsav32.exe\": \"F-Secure\",\n" +
            "\"fsav530stbyb.exe\": \"F-Secure\",\n" +
            "\"fsav530wtbyb.exe\": \"F-Secure\",\n" +
            "\"fsav95.exe\": \"F-Secure\",\n" +
            "\"fsgk32.exe\": \"F-Secure\",\n" +
            "\"fsm32.exe\": \"F-Secure\",\n" +
            "\"fsma32.exe\": \"F-Secure\",\n" +
            "\"fsmb32.exe\": \"F-Secure\",\n" +
            "\"gbmenu.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"guard.exe\": \"ewido\",\n" +
            "\"guarddog.exe\": \"ewido\",\n" +
            "\"htlog.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"htpatch.exe\": \"Silicon Integrated Systems Corporation\",\n" +
            "\"hwpe.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"iamapp.exe\": \"Symantec\",\n" +
            "\"iamserv.exe\": \"Symantec\",\n" +
            "\"iamstats.exe\": \"Symantec\",\n" +
            "\"iedriver.exe\": \" Urlblaze.com\",\n" +
            "\"iface.exe\": \"Panda Antivirus Module\",\n" +
            "\"infus.exe\": \"Infus Dialer\",\n" +
            "\"infwin.exe\": \"Msviewparasite\",\n" +
            "\"intdel.exe\": \"Inet Delivery\",\n" +
            "\"intren.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"jammer.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"kavpf.exe\": \"Kapersky\",\n" +
            "\"kazza.exe\": \"Kapersky\",\n" +
            "\"keenvalue.exe\": \"EUNIVERSE INC\",\n" +
            "\"launcher.exe\": \"Intercort Systems\",\n" +
            "\"ldpro.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"ldscan.exe\": \"Windows Trojans Inspector\",\n" +
            "\"localnet.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"luall.exe\": \"Symantec\",\n" +
            "\"luau.exe\": \"Symantec\",\n" +
            "\"lucomserver.exe\": \"Norton\",\n" +
            "\"mcagent.exe\": \"McAfee\",\n" +
            "\"mcmnhdlr.exe\": \"McAfee\",\n" +
            "\"mctool.exe\": \"McAfee\",\n" +
            "\"mcupdate.exe\": \"McAfee\",\n" +
            "\"mcvsrte.exe\": \"McAfee\",\n" +
            "\"mcvsshld.exe\": \"McAfee\",\n" +
            "\"mfin32.exe\": \"MyFreeInternetUpdate\",\n" +
            "\"mfw2en.exe\": \"MyFreeInternetUpdate\",\n" +
            "\"mfweng3.02d30.exe\": \"MyFreeInternetUpdate\",\n" +
            "\"mgavrtcl.exe\": \"McAfee\",\n" +
            "\"mgavrte.exe\": \"McAfee\",\n" +
            "\"mghtml.exe\": \"McAfee\",\n" +
            "\"mgui.exe\": \"BullGuard\",\n" +
            "\"minilog.exe\": \"Zone Labs Inc\",\n" +
            "\"mmod.exe\": \"EzulaInc\",\n" +
            "\"mostat.exe\": \"WurldMediaInc\",\n" +
            "\"mpfagent.exe\": \"McAfee\",\n" +
            "\"mpfservice.exe\": \"McAfee\",\n" +
            "\"mpftray.exe\": \"McAfee\",\n" +
            "\"mscache.exe\": \"Integrated Search Technologies Spyware\",\n" +
            "\"mscman.exe\": \"OdysseusMarketingInc\",\n" +
            "\"msmgt.exe\": \"Total Velocity Spyware\",\n" +
            "\"msvxd.exe\": \"W32/Datom-A\",\n" +
            "\"mwatch.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"nav.exe\": \"Reuters Limited\",\n" +
            "\"navapsvc.exe\": \"Norton AntiVirus\",\n" +
            "\"navapw32.exe\": \"Norton AntiVirus\",\n" +
            "\"navw32.exe\": \"Norton Antivirus\",\n" +
            "\"ndd32.exe\": \"诺顿磁盘医生\",\n" +
            "\"neowatchlog.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"netutils.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"nisserv.exe\": \"Norton\",\n" +
            "\"nisum.exe\": \"Norton\",\n" +
            "\"nmain.exe\": \"Norton\",\n" +
            "\"nod32.exe\": \"ESET Smart Security\",\n" +
            "\"norton_internet_secu_3.0_407.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"notstart.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"nprotect.exe\": \"Symantec\",\n" +
            "\"npscheck.exe\": \"Norton\",\n" +
            "\"npssvc.exe\": \"Norton\",\n" +
            "\"ntrtscan.exe\": \"趋势反病毒应用程序\",\n" +
            "\"nui.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"otfix.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"outpostinstall.exe\": \"Outpost\",\n" +
            "\"patch.exe\": \"趋势科技\",\n" +
            "\"pavw.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"pcscan.exe\": \"趋势科技\",\n" +
            "\"pdsetup.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"persfw.exe\": \"Tiny Personal Firewall\",\n" +
            "\"pgmonitr.exe\": \"PromulGate SpyWare\",\n" +
            "\"pingscan.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"platin.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"pop3trap.exe\": \"PC-cillin\",\n" +
            "\"poproxy.exe\": \"NortonAntiVirus\",\n" +
            "\"popscan.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"powerscan.exe\": \"Integrated Search Technologies\",\n" +
            "\"ppinupdt.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"pptbc.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"ppvstop.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"prizesurfer.exe\": \"Prizesurfer\",\n" +
            "\"prmt.exe\": \"OpiStat\",\n" +
            "\"prmvr.exe\": \"Adtomi\",\n" +
            "\"processmonitor.exe\": \"Sysinternals\",\n" +
            "\"proport.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"protectx.exe\": \"ProtectX\",\n" +
            "\"pspf.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"purge.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"qconsole.exe\": \"Norton AntiVirus Quarantine Console\",\n" +
            "\"qserver.exe\": \"Norton Internet Security\",\n" +
            "\"rapapp.exe\": \"BlackICE\",\n" +
            "\"rb32.exe\": \"RapidBlaster\",\n" +
            "\"rcsync.exe\": \"PrizeSurfer\",\n" +
            "\"realmon.exe\": \"Realmon \",\n" +
            "\"rescue.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"rescue32.exe\": \"卡巴斯基互联网安全套装\",\n" +
            "\"rshell.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"rtvscn95.exe\": \"Real-time virus scanner \",\n" +
            "\"rulaunch.exe\": \"McAfee User Interface\",\n" +
            "\"run32dll.exe\": \"PAL PC Spy\",\n" +
            "\"safeweb.exe\": \"PSafe Tecnologia\",\n" +
            "\"sbserv.exe\": \"Norton Antivirus\",\n" +
            "\"scrscan.exe\": \"360杀毒\",\n" +
            "\"sfc.exe\": \"System file checker\",\n" +
            "\"sh.exe\": \"MKS Toolkit for Win3\",\n" +
            "\"showbehind.exe\": \"MicroSmarts Enterprise Component \",\n" +
            "\"soap.exe\": \"System Soap Pro\",\n" +
            "\"sofi.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"sperm.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"supporter5.exe\": \"eScorcher反病毒\",\n" +
            "\"symproxysvc.exe\": \"Symantec\",\n" +
            "\"symtray.exe\": \"Symantec\",\n" +
            "\"tbscan.exe\": \"ThunderBYTE\",\n" +
            "\"tc.exe\": \"TimeCalende\",\n" +
            "\"titanin.exe\": \"TitanHide\",\n" +
            "\"tvmd.exe\": \"Total Velocity\",\n" +
            "\"tvtmd.exe\": \" Total Velocity\",\n" +
            "\"vettray.exe\": \"eTrust\",\n" +
            "\"vir-help.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"vnpc3000.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"vpc32.exe\": \"Symantec\",\n" +
            "\"vpc42.exe\": \"Symantec\",\n" +
            "\"vshwin32.exe\": \"McAfee\",\n" +
            "\"vsmain.exe\": \"McAfee\",\n" +
            "\"vsstat.exe\": \"McAfee\",\n" +
            "\"wfindv32.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"zapro.exe\": \"Zone Alarm\",\n" +
            "\"zonealarm.exe\": \"Zone Alarm\",\n" +
            "\"AVPM.exe\": \"Kaspersky\",\n" +
            "\"A2CMD.exe\": \"Emsisoft Anti-Malware\",\n" +
            "\"A2SERVICE.exe\": \"a-squared free\",\n" +
            "\"A2FREE.exe\": \"a-squared Free\",\n" +
            "\"ADVCHK.exe\": \"Norton AntiVirus\",\n" +
            "\"AGB.exe\": \"安天防线\",\n" +
            "\"AHPROCMONSERVER.exe\": \"安天防线\",\n" +
            "\"AIRDEFENSE.exe\": \"AirDefense\",\n" +
            "\"ALERTSVC.exe\": \"Norton AntiVirus\",\n" +
            "\"AVIRA.exe\": \"小红伞杀毒\",\n" +
            "\"AMON.exe\": \"Tiny Personal Firewall\",\n" +
            "\"AVZ.exe\": \"AVZ\",\n" +
            "\"ANTIVIR.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"APVXDWIN.exe\": \"熊猫卫士\",\n" +
            "\"ASHMAISV.exe\": \"Alwil\",\n" +
            "\"ASHSERV.exe\": \"Avast Anti-virus\",\n" +
            "\"ASHSIMPL.exe\": \"AVAST!VirusCleaner\",\n" +
            "\"ASHWEBSV.exe\": \"Avast\",\n" +
            "\"ASWUPDSV.exe\": \"Avast\",\n" +
            "\"ASWSCAN.exe\": \"Avast\",\n" +
            "\"AVCIMAN.exe\": \"熊猫卫士\",\n" +
            "\"AVCONSOL.exe\": \"McAfee\",\n" +
            "\"AVENGINE.exe\": \"熊猫卫士\",\n" +
            "\"AVESVC.exe\": \"Avira AntiVir Security Service\",\n" +
            "\"AVEVL32.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"AVGAM.exe\": \"AVG\",\n" +
            "\"AVGCC.exe\": \"AVG\",\n" +
            "\"AVGCHSVX.exe\": \"AVG\",\n" +
            "\"AVGCSRVX\": \"AVG\",\n" +
            "\"AVGNSX.exe\": \"AVG\",\n" +
            "\"AVGCC32.exe\": \"AVG\",\n" +
            "\"AVGCTRL.exe\": \"AVG\",\n" +
            "\"AVGEMC.exe\": \"AVG\",\n" +
            "\"AVGFWSRV.exe\": \"AVG\",\n" +
            "\"AVGNTMGR.exe\": \"AVG\",\n" +
            "\"AVGSERV.exe\": \"AVG\",\n" +
            "\"AVGTRAY.exe\": \"AVG\",\n" +
            "\"AVGUPSVC.exe\": \"AVG\",\n" +
            "\"AVINITNT.exe\": \"Command AntiVirus for NT Server\",\n" +
            "\"AVPCC.exe\": \"Kaspersky\",\n" +
            "\"AVSERVER.exe\": \"Kerio MailServer\",\n" +
            "\"AVSCHED32.exe\": \"H+BEDV\",\n" +
            "\"AVSYNMGR.exe\": \"McAfee\",\n" +
            "\"AVWUPSRV.exe\": \"H+BEDV\",\n" +
            "\"BDSWITCH.exe\": \"BitDefender Module\",\n" +
            "\"BLACKD.exe\": \"BlackICE\",\n" +
            "\"CCEVTMGR.exe\": \"Symantec\",\n" +
            "\"CFP.exe\": \"COMODO\",\n" +
            "\"CLAMWIN.exe\": \"ClamWin Portable\",\n" +
            "\"CUREIT.exe\": \"DrWeb CureIT\",\n" +
            "\"DEFWATCH.exe\": \"Norton Antivirus\",\n" +
            "\"DRWADINS.exe\": \"Dr.Web\",\n" +
            "\"DRWEB.exe\": \"Dr.Web\",\n" +
            "\"DEFENDERDAEMON.exe\": \"ShadowDefender\",\n" +
            "\"EWIDOCTRL.exe\": \"Ewido Security Suite\",\n" +
            "\"EZANTIVIRUSREGISTRATIONCHECK.exe\": \"e-Trust Antivirus\",\n" +
            "\"FIREWALL.exe\": \"AshampooSoftware\",\n" +
            "\"FPROTTRAY.exe\": \"F-PROT Antivirus\",\n" +
            "\"FPWIN.exe\": \"Verizon\",\n" +
            "\"FRESHCLAM.exe\": \"ClamAV\",\n" +
            "\"FSAV32.exe\": \"F-Secure\",\n" +
            "\"FSBWSYS.exe\": \"F-secure\",\n" +
            "\"FSDFWD.exe\": \"F-Secure\",\n" +
            "\"FSGK32.exe\": \"F-Secure\",\n" +
            "\"FSGK32ST.exe\": \"F-Secure\",\n" +
            "\"FSMA32.exe\": \"F-Secure\",\n" +
            "\"FSMB32.exe\": \"F-Secure\",\n" +
            "\"FSSM32.exe\": \"F-Secure\",\n" +
            "\"GUARDGUI.exe\": \"网游保镖\",\n" +
            "\"GUARDNT.exe\": \"IKARUS\",\n" +
            "\"IAMAPP.exe\": \"Symantec\",\n" +
            "\"INOCIT.exe\": \"eTrust\",\n" +
            "\"INORPC.exe\": \"eTrust\",\n" +
            "\"INORT.exe\": \"eTrust\",\n" +
            "\"INOTASK.exe\": \"eTrust\",\n" +
            "\"INOUPTNG.exe\": \"eTrust\",\n" +
            "\"ISAFE.exe\": \"eTrust\",\n" +
            "\"KAV.exe\": \"Kaspersky\",\n" +
            "\"KAVMM.exe\": \"Kaspersky\",\n" +
            "\"KAVPF.exe\": \"Kaspersky\",\n" +
            "\"KAVPFW.exe\": \"Kaspersky\",\n" +
            "\"KAVSTART.exe\": \"Kaspersky\",\n" +
            "\"KAVSVC.exe\": \"Kaspersky\",\n" +
            "\"KAVSVCUI.exe\": \"Kaspersky\",\n" +
            "\"KMAILMON.exe\": \"金山毒霸\",\n" +
            "\"MCAGENT.exe\": \"McAfee\",\n" +
            "\"MCMNHDLR.exe\": \"McAfee\",\n" +
            "\"MCREGWIZ.exe\": \"McAfee\",\n" +
            "\"MCUPDATE.exe\": \"McAfee\",\n" +
            "\"MCVSSHLD.exe\": \"McAfee\",\n" +
            "\"MINILOG.exe\": \"Zone Alarm\",\n" +
            "\"MYAGTSVC.exe\": \"McAfee\",\n" +
            "\"MYAGTTRY.exe\": \"McAfee\",\n" +
            "\"NAVAPSVC.exe\": \"Norton\",\n" +
            "\"NAVAPW32.exe\": \"Norton\",\n" +
            "\"NAVLU32.exe\": \"Norton\",\n" +
            "\"NAVW32.exe\": \"Norton Antivirus\",\n" +
            "\"NEOWATCHLOG.exe\": \"NeoWatch\",\n" +
            "\"NEOWATCHTRAY.exe\": \"NeoWatch\",\n" +
            "\"NISSERV.exe\": \"Norton\",\n" +
            "\"NISUM.exe\": \"Norton\",\n" +
            "\"NMAIN.exe\": \"Norton\",\n" +
            "\"NOD32.exe\": \"ESET NOD32\",\n" +
            "\"NPFMSG.exe\": \"Norman个人防火墙\",\n" +
            "\"NPROTECT.exe\": \"Symantec\",\n" +
            "\"NSMDTR.exe\": \"Norton\",\n" +
            "\"NTRTSCAN.exe\": \"趋势科技\",\n" +
            "\"OFCPFWSVC.exe\": \"OfficeScanNT\",\n" +
            "\"ONLINENT.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"OP_MON.exe\": \" OutpostFirewall\",\n" +
            "\"PAVFIRES.exe\": \"熊猫卫士\",\n" +
            "\"PAVFNSVR.exe\": \"熊猫卫士\",\n" +
            "\"PAVKRE.exe\": \"熊猫卫士\",\n" +
            "\"PAVPROT.exe\": \"熊猫卫士\",\n" +
            "\"PAVPROXY.exe\": \"熊猫卫士\",\n" +
            "\"PAVPRSRV.exe\": \"熊猫卫士\",\n" +
            "\"PAVSRV51.exe\": \"熊猫卫士\",\n" +
            "\"PAVSS.exe\": \"熊猫卫士\",\n" +
            "\"PCCGUIDE.exe\": \"PC-cillin\",\n" +
            "\"PCCIOMON.exe\": \"PC-cillin\",\n" +
            "\"PCCNTMON.exe\": \"PC-cillin\",\n" +
            "\"PCCPFW.exe\": \"趋势科技\",\n" +
            "\"PCCTLCOM.exe\": \"趋势科技\",\n" +
            "\"PCTAV.exe\": \"PC Tools AntiVirus\",\n" +
            "\"PERSFW.exe\": \"Tiny Personal Firewall\",\n" +
            "\"PERVAC.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"PESTPATROL.exe\": \"Ikarus\",\n" +
            "\"PREVSRV.exe\": \"熊猫卫士\",\n" +
            "\"RTVSCN95.exe\": \"Real-time Virus Scanner\",\n" +
            "\"SAVADMINSERVICE.exe\": \"SAV\",\n" +
            "\"SAVMAIN.exe\": \"SAV\",\n" +
            "\"SAVSCAN.exe\": \"SAV\",\n" +
            "\"SDHELP.exe\": \"Spyware Doctor\",\n" +
            "\"SHSTAT.exe\": \"McAfee\",\n" +
            "\"SPBBCSVC.exe\": \"Symantec\",\n" +
            "\"SPIDERCPL.exe\": \"Dr.Web\",\n" +
            "\"SPIDERML.exe\": \"Dr.Web\",\n" +
            "\"SPIDERUI.exe\": \"Dr.Web\",\n" +
            "\"SPYBOTSD.exe\": \"Spybot \",\n" +
            "\"SWAGENT.exe\": \"SonicWALL\",\n" +
            "\"SWDOCTOR.exe\": \"SonicWALL\",\n" +
            "\"SWNETSUP.exe\": \"Sophos\",\n" +
            "\"SYMLCSVC.exe\": \"Symantec\",\n" +
            "\"SYMPROXYSVC.exe\": \"Symantec\",\n" +
            "\"SYMSPORT.exe\": \"Sysmantec\",\n" +
            "\"SYMWSC.exe\": \"Sysmantec\",\n" +
            "\"SYNMGR.exe\": \"Sysmantec\",\n" +
            "\"TMLISTEN.exe\": \"趋势科技\",\n" +
            "\"TMNTSRV.exe\": \"趋势科技\",\n" +
            "\"TMPROXY.exe\": \"趋势科技\",\n" +
            "\"TNBUTIL.exe\": \"Anti-Virus\",\n" +
            "\"VBA32ECM.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"VBA32IFS.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"VBA32PP3.exe\": \"已知杀软进程,名称暂未收录\",\n" +
            "\"VCRMON.exe\": \"VirusChaser\",\n" +
            "\"VRMONNT.exe\": \"HAURI\",\n" +
            "\"VRMONSVC.exe\": \"HAURI\",\n" +
            "\"VSHWIN32.exe\": \"McAfee\",\n" +
            "\"VSSTAT.exe\": \"McAfee\",\n" +
            "\"XCOMMSVR.exe\": \"BitDefender\",\n" +
            "\"ZONEALARM.exe\": \"Zone Alarm\",\n" +
            "\"360rp.exe\": \"360杀毒\",\n" +
            "\"afwServ.exe\": \" Avast Antivirus\",\n" +
            "\"safeboxTray.exe\": \"360杀毒\",\n" +
            "\"360safebox.exe\": \"360杀毒\",\n" +
            "\"QQPCTray.exe\": \"QQ电脑管家\",\n" +
            "\"KSafeTray.exe\": \"金山毒霸\",\n" +
            "\"KSafeSvc.exe\": \"金山毒霸\",\n" +
            "\"KWatch.exe\": \"金山毒霸\",\n" +
            "\"gov_defence_service.exe\": \"云锁\",\n" +
            "\"gov_defence_daemon.exe\": \"云锁\",\n" +
            "\"smartscreen.exe\": \"Windows Defender\",\n" +
            "\"securityhealthservice.exe\": \"Windows Defender\",\n" +
            "\"SunloginClient.exe\": \"向日葵\",\n" +
            "\"finalshell.exe\": \"finalshell终端管理\",\n" +
            "\"navicat.exe\": \"数据库管理\",\n" +
            "\"AliSecGuard.exe\": \"阿里云盾\",\n" +
            "\"AliYunDunUpdate.exe\": \"阿里云盾\",\n" +
            "\"AliYunDun.exe\": \"阿里云盾\",\n" +
            "\"CmsGoAgent.windows-amd64.\": \"阿里云监控\",\n" +
            "\"vmnetdhcp.exe\": \"vm虚拟机\",\n" +
            "\"ToDesk.exe\": \"ToDesk远控\",\n" +
            "\"ToDesk_Service.exe\": \"ToDesk远控\",";
    @FXML
    private TextArea textArea_res;

    @FXML
    private TextArea textArea_check;

    @FXML
    private Button button_check;

    @FXML
    void clicked_check(MouseEvent event) {
        String tasklist = textArea_check.getText();
        Map<String, String> exelist = new HashMap<String, String>();
        String[] exetestlist = list.split("\n");

        for (String str : exetestlist)
            if (str != null) {
                exelist.put(str.split(": ")[0], str.split(":")[1]);
            }
        String[] resultlist2 = tasklist.split("\n"); // 将读取的进程通过换行分割成字符串组
        String[] resultlist22;
        resultlist22 = Methods.taskexechange(resultlist2);
        String finallist = Methods.ifexe(resultlist22, exelist);
        String res;
        try {
            res = new String(finallist.getBytes());
            textArea_res.setText(res);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
