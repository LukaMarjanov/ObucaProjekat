/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.obuca;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Obuca;
import so.AbstractSO;

/**
 *
 * @author Luka
 */
public class SOAddObuca extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Obuca)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Obuca!");
        }

        Obuca o = (Obuca) ado;

        if (o.getVelicina() < 35 || o.getVelicina() > 50) {
            throw new Exception("Velicina mora biti izmedju 35 i 50!");
        }

        if (o.getCena() <= 0) {
            throw new Exception("Cena mora biti veca od 0!");
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }

}
