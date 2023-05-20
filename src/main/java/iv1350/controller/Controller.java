package iv1350.controller;

import iv1350.dto.SaleDTO;
import iv1350.integration.ItemNotFoundException;
import iv1350.model.Sale;

/**
 * A Controller class exposing all system operations of the program
 */
public class Controller {
    private Sale currentSale;

    /**
     * Starts a new sale process. Use at the beginning of each sale.
     * @return The state of the new sale in the form of a SaleDTO
     */
    public SaleDTO startNewSale() {
        this.currentSale = new Sale();
        return this.currentSale.getSaleState();
    }

    /**
     * Adds an item with the given id to the ongoing sale
     * @param itemId An item to an id
     * @return The state of the current sale in the form of a SaleDTO
     */
    public SaleDTO addItemToSale(int itemId) throws ItemNotFoundException {
        currentSale.addItem(itemId);
        return currentSale.getSaleState();
    }

    /**
     * Adds multiple copies of the same item (given id) to the ongoing sale
     * @param itemId The id of the kind of item to add
     * @param quantity The number of items to add
     * @return The state of the current sale in the form of a SaleDTO
     */
    public SaleDTO addItemToSaleWithQuantity(int itemId, int quantity) throws ItemNotFoundException {
        currentSale.addItemWithQuantity(itemId, quantity);
        return currentSale.getSaleState();
    }

    /**
     * Finalizes the items to be purchased in the sale, disallowing further additions
     * @return The state of the current sale in the form of a SaleDTO
     */
    public SaleDTO endSale() {
        currentSale.finalizeItemSelection();
        return currentSale.getSaleState();
    }

    /**
     * Applies all discounts eligible for the customer (given id), if any
     * @param customerId The id of the customer requesting discounts
     * @return The state of the current sale in the form of a SaleDTO
     */
    public SaleDTO requestDiscount(int customerId) {
        currentSale.applyEligibleDiscounts(customerId);
        return currentSale.getSaleState();
    }

    /**
     * Registers a cash payment and ends the sale
     * @param cashPaid The amount paid
     * @return The final state of the sale in the form of a SaleDTO
     */
    public SaleDTO registerPaymentForSale(int cashPaid) throws ItemNotFoundException {
        currentSale.registerPayment(cashPaid);
        return currentSale.getSaleState();
    }
}
