/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package session;

import domain.Prodavac;
import forme.MainForma;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Luka
 */
public class Session {
    
    private static Session instance;
    private Socket socket;
    private Prodavac ulogovani;
    private MainForma mf;

    private Session() {
    
        try {
            socket = new Socket("localhost", 9000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Session getInstance() {
        if(instance == null){
            instance = new Session();
        }
        return instance;
    }

    public Socket getSocket() {
        return socket;
    }

    public Prodavac getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Prodavac ulogovani) {
        this.ulogovani = ulogovani;
    }

    public MainForma getMf() {
        return mf;
    }

    public void setMf(MainForma mf) {
        this.mf = mf;
    }
    
    
    
}
