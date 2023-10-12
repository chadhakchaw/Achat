package tn.esprit.rh.achat;

import org.junit.Before;
import org.junit.Test;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.entities.Stock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ProduitTest {

    private Produit produit;

    @Before
    public void setUp() {
        produit = new Produit();
        produit.setIdProduit(1L);
        produit.setCodeProduit("P123");
        produit.setLibelleProduit("Produit de test");
        produit.setPrix(10.0f);
    }

    @Test
    public void testProduitGetterSetter() {
        assertEquals(Long.valueOf(1L), produit.getIdProduit());
        assertEquals("P123", produit.getCodeProduit());
        assertEquals("Produit de test", produit.getLibelleProduit());
        assertEquals(10.0f, produit.getPrix(), 0.01); // Utilisation d'une marge d'erreur pour les valeurs flottantes
    }


    @Test
    public void testIntegrationAvecCategorie() {
        CategorieProduit categorieProduit = new CategorieProduit();
        categorieProduit.setIdCategorieProduit(1L);
        categorieProduit.setCodeCategorie("C123");
        categorieProduit.setLibelleCategorie("Catégorie de test");

        produit.setCategorieProduit(categorieProduit);

        assertEquals(categorieProduit, produit.getCategorieProduit());
    }

    @Test
    public void testMockStock() {
        Stock stock = mock(Stock.class);
        when(stock.getLibelleStock()).thenReturn("Stock de test");
        produit.setStock(stock);
        assertEquals("Stock de test", produit.getStock().getLibelleStock());
    }
}
