package controller;

import cn.hutool.core.util.StrUtil;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Window;

import java.io.*;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class SqlmapController {
    public TextField pythonText;
    public TextField proxyText;
    public TextField tamperText;
    public TextField techniqueText;
    public TextField dbtypeText;
    public TextField targetText;
    public TextField sqlmapText;
    public TextField postText;
    public TextField defineText;
    public Button start;
    public TextArea textArea_exec;
    @FXML
    private TextArea textArea_sqlmap;

    @FXML
    private Button clear;

    @FXML
    private Button Sqlmap;
    private Process process;

    @FXML
    private void clearinfo(){
        Platform.runLater(() -> {
            textArea_sqlmap.clear();
            textArea_exec.clear();
        });
    }
    @FXML
    private void stop() {

        try {
            Runtime.getRuntime().exec("taskkill /f /im python*");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @FXML
    private void SqlmapCmd(String[] cmd) {
        textArea_sqlmap.setWrapText(true);
        textArea_exec.setWrapText(true);
        String result = Paths.get("").toAbsolutePath().toString()+"> ";
        for (String element : cmd) {
            result += element+" ";
        }
        String finalResult = result;
        Platform.runLater(() -> {
            textArea_exec.appendText(finalResult);
        });
        ProcessBuilder pb = new ProcessBuilder(cmd);
        try {
            process = pb.start();
            new Thread(() -> {
                try {
                    Reader reader = new InputStreamReader(process.getInputStream());
                    int ch;
                    while ((ch = reader.read()) != -1) {
                        int finalCh = ch;
                        Platform.runLater(() -> textArea_sqlmap.appendText(String.valueOf((char) finalCh)));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void SqlStart(ActionEvent actionEvent) {
        String python = pythonText.getText();
        String proxy = proxyText.getText();
        String tamper = tamperText.getText();
        String technique = techniqueText.getText();
        String dbtype = dbtypeText.getText();
        String target =  targetText.getText();
        String sqlmap = sqlmapText.getText();
        String post = postText.getText();
        String define = defineText.getText();
        textArea_exec.clear();
        textArea_sqlmap.clear();
        if(StrUtil.isBlank(python)){
            Platform.runLater(() -> {
                textArea_exec.appendText("\n");
                textArea_exec.appendText("请填写python命令,仅支持python2");
            });
            return;
        }
        if(StrUtil.isBlank(sqlmap)){
            Platform.runLater(() -> {
                textArea_exec.appendText("\n");
                textArea_exec.appendText("请填写sqlmap路径");
            });
            return;
        }
        if(StrUtil.isBlank(target)){
            Platform.runLater(() -> {
                textArea_exec.appendText("\n");
                textArea_exec.appendText("请填写target,支持url与req文件");
            });
            return;
        }
        String var1="",var2 = "",var3="",var4="",var5="",var6="",var7="";
        if (!(target.contains("http")&&target.contains("://"))){
            var1 = "-r "+target;
        }else{
            var1 = "-u "+target;
        }
        if (!tamper.isEmpty()){
            var2 = "-tamper "+tamper;
        }
        if (!dbtype.isEmpty()){
            var3 = "--dbms=\""+dbtype+ "\" ";
        }
        if (!proxy.isEmpty()){
            var4 = "--proxy "+proxy;
        }
        if (!technique.isEmpty()){
            var5 = "--technique="+technique;
        }
        if (!post.isEmpty()){
            var6 = "--data="+post;
        }if(!define.isEmpty()){
            var7 = ""+define;
        }
        String[] cmd10 = {"cmd.exe","/c",python+" "+sqlmap+" "+var1+" "+var2+" "+var3+" "+var4+" "+var5+" "+var6+" --batch "+var7};
        SqlmapCmd(cmd10);
    }


}
