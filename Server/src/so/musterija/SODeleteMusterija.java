/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.musterija;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Musterija;
import so.AbstractSO;

/**
 *
 * @author Luka
 */
public class SODeleteMusterija extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Musterija)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Musterija!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }

}
