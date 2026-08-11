/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Luka
 */
public class Iskustvo extends AbstractDomainObject{

    private Long iskustvoID;
    private String opis;

    public Iskustvo(Long iskustvoID, String opis) {
        this.iskustvoID = iskustvoID;
        this.opis = opis;
    }

    public Iskustvo() {
    }
    
    
    
    @Override
    public String nazivTabele() {
        return " ISKUSTVO ";
    }

    @Override
    public String alijas() {
        return " I ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while(rs.next()){
            Iskustvo i = new Iskustvo(rs.getLong("IskustvoID"), rs.getString("Opis"));
            lista.add(i);
        }
        rs.close();
        return lista;
    }
    

    @Override
    public String koloneZaInsert() {
        return " (Opis) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " '" + opis + "' ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " Opis = '" + opis + "' ";
    }

    @Override
    public String uslov() {
        return " IskustvoID = " + iskustvoID;
    }

    @Override
    public String dodatniUslov() {
        return " WHERE LOWER(OPIS) LIKE '%" + opis + "%' ";
    }

    @Override
    public String orderBy() {
        return " ORDERBY ISKUSTVOID ASC ";
    }

    public Long getIskustvoID() {
        return iskustvoID;
    }

    public void setIskustvoID(Long iskustvoID) {
        this.iskustvoID = iskustvoID;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
    
}
