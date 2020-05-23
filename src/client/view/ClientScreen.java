package client.view;

import client.socket.SocketClient;
import utils.criptografia.Criptografia;
import utils.view.CustomFrame;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ClientScreen {

    CustomFrame frame;
    JPanel panel;
    JLabel controlLabel;
    JTextArea msgAreaText;
    JTextArea consoleArea;
    SocketClient client;

    public ClientScreen(){
        frame = new CustomFrame("Tela do Client");
        frame.setCustomSize(600,360);
        createPanel();
        frame.setVisible(true);
        initClient();
    }

    void initClient(){
        client = new SocketClient(controlLabel);
        client.start();
    }

    void enviarMsg(){
        String msg = msgAreaText.getText();
        System.out.println(msg);
        if(client != null && !msg.isEmpty()){
            String encrypt = Criptografia.encrypt(msg);
            msgAreaText.setText("");
            consoleArea.append("Mensagem Original: "+msg+"\n");
            consoleArea.append("Mensagem Criptografada: "+ encrypt +"\n\n");
            client.send(encrypt);
            msgAreaText.requestFocus();
        }else{
            showErrorDialog();
        }
    }

    private void showErrorDialog(){
        JOptionPane.showMessageDialog(
                frame,
                "Escreva uma mensagem",
                "Erro ao tentar Enviar"
                ,JOptionPane.ERROR_MESSAGE
        );
    }

    private void createPanel(){
        panel = new JPanel();
        panel.setLayout(null);
        createControlLabel();
        createTextArea();
        createConsoleArea();
        createBtn();
        createCleanBtn();
        frame.add(panel);
    }

    private void createControlLabel(){
        controlLabel = new JLabel();
        controlLabel.setText("Client não conecntado a nenhum servidor");
        controlLabel.setBounds(30,20,500,25);
        panel.add(controlLabel);
    }

    private void createTextArea(){
        JLabel labelMsg = new JLabel("Mensagem:");
        labelMsg.setBounds(30, 50, 100, 25);
        msgAreaText = new JTextArea();
        createListener();
        JScrollPane scrollPane = new JScrollPane(
                msgAreaText,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );
        scrollPane.setBounds(30, 75, 260, 200);
        panel.add(labelMsg);
        panel.add(scrollPane);
    }

    private void createConsoleArea(){
        JLabel labelMsg = new JLabel("Console:");
        labelMsg.setBounds(310, 50, 100, 25);
        consoleArea = new JTextArea();
        consoleArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(
                consoleArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );
        scrollPane.setBounds(310, 75, 260, 200);
        panel.add(labelMsg);
        panel.add(scrollPane);
    }

    private void createListener(){
        msgAreaText.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    e.consume();
                    enviarMsg();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    private void createBtn(){
        JButton btn = new JButton("Enviar");
        btn.setBounds(330, 290, 100,25);
        btn.addActionListener(e -> enviarMsg());
        panel.add(btn);
    }

    private void createCleanBtn(){
        JButton btn = new JButton("Limpar Console");
        btn.setBounds(440, 290, 130,25);
        btn.addActionListener(e -> consoleArea.setText(""));
        panel.add(btn);
    }
}
