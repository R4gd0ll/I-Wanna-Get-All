package controller;

import cn.hutool.core.util.StrUtil;
import core.CryptInterface;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import utils.Kinds_Crypt;
import utils.Methods;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static controller.AttController.configureFileChooser;

public class CryptController {
    private final Kinds_Crypt typeDecrypt = new Kinds_Crypt();//初始化type相关数据

    private final ExecutorService service = Executors.newCachedThreadPool();
    private final CompletionService<HashMap<String, Object>> completionService = new ExecutorCompletionService<>(
            service);
    public Button undo;
    private String filename = "";
    public Button button_Dec;
    public Button button_Enc;
    public TextArea textArea_source;
    public TextArea text_file;
    public TextArea textArea_info;
    public Button file;
    private boolean initialized = false;//是否初始化
    @FXML
    private TextArea textArea_encrypt; //密文文本域

    @FXML
    private ChoiceBox<String> choiceBox_type; //类型

    @FXML
    private ListView<String> listview_type;
    @FXML
    private TextArea textArea_decrypt; //明文文本域

    @FXML
    void listview_clicked() {
        listview_type.setItems(typeDecrypt.OAType());
    }

    @FXML
    void clicked_decrypt(MouseEvent event) {//decrypt按钮
        textArea_decrypt.setEditable(true);
        //初始清空
        Platform.runLater(() -> {

            textArea_decrypt.clear();
        });

        //获取oa类型
        String target_type = "已选择"+listview_type.getSelectionModel().getSelectedItem()+"类型";
        String text = textArea_source.getText().trim();

        if (StrUtil.isBlank(text)) {
            Platform.runLater(() -> {
                textArea_info.appendText(getTime());
                textArea_info.appendText("\n请在源码栏输入目标字符串\n");
            });
            return;
        }
        if (StrUtil.isBlank(listview_type.getSelectionModel().getSelectedItem())) {
            Platform.runLater(() -> {
                textArea_info.appendText(getTime());
                textArea_info.appendText("\n请选择编解码类型\n");
            });
            return;
        }

        //生成decrypt对应类对象
        CryptInterface dec = Kinds_Crypt.getCrypt(target_type);
        Boolean decryptResult = false;
        if(target_type.contains("Classbyte")){
            if(filename.contains(".class")){
                if(text.startsWith("yv66") || text.startsWith("H4s")){

                    decryptResult = dec.decrypt(text, target_type, textArea_decrypt, textArea_info);
                }else{
                    decryptResult = dec.decrypt(filename, target_type, textArea_decrypt, textArea_info);
                    Platform.runLater(() -> {
                        textArea_info.appendText(getTime());
                        textArea_info.appendText("\n非base64编码字节码默认以class文件内容进行操作\n");
                    });
                }
            }else if(text.startsWith("yv66") || text.startsWith("H4s")) {
                decryptResult = dec.decrypt(text, target_type, textArea_decrypt, textArea_info);
            }else{
                Platform.runLater(() -> {
                    textArea_info.appendText(getTime());
                    textArea_info.appendText("\n请选择class文件或输入classbyte的base64编码(yv66..或H4sI..)\n");
                });
                return;
            }
        }else if(target_type.contains("BCEL")) {
            if (filename.contains(".class")) {
                if (text.startsWith("$$BCEL$$")) {
                    decryptResult = dec.decrypt(text, target_type, textArea_decrypt, textArea_info);
                } else {
                    decryptResult = dec.decrypt(filename, target_type, textArea_decrypt, textArea_info);
                    Platform.runLater(() -> {
                        textArea_info.appendText(getTime());
                        textArea_info.appendText("\n非BCEL字节码默认以class文件内容进行操作\n");
                    });
                }
            } else if (text.startsWith("$$BCEL$$")) {
                decryptResult = dec.decrypt(text, target_type, textArea_decrypt, textArea_info);
            } else {
                Platform.runLater(() -> {
                    textArea_info.appendText(getTime());
                    textArea_info.appendText("\n请选择class文件或输入classbyte的BCEL编码($$BCEL$$..)\n");
                });
                return;
            }
        }else{
            decryptResult = dec.decrypt(text, target_type, textArea_decrypt, textArea_info);
        }


//        Boolean decryptResult = dec.decrypt(text,target_type, textArea_decrypt,textArea_info);

        if (decryptResult) {
            if (StrUtil.isBlank(textArea_source.getText())) {
                Platform.runLater(() -> {
                    textArea_info.appendText(getTime());
                    textArea_info.appendText("\n请输入密文\n");
                });
            }
            return;
        }
    }
    @FXML
    void undo(){
        filename = "";
        Platform.runLater(() -> {
            text_file.clear();
            textArea_source.clear();
            textArea_info.appendText(getTime());
            textArea_info.appendText("\n已取消所选文件"+"及文本内容\n");
        });
    }

