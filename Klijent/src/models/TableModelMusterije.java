/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.Musterija;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Luka
 */
public class TableModelMusterije extends AbstractTableModel implements Runnable {

    private ArrayList<Musterija> lista;
    private String[] kolone = {"ID", "Ime", "Prezime", "Email", "Telefon", "Lokacija"};
    private String parametarIme = "";
    private String parametarPrezime = "";
    private String parametarEmail = "";
    private Musterija musterija = new Musterija(null, "", "", "", "", null);

    public TableModelMusterije() {
        try {
            lista = ClientController.getInstance()
                    .getAllMusterija(new Musterija(null, "", "", "", "", null));
        } catch (Exception ex) {
            Logger.getLogger(TableModelMusterije.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int row, int column) {
        Musterija m = lista.get(row);

        switch (column) {
            case 0:
                return m.getMusterijaID();
            case 1:
                return m.getIme();
            case 2:
                return m.getPrezime();
            case 3:
                return m.getEmail();
            case 4:
                return m.getTelefon();
            case 5:
                return m.getLokacija();

            default:
                return null;
        }
    }

    public Musterija getSelectedMusterija(int row) {
        return lista.get(row);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(10000);
                refreshTable();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(TableModelMusterije.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametarIme(String ime) {
        this.parametarIme = ime;
        refreshTable();
    }

    public void setParametarPrezime(String prezime) {
        this.parametarPrezime = prezime;
        refreshTable();
    }

    public void setParametarEmail(String email) {
        this.parametarEmail = email;
        refreshTable();
    }

    public void refreshTable() {
        try {

            musterija.setIme(parametarIme.toLowerCase());
            musterija.setPrezime(parametarPrezime.toLowerCase());
            musterija.setEmail(parametarEmail.toLowerCase());

            lista = ClientController.getInstance().getAllMusterija(musterija);
            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Musterija> getLista() {
        return lista;
    }

}
