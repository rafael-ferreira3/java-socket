package server.view;

import server.socket.SocketServer;
import utils.view.CustomFrame;

import javax.swing.*;

public class ServerScreen {

    private CustomFrame frame;
    private JPanel panel;
    private JTextArea consoleTextArea;

    public ServerScreen(){
        frame = new CustomFrame("Tela do Servidor");
        frame.setCustomSize(600,360);
        createPanel();
        frame.setVisible(true);
        startServer();
    }

    private void startServer(){
        final SocketServer server = new SocketServer(consoleTextArea);
        server.start();
    }

    private void createPanel(){
        panel = new JPanel();
        panel.setLayout(null);
        createTextArea();
        createCleanBtn();
        frame.add(panel);
    }

    private void createCleanBtn(){
        JButton cleanBtn = new JButton("Limpar Console");
        cleanBtn.setBounds(435, 20, 130,25);
        cleanBtn.addActionListener(e-> consoleTextArea.setText(""));
        panel.add(cleanBtn);
    }

    private void createTextArea(){
        JLabel consoleLabel = new JLabel("Console:");
        consoleLabel.setBounds(30, 20, 100, 25);
        consoleTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(
                consoleTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );
        scrollPane.setBounds(30, 50, 535, 200);
        panel.add(consoleLabel);
        panel.add(scrollPane);
    }

}
