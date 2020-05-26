package server.socket;

import server.helper.ConnectionHandler;
import utils.Constants;
import utils.criptografia.Criptografia;

import javax.swing.*;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer extends Thread{

    private final int PORTA = Constants.PORTA;
    private ServerSocket server;

    private final JTextArea consoleTextArea;

    public SocketServer(JTextArea textArea) {
        consoleTextArea = textArea;
    }

    private void show(String txt){
        consoleTextArea.append(txt+ "\n");
    }

    private void createConnection() throws Exception{
        server = new ServerSocket(PORTA);
        show("Servidor iniciado na porta "+PORTA);
    }

    private void waitConnection() throws Exception{
        while(true){
            show("Aguardando conexões... ");
            Socket soc = server.accept();
            show("Conexao de: " +
                    soc.getInetAddress().getHostAddress());

            new Thread(new ConnectionHandler(soc,consoleTextArea)).start();
        }
    }

    @Override
    public void run() {
        super.run();
        try{
            createConnection();
            waitConnection();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
