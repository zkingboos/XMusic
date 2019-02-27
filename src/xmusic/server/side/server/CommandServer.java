package xmusic.server.side.server;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class CommandServer {

    public static void onLoad(){

        CommandServer.registerCommand("teste", "teste", "eae");
    }

    private static Commands commands = new Commands();
    private static HashMap<String, Method> commandServer = new HashMap<>();

    public static int runCommand(String[] args){
        String command = args[0], loadArgs = "";
        if (!command.startsWith("/")) { return 1; }
        command = command.replace("/", "");
        if (commandServer.containsKey(command)) {
            int i = 0;
            while (i < args.length - 1) {
                i++;
                loadArgs += args[i] + " ";
            }
            Method method = commandServer.get(command);
            try {
                method.setAccessible(true);
                method.invoke(commands, new Object[]{loadArgs.split(" ")});
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }else{
            return 2;
        }
        return 0;
    }

    public static void registerCommand(String method, String... commands){
        Class c = Commands.class;
        try {
            Method m = c.getDeclaredMethod(method, String[].class);
            Iterator<String> i = Arrays.asList(commands).iterator();
            while (i.hasNext()){
                String cmd = i.next();
                if (!commandServer.containsKey(cmd)) {
                    commandServer.put(cmd , m);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void unregisterCommand(String... commands){
        Iterator<String> i = Arrays.asList(commands).iterator();
        while (i.hasNext()){
            String cmd = i.next();
            if (commandServer.containsKey(cmd)) {
                commandServer.remove(cmd);
            }
        }
    }


}
