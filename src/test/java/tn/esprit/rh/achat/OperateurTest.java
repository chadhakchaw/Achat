package tn.esprit.rh.achat;
import lombok.var;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.ProduitServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.junit.Before;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.entities.Stock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
@ContextConfiguration(classes = {ProduitServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class OperateurTest {

    private Operateur operateur;

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
        assertEquals("kchaw", operateur.getNom());
        assertEquals("chadha", operateur.getPrenom());
        assertEquals("123", operateur.getPassword());
    }

    @Test
    public void testIntegrationAvecFacture() {
        Facture facture = new Facture();
        facture.setIdFacture(1L);
        facture.setMontantFacture(10.0f);
        facture.setMontantRemise(5.4f);
        var date = new Date(2023, 10, 16);
        facture.setDateCreationFacture(date);
        facture.setDateDerniereModificationFacture(date);
        facture.setArchivee(true);
        assertEquals(facture, operateur.getFactures());
    }

    @Test
    public void testMockFacture() {
        Facture facture = mock(Facture.class);
        when(facture.getArchivee()).thenReturn(true);
        operateur.setFactures((Set<Facture>) facture);
        assertEquals("facture du test", operateur.getFactures());
    }


}
