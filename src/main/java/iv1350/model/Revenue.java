package iv1350.model;


import iv1350.Observer;

/**
 * A class representing the revenue of a point of sale
 */
public class Revenue {
    private final Observer<Integer> revenue = new Observer<>();
    {
        revenue.update(0);
    }

    /**
     * Adds the total of a sale to the revenue
     * @param sale The sale to add to revenue
     */
    public void addSaleToRevenue(Sale sale) {
        Integer newRevenue = revenue.get() + sale.getSaleState().TOTAL_PRICE;
        revenue.update(newRevenue);
    }

    /**
     * Exposes an observable object describing the amount of revenue
     * @return An observable revenue
     */
    public Observer<Integer> getObservable() {
        return revenue;
    }
}
