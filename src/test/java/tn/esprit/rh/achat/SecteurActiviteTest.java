package tn.esprit.rh.achat;

import org.junit.jupiter.api.Test;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.repositories.FournisseurRepository;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class SecteurActiviteTest {

    @Test
    public void testGetterSetter() {
        SecteurActivite secteurActivite = new SecteurActivite();
        secteurActivite.setIdSecteurActivite(1L);
        secteurActivite.setCodeSecteurActivite("123");
        secteurActivite.setLibelleSecteurActivite("Test Secteur");
        // Vous pouvez également initialiser d'autres propriétés si nécessaire

        assertEquals(1L, secteurActivite.getIdSecteurActivite());
        assertEquals("123", secteurActivite.getCodeSecteurActivite());
        assertEquals("Test Secteur", secteurActivite.getLibelleSecteurActivite());
        // Assurez-vous de tester d'autres getters et setters selon vos besoins.
    }
    @Test
    public void testIntegrationAvecFournisseur() {
        // Créez une instance de SecteurActivite
        SecteurActivite secteurActivite = new SecteurActivite();
        secteurActivite.setCodeSecteurActivite("123");
        secteurActivite.setLibelleSecteurActivite("Test Secteur");

        // Créez une instance de Fournisseur
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setCode("F123");
        fournisseur.setLibelle("Test Fournisseur");

        // Associez le secteur d'activité au fournisseur
        Set<SecteurActivite> secteurActivites = new HashSet<>();
        secteurActivites.add(secteurActivite);
        fournisseur.setSecteurActivites(secteurActivites);

        // Assurez-vous que le secteur d'activité est associé au fournisseur
        assertEquals(1, fournisseur.getSecteurActivites().size());
        assertEquals(secteurActivite, fournisseur.getSecteurActivites().iterator().next());
    }
}
