package Graphics;

import gameClient.Ex2_Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoginFrame implements Runnable{

    private long tz;
    private int scenario;
    private JComboBox liste1;


    @Override
    public synchronized void run() {
        //Crée une fenetre "Login" de taille 500x400

        JFrame fen=new JFrame("Login");
        fen.setSize(500, 400);
        fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Crée un Panel de couleur grey
        JPanel pan = new JPanel();
        pan.setBackground( new Color(102, 118, 134) );
        fen.setContentPane(pan);
        pan.setLayout(null); // desactive automatic layout


        //Crée un bouton "Start Game"
        JButton b=new JButton("Start Game");
        // fen.getContentPane().add(b);
        fen.add(b);
        b.setBounds(200,250, 100,50);


        //Crée une zone de text de taille 10 pour TZ
        JTextField tid = new JTextField(10);
        fen.getContentPane().add(tid);
        tid.setBounds(300, 100, 70, 20);


        JLabel id=new JLabel("Enter ID: ");
        fen.getContentPane().add(id);
        id.setBounds(100, 100, 70, 20);

        String[] scenarStrings = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};


        //Create the combo box, select item at index 23.
        JComboBox scenList = new JComboBox(scenarStrings);
        scenList.setSelectedIndex(0);
        scenList.addActionListener(this.liste1);
        fen.getContentPane().add(scenList);
        scenList.setBounds(300, 150, 100, 20);


        JLabel scenar=new JLabel("Enter Scenario number: ");
        fen.getContentPane().add(scenar);
        scenar.setBounds(100, 150, 150, 20);


        fen.getRootPane().setDefaultButton(b); //set button b as default button
        b.requestFocus(); // set focus toi b so we can press enter to push it
        b.addActionListener(this::actiongraph);
        fen.setVisible(true);
        b.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e){

                fen.setVisible(false);
                String idtext = tid.getText();
                id.setText("ID : "+idtext);
                setTz(Long.parseLong(idtext)); //update tz
                setScenario(Integer.parseInt(scenList.getSelectedItem().toString()));

            }
        });

    }

    public void actiongraph(ActionEvent e) {
        //DO SOMETHING
        Thread game = new Thread(new DijkstraAlgo(this.tz,this.scenario));
        game.start();
    }

    public long getTz() {
        return tz;
    }

    public void setTz(long tz) {
        this.tz = tz;
    }

    public int getScenario() {
        return scenario;
    }

    public void setScenario(int scenario) {
        this.scenario = scenario;
    }
}
