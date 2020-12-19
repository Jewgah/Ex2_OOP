package gameClient;
import Graphics.LoginFrame;
import Graphics.MyFrame;
import Informations.Pokemon;
import api.*;
import Server.Game_Server_Ex2;
import gameClient.util.Point3D;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;


public class Ex2 {

    private static int id;
    private static int scenario;


    public static void main(String[] args) {

        if(args.length != 0) { // if we give arguments skip login window

            try {
                id = Integer.parseInt(args[0]);
                scenario = Integer.parseInt(args[1]);
            } catch (Exception e) {
                id = -1;
                scenario = 0;
            }

            Thread client = new Thread(new MyFrame(id,scenario));
            client.start();
        }

        else { //else go to login frame
            Thread client = new Thread(new LoginFrame());
            client.start();
        }
    }

}


