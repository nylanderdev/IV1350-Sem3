package iv1350.controller;

import iv1350.dto.SaleDTO;
import iv1350.integration.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTests {
    @BeforeAll
    static void setup() {
        DiscountDAO discountDAO = new MockDiscountDAO();
        ItemDAO itemDAO = new MockItemDAO();
        SaleDAO saleDAO = new MockSaleDAO();
        DAOCollection daoCollection = new DAOCollection(discountDAO, itemDAO, saleDAO);
        DAOCollection.setSingleton(daoCollection);
    }

    @Test
    void paymentIsNullUntilPayment() {
        Controller controller = new Controller();
        SaleDTO saleState = controller.startNewSale();
        assertNull(saleState.PAYMENT);
    }

    @Test
    void discountListIsEmptyNotNull() {
        Controller controller = new Controller();
        SaleDTO saleState = controller.startNewSale();
        assertNotNull(saleState.DISCOUNTS);
        assertTrue(saleState.DISCOUNTS.isEmpty());
    }

    @Test
    void itemOrderIrrelevant() {
        final int VALID_ITEM1 = 1;
        final int VALID_ITEM2 = 2;
        Controller controller1 = new Controller();
        Controller controller2 = new Controller();
        controller1.startNewSale();
        controller2.startNewSale();

        controller1.addItemToSaleWithQuantity(VALID_ITEM2, 2);
        controller1.addItemToSale(VALID_ITEM1);

        controller2.addItemToSale(VALID_ITEM1);
        controller2.addItemToSale(VALID_ITEM2);
        controller2.addItemToSale(VALID_ITEM2);

        SaleDTO saleState1 = controller1.endSale();
        SaleDTO saleState2 = controller2.endSale();
        assertEquals(saleState1.ITEMS_AND_QUANTITIES, saleState2.ITEMS_AND_QUANTITIES);
    }
}
