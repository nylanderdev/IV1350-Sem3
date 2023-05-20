package iv1350.integration;

import iv1350.dto.SaleDTO;

/**
 * A mock example of a data access object for sales. Used for testing
 */
public class MockSaleDAO implements SaleDAO {
    /**
     * Records the sale in the relevant external system
     * <p>
     * In this mock example, this is a no-op
     *
     * @param saleDTO The sale to be recorded
     */
    @Override
    public void recordSale(SaleDTO saleDTO) {
        if (saleDTO.TOTAL_PRICE < 0) {
            throw new DatabaseFailureException();
        }
    }
}
