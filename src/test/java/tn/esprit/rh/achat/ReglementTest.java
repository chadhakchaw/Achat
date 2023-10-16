
package tn.esprit.rh.achat;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.entities.Facture;
//test
public class ReglementTest {
    private Reglement reglement;

    @Before
    public void setUp() {
        reglement = new Reglement();
        reglement.setIdReglement(1L); // long
        reglement.setMontantPaye(100.0f); // float
        reglement.setMontantRestant(50.0f); // float
        reglement.setPayee(false); // boolean
    }

    @Test
    public void testReglementGetterSetter() {
        assertEquals(Long.valueOf(1L), reglement.getIdReglement());
        assertEquals(100.0f, reglement.getMontantPaye(), 0.01f);
        assertEquals(50.0f, reglement.getMontantRestant(), 0.01f);
        assertEquals(false, reglement.getPayee());
    }

    public void testIntegrationAvecFacture() {
        // Créez une instance de Facture
        Facture facture = new Facture();
        facture.setMontantRemise(10.0f); // Exemple de montant de remise
        facture.setMontantFacture(100.0f); // Exemple de montant de facture
        facture.setArchivee(false); // Exemple de statut d'archive
        assertEquals(facture, reglement.getFacture());
    }

}
