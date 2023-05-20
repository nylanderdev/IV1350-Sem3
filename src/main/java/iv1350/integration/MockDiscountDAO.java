package iv1350.integration;

import iv1350.dto.DiscountDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * A mock example of a data access object for discounts. Used for testing
 */
public class MockDiscountDAO implements DiscountDAO {
    /**
     * Returns all eligible discounts for an identified customer
     * <p>
     * In this mock example, customerIds divisible by 2 get 10% off,
     * those divisible by 3 get 25% off, divisible by both gets both discount,
     * and divisible by neither gets no discount
     *
     * @param customerId The id of the customer
     * @return
     */
    @Override
    public List<DiscountDTO> fetchDiscountsByCustomerId(int customerId) {
        if (customerId == DatabaseFailureException.DATABASE_FAILURE_TRIGGER_ID) {
            throw new DatabaseFailureException();
        }
        List<DiscountDTO> discounts = new ArrayList<>();
        if (customerId % 3 == 0) {
            discounts.add(new DiscountDTO(25));
        }
        if (customerId % 2 == 0) {
            discounts.add(new DiscountDTO(10));
        }
        return discounts;
    }
}
