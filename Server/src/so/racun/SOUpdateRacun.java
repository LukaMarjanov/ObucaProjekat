/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.racun;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Racun;
import domain.StavkaRacuna;
import java.util.ArrayList;
import java.util.HashMap;
import so.AbstractSO;

/**
 *
 * @author Luka
 */
public class SOUpdateRacun extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Racun)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Racun");
        }

        Racun r = (Racun) ado;

        if (r.getStavkeRacuna().isEmpty()) {
            throw new Exception("Racun mora imati barem 1 stavku");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {

        Racun racun = (Racun) ado;

        DBBroker.getInstance().update(ado);

        ArrayList<StavkaRacuna> stareStavke = (ArrayList<StavkaRacuna>) (ArrayList<?>) DBBroker.getInstance().
                select(new StavkaRacuna(racun, 0, 0, 0, 0, null));

        HashMap<Integer, StavkaRacuna> mapaStarih = new HashMap<>();

        for (StavkaRacuna sr : stareStavke) {
            mapaStarih.put(sr.getRb(), sr);
        }

        HashMap<Integer, StavkaRacuna> mapaNovih = new HashMap<>();
        for (StavkaRacuna nova : racun.getStavkeRacuna()) {
            mapaNovih.put(nova.getRb(), nova);
        }

        for (StavkaRacuna stara : stareStavke) {
            if (!mapaNovih.containsKey(stara.getRb())) {
                DBBroker.getInstance().delete(stara);
            }
        }

        for (StavkaRacuna nova : racun.getStavkeRacuna()) {
            if (mapaStarih.containsKey(nova.getRb())) {
                DBBroker.getInstance().update(nova);
            }
        }

        for (StavkaRacuna nova : racun.getStavkeRacuna()) {
            if (!mapaStarih.containsKey(nova.getRb())) {
                DBBroker.getInstance().insert(nova);
            }
        }

    }

}
