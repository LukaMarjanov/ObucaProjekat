/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.musterija;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Musterija;
import java.util.ArrayList;
import java.util.regex.Pattern;
import so.AbstractSO;

/**
 *
 * @author Luka
 */
public class SOUpdateMusterija extends AbstractSO {

    private static final Pattern EMAIL_PATTERN
            = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    private static final Pattern TELEFON_PATTERN
            = Pattern.compile("^06[0-9]{8}$");

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Musterija)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Musterija!");
        }

        Musterija m = (Musterija) ado;

        if (!EMAIL_PATTERN.matcher(m.getEmail()).matches()) {
            throw new Exception("Email nije u ispravnom formatu!");
        }

        if (!TELEFON_PATTERN.matcher(m.getTelefon()).matches()) {
            throw new Exception("Telefon mora biti u formatu 06XXXXXXXX!");
        }

        ArrayList<Musterija> musterije = (ArrayList<Musterija>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Musterija musterija : musterije) {
            if (!musterija.getMusterijaID().equals(m.getMusterijaID())) {
                if (musterija.getEmail().equals(m.getEmail())) {
                    throw new Exception("Musterija sa tim emailom vec postoji!");
                }
                if (musterija.getTelefon().equals(m.getTelefon())) {
                    throw new Exception("Musterija sa tim telefonom vec postoji!");
                }
            }
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }

}
