/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Luka
 */
public class Racun extends AbstractDomainObject {

    private Long racunID;
    private Date datumVreme;
    private String status;
    private Long stornoOdRacunaID;
    private double ukupanIznos;
    private Prodavac prodavac;
    private Musterija musterija;

    public Racun(Long racunID, Date datumVreme, String status, Long stornoOdRacunaID, double ukupanIznos, Prodavac prodavac, Musterija musterija) {
        this.racunID = racunID;
        this.datumVreme = datumVreme;
        this.status = status;
        this.stornoOdRacunaID = stornoOdRacunaID;
        this.ukupanIznos = ukupanIznos;
        this.prodavac = prodavac;
        this.musterija = musterija;
    }

    public Racun() {
    }

    @Override
    public String nazivTabele() {
        return " Racun ";
    }

    @Override
    public String alijas() {
        return " R ";
    }

    @Override
    public String join() {
        return " JOIN PRODAVAC P ON (R.PRODAVACID = P.PRODAVACID) "
                + "JOIN MUSTERIJA M ON (R.MUSTERIJAID = M.MUSTERIJAID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Prodavac p = new Prodavac(rs.getLong("ProdavacID"), rs.getString("P.Ime"), rs.getString("P.Prezime"),
                    rs.getString("P.KorisnickoIme"), rs.getString("P.Lozinka"));
            Lokacija l = new Lokacija(rs.getLong("LokacijaID"),
                    rs.getString("L.Grad"), rs.getString("L.Ulica"), rs.getInt("L.Broj"));
            Musterija m = new Musterija(rs.getLong("MusterijaID"), rs.getString("M.Ime"), rs.getString("M.Prezime"),
                    rs.getString("M.Email"), rs.getString("M.Telefon"), l);
            Racun r = new Racun(rs.getLong("RacunID"), rs.getTimestamp("R.DatumVreme"),
                    rs.getString("R.STATUS"), rs.getLong("R.StornoOdRacunaID"), rs.getDouble("R.UkupanIznos"),
                    p, m);
            lista.add(r);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (DatumVreme, Status, StornoOdRacunaID, UkupanIznos, ProdavacID, MusterijaID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " '" + new Timestamp(datumVreme.getTime()) + "', '" + status + "' " + stornoOdRacunaID + ", " + ukupanIznos + ", " + prodavac.getProdavacID() + ", "
                + musterija.getMusterijaID() + " ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " DatumVreme = '" + new Timestamp(datumVreme.getTime()) + "', "
                + "Status = '" + status + "', UkupanIznos = " + ukupanIznos;
    }

    @Override
    public String uslov() {
        return " RacunID = " + racunID;
    }

    @Override
    public String dodatniUslov() {
        if (racunID != null) {
            return " WHERE racunID = " + racunID;
        }
        return " WHERE LOWER(M.IME) LIKE '%" + musterija.getIme() + "%' "
                + "AND LOWER(M.PREZIME) LIKE '%" + musterija.getPrezime() + "%' ";
    }

    @Override
    public String orderBy() {
        return " ORDERBY RACUNID ASC ";
    }

    public Long getRacunID() {
        return racunID;
    }

    public void setRacunID(Long racunID) {
        this.racunID = racunID;
    }

    public Date getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(Date datumVreme) {
        this.datumVreme = datumVreme;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getStornoOdRacunaID() {
        return stornoOdRacunaID;
    }

    public void setStornoOdRacunaID(Long stornoOdRacunaID) {
        this.stornoOdRacunaID = stornoOdRacunaID;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public Prodavac getProdavac() {
        return prodavac;
    }

    public void setProdavac(Prodavac prodavac) {
        this.prodavac = prodavac;
    }

    public Musterija getMusterija() {
        return musterija;
    }

    public void setMusterija(Musterija musterija) {
        this.musterija = musterija;
    }

}
