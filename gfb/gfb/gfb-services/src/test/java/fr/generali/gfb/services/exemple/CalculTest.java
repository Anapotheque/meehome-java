package fr.generali.gfb.services.exemple;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;
import fr.generali.gfb.services.exemple.domain.ListeNombresEntier;

public class CalculTest extends TestCase {

    public void testAddition() {
        ListeNombresEntier lNE = new ListeNombresEntier();
        List<Integer> l = new ArrayList<Integer>();
        l.add(7);
        l.add(4);
        l.add(6);
        l.add(3);

        lNE.setListNombresEntier(l);

        CalculServiceImpl calcul = new CalculServiceImpl();
        Integer res = calcul.additionner(lNE);

        Assert.assertEquals(res.intValue(), 20);
    }

    public void testMultiplication() {
        ListeNombresEntier lNE = new ListeNombresEntier();
        List<Integer> l = new ArrayList<Integer>();
        l.add(4);
        l.add(5);
        l.add(2);

        lNE.setListNombresEntier(l);

        CalculServiceImpl calcul = new CalculServiceImpl();
        Integer res = calcul.multiplier(lNE);

        Assert.assertEquals(res.intValue(), 40);
    }
}