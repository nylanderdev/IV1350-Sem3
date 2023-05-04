package iv1350.dto;

/**
 * A data transfer object containing information on a discount
 */
public class DiscountDTO {
    /**
     * An integer between from 0 to 100 indicating the percentage off that the discount gives
     */
    public final int PERCENTAGE_OFF;

    /**
     * Creates a new DiscountDTO with the relevant percentage discount
     * @param percentageOff The value of the discount, as a percentage (0 to 100)
     */
    public DiscountDTO(int percentageOff) {
        PERCENTAGE_OFF = percentageOff;
    }
}
