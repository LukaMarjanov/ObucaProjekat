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
public class Lokacija extends AbstractDomainObject {

    private Long lokacijaID;
    private String grad;
    private String ulica;
    private int broj;

    public Lokacija(Long lokacijaID, String grad, String ulica, int broj) {
        this.lokacijaID = lokacijaID;
        this.grad = grad;
        this.ulica = ulica;
        this.broj = broj;
    }

    public Lokacija() {
    }

    @Override
    public String nazivTabele() {
        return " Lokacija ";
    }

    @Override
    public String alijas() {
        return " L ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
             Lokacija l = new Lokacija(
                    rs.getLong("LokacijaID"),
                    rs.getString("L.Grad"),
                    rs.getString("L.Ulica"),
                    rs.getInt("L.Broj"));
            lista.add(l);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Grad, Ulica, Broj) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " '" + grad + "', '" + ulica + "', " + broj;
    }

    @Override
    public String vrednostiZaUpdate() {
        return " Grad = '" + grad + "', Ulica = '" + ulica + "', Broj = " + broj;
    }

    @Override
    public String uslov() {
        return " LokacijaID = " + lokacijaID;
    }

    @Override
    public String dodatniUslov() {
        if (lokacijaID != null) {
            return " WHERE LokacijaID = " + lokacijaID;
        }
        return " WHERE LOWER(Grad) like '%" + grad + "%' AND LOWER(Ulica) like '%" + ulica + "%' ";
    }

    @Override
    public String orderBy() {
        return " ORDER BY LOKACIJAID ASC ";
    }

    public Long getLokacijaID() {
        return lokacijaID;
    }

    public void setLokacijaID(Long lokacijaID) {
        this.lokacijaID = lokacijaID;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public int getBroj() {
        return broj;
    }

    public void setBroj(int broj) {
        this.broj = broj;
    }

    @Override
    public String toString() {
        return grad + ", " + ulica + " " + broj;
    }

}
