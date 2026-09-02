/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.Obuca;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Luka
 */
public class TableModelObuca extends AbstractTableModel implements Runnable {

    private ArrayList<Obuca> lista;
    private String[] kolone = {"ID", "Tip obuce", "Boja", "Velicina", "Cena"};
    private String parametarTipObuce = "";
    private String parametarBoja = "";
    private Obuca obuca = new Obuca(null, "", "", 0, 0);

    public TableModelObuca() {
        try {
            lista = ClientController.getInstance()
                    .getAllObuca(new Obuca(null, "", "", 0, 0));
        } catch (Exception ex) {
            Logger.getLogger(TableModelObuca.class.getName()).log(Level.SEVERE, null, ex);
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
        Obuca o = lista.get(row);

        switch (column) {
            case 0:
                return o.getObucaID();
            case 1:
                return o.getTipObuce();
            case 2:
                return o.getBoja();
            case 3:
                return o.getVelicina();
            case 4:
                return o.getCena() + "din";

            default:
                return null;
        }
    }

    public Obuca getSelectedObuca(int row) {
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
            Logger.getLogger(TableModelObuca.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametarTipObuce(String tipObuce) {
        this.parametarTipObuce = tipObuce;
        refreshTable();
    }

    public void setParametarBoja(String boja) {
        this.parametarBoja = boja;
        refreshTable();
    }


    public void refreshTable() {
        try {

            obuca.setTipObuce(parametarTipObuce.toLowerCase());
            obuca.setBoja(parametarBoja.toLowerCase());

            lista = ClientController.getInstance().getAllObuca(obuca);
            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Obuca> getLista() {
        return lista;
    }

}
