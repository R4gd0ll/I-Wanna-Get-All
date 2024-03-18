package core;

import javafx.scene.control.TextArea;

public interface MemShellInterface {
    Boolean inject(String url,String interF,String type,int dirtyN,String[] array, TextArea textArea_memshell);
    Boolean injectPrint(String type,String interFace,String PrintType, int dirtyN, String[] array, TextArea textArea_memshell);
}
