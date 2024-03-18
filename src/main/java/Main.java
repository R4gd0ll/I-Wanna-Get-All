import cn.hutool.core.io.resource.ResourceUtil;
import java.net.URL;
import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class Main extends Application {
    public Main() {
    }

    public void start(Stage primaryStage) throws Exception {
        Parent root = (Parent)FXMLLoader.load(ResourceUtil.getResource("fxml/Main.fxml"));

        primaryStage.setTitle("I Wanna Get All     --by R4gd0ll");
        try {
            // 设置窗口图标
            Image icon = new Image(getClass().getResourceAsStream("/icon.png"));
            primaryStage.getIcons().add(icon);
        } catch (NullPointerException e) {
            System.out.println("无法加载图标文件");
        }
        Scene scene = new Scene(root, 1280.0, 910.0);
        scene.getStylesheets().add(((URL)Objects.requireNonNull(Main.class.getResource("/css/main.css"))).toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        System.setProperty("file.encoding", "GBK");
        launch(args);
    }
}
