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
    private ArrayList<StavkaRacuna> stavkeRacuna;

    public Racun() {
    }

    public Racun(Long racunID, Date datumVreme, String status, Long stornoOdRacunaID, double ukupanIznos, Prodavac prodavac, Musterija musterija, ArrayList<StavkaRacuna> stavkeRacuna) {
        this.racunID = racunID;
        this.datumVreme = datumVreme;
        this.status = status;
        this.stornoOdRacunaID = stornoOdRacunaID;
        this.ukupanIznos = ukupanIznos;
        this.prodavac = prodavac;
        this.musterija = musterija;
        this.stavkeRacuna = stavkeRacuna;
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
        return " JOIN PRODAVAC P ON ( P.PRODAVACID = R.PRODAVACID ) "
                + " JOIN MUSTERIJA M ON ( M.MUSTERIJAID = R.MUSTERIJAID ) "
                + " JOIN LOKACIJA L ON ( L.LOKACIJAID = M.LOKACIJAID ) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Lokacija l = new Lokacija(
                    rs.getLong("LokacijaID"),
                    rs.getString("L.Grad"),
                    rs.getString("L.Ulica"),
                    rs.getInt("L.Broj")
            );
            Musterija m = new Musterija(
                    rs.getLong("MusterijaID"),
                    rs.getString("M.Ime"),
                    rs.getString("M.Prezime"),
                    rs.getString("M.Email"),
                    rs.getString("M.Telefon"),
                    l
            );
            Prodavac p = new Prodavac(
                    rs.getLong("ProdavacID"),
                    rs.getString("ime"),
                    rs.getString("prezime"),
                    rs.getString("korisnickoIme"),
                    rs.getString("lozinka")
            );
            Racun r = new Racun(
                    rs.getLong("RacunID"),
                    rs.getTimestamp("R.DatumVreme"),
                    rs.getString("R.Status"),
                    rs.getLong("StornoOdRacunaID"),
                    rs.getDouble("R.UkupanIznos"),
                    p,
                    m,
                    new ArrayList<>()
            );
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
        return " '" + new Timestamp(datumVreme.getTime()) + "', "
                + "'" + status + "', " + stornoOdRacunaID + ", " + ukupanIznos + ", "
                + prodavac.getProdavacID() + ", " + musterija.getMusterijaID();
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
        return " ORDER BY RACUNID ASC ";
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

    public ArrayList<StavkaRacuna> getStavkeRacuna() {
        return stavkeRacuna;
    }

    public void setStavkeRacuna(ArrayList<StavkaRacuna> stavkeRacuna) {
        this.stavkeRacuna = (stavkeRacuna != null) ? stavkeRacuna : new ArrayList<>();
    }

    public Long getStornoOdRacunaID() {
        return stornoOdRacunaID;
    }

    public void setStornoOdRacunaID(Long stornoOdRacunaID) {
        this.stornoOdRacunaID = stornoOdRacunaID;
    }
}
