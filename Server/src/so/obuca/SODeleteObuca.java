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
public class SODeleteObuca extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Obuca)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Obuca!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }

}
