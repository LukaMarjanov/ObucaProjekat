/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import controller.ClientController;
import domain.Musterija;
import domain.Racun;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Luka
 */
public class TableModelRacuni extends AbstractTableModel implements Runnable {

    private ArrayList<Racun> lista;
    private String[] kolone = {"ID", "Musterija", "Zaposleni",
        "Datum i vreme", "Ukupan iznos", "Status", "Storno od racuna"};
    private String parametarIme = "";
    private String parametarPrezime = "";
    private Racun racun = new Racun(null, null, null, null, 0, null, new Musterija(null, "", "", "", "", null), null);

    public TableModelRacuni(){
        try {
            lista = ClientController.getInstance().getAllRacun(new Racun(null, null, null, null, 0,
                    null, new Musterija(null, "", "", "", "", null), null));
        } catch (Exception ex) {
            Logger.getLogger(TableModelRacuni.class.getName()).log(Level.SEVERE, null, ex);
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
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Racun r = lista.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

        switch (columnIndex) {
            case 0:
                return r.getRacunID();
            case 1:
                return r.getMusterija();
            case 2:
                return r.getProdavac();
            case 3:
                return sdf.format(r.getDatumVreme());
            case 4:
                return r.getUkupanIznos() + "din";
            case 5:
                return r.getStatus();
            case 6:
                if (r.getStornoOdRacunaID() == 0) {
                    return null;
                }
                return r.getStornoOdRacunaID();

            default:
                return null;
        }
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(10000);
                refreshTable();
            } catch (InterruptedException ex) {
                Logger.getLogger(TableModelRacuni.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(TableModelRacuni.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void setParametarIme(String parametarIme) {
        this.parametarIme = parametarIme;
        refreshTable();
    }

    public void setParametarPrezime(String parametarPrezime){ 
        this.parametarPrezime = parametarPrezime;
        refreshTable();
    }

    private void refreshTable() {
        try {
            racun.getMusterija().setIme(parametarIme);
            racun.getMusterija().setPrezime(parametarPrezime);

            lista = ClientController.getInstance().getAllRacun(racun);
            fireTableDataChanged();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Racun getSelectedRacun(int row) {
        return lista.get(row);
    }

}
