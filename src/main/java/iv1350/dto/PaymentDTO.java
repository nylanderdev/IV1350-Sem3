package iv1350.dto;

import java.time.LocalDateTime;

/**
 * A data transfer object containing information on a payment
 */
public class PaymentDTO {
    /**
     * The amount the customer has paid (may exceed the total price of a sale)
     */
    public final int AMOUNT_PAID;
    /**
     * The amount the customer should be given back
     */
    public final int AMOUNT_OF_CHANGE;
    /**
     * The time the payment was made and the sale completed
     */
    public final LocalDateTime TIME_OF_SALE;

    public PaymentDTO(int amountPaid, int amountOfChange, LocalDateTime timeOfSale) {
        AMOUNT_PAID = amountPaid;
        AMOUNT_OF_CHANGE = amountOfChange;
        TIME_OF_SALE = timeOfSale;
    }
}
