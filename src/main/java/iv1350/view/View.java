package iv1350.view;

import iv1350.controller.Controller;
import iv1350.dto.DiscountDTO;
import iv1350.dto.ItemDTO;
import iv1350.dto.SaleDTO;

import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * A class handling user interface
 */
public class View {
    private SaleDTO lastSaleState;
    private Controller controller;

    /**
     * Starts the user interface and calls relevant system operations
     * @param controller A controller object exposing system operations
     */
    public void run(Controller controller) {
        this.controller = controller;
        final int VALID_ITEM1 = 1;
        final int VALID_ITEM2 = 2;
        final int INVALID_ITEM = 3;
        lastSaleState = controller.startNewSale();
        addItem(VALID_ITEM1);
        addItem(VALID_ITEM2, 3);
        addItem(INVALID_ITEM);
        addItem(VALID_ITEM1);
        endSale();
        requestDiscount();
        registerPayment();
        renderReceipt(lastSaleState);
    }

    private void registerPayment() {
        final int OVERPAYMENT = 25;
        lastSaleState = controller.registerPaymentForSale(lastSaleState.TOTAL_PRICE + OVERPAYMENT);
        System.out.printf("Give the customer %d¤ in change\n\n", lastSaleState.PAYMENT.AMOUNT_OF_CHANGE);
    }

    private void requestDiscount() {
        System.out.println("Searching for discounts...");
        SaleDTO saleState = controller.requestDiscount(2);
        if (saleState.TOTAL_PRICE != lastSaleState.TOTAL_PRICE) {
            System.out.printf("Discounts applied! New total is %d¤\n\n", saleState.TOTAL_PRICE);
        } else {
            System.out.println("No discounts found :(\n");
        }
        lastSaleState = saleState;
    }

    private void endSale() {
        lastSaleState = controller.endSale();
        System.out.printf("Sale finalized. Total is %d¤\n\n", lastSaleState.TOTAL_PRICE);
    }

    private void addItem(int id, int quantity) {
        SaleDTO newSaleState = controller.addItemToSaleWithQuantity(id, quantity);
        renderItemAdded(newSaleState);
    }

    private void addItem(int id) {
        SaleDTO newSaleState = controller.addItemToSale(id);
        renderItemAdded(newSaleState);
    }

    private void renderItemAdded(SaleDTO newSaleState) {
        Set<Map.Entry<ItemDTO, Integer>> itemsAdded = new HashSet<>(newSaleState.ITEMS_AND_QUANTITIES.entrySet());
        itemsAdded.removeAll(lastSaleState.ITEMS_AND_QUANTITIES.entrySet());
        if (itemsAdded.isEmpty()) {
            System.out.println(" ! Invalid item id ! \n");
        } else {
            for (Map.Entry<ItemDTO, Integer> itemAndQuantity : itemsAdded) {
                ItemDTO itemAdded = itemAndQuantity.getKey();
                int runningQuantity = itemAndQuantity.getValue();
                System.out.printf("Added %s\n", itemAdded.NAME);
                System.out.printf(" *total of %d units, at %d¤\n", runningQuantity, itemAdded.PRICE * runningQuantity);
                System.out.printf(" - %s\n\n", itemAdded.DESCRIPTION);
                System.out.printf("Total is %d¤\n\n", newSaleState.TOTAL_PRICE);
            }
        }
        this.lastSaleState = newSaleState;
    }
    private void renderReceipt(SaleDTO saleState) {
        System.out.print("--- Receipt ---\n");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(timeFormatter.format(saleState.PAYMENT.TIME_OF_SALE));

        for (Map.Entry<ItemDTO, Integer> itemAndQuantity : saleState.ITEMS_AND_QUANTITIES.entrySet()) {
            ItemDTO item = itemAndQuantity.getKey();
            int quantity = itemAndQuantity.getValue();
            System.out.printf("%s x%d (%d%% VAT) : %d¤\n", item.NAME, quantity, item.VAT_RATE, quantity * item.PRICE);
        }
        System.out.println();
        for (DiscountDTO discountDTO : saleState.DISCOUNTS) {
            System.out.printf(" ! Discount: %d%% off\n", discountDTO.PERCENTAGE_OFF);
        }
        System.out.printf("Total: %d¤,\n including %d¤ of VAT\n", saleState.TOTAL_PRICE, saleState.TOTAL_VAT);
        System.out.print("---------------\n");
        System.out.printf("You paid: %d¤\n", saleState.PAYMENT.AMOUNT_PAID);
        System.out.printf("Change back: %d¤\n", saleState.PAYMENT.AMOUNT_OF_CHANGE);
        System.out.print("---------------\n");
    }

    private static <T> T last(Collection<T> collection) {
        T element = null;
        Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            element = iterator.next();
        }
        return element;
    }
}
