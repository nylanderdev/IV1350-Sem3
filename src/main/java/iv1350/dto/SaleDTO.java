package iv1350.dto;

import java.util.List;
import java.util.Map;

/**
 * A data transfer object containing information on a sale
 */
public class SaleDTO {
    /**
     * The total cost of all items, after discounts and including VAT
     */
    public final int TOTAL_PRICE;
    /**
     * The total amount of VAT (included in the price)
     */
    public final int TOTAL_VAT;
    /**
     * The items being purchased and their respective quantities
     */
    public final Map<ItemDTO, Integer> ITEMS_AND_QUANTITIES;
    /**
     * The discounts added to the sale
     */
    public final List<DiscountDTO> DISCOUNTS;
    /**
     * The payment made. If the sale has yet to be paid, this will be null.
     */
    public final PaymentDTO PAYMENT;

    /**
     * Creates a new SaleDTO with the given values
     * @param totalPrice
     * @param totalVAT
     * @param itemsAndQuantities
     * @param discounts
     * @param payment
     */
    public SaleDTO(int totalPrice, int totalVAT, Map<ItemDTO, Integer> itemsAndQuantities,
                   List<DiscountDTO> discounts, PaymentDTO payment) {
        TOTAL_PRICE = totalPrice;
        TOTAL_VAT = totalVAT;
        ITEMS_AND_QUANTITIES = itemsAndQuantities;
        DISCOUNTS = discounts;
        PAYMENT = payment;
    }
}
