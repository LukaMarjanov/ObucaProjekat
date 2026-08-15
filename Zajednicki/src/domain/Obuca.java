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
public class Obuca extends AbstractDomainObject{

    private Long obucaID;
    private String tipObuce;
    private String boja;
    private double velicina;
    private double cena;

    public Obuca(Long obucaID, String tipObuce, String boja, double velicina, double cena) {
        this.obucaID = obucaID;
        this.tipObuce = tipObuce;
        this.boja = boja;
        this.velicina = velicina;
        this.cena = cena;
    }

    @Override
    public String toString() {
        return tipObuce + " (" + velicina + ", " + boja + ", Cena: " + cena + " din)";
    }

    
    public Obuca() {
    }
    
    @Override
    public String nazivTabele() {
        return " OBUCA ";
    }

    @Override
    public String alijas() {
        return " O ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while(rs.next()){
            Obuca o = new Obuca(rs.getLong("ObucaID"), rs.getString("TipObuce"), rs.getString("Boja"),
                    rs.getDouble("Velicina"), rs.getDouble("Cena"));
            lista.add(o);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (TipObuce, Boja, Velicina, Cena) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " '" + tipObuce + "', '" + boja + "', '" + velicina + "', " + cena;
    }

    @Override
    public String vrednostiZaUpdate() {
        return " Cena = " + cena;
    }

    @Override
    public String uslov() {
        return " OBUCAID = " + obucaID;
    }

    @Override
    public String dodatniUslov() {
        if(obucaID != null){
            return " WHERE OBUCAID = " + obucaID;
        }
        return " WHERE LOWER(TIPOBUCE) LIKE '%" + tipObuce + "%' AND LOWER(BOJA) LIKE '%" + boja + "%' ";
    }

    @Override
    public String orderBy() {
        return " ORDER BY OBUCAID ASC ";
    }

    public Long getObucaID() {
        return obucaID;
    }

    public void setObucaID(Long obucaID) {
        this.obucaID = obucaID;
    }

    public String getTipObuce() {
        return tipObuce;
    }

    public void setTipObuce(String tipObuce) {
        this.tipObuce = tipObuce;
    }

    public String getBoja() {
        return boja;
    }

    public void setBoja(String boja) {
        this.boja = boja;
    }

    public double getVelicina() {
        return velicina;
    }

    public void setVelicina(double velicina) {
        this.velicina = velicina;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }
    
}
