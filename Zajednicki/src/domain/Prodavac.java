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
public class Prodavac extends AbstractDomainObject{

    private Long prodavacID;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String lozinka; 

    @Override
    public boolean equals(Object obj) {
        if(obj == null || (!(obj instanceof Prodavac))){
            return false;
        }
        return ((Prodavac) obj).getProdavacID().equals(this.prodavacID);
    }
    
    

    public Prodavac(Long prodavacID, String ime, String prezime, String email, String lozinka) {
        this.prodavacID = prodavacID;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = email;
        this.lozinka = lozinka;
    }
    

    public Prodavac() {
    }
    
    
    @Override
    public String nazivTabele() {
        return " PRODAVAC "; 
    }

    @Override
    public String alijas() {
        return " P ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while(rs.next()){
            Prodavac p = new Prodavac(rs.getLong("ProdavacID"), rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("KorisnickoIme"), rs.getString("Lozinka"));
            lista.add(p);
        }
        rs.close();
        return lista;
                
    }

    @Override
    public String koloneZaInsert() {
        return " (Ime, Prezime, KorisnickoIme, Lozinka) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " '" + ime + "', '" + prezime + "', '" + korisnickoIme + "', '" + lozinka + "' ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " Ime = '" + ime + "', Prezime = '" + prezime + "', KorisnickoIme = '" + korisnickoIme + "', Lozinka = '" + lozinka + "' ";
    }

    @Override
    public String uslov() {
        return " PRODAVACID = " + prodavacID;
    }

    @Override
    public String dodatniUslov() {
        if(prodavacID != null){
            return " WHERE PRODAVACID = " + prodavacID;
        }
        return " WHERE LOWER(IME) LIKE '%" + ime + "%' AND LOWER(PREZIME) LIKE '%" + prezime + "%' AND LOWER(KORISNICKOIME) LIKE '%" + korisnickoIme + "%' ";
    }

    @Override
    public String orderBy() {
        return " ORDER BY PRODAVACID ASC ";
    }

    @Override
    public String toString() {
        return ime + " " + prezime; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

        
    public Long getProdavacID() {
        return prodavacID;
    }

    public void setProdavacID(Long prodavacID) {
        this.prodavacID = prodavacID;
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

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }
    
}
