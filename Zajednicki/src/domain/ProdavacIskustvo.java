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
public class ProdavacIskustvo extends AbstractDomainObject {

    private Prodavac prodavac;
    private Iskustvo iskustvo;
    private String opis;

    public ProdavacIskustvo() {
    }

    public ProdavacIskustvo(Prodavac prodavac, Iskustvo iskustvo, String opis) {
        this.prodavac = prodavac;
        this.iskustvo = iskustvo;
        this.opis = opis;
    }

    @Override
    public String nazivTabele() {
        return " PRODAVACISKUSTVO ";
    }

    @Override
    public String alijas() {
        return " PI ";
    }

    @Override
    public String join() {
        return " JOIN PRODAVAC P ON (PI.PRODAVACID = P.PRODAVACID) "
                + "JOIN ISKUSTVO I ON (PI.ISKUSTVOID = I.ISKUSTVOID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Prodavac p = new Prodavac(rs.getLong("ProdavacID"), rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("KorisnickoIme"), rs.getString("Lozinka"));
            Iskustvo i = new Iskustvo(rs.getLong("IskustvoID"), rs.getString("Opis"));
            ProdavacIskustvo pi = new ProdavacIskustvo(prodavac, iskustvo, rs.getString("Opis"));
            lista.add(pi);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Prodavacid, IskustvoID, OPIS) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + prodavac.getProdavacID()
                + ", " + iskustvo.getIskustvoID()
                + ", '" + opis + "' ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " Opis = '" + opis + "' ";
    }

    @Override
    public String uslov() {
        return " ProdavacID = " + prodavac.getProdavacID()
                + " AND IskustvoID = " + iskustvo.getIskustvoID();
    }

    @Override
    public String dodatniUslov() {
        return " WHERE PRODAVACID = " + prodavac.getProdavacID();
    }

    @Override
    public String orderBy() {
        return " ORDER BY ISKUSTVOID ASC ";
    }

    public Prodavac getProdavac() {
        return prodavac;
    }

    public void setProdavac(Prodavac prodavac) {
        this.prodavac = prodavac;
    }

    public Iskustvo getIskustvo() {
        return iskustvo;
    }

    public void setIskustvo(Iskustvo iskustvo) {
        this.iskustvo = iskustvo;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

}
