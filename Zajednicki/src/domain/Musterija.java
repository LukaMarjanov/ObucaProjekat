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
public class Musterija extends AbstractDomainObject {

    private Long musterijaID;
    private String ime;
    private String prezime;
    private String email;
    private String telefon;
    private Lokacija lokacija;

    public Musterija(Long musterijaID, String ime, String prezime, String email, String telefon, Lokacija lokacija) {
        this.musterijaID = musterijaID;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.telefon = telefon;
        this.lokacija = lokacija;
    }

    public Musterija() {
    }

    @Override
    public String nazivTabele() {
        return " MUSTERIJA ";
    }

    @Override
    public String alijas() {
        return " M ";
    }

    @Override
    public String join() {
        return " JOIN LOKACIJA L ON (L.LOKACIJAID = M.LOKACIJAID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Lokacija l = new Lokacija(rs.getLong("LokacijaID"),
                    rs.getString("L.Grad"), rs.getString("L.Ulica"), rs.getInt("L.Broj"));
            Musterija m = new Musterija(rs.getLong("MusterijaID"), rs.getString("M.Ime"), rs.getString("M.Prezime"),
                    rs.getString("M.Email"), rs.getString("M.Telefon"), l);
            lista.add(m);
            
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Ime, Prezime, Email, Telefon, LokacijaID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " '" + ime + "', '" + prezime + "', '" + email + "', '" + telefon + "', " + lokacija.getLokacijaID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " EMAIL = '" + email + "', TELEFON = '" + telefon + "', Lokacija = " + lokacija.getLokacijaID();
    }

    @Override
    public String uslov() {
        return " MUSTERIJAID = " + musterijaID;
    }

    @Override
    public String dodatniUslov() {
        if(musterijaID != null){
            return " WHERE MUSTERIJAID = " + musterijaID;
        }
        return " WHERE LOWER(IME) LIKE '%" + ime + "%' AND LOWER(PREZIME) LIKE '%" + prezime + "%' AND LOWER(EMAIL) LIKE '%" + email + "%' ";
    }

    @Override
    public String orderBy() {
        return " ORDER BY MUSTERIJAID ASC ";
    }

    public Long getMusterijaID() {
        return musterijaID;
    }

    public void setMusterijaID(Long musterijaID) {
        this.musterijaID = musterijaID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Lokacija getLokacija() {
        return lokacija;
    }

    public void setLokacija(Lokacija lokacija) {
        this.lokacija = lokacija;
    }

}
