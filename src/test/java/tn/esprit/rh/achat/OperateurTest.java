package tn.esprit.rh.achat;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.entities.Facture;

public class OperateurTest {
    private Operateur operateur;
//test
    @Before
    public void setUp() {
        operateur = new Operateur();
        operateur.setIdOperateur(1L);
        operateur.setNom("kchaw");
        operateur.setPrenom("chadha");
        operateur.setPassword("123");
    }

    @Test
    public void testOperateurGetterSetter() {
        assertEquals(Long.valueOf(1L), operateur.getIdOperateur());
        assertEquals("kchaw", operateur.getNom(), "hammami");
        assertEquals("chadha", operateur.getPrenom(), "arslan");
        assertEquals("123",operateur.getPassword(),"0000");
    }
    @Test
    public void testIntegrationAvecFacture() {
        Facture facture = new Facture();
        facture.setMontantFacture(200.50f);
        facture.setMontantRemise(5.0f);
        facture.setArchivee(true);
        assertEquals(facture, operateur.getFactures());
    }

}