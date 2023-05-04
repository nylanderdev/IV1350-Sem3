package iv1350.integration;

/**
 * A collection of all Data Access Objects required by the model package
 */
public class DAOCollection {
    private DiscountDAO discountDAO;
    private ItemDAO itemDAO;
    private SaleDAO saleDAO;
    private static DAOCollection singleton;

    /**
     * Creates a new DAOCollection containing the given DAOs
     * @param discountDAO A data access object for discounts
     * @param itemDAO A data access object for items
     * @param saleDAO A data access object for sales
     */
    public DAOCollection(DiscountDAO discountDAO, ItemDAO itemDAO, SaleDAO saleDAO) {
        this.discountDAO = discountDAO;
        this.itemDAO = itemDAO;
        this.saleDAO = saleDAO;
    }

    /**
     * Returns a singleton set up to contain all necessary DAOs for the model
     * @return A DAOCollection object if set, else null
     */
    public static DAOCollection getSingleton() {
        return singleton;
    }

    /**
     * Sets the DAOCollection singleton, necessary for the model package to function
     * @param daoCollectionSingleton A filled out DAOCollection object to be used by the model
     */
    public static void setSingleton(DAOCollection daoCollectionSingleton) {
        singleton = daoCollectionSingleton;
    }

    /**
     * Returns the Discount data access object
     * @return The DAO for discounts
     */
    public DiscountDAO getDiscountDAO() {
        return this.discountDAO;
    }

    /**
     * Returns the Item data access object
     * @return The DAO for items
     */
    public ItemDAO getItemDAO() {
        return this.itemDAO;
    }

    /**
     * Returns the Sale data access object
     * @return The DAO for sales
     */
    public SaleDAO getSaleDAO() {
        return this.saleDAO;
    }
}
