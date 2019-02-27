package xmusic.server.side;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import xmusic.server.side.server.CommandServer;
import xmusic.server.side.server.Database;
import xmusic.server.side.server.ServerLoader;
import xmusic.server.side.utils.PrintSystem;

import java.util.concurrent.Executors;

public class Controller {

    PrintSystem ps = new PrintSystem();

    @FXML
    public TextArea Console;
    @FXML
    public TextField CommandBox;
    @FXML
    public Button StartServer_button;
    @FXML
    public Button sendCommand_button;
    @FXML
    public Label LoadServer;
    public Spinner<Integer> spinner;

    public void initialize(){
        Database.server = new ServerLoader();
        PrintSystem.setConsole(Console);
        CommandServer.onLoad();
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 65535, 55555);
        spinner.setValueFactory(valueFactory);
    }
    public void quit(){}

    public void StartAndStop(ActionEvent e){
        ServerLoader sl = Database.server;
        try {
            if (!sl.isRunning()) {
                ServerLoader.tryingConnection = true;
                ServerLoader.setPort(spinner.getValue());
                Executors.newSingleThreadExecutor().submit(() -> {
                    sl.run();
                });

            }else {
                sl.stop();

            }
            while(ServerLoader.tryingConnection){ LoadServer.setVisible(true);  }
            if (sl.isRunning()){
                StartServer_button.setText("Desligar Servidor");
                Main.stage.setTitle(Main.stage.getTitle().replace("Offline", "Online"));
                CommandBox.setDisable(false);
                sendCommand_button.setDisable(false);
                spinner.setDisable(true);
            }else{
                StartServer_button.setText("Iniciar Servidor");
                Main.stage.setTitle(Main.stage.getTitle().replace("Online", "Offline"));
                CommandBox.setDisable(true);
                sendCommand_button.setDisable(true);
                spinner.setDisable(false);
            }
            LoadServer.setVisible(false);

        } catch ( Exception e1 ){}

    }

    public void callCommand(){
        if (CommandBox.getLength() > 0) {
            int status = CommandServer.runCommand(CommandBox.getText().split(" "));
            if (status == 1) {
                ps.print("Somente comandos podem ser enviados.");
            }else if (status == 2) {
                ps.print("Comando não encontrado.");
            }
            CommandBox.clear();
        }
    }

}
