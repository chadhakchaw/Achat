package tn.esprit.rh.achat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.Stock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
public class stockTest {



    @SpringBootTest
    public class StockTest {
//test
        @Mock
        private Stock stock;

        @BeforeEach
        public void setUp() {
            stock = new Stock("Test Stock", 100, 10);
        }

        @Test
        public void testGetters() {
            assertEquals("Test Stock", stock.getLibelleStock());
            assertEquals(100, stock.getQte());
            assertEquals(10, stock.getQteMin());
        }

        @Test
        public void testSetters() {
            stock.setLibelleStock("Updated Stock");
            stock.setQte(200);
            stock.setQteMin(20);

            assertEquals("Updated Stock", stock.getLibelleStock());
            assertEquals(200, stock.getQte());
            assertEquals(20, stock.getQteMin());
        }
    }
}
