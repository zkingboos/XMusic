package xmusic.server.side.utils;

import javafx.scene.control.TextArea;

public class PrintSystem {

    private static  TextArea Console;

    public PrintSystem(){}
    public static void setConsole(TextArea Console){
        PrintSystem.Console = Console;
    }

    public void print(String message){
        if (Console.getText().length() < 1) {
            Console.appendText(message);
        }else{
            Console.appendText("\n"+message);
        }
    }


}
