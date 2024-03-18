package controller;

import javafx.scene.control.TextArea;

public class AboutController {

    public TextArea textArea_About;
    public void initialize(){

        textArea_About.appendText(
                "\n                                      R4gApt集成化平台\n\n"
        );
        textArea_About.appendText(
                "\n 一. ATT模块"
        );

        textArea_About.appendText(
                "\n     1. 用友serial漏洞大部分为接口探测，请ATT根据实际情况进行判断"
        );
        textArea_About.appendText(
                "\n     2. 部分漏洞为确保准确性，请先CHK检测，再ATT利用"
        );
        textArea_About.appendText(
                "\n     3. 使用ALL进行漏洞探测时，由于多线程等其他原因会产生误报"
        );

        textArea_About.appendText(
                "\n     4. 文件上传漏洞利用UPLOAD上传文件后ATT进行上传，并使CMD栏为空，请先CHK尝试上传后再利用"
        );

        textArea_About.appendText(
                "\n     5. 命令执行漏洞利用CMD写入命令后ATT进行执行，请先CHK尝试上传后再利用"
        );
        textArea_About.appendText(
                "\n     6. CLEAR FILE按钮可清除所选文件信息"
        );
        textArea_About.appendText(
                "\n     7. 根据执行结果提示并选择其他模块利用"
        );
        textArea_About.appendText(
                "\n     8. ATT模块目前集成poc情况共近230余个，如下:"
        );
        textArea_About.appendText(
                "\n            (1).用友NC&反序列化接口----------------------------------fofa:title=\"YONYOU NC\"\n"+
                "            (2).用友GRP--------------------------------------------fofa:app=\"yonyou-GRP-U8\"\n"+
                "            (3).用友NCCloud----------------------------------------fofa:body=\"nccloud\"\n"+
                "            (4).用友tplus------------------------------------------fofa:app=\"畅捷通-TPlus\"\n"+
                "            (5).用友U8C--------------------------------------------fofa:app=\"用友-U8-Cloud\"\n"+
                "            (6).用友ufida------------------------------------------fofa:body=\"ufida.ico\"\n"+
                "            (7).泛微Ecology----------------------------------------fofa:app=\"泛微-协同办公OA\"\n"+
                "            (8).泛微Emobile----------------------------------------fofa:title=\"移动管理平台-企业管理\"\n"+
                "            (9).泛微Eoffice----------------------------------------fofa:app=\"泛微-EOffice\"\n"+
                "            (10).蓝凌OA--------------------------------------------fofa:app=\"Landray-OA系统\"\n"+
                "            (11).蓝凌EIS-------------------------------------------fofa:app=\"Landray-EIS智慧协同平台\"\n"+
                "            (12).万户OA--------------------------------------------fofa:body=\"/defaultroot/\"\n"+
                "            (13).致远A6A8------------------------------------------fofa:app=\"致远互联-OA\"\n"+
                "            (14).致远MServer---------------------------------------fofa:body=\"/mobile_portal/\"\n"+
                "            (15).致远yyoa------------------------------------------fofa:body=\"yyoa\" && app=\"致远互联-OA\"\n"+
                "            (16).通达OA--------------------------------------------fofa:app=\"TDXK-通达OA\"\n"+
                "            (17).帆软组件-------------------------------------------fofa:\"Powered by 帆软\"\n"+
                "            (18).金蝶Apusic----------------------------------------fofa:header=\"Apusic\"\n"+
                "            (19).金蝶EAS-------------------------------------------fofa:app=\"Kingdee-EAS\"\n"+
                "            (20).金蝶云OA------------------------------------------fofa:app=\"金蝶云星空-管理中心\"\n"+
                "            (21).金和OA--------------------------------------------fofa:app=\"金和网络-金和OA\"\n"+
                "            (22).红帆OA--------------------------------------------fofa:app=\"红帆-ioffice\"\n"+
                "            (23).宏景HCM--------------------------------------------fofa:app=\"HJSOFT-HCM\"\n"+
                "            (24).亿赛通---------------------------------------------fofa:app=\"亿赛通-电子文档安全管理系统\"\n"+
                "            (23).飞企互联-------------------------------------------fofa:app=\"FE-协作平台\"\n"


        );
        textArea_About.appendText(
                "\n 二. MemShell模块"
        );
        textArea_About.appendText(
                "\n     1. 支持冰蝎3.0、哥斯拉、蚁剑、suo5、cmdecho、neoReGeorg、自定义内存马"
        );
        textArea_About.appendText(
                "\n     2. 支持输出源码、Base64、hex、gzip格式payload"
        );textArea_About.appendText(
                "\n     3. 用友NC反序列化 集成接口反序列化（测试环境）"
        );textArea_About.appendText(
                "\n     4. 用友U8C反序列化 集接口反序列化（测试环境）"
        );textArea_About.appendText(
                "\n     5. 亿赛通XStream反序列化 集接口反序列化（测试环境）"
        );textArea_About.appendText(
                "\n     6. 用友NC内存马支持bypass脏数据传入，默认为100字节\n"
        );textArea_About.appendText(
                "\n 三. Sqlmap模块"
        );textArea_About.appendText(
                "\n     根据提示输入内容执行，集成调用sqlmap\n"
        );textArea_About.appendText(
                "\n 四. Crypt模块"
        );textArea_About.appendText(
                "\n     1. 各类OA加解密"
        );textArea_About.appendText(
                "\n     2. 各类编码解码"
        );textArea_About.appendText(
                "\n     3. Class类反编译、class字节码生成(base64格式、gzip-base64格式)"
        );textArea_About.appendText(
                "\n     4. class反编译仅文件读取、base64格式(yv66)、gzip-base64格式(H4sI)可反编译\n"
        );textArea_About.appendText(
                "\n 五. TaskList模块"
        );textArea_About.appendText(
                "\n     保留Apt-T00ls功能:杀软识别\n"
        );textArea_About.appendText(
                "\n 六. Command Create模块"
        );textArea_About.appendText(
                "\n     保留Apt-T00ls功能:常用命令创建\n"
        );

    }
}
