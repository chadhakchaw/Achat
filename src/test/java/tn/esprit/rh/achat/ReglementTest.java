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
        MockitoAnnotations.initMocks(this); // Initialization of Mockito annotations
        reglement = new Reglement();
        reglement.setIdReglement(1L);
        reglement.setMontantPaye(100.0f);
        reglement.setMontantRestant(50.0f);
        reglement.setPayee(false);
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
        Facture facture = new Facture();
        facture.setMontantRemise(10.0f);
        facture.setMontantFacture(100.0f);
        facture.setArchivee(false);
        reglement.setFacture(facture);
        assertEquals(facture, reglement.getFacture());
    }

    @Test
    public void testRetrieveAllReglements() {
        List<Reglement> mockReglements = new ArrayList<>();
        when(reglementRepository.findAll()).thenReturn(mockReglements);

        List<Reglement> result = reglementService.retrieveAllReglements();

        assertEquals(mockReglements, result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testAddReglement() {
        Reglement newReglement = new Reglement();
        newReglement.setIdReglement(2L);
        newReglement.setMontantPaye(75.0f);
        newReglement.setMontantRestant(25.0f);
        newReglement.setPayee(false);

        when(reglementRepository.save(newReglement)).thenReturn(newReglement);

        Reglement addedReglement = reglementService.addReglement(newReglement);

        assertNotNull(addedReglement);
        assertEquals(newReglement.getIdReglement(), addedReglement.getIdReglement());
        assertEquals(newReglement.getMontantPaye(), addedReglement.getMontantPaye(), 0.01f);
        assertEquals(newReglement.getMontantRestant(), addedReglement.getMontantRestant(), 0.01f);
        assertEquals(newReglement.getPayee(), addedReglement.getPayee());

        verify(reglementRepository).save(newReglement);
    }

    @Test
    public void testRetrieveReglement() {
        Reglement expectedReglement = new Reglement();
        expectedReglement.setIdReglement(1L);
        expectedReglement.setMontantPaye(100.0f);
        expectedReglement.setMontantRestant(50.0f);
        expectedReglement.setPayee(false);

        when(reglementRepository.findById(1L)).thenReturn(java.util.Optional.of(expectedReglement));

        Reglement retrievedReglement = reglementService.retrieveReglement(1L);

        assertEquals(expectedReglement, retrievedReglement);
    }

    @Test
    public void testRetrieveReglementByFacture() {
        Long idFacture = 1L;

        List<Reglement> expectedReglements = new ArrayList<>();
        Reglement reglement1 = new Reglement();
        reglement1.setIdReglement(1L);
        // Add other attributes to reglement1
        Reglement reglement2 = new Reglement();
        reglement2.setIdReglement(2L);
        // Add other attributes to reglement2
        expectedReglements.add(reglement1);
        expectedReglements.add(reglement2);

        when(reglementRepository.retrieveReglementByFacture(idFacture)).thenReturn(expectedReglements);

        List<Reglement> retrievedReglements = reglementService.retrieveReglementByFacture(idFacture);

        assertEquals(expectedReglements, retrievedReglements);
    }
}
