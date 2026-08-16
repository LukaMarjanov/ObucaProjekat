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
import javax.swing.JOptionPane;
import so.AbstractSO;

/**
 *
 * @author Luka
 */
public class SOGetAllRacun extends AbstractSO {

    private ArrayList<Racun> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Racun)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Racun");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {

        ArrayList<AbstractDomainObject> racuni = DBBroker.getInstance().select(ado);

        lista = (ArrayList<Racun>) (ArrayList<?>) racuni;
        
        for (Racun trenutniRacun : lista) {
            
            StavkaRacuna sr = new StavkaRacuna();
            sr.setRacun(trenutniRacun);
            
            ArrayList<StavkaRacuna> listaStavki = (ArrayList<StavkaRacuna>) (ArrayList<?>) DBBroker.getInstance().select(sr);
            
            trenutniRacun.setStavkeRacuna(listaStavki);
        }

    }

    public ArrayList<Racun> getLista() {
        return lista;
    }

}
