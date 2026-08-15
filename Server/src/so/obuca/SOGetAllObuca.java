/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.obuca;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Obuca;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Luka
 */
public class SOGetAllObuca extends AbstractSO {

    public ArrayList<Obuca> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Obuca)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Obuca");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> listaObuca = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Obuca>) (ArrayList<?>) listaObuca;
    }

    public ArrayList<Obuca> getLista() {
        return lista;
    }
}
