/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Musterija;
import domain.Obuca;
import domain.Prodavac;
import domain.Racun;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import session.Session;
import transfer.Request;
import transfer.Response;
import transfer.util.Operation;
import transfer.util.ResponseStatus;

/**
 *
 * @author Luka
 */
public class ClientController {

    private static ClientController instance;

    public ClientController() {
    }

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    private synchronized Object sendRequest(int operation, Object data) throws Exception {
        //kreiramo zahtev
        Request request = new Request(operation, data);
        //Saljemo zahtev
        ObjectOutputStream oos = new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        oos.writeObject(request);

        //Primamo odgovor
        ObjectInputStream ois = new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response response = (Response) ois.readObject();

        if (response.getResponseStatus().equals(ResponseStatus.Error)) {
            throw response.getExc();
        } else {
            return response.getData();
        }

    }

    public Prodavac login(Prodavac p) throws Exception {
        return (Prodavac) sendRequest(Operation.LOGIN, p);
    }

    public ArrayList<Obuca> getAllObuca(Obuca obuca) throws Exception {
        return (ArrayList<Obuca>) sendRequest(Operation.GET_ALL_OBUCA, obuca);
    }

    public ArrayList<Musterija> getAllMusterija(Musterija musterija) throws Exception {
        return (ArrayList<Musterija>) sendRequest(Operation.GET_ALL_MUSTERIJA, musterija);
    }

    public void addRacun(Racun r) throws Exception {
        sendRequest(Operation.ADD_RACUN, r);
    }

    public ArrayList<Racun> getAllRacun(Racun racun) throws Exception {
        return (ArrayList<Racun>) sendRequest(Operation.GET_ALL_RACUN, racun);
    }

    public void logout(Prodavac ulogovani) throws Exception {
        sendRequest(Operation.LOGOUT, ulogovani);
    }

}
