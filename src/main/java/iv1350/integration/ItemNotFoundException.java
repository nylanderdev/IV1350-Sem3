package iv1350.integration;

/**
 * An exception indicating that a requested item could not be found in the inventory system
 */
public class ItemNotFoundException extends Exception {
    private final int itemId;
    /**
     * Creates an exception indicating that no item with the given id could be found
     * @param itemId The id of the non-existent item
     */
    public ItemNotFoundException(int itemId) {
        super(String.format("Item with given id %d not found", itemId));
        this.itemId = itemId;
    }

    /**
     * Returns the id of the nonexistent item
     * @return The id of the item that was not found
     */
    public int getItemId() {
        return itemId;
    }
}
