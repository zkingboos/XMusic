package xmusic.server.side.server;

import xmusic.server.side.utils.PrintSystem;

import javax.net.ServerSocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerLoader{

    private static int port = 55555, maxUsers = 100, users = 0;

    private PrintSystem ps = new PrintSystem();
    private ServerSocketFactory serverSocketFactory;
    private ServerSocket serverSocket;
    private PrintStream output;
    private BufferedReader input;

    private boolean running = false;
    public static boolean tryingConnection = false;

    public static void setPort(int port) {
        ServerLoader.port = port;
    }

    public static int getPort() {
        return port;
    }

    public boolean isRunning() { return running; }
    public boolean inConnection() { return tryingConnection; }

    public void run() {
        if (!running) {
            running = true;

            serverSocketFactory = ServerSocketFactory.getDefault();
            try {
                serverSocket = serverSocketFactory.createServerSocket(getPort());
            } catch (IOException e) {
                ps.print("Há algum programa/servidor utilizando esta porta.");
                tryingConnection = false;
                running = false;
            }
            if (running && serverSocket.isBound()) {
                ps.print("Servidor iniciado, utilizando a porta: " + getPort());
                tryingConnection = false;
                while (serverSocket.isBound() && running) {
                    Socket socket = null;
                    try {
                        socket = serverSocket.accept();
                        //new User(socket).start();
                        if(users >= maxUsers){

                        }
                        users++;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        tryingConnection = false;
    }
    public void stop() throws Exception {
        if (running) {
            running = false;
            serverSocket.close();
            ps.print("Servidor desligado.");
            tryingConnection = false;
        }
    }

}
