import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Before;
import org.junit.Test;
import tn.esprit.rh.achat.entities.Fournisseur;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class FournisseurTest {

    private Fournisseur fournisseur;

    @Before
    public void setUp() {
        // Initialisation avant chaque test
        fournisseur = new Fournisseur();
    }

    @Test
    public void testGetIdFournisseur() {
        // Définir l'ID du fournisseur
        fournisseur.setIdFournisseur(1L);

        // Vérifier si l'ID est correct
        assertEquals(1L, fournisseur.getIdFournisseur());
    }

    @Test
    public void testGetCode() {
        // Définir le code du fournisseur
        fournisseur.setCode("F123");

        // Vérifier si le code est correct
        assertEquals("F123", fournisseur.getCode());
    }

    @Test
    public void testGetLibelle() {
        // Définir le libellé du fournisseur
        fournisseur.setLibelle("Fournisseur ABC");

        // Vérifier si le libellé est correct
        assertEquals("Fournisseur ABC", fournisseur.getLibelle());
    }

    // Vous pouvez ajouter d'autres tests pour les autres attributs et méthodes de la classe Fournisseur
}
