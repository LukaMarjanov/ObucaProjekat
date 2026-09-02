/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.Lokacija;
import domain.Obuca;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Luka
 */
public class TableModelLokacija extends AbstractTableModel implements Runnable {

    private ArrayList<Lokacija> lista;
    private String[] kolone = {"ID", "Grad", "Lokacija", "Broj"};
    private String parametarGrad = "";
    private String parametarUlica = "";
    private Lokacija lokacija = new Lokacija(null, "", "", 0);

    public TableModelLokacija() {
        try {
            lista = ClientController.getInstance()
                    .getAllLokacija(new Lokacija(null, "", "", 0));
        } catch (Exception ex) {
            Logger.getLogger(TableModelLokacija.class.getName()).log(Level.SEVERE, null, ex);
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
        Lokacija l = lista.get(row);

        switch (column) {
            case 0:
                return l.getLokacijaID();
            case 1:
                return l.getGrad();
            case 2:
                return l.getUlica();
            case 3:
                return l.getBroj();

            default:
                return null;
        }
    }

    public Lokacija getSelectedLokacija(int row) {
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
            Logger.getLogger(TableModelLokacija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametarUlica(String ulica) {
        this.parametarUlica = ulica;
        refreshTable();
    }

    public void parametarGrad(String grad) {
        this.parametarGrad = grad;
        refreshTable();
    }


    public void refreshTable() {
        try {

            lokacija.setGrad(parametarGrad.toLowerCase());
            lokacija.setUlica(parametarUlica.toLowerCase());

            lista = ClientController.getInstance().getAllLokacija(lokacija);
            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Lokacija> getLista() {
        return lista;
    }

}
