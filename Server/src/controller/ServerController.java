/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Lokacija;
import domain.Musterija;
import domain.Obuca;
import domain.Prodavac;
import domain.Racun;
import java.util.ArrayList;
import so.login.SOLogin;
import so.lokacija.SOAddLokacija;
import so.lokacija.SODeleteLokacija;
import so.lokacija.SOGetAllLokacija;
import so.lokacija.SOUpdateLokacija;
import so.musterija.SOAddMusterija;
import so.musterija.SODeleteMusterija;
import so.musterija.SOGetAllMusterija;
import so.musterija.SOUpdateMusterija;
import so.obuca.SOAddObuca;
import so.obuca.SODeleteObuca;
import so.obuca.SOGetAllObuca;
import so.obuca.SOUpdateObuca;
import so.racun.SOAddRacun;
import so.racun.SOCancelRacun;
import so.racun.SOGetAllRacun;
import so.racun.SOUpdateRacun;

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

    public void updateRacun(Racun racun) throws Exception {
        (new SOUpdateRacun()).templateExecute(racun);
    }

    public void cancelRacun(Racun racun) throws Exception {
        (new SOCancelRacun()).templateExecute(racun);
    }

    public void addMusterija(Musterija musterija) throws Exception {
        (new SOAddMusterija()).templateExecute(musterija);
    }

    public void deleteMusterija(Musterija musterija) throws Exception {
        (new SODeleteMusterija()).templateExecute(musterija);
    }

    public void updateMusterija(Musterija musterija) throws Exception {
        (new SOUpdateMusterija()).templateExecute(musterija);
    }

    public ArrayList<Lokacija> getAllLokacija(Lokacija lokacija) throws Exception {
        SOGetAllLokacija so = new SOGetAllLokacija();
        so.templateExecute(lokacija);
        return so.getLista();
    }

    public void addObuca(Obuca obuca) throws Exception {
        (new SOAddObuca()).templateExecute(obuca);
    }

    public void addLokacija(Lokacija lokacija) throws Exception {
        (new SOAddLokacija()).templateExecute(lokacija);
    }

    public void updateObuca(Obuca obuca) throws Exception {
        (new SOUpdateObuca()).templateExecute(obuca);
    }

    public void updateLokacija(Lokacija lokacija) throws Exception {
        (new SOUpdateLokacija()).templateExecute(lokacija);
    }

    public void deleteObuca(Obuca obuca) throws Exception {
        (new SODeleteObuca()).templateExecute(obuca);
    }

    public void deleteLokacija(Lokacija lokacija) throws Exception {
        (new SODeleteLokacija()).templateExecute(lokacija);
    }
}
