package iv1350.integration;

import iv1350.dto.ItemDTO;

/**
 * A mock example of a data access object for item. Used for testing
 */
public class MockItemDAO implements ItemDAO {
    private static final ItemDTO YOGHURT =
            new ItemDTO(1, "Yoghurt",
                    "Greek yoghurt. Vanilla flavored",
                    20, 10);

    private static final ItemDTO MUESLI =
            new ItemDTO(2, "Müsli",
                    "Plain oat müsli", 30, 20);

    /**
     * Fetches information about an item in the form of an ItemDTO, when given a valid item id
     * <p>
     * In this mock example, the itemId is divided by 3 and the remainder determines the item as follows:
     * 0 -> invalid item (null)
     * 1 -> Yoghurt
     * 2 -> Muesli
     *
     * @param itemId An id of the item to fetch
     * @return A filled out ItemDTO, or null if no item exists
     */
    @Override
    public ItemDTO fetchItemById(int itemId) {
        if (itemId % 3 == 1) {
            return YOGHURT;
        } else if (itemId % 3 == 2) {
            return MUESLI;
        }
        return null;
    }

    /**
     * Decreases the quantity on record for an item in an external inventory system
     * <p>
     * In this mock example, this is a no-op
     *
     * @param itemId The id of the item whose quantity is to be decreased
     * @param quantityChange The amount by which the quantity will be decreased
     */
    @Override
    public void decreaseQuantityOfItem(int itemId, int quantityChange) {
    }
}
