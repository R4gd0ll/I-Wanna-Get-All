package core;

import javafx.scene.control.TextArea;

public interface CryptInterface {
    Boolean decrypt(String text,String type, TextArea textArea_decrypt,TextArea textArea_info);
    Boolean encrypt(String text,String type, TextArea textArea_encrypt,TextArea textArea_info);
}
