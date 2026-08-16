/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Musterija;
import domain.Obuca;
import domain.Prodavac;
import domain.Racun;
import java.util.ArrayList;
import so.login.SOLogin;
import so.musterija.SOGetAllMusterija;
import so.obuca.SOGetAllObuca;
import so.racun.SOAddRacun;
import so.racun.SOGetAllRacun;

/**
 *
 * @author Luka
 */
public class ServerController {

    private static ServerController instance;
    private ArrayList<Prodavac> ulogovaniProdavci = new ArrayList<>();

    public static ServerController getInstance() {
        if (instance == null) {
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

    public Prodavac login(Prodavac prodavac) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(prodavac);
        return so.getUlogovani();
    }

    public ArrayList<Obuca> getAllObuca(Obuca obuca) throws Exception {
        SOGetAllObuca so = new SOGetAllObuca();
        so.templateExecute(obuca);
        return so.getLista();
    }

    public ArrayList<Musterija> getAllMusterija(Musterija musterija) throws Exception {
        SOGetAllMusterija so = new SOGetAllMusterija();
        so.templateExecute(musterija);
        return so.getLista();
    }

    public void addRacun(Racun racun) throws Exception {
        SOAddRacun so = new SOAddRacun();
        so.templateExecute(racun);
    }

    public ArrayList<Racun> getAllRacun(Racun racun) throws Exception {
        SOGetAllRacun so = new SOGetAllRacun();
        so.templateExecute(racun);
        return so.getLista();
    }

    public void logout(Prodavac ulogovani) {
        ulogovaniProdavci.remove(ulogovani);
    }

}
