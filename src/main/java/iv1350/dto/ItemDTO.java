package iv1350.dto;

/**
 * A data transfer object containing information on an item
 */
public class ItemDTO {
    /**
     * A numerical id used to uniquely identify the item
     */
    public final int ID;
    /**
     * A human friendly name of the item
     */
    public final String NAME;
    /**
     * A human friendly description of the item
     */
    public final String DESCRIPTION;
    /**
     * The price of the item, including VAT
     */
    public final int PRICE;

    /**
     * The percentage (0 to 100) indicating the VAT rate of the item
     */
    public final int VAT_RATE;

    /**
     * Creates a new ItemDTO with the given values
     * @param id
     * @param name
     * @param description
     * @param price
     * @param vat_rate
     */
    public ItemDTO(int id, String name, String description, int price, int vat_rate) {
        ID = id;
        NAME = name;
        DESCRIPTION = description;
        PRICE = price;
        VAT_RATE = vat_rate;
    }
}
