package server.helper;

import utils.Constants;
import utils.criptografia.Criptografia;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ConnectionHandler implements Runnable {
    private final Socket clientSocket;
    private final JTextArea consoleTextArea;

    private ObjectInputStream msg_in;

    public ConnectionHandler(Socket socket, JTextArea tArea){
        this.clientSocket = socket;
        this.consoleTextArea = tArea;
    }

    private void show(String txt){
        consoleTextArea.append(txt+ "\n");
    }

    public void run() {
        try{
            msg_in = new ObjectInputStream(clientSocket.getInputStream());

            String msg;
            do{
                msg = (String)msg_in.readObject();
                show("Mensagem Criptografada: "+msg);
                show("Mensagem Descriptografada: "+ Criptografia.descrypt(msg));
                show("\n");
            }while (!msg.toUpperCase().equals(Constants.END_DELIMITER));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