    @FXML
    void chooseFile() {
        FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);

        // 获取主舞台
        Stage stage = (Stage) file.getScene().getWindow();

        // 显示文件选择对话框
        File selectedFile = fileChooser.showOpenDialog(stage);
        filename = selectedFile.getAbsolutePath();
        if (!filename.equals("")) {
            Platform.runLater(() -> {
                text_file.clear();
                text_file.appendText(filename);
            });

            Platform.runLater(() -> {
                textArea_info.appendText(getTime());
                textArea_info.appendText("\n已读取"+selectedFile.getAbsolutePath()+"文件内容\n");
            });
            try {
                // 读取文件内容
                String content = Methods.readFile(selectedFile.getAbsolutePath());

                // 将文件内容设置到TextArea中
                textArea_source.setText(content);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Platform.runLater(() -> {
                textArea_info.appendText(getTime());
                textArea_info.appendText("\n请选择文件");
            });
        }
    }

    @FXML
    void clicked_encrypt(MouseEvent event) {         //encrypt按钮
        //初始清空
        textArea_encrypt.setEditable(true);
        Platform.runLater(() -> {
//            textArea_encrypt.clear();

            textArea_encrypt.clear();
        });

        //获取oa类型
        String target_type = "已选择"+listview_type.getSelectionModel().getSelectedItem()+"类型";
        String text = textArea_source.getText().trim();

        if (StrUtil.isBlank(text)) {
            Platform.runLater(() -> {
                textArea_info.appendText(getTime());
                textArea_info.appendText("\n请在源码栏输入目标字符串\n");
            });
            return;
        }
        if (StrUtil.isBlank(listview_type.getSelectionModel().getSelectedItem())) {
            Platform.runLater(() -> {
                textArea_info.appendText(getTime());
                textArea_info.appendText("\n请选择编解码类型\n");
            });
            return;
        }

        //生成encrypt对应类对象
        CryptInterface enc = Kinds_Crypt.getCrypt(target_type);
        Boolean encryptResult ;

        if(target_type.contains("Classbyte")|| target_type.contains("BCEL")){
            if(filename.contains(".class")){
                encryptResult = enc.encrypt(filename,target_type,textArea_encrypt,textArea_info);
            }else{
                Platform.runLater(() -> {
                    textArea_info.appendText(getTime());
                    textArea_info.appendText("\n请选择class文件\n");
                });
                return;
            }
        }else {
            encryptResult = enc.encrypt(text, target_type, textArea_encrypt, textArea_info);
        }

        if (encryptResult) {
            return;
        }
    }
    private static String getTime(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        return formattedDateTime+" ";
    }

    @FXML
    public void initialize() {

        //设置自动换行
        textArea_encrypt.setEditable(false);
        textArea_decrypt.setEditable(false);
        textArea_info.setWrapText(true);
        textArea_source.setWrapText(true);
        textArea_encrypt.setWrapText(true);
        textArea_decrypt.setWrapText(true);

        //适配屏幕
        System.setProperty("prism.allowhidpi", "true");
        listview_clicked();
        Platform.runLater(() -> {
            textArea_info.appendText(getTime());
            textArea_info.appendText("\n-----------------------------------------\nClassbyte采用base64编码展示\n加密采用读取class文件\n解密采用base64编码字符串\nASCII解码字符以空格隔开\n-----------------------------------------\n");
        });
        // 第一次渲染该页面时渲染数据
        if (!initialized) {
            //更新列表数据
            initialized = true;
        }

    }

}
