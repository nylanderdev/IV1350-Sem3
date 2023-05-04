package iv1350.integration;

import iv1350.dto.ItemDTO;

/**
 * A Data Access Object used to interface with external persistence e.g. databases, for Items
 */
public interface ItemDAO {
    /**
     * Fetches information about an item in the form of an ItemDTO, when given a valid item id
     * @param itemId An id of the item to fetch
     * @return A filled out ItemDTO, or null if no item exists
     */
    ItemDTO fetchItemById(int itemId);

    /**
     * Decreases the quantity on record for an item in an external inventory system
     * @param itemId The id of the item whose quantity is to be decreased
     * @param quantityChange The amount by which the quantity will be decreased
     */
    void decreaseQuantityOfItem(int itemId, int quantityChange);
}
