package iv1350.integration;

import iv1350.dto.DiscountDTO;

import java.util.List;

/**
 * A Data Access Object used to interface with external persistence e.g. databases, for Discounts
 */
public interface DiscountDAO {
    /**
     * Fetches a list of all discounts a customer (identified by id) is eligible for
     * @param customerId The id of the customer
     * @return A list of all eligible discounts, can be empty but never null
     */
    List<DiscountDTO> fetchDiscountsByCustomerId(int customerId);
}
