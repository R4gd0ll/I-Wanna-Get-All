package controller;

import cn.hutool.core.util.StrUtil;
import core.Exploitlnterface;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.CompletionService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import utils.Kinds_Exp;
import utils.Methods;

import javax.swing.*;


public class AttController {

  private final Kinds_Exp exp = new Kinds_Exp();//初始化EXP相关数据

  private final ExecutorService service = Executors.newCachedThreadPool();
  private final CompletionService<HashMap<String, Object>> completionService = new ExecutorCompletionService<>(
      service);
  public Button Button_Upload;
  public TextField textField_file;
  public Button Button_CLEAR;
  private String filename = "";
  private String content = "";
  private boolean initialized = false;//是否初始化

  @FXML
  private Button Button_ATT;

  @FXML
  private Button Button_CHK;



  @FXML
  private TextArea textArea_attInfo; //ATT 结果文本域

  @FXML
  private TextArea textArea_cmdInfo; //CMD 结果文本域

  @FXML
  private TextField textField_url;

  @FXML
  private TextField textField_cmd;

  @FXML
  private TextArea textArea_info; //中间说明文本域

  @FXML
  private ChoiceBox<String> choiceBox_exp;

  @FXML
  private ListView<String> listview_kinds;

  @FXML
  private ChoiceBox<String> choiceBox_kinds;


  @FXML
  public void choiceExp(ActionEvent actionEvent) {
    String Exp = choiceBox_exp.getValue();
    if(Exp!=null){
      Platform.runLater(() -> {
        textArea_info.appendText(Methods.getTime());
        textArea_info.appendText("\n已选择漏洞: "+Exp+"\n");
        textArea_info.appendText("\n");
      });
    }

  }

