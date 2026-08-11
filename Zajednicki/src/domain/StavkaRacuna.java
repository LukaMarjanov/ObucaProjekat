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
public class StavkaRacuna extends AbstractDomainObject{

     private Racun racun;
    private int rb;
    private int kolicina;
    private double cena;
    private double iznos;
    private Obuca obuca;

    public StavkaRacuna() {
    }

    public StavkaRacuna(Racun racun, int rb, int kolicina, double cena, double iznos, Obuca obuca) {
        this.racun = racun;
        this.rb = rb;
        this.kolicina = kolicina;
        this.cena = cena;
        this.iznos = iznos;
        this.obuca = obuca;
    }

    @Override
    public String nazivTabele() {
        return " StavkaRacuna ";
    }

    @Override
    public String alijas() {
        return " SR ";
    }

    @Override
    public String join() {
        return " JOIN RACUN R ON ( R.RACUNID = SR.RACUNID ) "
                + " JOIN PRODAVAC P ON ( P.PRODAVACID = R.PRODAVACID ) "
                + " JOIN MUSTERIJA M ON ( M.MUSTERIJAID = R.MUSTERIJAID ) "
                + " JOIN LOKACIJA L ON ( L.LOKACIJAID = M.LOKACIJAID ) "
                + " JOIN OBUCA O ON ( O.OBUCAID = SR.OBUCAID ) ";
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
                    null,
                    rs.getDouble("R.UkupanIznos"),
                    p,
                    m,
                    new ArrayList<>()
            );
            Obuca o = new Obuca(
                    rs.getLong("ObucaID"),
                    rs.getString("O.TipObuce"),
                    rs.getString("O.Boja"),
                    rs.getDouble("O.Velicina"),
                    rs.getDouble("O.Cena")
            );
            StavkaRacuna sr = new StavkaRacuna(
                    r,
                    rs.getInt("SR.Rb"),
                    rs.getInt("SR.Kolicina"),
                    rs.getDouble("SR.Cena"),
                    rs.getDouble("SR.Iznos"),
                    o
            );

            lista.add(sr);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (RacunID, Rb, Kolicina, Cena, Iznos, ObucaID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + racun.getRacunID()
                + ", " + rb
                + ", " + kolicina
                + ", " + cena
                + ", " + iznos
                + ", " + obuca.getObucaID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " Kolicina = " + kolicina
                + ", Cena = " + cena
                + ", Iznos = " + iznos
                + ", ObucaID = " + obuca.getObucaID() + " ";
    }

    @Override
    public String uslov() {
        return " RacunID = " + racun.getRacunID() + " AND Rb = " + rb;
    }

    @Override
    public String dodatniUslov() {
        return " WHERE R.RacunID = " + racun.getRacunID();
    }

    @Override
    public String orderBy() {
        return " ORDER BY RB ASC ";
    }

    public Racun getRacun() {
        return racun;
    }

    public void setRacun(Racun racun) {
        this.racun = racun;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public Obuca getObuca() {
        return obuca;
    }

    public void setObuca(Obuca obuca) {
        this.obuca = obuca;
    }
}
