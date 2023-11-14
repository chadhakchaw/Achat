package tn.esprit.rh.achat;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.repositories.OperateurRepository;
import tn.esprit.rh.achat.services.IOperateurService;
import tn.esprit.rh.achat.services.OperateurServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class OperateurTest {
    private Operateur operateur;

    @InjectMocks
    private OperateurServiceImpl operateurService;
    @Mock
    private OperateurRepository operateurRepository;
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
        assertEquals("kchaw", operateur.getNom(), "kchaw");
        assertEquals("chadha", operateur.getPrenom(), "chadha");
        assertEquals("123",operateur.getPassword(),"123");
    }
    @Test
    public void testIntegrationAvecFacture() {
        Facture facture = new Facture();
        facture.setMontantFacture(200.50f);
        facture.setMontantRemise(5.0f);
        facture.setArchivee(true);
        assertEquals(facture, operateur.getFactures());
    }

    @Test
    public void testAddOperateur() {
        Operateur newOperateur = new Operateur();
        newOperateur.setIdOperateur(2L);
        newOperateur.setNom("ben abdallah");
        newOperateur.setPrenom("thouraya");
        newOperateur.setPassword("0000");
        newOperateur.setFactures(operateur.getFactures());

        when(operateurRepository.save(newOperateur)).thenReturn(newOperateur);

        Operateur addOperateur = operateurService.addOperateur(newOperateur);

        assertNotNull(addOperateur);
        assertEquals(newOperateur.getIdOperateur(), addOperateur.getIdOperateur());
        assertEquals(newOperateur.getNom(), addOperateur.getNom(), "kchaou");
        assertEquals(newOperateur.getPrenom(), addOperateur.getPrenom(), "chacha");
        assertEquals(newOperateur.getPassword(), addOperateur.getPassword(), "new");
        verify(operateurRepository).save(newOperateur);
    }

}