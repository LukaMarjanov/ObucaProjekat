/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.racun;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Racun;
import domain.StavkaRacuna;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import so.AbstractSO;

/**
 *
 * @author Luka
 */
public class SOCancelRacun extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Racun)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Racun!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        PreparedStatement ps = DBBroker.getInstance().insert(ado);

        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        Long stornoRacunID = tableKeys.getLong(1);

        Racun stornoRacun = (Racun) ado;
        stornoRacun.setRacunID(stornoRacunID);

        for (StavkaRacuna stavkaRacuna : stornoRacun.getStavkeRacuna()) {
            stavkaRacuna.setRacun(stornoRacun);
            DBBroker.getInstance().insert(stavkaRacuna);
        }
    }

}
