/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Prodavac;
import java.util.ArrayList;

/**
 *
 * @author Luka
 */
public class ServerController {
    
    private static ServerController instance;
    private ArrayList<Prodavac> ulogovaniProdavci = new ArrayList<>();

    public static ServerController getInstance() {
        if(instance == null){
            instance = new ServerController();
        }
        return instance;
    }

    public ArrayList<Prodavac> getUlogovaniProdavci() {
        return ulogovaniProdavci;
    }

    public void setUlogovaniProdavci(ArrayList<Prodavac> ulogovaniProdavci) {
        this.ulogovaniProdavci = ulogovaniProdavci;
    }
    
    
}
