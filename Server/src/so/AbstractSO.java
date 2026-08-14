/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import db.DBBroker;
import domain.AbstractDomainObject;
import java.sql.SQLException;

/**
 *
 * @author Luka
 */
public abstract class AbstractSO {

    protected abstract void validate(AbstractDomainObject ado) throws Exception;

    protected abstract void execute(AbstractDomainObject ado) throws Exception;

    public void templateExecute(AbstractDomainObject ado) throws Exception {
        try {
            validate(ado);
            execute(ado);
            commit();
        }catch(Exception e){
            rollback();
            throw e;
        }

    }

    private void commit() throws SQLException {
        DBBroker.getInstance().getConnection().commit();
    }

    private void rollback() throws SQLException {
        DBBroker.getInstance().getConnection().rollback();
    }
}
