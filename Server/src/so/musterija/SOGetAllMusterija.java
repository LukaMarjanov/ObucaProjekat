/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.musterija;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Musterija;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Luka
 */
public class SOGetAllMusterija extends AbstractSO {
    
    ArrayList<Musterija> lista;
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Musterija)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Musterija");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> listaMusterija = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Musterija>) (ArrayList<?>) listaMusterija;
    }

    public ArrayList<Musterija> getLista() {
        return lista;
    }
    

}
