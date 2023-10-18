

import org.junit.Before;
import org.junit.Test;
import tn.esprit.rh.achat.entities.CategorieFournisseur;
import tn.esprit.rh.achat.entities.DetailFournisseur;
import tn.esprit.rh.achat.entities.Fournisseur;

import java.util.Date;

import static org.junit.Assert.*;

public class FournisseurTest {
    private Fournisseur fournisseur;

    @Before
    public void setUp() {
        fournisseur = new Fournisseur();
        fournisseur.setIdFournisseur(1L);
        fournisseur.setCode("ABC123");
        fournisseur.setLibelle("Fournisseur ABC");
        fournisseur.setCategorieFournisseur(CategorieFournisseur.ORDINAIRE);
    }

    @Test
    public void testFournisseurGetterSetter() {
        assertEquals(Long.valueOf(1L), fournisseur.getIdFournisseur());
        assertEquals("ABC123", fournisseur.getCode());
        assertEquals("Fournisseur ABC", fournisseur.getLibelle());
        assertEquals(CategorieFournisseur.ORDINAIRE, fournisseur.getCategorieFournisseur());
    }

    @Test
    public void testFournisseurDetails() {
        DetailFournisseur detailFournisseur = new DetailFournisseur();
        detailFournisseur.setEmail("fournisseur@abc.com");
        detailFournisseur.setDateDebutCollaboration(new Date());
        detailFournisseur.setAdresse("123 Rue ABC");
        detailFournisseur.setMatricule("MAT123");
        fournisseur.setDetailFournisseur(detailFournisseur);

        assertEquals("fournisseur@abc.com", fournisseur.getDetailFournisseur().getEmail());
        assertNotNull(fournisseur.getDetailFournisseur().getDateDebutCollaboration());
        assertEquals("123 Rue ABC", fournisseur.getDetailFournisseur().getAdresse());
        assertEquals("MAT123", fournisseur.getDetailFournisseur().getMatricule());
    }
}
