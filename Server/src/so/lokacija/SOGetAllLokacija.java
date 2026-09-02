/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.lokacija;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Lokacija;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Luka
 */
public class SOGetAllLokacija extends AbstractSO {

    private ArrayList<Lokacija> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Lokacija)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Lokacija!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> lokacije = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Lokacija>) (ArrayList<?>) lokacije;
    }

    public ArrayList<Lokacija> getLista() {
        return lista;
    }

}
