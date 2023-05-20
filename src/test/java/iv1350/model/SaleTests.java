package iv1350.model;

import iv1350.dto.PaymentDTO;
import iv1350.dto.SaleDTO;
import iv1350.integration.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaleTests {
    static final int CUSTOMER_ID_NO_DISCOUNT = 1;
    static final int CUSTOMER_ID_10_DISCOUNT = 2;
    static final int CUSTOMER_ID_25_DISCOUNT = 3;
    static final int CUSTOMER_ID_10_AND_25_DISCOUNT = 6;
    static final int VALID_ITEM_ID = 2;

    @BeforeAll
    static void setup() {
        DiscountDAO discountDAO = new MockDiscountDAO();
        ItemDAO itemDAO = new MockItemDAO();
        SaleDAO saleDAO = new MockSaleDAO();
        DAOCollection daoCollection = new DAOCollection(discountDAO, itemDAO, saleDAO);
        DAOCollection.setSingleton(daoCollection);
    }

    @Test
    void itemFinalizationDisallowsItemAdditionWhenEmpty() throws ItemNotFoundException {
        Sale sale = new Sale();
        SaleDTO initialState = sale.getSaleState();
        sale.finalizeItemSelection();
        sale.addItem(VALID_ITEM_ID);
        SaleDTO modifiedState = sale.getSaleState();
        assertEquals(initialState.ITEMS_AND_QUANTITIES, modifiedState.ITEMS_AND_QUANTITIES);
    }

    @Test
    void itemFinalizationDisallowsItemAdditionWhenNonEmpty() throws ItemNotFoundException{
        Sale sale = new Sale();
        sale.addItem(VALID_ITEM_ID);
        SaleDTO initialState = sale.getSaleState();
        sale.finalizeItemSelection();
        sale.addItem(VALID_ITEM_ID);
        SaleDTO modifiedState = sale.getSaleState();
        assertEquals(initialState.ITEMS_AND_QUANTITIES, modifiedState.ITEMS_AND_QUANTITIES);
    }

    @Test
    void multipleDiscountsCanBeAppliedCorrectly() throws ItemNotFoundException {
        Sale sale = new Sale();
        sale.addItem(VALID_ITEM_ID);
        sale.addItem(VALID_ITEM_ID);
        sale.addItem(VALID_ITEM_ID);
        int totalBeforeDiscount = sale.getSaleState().TOTAL_PRICE;
        sale.applyEligibleDiscounts(CUSTOMER_ID_10_AND_25_DISCOUNT);
        int totalAfterDiscount = sale.getSaleState().TOTAL_PRICE;

        int expectedDiscount = 100 - ((100 - 10) * (100 - 25)) / 100;
        int actualDiscount = 100 * (totalBeforeDiscount - totalAfterDiscount) / totalBeforeDiscount;
        assertEquals(expectedDiscount, actualDiscount);
    }

    @Test
    void overpaymentResultsInCorrectChange() throws ItemNotFoundException {
        final int EXPECTED_CHANGE = 79;
        Sale sale = new Sale();
        sale.addItemWithQuantity(VALID_ITEM_ID, 3);
        int total = sale.getSaleState().TOTAL_PRICE;
        sale.finalizeItemSelection();
        sale.registerPayment(total + EXPECTED_CHANGE);
        PaymentDTO payment = sale.getSaleState().PAYMENT;
        assertEquals(EXPECTED_CHANGE, payment.AMOUNT_OF_CHANGE);
    }
}
