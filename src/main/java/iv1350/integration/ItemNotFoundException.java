package iv1350.integration;

/**
 * An exception indicating that a requested item could not be found in the inventory system
 */
public class ItemNotFoundException extends Exception {
    /**
     * Creates an exception indicating that no item with the given id could be found
     * @param itemId The id of the non-existent item
     */
    public ItemNotFoundException(int itemId) {
        super(String.format("Item with given id %d not found", itemId));
    }
}
