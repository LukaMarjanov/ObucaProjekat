/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.lokacija;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Lokacija;
import so.AbstractSO;

/**
 *
 * @author Luka
 */
public class SOAddLokacija extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Lokacija)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Lokacija!");
        }

        Lokacija l = (Lokacija) ado;

        if (l.getGrad().isEmpty()) {
            throw new Exception("Unesite ime grada");
        }
        if(l.getUlica().isEmpty()){
            throw new Exception("Unesite naziv ulice");
        }
        
       if (l.getBroj() < 1) {
            throw new Exception("Unesite broj ulice koji je veci od 0");
        }
        

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }

}
