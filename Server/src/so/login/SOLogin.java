/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.login;

import controller.ServerController;
import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Prodavac;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Luka
 */
public class SOLogin extends AbstractSO{

    Prodavac ulogovani;

    public Prodavac getUlogovani() {
        return ulogovani;
    }
    
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if(!(ado instanceof Prodavac)){
            throw new Exception("Prosledjeni objekat nije instanca klase prodavac");
        }
        Prodavac p = (Prodavac) ado;
        for (Prodavac prodavac : ServerController.getInstance().getUlogovaniProdavci()) {
            if(prodavac.getKorisnickoIme().equals(p.getKorisnickoIme())){
                throw new Exception("Ovaj prodavac je vec ulogovan na sistem");
            }
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        Prodavac p = (Prodavac) ado;
        
        ArrayList<Prodavac> listaProdavaca = (ArrayList<Prodavac>) (ArrayList<?>) DBBroker.getInstance().select(ado);
        
        for (Prodavac prodavac : listaProdavaca) {
            if(prodavac.getKorisnickoIme().equals(p.getKorisnickoIme()) && prodavac.getLozinka().equals(p.getLozinka())){
                ulogovani = prodavac;
                ServerController.getInstance().getUlogovaniProdavci().add(prodavac);
                return;
            }
        }
        
        throw new Exception("Ne postoji prodavac sa unetim kredencijalima");
    }
    
}
