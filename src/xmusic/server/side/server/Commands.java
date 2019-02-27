package xmusic.server.side.server;

import xmusic.server.side.utils.PrintSystem;

import java.util.Arrays;

public class Commands {

    PrintSystem ps = new PrintSystem();

    public void teste(String[] args){
        String a = "";
        for (String arg : args){
            a += (arg + " ");
        }
        ps.print(Arrays.asList(args).toString() + " < comando teste > "+a);
    }

}
