/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import domain.StavkaRacuna;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Luka
 */
public class TableModelStavkeRacuna extends AbstractTableModel {

    private ArrayList<StavkaRacuna> lista;
    private String[] kolone = {"Rb", "Obuca", "Cena", "Kolicina", "Iznos"};
    private int rb = 0;

    public TableModelStavkeRacuna() {
        lista = new ArrayList<>();
    }

    public TableModelStavkeRacuna(ArrayList<StavkaRacuna> stavkeRacuna) {
        lista = stavkeRacuna;
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
        StavkaRacuna sr = lista.get(row);

        switch (column) {
            case 0:
                return sr.getRb();
            case 1:
                return sr.getObuca();
            case 2:
                return sr.getCena() + "din";
            case 3:
                return sr.getKolicina();
            case 4:
                return sr.getIznos() + "din";
            default:
                throw null;
        }
    }

    public ArrayList<StavkaRacuna> getLista() {
        return lista;
    }

    public void dodajStavku(StavkaRacuna sr) {
        for (StavkaRacuna stavkaRacuna : lista) {
            if(stavkaRacuna.getObuca().getObucaID().equals(sr.getObuca().getObucaID())){
                stavkaRacuna.setKolicina(stavkaRacuna.getKolicina() + sr.getKolicina());
                stavkaRacuna.setIznos(stavkaRacuna.getIznos() + sr.getIznos());
                fireTableDataChanged();
                return;
            }
        }
            
            rb = lista.size();
            sr.setRb(++rb);
            lista.add(sr);
            fireTableDataChanged();
            
            
        }
    

    public double vratiUkupanIznos() {
        double ukupanIznos = 0;
        
        for (StavkaRacuna stavkaRacuna : lista) {
            ukupanIznos += stavkaRacuna.getIznos();
        }
        
        return ukupanIznos;
    }

    public void obrisiStavkuRacuna(int row) {
        lista.remove(row);
        
        rb = 0;
        
        for (StavkaRacuna stavkaRacuna : lista) {
            stavkaRacuna.setRb(++rb);
        }
        
        fireTableDataChanged();
    }

}
