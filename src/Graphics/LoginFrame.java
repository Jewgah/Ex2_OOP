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

public class LoginFrame implements Runnable {

    private long tz;
    private int scenario;

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


        //Crée une zone de text de taille  pour SCENARIO
        JTextField tscn = new JTextField(10);
        fen.getContentPane().add(tscn);
        tscn.setBounds(300, 150, 70, 20);


        JLabel id=new JLabel("Enter ID: ");
        fen.getContentPane().add(id);
        id.setBounds(100, 100, 70, 20);


        JLabel scenar=new JLabel("Enter Scenario number: ");
        fen.getContentPane().add(scenar);
        scenar.setBounds(100, 150, 150, 20);

        fen.getRootPane().setDefaultButton(b); //set button b as default button
        b.requestFocus(); // set focus to button b so we can press enter to push it

        b.addActionListener(this::actiongraph); // bind this button with action listener

        fen.setVisible(true);

        b.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){

                //TODO: recupere les valeurs de id et scenario, ferme la fenetre fen et load le graph au scenario designé

                fen.setVisible(false);

                String idtext = tid.getText();
                String scntext = tscn.getText();
                id.setText("ID : "+idtext);
                scenar.setText("Scenario numéro : "+scntext);

                setTz(Long.parseLong(idtext)); //update tz
                setScenario(Integer.parseInt(scntext)); // update scenario number
            }
        });
    }

    public void actiongraph(ActionEvent e) {

        Thread game = new Thread(new MyFrame(this.tz,this.scenario));
        game.start();

        // System.exit(0);
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