  @FXML
  void CHK_clicked(MouseEvent event) {         //CHK按钮
    //初始清空

    //获取url地址
    String url;
    if (StrUtil.isBlank(textField_url.getText())) {
      Platform.runLater(() -> {
        textArea_info.appendText(Methods.getTime());
        textArea_info.appendText("\n请填写URL\n");
        textArea_info.appendText("\n");
      });
      return;
    }
    if (textField_url.getText().trim().endsWith("/")) {
      url = textField_url.getText().trim()
          .substring(0, textField_url.getText().trim().length() - 1);
    } else {
      url = textField_url.getText().trim();
    }

    //获取需要利用的exp
    String vulname = choiceBox_exp.getValue();
    //获取get shell按钮是否被选中
//    Boolean getshell = radioButton_getshell.selectedProperty().get();

    //如果是all
    if (vulname != null && vulname.equals("All")) {
      //清空
      Platform.runLater(() -> {
        textArea_info.appendText(Methods.getTime());
        textArea_info.appendText("\n开始检测......\n");
//        textArea_info.appendText("\n");
        textArea_attInfo.clear();
        textArea_cmdInfo.clear();
      });
      ObservableList<String> items = choiceBox_exp.getItems();
      CountDownLatch countDownLatch = new CountDownLatch(items.size() - 1);
      long start = System.currentTimeMillis();
      for (int i = 1; i < items.size(); i++) {
        String val = items.get(i);
        service.submit(() -> {
          try {
            String x = val + "：" + Thread.currentThread().getName() + "开始检测";
            System.out.println(x);
//            Platform.runLater(() -> {
//              textArea_info.appendText(x);
//              textArea_info.appendText("\n");
//            });
            Exploitlnterface exploit = Kinds_Exp.getExploit(val);
            if (exploit == null) {
              Platform.runLater(() -> {
                textArea_info.appendText(Methods.getTime());
                textArea_info.appendText("\n未找到EXP：" + val+"\n");
                textArea_info.appendText("\n");
              });
              throw new RuntimeException("未找到EXP");
            }
            if (Objects.isNull(textArea_attInfo)) {
              System.out.println("NPE debugger");
            }

            Boolean aBoolean = exploit.checkVul(url, textArea_attInfo,textArea_cmdInfo);

            if(aBoolean){
              Platform.runLater(() -> {

                textArea_info.appendText("[ + ] " + val + "漏洞存在");
                textArea_info.appendText("\n");
//                textArea_info.appendText("\n");
              });
            }else{
              Platform.runLater(() -> {
                textArea_info.appendText("[  - ] " + val + "漏洞不存在");
                textArea_info.appendText("\n");
//                textArea_info.appendText("\n");
              });
            }

          } catch (Exception e) {
            if (e instanceof IndexOutOfBoundsException) {
              System.out.println("数组下标越界异常");
            }
            if (e instanceof NullPointerException) {
              System.out.println("NPE异常");
            }
            e.printStackTrace();
          } finally {

            String threadName = "线程：" + Thread.currentThread().getName();
            // String x = threadName + "结束";
            System.out.println(threadName+" spend:" + (System.currentTimeMillis() - start) + "ms");

            countDownLatch.countDown();
          }
        });

      }

      try {
        countDownLatch.await();
        System.out.println("total spend:" + (System.currentTimeMillis() - start) + "ms");
      } catch (Exception e) {
        e.printStackTrace();
      }
      Platform.runLater(() -> {
        textArea_info.appendText("\n");
      });

    } else if (vulname != null) {
      Platform.runLater(() -> {
        textArea_attInfo.clear();
        textArea_cmdInfo.clear();
      });
      //生成exp对应类对象
      Exploitlnterface exploit = Kinds_Exp.getExploit(vulname);
      //检查是否存在漏洞
      Boolean checkVul = exploit.checkVul(url, textArea_attInfo,textArea_cmdInfo);



      if (checkVul) {
        if (StrUtil.isBlank(textField_cmd.getText())) {
          Platform.runLater(() -> {
            textArea_info.appendText(Methods.getTime());
            textArea_info.appendText("\n"+vulname+" 漏洞存在，请进行测试"+"\n ");
            textArea_info.appendText("\n");
//            textArea_attInfo.appendText("请输入执行命令");
          });
        }
        return;
      }else{
        Platform.runLater(() -> {
          textArea_info.appendText(Methods.getTime());
          textArea_info.appendText("\n"+vulname+" 漏洞不存在"+"\n ");
          textArea_info.appendText("\n");
//            textArea_attInfo.appendText("请输入执行命令");
        });
      }
    }
    return;
  }
  public static void configureFileChooser(
          final FileChooser fileChooser) {
    fileChooser.setTitle("选择文件");
    fileChooser.setInitialDirectory(
            new File(System.getProperty("user.home"))
    );
    fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("所有文件", "*.*")
    );
  }


  @FXML
  void chooseFile() {

    FileChooser fileChooser = new FileChooser();
    configureFileChooser(fileChooser);


    // 获取主舞台
    Stage stage = (Stage) Button_Upload.getScene().getWindow();

    // 显示文件选择对话框
    File selectedFile = fileChooser.showOpenDialog(stage);
    filename = selectedFile.getAbsolutePath();
    if (!filename.equals("")) {
      Platform.runLater(() -> {
        textField_file.clear();
        textField_file.setText(filename);
      });

      Platform.runLater(() -> {
        textArea_info.appendText(Methods.getTime());
        textArea_info.appendText("\n已读取"+selectedFile.getAbsolutePath()+"文件内容\n");
        textArea_info.appendText("\n");
      });
      try {
        // 读取文件内容
        content = Methods.readFile(selectedFile.getAbsolutePath());

        // 将文件内容设置到TextArea中
//        textArea_source.setText(content);
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else {
      Platform.runLater(() -> {
        textArea_info.appendText(Methods.getTime());
        textArea_info.appendText("\n请选择文件\n");
        textArea_info.appendText("\n");
      });
    }
  }

  @FXML
  void ATT_clicked(MouseEvent event) {         //ATT按钮
    //初始清空
    Platform.runLater(() -> {
      textArea_attInfo.clear();
      textArea_cmdInfo.clear();

    });
    //获取url地址
    String url;
    if (StrUtil.isBlank(textField_url.getText())) {
      Platform.runLater(() -> {
        textArea_info.appendText(Methods.getTime());
        textArea_info.appendText("\n请填写URL\n");
        textArea_info.appendText("\n");
      });
      return;
    }
    if (textField_url.getText().trim().endsWith("/")) {
      url = textField_url.getText().trim()
              .substring(0, textField_url.getText().trim().length() - 1);
    } else {
      url = textField_url.getText().trim();
    }


    if (StrUtil.isBlank(textField_cmd.getText())&&StrUtil.isBlank(filename)) {
      Platform.runLater(() -> {
        textArea_info.appendText(Methods.getTime());
        textArea_info.appendText("\n请填写CMD或上传文件\n");
        textArea_info.appendText("\n");
      });
      return;
    }

    //获取需要利用的exp
    String vulname = choiceBox_exp.getValue();
    //获取get shell按钮是否被选中
//    Boolean getshell = radioButton_getshell.selectedProperty().get();

    //如果是all
    if (vulname != null && vulname.equals("All")) {

      Platform.runLater(() -> {
        textArea_info.appendText(Methods.getTime());
        textArea_info.appendText("\n请选择单个漏洞ATT测试\n");
        textArea_info.appendText("\n");
      });
      return;

    } else if (vulname != null) {

      //生成exp对应类对象
      Exploitlnterface exploit = Kinds_Exp.getExploit(vulname);
      //检查是否存在漏洞
//      Boolean checkVul = exploit.checkVul(url, textArea_attInfo);
      //cmd利用


      String cmd;

      if (StrUtil.isBlank(textField_cmd.getText()) && filename.isEmpty()) {
        Platform.runLater(() -> {
          textArea_info.appendText(Methods.getTime());
          textArea_info.appendText("\n命令执行请输入命令，文件上传请上传文件\n");
          textArea_info.appendText("CMD栏为空则上传文件\n");
          textArea_info.appendText("\n");
        });
      }
      cmd = !textField_cmd.getText().trim().isEmpty()?textField_cmd.getText().trim():filename;
      Boolean checkVulCmd= exploit.checkVulCmd(url,cmd,textArea_attInfo, textArea_cmdInfo);

      if (checkVulCmd) {
        Platform.runLater(() -> {
          textArea_info.appendText(Methods.getTime());
          textArea_info.appendText("\n漏洞名称: "+vulname+"执行成功\n");
          textArea_info.appendText("\n");
        });
        return;
      }else{
        Platform.runLater(() -> {
          textArea_info.appendText(Methods.getTime());
          textArea_info.appendText("\n漏洞名称: "+vulname+"执行失败\n");
          textArea_info.appendText("\n");
        });
      }
    }
    return;
  }

  @FXML
  public void clear(){
    filename = "";
    content = "";
    textField_file.setText(filename);
    Platform.runLater(() -> {
      textArea_info.clear();
      textArea_attInfo.clear();
      textField_cmd.clear();
      textArea_cmdInfo.clear();
    });
    return;
  }


  @FXML
  public void initialize() {
    Platform.runLater(() -> {
      textArea_info.appendText(Methods.getTime());
      textArea_info.appendText("\n初始化成功，欢迎使用IWannaGetAll平台!\n");
      textArea_info.appendText("\n");
    });

    //设置自动换行
    textArea_info.setWrapText(true);
    textArea_attInfo.setWrapText(true);

    //适配屏幕
    System.setProperty("prism.allowhidpi", "true");

    //渲染Tab1 左上 下拉选
    //设置初始化数据
    choiceBox_kinds.setItems(exp.getFXKindList());
    //设置默认选项
    choiceBox_kinds.setValue(exp.getKindList().get(0));
    //选项绑定监听器，当左上 下拉选 数据发生改变，更新列表数据，同时更新exp下拉选数据
    choiceBox_kinds.getSelectionModel().selectedItemProperty()
        .addListener((observable, oldValue, newValue) -> buildChoiceBoxListener(newValue));

    // 第一次渲染该页面时渲染数据
    if (!initialized) {
      //更新列表数据
      buildChoiceBoxListener(exp.getKindList().get(0));
      initialized = true;

    }

  }

  private void buildChoiceBoxListener(String newValue) {
    switch (newValue) {
      case "OA":
        listview_kinds.setItems(Kinds_Exp.oa());
        break;
    }
    updateListView(listview_kinds.getItems().get(0));
  }

  /**
   * 列表监听事件，监听所选的类型更新exp下拉选数据.
   */
  @FXML
  void listview_clicked() {
    String selectedItem = listview_kinds.getSelectionModel().getSelectedItem();
    updateListView(selectedItem);
    Platform.runLater(() -> {
      textArea_info.appendText(Methods.getTime());
      textArea_info.appendText("\n已选择OA: "+selectedItem+"\n");
      textArea_info.appendText("\n");
    });

  }

  /**
   * 根据列表所选类型给中间EXP下拉选赋值
   *
   * @param selectedItem 左边列表选的类型
   */
  private void updateListView(String selectedItem) {
    switch (selectedItem) {
      case "泛微-ECology":
        choiceBox_exp.setItems(exp.weaverec());
        break;
      case "泛微-EMobile":
        choiceBox_exp.setItems(exp.weaverem());
        break;
      case "泛微-EOffice":
        choiceBox_exp.setItems(exp.weavereo());
        break;
      case "蓝凌-OA":
        choiceBox_exp.setItems(exp.landrayoa());
        break;
      case "用友-NC":
        choiceBox_exp.setItems(exp.yongyounc());
        break;
      case "用友-GRP":
        choiceBox_exp.setItems(exp.yongyougrp());
        break;
      case "用友-U8C":
        choiceBox_exp.setItems(exp.yongyouU8C());
        break;
      case "用友-Tplus":
        choiceBox_exp.setItems(exp.yongyouTplus());
        break;
      case "用友-serial接口":
        choiceBox_exp.setItems(exp.yongyouserial());
        break;
      case "万户-OA":
        choiceBox_exp.setItems(exp.wanhuoa());
        break;
      case "致远-OA":
        choiceBox_exp.setItems(exp.zhiyuanoa());
        break;
      case "通达-OA":
        choiceBox_exp.setItems(exp.tongdaoa());
        break;
      case "帆软-OA":
        choiceBox_exp.setItems(exp.fanruanoa());
        break;
      case "金蝶-OA":
        choiceBox_exp.setItems(exp.kingdeeoa());
        break;
      case "金和-OA":
        choiceBox_exp.setItems(exp.jinheoa());
        break;
      case "红帆-OA":
        choiceBox_exp.setItems(exp.hongfanoa());
        break;
      case "宏景-OA":
        choiceBox_exp.setItems(exp.hongjingoa());
        break;
      case "亿赛通-OA":
        choiceBox_exp.setItems(exp.esafenet());
        break;
      case "飞企互联-FE":
        choiceBox_exp.setItems(exp.FE());
        break;


      case "Apache":
        choiceBox_exp.setItems(exp.apache());
        break;



      default:
        System.out.println(selectedItem);
        // 当所选项还没有exp给默认选项
        choiceBox_exp.setItems(exp.defaultList());
    }
    // 设置下拉选默认exp
    choiceBox_exp.setValue(exp.getExpList().get(0));
  }


}
