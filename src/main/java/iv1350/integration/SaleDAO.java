package iv1350.integration;

import iv1350.dto.SaleDTO;

/**
 * A Data Access Object used to interface with external persistence e.g. databases, for Sales
 */
public interface SaleDAO {
    /**
     * Records the sale in the relevant external system
     * @param saleDTO The sale to be recorded
     */
    void recordSale(SaleDTO saleDTO);
}
