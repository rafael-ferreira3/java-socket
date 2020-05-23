package client.socket;

import utils.Constants;

import javax.swing.*;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketClient extends Thread{

    private final int PORTA = Constants.PORTA;
    private final String URL = Constants.URL;

    private ObjectOutputStream msg_out;

    private final JLabel controlLael;

    public SocketClient(JLabel controlLael){
        this.controlLael = controlLael;
    }

    public void send(String msg){
        try {
            msg_out.writeObject(msg);
            msg_out.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void createConnection() throws Exception{
        Socket s =  new Socket(URL,PORTA);
        controlLael.setText("Conectado ao servidor "+URL+":" + PORTA);
        msg_out = new ObjectOutputStream(s.getOutputStream());
    }

    @Override
    public void run() {
        super.run();
        try {
            createConnection();
        } catch (Exception e) {
            System.out.println("Erro: "+e.getMessage());
        }
    }
}
