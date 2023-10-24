package tn.esprit.rh.achat;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.repositories.ReglementRepository;
import tn.esprit.rh.achat.services.ReglementServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReglementTest {
    private Reglement reglement;

    @Mock
    private ReglementRepository reglementRepository;

    @InjectMocks
    private ReglementServiceImpl reglementService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this); // Initialisation des annotations Mockito
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

    @Test
    public void testIntegrationAvecFacture() {
        // Créez une instance de Facture
        Facture facture = new Facture();
        facture.setMontantRemise(10.0f); // Exemple de montant de remise
        facture.setMontantFacture(100.0f); // Exemple de montant de facture
        facture.setArchivee(false); // Exemple de statut d'archive
        reglement.setFacture(facture); // Associez la facture au règlement
        assertEquals(facture, reglement.getFacture());
    }

    @Test
    public void testRetrieveAllReglements() {
        // Mocking
        List<Reglement> mockReglements = new ArrayList<>();
        when(reglementRepository.findAll()).thenReturn(mockReglements);

        // Test
        List<Reglement> result = reglementService.retrieveAllReglements();

        // Assertions
        assertEquals(mockReglements, result);
        assertTrue(result.isEmpty());
    }
    @Test
    void testAddReglement() {
        // Créez un exemple de Reglement à ajouter
        Reglement newReglement = new Reglement();
        newReglement.setIdReglement(2L);
        newReglement.setMontantPaye(75.0f);
        newReglement.setMontantRestant(25.0f);
        newReglement.setPayee(false);

        // Mocking
        when(reglementRepository.save(newReglement)).thenReturn(newReglement);

        // Test
        Reglement addedReglement = reglementService.addReglement(newReglement);

        // Assertions
        assertNotNull(addedReglement);
        assertEquals(newReglement.getIdReglement(), addedReglement.getIdReglement());
       // assertEquals(newReglement.getMontantPaye(), addedReglement.getMontantPaye(), 0.01f);
        assertEquals(0.01f, addedReglement.getMontantPaye(), 0.01f);
        // assertEquals(newReglement.getMontantRestant(), 0.01f, addedReglement.getMontantRestant());
        assertEquals(0.01f, addedReglement.getMontantRestant(), 0.01f);

        assertEquals(newReglement.getPayee(), addedReglement.getPayee());

        // Vérification que la méthode save a été appelée avec le bon Reglement
        verify(reglementRepository).save(newReglement);
    }
    @Test
    public void testRetrieveReglement() {
        // Créez un exemple de Reglement
        Reglement expectedReglement = new Reglement();
        expectedReglement.setIdReglement(1L);
        expectedReglement.setMontantPaye(100.0f);
        expectedReglement.setMontantRestant(50.0f);
        expectedReglement.setPayee(false);

        // Configurez le mock du repository pour retourner le Reglement attendu
        when(reglementRepository.findById(1L)).thenReturn(java.util.Optional.of(expectedReglement));

        // Appelez la méthode à tester
        Reglement retrievedReglement = reglementService.retrieveReglement(1L);

        // Vérifiez que le Reglement retourné correspond à l'attendu
        assertEquals(expectedReglement, retrievedReglement);
    }

    @Test
    public void testRetrieveReglementByFacture() {
        // Créez un exemple d'ID de Facture
        Long idFacture = 1L;

        // Créez une liste d'exemples de Reglements associés à la facture
        List<Reglement> expectedReglements = new ArrayList<>();
        Reglement reglement1 = new Reglement();
        reglement1.setIdReglement(1L);
        // Ajoutez d'autres attributs au Reglement1
        Reglement reglement2 = new Reglement();
        reglement2.setIdReglement(2L);
        // Ajoutez d'autres attributs au Reglement2
        expectedReglements.add(reglement1);
        expectedReglements.add(reglement2);

        // Configurez le mock du repository pour retourner la liste de Reglements attendus
        when(reglementRepository.retrieveReglementByFacture(idFacture)).thenReturn(expectedReglements);

        // Appelez la méthode à tester
        List<Reglement> retrievedReglements = reglementService.retrieveReglementByFacture(idFacture);

        // Vérifiez que la liste de Reglements retournée correspond à celle attendue
        assertEquals(expectedReglements, retrievedReglements);
    }
    @Test
    public void testGetChiffreAffaireEntreDeuxDate() {
        // Créez des dates de début et de fin d'exemple
        Date startDate = new Date(); // Remplacez par la date de début souhaitée
        Date endDate = new Date(); // Remplacez par la date de fin souhaitée

        // Définissez le chiffre d'affaires attendu
        float expectedChiffreAffaire = 1000.0f; // Remplacez par la valeur attendue

        // Configurez le mock du repository pour retourner le chiffre d'affaires attendu
        when(reglementRepository.getChiffreAffaireEntreDeuxDate(startDate, endDate)).thenReturn(expectedChiffreAffaire);

        // Appelez la méthode à tester
        float retrievedChiffreAffaire = reglementService.getChiffreAffaireEntreDeuxDate(startDate, endDate);

        // Vérifiez que le chiffre d'affaires retourné correspond à celui attendu
        assertEquals(expectedChiffreAffaire, retrievedChiffreAffaire, 0.01f); // Utilisez une marge d'erreur si nécessaire
    }
    @Test
    public void testSetPayee() {
        // Définir l'état de paiement à true
        reglement.setPayee(true);
        assertTrue(reglement.getPayee());

        // Définir l'état de paiement à false
        reglement.setPayee(false);
        assertFalse(reglement.getPayee());
    }
}
